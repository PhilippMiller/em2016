package whs.gdi2.tippspiel;

/**
 * 
 * @author Philipp Miller
 * @version 1.0
 *
 */
public class Config {
	
	private static final String homeDir = System.getProperty("user.home") + "/w_hs_tippspiel";
	private static final String logDir = homeDir + "/log";
	
	
	
	public static String getHomedir() {
		return homeDir;
	}
	public static String getLogdir() {
		return logDir;
	}
	
	
	
	
}
