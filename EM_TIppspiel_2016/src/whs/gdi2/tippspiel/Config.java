package whs.gdi2.tippspiel;

import java.awt.image.BufferedImageFilter;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import whs.gdi2.tippspiel.log.Log;

/**
 * 
 * @version 1.0
 * @author Mario Kellner <mario.kellner@studmail.w-hs.de>
 * @author Jan-Markus Momper <jan-markus.momper@studmail.w-hs.de>
 * @author Philipp Miller <philipp.miller@studmail.w-hs.de>
 *
 */
public class Config {

	private static final String homeDir = System.getProperty("user.home") + "/w_hs_tippspiel";
	private static final String logDir = homeDir + "/log";
	private static final String configFile = "application.ini";

	private static final String version = "1.0";
	private static final String[] autor = { "Jan-Markus Momper", "Mario Kellner", "Philipp Miller" };

	private static final String table_user = "benutzer";
	private static final String table_games = "spiele";
	private static final String table_ranking = "ranking";
	private static final String table_hint = "tipps";

	private static Properties pt;

	private static boolean DBType;

	private static String DBIp_online;
	private static String DBUser_online;
	private static String DBPass_online;
	private static String DB_online;

	private static String DBIp_offline;
	private static String DBUser_offline;
	private static String DBPass_offline;
	private static String DB_offline;

	public static String getConfigfile() {
		return configFile;
	}

	public static String getHomeDir() {
		return homeDir;
	}

	public static String getLogDir() {
		return logDir;
	}

	public static String getVersion() {
		return version;
	}

	public static String[] getAutor() {
		return autor;
	}

	public static String getTableUser() {
		return table_user;
	}

	public static String getTableGames() {
		return table_games;
	}

	public static String getTableRanking() {
		return table_ranking;
	}

	public static String getTableHint() {
		return table_hint;
	}

	public static Properties getPt() {
		return pt;
	}

	/*
	 * DB Parameter
	 */

	public static void setDBType(boolean dBType) {
		DBType = dBType;
	}

	public static boolean isDBType() {
		return DBType;
	}

	public static String getDBIp_online() {
		return DBIp_online;
	}

	public static void setDBIp_online(String dBIp_online) {
		DBIp_online = dBIp_online;
	}

	public static String getDBUser_online() {
		return DBUser_online;
	}

	public static void setDBUser_online(String dBUser_online) {
		DBUser_online = dBUser_online;
	}

	public static String getDBPass_online() {
		return DBPass_online;
	}

	public static void setDBPass_online(String dBPass_online) {
		DBPass_online = dBPass_online;
	}

	public static String getDB_online() {
		return DB_online;
	}

	public static void setDB_online(String dB_online) {
		DB_online = dB_online;
	}

	public static String getDBIp_offline() {
		return DBIp_offline;
	}

	public static void setDBIp_offline(String dBIp_offline) {
		DBIp_offline = dBIp_offline;
	}

	public static String getDBUser_offline() {
		return DBUser_offline;
	}

	public static void setDBUser_offline(String dBUser_offline) {
		DBUser_offline = dBUser_offline;
	}

	public static String getDBPass_offline() {
		return DBPass_offline;
	}

	public static void setDBPass_offline(String dBPass_offline) {
		DBPass_offline = dBPass_offline;
	}

	public static String getDB_offline() {
		return DB_offline;
	}

	public static void setDB_offline(String dB_offline) {
		DB_offline = dB_offline;
	}

	/**
	 * Loads the configuration file.
	 * 
	 * @return pt (properties file) if the configuration file loaded
	 *         successfully
	 * @return null if the configuration file can't be loaded
	 */
	private static Properties loadFile() {
		try {
			pt = new Properties();
			pt.load(new FileInputStream(new File(Config.getHomeDir() + "/" + Config.getConfigfile())));
			Log.info("Successfully load config File.");
			return pt;
		} catch (Exception e) {
			Log.error("Can't load config file from [" + Config.getConfigfile() + "] Exception: " + e.getMessage());
			return null;
		}

	}

