package whs.gdi2.tippspiel.log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import whs.gdi2.tippspiel.*;


/**
 * 
 * This class contains the logging functionality. By executing the add function
 * ('Log.add') it try to write a new line into the newest log file.
 * (e.g. /log_02-05-2016.log)
 * 
 * @author Philipp Miller
 * @version 1.0
 *
 */

public class Log {
	protected static String logFolder = Config.getLogDir();
	
	public static String getLogFolder() {
		return logFolder;
	}

	public static void info(String message) {
		Log.log(LogLevel.INFO, message);
	}

	public static void debug(String message) {
		Log.log(LogLevel.DEBUG, message);
	}
	
	public static void error(String message) {
		Log.log(LogLevel.ERROR, message);
		
	}
	public static void critical(String message) {
		Log.log(LogLevel.CRITICAL, message);
		Log.exit(LogLevel.CRITICAL);
	}

	public static void stop(String message) {
		Log.log(LogLevel.STOP, message);
		Log.exit(LogLevel.STOP);
	}
	
	public static void mysql(String message) {
		Log.log(LogLevel.MYSQL, message);
	}
	
	public static void mysqlError(String message) {
		Log.log(LogLevel.MYSQL_ERROR, message);
	}
	
	public static void log(LogLevel level, String message) {
		String logFileName = "log_" + Log.date() + ".log";
		
		File logFolder = new File(Config.getLogDir());
		
		if (!logFolder.exists()) {
		    try{
		    	logFolder.mkdirs();
		    } 
		    catch(SecurityException se){
		    	System.out.println(se.getMessage());
		    }
		}
		
		FileWriter fw = null;
		BufferedWriter bw = null;
		File logFile = new File(Log.getLogFolder() + "/" + logFileName);
		
		
		try {

			if (!logFile.exists()) {
				if (logFile.createNewFile()) {
					Log.info("LogFile '" + logFileName + "' created!");
				}
			}

			bw = new BufferedWriter(new FileWriter(logFile, true));
			bw.write("[" + level.name() + "][" + (new Date()).toString() + "]  " + message
					+ System.lineSeparator());
			

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static String date() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-YYYY");
		return df.format(date);
	}
	
	private static void exit(LogLevel level) {
		switch (level) {
		case CRITICAL:
			Log.log(LogLevel.CRITICAL,"Critical error occurs!" + System.lineSeparator() + "Application will be terminated...");
			break;
		case STOP:
			Log.log(LogLevel.STOP, "Received stop signal!" + System.lineSeparator() + "Application will be terminated...");
			break;
		}
		System.exit(0);
}
}
