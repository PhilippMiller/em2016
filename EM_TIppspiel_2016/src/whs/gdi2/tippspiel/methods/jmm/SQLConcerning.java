package whs.gdi2.tippspiel.methods.jmm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

/**
 * This class contains all methods we need to describe, read and write our SQL
 * database
 * 
 * @author Jan-Markus Momper
 * @version 1.1
 */

public class SQLConcerning {

	static Connection connection = null;
	static Statement statement = null;
	static PreparedStatement updSql = null;

	/** Loading SQL driver */
	public static String loadDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return "Driver successfully initialized!";
		} catch (ClassNotFoundException e) {
			return "Could not find / open driver...";
		}
	}

	/** Connect to live - sql */
	public static String connectToLiveDB() {

		String ip = "jdbc:mysql://192.168.56.101:";
		String port = "3306";
		String userName = "jmm";
		String password = "testimctestface";
		String url = ip + port + "/";

		/** Close possibly existing connections. */
		if (connection != null) {
			try {
				connection.close();
				System.out.println("Old connection closed.");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Closing connection failed.");
			}
		}

		/** Connect */
		try {
			System.out.println("Try to connect...");
			connection = DriverManager.getConnection(url, userName, password);
			return "Connection succesfully established.";
		} catch (SQLException ex) {
			ex.printStackTrace();
			return "Connection failure!";
		}
	}

	/** Connect to test - sql */
	public static String connectToTestDB() {

		final String ip = "jdbc:mysql://192.168.56.101:";
		final String port = "3306";
		final String userName = "jmm";
		final String password = "testimctestface";
		final String url = ip + port + "/";

		/** Close possibly existing connections. */
		if (connection != null) {
			try {
				connection.close();
				System.out.println("Old connection closed.");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Closing connection failed.");
			}
		}

		/** Connect */
		try {
			System.out.println("Try to connect...");
			connection = DriverManager.getConnection(url, userName, password);
			return "Connection succesfully established.";
		} catch (SQLException ex) {
			ex.printStackTrace();
			return "Connection failure!";
		}
	}

	/** Disconnect from sql */
	public static String disconnect() {
		if (connection != null) {
			try {
				connection.close();
				return "Connection closed.";
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Closing connection failed.");
			}
		}
		return "No connection to close!";
	}

	/** Creates necessary db */
	public static String createDB() {

		try {
			statement = connection.createStatement();
			statement.executeUpdate("create database em2016");
			statement.close();
			return "Database em2016 created!";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Database em2016 already exists.";
		}
	}

	/** Creates necessary tables */
	public static String createTables() {

		String sqlInput = "CREATE TABLE ";
		String[] tableNames = new String[] { "benutzer", "spiele", "ranking", "tipps" };
		try {
			statement = connection.createStatement();
			statement.executeUpdate("USE em2016");
			statement.executeUpdate(
					sqlInput + tableNames[0] + " (benutzerid INT(11) NOT NULL AUTO_INCREMENT primary key,"
							+ "benutzerName VARCHAR(20), autologin VARCHAR(32), IP VARCHAR(15) NOT NULL,"
							+ "sessionID VARCHAR(32) NOT NULL, nickname VARCHAR(30) NOT NULL, passwort VARCHAR(32) NOT NULL,"
							+ "gruppenname VARCHAR(32), email VARCHAR(70) NOT NULL, show_Email BIT(1),"
							+ "registrierungsdatum DATE)");
			statement.executeUpdate(sqlInput + tableNames[1] + " (spieleid INT(10) NOT NULL AUTO_INCREMENT primary key,"
					+ "spielbezeichnung VARCHAR(20), spielort VARCHAR(20), datumuhrzeit DATETIME,"
					+ "heimmannschaft VARCHAR(20), gastmannschaft VARCHAR(20), heimmanschafthz INT(2),"
					+ "gastmannschafthz INT(2), heimmannschaftende INT(2), gastmannschaftende INT(2),"
					+ "verlaengerung BIT(1), heimmannschaftverl INT(2), gastmannschaftverl INT(2),"
					+ "elfmeter BIT(1), heimmannschaftelf INT(2), gastmannschaftelf INT(2),"
					+ "gelbekartenheim INT(2), gelbekartengast INT(2), rotekartenheim INT(2),"
					+ "rotekartengast INT(2))");
			statement.executeUpdate(sqlInput + tableNames[2] + " (datum DATETIME, benutzerid INT(11),"
					+ "punkte INT(10), platz INT(10))");
			statement.executeUpdate(sqlInput + tableNames[3] + " (tippid INT(10) NOT NULL AUTO_INCREMENT primary key,"
					+ "benutzerid INT(10), spieleid INT(10), tippdatum DATETIME, tippheimhz INT(4),"
					+ "tippgasthz INT(4), tippheimende INT(4), tippgastende INT(4), tippheimverl INT(4),"
					+ "tippgastverl INT(4), tippheimelf INT(4), tippgastelf INT(4), tippgelbeheim INT(4),"
					+ "tippgelbegast INT(4), tipproteheim INT(4), tipprotegast INT(4))");
			statement.close();
			return "Tables in em2016 created!";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Creating tables failed.";
		}
	}

	/** Method for reading and adding all the test datas to the database */
	public static String addTestData() {
		final String[] s = new String[] { "benutzer", "tipps" };
		boolean tempBool = false;

		try {
			statement = connection.createStatement();
			String userdir = System.getProperty("user.dir");
			FileReader fr = new FileReader(userdir + "\\data\\benutzer.txt");
			BufferedReader br = new BufferedReader(fr);
			while (true) {
				String tempString = br.readLine();
				if (tempString == null) {
					break;
				}
				String[] tempStringArray = tempString.split(";");
				statement.executeUpdate(
						"INSERT INTO " + s[0] + " (IP, sessionID, nickname, passwort, gruppenname, email) " + "VALUES '"
								+ tempStringArray[3] + "','" + tempStringArray[4] + "','" + tempStringArray[5] + "','"
								+ tempStringArray[6] + "','" + tempStringArray[7] + "','" + tempStringArray[8] + "'");

				updSql = connection.prepareStatement("INSERT INTO (show_Email) VALUES (?)");
				String tempSplitString = tempStringArray[9];
				int tempInt = Integer.parseInt(tempSplitString);
				if (tempInt == 1) {
					tempBool = true;
				}
				if (tempInt == 0) {
					tempBool = false;
				}
				updSql.setBoolean(0, tempBool);
				updSql.executeUpdate();
			}
			statement.close();
			updSql.close();
			return "Inserted test datas.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Inserting datas failed.";
		}
	}
}
