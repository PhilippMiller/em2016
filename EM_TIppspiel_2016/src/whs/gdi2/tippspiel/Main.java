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
		Log.info("Application started.");
		SplashFrame.main(null);

		Main.Initialize();

		// EVERYTHING HAS TO INITIALIZED!
		try {
			Thread.sleep(3000);

			
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

		Log.info("Application has reached its end.");
	}

	public static void Initialize() {
		File homeDir = new File(Config.getHomeDir());

		if (!homeDir.exists()) {
			homeDir.mkdirs();
		}
		
		Config.createDefault();

	}
}