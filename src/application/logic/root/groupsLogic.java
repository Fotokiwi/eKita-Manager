package application.logic.root;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.EKitaManager;
import customclasses.ListGroup;
import customclasses.ListPerson;
import database.mysql.MySQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class groupsLogic {

	// Reference to the main application
	private EKitaManager app;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public groupsLogic(EKitaManager app) {
		
		this.app = app;
		
	}

	public ObservableList<ListGroup> generateGroupsList() {
		
		ObservableList<ListGroup> groups = FXCollections.observableArrayList();
		
		groups.add(new ListGroup(0, "< Neue Gruppe anlegen >", "", "", 0));
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return groups;
		
		ResultSet rs = dbconn.select("SELECT * FROM `Gruppenindex` ORDER BY `Gruppenname`;");
		
		try {
			if (!rs.isBeforeFirst() ) {    
				dbconn.disconnect();
				dbconn = null;
				return groups;
			} else {
				while(rs.next()) {
					groups.add(new ListGroup(rs.getInt("GID"), rs.getString("Gruppenname") , rs.getString("Gruppenbeschreibung"), rs.getString("Gruppenzimmer"), rs.getInt("Mitarbeiter")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error generateGroupsList", e);
			this.app.displayErrorLayer().setErrorDetails("10 - 001", "Die Gruppenliste konnte nicht geladen werden.");
		}

		dbconn.disconnect();
		dbconn = null;
		return groups;
	}

	public ObservableList<ListPerson> generateKidsList(int groupID) {
		
		ObservableList<ListPerson> persons = FXCollections.observableArrayList();
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return persons;
		
		ResultSet rs = dbconn.select("SELECT * FROM `Personenindex` WHERE `Gruppe` = " + groupID + " ORDER BY `Nachname`;");
		
		try {
			if (!rs.isBeforeFirst() ) {    
				dbconn.disconnect();
				dbconn = null;
				return persons;
			} else {
				while(rs.next()) {
					persons.add(new ListPerson(rs.getInt("PID"), rs.getString("Nachname") + ", " + rs.getString("Vorname")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error generateKidsList", e);
			this.app.displayErrorLayer().setErrorDetails("10 - 002", "Die Kinderliste konnte nicht geladen werden.");
		}

		dbconn.disconnect();
		dbconn = null;
		return persons;
	}
	
	public ListGroup openGroup(int id) {
		
		ListGroup group = new ListGroup();
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return group;
		
		ResultSet rs = dbconn.select("SELECT * FROM `Gruppenindex` WHERE `Gruppenindex`.`GID`=" + id + ";");
		
		try {
			if (!rs.isBeforeFirst() ) {    
				dbconn.disconnect();
				dbconn = null;
				return group;
			} else {
				while(rs.next()) {
					group.setGroupID(rs.getInt("GID"));
					group.setGroupName(rs.getString("Gruppenname"));
					group.setGroupDescription(rs.getString("Gruppenbeschreibung"));
					group.setGroupRoom(rs.getString("Gruppenzimmer"));
					group.setGroupEmployee(rs.getInt("Mitarbeiter"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error openGroup", e);
			this.app.displayErrorLayer().setErrorDetails("10 - 003", "Die Gruppendaten konnten nicht geladen werden.");
		}

		dbconn.disconnect();
		dbconn = null;
		return group;
	}

	public void saveGroup(ListGroup groupData) {
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return;
		
		groupData = saveGruppenindex(dbconn, groupData);

		dbconn.disconnect();
		dbconn = null;
		
	}
	
	private ListGroup saveGruppenindex(MySQL dbconn, ListGroup data) {
		ResultSet rs = dbconn.select("SELECT * FROM `Gruppenindex` WHERE `GID`=" + data.getGroupID() + ";");
		try {
			if (!rs.isBeforeFirst() ) {
				data.setGroupID(dbconn.insert("INSERT INTO `Gruppenindex` "
						+ "(`GID` ,`Gruppenname` ,`Gruppenbeschreibung` ,`Gruppenzimmer` ,`Mitarbeiter`) "
						+ "VALUES "
						+ "(NULL ,  '" + data.getGroupName() + "',  '" + data.getGroupDescription() + "',  '" + data.getGroupRoom() + "',  " + data.getGroupEmployee() + ");"));
				return data;
			} else {
				dbconn.update("UPDATE `Gruppenindex` SET "
						+ "`Gruppenname` = '" + data.getGroupName() + "', "
						+ "`Gruppenbeschreibung` = '" + data.getGroupDescription() + "', "
						+ "`Gruppenzimmer` = '" + data.getGroupRoom() + "', "
						+ "`Mitarbeiter` = " + data.getGroupEmployee() + " "
						+ " WHERE `Gruppenindex`.`GID` =" + data.getGroupID() + ";");
				return data;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error saveGroup", e);
			this.app.displayErrorLayer().setErrorDetails("10 - 004", "Die Gruppendaten konnten nicht gespeichert werden.");
			return data;
		}
	}
	
	public boolean deleteGroup(ListGroup data) {
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return false;
		
		int gid = data.getGroupID();
		
		//System.out.println(pid);
		
		if(gid >= 1) {
			
			//System.out.println("true");
			
			deleteGruppenindexGID(dbconn, gid);
			
			dbconn.disconnect();
			dbconn = null;
			
			return true;
			
		} else {
			
			//System.out.println("false");
			
			dbconn.disconnect();
			dbconn = null;
			return false;
			
		}		
		
	}
	
	private boolean deleteGruppenindexGID(MySQL dbconn, int gid) {
		
		ResultSet rs = dbconn.select("SELECT * FROM `Gruppenindex` WHERE `GID`=" + gid + ";");
		try {
			if (!rs.isBeforeFirst() ) {
				return false;
			} else {
				int check = dbconn.delete("DELETE FROM `Gruppenindex` WHERE `Gruppenindex`.`GID` = " + gid + ";");
				if(check == 0)
					return false;
				
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error deleteGroup", e);
			this.app.displayErrorLayer().setErrorDetails("10 - 005", "Die Gruppendaten konnten nicht gelöscht werden.");
			return false;
		}	
		
	}

}
