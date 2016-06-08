/**
 * 
 */
package whs.gdi2.tippspiel.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

import whs.gdi2.tippspiel.database.models.*;
import whs.gdi2.tippspiel.gui.SpielerRankingEM2016Frame;
import whs.gdi2.tippspiel.log.Log;

public class RankingHelper {
	 public static void calculateRanking(MySQLConnection con) throws Exception {
		 Log.info("Start Ranking calculation");
		 try {
			 Statement stmt = con.getConnection().createStatement();
			 Map<Integer, PlayerRanking> guesser = new HashMap<Integer, PlayerRanking>();
			 Date now = new Date();
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String currentTime = sdf.format(now);
			 
			 String sqlQuery = "SELECT * FROM tipps AS ti " +
					 	"JOIN spiele AS sp on ("
					 	+ "ti.spieleid = sp.spieleid"
					 	+ ")"
					 	+ "WHERE sp.datumuhrzeit < DATE_SUB(NOW(), INTERVAL 3 HOUR)"
					 	+ "ORDER BY ti.benutzerid";
			 
			 ResultSet rs = stmt.executeQuery(sqlQuery);
			 
			 while(rs.next()) {
				 if(guesser.get(rs.getInt("benutzerid")) != null) {
					 guesser.get(rs.getInt("benutzerid")).setPoints(guesser.get(rs.getInt("benutzerid")).getPoints() + getPoints(rs));
				 }
				 else {
					 PlayerRanking tmp = new PlayerRanking();
					 tmp.setPoints(getPoints(rs));
					 tmp.setPlayerID(rs.getInt("benutzerid"));
					 tmp.setDate(now); 
					 guesser.put(rs.getInt("benutzerid"), tmp);
				 }
			 }
			 
			 
			 Object[] tipper = guesser.values().toArray();
			 Arrays.sort(tipper);
			 Statement insert = con.getConnection().createStatement();
			 
			 for(int i = 0; i < tipper.length; i++) {
				 ((PlayerRanking)tipper[i]).setPlatz(i+1);
				 String insertsql = "INSERT INTO ranking (benutzerid, datum, punkte, platz) VALUES"
					 		+ "(" + ((PlayerRanking)tipper[i]).getPlayerID() + ", '" +
					 		currentTime + "',"
					 		+ ((PlayerRanking)tipper[i]).getPoints() + ","
					 		+ ((PlayerRanking)tipper[i]).getPlatz() + ")";
				 insert.executeUpdate(insertsql);
				 Log.debug("SQL Query for rankinginsert + " + insertsql);
			 }
			 
		} catch (SQLException e) {
			Log.mysqlError(e.getMessage());
			throw e;
		}
		 catch(Exception e) {
			 Log.error(e.getMessage());
			 throw e;
		 }
	 }
	 
	 
	 private static int getPoints(ResultSet rs) throws SQLException {
		 int points = 0;
		 // halbzeit
		 if(rs.getInt("tippheimhz") == rs.getInt("heimmannschafthz") && rs.getInt("tippgasthz") == rs.getInt("gastmannschafthz")) {
			 points += RankingPoints.EXACT_AFTER_HALFTIME.scoring;
			 Log.info("User " + rs.getInt("benutzerid") + " has guess the correct halftime results for game " + rs.getInt("spieleid"));
		 }
		 else {
			 points += getTendecy(rs.getInt("tippheimhz"), rs.getInt("tippgasthz"), rs.getInt("heimmannschafthz"), rs.getInt("gastmannschafthz"), 1).scoring;

			 Log.info("User " + rs.getInt("benutzerid") + " has guess the correct tendency of the halftime results for game " + rs.getInt("spieleid"));
		 }
		 // ende
		 if(rs.getInt("tippheimende") == rs.getInt("heimmannschaftende") && rs.getInt("tippgastende") == rs.getInt("gastmannschaftende")) {
			 points += RankingPoints.EXACT_AFTER_REGULAR_GAME.scoring;
			 Log.info("User " + rs.getInt("benutzerid") + " has guess the correct results for game " + rs.getInt("spieleid"));
			 
		 }
		 else {
			 points += getTendecy(rs.getInt("tippheimende"), rs.getInt("tippgastende"), rs.getInt("heimmannschaftende"), rs.getInt("gastmannschaftende"), 2).scoring;
			 Log.info("User " + rs.getInt("benutzerid") + " has guess the correct tendency of the results for game " + rs.getInt("spieleid"));
		 }

		 //verlängerung
		 if(rs.getInt("heimmannschaftverl") != 0) {
			 if(rs.getInt("tippheimverl") == rs.getInt("heimmannschaftverl") && rs.getInt("tippgastverl") == rs.getInt("gastmannschaftverl")) {
				 points += RankingPoints.EXACT_AFTER_FULL_GAME.scoring;

				 Log.info("User " + rs.getInt("benutzerid") + " has guess the correct results after overtime for game " + rs.getInt("spieleid"));
			 }
			 else {
				 points += getTendecy(rs.getInt("tippheimverl"), rs.getInt("tippgastverl"), rs.getInt("heimmannschaftverl"), rs.getInt("gastmannschaftverl"), 3).scoring;
				 Log.info("User " + rs.getInt("benutzerid") + " has guess the correct tendency of the results after overtime for game " + rs.getInt("spieleid"));
			 }
		 }
		 //elfmeter
		 if(rs.getInt("heimmannschaftelf") != 0) {
			 if(rs.getInt("tippheimelf") == rs.getInt("heimmannschaftelf") && rs.getInt("tippgastelf") == rs.getInt("gastmannschaftelf")) {
				 points += RankingPoints.EXACT_AFTER_PENALTY.scoring;
				 Log.info("User " + rs.getInt("benutzerid") + " has guess the correct results after penalty for game " + rs.getInt("spieleid"));
			 }
			 else {
				 points += getTendecy(rs.getInt("tippheimelf"), rs.getInt("tippgastelf"), rs.getInt("heimmannschaftelf"), rs.getInt("gastmannschaftelf"), 4).scoring;
				 Log.info("User " + rs.getInt("benutzerid") + " has guess the correct tendency of the results after penalty for game " + rs.getInt("spieleid"));
			 }
		 }			

		 if(rs.getInt("tippgelbeheim") == rs.getInt("tippgelbeheim")) {
			 points += RankingPoints.CORRECT_AMOUNT_YELLOW_CARD_HOME.scoring;
			 Log.info("User " + rs.getInt("benutzerid") + " has guess the correct amount of yellow cards for game " + rs.getInt("spieleid"));

		 }	
		 if(rs.getInt("tippgelbegast") == rs.getInt("tippgelbegast")) {
			 points += RankingPoints.CORRECT_AMOUNT_YELLOW_CARD_GUEST.scoring;
			 Log.info("User " + rs.getInt("benutzerid") + " has guess the correct amount of yellow cards for game " + rs.getInt("spieleid"));

		 }	
		 if((rs.getInt("tipproteheim") == rs.getInt("tipproteheim") && rs.getInt("tipproteheim") != 0)) {
			 points += RankingPoints.CORRECT_AMOUNT_RED_CARD_IF_AMOUNT_NE_ZERO_HOME.scoring;
			 Log.info("User " + rs.getInt("benutzerid") + " has guess the correct amount of red cards for game " + rs.getInt("spieleid"));

		 }
		 if(rs.getInt("tipprotegast") == rs.getInt("tipprotegast") && rs.getInt("tipprotegast") != 0) {
			 points += RankingPoints.CORRECT_AMOUNT_RED_CARD_IF_AMOUNT_NE_ZERO_GUEST.scoring;
			 Log.info("User " + rs.getInt("benutzerid") + " has guess the correct amount of red cards for game " + rs.getInt("spieleid"));

		 }
		 Log.debug("Points after calculation: " + points + " for userid " + rs.getInt("benutzerid"));
		 
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