	/**
	 * Loads the configuration step by step out of the .ini file.
	 * 
	 * @return true if the configuration file is completely loaded
	 * @return false if the configuration file can't be loaded
	 */
	public static boolean load() {
		pt = Config.loadFile();
		if (pt != null) {

			if (pt.getProperty("DBType").equals("true")) {
				Config.setDBType(true);
			} else {
				Config.setDBType(false);
			}

			Config.setDBIp_online(pt.getProperty("DBIp_online"));
			Config.setDBUser_online(pt.getProperty("DBUser_online"));
			Config.setDBPass_online(pt.getProperty("DBPass_online"));
			Config.setDB_online(pt.getProperty("DB_online"));

			Config.setDBIp_offline(pt.getProperty("DBIp_offline"));
			Config.setDBUser_offline(pt.getProperty("DBUser_offline"));
			Config.setDBPass_offline(pt.getProperty("DBPass_offline"));
			Config.setDB_offline(pt.getProperty("DB_offline"));

			Log.info("Config successfully loaded.");
			return true;
		} else {
			Log.error("Can't load config data!");
			return false;
		}
	}

	/**
	 * Writes to the configuration file, if it exists else its create it
	 * 
	 * @return true if configuration was successfully wrote
	 * @return false if something went wrong by writing to the configuration
	 *         file
	 */
	public static boolean write(HashMap<String, String> map) {
		try {
			File cfgFile = new File(Config.getHomeDir() + "/" + Config.getConfigfile());
			if (!cfgFile.exists()) {
				cfgFile.createNewFile();
				Log.info("Successfully create config file.");
			}

			if (pt == null) {
				pt = new Properties();
				pt.load(new FileInputStream(new File(Config.getHomeDir() + "/" + Config.getConfigfile())));
				Log.info("Succesfully load config File.");
			}

			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				pt.setProperty((String) pair.getKey(), (String) pair.getValue());
				if (!pair.getKey().equals("DBType")) {
					Method setter = Config.class.getMethod("set" + pair.getKey(), String.class);
					setter.invoke(null, pair.getValue());
				} else {
					if (pair.getValue().equals("true")) {
						setDBType(true);
					} else {
						setDBType(false);
					}
				}

			}
			pt.store(new FileOutputStream(new File(getHomeDir() + "/" + getConfigfile())), "");

			Log.info("Successfully write to config file.");
			return true;
		} catch (Exception e) {
			Log.error("Can't write to config file! Exception: " + e.getMessage());
			return false;
		}
	}

	public static boolean createDefault() {
		try {
			File cfgFile = new File(getHomeDir() + "/" + getConfigfile());

			if (!cfgFile.exists()) {
				BufferedWriter bos = new BufferedWriter(new FileWriter(cfgFile));

				String defaultConfig = "DBType = false" + "\n" + "DBIp_online = " + "\n" + "DBUser_online" + "\n"
						+ "DBPass_online" + "\n" + "DB_online" + "\n"

						+ "DBIp_offline" + "\n" + "DBUser_offline" + "\n" + "DBPass_offline" + "\n" + "DB_offline";

				bos.write(defaultConfig);
				bos.close();

				Log.info("Default configuration successfully created.");
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			Log.error("Can't create the default configuration!");
			return false;
		}
	}

	/*
	 * pt.setProperty("DBType", "false");
	 * 
	 * pt.setProperty("DBIP_online", getDBIp_online());
	 * pt.setProperty("DBUser_online", getDBUser_online());
	 * pt.setProperty("DBPass_online", getDBPass_online());
	 * pt.setProperty("DB_online", getDB_online());
	 * 
	 * pt.setProperty("DBIP_offline", getDBIp_offline());
	 * pt.setProperty("DBUser_offline", getDBUser_offline());
	 * pt.setProperty("DBPass_offline", getDBPass_offline());
	 * pt.setProperty("DB_offline", getDB_offline());
	 * 
	 */

}
