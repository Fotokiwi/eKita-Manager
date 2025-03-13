package application.logic.root;


import java.sql.ResultSet;
import java.sql.SQLException;

import application.EKitaManager;
import customclasses.ListDoctors;
import database.mysql.MySQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class doctorsLogic {
	
	EKitaManager app;

	public doctorsLogic(EKitaManager eKitaManager) {
		
		this.app = eKitaManager;
		
	}

	public ObservableList<ListDoctors> generateDoctorsList() {
		
		ObservableList<ListDoctors> doctors = FXCollections.observableArrayList();
		
		doctors.add(new ListDoctors(0, "< Neuen Arzt anlegen >", ""));
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return doctors;
		
		ResultSet rs = dbconn.select("SELECT * FROM `Kinderarztindex`;");
		
		try {
			if (!rs.isBeforeFirst() ) {    
				
			} else {
				while(rs.next()) {
					doctors.add(new ListDoctors(rs.getInt("DID"), rs.getString("Anrede"), rs.getString("Titel"), rs.getString("Nachname"), rs.getString("Vorname"), rs.getString("Adresse"), rs.getString("Plz"), rs.getString("Ort"), rs.getString("Praxis"), rs.getString("Telefon"), rs.getString("Fax"), rs.getString("Email")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error generateDoctorsList", e);
			this.app.displayErrorLayer().setErrorDetails("8 - 001", "Die Ärzteliste konnte nicht geladen werden.");
		}

		dbconn.disconnect();
		dbconn = null;
		
		return doctors;
		
	}

	public ListDoctors openDoctor(int id) {
		ListDoctors doc = new ListDoctors();
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return doc;
		
		ResultSet rs = dbconn.select("SELECT * FROM `Kinderarztindex` WHERE `Kinderarztindex`.`DID`=" + id + ";");
		
		try {
			if (!rs.isBeforeFirst() ) {    
				dbconn.disconnect();
				dbconn = null;
				return doc;
			} else {
				while(rs.next()) {
					doc.setDID(rs.getInt("DID"));
					doc.setAnrede(rs.getString("Anrede"));
					doc.setTitel(rs.getString("Titel"));
					doc.setNachname(rs.getString("Nachname"));
					doc.setVorname(rs.getString("Vorname"));
					doc.setAdresse(rs.getString("Adresse"));
					doc.setPlz(rs.getString("Plz"));
					doc.setOrt(rs.getString("Ort"));
					doc.setPraxis(rs.getString("Praxis"));
					doc.setTelefon(rs.getString("Telefon"));
					doc.setFax(rs.getString("Fax"));
					doc.setEmail(rs.getString("Email"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error openDoctor", e);
			this.app.displayErrorLayer().setErrorDetails("8 - 002", "Die Arztdaten konnten nicht geladen werden.");
		}

		dbconn.disconnect();
		dbconn = null;
		return doc;
	}

	public void deleteDoctor(ListDoctors data) {
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return;
		
		ResultSet rs = dbconn.select("SELECT * FROM `Kinderarztindex` WHERE `DID`=" + data.getDID() + ";");
		try {
			if (!rs.isBeforeFirst() ) {
				
			} else {
				dbconn.delete("DELETE FROM `Kinderarztindex` WHERE `Kinderarztindex`.`DID` = " + data.getDID() + ";");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error deleteDoctor", e);
			this.app.displayErrorLayer().setErrorDetails("8 - 003", "Die Arztdaten konnten nicht gelöscht werden.");
		}
		
		dbconn.disconnect();
		dbconn = null;
		return;
		
	}

	public ListDoctors saveDoctor(ListDoctors data) {
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return data;
		
		ResultSet rs = dbconn.select("SELECT * FROM `Kinderarztindex` WHERE `DID`=" + data.getDID() + ";");
		try {
			if (!rs.isBeforeFirst() ) {
				data.setDID(dbconn.insert("INSERT INTO `Kinderarztindex` "
						+ "(`Anrede`, `Titel`, `Nachname`, `Vorname`, `Adresse`, `Plz`, `Ort`, `Praxis`, `Telefon`, `Fax`, `Email`) "
						+ "VALUES "
						+ "('" + data.getAnrede() + "',  '" + data.getTitel() + "',  '" + data.getNachname() + "',  '" + data.getVorname() + "',  '" + data.getAdresse() + "',  '" + data.getPlz() + "',  '" + data.getOrt() + "',  '" + data.getPraxis() + "',  '" + data.getTelefon() + "',  '" + data.getFax() + "',  '" + data.getEmail() + "');"));
				
				dbconn.disconnect();
				dbconn = null;
				return data;
			} else {
				dbconn.update("UPDATE `Kinderarztindex` SET "
						+ "`Anrede` = '" + data.getAnrede() + "', "
						+ "`Titel` = '" + data.getTitel() + "', "
						+ "`Nachname` = '" + data.getNachname() + "', "
						+ "`Vorname` = '" + data.getVorname() + "', "
						+ "`Adresse` = '" + data.getAdresse() + "', "
						+ "`Plz` = '" + data.getPlz() + "', "
						+ "`Ort` = '" + data.getOrt() + "', "
						+ "`Praxis` = '" + data.getPraxis() + "', "
						+ "`Telefon` = '" + data.getTelefon() + "', "
						+ "`Fax` = '" + data.getFax() + "', "
						+ "`Email` = '" + data.getEmail() + "' "
						+ " WHERE `DID` =" + data.getDID() + ";");
				
				dbconn.disconnect();
				dbconn = null;
				return data;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error saveDoctor", e);
			this.app.displayErrorLayer().setErrorDetails("8 - 004", "Die Arztdaten konnten nicht gespeichert werden.");
			
			dbconn.disconnect();
			dbconn = null;
			return data;
		}
		
	}
	
	

}
