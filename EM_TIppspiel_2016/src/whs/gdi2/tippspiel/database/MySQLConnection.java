package whs.gdi2.tippspiel.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import whs.gdi2.tippspiel.log.Log;

public class MySQLConnection {
	private static MySQLConnection testInstance;
	private static MySQLConnection liveInstance;
	
	protected Connection connection;
	protected String databaseHost;
	protected String databaseUser;
	protected String databasePassword;
	protected String database;
	
	
	private MySQLConnection() throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw e;
		}
	}
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public String getDatabaseHost() {
		return databaseHost;
	}

	public void setDatabaseHost(String databaseHost) {
		this.databaseHost = databaseHost;
	}
	
	public String getDatabaseUser() {
		return databaseUser;
	}

	public void setDatabaseUser(String databaseUser) {
		this.databaseUser = databaseUser;
	}

	public String getDatabasePassword() {
		return databasePassword;
	}
	
	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public boolean connectToDatabase() {
		String dsn = "jdbc:mysql://" + this.getDatabaseHost() + "/" + this.getDatabase();
		
		try {
			Log.info("Connecting to ");
			connection = DriverManager.getConnection(dsn, this.getDatabaseUser(), this.getDatabasePassword());
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public static MySQLConnection getInstance(boolean getLive) {
		try {
			
			if(getLive) {
				if(MySQLConnection.liveInstance == null) {
					MySQLConnection.liveInstance = new MySQLConnection();
				}
				return MySQLConnection.liveInstance;
			}
			else {
				if(MySQLConnection.testInstance == null) {
					MySQLConnection.testInstance = new MySQLConnection();
				}
				return MySQLConnection.testInstance;
			}
		}
		catch(Exception e) {
			Log.error(e.getMessage());
			Log.critical("Cannot load driver.");
		}
		return null;
	}
	
	public boolean createOrRecreateDatabase() {
		Statement stmt;
		boolean recreate = false;
		
		try {
			stmt = this.getConnection().createStatement();
			stmt.execute("DROP DATABASE " + this.getDatabase());
			recreate = true;
		}
		catch(SQLException err) {
			Log.error(err.getMessage());
		}
		
		try {
			stmt = this.getConnection().createStatement();
			stmt.execute("CREATE DATABASE " + this.getDatabase());
			
			stmt.execute("use EM2016");
			return true;
		} catch (SQLException e) {
			Log.error(e.getMessage());
			return false;
		}
	}
}
