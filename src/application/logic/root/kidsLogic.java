package application.logic.root;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import customclasses.Betreuungszeit;
import customclasses.ListBerechtigte;
import customclasses.Permission;
import customclasses.ListPerson;
import customclasses.ListPersonensorgeberechtigte;
import customclasses.PersonBerechtigte;
import customclasses.PersonKid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import database.mysql.MySQL;
import application.EKitaManager;

public class kidsLogic {

	// Reference to the main application
	private EKitaManager app;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public kidsLogic(EKitaManager app) {
		
		this.app = app;
		
	}

	public ObservableList<ListPerson> generateKidsList(int kinderstatus) {
		
		ObservableList<ListPerson> persons = FXCollections.observableArrayList();
		
		persons.add(new ListPerson(0, "< Neue Person anlegen >"));
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return persons;
		
		ResultSet rs = null;
		
		if(kinderstatus == 0) {
			rs = dbconn.select("SELECT * FROM `Personenindex` WHERE `Aktiv`=0 ORDER BY `Nachname`;");
		} else if(kinderstatus == 1) {
			rs = dbconn.select("SELECT * FROM `Personenindex` WHERE `Aktiv`=1 ORDER BY `Nachname`;");
		} else {
			rs = dbconn.select("SELECT * FROM `Personenindex` ORDER BY `Nachname`;");
		}
				
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
			this.app.displayErrorLayer().setErrorDetails("7 - 001", "Die Kinderliste konnte nicht geladen werden.");

		}

