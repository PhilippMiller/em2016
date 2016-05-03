package whs.gdi2.tippspiel;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import whs.gdi2.tippspiel.database.*;
import whs.gdi2.tippspiel.gui.DBConfigFrame;
import whs.gdi2.tippspiel.gui.MainFrame;
import whs.gdi2.tippspiel.gui.SplashFrame;
import whs.gdi2.tippspiel.log.Log;

/**
 * Haupteinstiegspunkt unserer Applikation.
 * 
 * Diese Klasse ist der Haupteinstiegspunkt unsere Application. Es wird statisch
 * die Function main aufgerufen.
 * 
 * @version 1.0
 * @author Mario Kellner <mario.kellner@studmail.w-hs.de>
 * @author Jan-Markus Momper <jan-markus.momper@studmail.w-hs.de>
 * @author Philipp Miller <philipp.miller@studmail.w-hs.de>
 *
 */
public class Main {
	public static MySQLConnection mainConnection;

	/**
	 * Einstiegspunkt unserer Applikation
	 * 
	 * @param args
	 *            Übergebene Startparameter
	 */
	public static void main(String[] args) {
		Log.info("Application started");
		SplashFrame.main(null);

		Main.Initialize();

		// EVERYTHING HAS TO INITIALIZED!
		try {
			Thread.sleep(500);
			
			MySQLConnection tmp;
			
			if (Config.load()) {
				if (Config.isDBType() && !Config.getDBIp_online().equals("")) {
					// live db
					SplashFrame.setWorkOnIt("Connect to test database");
					tmp = MySQLConnection.getInstance(Config.isDBType());
					tmp.setDatabaseHost(Config.getDBIp_online());
					tmp.setDatabaseUser(Config.getDBUser_online());
					tmp.setDatabasePassword(Config.getDBPass_online());
					tmp.setDatabase(Config.getDB_online());
					
					if (tmp.connectToDatabase()) {
						Main.mainConnection = tmp;
						Thread.sleep(2000);
						SplashFrame.finish();
						MainFrame.main(null);
					} else {
						Thread.sleep(2000);
						SplashFrame.finish();
						DBConfigFrame.main(null);
					}
					
				} else if (!Config.isDBType() && !Config.getDBIp_offline().equals("")) {
					// test db
					SplashFrame.setWorkOnIt("Connect to live database");
					tmp = MySQLConnection.getInstance(Config.isDBType());
					tmp.setDatabaseHost(Config.getDBIp_offline());
					tmp.setDatabaseUser(Config.getDBUser_offline());
					tmp.setDatabasePassword(Config.getDBPass_offline());
					tmp.setDatabase(Config.getDB_offline());
					
					if (tmp.connectToDatabase()) {
						Main.mainConnection = tmp;
						Thread.sleep(2000);
						SplashFrame.finish();
						MainFrame.main(null);
					} else {
						Thread.sleep(2000);
						SplashFrame.finish();
						DBConfigFrame.main(null);
					}
				} else {
					Thread.sleep(2000);
					SplashFrame.finish();
					DBConfigFrame.main(null);
				}
			} else {
				Thread.sleep(2000);
				SplashFrame.finish();
				DBConfigFrame.main(null);
			}

			
			/*
			SplashFrame.setWorkOnIt(DatabaseManagement.createTables(Main.mainConnection));
			SplashFrame.setWorkOnIt(DatabaseManagement.addTestData(Main.mainConnection));
			*/

			
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * System.out.println(SQLConcerning.loadDriver());
		 * System.out.println(SQLConcerning.connectToLiveDB());
		 * System.out.println(SQLConcerning.connectToTestDB());
		 * System.out.println(SQLConcerning.createDB());
		 * System.out.println(SQLConcerning.createTables());
		 * System.out.println(SQLConcerning.addTestData());
		 * System.out.println(SQLConcerning.addSpieleTestData());
		 * System.out.println(SQLConcerning.disconnect());
		 */

		Log.info("Application has reached its end");
	}

	public static void Initialize() {
		File homeDir = new File(Config.getHomeDir());

		if (!homeDir.exists()) {
			homeDir.mkdirs();
		}
		
		Config.createDefault();

	}
}