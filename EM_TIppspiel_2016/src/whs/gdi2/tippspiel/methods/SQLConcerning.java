package whs.gdi2.tippspiel.methods;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.Scanner;

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
					+ "heimmannschaft VARCHAR(20), gastmannschaft VARCHAR(20), heimmannschafthz INT(2),"
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

	/**
	 * Method for reading and adding all the 'benutzer' and 'tipps' test datas
	 * to the database
	 */
	public static String addTestData() {
		final String[] s = new String[] { "benutzer", "tipps" };
		boolean tempBool = false;

		try {
			statement = connection.createStatement();
			FileReader fr = new FileReader("data\\benutzer.txt");
			BufferedReader br = new BufferedReader(fr);
			while (true) {
				String tempString = br.readLine();
				if (tempString == null) {
					fr.close();
					br.close();
					break;
				}
				String[] tempStringArray = tempString.split(";");
				int tempInt = Integer.parseInt(tempStringArray[9]);
				statement.executeUpdate(
						"INSERT INTO " + s[0] + " (IP, sessionID, nickname, passwort, gruppenname, email, show_Email) "
								+ "VALUES ('" + tempStringArray[3] + "','" + tempStringArray[4] + "','"
								+ tempStringArray[5] + "','" + tempStringArray[6] + "','" + tempStringArray[7] + "','"
								+ tempStringArray[8] + "'," + tempInt + ")");
			}
			FileReader fr2 = new FileReader("data\\tipps.txt");
			BufferedReader br2 = new BufferedReader(fr2);
			while (true) {
				String tempString = br2.readLine();
				if (tempString == null) {
					fr2.close();
					br2.close();
					break;
				}
				String[] tempStringArray = tempString.split(";");
				int[] tempIntArray = new int[tempStringArray.length];
				for (int k = 0; k < tempIntArray.length; k++) {
					if (tempStringArray[k].equals("")) {
						tempIntArray[k] = 0;
						tempStringArray[k] = null;
					} else {
						tempIntArray[k] = Integer.parseInt(tempStringArray[k]);
					}
				}
				statement.executeUpdate("INSERT INTO " + s[1]
						+ " (tippid, benutzerid, spieleid, tippdatum, tippheimhz, tippgasthz, tippheimende, tippgastende, tippheimverl, tippgastverl, "
						+ "tippheimelf, tippgastelf, tippgelbeheim, tippgelbegast, tipproteheim, tipprotegast) "
						+ "VALUES (" + tempIntArray[0] + "," + tempIntArray[1] + "," + tempIntArray[2] + ","
						+ tempStringArray[3] + "," + tempIntArray[4] + "," + tempIntArray[5] + "," + tempIntArray[6]
						+ "," + tempIntArray[7] + "," + tempIntArray[8] + "," + tempIntArray[9] + "," + tempIntArray[10]
						+ "," + tempIntArray[11] + "," + tempIntArray[12] + "," + tempIntArray[13] + ","
						+ tempIntArray[14] + "," + tempIntArray[15] + ")");
			}
			statement.close();
			return "Inserted test datas.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Inserting datas failed.";
		}
	}

	/** Method for adding the 'spiele' test datas */
	public static String addSpieleTestData() {

		try {
			statement = connection.createStatement();
			FileReader fr = new FileReader("data\\spiele_test.txt");
			BufferedReader br = new BufferedReader(fr);
			while (true) {
				String[] tempStringArray = null;
				String tempString = br.readLine();
				if (tempString == null) {
					fr.close();
					br.close();
					break;
				}
				tempStringArray = tempString.replaceAll("endOfLine", "").split(";");
				int[] tempIntArray = new int[tempStringArray.length];
				for (int i = 0; i < tempStringArray.length; i++) {
					if (tempStringArray[i].equals("/N")) {
						tempStringArray[i] = null;
					}
					if (tempStringArray[i].equals("")) {
						tempIntArray[i] = 0;
						tempStringArray[i] = null;
					} else {
						try {
							tempIntArray[i] = Integer.parseInt(tempStringArray[i]);
						} catch (NumberFormatException e) {
						}
					}
				}
				if (tempStringArray.length == 20) {
					statement.executeUpdate("INSERT INTO spiele (spielbezeichnung, spielort,"
							+ "datumuhrzeit, heimmannschaft, gastmannschaft, heimmannschafthz, gastmannschafthz, heimmannschaftende,"
							+ "gastmannschaftende, verlaengerung, heimmannschaftverl, gastmannschaftverl,"
							+ "elfmeter, heimmannschaftelf, gastmannschaftelf, gelbekartenheim, gelbekartengast,"
							+ "rotekartenheim, rotekartengast) VALUES ('" + tempStringArray[1] + "','"
							+ tempStringArray[2] + "','" + tempStringArray[3] + "','" + tempStringArray[4] + "','"
							+ tempStringArray[5] + "'," + tempIntArray[6] + "," + tempIntArray[7] + ","
							+ tempIntArray[8] + "," + tempIntArray[9] + "," + +tempIntArray[10] + "," + tempIntArray[11]
							+ "," + tempIntArray[12] + "," + tempIntArray[13] + "," + tempIntArray[14] + ","
							+ tempIntArray[15] + "," + tempIntArray[16] + "," + tempIntArray[17] + ","
							+ tempIntArray[18] + "," + tempIntArray[19] + ")");
				} else {
					statement.executeUpdate("INSERT INTO spiele (spielbezeichnung, spielort,"
							+ "datumuhrzeit, heimmannschaft, gastmannschaft) VALUES ('" + tempStringArray[1] + "','"
							+ tempStringArray[2] + "','" + tempStringArray[3] + "','" + tempStringArray[4] + "','"
							+ tempStringArray[5] + "')");
				}
			}
			statement.close();
			return "Inserted spiele's test datas.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Inserting spiele's datas failed.";
		}
	}
}
