package whs.gdi2.tippspiel;

import java.io.FileInputStream;
import java.util.Properties;

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
	private static final String configFile = "application.config";

	private static final String version = "1.0";
	private static final String[] autor = { "Jan-Markus Momper", "Mario Kellner", "Philipp Miller" };

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

	public static void load() {
		Properties pt = new Properties();
		// pt.load(FileInputStream(Config.getConfigfile()));

	}

}
