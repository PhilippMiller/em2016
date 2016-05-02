package whs.gdi2.tippspiel.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.fabric.xmlrpc.base.Data;

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
	
	public static void main(String [] args) {
		System.out.println(date());
	}

	public static void add(String logZeile, LogLevel logLevel) {
		boolean exist = true;
		String logFileName = Variables.logFilePath + Datum.tagDatum + Variables.logFileType;
		File logFile = new File(logFileName);

		FileWriter fw = null;
		BufferedWriter bw = null;

		try {

			if (!logFile.exists()) {
				if (logFile.createNewFile()) {
					Log.add("LogFile '" + logFileName + "' erstellt!", logLevel);
				} else {
					System.err.println("Konnte LogFile '" + logFileName + "' nicht erstellt!");

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.exit(0);

				}
			}

			fw = new FileWriter(logFile, true);
			bw = new BufferedWriter(fw);
			bw.write("[" + logLevel.getType() + "]\t[" + Datum.tagUndZeitDatum + "]  " + logZeile
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

		switch (logLevel.getValue()) {
		case 0:
			System.err.println(
					"Programm angehalten!" + System.lineSeparator() + "Erfasster Typ: '" + logLevel.getType() + "'!");
			break;
		case 4:
			exit(4);
			break;
		case 5:
			exit(5);
			break;
		}
	}
	
	private static String date() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-YYYY");
		return df.format(date);
	}

	private static void exit(int value) {
		// EVTL DATEN SPEICHERN?! MYSQL ODER SO?
		switch (value) {
		case 4:
			System.err.println("Critischer Fehler aufgetreten!" + System.lineSeparator() + "Programm wird beendet...");
			break;
		case 5:
			System.err.println("Gelpanter Stopp ausgeführt!" + System.lineSeparator() + "Programm wird beendet...");
			break;
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.exit(0);
	}

}
