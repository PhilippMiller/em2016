/**
 * 
 */
package whs.gdi2.tippspiel.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import whs.gdi2.tippspiel.database.models.*;

/**
 * @author Mario
 *
 */
public class RankingHelper {
	
	 public void calculateRanking(MySQLConnection con) {
		 try {
			 Statement stmt = con.getConnection().createStatement();
			 List<PlayerRanking> Guesser = new ArrayList<PlayerRanking>();
			 Date now = new Date();
			 
			 String sqlQuery = "SELECT * FROM tipps AS ti" +
					 	"JOIN spiele AS sp on ("
					 	+ "sp.spieleid = sp.spieleid )"
					 	+ "ORDER BY ti.spieleid";
			 
			 ResultSet rs = stmt.executeQuery(sqlQuery);
			 
			 while(rs.next()) {
				 PlayerRanking tmp = new PlayerRanking();
				 tmp.setPoints(getPoints(rs));
				 tmp.setPlayerID(rs.getInt("benutzerid"));
				 tmp.setDate(now);
			 }
		} catch (SQLException e) {
			
		}
		 
	 }
	 
	 private int getPoints(ResultSet rs) throws SQLException {
		 int points = 0;
		 // halbzeit
		 if(rs.getInt("tippheimhz") == rs.getInt("heimmannschafthz") && rs.getInt("tippgasthz") == rs.getInt("gastmannschafthz")) {
			 points += RankingPoints.EXACT_AFTER_HALFTIME.scoring;
			 
		 }
		 else {
			 points += getTendecy(rs.getInt("tippheimhz"), rs.getInt("tippgasthz"), rs.getInt("heimmannschafthz"), rs.getInt("gastmannschafthz"), true).scoring;
		 }
		 // ende
		 if(rs.getInt("tippheimende") == rs.getInt("heimmannschaftende") && rs.getInt("tippgastende") == rs.getInt("gastmannschaftende")) {
			 points += RankingPoints.EXACT_AFTER_HALFTIME.scoring;
			 
		 }
		 else {
			 points += getTendecy(rs.getInt("tippheimende"), rs.getInt("tippgastende"), rs.getInt("heimmannschaftende"), rs.getInt("gastmannschaftende"), false).scoring;
		 }
		 
		 return points;
	 }
	 
	 private RankingPoints getTendecy(int tippheim, int tippgast, int ergheim, int erggast, boolean isHalfTime) {
		 if(erggast > ergheim && tippgast > tippheim) {
			 return (isHalfTime) ? RankingPoints.TENDENCY_AFTER_HALFTIME : RankingPoints.TENDENCY_AFTER_FULL_GAME;
		 }
		 if(erggast < ergheim && tippgast < tippheim) {
			 return (isHalfTime) ? RankingPoints.TENDENCY_AFTER_HALFTIME : RankingPoints.TENDENCY_AFTER_FULL_GAME;
			 
		 }
		 return RankingPoints.ZERO;
	 }
	 
}