		dbconn.disconnect();
		dbconn = null;
		return persons;
	}

	public ObservableList<ListBerechtigte> generateBerechtigtenList(int id) {
		
		ObservableList<ListBerechtigte> persons = FXCollections.observableArrayList();
		
		persons.add(new ListBerechtigte(0, "< Neuen Berechtigten anlegen >"));
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return persons;
		
		ResultSet rs = dbconn.select("SELECT * FROM `PBIndex` WHERE `PID`=" + id + ";");
		
		try {
			if (!rs.isBeforeFirst() ) {    
				dbconn.disconnect();
				dbconn = null;
				return persons;
			} else {
				while(rs.next()) {
					ResultSet result = dbconn.select("SELECT * FROM `Berechtigtenindex` WHERE `BID`=" + rs.getInt("BID") + ";");
					
					try {
						if (!result.isBeforeFirst() ) {    
							
						} else {
							while(result.next()) {
								persons.add(new ListBerechtigte(result.getInt("BID"), result.getString("Nachname") + ", " + result.getString("Vorname")));
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						this.app.log.LogError("mysql error generateBerechtigtenList", e);
						this.app.displayErrorLayer().setErrorDetails("7 - 002", "Die Berechtigtenliste konnte nicht geladen werden.");
					}				
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error generateBerechtigtenList", e);
			this.app.displayErrorLayer().setErrorDetails("7 - 003", "Die Berechtigtenliste konnte nicht geladen werden.");
		}

		dbconn.disconnect();
		dbconn = null;
		return persons;
	}

	public ObservableList<ListPersonensorgeberechtigte> generatePersonensorgeberechtigteList(int id) {
		
		ObservableList<ListPersonensorgeberechtigte> persons = FXCollections.observableArrayList();
		
		persons.add(new ListPersonensorgeberechtigte(0, "< Nicht verfügbar >"));
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return persons;
		
		ResultSet rs = dbconn.select("SELECT * FROM `PBIndex` WHERE `PID`=" + id + ";");
		
		try {
			if (!rs.isBeforeFirst() ) {    
				dbconn.disconnect();
				dbconn = null;
				return persons;
			} else {
				while(rs.next()) {
					ResultSet result = dbconn.select("SELECT * FROM `Berechtigtenindex` WHERE `BID`=" + rs.getInt("BID") + " AND `Sorgeberechtigt`=1;");
					
					try {
						if (!result.isBeforeFirst() ) {    
							
						} else {
							while(result.next()) {
								persons.add(new ListPersonensorgeberechtigte(result.getInt("BID"), result.getString("Nachname") + ", " + result.getString("Vorname")));
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						this.app.log.LogError("mysql error generatePersonensorgeberechtigtenList", e);
						this.app.displayErrorLayer().setErrorDetails("7 - 004", "Die Personensorgeberechtigtenliste konnte nicht geladen werden.");
					}				
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error generatePersonensorgeberechtigtenList", e);
			this.app.displayErrorLayer().setErrorDetails("7 - 005", "Die Personensorgeberechtigtenliste konnte nicht geladen werden.");
		}

		dbconn.disconnect();
		dbconn = null;
		return persons;
	}
	
	public PersonKid openPerson(int id) {
		
		//PersonKid kid = new PersonKid("", "", "", "", "", "", "", false);
		PersonKid kid = new PersonKid();
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return kid;
		
		ResultSet rs = dbconn.select("SELECT * FROM `Personenindex` LEFT JOIN `Gruppenindex` ON `Personenindex`.`Gruppe`=`Gruppenindex`.`GID` LEFT JOIN `Anschriftenindex` ON `Personenindex`.`PID`=`Anschriftenindex`.`PID` LEFT JOIN `Vertragsindex` ON `Personenindex`.`PID`=`Vertragsindex`.`PID` LEFT JOIN `Gesundheitsindex` ON `Personenindex`.`PID`=`Gesundheitsindex`.`PID` LEFT JOIN `Kinderarztindex` ON `Kinderarztindex`.`DID`=`Gesundheitsindex`.`Kinderarzt` WHERE `Personenindex`.`PID`=" + id + ";");
		
		try {
			if (!rs.isBeforeFirst() ) {    
				dbconn.disconnect();
				dbconn = null;
				return kid;
			} else {
				while(rs.next()) {
					kid.setPID(rs.getInt("PID"));
					kid.setNachname(rs.getString("Nachname"));
					kid.setVorname(rs.getString("Vorname"));
					kid.setStrasse(rs.getString("Straße"));
					kid.setHausnummer(rs.getString("Hausnummer"));
					kid.setPlz(rs.getString("Plz"));
					kid.setOrt(rs.getString("Ort"));
					kid.setGeburtsdatum(rs.getString("Geburtsdatum"));
					kid.setGeschlecht(this.app.gender.get(rs.getInt("Geschlecht")));
					kid.setGruppe(this.app.groupConverter.fromString(rs.getString("Gruppenname")));
					kid.setAktiv(rs.getBoolean("Aktiv"));
					kid.setVertragsbeginn(rs.getString("Vertragsbeginn"));
					kid.setVertragsende(rs.getString("Vertragsende"));
					kid.setBetreuungsart(rs.getInt("Betreuungsart"));
					//kid.setBetreuungszeit(rs.getInt("Betreuungszeit"));
					kid.setNationalitaet(rs.getString("Nationalitaet"));
					kid.setMuttersprache(rs.getString("Muttersprache"));
					kid.setKrankenkasse(rs.getString("Krankenkasse"));
					kid.setVersicherter(rs.getInt("Versicherter"));
					kid.setKrankheiten(rs.getString("Krankheiten"));
					kid.setAllergien(rs.getString("Allergien"));
					kid.setBesonderheiten(rs.getString("Besonderheiten"));
					kid.setKinderarzt(rs.getInt("Kinderarzt"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error openPerson", e);
			this.app.displayErrorLayer().setErrorDetails("7 - 006", "Die Kinderdaten konnten nicht geladen werden.");
		}

		dbconn.disconnect();
		dbconn = null;
		kid.setBetreuungszeit(openPersonBetreuungszeit(kid.getPID()));
		kid.setVollmachten(openPersonVollmachten(kid.getPID()));
		return kid;
	}
	
	public List<Betreuungszeit> openPersonBetreuungszeit(int id) {
		
		List<Betreuungszeit> zeiten = new ArrayList<Betreuungszeit>();
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return zeiten;
		
		ResultSet rs = dbconn.select("SELECT * FROM `Betreuungszeitindex` WHERE `Betreuungszeitindex`.`PID`=" + id + " ORDER BY `Betreuungszeitindex`.`Beginn`;");
		
		try {
			if (!rs.isBeforeFirst() ) {    
				dbconn.disconnect();
				dbconn = null;
				return zeiten;
			} else {
				while(rs.next()) {
					zeiten.add(new Betreuungszeit(rs.getInt("ZID"), rs.getString("Beginn"), rs.getString("Ende"), rs.getInt("Stunden")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error openPersonBetreuungszeitList", e);
			this.app.displayErrorLayer().setErrorDetails("7 - 007", "Die Betreuungszeitenliste konnte nicht geladen werden.");
		}

		dbconn.disconnect();
		dbconn = null;
		return zeiten;
	}
	
	public List<Permission> openPersonVollmachten(int id) {
		
		List<Permission> vollmachten = new ArrayList<Permission>();
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return vollmachten;
		
		ResultSet rs = dbconn.select("SELECT * FROM `Vollmachtenindex` WHERE `Vollmachtenindex`.`PID`=" + id + ";");
		
		try {
			if (!rs.isBeforeFirst() ) {
				vollmachten.add(new Permission("Vollmacht_1", false));
				vollmachten.add(new Permission("Vollmacht_2", false));
				vollmachten.add(new Permission("Vollmacht_3", false));
				vollmachten.add(new Permission("Vollmacht_4", false));
				vollmachten.add(new Permission("Vollmacht_5", false));
				vollmachten.add(new Permission("Vollmacht_6", false));
				vollmachten.add(new Permission("Vollmacht_7", false));
				vollmachten.add(new Permission("Vollmacht_8", false));
				vollmachten.add(new Permission("Vollmacht_9", false));
				vollmachten.add(new Permission("Vollmacht_10", false));
				vollmachten.add(new Permission("Vollmacht_11", false));
				vollmachten.add(new Permission("Vollmacht_12", false));
				vollmachten.add(new Permission("Vollmacht_13", false));
				vollmachten.add(new Permission("Vollmacht_14", false));
				vollmachten.add(new Permission("Vollmacht_15", false));
				vollmachten.add(new Permission("Vollmacht_16", false));
				vollmachten.add(new Permission("Vollmacht_17", false));
				vollmachten.add(new Permission("Vollmacht_18", false));
				vollmachten.add(new Permission("Vollmacht_19", false));
				vollmachten.add(new Permission("Vollmacht_20", false));
			} else {
				while(rs.next()) {
					vollmachten.add(new Permission("Vollmacht_1", rs.getBoolean("Vollmacht_1")));
					vollmachten.add(new Permission("Vollmacht_2", rs.getBoolean("Vollmacht_2")));
					vollmachten.add(new Permission("Vollmacht_3", rs.getBoolean("Vollmacht_3")));
					vollmachten.add(new Permission("Vollmacht_4", rs.getBoolean("Vollmacht_4")));
					vollmachten.add(new Permission("Vollmacht_5", rs.getBoolean("Vollmacht_5")));
					vollmachten.add(new Permission("Vollmacht_6", rs.getBoolean("Vollmacht_6")));
					vollmachten.add(new Permission("Vollmacht_7", rs.getBoolean("Vollmacht_7")));
					vollmachten.add(new Permission("Vollmacht_8", rs.getBoolean("Vollmacht_8")));
					vollmachten.add(new Permission("Vollmacht_9", rs.getBoolean("Vollmacht_9")));
					vollmachten.add(new Permission("Vollmacht_10", rs.getBoolean("Vollmacht_10")));
					vollmachten.add(new Permission("Vollmacht_11", rs.getBoolean("Vollmacht_11")));
					vollmachten.add(new Permission("Vollmacht_12", rs.getBoolean("Vollmacht_12")));
					vollmachten.add(new Permission("Vollmacht_13", rs.getBoolean("Vollmacht_13")));
					vollmachten.add(new Permission("Vollmacht_14", rs.getBoolean("Vollmacht_14")));
					vollmachten.add(new Permission("Vollmacht_15", rs.getBoolean("Vollmacht_15")));
					vollmachten.add(new Permission("Vollmacht_16", rs.getBoolean("Vollmacht_16")));
					vollmachten.add(new Permission("Vollmacht_17", rs.getBoolean("Vollmacht_17")));
					vollmachten.add(new Permission("Vollmacht_18", rs.getBoolean("Vollmacht_18")));
					vollmachten.add(new Permission("Vollmacht_19", rs.getBoolean("Vollmacht_19")));
					vollmachten.add(new Permission("Vollmacht_20", rs.getBoolean("Vollmacht_20")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error openPersonVollmachten", e);
			this.app.displayErrorLayer().setErrorDetails("7 - 008", "Die Vollmachtenliste konnte nicht geladen werden.");
		}

		dbconn.disconnect();
		dbconn = null;
		return vollmachten;
	}
	
	public PersonBerechtigte openPersonBerechtigte(int id) {
		
		//PersonKid kid = new PersonKid("", "", "", "", "", "", "", false);
		PersonBerechtigte berechtigte = new PersonBerechtigte();
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return berechtigte;
		
		ResultSet rs = dbconn.select("SELECT * FROM `Berechtigtenindex` LEFT JOIN `Anschriftenindex` ON `Berechtigtenindex`.`BID`=`Anschriftenindex`.`BID` LEFT JOIN `Kontakteindex` ON `Berechtigtenindex`.`BID`=`Kontakteindex`.`BID` WHERE `Berechtigtenindex`.`BID`=" + id + ";");
		
		try {
			if (!rs.isBeforeFirst() ) {    
				dbconn.disconnect();
				dbconn = null;
				return berechtigte;
			} else {
				while(rs.next()) {
					berechtigte.setBID(rs.getInt("BID"));
					berechtigte.setNachname(rs.getString("Nachname"));
					berechtigte.setVorname(rs.getString("Vorname"));
					berechtigte.setGeburtsdatum(rs.getString("Geburtsdatum"));
					berechtigte.setSorgeberechtigt(rs.getBoolean("Sorgeberechtigt"));
					berechtigte.setKontaktperson(rs.getBoolean("Kontaktperson"));
					berechtigte.setStrasse(rs.getString("Straße"));
					berechtigte.setHausnummer(rs.getString("Hausnummer"));
					berechtigte.setPlz(rs.getString("Plz"));
					berechtigte.setOrt(rs.getString("Ort"));
					berechtigte.setPrivat(rs.getString("Privat"));
					berechtigte.setArbeit(rs.getString("Arbeit"));
					berechtigte.setMobil(rs.getString("Mobil"));
					berechtigte.setMail(rs.getString("Mail"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error openPersonBerechtigte", e);
			this.app.displayErrorLayer().setErrorDetails("7 - 009", "Die Abholberechtigtenliste konnte nicht geladen werden.");
		}

		dbconn.disconnect();
		dbconn = null;
		return berechtigte;
	}
	
	public void savePerson(PersonKid data) {
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return;
		
		data = savePersonenindex(dbconn, data);
		saveAnschriftenindex(dbconn, data);
		saveVertragsindex(dbconn, data);
		saveGesundheitsindex(dbconn, data);
		saveBetreuungszeitindex(dbconn, data);
		saveVollmachtenindex(dbconn, data);

		dbconn.disconnect();
		dbconn = null;
		
	}

	public boolean deletePerson(PersonKid data) {
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return false;
		
		int pid = data.getPID();
		
		//System.out.println(pid);
		
		if(pid >= 1) {
			
			//System.out.println("true");
			
			deletePersonenindexPID(dbconn, pid);
			deleteAnschriftenindexPID(dbconn, pid);
			deleteVertragsindexPID(dbconn, pid);
			deleteGesundheitsindexPID(dbconn, pid);
			deleteBetreuungszeitindexPID(dbconn, pid);
			deleteVollmachtenindex(dbconn, pid);
			
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

	public boolean deletePerson(PersonBerechtigte data) {
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return false;
		
		int bid = data.getBID();
		
		if(bid >= 1) {
			
			deleteBerechtigtenindexBID(dbconn, bid);
			deleteAnschriftenindexBID(dbconn, bid);
			deleteKontakteindexBID(dbconn, bid);
			
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

	public void savePersonBerechtigte(PersonBerechtigte data) {
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return;
		
		data = savePersonenindex(dbconn, data);
		saveAnschriftenindex(dbconn, data);
		saveKontakteindex(dbconn, data);

		dbconn.disconnect();
		dbconn = null;
	}
	
	public void savePBIndexTable(int pid, int bid) {
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return;
		
		ResultSet rs = dbconn.select("SELECT * FROM `PBIndex` WHERE `PID`=" + pid + " AND `BID`=" + bid + ";");
		try {
			if (!rs.isBeforeFirst() ) {
				dbconn.insert("INSERT INTO `PBIndex` "
						+ "(`PID` ,`BID`) "
						+ "VALUES "
						+ "('" + pid + "',  '" + bid + "');");
			} /*else {
				dbconn.update("UPDATE `Personenindex` SET "
						+ "`Nachname` = '" + data.getNachname() + "', "
						+ "`Vorname` = '" + data.getVorname() + "', "
						+ "`Geburtsdatum` = '" + data.getGeburtsdatum() + "', "
						+ "`Gruppe` = '" + data.getGruppe().getGroupID() + "', "
						+ "`Aktiv` = '" + data.getAktiv(false) + "' "
						+ " WHERE `PID` =" + data.getPID() + ";");
			}*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error savePBIndexTable", e);
			this.app.displayErrorLayer().setErrorDetails("7 - 010", "Es war nicht möglich die Daten in die Datenbank zu schreiben.");
		}

		dbconn.disconnect();
		dbconn = null;
	}
	
	private PersonKid savePersonenindex(MySQL dbconn, PersonKid data) {
		ResultSet rs = dbconn.select("SELECT * FROM `Personenindex` WHERE `PID`=" + data.getPID() + ";");
		try {
			if (!rs.isBeforeFirst() ) {
				data.setPID(dbconn.insert("INSERT INTO `Personenindex` "
						+ "(`Nachname` ,`Vorname` ,`Geburtsdatum` ,`Geschlecht` ,`Gruppe` ,`Aktiv`) "
						+ "VALUES "
						+ "('" + data.getNachname() + "',  '" + data.getVorname() + "',  '" + data.getGeburtsdatum() + "',  '" + data.getGeschlecht().getID() + "',  '" + data.getGruppe().getGroupID() + "',  '" + data.getAktiv(false) + "');"));
				return data;
			} else {
				dbconn.update("UPDATE `Personenindex` SET "
						+ "`Nachname` = '" + data.getNachname() + "', "
						+ "`Vorname` = '" + data.getVorname() + "', "
						+ "`Geburtsdatum` = '" + data.getGeburtsdatum() + "', "
						+ "`Geschlecht` = '" + data.getGeschlecht().getID() + "', "
						+ "`Gruppe` = '" + data.getGruppe().getGroupID() + "', "
						+ "`Aktiv` = '" + data.getAktiv(false) + "' "
						+ " WHERE `PID` =" + data.getPID() + ";");
				return data;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error savePersonenindex", e);
			this.app.displayErrorLayer().setErrorDetails("7 - 011", "Es war nicht möglich die Daten in die Datenbank zu schreiben.");
			return data;
		}
	}
	
	private boolean deletePersonenindexPID(MySQL dbconn, int pid) {
		
		ResultSet rs = dbconn.select("SELECT * FROM `Personenindex` WHERE `PID`=" + pid + ";");
		try {
			if (!rs.isBeforeFirst() ) {
				return false;
			} else {
				int check = dbconn.delete("DELETE FROM `Personenindex` WHERE `Personenindex`.`PID` = " + pid + ";");
				if(check == 0)
					return false;
				
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error deletePersonenindexPID", e);
			this.app.displayErrorLayer().setErrorDetails("7 - 012", "Es war nicht möglich die Daten aus der Datenbank zu löschen.");
			return false;
		}	
		
	}
	
	private boolean deleteBerechtigtenindexBID(MySQL dbconn, int bid) {
		
		ResultSet rs = dbconn.select("SELECT * FROM `Berechtigtenindex` WHERE `BID`=" + bid + ";");
		try {
			if (!rs.isBeforeFirst() ) {
				return false;
			} else {
				int check = dbconn.delete("DELETE FROM `Berechtigtenindex` WHERE `Berechtigtenindex`.`BID` = " + bid + ";");
				if(check == 0)
					return false;
				
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error deleteBerechtigtenindexBID", e);
			this.app.displayErrorLayer().setErrorDetails("7 - 013", "Es war nicht möglich die Daten aus der Datenbank zu löschen.");
			return false;
		}	
		
	}
	
	private PersonBerechtigte savePersonenindex(MySQL dbconn, PersonBerechtigte data) {
		ResultSet rs = dbconn.select("SELECT * FROM `Berechtigtenindex` WHERE `BID`=" + data.getBID() + ";");
		try {
			if (!rs.isBeforeFirst() ) {
				data.setBID(dbconn.insert("INSERT INTO `Berechtigtenindex` "
						+ "(`Nachname` ,`Vorname` ,`Geburtsdatum` ,`Sorgeberechtigt` ,`Kontaktperson` ,`Notizen`) "
						+ "VALUES "
						+ "('" + data.getNachname() + "',  '" + data.getVorname() + "',  '" + data.getGeburtsdatum() + "',  '" + data.getSorgeberechtigt(false) + "',  '" + data.getKontaktperson(false) + "', '');"));
				return data;
			} else {
				dbconn.update("UPDATE `Berechtigtenindex` SET "
						+ "`Nachname` = '" + data.getNachname() + "', "
						+ "`Vorname` = '" + data.getVorname() + "', "
						+ "`Geburtsdatum` = '" + data.getGeburtsdatum() + "', "
						+ "`Sorgeberechtigt` = '" + data.getSorgeberechtigt(false) + "', "
						+ "`Kontaktperson` = '" + data.getKontaktperson(false) + "' "
						+ " WHERE `BID` =" + data.getBID() + ";");
				return data;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error savePersonenindex", e);
			this.app.displayErrorLayer().setErrorDetails("7 - 014", "Es war nicht möglich die Daten in die Datenbank zu schreiben.");
			return data;
		}
	}
	
	private void saveAnschriftenindex(MySQL dbconn, PersonKid data) {
		ResultSet rs = dbconn.select("SELECT * FROM `Anschriftenindex` WHERE `PID`=" + data.getPID() + ";");
		try {
			if (!rs.isBeforeFirst() ) {
				dbconn.insert("INSERT INTO `Anschriftenindex` "
						+ "(`PID` ,`BID` ,`Straße` ,`Hausnummer` ,`Plz` ,`Ort`) "
						+ "VALUES "
						+ "('" + data.getPID() + "' , 0 , '" + data.getStrasse() + "',  '" + data.getHausnummer() + "',  '" + data.getPlz() + "',  '" + data.getOrt() + "');");
			} else {
				dbconn.update("UPDATE `Anschriftenindex` SET "
						+ "`Straße` = '" + data.getStrasse() + "', "
						+ "`Hausnummer` = '" + data.getHausnummer() + "', "
						+ "`Plz` = '" + data.getPlz() + "', "
						+ "`Ort` = '" + data.getOrt() + "' "
						+ " WHERE `PID` =" + data.getPID() + ";");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error saveAnschriftenindex", e);
			this.app.displayErrorLayer().setErrorDetails("7 - 015", "Es war nicht möglich die Daten in die Datenbank zu schreiben.");
		}
	}
	
	private void saveAnschriftenindex(MySQL dbconn, PersonBerechtigte data) {
		ResultSet rs = dbconn.select("SELECT * FROM `Anschriftenindex` WHERE `BID`=" + data.getBID() + ";");
		try {
			if (!rs.isBeforeFirst() ) {
				dbconn.insert("INSERT INTO `Anschriftenindex` "
						+ "(`PID` ,`BID` ,`Straße` ,`Hausnummer` ,`Plz` ,`Ort`) "
						+ "VALUES "
						+ "(0 , '" + data.getBID() + "' , '" + data.getStrasse() + "',  '" + data.getHausnummer() + "',  '" + data.getPlz() + "',  '" + data.getOrt() + "');");
			} else {
				dbconn.update("UPDATE `Anschriftenindex` SET "
						+ "`Straße` = '" + data.getStrasse() + "', "
						+ "`Hausnummer` = '" + data.getHausnummer() + "', "
						+ "`Plz` = '" + data.getPlz() + "', "
						+ "`Ort` = '" + data.getOrt() + "' "
						+ " WHERE `BID` =" + data.getBID() + ";");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error saveAnschriftenindex", e);
			this.app.displayErrorLayer().setErrorDetails("7 - 016", "Es war nicht möglich die Daten in die Datenbank zu schreiben.");
		}
	}
	
	private boolean deleteAnschriftenindexPID(MySQL dbconn, int pid) {
		
		ResultSet rs = dbconn.select("SELECT * FROM `Anschriftenindex` WHERE `PID`=" + pid + ";");
		try {
			if (!rs.isBeforeFirst() ) {
				return false;
			} else {
				int check = dbconn.delete("DELETE FROM `Anschriftenindex` WHERE `Anschriftenindex`.`PID` = " + pid + ";");
				if(check == 0)
					return false;
				
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error deleteAnschriftenindex", e);
			this.app.displayErrorLayer().setErrorDetails("7 - 017", "Es war nicht möglich die Daten aus der Datenbank zu löschen.");
			return false;
		}	
		
	}
	
	private boolean deleteAnschriftenindexBID(MySQL dbconn, int bid) {
		
		ResultSet rs = dbconn.select("SELECT * FROM `Anschriftenindex` WHERE `BID`=" + bid + ";");
		try {
			if (!rs.isBeforeFirst() ) {
				return false;
			} else {
				int check = dbconn.delete("DELETE FROM `Anschriftenindex` WHERE `Anschriftenindex`.`BID` = " + bid + ";");
				if(check == 0)
					return false;
				
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error deleteAnschriftenindex", e);
			this.app.displayErrorLayer().setErrorDetails("7 - 018", "Es war nicht möglich die Daten aus der Datenbank zu löschen.");
			return false;
		}	
		
	}
	
	private void saveVertragsindex(MySQL dbconn, PersonKid data) {
		ResultSet rs = dbconn.select("SELECT * FROM `Vertragsindex` WHERE `PID`=" + data.getPID() + ";");
		try {
			if (!rs.isBeforeFirst() ) {
				dbconn.insert("INSERT INTO `Vertragsindex` "
						+ "(`PID` ,`Vertragsbeginn`, `Vertragsende`, `Betreuungsart`, `Nationalitaet`, `Muttersprache`) "
						+ "VALUES "
						+ "('" + data.getPID() + "' ,  '" + data.getVertragsbeginn() + "',  '" + data.getVertragsende() + "',  " + data.getBetreuungsart() + ",  '" + data.getNationalitaet() + "',  '" + data.getMuttersprache() + "');");
			} else {
				dbconn.update("UPDATE `Vertragsindex` SET "
						+ "`Vertragsbeginn` = '" + data.getVertragsbeginn() + "', "
						+ "`Vertragsende` = '" + data.getVertragsende() + "', "
						+ "`Betreuungsart` = " + data.getBetreuungsart() + ", "
						+ "`Nationalitaet` = '" + data.getNationalitaet() + "', "
						+ "`Muttersprache` = '" + data.getMuttersprache() + "' "
						+ " WHERE `PID` =" + data.getPID() + ";");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error saveVertragsindex", e);
			this.app.displayErrorLayer().setErrorDetails("7 - 019", "Es war nicht möglich die Daten in die Datenbank zu schreiben.");
		}
	}
	
	private boolean deleteVertragsindexPID(MySQL dbconn, int pid) {
		
		ResultSet rs = dbconn.select("SELECT * FROM `Vertragsindex` WHERE `PID`=" + pid + ";");
		try {
			if (!rs.isBeforeFirst() ) {
				return false;
			} else {
				int check = dbconn.delete("DELETE FROM `Vertragsindex` WHERE `Vertragsindex`.`PID` = " + pid + ";");
				if(check == 0)
					return false;
				
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error deleteVertragsindexPID", e);
			this.app.displayErrorLayer().setErrorDetails("7 - 020", "Es war nicht möglich die Daten aus der Datenbank zu löschen.");
			return false;
		}	
		
	}
	
	private void saveGesundheitsindex(MySQL dbconn, PersonKid data) {
		ResultSet rs = dbconn.select("SELECT * FROM `Gesundheitsindex` WHERE `PID`=" + data.getPID() + ";");
		try {
			if (!rs.isBeforeFirst()) {
				dbconn.insert("INSERT INTO `Gesundheitsindex` "
						+ "(`PID` ,`Krankenkasse` ,`Versicherter` ,`Krankheiten` ,`Allergien` ,`Besonderheiten` ,`Kinderarzt`) "
						+ "VALUES "
						+ "('" + data.getPID() + "' ,  '" + data.getKrankenkasse() + "',  '" + data.getVersicherter() + "',  '" + data.getKrankheiten() + "',  '" + data.getAllergien() + "',  '" + data.getBesonderheiten() + "',  '" + data.getKinderarzt() + "');");
			} else {
				dbconn.update("UPDATE `Gesundheitsindex` SET "
						+ "`Krankenkasse` = '" + data.getKrankenkasse() + "', "
						+ "`Versicherter` = '" + data.getVersicherter() + "', "
						+ "`Krankheiten` = '" + data.getKrankheiten() + "', "
						+ "`Allergien` = '" + data.getAllergien() + "', "
						+ "`Besonderheiten` = '" + data.getBesonderheiten() + "', "
						+ "`Kinderarzt` = '" + data.getKinderarzt() + "' "
						+ " WHERE `PID` =" + data.getPID() + ";");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error saveGesundheitsindex", e);
			this.app.displayErrorLayer().setErrorDetails("7 - 021", "Es war nicht möglich die Daten in die Datenbank zu schreiben.");
		}
	}
	
	private boolean deleteGesundheitsindexPID(MySQL dbconn, int pid) {
		
		ResultSet rs = dbconn.select("SELECT * FROM `Gesundheitsindex` WHERE `PID`=" + pid + ";");
		try {
			if (!rs.isBeforeFirst() ) {
				return false;
			} else {
				int check = dbconn.delete("DELETE FROM `Gesundheitsindex` WHERE `Gesundheitsindex`.`PID` = " + pid + ";");
				if(check == 0)
					return false;
				
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error deleteGesundheitsindexPID", e);
			this.app.displayErrorLayer().setErrorDetails("7 - 022", "Es war nicht möglich die Daten aus der Datenbank zu löschen.");
			return false;
		}	
		
	}
	
	private void saveKontakteindex(MySQL dbconn, PersonBerechtigte data) {
		ResultSet rs = dbconn.select("SELECT * FROM `Kontakteindex` WHERE `BID`=" + data.getBID() + ";");
		try {
			if (!rs.isBeforeFirst() ) {
				dbconn.insert("INSERT INTO `Kontakteindex` "
						+ "(`BID` ,`Privat` ,`Arbeit` ,`Mobil` ,`Mail`) "
						+ "VALUES "
						+ "('" + data.getBID() + "' , '" + data.getPrivat() + "',  '" + data.getArbeit() + "',  '" + data.getMobil() + "',  '" + data.getMail() + "');");
			} else {
				dbconn.update("UPDATE `Kontakteindex` SET "
						+ "`Privat` = '" + data.getPrivat() + "', "
						+ "`Arbeit` = '" + data.getArbeit() + "', "
						+ "`Mobil` = '" + data.getMobil() + "', "
						+ "`Mail` = '" + data.getMail() + "' "
						+ " WHERE `BID` =" + data.getBID() + ";");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error saveKontakteindex", e);
			this.app.displayErrorLayer().setErrorDetails("7 - 023", "Es war nicht möglich die Daten in die Datenbank zu schreiben.");
		}
	}
	
	private boolean deleteKontakteindexBID(MySQL dbconn, int bid) {
		
		ResultSet rs = dbconn.select("SELECT * FROM `Kontakteindex` WHERE `BID`=" + bid + ";");
		try {
			if (!rs.isBeforeFirst() ) {
				return false;
			} else {
				int check = dbconn.delete("DELETE FROM `Kontakteindex` WHERE `Kontakteindex`.`BID` = " + bid + ";");
				if(check == 0)
					return false;
				
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error deleteKontakteindexBID", e);
			this.app.displayErrorLayer().setErrorDetails("7 - 024", "Es war nicht möglich die Daten aus der Datenbank zu löschen.");
			return false;
		}	
		
	}

	public void displayBetreuungszeitLayer(PersonKid data) {
		Stage dialog = new Stage();
		this.app.kidsBetreuungszeitStage = dialog;
		dialog.initStyle(StageStyle.DECORATED);
		
		AnchorPane pane = null;
		FXMLLoader fxmlLoader = null;
		fxmlLoader = new FXMLLoader(this.getClass().getResource("/resources/fxml/main/kids_hours.fxml"));
		fxmlLoader.setResources(this.app.resourceBundle);
		try {
			pane = fxmlLoader.load();
		} catch (Exception ex) {
			this.app.log.LogError("can't load /resources/fxml/main/kids_hours.fxml", ex);
			this.app.displayErrorLayer().setErrorDetails("7 - 025", "Es war nicht möglich den Betreuungszeiteditor zu öffnen.");
		}
		
		Scene scene = new Scene(pane);
		dialog.setScene(scene);
		dialog.setResizable(false);
		dialog.setTitle(this.app.APPLICATION_TITLE);
		dialog.getIcons().add(new Image(this.app.APPLICATION_ICON));
		dialog.show();
		
		return;
	}
	
	private void saveBetreuungszeitindex(MySQL dbconn, PersonKid data) {
		
		List<Betreuungszeit> list = data.getBetreuungszeiten();
		
		ResultSet rs = dbconn.select("SELECT * FROM `Betreuungszeitindex` WHERE `PID`=" + data.getPID() + ";");
		
		try {
			if (!rs.isBeforeFirst() ) { 
				
			} else {
				while(rs.next()) {
					boolean delete = true;
					for(int i = 0; i < list.size(); i++) {
						if(list.get(i).getZid() == rs.getInt("ZID")) {
							delete = false;
						}
					}	
					if(delete) {
						dbconn.delete("DELETE FROM `Betreuungszeitindex` WHERE `ZID` = " + rs.getInt("ZID") + ";");
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error saveBetreuungszeitindex", e);
			this.app.displayErrorLayer().setErrorDetails("7 - 026", "Es war nicht möglich die Daten in die Datenbank zu schreiben.");
		}
		
		for(int i = 0; i < list.size(); i++) {
			
			rs = dbconn.select("SELECT * FROM `Betreuungszeitindex` WHERE `ZID`=" + list.get(i).getZid() + ";");
			try {
				if (!rs.isBeforeFirst() ) {
					dbconn.insert("INSERT INTO `Betreuungszeitindex` "
							+ "(`PID` ,`Beginn` ,`Ende` ,`Stunden`) "
							+ "VALUES "
							+ "('" + data.getPID() + "', '" + list.get(i).getBeginn().toString() + "', '" + list.get(i).getEnde().toString() + "', '" + list.get(i).getStunden() + "');");
				} else {
					dbconn.update("UPDATE `Betreuungszeitindex` SET "
							+ "`Beginn` = '" + list.get(i).getBeginn().toString() + "', "
							+ "`Ende` = '" + list.get(i).getEnde().toString() + "', "
							+ "`Stunden` = " + list.get(i).getStunden() + " "
							+ " WHERE `ZID` =" + list.get(i).getZid() + ";");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				this.app.log.LogError("mysql error saveBetreuungszeitindex", e);
				this.app.displayErrorLayer().setErrorDetails("7 - 027", "Es war nicht möglich die Daten in die Datenbank zu schreiben.");
			}
			
		}
		
	}
		
	private boolean deleteBetreuungszeitindexPID(MySQL dbconn, int pid) {
		
		ResultSet rs = dbconn.select("SELECT * FROM `Betreuungszeitindex` WHERE `PID`=" + pid + ";");
		try {
			if (!rs.isBeforeFirst() ) {
				return false;
			} else {
				int check = dbconn.delete("DELETE FROM `Betreuungszeitindex` WHERE `Betreuungszeitindex`.`PID` = " + pid + ";");
				if(check == 0)
					return false;
				
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error deleteBetreuungszeitindexPID", e);
			this.app.displayErrorLayer().setErrorDetails("7 - 028", "Es war nicht möglich die Daten aus der Datenbank zu löschen.");
			return false;
		}	
		
	}

	private void saveVollmachtenindex(MySQL dbconn, PersonKid data) {
		
		ResultSet rs = dbconn.select("SELECT * FROM `Vollmachtenindex` WHERE `PID`=" + data.getPID() + ";");
		try {
			if (!rs.isBeforeFirst() ) {
				dbconn.insert("INSERT INTO `Vollmachtenindex` "
						+ "(`PID` ,`Vollmacht_1` ,`Vollmacht_2` ,`Vollmacht_3` ,`Vollmacht_4` ,`Vollmacht_5` ,`Vollmacht_6` ,`Vollmacht_7` ,`Vollmacht_8` ,`Vollmacht_9` ,`Vollmacht_10` ,`Vollmacht_11` ,`Vollmacht_12` ,`Vollmacht_13` ,`Vollmacht_14` ,`Vollmacht_15` ,`Vollmacht_16` ,`Vollmacht_17` ,`Vollmacht_18` ,`Vollmacht_19`, `Vollmacht_20`) "
						+ "VALUES "
						+ "('" + data.getPID() + "' , " + data.getVollmachten().get(0).getGiven() + " , " + data.getVollmachten().get(1).getGiven() + " , " + data.getVollmachten().get(2).getGiven() + " , " + data.getVollmachten().get(3).getGiven() + " , " + data.getVollmachten().get(4).getGiven() + " , " + data.getVollmachten().get(5).getGiven() + " , " + data.getVollmachten().get(6).getGiven() + " , " + data.getVollmachten().get(7).getGiven() + " , " + data.getVollmachten().get(8).getGiven() + " , " + data.getVollmachten().get(9).getGiven() + " , " + data.getVollmachten().get(10).getGiven() + " , " + data.getVollmachten().get(11).getGiven() + " , " + data.getVollmachten().get(12).getGiven() + " , " + data.getVollmachten().get(13).getGiven() + " , " + data.getVollmachten().get(14).getGiven() + " , " + data.getVollmachten().get(15).getGiven() + " , " + data.getVollmachten().get(16).getGiven() + " , " + data.getVollmachten().get(17).getGiven() + " , " + data.getVollmachten().get(18).getGiven() + " , " + data.getVollmachten().get(19).getGiven() + ");");
			} else {
				dbconn.update("UPDATE `Vollmachtenindex` SET "
						+ "`Vollmacht_1` = " + data.getVollmachten().get(0).getGiven() + ", "
						+ "`Vollmacht_2` = " + data.getVollmachten().get(1).getGiven() + ", "
						+ "`Vollmacht_3` = " + data.getVollmachten().get(2).getGiven() + ", "
						+ "`Vollmacht_4` = " + data.getVollmachten().get(3).getGiven() + ", "
						+ "`Vollmacht_5` = " + data.getVollmachten().get(4).getGiven() + ", "
						+ "`Vollmacht_6` = " + data.getVollmachten().get(5).getGiven() + ", "
						+ "`Vollmacht_7` = " + data.getVollmachten().get(6).getGiven() + ", "
						+ "`Vollmacht_8` = " + data.getVollmachten().get(7).getGiven() + ", "
						+ "`Vollmacht_9` = " + data.getVollmachten().get(8).getGiven() + ", "
						+ "`Vollmacht_10` = " + data.getVollmachten().get(9).getGiven() + ", "
						+ "`Vollmacht_11` = " + data.getVollmachten().get(10).getGiven() + ", "
						+ "`Vollmacht_12` = " + data.getVollmachten().get(11).getGiven() + ", "
						+ "`Vollmacht_13` = " + data.getVollmachten().get(12).getGiven() + ", "
						+ "`Vollmacht_14` = " + data.getVollmachten().get(13).getGiven() + ", "
						+ "`Vollmacht_15` = " + data.getVollmachten().get(14).getGiven() + ", "
						+ "`Vollmacht_16` = " + data.getVollmachten().get(15).getGiven() + ", "
						+ "`Vollmacht_17` = " + data.getVollmachten().get(16).getGiven() + ", "
						+ "`Vollmacht_18` = " + data.getVollmachten().get(17).getGiven() + ", "
						+ "`Vollmacht_19` = " + data.getVollmachten().get(18).getGiven() + ", "
						+ "`Vollmacht_20` = " + data.getVollmachten().get(19).getGiven() + " "
						+ " WHERE `PID` =" + data.getPID() + ";");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error saveVollmachtenindex", e);
			this.app.displayErrorLayer().setErrorDetails("7 - 029", "Es war nicht möglich die Daten in die Datenbank zu schreiben.");
		}
		
	}

	private boolean deleteVollmachtenindex(MySQL dbconn, int pid) {
		
		ResultSet rs = dbconn.select("SELECT * FROM `Vollmachtenindex` WHERE `PID`=" + pid + ";");
		try {
			if (!rs.isBeforeFirst() ) {
				return false;
			} else {
				int check = dbconn.delete("DELETE FROM `Vollmachtenindex` WHERE `Vollmachtenindex`.`PID` = " + pid + ";");
				if(check == 0)
					return false;
				
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error deleteVollmachtenindex", e);
			this.app.displayErrorLayer().setErrorDetails("7 - 030", "Es war nicht möglich die Daten aus der Datenbank zu löschen.");
			return false;
		}
		
	}

}
