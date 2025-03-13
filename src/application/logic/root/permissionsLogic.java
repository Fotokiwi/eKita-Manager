package application.logic.root;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.EKitaManager;
import customclasses.ListPermissions;
import database.mysql.MySQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class permissionsLogic {

	// Reference to the main application
	private EKitaManager app;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public permissionsLogic(EKitaManager app) {
		
		this.app = app;
		
	}

	public ObservableList<ListPermissions> generatePermissionsList() {
		ObservableList<ListPermissions> permissions = FXCollections.observableArrayList();
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return permissions;
		
		ResultSet rs = dbconn.select("SELECT * FROM `Vollmachtenbeschreibung`;");
		
		try {
			if (!rs.isBeforeFirst() ) {    
				dbconn.disconnect();
				dbconn = null;
				return null;
			} else {
				while(rs.next()) {
					permissions.add(new ListPermissions(rs.getString("VID"), rs.getBoolean("Aktiv"), rs.getString("Name"), rs.getString("Beschreibung"), rs.getString("Kurzbeschreibung")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error generatePermissionsList", e);
			this.app.displayErrorLayer().setErrorDetails("12 - 001", "Die Vollmachtenliste konnte nicht geladen werden.");
		}

		dbconn.disconnect();
		dbconn = null;
		
		return permissions;
	}
	
	public ListPermissions openPermission(String id) {
		
		ListPermissions permission = new ListPermissions();
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return permission;
		
		ResultSet rs = dbconn.select("SELECT * FROM `Vollmachtenbeschreibung` WHERE `Vollmachtenbeschreibung`.`VID`='" + id + "';");
		
		try {
			if (!rs.isBeforeFirst() ) {    
				dbconn.disconnect();
				dbconn = null;
				return permission;
			} else {
				while(rs.next()) {
					permission.setID(rs.getString("VID"));
					permission.setActive(rs.getBoolean("Aktiv"));
					permission.setName(rs.getString("Name"));
					permission.setDescription(rs.getString("Beschreibung"));
					permission.setShortDescription(rs.getString("Kurzbeschreibung"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error openPermission", e);
			this.app.displayErrorLayer().setErrorDetails("12 - 002", "Die Vollmachtendaten konnte nicht geladen werden.");
		}

		dbconn.disconnect();
		dbconn = null;
		return permission;
	}
	
	public void savePermission(ListPermissions data) {
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return;
		
		ResultSet rs = dbconn.select("SELECT * FROM `Vollmachtenbeschreibung` WHERE `VID`='" + data.getID() + "';");
		try {
			if (!rs.isBeforeFirst() ) {
				dbconn.insert("INSERT INTO `Vollmachtenbeschreibung` "
						+ "(`VID` ,`Aktiv` ,`Name` ,`Beschreibung` ,`Kurzbeschreibung`) "
						+ "VALUES "
						+ "('" + data.getID() + "',  " + data.getActive() + ",  '" + data.getName() + "',  '" + data.getDescription() + "',  '" + data.getShortDescription() + "');");
			} else {
				dbconn.update("UPDATE `Vollmachtenbeschreibung` SET "
						+ "`Aktiv` = " + data.getActive() + ", "
						+ "`Name` = '" + data.getName() + "', "
						+ "`Beschreibung` = '" + data.getDescription() + "', "
						+ "`Kurzbeschreibung` = '" + data.getShortDescription() + "' "
						+ " WHERE `VID` = '" + data.getID() + "';");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error savePermission", e);
			this.app.displayErrorLayer().setErrorDetails("12 - 003", "Die Vollmachtendaten konnten nicht gespeichert werden.");
		}
		
	}

}
