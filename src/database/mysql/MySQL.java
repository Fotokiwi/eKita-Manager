package database.mysql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import application.EKitaManager;

public class MySQL {

	private EKitaManager app = null;
	private Connection conn = null;
	private Statement stmt = null;

	public MySQL(EKitaManager app){
		this.app = app;		
	}
	
	public void testConnection() {
		this.connect("localhost", 3306, "test", "test", "test");
		create("CREATE TABLE IF NOT EXISTS Test ( ID int(11) NOT NULL, A text NOT NULL, B text NOT NULL, C text NOT NULL ) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
	}
	
	public ResultSet getMetaData() {
		
		DatabaseMetaData md;
		try {
			md = conn.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);
			return rs;
		} catch (SQLException e) {
			if(this.app.ERROR_MYSQL)
				this.app.log.LogError("", e);
			this.app.displayErrorLayer().setErrorDetails("2 - 001", "Es ist ein Fehler in der Datenbankkommunikation aufgetreten.");
			return null;
		}
	}

	public int create(String statement) {
		
		try {
			stmt = conn.createStatement();
			int rs = stmt.executeUpdate(statement);
			return rs;
		} catch (Exception e) {
			if(this.app.ERROR_MYSQL)
				this.app.log.LogError("", e);
			this.app.displayErrorLayer().setErrorDetails("2 - 002", "Die Tabelle konnte in der Datenbank nicht angelegt werden.");
			return 0;
		}

	}

	public int update(String statement) {
		
		try {
			stmt = conn.createStatement();
			int rs = stmt.executeUpdate(statement);
			return rs;
		} catch (Exception e) {
			if(this.app.ERROR_MYSQL)
				this.app.log.LogError("", e);
			this.app.displayErrorLayer().setErrorDetails("2 - 003", "Die Daten konnten in der Datenbank nicht aktualisiert werden.");
			return 0;
		}

	}

	public int delete(String statement) {
		
		try {
			stmt = conn.createStatement();
			int rs = stmt.executeUpdate(statement);
			return rs;
		} catch (Exception e) {
			if(this.app.ERROR_MYSQL)
				this.app.log.LogError("", e);
			this.app.displayErrorLayer().setErrorDetails("2 - 004", "Die Daten konnten in der Datenbank nicht gelöscht werden.");
			return 0;
		}

	}

	public int insert(String statement) {
		
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(statement, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()){
			    return rs.getInt(1);
			} else {
				return -1;
			}
		} catch (Exception e) {
			if(this.app.ERROR_MYSQL)
				this.app.log.LogError("", e);
			this.app.displayErrorLayer().setErrorDetails("2 - 005", "Die Daten konnten in der Datenbank nicht eingefügt werden.");
			return 0;
		}

	}

	public ResultSet select(String statement) {
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(statement);
			return rs;
		} catch (Exception e) {
			if(this.app.ERROR_MYSQL)
				this.app.log.LogError("", e);
			this.app.displayErrorLayer().setErrorDetails("2 - 006", "Die Daten konnten in der Datenbank nicht abgefragt werden.");
			return null;
		}

	}

	public boolean connect(String url, int port, String database, String username, String password) {		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			MysqlDataSource ds = new MysqlDataSource();
			ds.setServerName(url);
			ds.setPort(port);
			ds.setDatabaseName(database);
			ds.setUser(username);
			ds.setPassword(password);
			this.conn = ds.getConnection();
			//this.conn = DriverManager.getConnection("jdbc:mysql://" + url + "/" + database + "?user="+ username + "&password=" + password + "");
			//this.conn = DriverManager.getConnection("jdbc:mysql://10.0.100.111/test?user=test&password=test");
			//this.conn = DriverManager.getConnection("jdbc:mysql://" + url + "/" + database, username, password);
			if(this.app.DEBUG_MYSQL)
				this.app.log.LogDebug("Successfully connected to Database.");
			return true;
		} catch (Exception e) {
			if(this.app.ERROR_MYSQL)
				this.app.log.LogError("Can't connect to MySQL Database", e);
			this.app.displayErrorLayer().setErrorDetails("2 - 007", "Es war keine Verbindung mit der Datenbank möglich. Bitte überprüfen Sie die Zugangsdaten");
			return false;
		}

	}

	public void disconnect() {

		try {
			if(stmt != null)
				this.stmt.close();
			this.conn.close();
			if(this.app.DEBUG_MYSQL)
				this.app.log.LogDebug("Successfully disconnected from Database.");
		} catch (Exception e) {
			if(this.app.ERROR_MYSQL)
				this.app.log.LogError("", e);
			this.app.displayErrorLayer().setErrorDetails("2 - 008", "Die Verbindung mit der Datenbank konnte nicht getrennt werden. Dieser Fehler ist bei geringem Vorkommen nicht kritisch.");
		}

	}

}
