package whs.gdi2.tippspiel.database.models;

public class Spiele {
	
	private static String heimmannschafthz, gastmannschafthz, heimmannschaftende, gastmannschaftende, heimmannschaftverl,
	gastmannschaftverl, heimmannschaftelf, gastmannschaftelf, gelbekartenheim, gelbekartengast, rotekartenheim,
	rotekartengast;
	

	private static boolean verlaengerung, elfmeter;


	public static String getHeimmannschafthz() {
		return heimmannschafthz;
	}


	public static void setHeimmannschafthz(String heimmannschafthz) {
		Spiele.heimmannschafthz = heimmannschafthz;
	}


	public static String getGastmannschafthz() {
		return gastmannschafthz;
	}


	public static void setGastmannschafthz(String gastmannschafthz) {
		Spiele.gastmannschafthz = gastmannschafthz;
	}


	public static String getHeimmannschaftende() {
		return heimmannschaftende;
	}


	public static void setHeimmannschaftende(String heimmannschaftende) {
		Spiele.heimmannschaftende = heimmannschaftende;
	}


	public static String getGastmannschaftende() {
		return gastmannschaftende;
	}


	public static void setGastmannschaftende(String gastmannschaftende) {
		Spiele.gastmannschaftende = gastmannschaftende;
	}


	public static String getHeimmannschaftverl() {
		return heimmannschaftverl;
	}


	public static void setHeimmannschaftverl(String heimmannschaftverl) {
		Spiele.heimmannschaftverl = heimmannschaftverl;
	}


	public static String getGastmannschaftverl() {
		return gastmannschaftverl;
	}


	public static void setGastmannschaftverl(String gastmannschaftverl) {
		Spiele.gastmannschaftverl = gastmannschaftverl;
	}


	public static String getHeimmannschaftelf() {
		return heimmannschaftelf;
	}


	public static void setHeimmannschaftelf(String heimmannschaftelf) {
		Spiele.heimmannschaftelf = heimmannschaftelf;
	}


	public static String getGastmannschaftelf() {
		return gastmannschaftelf;
	}


	public static void setGastmannschaftelf(String gastmannschaftelf) {
		Spiele.gastmannschaftelf = gastmannschaftelf;
	}


	public static String getGelbekartenheim() {
		return gelbekartenheim;
	}


	public static void setGelbekartenheim(String gelbekartenheim) {
		Spiele.gelbekartenheim = gelbekartenheim;
	}


	public static String getGelbekartengast() {
		return gelbekartengast;
	}


	public static void setGelbekartengast(String gelbekartengast) {
		Spiele.gelbekartengast = gelbekartengast;
	}


	public static String getRotekartenheim() {
		return rotekartenheim;
	}


	public static void setRotekartenheim(String rotekartenheim) {
		Spiele.rotekartenheim = rotekartenheim;
	}


	public static String getRotekartengast() {
		return rotekartengast;
	}


	public static void setRotekartengast(String rotekartengast) {
		Spiele.rotekartengast = rotekartengast;
	}


	public static boolean isVerlaengerung() {
		return verlaengerung;
	}


	public static void setVerlaengerung(boolean verlaengerung) {
		Spiele.verlaengerung = verlaengerung;
	}


	public static boolean isElfmeter() {
		return elfmeter;
	}


	public static void setElfmeter(boolean elfmeter) {
		Spiele.elfmeter = elfmeter;
	}
}
