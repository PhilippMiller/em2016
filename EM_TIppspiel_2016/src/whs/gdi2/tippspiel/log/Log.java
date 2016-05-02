package whs.gdi2.tippspiel.log;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import miller.philipp.Datum;
import miller.philipp.Variables;

public class Log {

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

			fw = new FileWriter(logFile,true);
			bw = new BufferedWriter(fw);
			bw.write("[" + logLevel.getType() + "]\t[" + Datum.tagUndZeitDatum + "]  "+ logZeile + System.lineSeparator());

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
