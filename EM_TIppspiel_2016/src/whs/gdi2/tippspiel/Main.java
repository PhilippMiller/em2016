package whs.gdi2.tippspiel;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;

import whs.gdi2.tippspiel.database.*;
import whs.gdi2.tippspiel.gui.MainFrame;
import whs.gdi2.tippspiel.gui.SplashFrame;
import whs.gdi2.tippspiel.log.Log;

/**
 * 
 * Main access to our application.
 * 
 * @version 1.0
 * @author Mario Kellner <mario.kellner@studmail.w-hs.de>
 * @author Jan-Markus Momper <jan-markus.momper@studmail.w-hs.de>
 * @author Philipp Miller <philipp.miller@studmail.w-hs.de>
 */

public class Main {
	public static MySQLConnection mainConnection;

	/**
	 * 
	 * @param args
	 *            
	 */
	public static void main(String[] args) {
		Log.info("Application started.");
		SplashFrame.main(null);

		Main.Initialize();

		// EVERYTHING HAS TO INITIALIZE!
		try {
			
			Thread.sleep(3000);
			
			@SuppressWarnings("unused")
			MainFrame mainFrame;
			if(switchDatabaseConnection(Config.isDBType())) {
				SplashFrame.finish();
				mainFrame = new MainFrame(true);
			}
			else {
				SplashFrame.finish();
				mainFrame = new MainFrame(false);
				Log.info("Database settings are incorrect. Check DBConfigFrame.");
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	

		Log.info("Application has reached its end.");
		
	}

	public static void Initialize() {
		File homeDir = new File(Config.getHomeDir());

		if (!homeDir.exists()) {
			homeDir.mkdirs();
		}
		
		Config.createDefault();
		Config.load();

	}
	
	public static boolean switchDatabaseConnection(boolean DBType) {
		MySQLConnection tmp;
		boolean hasValidConnection = false;
		
		if(DBType) {
			tmp = MySQLConnection.getInstance(DBType);
			try {
				
				if(tmp.getConnection() != null && tmp.getConnection().isValid(0)) {
					hasValidConnection = true;
				}
			} catch (SQLException e) {
				Log.debug("Ignorable error:" + e.getMessage() + ".");
			}
			
			if(!hasValidConnection) {
				tmp.setDatabaseHost(Config.getDBIp_online());
				tmp.setDatabaseUser(Config.getDBUser_online());
				tmp.setDatabasePassword(Config.getDBPass_online());
				tmp.setDatabase(Config.getDB_online());
				
				if(!tmp.connectToDatabase()) {
					Log.error("Cannot switch mainConnection to livedatabase.");
					return false;
				}
				
			}
			else {
				Log.info("Livedatabase already connected. Set it as mainConnection.");
			}
			
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("DBType", "true");
			Config.write(map);
			
			Main.mainConnection = tmp;
			return true;
		}
		else {
			tmp = MySQLConnection.getInstance(DBType);
			try {
				
				if(tmp.getConnection() != null && tmp.getConnection().isValid(0)) {
					hasValidConnection = true;
				}
			} catch (SQLException e) {
				Log.debug("Ignorable error:" + e.getMessage() + ".");
			}
			
			if(!hasValidConnection) {
				tmp.setDatabaseHost(Config.getDBIp_offline());
				tmp.setDatabaseUser(Config.getDBUser_offline());
				tmp.setDatabasePassword(Config.getDBPass_offline());
				tmp.setDatabase(Config.getDB_offline());
				
				if(!tmp.connectToDatabase()) {
					Log.error("Cannot switch mainConnection to testdatabase.");
					return false;
				}
				
			}
			else {
				Log.info("Testdatabase already connected. Set it as mainConnection.");
			}
			
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("DBType", "false");
			Config.write(map);
			
			Main.mainConnection = tmp;
			return true;
		}
	}
}