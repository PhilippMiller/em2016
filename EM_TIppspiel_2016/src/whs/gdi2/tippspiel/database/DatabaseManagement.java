package whs.gdi2.tippspiel.database;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import whs.gdi2.tippspiel.Config;
import whs.gdi2.tippspiel.Main;
import whs.gdi2.tippspiel.database.models.*;
import whs.gdi2.tippspiel.log.Log;

/**
 * This class contains all methods we need to describe, read and write for MySQL
 * 
 * 
 * @author Jan-Markus Momper
 * @author Mario Kellner
 * @version 1.1
 */

public class DatabaseManagement {

	static int offset = 0;
	static int spieleIDinErgebnisFrame = 0;
	static SpieleStatic spiele = null;

	/** Creates necessary db */
	public static String createDB(MySQLConnection connection) {

		if (connection.createOrRecreateDatabase()) {
			return "Database created successfully.";
		}

		return "Database creation failed.";
	}

	public static boolean dropTables(MySQLConnection connection) {
		try {
			Statement statement = connection.getConnection().createStatement();
			statement.execute("DROP TABLE IF EXISTS " + Config.getTableUser());
			statement.execute("DROP TABLE IF EXISTS " + Config.getTableGames());
			statement.execute("DROP TABLE IF EXISTS " + Config.getTableRanking());
			statement.execute("DROP TABLE IF EXISTS " + Config.getTableBets());
			return true;

		} catch (SQLException e) {
			Log.error(e.getMessage());
			return false;
		}
	}

	/** Creates necessary tables */
	public static boolean createTables(MySQLConnection connection) {
		String[] tables = { "CREATE TABLE " + Config.getTableUser() + " ("
				+ "benutzerid INT(11) NOT NULL AUTO_INCREMENT primary key," + "benutzerName VARCHAR(20),"
				+ "autologin VARCHAR(32)," + "IP VARCHAR(15) NOT NULL," + "sessionID VARCHAR(32) NOT NULL,"
				+ "nickname VARCHAR(30) NOT NULL," + "passwort VARCHAR(32) NOT NULL," + "gruppenname VARCHAR(32),"
				+ "email VARCHAR(70) NOT NULL," + "show_Email BIT(1)," + "registrierungsdatum DATE" + ")",
				"CREATE TABLE " + Config.getTableGames() + " ("
						+ "spieleid INT(10) NOT NULL AUTO_INCREMENT primary key," + "spielbezeichnung VARCHAR(20),"
						+ "spielort VARCHAR(20)," + "datumuhrzeit DATETIME," + "heimmannschaft VARCHAR(20),"
						+ "gastmannschaft VARCHAR(20)," + "heimmannschafthz INT(2)," + "gastmannschafthz INT(2),"
						+ "heimmannschaftende INT(2)," + "gastmannschaftende INT(2)," + "verlaengerung BIT(1),"
						+ "heimmannschaftverl INT(2)," + "gastmannschaftverl INT(2)," + "elfmeter BIT(1),"
						+ "heimmannschaftelf INT(2)," + "gastmannschaftelf INT(2)," + "gelbekartenheim INT(2),"
						+ "gelbekartengast INT(2)," + "rotekartenheim INT(2)," + "rotekartengast INT(2)" + ")",
				"CREATE TABLE " + Config.getTableRanking() + " (" + "datum DATETIME," + "benutzerid INT(11),"
						+ "punkte INT(10), " + "platz INT(10)" + ")",
				"CREATE TABLE " + Config.getTableBets() + "(" + "tippid INT(10) NOT NULL AUTO_INCREMENT primary key,"
						+ "benutzerid INT(10)," + "spieleid INT(10)," + "tippdatum DATETIME," + "tippheimhz INT(4),"
						+ "tippgasthz INT(4)," + "tippheimende INT(4)," + "tippgastende INT(4),"
						+ "tippheimverl INT(4)," + "tippgastverl INT(4)," + "tippheimelf INT(4),"
						+ "tippgastelf INT(4)," + "tippgelbeheim INT(4)," + "tippgelbegast INT(4),"
						+ "tipproteheim INT(4)," + "tipprotegast INT(4)" + ")" };
		try {
			Statement statement = connection.getConnection().createStatement();

			for (String table : tables) {
				statement.executeUpdate(table);
				Log.debug(table);
			}

			Log.info("Tables created in " + connection.getDatabase() + ".");
			return true;
		} catch (SQLException e) {
			Log.error("Error while creating tables for " + connection.getDatabase() + ".");
			Log.debug(e.getMessage());

			return false;
		}
	}

