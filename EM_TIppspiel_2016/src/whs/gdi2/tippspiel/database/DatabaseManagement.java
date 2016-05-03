package whs.gdi2.tippspiel.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

import whs.gdi2.tippspiel.Config;
import whs.gdi2.tippspiel.gui.SplashFrame;
import whs.gdi2.tippspiel.log.Log;

/**
 * This class contains all methods we need to describe, read and write our SQL
 * database
 * 
 * @author Jan-Markus Momper
 * @author Mario Kellner
 * @version 1.1
 */

public class DatabaseManagement {

	/** Creates necessary db */
	public static String createDB(MySQLConnection connection) {
		
		if(connection.createOrRecreateDatabase()) {
			return "Database created successfully";
		}

		return "Creation of the Database failed";
	}

	/** Creates necessary tables */
	public static String createTables(MySQLConnection connection) {
		String[] tables = {
				"CREATE TABLE " + Config.getTableUser() + " ("
						+ "benutzerid INT(11) NOT NULL AUTO_INCREMENT primary key,"
						+ "benutzerName VARCHAR(20),"
						+ "autologin VARCHAR(32),"
						+ "IP VARCHAR(15) NOT NULL,"
						+ "sessionID VARCHAR(32) NOT NULL,"
						+ "nickname VARCHAR(30) NOT NULL,"
						+ "passwort VARCHAR(32) NOT NULL,"
						+ "gruppenname VARCHAR(32),"
						+ "email VARCHAR(70) NOT NULL,"
						+ "show_Email BIT(1),"
						+ "registrierungsdatum DATE"
						+ ")",
					"CREATE TABLE " + Config.getTableGames() + " ("
						+ "spieleid INT(10) NOT NULL AUTO_INCREMENT primary key,"
						+ "spielbezeichnung VARCHAR(20),"
						+ "spielort VARCHAR(20),"
						+ "datumuhrzeit DATETIME,"
						+ "heimmannschaft VARCHAR(20),"
						+ "gastmannschaft VARCHAR(20),"
						+ "heimmannschafthz INT(2),"
						+ "gastmannschafthz INT(2),"
						+ "heimmannschaftende INT(2),"
						+ "gastmannschaftende INT(2),"
						+ "verlaengerung BIT(1),"
						+ "heimmannschaftverl INT(2),"
						+ "gastmannschaftverl INT(2),"
						+ "elfmeter BIT(1),"
						+ "heimmannschaftelf INT(2),"
						+ "gastmannschaftelf INT(2),"
						+ "gelbekartenheim INT(2),"
						+ "gelbekartengast INT(2),"
						+ "rotekartenheim INT(2),"
						+ "rotekartengast INT(2)"
						+ ")",
				"CREATE TABLE " + Config.getTableRanking() + " ("
						+ "datum DATETIME,"
						+ "benutzerid INT(11),"
						+ "punkte INT(10), "
						+ "platz INT(10)"
						+ ")",
				"CREATE TABLE " + Config.getTableHint() + "("
						+ "tippid INT(10) NOT NULL AUTO_INCREMENT primary key,"
						+ "benutzerid INT(10),"
						+ "spieleid INT(10),"
						+ "tippdatum DATETIME,"
						+ "tippheimhz INT(4),"
						+ "tippgasthz INT(4),"
						+ "tippheimende INT(4),"
						+ "tippgastende INT(4),"
						+ "tippheimverl INT(4),"
						+ "tippgastverl INT(4),"
						+ "tippheimelf INT(4),"
						+ "tippgastelf INT(4),"
						+ "tippgelbeheim INT(4),"
						+ "tippgelbegast INT(4),"
						+ "tipproteheim INT(4),"
						+ "tipprotegast INT(4)"
						+ ")"
		};
		try {
			Statement statement = connection.getConnection().createStatement();
			
			for(String table : tables) {
				statement.executeUpdate(table);
			}
			Log.info("Tables created in " + connection.getDatabase());
			return "Tables created in " + connection.getDatabase();
		} catch (SQLException e) {
			Log.error("Error while creating tables for" + connection.getDatabase());
			return "Error while creating tables for" + connection.getDatabase();
		}
	}

	/**
	 * Method for reading and adding all the 'benutzer' and 'tipps' test datas
	 * to the database
	 */
	public static String addTestData(MySQLConnection connection) {
		boolean tempBool = false;

		try {
			Statement statement = connection.getConnection().createStatement();
			BufferedReader br = new BufferedReader(
				new InputStreamReader(
					DatabaseManagement.class.getResourceAsStream("/whs/gdi2/tippspiel/data/benutzer.txt")
				)
			);
			
			String readline = "";
			String sql = "INSERT INTO " + Config.getTableUser() + " (IP, sessionID, nickname, passwort, gruppenname, email, show_Email) "
					+ "VALUES";
			
			while((readline = br.readLine()) != null) {
				String[] str = readline.replaceAll("endOfLine", "").split(";");
				str = Arrays.copyOfRange(str, 1, str.length);
				
				sql += "('";
				sql += String.join("','", str);
				sql += "')\n,";
			}
			sql = sql.substring(0, sql.length()-1);
			
			statement.executeUpdate(sql);

			Log.info("User inserted into Database");
			
			BufferedReader br2 = new BufferedReader(
				new InputStreamReader(
					DatabaseManagement.class.getResourceAsStream("/whs/gdi2/tippspiel/data/tipps.txt")
				)
			);
			sql = "INSERT INTO " + Config.getTableHint() + "(tippid, benutzerid, spieleid, tippdatum, tippheimhz, tippgasthz, tippheimende, tippgastende, tippheimverl, tippgastverl, "
						+ "tippheimelf, tippgastelf, tippgelbeheim, tippgelbegast, tipproteheim, tipprotegast)"
					+ "VALUES";
			
			while((readline = br2.readLine()) != null) {
				String[] str = readline.replaceAll("endOfLine", "").split(";");
				str = Arrays.copyOfRange(str, 1, str.length);
				
				sql += "('";
				sql += String.join("','", str);
				sql += "')\n,";
			}
			sql = sql.substring(0, sql.length()-1);
			
			statement.executeUpdate(sql);

			Log.info("Hints inserted into Database");
			BufferedReader br3 = new BufferedReader(
				new InputStreamReader(
					DatabaseManagement.class.getResourceAsStream("/whs/gdi2/tippspiel/data/spiele_test.txt")
				)
			);
			
			while((readline = br2.readLine()) != null) {
				String[] str = readline.replaceAll("endOfLine", "").split(";");
				str = Arrays.copyOfRange(str, 1, str.length);
				if(str.length == 5) {
					sql = "INSERT INTO " + Config.getTableGames() + "(spielbezeichnung, spielort, datumuhrzeit, heimmannschaft, gastmannschaft)" +
							"VALUES";
				}
				else {
					sql = "INSERT INTO spiele (spielbezeichnung, spielort, datumuhrzeit, heimmannschaft, gastmannschaft, heimmannschafthz, gastmannschafthz, heimmannschaftende," +
							"gastmannschaftende, verlaengerung, heimmannschaftverl, gastmannschaftverl,"
							+ "elfmeter, heimmannschaftelf, gastmannschaftelf, gelbekartenheim, gelbekartengast,"
							+ "rotekartenheim, rotekartengast) VALUES ";
				}
				sql += "('";
				sql += String.join("','", str);
				sql += "')\n,";
			}
			
			statement.executeUpdate(sql);
			Log.info("Games inserted into Database");
			statement.close();
			return "Inserted test datas.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Inserting datas failed.";
		}
	}
}
