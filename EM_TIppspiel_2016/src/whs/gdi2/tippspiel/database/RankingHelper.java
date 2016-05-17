/**
 * 
 */
package whs.gdi2.tippspiel.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import whs.gdi2.tippspiel.database.models.*;
import whs.gdi2.tippspiel.log.Log;

/**
 * @author Mario
 *
 */
public class RankingHelper {
	// not finished jet
	 public static boolean calculateRanking(MySQLConnection con) {
		 try {
			 Statement stmt = con.getConnection().createStatement();
			 Date now = new Date();
			 
			 String sqlQuery = "SELECT * FROM tipps AS ti " +
					 	"JOIN spiele AS sp on ("
					 	+ "ti.spieleid = sp.spieleid"
					 	+ ") "
					 	+ "ORDER BY ti.spieleid";
			 
			 ResultSet rs = stmt.executeQuery(sqlQuery);
			 
			 while(rs.next()) {
				 String sql = "INSERT INTO ranking (datum, benutzerid, punkte, platz)";
				 
				 PlayerRanking tmp = new PlayerRanking();
				 
				 
				 tmp.setPoints(getPoints(rs));
				 tmp.setPlayerID(rs.getInt("benutzerid"));
				 tmp.setDate(now);
				 
			 }
		} catch (SQLException e) {
			Log.mysqlError(e.getMessage());
		}
		 return false;
	 }
	 
	 private static int getPoints(ResultSet rs) throws SQLException {
		 int points = 0;
		 // halbzeit
		 if(rs.getInt("tippheimhz") == rs.getInt("heimmannschafthz") && rs.getInt("tippgasthz") == rs.getInt("gastmannschafthz")) {
			 points += RankingPoints.EXACT_AFTER_HALFTIME.scoring;
			 
		 }
		 else {
			 points += getTendecy(rs.getInt("tippheimhz"), rs.getInt("tippgasthz"), rs.getInt("heimmannschafthz"), rs.getInt("gastmannschafthz"), 1).scoring;
		 }
		 // ende
		 if(rs.getInt("tippheimende") == rs.getInt("heimmannschaftende") && rs.getInt("tippgastende") == rs.getInt("gastmannschaftende")) {
			 points += RankingPoints.EXACT_AFTER_REGULAR_GAME.scoring;
			 
		 }
		 else {
			 points += getTendecy(rs.getInt("tippheimende"), rs.getInt("tippgastende"), rs.getInt("heimmannschaftende"), rs.getInt("gastmannschaftende"), 2).scoring;
		 }

		 //verlängerung
		 if(rs.getInt("tippheimverl") == rs.getInt("heimmannschaftverl") && rs.getInt("tippgastverl") == rs.getInt("gastmannschaftverl")) {
			 points += RankingPoints.EXACT_AFTER_FULL_GAME.scoring;	 
		 }
		 else {
			 points += getTendecy(rs.getInt("tippheimverl"), rs.getInt("tippgastverl"), rs.getInt("heimmannschaftverl"), rs.getInt("gastmannschaftverl"), 3).scoring;
		 }
		 //verlängerung
		 if(rs.getInt("tippheimelf") == rs.getInt("heimmannschaftelf") && rs.getInt("tippgastelf") == rs.getInt("gastmannschaftelf")) {
			 points += RankingPoints.EXACT_AFTER_PENALTY.scoring;	 
		 }
		 else {
			 points += getTendecy(rs.getInt("tippheimelf"), rs.getInt("tippgastelf"), rs.getInt("heimmannschaftelf"), rs.getInt("gastmannschaftelf"), 4).scoring;
		 }
		 Log.debug("Points after calculation:" + points + " for userid " + rs.getInt("benutzerid"));
		 
		 return points;
	 }
	 
	 private static RankingPoints getTendecy(int tippheim, int tippgast, int ergheim, int erggast, int type) {
		 if((erggast > ergheim && tippgast > tippheim) || (erggast < ergheim && tippgast < tippheim)) {
			 switch(type) {
			 case 1:
				 return RankingPoints.TENDENCY_AFTER_HALFTIME;
			 case 2:
				 return RankingPoints.TENDENCY_AFTER_REGULAR_GAME;
			 case 3:
				 return RankingPoints.TENDENCY_AFTER_FULL_GAME;
			 case 4:
				 return RankingPoints.TENDEMCY_AFTER_PENALTY;
			 }
		 }

		 return RankingPoints.ZERO;
	 }
	 
}
