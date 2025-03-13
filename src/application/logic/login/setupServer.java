package application.logic.login;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

import database.sqlite.SQLite;
import util.codegeneration.CodeGenerator;
import application.EKitaManager;

public class setupServer {

	// Reference to the main application
	private EKitaManager app;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 * @throws UnknownHostException 
	 */
	public setupServer(EKitaManager app) {
		
		this.app = app;
		
	}
	
	public boolean saveServer(String dbtype, String dbname, String dburl, String protocol, String dbuser, String dbpassword) {
		URL url;
		try {
			url = new URL(protocol + "://" + dburl + "/tools/servercheck.php");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(1000);
			conn.setReadTimeout(1000);
			if( conn.getResponseCode() == HttpURLConnection.HTTP_OK ){
				if(dbtype.equalsIgnoreCase("Locale")) {
					this.app.settings.setLocaleServerSettings(dburl);
					return true;
				} else {
					this.app.settings.setRemoteServerSettings(dbname, dburl, dbuser, dbpassword);
					return true;
				}
			} else {
				this.app.log.LogDebug("Konnte keine Verbindung herstellen");
				this.app.displayErrorLayer().setErrorDetails("4 - 001", "Die angegebene IP ist kein gültiger Server");
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogDebug("Konnte keine Verbindung herstellen v2");
			this.app.displayErrorLayer().setErrorDetails("4 - 001", "Die angegebene IP ist kein gültiger Server");
			return false;
		}
	}
	
	public void createUser(String username, String password, String dbtype, String dbname, String dburl, String dbuser, String dbpassword) {
		
		String securePassword = this.app.loginLogicC.generatePasswordHash(password);
		String secureDbPassword = this.app.loginLogicC.generatePasswordHash(dbpassword);
		if(dbpassword.equalsIgnoreCase(""))
			secureDbPassword="";
		
		String sql = "";
		
		if(dbtype.equalsIgnoreCase("Locale")) {
			sql = "INSERT INTO User (Username, Password, DatabaseType, DatabaseFile, DatabaseName, DatabaseURL, DatabaseUser, DatabasePassword) VALUES('" + username + "','" + securePassword + "','" + dbtype + "','" + CodeGenerator.getNext() + ".db" + "','" + dbname + "','" + dburl + "','" + dbuser + "','" + secureDbPassword + "')";
		} else {
			sql = "INSERT INTO User (Username, Password, DatabaseType, DatabaseFile, DatabaseName, DatabaseURL, DatabaseUser, DatabasePassword) VALUES('" + username + "','" + securePassword + "','" + dbtype + "','" + "" + "','" + dbname + "','" + dburl + "','" + dbuser + "','" + secureDbPassword + "')";
		}
		
		
		SQLite dbconn = new SQLite();
		dbconn.connect("settings", "settings.db");
		
		dbconn.insert(sql);		
		
		dbconn.disconnect();
		dbconn = null;
		
	}
	
	public String scanNetworkForServer(String ipNet) throws IOException {
		this.app.log.LogDebug(ipNet);
		String[] ipSegments = ipNet.split(".");
		for(int i = 100; i < 255; i++) {
			URL url = new URL("http://" + ipSegments[0] + "." + ipSegments[1] + "." + ipSegments[2] + "." + i + "/tools/servercheck.php");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			if( conn.getResponseCode() == HttpURLConnection.HTTP_OK ){
				return ipSegments[0] + "." + ipSegments[1] + "." + ipSegments[2] + "." + i;
			}
		}
		return "Server nicht gefunden";

	}

}