	/**
	 * Method for reading and adding all the 'benutzer' and 'tipps' test datas
	 * to the database
	 */
	public static boolean addTestData(MySQLConnection connection) {

		try {
			Statement statement = connection.getConnection().createStatement();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					DatabaseManagement.class.getResourceAsStream("/whs/gdi2/tippspiel/data/benutzer.txt")));

			String readline = "";
			String sql = "INSERT INTO " + Config.getTableUser()
					+ "\n(benutzerName, autologin, IP, sessionID, nickname, passwort, gruppenname,email,show_Email,registrierungsdatum)\n"
					+ "VALUES";

			while ((readline = br.readLine()) != null) {
				String[] str = (readline + "NULL").replaceAll("endOfLine", "").split(";");
				str = Arrays.copyOfRange(str, 1, str.length);

				for (int i = 0; i < str.length; i++) {
					if (str[i].equals("")) {
						str[i] = "NULL";
					}
					if (i >= 2 && i <= 7) {
						str[i] = "'" + str[i] + "'";
					}
				}

				sql += "\n(";
				sql += String.join(",", str);
				sql += "),";
			}
			sql = sql.substring(0, sql.length() - 1);

			statement.executeUpdate(sql);

			Log.info("'" + Config.getTableUser() + "' inserted into database.");

			BufferedReader br2 = new BufferedReader(new InputStreamReader(
					DatabaseManagement.class.getResourceAsStream("/whs/gdi2/tippspiel/data/tipps.txt")));
			sql = "INSERT INTO " + Config.getTableBets()
					+ "(benutzerid, spieleid, tippdatum, tippheimhz, tippgasthz, tippheimende, tippgastende, tippheimverl, tippgastverl, "
					+ "tippheimelf, tippgastelf, tippgelbeheim, tippgelbegast, tipproteheim, tipprotegast)" + "VALUES";

			while ((readline = br2.readLine()) != null) {
				String[] str = readline.replaceAll("endOfLine", "").split(";");
				str = Arrays.copyOfRange(str, 1, str.length);
				for (int i = 0; i < str.length; i++) {
					if (str[i].equals("")) {
						str[i] = "NULL";
					}
				}
				sql += "\n(";
				sql += String.join(",", str);
				sql += "),";
			}
			sql = sql.substring(0, sql.length() - 1);

			statement.executeUpdate(sql);

			Log.info("'" + Config.getTableBets() + "' inserted into database.");
			BufferedReader br3 = new BufferedReader(new InputStreamReader(
					DatabaseManagement.class.getResourceAsStream("/whs/gdi2/tippspiel/data/spiele_test.txt")));

			while ((readline = br3.readLine()) != null) {
				String[] str = readline.replaceAll("endOfLine", "").split(";");
				str = Arrays.copyOfRange(str, 1, str.length);
				if (str.length == 5) {
					sql = "INSERT INTO " + Config.getTableGames()
							+ "(spielbezeichnung, spielort, datumuhrzeit, heimmannschaft, gastmannschaft)" + "VALUES";
					for (int i = 0; i < str.length; i++) {
						if (str[i].equals("")) {
							str[i] = "NULL";
						} else {
							str[i] = "'" + str[i] + "'";
						}
					}
				} else {
					sql = "INSERT INTO " + Config.getTableGames()
							+ "(spielbezeichnung, spielort, datumuhrzeit, heimmannschaft, gastmannschaft, heimmannschafthz, gastmannschafthz, heimmannschaftende,"
							+ "gastmannschaftende, verlaengerung, heimmannschaftverl, gastmannschaftverl,"
							+ "elfmeter, heimmannschaftelf, gastmannschaftelf, gelbekartenheim, gelbekartengast,"
							+ "rotekartenheim, rotekartengast) VALUES ";
					for (int i = 0; i < str.length; i++) {
						if (str[i].equals("")) {
							str[i] = "NULL";
						}
						if (i <= 4) {
							str[i] = "'" + str[i] + "'";
						}
					}
				}
				sql += "(";
				sql += String.join(",", str);
				sql += ")";

				statement.executeUpdate(sql);
			}

