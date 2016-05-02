package whs.gdi2.tippspiel;

import whs.gdi2.tippspiel.log.Log;
import whs.gdi2.tippspiel.methods.*;
/**
 * Haupteinstiegspunkt unserer Applikation.
 * 
 * Diese Klasse ist der Haupteinstiegspunkt unsere Application.
 * Es wird statisch die Function main aufgerufen.
 * 
 * @version 1.0
 * @author Mario Kellner <mario.kellner@studmail.w-hs.de>
 * @author Jan-Markus Momper <jan-markus.momper@studmail.w-hs.de>
 * @author Philipp Miller <philipp.miller@studmail.w-hs.de>
 *
 */
public class Main {
 
	/**
	 * Einstiegspunkt unserer Applikation
	 * 
	 * @param args Übergebene Startparameter
	 */
	public static void main(String[] args) {
		Log.info("Application started");
		
		System.out.println(SQLConcerning.loadDriver());
		System.out.println(SQLConcerning.connectToLiveDB());
		System.out.println(SQLConcerning.connectToTestDB());
		System.out.println(SQLConcerning.createDB());
		System.out.println(SQLConcerning.createTables());
		System.out.println(SQLConcerning.addTestData());
		System.out.println(SQLConcerning.disconnect());
		Log.info("Application has reached its end");
	}
}