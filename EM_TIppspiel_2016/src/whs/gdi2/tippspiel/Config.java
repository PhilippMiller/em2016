package whs.gdi2.tippspiel;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * 
 * @author Philipp Miller
 * @version 1.0
 *
 */
public class Config {
	
	private static final String homeDir = System.getProperty("user.home") + "/w_hs_tippspiel";
	private static final String logDir = homeDir + "/log";
	private static final String configFile = "application.config";

	public static String getConfigfile() {
		return configFile;
	}
	public static String getHomeDir() {
		return homeDir;
	}
	public static String getLogDir() {
		return logDir;
	}
	
	public static void load() {
		Properties pt = new Properties();
		//pt.load(FileInputStream(Config.getConfigfile()));
		
	}
	
	
}