			Log.info("'" + Config.getTableGames() + "' inserted into database.");
			statement.close();
			return true;
		} catch (Exception e) {
			Log.error(e.getMessage());
			return false;
		}
	}

	/**
	 * Reads data from 'spiele'
	 */
	public static DefaultTableModel implementMatchSchedule() {

		String col[] = { "Spielmodus", "Datum", "Anstoﬂ", "Heimmannschaft", "Gastmannschaft", "Spielort" };
		DefaultTableModel dtm = new DefaultTableModel(col, 0);

		try {
			Statement statement = Main.mainConnection.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM spiele");
			while (rs.next()) {

				String spielbezeichnung = rs.getString("spielbezeichnung");
				Date datumSQL = rs.getDate("datumuhrzeit");
				Date uhrzeitSQL = rs.getTime("datumuhrzeit");
				String heimmannschaft = rs.getString("heimmannschaft");
				String gastmannschaft = rs.getString("gastmannschaft");
				String spielort = rs.getString("spielort");
				String datum = new SimpleDateFormat("dd.MM.yyyy").format(datumSQL);
				String uhrzeit = new SimpleDateFormat("HH:mm").format(uhrzeitSQL);
				Object[] objs = { spielbezeichnung, datum, uhrzeit, heimmannschaft, gastmannschaft, spielort };
				dtm.addRow(objs);
			}
			return dtm;
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
		return null;
	}

	public static DefaultTableModel implementMatchScheduleWithScores() {

		String col[] = { "Gruppe", "Datum", "Anstoﬂ", "Heimmannschaft", "Gastmannschaft", "Ergebnis nach 1. HZ",
				"Ergebnis nach 2. HZ", "Ergebnis nach Verl‰ngerung", "Ergebnis nach Elfmeterschieﬂen", "Gelbe Karten",
				"Rote Karten", "Spielort" };
		DefaultTableModel dtm = new DefaultTableModel(col, 0);
		String ergebnisverl = null;
		String ergebniself = null;

		try {
			Statement statement = Main.mainConnection.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(
					"SELECT * FROM spiele WHERE datumuhrzeit < DATE_SUB(NOW(), INTERVAL 3 HOUR) AND gelbekartenheim IS NOT NULL ORDER BY datumuhrzeit");
			while (rs.next()) {

				String spielbezeichnung = rs.getString("spielbezeichnung");
				Date datumSQL = rs.getDate("datumuhrzeit");
				Date uhrzeitSQL = rs.getTime("datumuhrzeit");
				String heimmannschaft = rs.getString("heimmannschaft");
				String gastmannschaft = rs.getString("gastmannschaft");
				String spielort = rs.getString("spielort");
				String datum = new SimpleDateFormat("dd.MM.yyyy").format(datumSQL);
				String uhrzeit = new SimpleDateFormat("HH:mm").format(uhrzeitSQL);
				String ergebnis1hz = rs.getString("heimmannschafthz") + ":" + rs.getString("gastmannschafthz");
				String ergebnis2hz = rs.getString("heimmannschaftende") + ":" + rs.getString("gastmannschaftende");
				if (rs.getBoolean("verlaengerung")) {
					ergebnisverl = rs.getString("heimmannschaftverl") + ":" + rs.getString("gastmannschaftverl");
				} else {
					ergebnisverl = "-:-";
				}
				if (rs.getBoolean("elfmeter")) {
					ergebniself = rs.getString("heimmannschaftelf") + ":" + rs.getString("gastmannschaftelf");
				} else {
					ergebniself = "-:-";
				}
				String gelbekarten = rs.getString("gelbekartenheim") + " - " + rs.getString("gelbekartengast");
				String rotekarten = rs.getString("rotekartenheim") + " - " + rs.getString("rotekartengast");
				Object[] objs = { spielbezeichnung, datum, uhrzeit, heimmannschaft, gastmannschaft, ergebnis1hz,
						ergebnis2hz, ergebnisverl, ergebniself, gelbekarten, rotekarten, spielort };
				dtm.addRow(objs);
			}
			return dtm;
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
		return null;
	}

	public static DefaultTableModel implementUserTop10Data() {

		String col[] = { "Platz", "Nickname", "Punkte", "Tipprunde" };
		DefaultTableModel dtm = new DefaultTableModel(col, 0);
		Object[] objs = new Object[4];
		Object[] defaultObj = { "", "", "", "" };
		String nickname = null;
		String gruppe = null;

		try {
			Statement statement = Main.mainConnection.getConnection().createStatement();
			Statement statement1 = Main.mainConnection.getConnection().createStatement();

			ResultSet daters = statement1
					.executeQuery("SELECT datum FROM ranking WHERE datum < now() ORDER BY datum DESC LIMIT 1");
			if (daters.next()) {
				Log.debug(
						"SELECT * FROM ranking AS r JOIN benutzer AS b ON (r.benutzerid = b.benutzerid) WHERE datum = '"
								+ daters.getString("datum") + "' ORDER BY platz LIMIT 10");

				ResultSet rs = statement.executeQuery(
						"SELECT * FROM ranking AS r JOIN benutzer AS b ON (r.benutzerid = b.benutzerid) WHERE datum = '"
								+ daters.getString("datum") + "' ORDER BY platz LIMIT 10");
				while (rs.next()) {
					int ranking = rs.getInt("platz");
					String punkte = rs.getString("punkte");
					String benutzerid = rs.getString("benutzerid");
					nickname = rs.getString("nickname");
					gruppe = rs.getString("gruppenname");

					objs[0] = ranking;
					objs[1] = nickname;
					objs[2] = punkte;
					objs[3] = gruppe;
					dtm.addRow(objs);
				}
			}
			if (dtm.getRowCount() == 0) {
				dtm.addRow(defaultObj);
			}
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
		return dtm;
	}

	public static DefaultTableModel implementNext10Games() {

		String col[] = { "Spielmodus", "Datum", "Anstoﬂ", "Heimmannschaft", "Gastmannschaft" };
		DefaultTableModel dtm = new DefaultTableModel(col, 0);

		Object[] defaultObj = { "", "", "", "", "" };

		try {
			Statement statement = Main.mainConnection.getConnection().createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT * FROM spiele WHERE datumuhrzeit > NOW() ORDER BY datumuhrzeit LIMIT 10");

			while (rs.next()) {
				Object[] objs = new Object[5];

				Date datumSQL = rs.getDate("datumuhrzeit");
				Date uhrzeitSQL = rs.getTime("datumuhrzeit");

				objs[0] = rs.getString("spielbezeichnung");
				objs[1] = new SimpleDateFormat("dd.MM.yyyy").format(datumSQL);
				objs[2] = new SimpleDateFormat("HH:mm").format(uhrzeitSQL);
				objs[3] = rs.getString("gastmannschaft");
				objs[4] = rs.getString("heimmannschaft");

				dtm.addRow(objs);
			}
		} catch (Exception e) {
			Log.error(e.getMessage());
		}

		return dtm;
	}

	public static DefaultTableModel getGamesWithNoInfoData() {

		String col[] = { "Spielmodus", "Datum", "Anstoﬂ", "Heimmannschaft", "Gastmannschaft" };
		DefaultTableModel dtm = new DefaultTableModel(col, 0);

		Object[] defaultObj = { "", "", "", "", "" };

		try {
			Statement statement = Main.mainConnection.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(
					"SELECT * FROM spiele WHERE datumuhrzeit < DATE_ADD(NOW(), INTERVAL 3 HOUR) AND gelbekartenheim IS NULL ORDER BY datumuhrzeit LIMIT 1 OFFSET "
							+ getOffset());
			while (rs.next()) {
				Object[] objs = new Object[5];

				spieleIDinErgebnisFrame = rs.getInt("spieleid");

				Date datumSQL = rs.getDate("datumuhrzeit");
				Date uhrzeitSQL = rs.getTime("datumuhrzeit");

				objs[0] = rs.getString("spielbezeichnung");
				objs[1] = new SimpleDateFormat("dd.MM.yyyy").format(datumSQL);
				objs[2] = new SimpleDateFormat("HH:mm").format(uhrzeitSQL);
				objs[4] = rs.getString("gastmannschaft");
				objs[3] = rs.getString("heimmannschaft");

				dtm.addRow(objs);
			}
		} catch (Exception e) {
			Log.error(e.getMessage());

		}

		return dtm;
	}

	public static DefaultTableModel getGameIncompleteGamesModel() {

		String col[] = { "Spiel-ID", "Spielmodus", "Datum", "Anstoﬂ", "Heimmannschaft", "Gastmannschaft" };
		DefaultTableModel dtm = new DefaultTableModel(col, 0);

		Object[] defaultObj = { "", "", "", "", "", "" };

		try {
			Statement statement = Main.mainConnection.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(
					"SELECT spiele.spieleid, spiele.spielbezeichnung,spiele.spielort, spiele.gastmannschaft,	spiele.heimmannschaft, spiele.datumuhrzeit FROM spiele WHERE (gelbekartenheim IS NULL OR gelbekartengast IS NULL OR rotekartenheim IS NULL OR rotekartengast IS NULL OR gastmannschafthz IS NULL OR heimmannschafthz IS NULL OR gastmannschaftende IS NULL OR heimmannschaftende IS NULL OR heimmannschaftende IS NULL) AND datumuhrzeit < DATE_ADD(NOW(), INTERVAL 3 HOUR) ORDER BY datumuhrzeit");
			while (rs.next()) {
				Object[] objs = new Object[6];

				spieleIDinErgebnisFrame = rs.getInt("spieleid");

				Date datumSQL = rs.getDate("datumuhrzeit");
				Date uhrzeitSQL = rs.getTime("datumuhrzeit");

				objs[0] = rs.getString("spieleid");
				objs[1] = rs.getString("spielbezeichnung");
				objs[2] = new SimpleDateFormat("dd.MM.yyyy").format(datumSQL);
				objs[3] = new SimpleDateFormat("HH:mm").format(uhrzeitSQL);
				objs[4] = rs.getString("gastmannschaft");
				objs[5] = rs.getString("heimmannschaft");

				dtm.addRow(objs);
			}
		} catch (Exception e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		return dtm;
	}

	public static DefaultTableModel getGamesWithInfoData() {

		String col[] = { "Spielmodus", "Datum", "Anstoﬂ", "Heimmannschaft", "Gastmannschaft" };
		DefaultTableModel dtm = new DefaultTableModel(col, 0);
		int i = 0;

		int tempInt = getOffset() * (-1);

		try {
			Statement statement = Main.mainConnection.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(
					"SELECT * FROM spiele WHERE datumuhrzeit < DATE_ADD(NOW(), INTERVAL 3 HOUR) AND gelbekartenheim IS NOT NULL ORDER BY datumuhrzeit DESC LIMIT 1 OFFSET "
							+ tempInt);
			while (rs.next()) {
				SpieleStatic.setVerlaengerung(rs.getBoolean("verlaengerung"));
				SpieleStatic.setElfmeter(rs.getBoolean("elfmeter"));
				SpieleStatic.setHeimmannschafthz(rs.getString("heimmannschafthz"));
				SpieleStatic.setGastmannschafthz(rs.getString("gastmannschafthz"));
				SpieleStatic.setHeimmannschaftende(rs.getString("heimmannschaftende"));
				SpieleStatic.setGastmannschaftende(rs.getString("gastmannschaftende"));
				SpieleStatic.setHeimmannschaftverl(rs.getString("heimmannschaftverl"));
				SpieleStatic.setGastmannschaftverl(rs.getString("gastmannschaftverl"));
				SpieleStatic.setHeimmannschaftelf(rs.getString("heimmannschaftelf"));
				SpieleStatic.setGastmannschaftelf(rs.getString("gastmannschaftelf"));
				SpieleStatic.setGelbekartenheim(rs.getString("gelbekartenheim"));
				SpieleStatic.setGelbekartengast(rs.getString("gelbekartengast"));
				SpieleStatic.setRotekartenheim(rs.getString("rotekartenheim"));
				SpieleStatic.setRotekartengast(rs.getString("rotekartengast"));

				Object[] objs = new Object[5];

				spieleIDinErgebnisFrame = rs.getInt("spieleid");

				Date datumSQL = rs.getDate("datumuhrzeit");
				Date uhrzeitSQL = rs.getTime("datumuhrzeit");

				objs[0] = rs.getString("spielbezeichnung");
				objs[1] = new SimpleDateFormat("dd.MM.yyyy").format(datumSQL);
				objs[2] = new SimpleDateFormat("HH:mm").format(uhrzeitSQL);
				objs[4] = rs.getString("gastmannschaft");
				objs[3] = rs.getString("heimmannschaft");

				dtm.addRow(objs);
				i++;
			}
			if (i == 0) {
				Object[] dobjs = { "", "", "", "", "" };
				SpieleStatic.setVerlaengerung(false);
				SpieleStatic.setElfmeter(false);
				SpieleStatic.setHeimmannschafthz("");
				SpieleStatic.setGastmannschafthz("");
				SpieleStatic.setHeimmannschaftende("");
				SpieleStatic.setGastmannschaftende("");
				SpieleStatic.setHeimmannschaftverl("");
				SpieleStatic.setGastmannschaftverl("");
				SpieleStatic.setHeimmannschaftelf("");
				SpieleStatic.setGastmannschaftelf("");
				SpieleStatic.setGelbekartenheim("");
				SpieleStatic.setGelbekartengast("");
				SpieleStatic.setRotekartenheim("");
				SpieleStatic.setRotekartengast("");
				dtm.addRow(dobjs);
			}
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
		return dtm;
	}

	public static int getOffset() {
		return offset;
	}

	public static void setOffset(int offset) {
		DatabaseManagement.offset = offset;
	}

	public static void sendTextToDB(String text) {
		try {
			Statement statement = Main.mainConnection.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(text);
			while (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsCount = rsmd.getColumnCount();
				for (int i = 1; i <= columnsCount; i++) {
					System.out.println(rs.getString(i));
				}
			}
		} catch (SQLException e) {
			Log.error(e.getMessage());
		}
	}

	/**
	 * Note: Warum wird die SpieleID nicht als Parameter ¸bergeben? Das w¸rde
	 * n‰mlich part die statische Variable und ist sauberer vom code her.
	 * 
	 * TODO: refactoring der funktion
	 * 
	 * @return
	 */
	public static boolean isGroupPhase() {
		String tempString = null;
		try {
			Statement statement = Main.mainConnection.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM spiele WHERE spieleid = " + spieleIDinErgebnisFrame);
			while (rs.next()) {
				tempString = rs.getString("spielbezeichnung");
				if (tempString.contains("ruppe")) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
		return true;
	}

	public static void addGameData(String data) {
		try {
			Statement statement = Main.mainConnection.getConnection().createStatement();
			statement.executeUpdate(data + " WHERE spieleid = " + spieleIDinErgebnisFrame);
		} catch (Exception e) {
			Log.mysqlError(e.getMessage());
		}
	}

	// ACHTUNG, NUR DIE PROVISORISCHE METHODE, MUSS NOCH KORREKT ANGELEGT
	// WERDEN, PLATZHALTER!!!
	public static DefaultTableModel playerRanking() {

		String col[] = { "Platz", "Nickname", "Punkte", "Tipprunde" };
		DefaultTableModel dtm = new DefaultTableModel(col, 0);

		Object[] defaultObj = { "", "", "", "" };

		try {
			Statement statement = Main.mainConnection.getConnection().createStatement();
			Statement statement1 = Main.mainConnection.getConnection().createStatement();

			ResultSet daters = statement1
					.executeQuery("SELECT datum FROM ranking WHERE datum < now() ORDER BY datum DESC LIMIT 1");
			if (daters.next()) {
				ResultSet rs = statement.executeQuery(
						"SELECT * FROM ranking AS r JOIN benutzer AS b ON (r.benutzerid = b.benutzerid) WHERE datum = '"
								+ daters.getString("datum") + "' ORDER BY platz");

				while (rs.next()) {
					int ranking = rs.getInt("platz");
					String punkte = rs.getString("punkte");
					String benutzerid = rs.getString("benutzerid");
					String nickname = rs.getString("nickname");
					String gruppe = rs.getString("gruppenname");

					Object[] objs = new Object[5];

					objs[0] = ranking;
					objs[1] = nickname;
					objs[2] = punkte;
					objs[3] = gruppe;

					dtm.addRow(objs);
				}
			}
		} catch (Exception e) {
			Log.error(e.getMessage());
		}

		return dtm;
	}

	// In arbeit die ich noch nicht verstehe
	public static DefaultTableModel groupRanking(String grp) {

		String col[] = { "Mannschaft", "Spiele", "Siege", "Unentschieden", "Niederlagen", "Erzielte Tore", "Gegentore",
				"Tordifferenz", "Punkte" };
		DefaultTableModel dtm = new DefaultTableModel(col, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		Object[] obj = new Object[9];

		try {
			ParticipantsField pf = new ParticipantsField();
			Group group;
			ArrayList<GroupRankingMatch> matches = null;
			ArrayList<Team> teams;

			Statement statement = Main.mainConnection.getConnection().createStatement();
			
				ResultSet rs = statement.executeQuery("SELECT * FROM spiele WHERE gelbekartenheim IS NOT NULL AND spielbezeichnung LIKE '%Gruppe "
								+ grp + "%'");
				group = new Group();
				pf.containsGroup(group);
				matches = new ArrayList<GroupRankingMatch>();

				try {
					while (rs.next()) {
						GroupRankingMatch match = new GroupRankingMatch();
						
						group.setGroupName(rs.getString("spielbezeichnung"));
						
						match.setGameId(rs.getInt("spieleid"));
						match.setHomeTeamEnd(rs.getInt("heimmannschaftende"));
						match.setGuestTeamEnd(rs.getInt("gastmannschaftende"));
						match.setHomeTeamName(rs.getString("heimmannschaft"));
						match.setGuestTeamName(rs.getString("gastmannschaft"));
						match.setRedCardsHome(rs.getInt("rotekartenheim"));
						match.setYellowCardsHome(rs.getInt("gelbekartenheim"));
						match.setRedCardsGuest(rs.getInt("rotekartengast"));
						match.setYellowCardsGuest(rs.getInt("gelbekartengast"));
						match.setPenalty(rs.getBoolean("elfmeter"));
	
						matches.add(match);
					}
				} catch (SQLException e) {
					Log.mysqlError("[GRP_RAN]Error while reading from database. | Error: " + e.getMessage());
				}
				teams = new ArrayList<Team>();
				int i = 1;
				for (GroupRankingMatch match : matches) {
					Team team1 = new Team();					
					team1.setTeamName(match.getHomeTeamName());
					team1.setGoals(match.getHomeTeamEnd());
					team1.setYellowCards(match.getYellowCardsHome());
					team1.setRedCards(match.getRedCardsHome());
					
					Team team2 = new Team();
					team2.setTeamName(match.getGuestTeamName());
					team2.setGoals(match.getGuestTeamEnd());
					team2.setYellowCards(match.getYellowCardsGuest());
					team2.setRedCards(match.getRedCardsGuest());
					
					
					//System.out.println(i++ + ") " + team1.getTeamName() + " : " + team2.getTeamName());
					
					boolean no1find = false;
					boolean no2find = false;
					if (group.getTeam().size() != 0) {
						for (Team teamItem : group.getTeam()) {
							if (teamItem.getTeamName().equals(team1.getTeamName())) {
								teamItem.setGoals(teamItem.getGoals() + team1.getGoals());
								teamItem.setGoalsAgainst(teamItem.getGoalsAgainst() + team2.getGoals());
								teamItem.setYellowCards(teamItem.getYellowCards() + team1.getYellowCards());
								teamItem.setRedCards(teamItem.getRedCards() + team1.getRedCards());
								if (team1.getGoals() > team2.getGoals()) {
									teamItem.setPoints(teamItem.getPoints() + 3);
								} else if  (team1.getGoals() == team2.getGoals()) {
									teamItem.setPoints(teamItem.getPoints() + 1);
								}
								no1find = true;
							}
							if (teamItem.getTeamName().equals(team2.getTeamName())) {
								teamItem.setGoals(teamItem.getGoals() + team2.getGoals());
								teamItem.setGoalsAgainst(teamItem.getGoalsAgainst() + team1.getGoals());
								teamItem.setYellowCards(teamItem.getYellowCards() + team2.getYellowCards());
								teamItem.setRedCards(teamItem.getRedCards() + team2.getRedCards());
								if (team2.getGoals() > team1.getGoals()) {
									teamItem.setPoints(teamItem.getPoints() + 3);
								} else if  (team2.getGoals() == team1.getGoals()) {
									teamItem.setPoints(teamItem.getPoints() + 1);
								}
								no2find = true;
							}
						}
					}
					if (!no1find) {
						Team newTeam1 = new Team();
						newTeam1.setPf(group);
						group.includesTeam(newTeam1);
						newTeam1.setTeamName(team1.getTeamName());
						System.out.println("1_INSERT: " + team1.getTeamName());
						newTeam1.setGoals(team1.getGoals());
						newTeam1.setGoalsAgainst(team2.getGoals());
						newTeam1.setYellowCards(team1.getYellowCards());
						newTeam1.setRedCards(team1.getRedCards());
						
						if (team1.getGoals() > team2.getGoals()) {
							newTeam1.setPoints(newTeam1.getPoints() + 3);
						} else if  (team1.getGoals() == team2.getGoals()) {
							newTeam1.setPoints(newTeam1.getPoints() + 1);
						}
						teams.add(newTeam1);
					}
					if (!no2find) {
						Team newTeam2 = new Team();
						newTeam2.setPf(group);
						group.includesTeam(newTeam2);
						newTeam2.setTeamName(team2.getTeamName());
						System.out.println("2_INSERT: " + team2.getTeamName());
						newTeam2.setGoals(team2.getGoals());
						newTeam2.setGoalsAgainst(team1.getGoals());
						newTeam2.setYellowCards(team2.getYellowCards());
						newTeam2.setRedCards(team2.getRedCards());
						
						if (team1.getGoals() > team2.getGoals()) {
							newTeam2.setPoints(newTeam2.getPoints() + 3);
						} else if  (team1.getGoals() == team2.getGoals()) {
							newTeam2.setPoints(newTeam2.getPoints() + 1);
						}
						teams.add(newTeam2);
					}
				}
				// INNERE GRUPPE VERGLEICHEN
				Object[] myTeams = teams.toArray();
				Arrays.sort(myTeams);
				System.out.println(myTeams.length + "\n");
				for (Object team : myTeams) {
					Team myTeam = (Team)team;
					obj[0] = myTeam.getTeamName();
					obj[1] = "-";
					obj[2] = "-";
					obj[3] = "-";
					obj[4] = "-";
					obj[5] = myTeam.getGoals();
					obj[6] = myTeam.getGoalsAgainst();
					obj[7] = (myTeam.getGoals() - myTeam.getGoalsAgainst());
					obj[8] = myTeam.getPoints();
					dtm.addRow(obj);
				}
				

			/* 
			 * Mannschaft | Spiele | Siege | Unentschieden | Niederlagen | Erzielte Tore | Gegentore | Tordifferenz | Punkte
			 * Last thing to do!
			 * dtm.addRow(obj);
			 */

		} catch (SQLException e) {
			Log.mysqlError(e.getMessage());
		} catch (Exception e) {
			Log.error("[GRP_RAN] Error: " + e.getMessage());
			e.printStackTrace();
		}

		return dtm;
	}

	public static DefaultTableModel getGameTableModel(int spielId) {
		String col[] = { "Spielmodus", "Datum", "Anstoﬂ", "Heimmannschaft", "Gastmannschaft" };
		DefaultTableModel dtm = new DefaultTableModel(col, 0);

		try {
			Statement statement = Main.mainConnection.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM spiele WHERE spieleid = " + spielId);
			while (rs.next()) {
				Object[] objs = new Object[5];

				Date datumSQL = rs.getDate("datumuhrzeit");
				Date uhrzeitSQL = rs.getTime("datumuhrzeit");

				objs[0] = rs.getString("spielbezeichnung");
				objs[1] = new SimpleDateFormat("dd.MM.yyyy").format(datumSQL);
				objs[2] = new SimpleDateFormat("HH:mm").format(uhrzeitSQL);
				objs[4] = rs.getString("gastmannschaft");
				objs[3] = rs.getString("heimmannschaft");

				dtm.addRow(objs);
			}
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
		return dtm;

	}

}