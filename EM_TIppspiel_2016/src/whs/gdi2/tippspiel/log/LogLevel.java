package whs.gdi2.tippspiel.log;


public class LogLevel {
	
	private String type;
	private int value;
	
	private LogLevel(String type, int value) {
		this.type = type;
		this.value = value;
	}
	
	// LogLevel's
	public static final LogLevel info = new LogLevel("INFO", 1);
	public static final LogLevel mysql = new LogLevel("MYSQL", 2);
	public static final LogLevel err = new LogLevel("ERROR", 3);
	public static final LogLevel crit = new LogLevel("CRITICAL", 4);
	public static final LogLevel stopp = new LogLevel("STOPP", 5);
	
	// Getters
	public String getType() {
		return this.type;
	}
	
	public int getValue() {
		return this.value;
	}

}
