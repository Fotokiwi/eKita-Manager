package application.logic.root;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.EKitaManager;
import customclasses.ListEmployee;
import database.mysql.MySQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class employeesLogic {

	// Reference to the main application
	private EKitaManager app;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public employeesLogic(EKitaManager app) {
		
		this.app = app;
		
	}

	public ObservableList<ListEmployee> generateEmployeesList() {
		
		ObservableList<ListEmployee> employees = FXCollections.observableArrayList();
		
		employees.add(new ListEmployee(0, "< Neuen Mitarbeiter anlegen >", ""));
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return employees;
		
		ResultSet rs = dbconn.select("SELECT * FROM `Mitarbeiterindex` ORDER BY `Nachname`;");
		
		try {
			if (!rs.isBeforeFirst() ) {    
				dbconn.disconnect();
				dbconn = null;
				return employees;
			} else {
				while(rs.next()) {
					employees.add(new ListEmployee(rs.getInt("EID"), rs.getString("Anrede") , rs.getString("Titel") , rs.getString("Nachname") , rs.getString("Vorname"), rs.getString("Adresse"), rs.getString("Plz") , rs.getString("Ort"), rs.getString("Geburtsdatum") , rs.getString("Telefon"), rs.getString("Arbeit") , rs.getString("Mobil") , rs.getString("Email"), rs.getString("Personalnummer"), rs.getString("Login_Name"), rs.getString("Login_Passwort")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error generateEmployeesList", e);
			this.app.displayErrorLayer().setErrorDetails("9 - 001", "Die Mitarbeiterliste konnte nicht geladen werden.");
		}

		dbconn.disconnect();
		dbconn = null;
		return employees;
	}
	
	public ListEmployee openEmployee(int id) {
		
		ListEmployee employee = new ListEmployee();
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return employee;
		
		ResultSet rs = dbconn.select("SELECT * FROM `Mitarbeiterindex` WHERE `Mitarbeiterindex`.`EID`=" + id + ";");
		
		try {
			if (!rs.isBeforeFirst() ) {    
				dbconn.disconnect();
				dbconn = null;
				return employee;
			} else {
				while(rs.next()) {
					employee.setEID(rs.getInt("EID"));
					employee.setAnrede(rs.getString("Anrede"));
					employee.setTitel(rs.getString("Titel"));
					employee.setLastname(rs.getString("Nachname"));
					employee.setFirstname(rs.getString("Vorname"));
					employee.setAdresse(rs.getString("Adresse"));
					employee.setPlz(rs.getString("Plz"));
					employee.setOrt(rs.getString("Ort"));
					employee.setGeburtsdatum(rs.getString("Geburtsdatum"));
					employee.setTelefon(rs.getString("Telefon"));
					employee.setArbeit(rs.getString("Arbeit"));
					employee.setMobil(rs.getString("Mobil"));
					employee.setEmail(rs.getString("Email"));
					employee.setPersonalnummer(rs.getString("Personalnummer"));
					employee.setLoginName(rs.getString("Login_Name"));
					employee.setLoginPasswort(rs.getString("Login_Passwort"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error openEmployee", e);
			this.app.displayErrorLayer().setErrorDetails("9 - 002", "Die Mitarbeiterdaten konnten nicht geladen werden.");
		}

		dbconn.disconnect();
		dbconn = null;
		return employee;
	}

	public void saveEmployee(ListEmployee employeeData) {
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return;
		
		employeeData = saveEmployeeindex(dbconn, employeeData);

		dbconn.disconnect();
		dbconn = null;
		
	}
	
	private ListEmployee saveEmployeeindex(MySQL dbconn, ListEmployee data) {
		ResultSet rs = dbconn.select("SELECT * FROM `Mitarbeiterindex` WHERE `EID`=" + data.getEID() + ";");
		try {
			if (!rs.isBeforeFirst() ) {
				data.setEID(dbconn.insert("INSERT INTO `Mitarbeiterindex` "
						+ "(`EID` ,`Anrede` ,`Titel` ,`Nachname` ,`Vorname` ,`Adresse` ,`Plz` ,`Ort` ,`Geburtsdatum` ,`Telefon` ,`Arbeit` ,`Mobil` ,`Email` ,`Personalnummer` ,`Login_Name` ,`Login_Passwort`) "
						+ "VALUES "
						+ "(NULL ,  '" + data.getAnrede() + "',  '" + data.getTitel() + "',  '" + data.getLastname() + "',  '" + data.getFirstname() + "',  '" + data.getAdresse() + "',  '" + data.getPlz() + "',  '" + data.getOrt() + "',  '" + data.getGeburtsdatum(true) + "',  '" + data.getTelefon() + "',  '" + data.getArbeit() + "',  '" + data.getMobil() + "',  '" + data.getEmail() + "',  '" + data.getPersonalnummer() + "',  '" + data.getLoginName() + "',  '" + data.getLoginPasswort() + "');"));
				return data;
			} else {
				dbconn.update("UPDATE `Mitarbeiterindex` SET "
						+ "`Anrede` = '" + data.getAnrede() + "', "
						+ "`Titel` = '" + data.getTitel() + "', "
						+ "`Nachname` = '" + data.getLastname() + "', "
						+ "`Vorname` = '" + data.getFirstname() + "', "
						+ "`Adresse` = '" + data.getAdresse() + "', "
						+ "`Plz` = '" + data.getPlz() + "', "
						+ "`Ort` = '" + data.getOrt() + "', "
						+ "`Geburtsdatum` = '" + data.getGeburtsdatum(true) + "', "
						+ "`Telefon` = '" + data.getTelefon() + "', "
						+ "`Arbeit` = '" + data.getArbeit() + "', "
						+ "`Mobil` = '" + data.getMobil() + "', "
						+ "`Email` = '" + data.getEmail() + "', "
						+ "`Personalnummer` = '" + data.getPersonalnummer() + "', "
						+ "`Login_Name` = '" + data.getLoginName() + "', "
						+ "`Login_Passwort` = '" + data.getLoginPasswort() + "', "
						+ " WHERE `EID` =" + data.getEID() + ";");
				return data;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error saveEmployeeindex", e);
			this.app.displayErrorLayer().setErrorDetails("9 - 003", "Die Mitarbeiterdaten konnten nicht gespeichert werden.");
			return data;
		}
	}
	
	public boolean deleteEmployee(ListEmployee data) {
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return false;
		
		int eid = data.getEID();
		
		if(eid >= 1) {
			
			//System.out.println("true");
			
			deleteEmployeeindexEID(dbconn, eid);
			
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
	
	private boolean deleteEmployeeindexEID(MySQL dbconn, int eid) {
		
		ResultSet rs = dbconn.select("SELECT * FROM `Mitarbeiterindex` WHERE `EID`=" + eid + ";");
		try {
			if (!rs.isBeforeFirst() ) {
				return false;
			} else {
				int check = dbconn.delete("DELETE FROM `Mitarbeiterindex` WHERE `Mitarbeiterindex`.`EID` = " + eid + ";");
				if(check == 0)
					return false;
				
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error deleteEmployeeindexEID", e);
			this.app.displayErrorLayer().setErrorDetails("9 - 004", "Die Mitarbeiterdaten konnten nicht gelöscht werden.");
			return false;
		}	
		
	}

}
