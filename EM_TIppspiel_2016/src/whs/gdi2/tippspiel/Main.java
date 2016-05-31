package whs.gdi2.tippspiel;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;

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
 * Note: only use switchDatabaseConnection to ensure that the attribute has 
 * a correct state.
 * 
 * Note 2: access means 'zugriff' but this class isn´t an accesspoint.
 * It is a entrypoint(Einstiegspunkt). Just for clarification.
 * 
 * @version 1.2
 * @author Mario Kellner <mario.kellner@studmail.w-hs.de>
 * @author Jan-Markus Momper <jan-markus.momper@studmail.w-hs.de>
 * @author Philipp Miller <philipp.miller@studmail.w-hs.de>
 */

public class Main {
	/**
	 * This object handles the main Connection to the Datebase.
	 * It can be switched by using the function switchDatabaseConnection
	 */
	public static MySQLConnection mainConnection;

	/**
	 * Initialize our splasher and bringing things up to life.
	 * 
	 * @param args Commandline parameters.
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Log.info("Application started.");
		SplashFrame.main(null);

		
		try {
			// Try until the initial text can be set
			// Dirty but it works.
			while(true) {
				try {
					SplashFrame.setWorkOnIt("Initialize main components...");
					break;
				}
				catch(Exception e) {
					continue;
				}
			}
			
			Main.Initialize();
			SplashFrame.setWorkOnIt("Setup database...");
			
			MainFrame mainFrame;
			if(switchDatabaseConnection(Config.isDBType())) {
				SplashFrame.setWorkOnIt("Antwort auf alle Fragen: 42");
				mainFrame = new MainFrame(true);
				SplashFrame.finish();
			}
			else {
				SplashFrame.setWorkOnIt("Database settings are incorrect. Show DBConfig...");
				SplashFrame.finish();
				mainFrame = new MainFrame(false);
				
				Log.info("Database settings are incorrect. Check DBConfigFrame.");
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * This function create the basic folder structure
	 * and load the configfile. 
	 */
	public static void Initialize() {
		Log.setPrintStream(System.out);
		
		File homeDir = new File(Config.getHomeDir());

		if (!homeDir.exists()) {
			homeDir.mkdirs();
		}
		
		Config.createDefault();
		Config.load();
	}
	/**
	 * This function switch our mainconnection between the testdatabase
	 * and the livedatabase. 
	 * 
	 * @since 1.0
	 * @author Mario Kellner <mario.kellner@studmail.w-hs.de>
	 * @param DBType
	 * @return  
	 */
	public static boolean switchDatabaseConnection(boolean DBType) {
		HashMap<String, String> ConfigMapper = new HashMap<String, String>();
		MySQLConnection connectionObject;
		boolean hasValidConnection = false;
		
		connectionObject = MySQLConnection.getInstance(DBType);

		try {
			if(connectionObject.getConnection() != null && connectionObject.getConnection().isValid(0)) {
				hasValidConnection = true;
			}
		} catch (SQLException e) {
			Log.debug("Ignorable error: " + e.getMessage() + ".");
		}
		
		
		if(DBType) {
			if(!hasValidConnection) {
				connectionObject.setDatabaseHost(Config.getDBIp_online());
				connectionObject.setDatabaseUser(Config.getDBUser_online());
				connectionObject.setDatabasePassword(Config.getDBPass_online());
				connectionObject.setDatabase(Config.getDB_online());
				
				if(!connectionObject.connectToDatabase()) {
					Log.error("Cannot switch mainConnection to livedatabase.");
					return false;
				}
			}
			else {
				Log.info("Livedatabase already connected. Set it as mainConnection.");
			}

			ConfigMapper.put("DBType", "true");
		}
		else {		
			if(!hasValidConnection) {
				connectionObject.setDatabaseHost(Config.getDBIp_offline());
				connectionObject.setDatabaseUser(Config.getDBUser_offline());
				connectionObject.setDatabasePassword(Config.getDBPass_offline());
				connectionObject.setDatabase(""); // prevent to be null
				
				if(!connectionObject.connectToDatabase()) {
					Log.error("Cannot switch mainConnection to testdatabase.");
					
					return false;
				}
				
				connectionObject.setDatabase(Config.getDB_offline());
				
				if(!connectionObject.connectToDatabase()) {
					int selectedOption = JOptionPane.showConfirmDialog(null, "Datenbank '" + connectionObject.getDatabase() + "' existiert nicht auf dem Testserver.\n" 
							+ "Möchten Sie diese anlegen und das Verwaltungstool öffnen?\n","Datenbank anlegen?", JOptionPane.YES_NO_OPTION);
					if (selectedOption == JOptionPane.YES_OPTION) {
						DatabaseManagement.createDB(connectionObject);

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

			ConfigMapper.put("DBType", "false");
		}
		
		Config.write(ConfigMapper);
		
		Main.mainConnection = connectionObject;
		return true;
	}
}