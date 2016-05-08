package whs.gdi2.tippspiel;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import whs.gdi2.tippspiel.database.*;
import whs.gdi2.tippspiel.gui.MainFrame;
import whs.gdi2.tippspiel.gui.SplashFrame;
import whs.gdi2.tippspiel.log.Log;

/**
 * 
 * Main entrypoint of our application.
 * 
 * This class is our Application class. It initialize the MainFrame and
 * check if a Database is present. It also holds an attribute for the main
 * connection of this program as the global connection.
 * 
 * Note: only use switchDatabaseConnection to ensure that the attrbute has 
 * a correct state.
 * 
 * Note 2: access means 'zugriff' but this class isn´t an accesspoint.
 * It is a entrypoint(Einstiegspunkt). Just for clarification:
 * 
 * @version 1.2
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

		try {
			Thread.sleep(100); // cz nullpointerexception in splashscreen
			
			SplashFrame.setWorkOnIt("Initialize main components...");
			Main.Initialize();
			
			Thread.sleep(1000);

			SplashFrame.setWorkOnIt("Setup database...");
			MainFrame mainFrame;
			if(switchDatabaseConnection(Config.isDBType())) {
				SplashFrame.setWorkOnIt("Die Antwort auf alle Fragen: 42!");
				mainFrame = new MainFrame(true);
				SplashFrame.finish();
			}
			else {

				SplashFrame.setWorkOnIt("Database settings are incorrect. Show DBConfig...");
				mainFrame = new MainFrame(false);
				SplashFrame.finish();
				
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
				tmp.setDatabase(""); // prevent to be null
				
				if(!tmp.connectToDatabase()) {
					Log.error("Cannot switch mainConnection to testdatabase.");
					
					return false;
				}
				
				tmp.setDatabase(Config.getDB_offline());
				
				if(!tmp.connectToDatabase()) {
					int selectedOption = JOptionPane.showConfirmDialog(null, "Datenbank '" + tmp.getDatabase() + "' existiert nicht auf dem Testserver.\n" 
							+ "Möchten Sie diese anlegen und das Verwaltungstool öffnen?\n","Datenbank anlegen?", JOptionPane.YES_NO_OPTION);
					if (selectedOption == JOptionPane.YES_OPTION) {
						DatabaseManagement.createDB(tmp);

						Log.error("Create Database");
					}
					else {
						Log.error("Cannot switch mainConnection to testdatabase.");
						return false;						
					}
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