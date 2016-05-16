package whs.gdi2.tippspiel.database.models;

public class SpieleStatic {
	
	private static String heimmannschafthz, gastmannschafthz, heimmannschaftende, gastmannschaftende, heimmannschaftverl,
	gastmannschaftverl, heimmannschaftelf, gastmannschaftelf, gelbekartenheim, gelbekartengast, rotekartenheim,
	rotekartengast;
	

	private static boolean verlaengerung, elfmeter;


	public static String getHeimmannschafthz() {
		return heimmannschafthz;
	}


	public static void setHeimmannschafthz(String heimmannschafthz) {
		SpieleStatic.heimmannschafthz = heimmannschafthz;
	}


	public static String getGastmannschafthz() {
		return gastmannschafthz;
	}


	public static void setGastmannschafthz(String gastmannschafthz) {
		SpieleStatic.gastmannschafthz = gastmannschafthz;
	}


	public static String getHeimmannschaftende() {
		return heimmannschaftende;
	}


	public static void setHeimmannschaftende(String heimmannschaftende) {
		SpieleStatic.heimmannschaftende = heimmannschaftende;
	}


	public static String getGastmannschaftende() {
		return gastmannschaftende;
	}


	public static void setGastmannschaftende(String gastmannschaftende) {
		SpieleStatic.gastmannschaftende = gastmannschaftende;
	}


	public static String getHeimmannschaftverl() {
		return heimmannschaftverl;
	}


	public static void setHeimmannschaftverl(String heimmannschaftverl) {
		SpieleStatic.heimmannschaftverl = heimmannschaftverl;
	}


	public static String getGastmannschaftverl() {
		return gastmannschaftverl;
	}


	public static void setGastmannschaftverl(String gastmannschaftverl) {
		SpieleStatic.gastmannschaftverl = gastmannschaftverl;
	}


	public static String getHeimmannschaftelf() {
		return heimmannschaftelf;
	}


	public static void setHeimmannschaftelf(String heimmannschaftelf) {
		SpieleStatic.heimmannschaftelf = heimmannschaftelf;
	}


	public static String getGastmannschaftelf() {
		return gastmannschaftelf;
	}


	public static void setGastmannschaftelf(String gastmannschaftelf) {
		SpieleStatic.gastmannschaftelf = gastmannschaftelf;
	}


	public static String getGelbekartenheim() {
		return gelbekartenheim;
	}


	public static void setGelbekartenheim(String gelbekartenheim) {
		SpieleStatic.gelbekartenheim = gelbekartenheim;
	}


	public static String getGelbekartengast() {
		return gelbekartengast;
	}


	public static void setGelbekartengast(String gelbekartengast) {
		SpieleStatic.gelbekartengast = gelbekartengast;
	}


	public static String getRotekartenheim() {
		return rotekartenheim;
	}


	public static void setRotekartenheim(String rotekartenheim) {
		SpieleStatic.rotekartenheim = rotekartenheim;
	}


	public static String getRotekartengast() {
		return rotekartengast;
	}


	public static void setRotekartengast(String rotekartengast) {
		SpieleStatic.rotekartengast = rotekartengast;
	}


	public static boolean isVerlaengerung() {
		return verlaengerung;
	}


	public static void setVerlaengerung(boolean verlaengerung) {
		SpieleStatic.verlaengerung = verlaengerung;
	}


	public static boolean isElfmeter() {
		return elfmeter;
	}


	public static void setElfmeter(boolean elfmeter) {
		SpieleStatic.elfmeter = elfmeter;
	}
}
