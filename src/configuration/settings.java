package configuration;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.sqlite.SQLite;
import application.EKitaManager;

public class settings {

	private EKitaManager app = null;

	public settings(EKitaManager app) {
		this.app = app;

		initiateSettings();
	}

	private void initiateSettings() {

		createSettingsDatabase();
		createServerDatabase();
		createDebugDatabase();
		createLoginAutoFillDatabase();
		createDefaultsDatabase();

		updateDatabase();

	}

	private void createSettingsDatabase() {

		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);

		ResultSet rs = dbconn.getMetaData();		
		Boolean contains = false;

		try {
			while (rs.next()) {
				if(rs.getString(3).contains("Settings")) {
					contains = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if(contains == false) {

			String sql = "CREATE TABLE IF NOT EXISTS Settings " +
					"(ID 						INTEGER 	PRIMARY KEY	AUTOINCREMENT, " +
					" Credit_Author           	TEXT    NOT NULL, " +
					" Credit_Organization       TEXT    NOT NULL, " +
					" Credit_Version           	TEXT    NOT NULL, " +
					" Localization_Language     TEXT    NOT NULL, " +
					" Localization_Country      TEXT    NOT NULL, " +
					" Localization_Description  TEXT    NOT NULL, " +
					" Servertype  				TEXT    NOT NULL);";

			dbconn.create(sql);

			sql = "INSERT INTO Settings " +
					"(Credit_Author, " +
					" Credit_Organization, " +
					" Credit_Version, " +
					" Localization_Language, " +
					" Localization_Country, " +
					" Localization_Description, " +
					" Servertype) " + 
					" VALUES " + 
					"('" + this.app.CREDIT_AUTHOR + "', " +
					"'" + this.app.CREDIT_ORGANIZATION + "', " +
					"'" + this.app.CREDIT_VERSION + "', " +
					"'de', " +
					"'DE', " +
					"'Deutsch', " +
					"'Locale');";

			dbconn.insert(sql);
		}

		dbconn.disconnect();
		dbconn = null;

	}

	private void createServerDatabase() {

		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);

		ResultSet rs = dbconn.getMetaData();		
		Boolean contains = false;

		try {
			while (rs.next()) {
				if(rs.getString(3).contains("Server")) {
					contains = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if(contains == false) {
			String sql = "CREATE TABLE IF NOT EXISTS Server " +
					"(ID 						INTEGER 	PRIMARY KEY	AUTOINCREMENT, " +
					" DatabaseType			    TEXT    			NOT NULL, " +
					" DatabaseName			    TEXT    			NOT NULL, " +
					" DatabaseURL			    TEXT    			NOT NULL, " +
					" DatabaseUser			    TEXT    			NOT NULL, " +
					" DatabasePassword         	TEXT    			NOT NULL);";

			dbconn.create(sql);

			sql = "INSERT INTO Server " +
					"(DatabaseType, " +
					" DatabaseName, " +
					" DatabaseURL, " +
					" DatabaseUser, " +
					" DatabasePassword) " +
					" VALUES " + 
					"('Locale', " +
					"'eKita-Management-Database', " +
					"'', " +
					"'phi-omega', " +
					"'OBTCLxAbGeaQbtmDkSZk_s3O');";

			dbconn.insert(sql);

			sql = "INSERT INTO Server " +
					"(DatabaseType, " +
					" DatabaseName, " +
					" DatabaseURL, " +
					" DatabaseUser, " +
					" DatabasePassword) " +
					" VALUES " + 
					"('Remote', " +
					"'', " +
					"'', " +
					"'', " +
					"'');";

			dbconn.insert(sql);
		}

		dbconn.disconnect();
		dbconn = null;

	}

	private void createDebugDatabase() {

		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);

		String sql = "CREATE TABLE IF NOT EXISTS Debug " +
				"(ID 						INTEGER 	PRIMARY KEY	AUTOINCREMENT, " +
				" Username				    TEXT    			NOT NULL, " +
				" Password				    TEXT    			NOT NULL);";

		dbconn.create(sql);

		dbconn.disconnect();
		dbconn = null;

	}

	private void createLoginAutoFillDatabase() {

		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);

		ResultSet rs = dbconn.getMetaData();		
		Boolean contains = false;

		try {
			while (rs.next()) {
				if(rs.getString(3).contains("LoginAutoFill")) {
					contains = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if(contains == false) {
			String sql = "CREATE TABLE IF NOT EXISTS LoginAutoFill " +
					"(ID 						INTEGER 	PRIMARY KEY	AUTOINCREMENT, " +
					" Username				    TEXT    			NOT NULL, " +
					" Password				    TEXT    			NOT NULL);";

			dbconn.create(sql);

			sql = "INSERT INTO LoginAutoFill " +
					"(Username, " +
					" Password) " +
					" VALUES " + 
					"('', " +
					"'');";

			dbconn.insert(sql);
		}

		dbconn.disconnect();
		dbconn = null;
		
	}

	private void createDefaultsDatabase() {

		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);

		ResultSet rs = dbconn.getMetaData();		
		Boolean contains = false;

		try {
			while (rs.next()) {
				if(rs.getString(3).contains("Defaults")) {
					contains = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if(contains == false) {
			String sql = "CREATE TABLE IF NOT EXISTS Defaults " +
					"(ID 						INTEGER 	PRIMARY KEY	AUTOINCREMENT, " +
					" DefaultName			    TEXT    			NOT NULL, " +
					" DefaultValue			    TEXT    			NOT NULL);";

			dbconn.create(sql);

			sql = "INSERT INTO Defaults " +
					"(DefaultName, " +
					" DefaultValue) " +
					" VALUES " + 
					"('Kind_Nachname', " +
					"'true');";

			dbconn.insert(sql);

			sql = "INSERT INTO Defaults " +
					"(DefaultName, " +
					" DefaultValue) " +
					" VALUES " + 
					"('Kind_Vorname', " +
					"'true');";

			dbconn.insert(sql);

			sql = "INSERT INTO Defaults " +
					"(DefaultName, " +
					" DefaultValue) " +
					" VALUES " + 
					"('Kind_Geburtsdatum', " +
					"'true');";

			dbconn.insert(sql);

			sql = "INSERT INTO Defaults " +
					"(DefaultName, " +
					" DefaultValue) " +
					" VALUES " + 
					"('Kind_Adresse', " +
					"'true');";

			dbconn.insert(sql);

			sql = "INSERT INTO Defaults " +
					"(DefaultName, " +
					" DefaultValue) " +
					" VALUES " + 
					"('Kind_Vertragsbeginn', " +
					"'true');";

			dbconn.insert(sql);

			sql = "INSERT INTO Defaults " +
					"(DefaultName, " +
					" DefaultValue) " +
					" VALUES " + 
					"('Kind_Vertragsende', " +
					"'true');";

			dbconn.insert(sql);

			sql = "INSERT INTO Defaults " +
					"(DefaultName, " +
					" DefaultValue) " +
					" VALUES " + 
					"('Kind_Nationalitaet', " +
					"'false');";

			dbconn.insert(sql);

			sql = "INSERT INTO Defaults " +
					"(DefaultName, " +
					" DefaultValue) " +
					" VALUES " + 
					"('Kind_Muttersprache', " +
					"'false');";

			dbconn.insert(sql);

			sql = "INSERT INTO Defaults " +
					"(DefaultName, " +
					" DefaultValue) " +
					" VALUES " + 
					"('Kind_Krankenkasse', " +
					"'true');";

			dbconn.insert(sql);

			sql = "INSERT INTO Defaults " +
					"(DefaultName, " +
					" DefaultValue) " +
					" VALUES " + 
					"('Kind_Krankheiten', " +
					"'false');";

			dbconn.insert(sql);

			sql = "INSERT INTO Defaults " +
					"(DefaultName, " +
					" DefaultValue) " +
					" VALUES " + 
					"('Kind_Allergien', " +
					"'false');";

			dbconn.insert(sql);

			sql = "INSERT INTO Defaults " +
					"(DefaultName, " +
					" DefaultValue) " +
					" VALUES " + 
					"('Kind_Besonderheiten', " +
					"'false');";

			dbconn.insert(sql);

			sql = "INSERT INTO Defaults " +
					"(DefaultName, " +
					" DefaultValue) " +
					" VALUES " + 
					"('Person_Nachname', " +
					"'true');";

			dbconn.insert(sql);

			sql = "INSERT INTO Defaults " +
					"(DefaultName, " +
					" DefaultValue) " +
					" VALUES " + 
					"('Person_Vorname', " +
					"'true');";

			dbconn.insert(sql);

			sql = "INSERT INTO Defaults " +
					"(DefaultName, " +
					" DefaultValue) " +
					" VALUES " + 
					"('Person_Geburtsdatum', " +
					"'false');";

			dbconn.insert(sql);

			sql = "INSERT INTO Defaults " +
					"(DefaultName, " +
					" DefaultValue) " +
					" VALUES " + 
					"('Person_Adresse', " +
					"'false');";

			dbconn.insert(sql);

			sql = "INSERT INTO Defaults " +
					"(DefaultName, " +
					" DefaultValue) " +
					" VALUES " + 
					"('Person_Privat', " +
					"'false');";

			dbconn.insert(sql);

			sql = "INSERT INTO Defaults " +
					"(DefaultName, " +
					" DefaultValue) " +
					" VALUES " + 
					"('Person_Mobil', " +
					"'false');";

			dbconn.insert(sql);

			sql = "INSERT INTO Defaults " +
					"(DefaultName, " +
					" DefaultValue) " +
					" VALUES " + 
					"('Person_Arbeit', " +
					"'false');";

			dbconn.insert(sql);

			sql = "INSERT INTO Defaults " +
					"(DefaultName, " +
					" DefaultValue) " +
					" VALUES " + 
					"('Person_Mail', " +
					"'false');";

			dbconn.insert(sql);
		}

		dbconn.disconnect();
		dbconn = null;

	}
	
	public String getDefaults() {
		
		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);

		ResultSet rs = dbconn.select("SELECT * FROM Defaults;");
		if(rs == null) {			
			dbconn.disconnect();
			dbconn = null;
			this.app.log.LogError("Kann keine Default-Werte aus der Datenbank laden.");
			return "";
		} else {
			String values = "";
			try {
				while ( rs.next() ) {
					values += rs.getString("DefaultValue") + ",";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dbconn.disconnect();
			dbconn = null;
			//this.app.log.LogInfo(values);
			return values;
		}
		
	}
	
	public void updateDefaults(boolean kind_nachname, boolean kind_vorname, boolean kind_geburtsdatum, boolean kind_adresse, boolean kind_vertragsbeginn, boolean kind_vertragsende, boolean kind_nationalitaet, boolean kind_muttersprache, boolean kind_krankenkasse, boolean kind_krankheiten, boolean kind_allergien, boolean kind_besonderheiten, boolean person_nachname, boolean person_vorname, boolean person_geburtsdatum, boolean person_adresse, boolean person_privat, boolean person_mobil, boolean person_arbeit, boolean person_mail) {
		
		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);

		String sql = "UPDATE Defaults SET " +
				"DefaultValue='" + kind_nachname + "' WHERE DefaultName='Kind_Nachname';";

		dbconn.update(sql);

		sql = "UPDATE Defaults SET " +
				"DefaultValue='" + kind_vorname + "' WHERE DefaultName='Kind_Vorname';";

		dbconn.update(sql);

		sql = "UPDATE Defaults SET " +
				"DefaultValue='" + kind_geburtsdatum + "' WHERE DefaultName='Kind_Geburtsdatum';";

		dbconn.update(sql);

		sql = "UPDATE Defaults SET " +
				"DefaultValue='" + kind_adresse + "' WHERE DefaultName='Kind_Adresse';";

		dbconn.update(sql);

		sql = "UPDATE Defaults SET " +
				"DefaultValue='" + kind_vertragsbeginn + "' WHERE DefaultName='Kind_Vertragsbeginn';";

		dbconn.update(sql);

		sql = "UPDATE Defaults SET " +
				"DefaultValue='" + kind_vertragsende + "' WHERE DefaultName='Kind_Vertragsende';";

		dbconn.update(sql);

		sql = "UPDATE Defaults SET " +
				"DefaultValue='" + kind_nationalitaet + "' WHERE DefaultName='Kind_Nationalitaet';";

		dbconn.update(sql);

		sql = "UPDATE Defaults SET " +
				"DefaultValue='" + kind_muttersprache + "' WHERE DefaultName='Kind_Muttersprache';";

		dbconn.update(sql);

		sql = "UPDATE Defaults SET " +
				"DefaultValue='" + kind_krankenkasse + "' WHERE DefaultName='Kind_Krankenkasse';";

		dbconn.update(sql);

		sql = "UPDATE Defaults SET " +
				"DefaultValue='" + kind_krankheiten + "' WHERE DefaultName='Kind_Krankheiten';";

		dbconn.update(sql);

		sql = "UPDATE Defaults SET " +
				"DefaultValue='" + kind_allergien + "' WHERE DefaultName='Kind_Allergien';";

		dbconn.update(sql);

		sql = "UPDATE Defaults SET " +
				"DefaultValue='" + kind_besonderheiten + "' WHERE DefaultName='Kind_Besonderheiten';";

		dbconn.update(sql);

		sql = "UPDATE Defaults SET " +
				"DefaultValue='" + person_nachname + "' WHERE DefaultName='Person_Nachname';";

		dbconn.update(sql);

		sql = "UPDATE Defaults SET " +
				"DefaultValue='" + person_vorname + "' WHERE DefaultName='Person_Vorname';";

		dbconn.update(sql);

		sql = "UPDATE Defaults SET " +
				"DefaultValue='" + person_geburtsdatum + "' WHERE DefaultName='Person_Geburtsdatum';";

		dbconn.update(sql);

		sql = "UPDATE Defaults SET " +
				"DefaultValue='" + person_adresse + "' WHERE DefaultName='Person_Adresse';";

		dbconn.update(sql);

		sql = "UPDATE Defaults SET " +
				"DefaultValue='" + person_privat + "' WHERE DefaultName='Person_Privat';";

		dbconn.update(sql);

		sql = "UPDATE Defaults SET " +
				"DefaultValue='" + person_mobil + "' WHERE DefaultName='Person_Mobil';";

		dbconn.update(sql);

		sql = "UPDATE Defaults SET " +
				"DefaultValue='" + person_arbeit + "' WHERE DefaultName='Person_Arbeit';";

		dbconn.update(sql);

		sql = "UPDATE Defaults SET " +
				"DefaultValue='" + person_mail + "' WHERE DefaultName='Person_Mail';";

		dbconn.update(sql);

		dbconn.disconnect();
		dbconn = null;
		
	}

	private void updateDatabase() {

		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);

		String sql = "UPDATE Settings SET " +
				"Credit_Author='" + this.app.CREDIT_AUTHOR + "', " +
				"Credit_Organization='" + this.app.CREDIT_ORGANIZATION + "', " +
				"Credit_Version='" + this.app.CREDIT_VERSION + "' WHERE ID=1;";

		dbconn.update(sql);

		dbconn.disconnect();
		dbconn = null;

	}

	public String getLanguageDescription() {

		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);

		ResultSet rs = dbconn.select("SELECT * FROM Settings WHERE ID=1;");
		if(rs == null) {			
			dbconn.disconnect();
			dbconn = null;
			return "Deutsch";
		} else {
			String description = "";
			try {
				while ( rs.next() ) {
					description = rs.getString("Localization_Description");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.app.log.LogDebug("Description: " + description);
			dbconn.disconnect();
			dbconn = null;
			return description;
		}
	}

	public String getLanguageLanguage() {

		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);

		ResultSet rs = dbconn.select("SELECT * FROM Settings WHERE ID=1;");
		if(rs == null) {
			dbconn.disconnect();
			dbconn = null;
			return "de";
		} else {
			String language = "";
			try {
				while ( rs.next() ) {
					language = rs.getString("Localization_Language");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.app.log.LogDebug("Language: " + language);
			dbconn.disconnect();
			dbconn = null;
			return language;
		}
	}

	public String getLanguageCountry() {

		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);

		ResultSet rs = dbconn.select("SELECT * FROM Settings WHERE ID=1;");
		if(rs == null) {
			dbconn.disconnect();
			dbconn = null;
			return "DE";
		} else {
			String country = "";
			try {
				while ( rs.next() ) {
					country = rs.getString("Localization_Country");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.app.log.LogDebug("Country: " + country);
			dbconn.disconnect();
			dbconn = null;
			return country;
		}
	}

	public void setLanguage(String language, String country, String description) {

		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);

		String sql = "UPDATE Settings SET " +
				"LOCALIZATION_LANGUAGE='" + language + "', " +
				"LOCALIZATION_COUNTRY='" + country + "', " +
				"LOCALIZATION_DESCRIPTION='" + description + "' WHERE ID=1;";

		dbconn.update(sql);

		dbconn.disconnect();
		dbconn = null;

	}

	public void setLocaleServerSettings(String ip) {

		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);
		
		//System.out.println(dbconn.toString());

		String sql = "UPDATE Server SET DatabaseURL='" + ip + "' WHERE DatabaseType='Locale';";

		dbconn.update(sql);

		sql = "UPDATE Settings SET Servertype='Locale' WHERE ID=1;";

		dbconn.update(sql);

		dbconn.disconnect();
		dbconn = null;

	}

	public void setRemoteServerSettings(String name, String ip, String user, String pwd) {

		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);
		
		//System.out.println(dbconn.toString());

		String sql = "UPDATE Server SET DatabaseName='" + name + "', DatabaseURL='" + ip + "', DatabaseUser='" + user + "', DatabasePassword='" + pwd + "' WHERE DatabaseType='Remote';";
	
		dbconn.update(sql);

		sql = "UPDATE Settings SET Servertype='Remote' WHERE ID=1;";

		dbconn.update(sql);

		dbconn.disconnect();
		dbconn = null;

	}
	
	public String getServerType() {
		
		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);

		ResultSet rs = dbconn.select("SELECT Servertype FROM Settings LIMIT 1;");
		if(rs == null) {
			dbconn.disconnect();
			dbconn = null;
			return null;
		} else {
			try {
				while ( rs.next() ) {
					String type = rs.getString("Servertype");
					dbconn.disconnect();
					dbconn = null;
					return type;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dbconn.disconnect();
			dbconn = null;
			return null;
		}
		
	}
	
	public String[] getMySQLData(String servertype) {
		
		String[] login = new String[4];
		
		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);

		ResultSet rs = dbconn.select("SELECT * FROM Server WHERE DatabaseType='" + servertype + "' LIMIT 1;");
		if(rs == null) {
			dbconn.disconnect();
			dbconn = null;
			return null;
		} else {
			try {
				while ( rs.next() ) {
					login[0] = rs.getString("DatabaseName");
					login[1] = rs.getString("DatabaseURL");
					login[2] = rs.getString("DatabaseUser");
					login[3] = rs.getString("DatabasePassword");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dbconn.disconnect();
			dbconn = null;
			return login;
		}
	}

	public void setLoginAutoFillData(String user, String password) {

		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);

		String sql = "UPDATE LoginAutoFill SET Username='" + user + "', Password='" + password + "' WHERE ID=1;";

		dbconn.update(sql);

		dbconn.disconnect();
		dbconn = null;

	}

	public String[] getLoginAutoFillData() {

		String[] login = new String[2];
		login[0] = "";
		login[1] = "";
		
		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);

		String sql = "SELECT * FROM LoginAutoFill WHERE ID=1;";

		ResultSet rs = dbconn.select(sql);
		
		if(rs == null) {
			dbconn.disconnect();
			dbconn = null;
			return null;
		} else {
			try {
				while ( rs.next() ) {
					login[0] = rs.getString("Username");
					login[1] = rs.getString("Password");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dbconn.disconnect();
			dbconn = null;
			return login;
		}

	}

}
