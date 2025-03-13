package application.logic.root;

import java.sql.ResultSet;
import java.sql.SQLException;

import customclasses.ListDoctors;
import customclasses.ListEmployee;
import customclasses.ListGroup;
import customclasses.ListPermissions;
import database.mysql.MySQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import application.EKitaManager;

public class rootLogic {

	// Reference to the main application
	private EKitaManager app;
	
	private AnchorPane kidsPage;
	private AnchorPane groupsPage;
	private AnchorPane doctorsPage;
	private AnchorPane permissionsPage;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public rootLogic(EKitaManager app) {
		
		this.app = app;
		
	}

	public void loadEmployees(BorderPane pane) {
		
		try {
	        FXMLLoader loader = new FXMLLoader(app.getClass().getResource("/resources/fxml/main/employee.fxml"));
	        this.kidsPage = (AnchorPane) loader.load();
	        pane.setCenter(this.kidsPage);
		} catch(Exception e) {
			//e.printStackTrace();
			this.app.log.LogError("can't load employee pane", e);
			this.app.displayErrorLayer().setErrorDetails("5 - 001", "Die Mitarbeiterverwaltung kann nicht geladen werden.");
		}
		
	}

	public void loadFieldsSetup(BorderPane pane) {
		
		try {
	        FXMLLoader loader = new FXMLLoader(app.getClass().getResource("/resources/fxml/menu/fieldSetup.fxml"));
	        this.kidsPage = (AnchorPane) loader.load();
	        pane.setCenter(this.kidsPage);
		} catch(Exception e) {
			//e.printStackTrace();
			this.app.log.LogError("can't load fieldsetup pane", e);
			this.app.displayErrorLayer().setErrorDetails("5 - 002", "Die Standardfeldverwaltung kann nicht geladen werden.");
		}
		
	}

	public void loadKids(BorderPane pane) {
		
		EKitaManager.getInstance().groups = getGroupList();
		
		try {
	        FXMLLoader loader = new FXMLLoader(app.getClass().getResource("/resources/fxml/main/kids.fxml"));
	        this.kidsPage = (AnchorPane) loader.load();
	        pane.setCenter(this.kidsPage);
		} catch(Exception e) {
			//e.printStackTrace();
			this.app.log.LogError("can't load kids pane", e);
			this.app.displayErrorLayer().setErrorDetails("5 - 003", "Die Kinderverwaltung kann nicht geladen werden.");
		}
		
	}
	
	public void loadGroups(BorderPane pane) {
		
		EKitaManager.getInstance().employees = getEmployeesList();
		
		try {
	        FXMLLoader loader = new FXMLLoader(app.getClass().getResource("/resources/fxml/main/groups.fxml"));
	        this.groupsPage = (AnchorPane) loader.load();
	        pane.setCenter(this.groupsPage);
		} catch(Exception e) {
			//e.printStackTrace();
			this.app.log.LogError("can't load groups pane", e);
			this.app.displayErrorLayer().setErrorDetails("5 - 004", "Die Gruppenverwaltung kann nicht geladen werden.");
		}
		
	}
	
	public void loadDoctors(BorderPane pane) {
		
		EKitaManager.getInstance().doctors = getDoctorsList();
		
		try {
	        FXMLLoader loader = new FXMLLoader(app.getClass().getResource("/resources/fxml/main/doctors.fxml"));
	        this.doctorsPage = (AnchorPane) loader.load();
	        pane.setCenter(this.doctorsPage);
		} catch(Exception e) {
			//e.printStackTrace();
			this.app.log.LogError("can't load doctorssetup pane", e);
			this.app.displayErrorLayer().setErrorDetails("5 - 005", "Die Ärzteverwaltung kann nicht geladen werden.");
		}
		
	}
	
	public void loadPermissions(BorderPane pane) {
		
		EKitaManager.getInstance().permissions = getPermissionsList();
		
		try {
	        FXMLLoader loader = new FXMLLoader(app.getClass().getResource("/resources/fxml/main/permissions.fxml"));
	        this.permissionsPage = (AnchorPane) loader.load();
	        pane.setCenter(this.permissionsPage);
		} catch(Exception e) {
			//e.printStackTrace();
			this.app.log.LogError("can't load permissions pane", e);
			this.app.displayErrorLayer().setErrorDetails("5 - 006", "Die Vollmachtenverwaltung kann nicht geladen werden.");
		}
		
	}

	public void displayFieldSetupLayer() {
		Stage dialog = new Stage();
		this.app.setupFieldStage = dialog;
		dialog.initStyle(StageStyle.UNDECORATED);
		
		AnchorPane pane = null;
		FXMLLoader fxmlLoader = null;
		fxmlLoader = new FXMLLoader(this.getClass().getResource("/resources/fxml/menu/fieldSetup.fxml"));
		fxmlLoader.setResources(this.app.resourceBundle);
		try {
			pane = fxmlLoader.load();
		} catch (Exception ex) {
			this.app.log.LogError("can't load /resources/fxml/menu/fieldSetup.fxml", ex);
			this.app.displayErrorLayer().setErrorDetails("5 - 007", "Die Standardfeldverwaltung kann nicht geladen werden.");
		}
		
		Scene scene = new Scene(pane);
		dialog.setScene(scene);
		dialog.setResizable(false);
		dialog.setTitle(this.app.APPLICATION_TITLE);
		dialog.getIcons().add(new Image(this.app.APPLICATION_ICON));
		dialog.show();
		
		return;
	}
	
	public ObservableList<ListGroup> getGroupList() {
		ObservableList<ListGroup> groups = FXCollections.observableArrayList();
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return groups;
		
		ResultSet rs = dbconn.select("SELECT * FROM `Gruppenindex`;");
		
		try {
			if (!rs.isBeforeFirst() ) {    
				dbconn.disconnect();
				dbconn = null;
				return null;
			} else {
				while(rs.next()) {
					groups.add(new ListGroup(rs.getInt("GID"), rs.getString("Gruppenname"), "", "", 0));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error groups list", e);
			this.app.displayErrorLayer().setErrorDetails("5 - 008", "Die Liste der Gruppen konnte nicht geladen werden.");
		}

		dbconn.disconnect();
		dbconn = null;
		
		return groups;
	}

	private ObservableList<ListEmployee> getEmployeesList() {
		ObservableList<ListEmployee> employees = FXCollections.observableArrayList();
		
		employees.add(new ListEmployee(0, "Keine Zuordnung", ""));
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return employees;
		
		ResultSet rs = dbconn.select("SELECT * FROM `Mitarbeiterindex`;");
		
		try {
			if (!rs.isBeforeFirst() ) {    
				dbconn.disconnect();
				dbconn = null;
				return null;
			} else {
				while(rs.next()) {
					employees.add(new ListEmployee(rs.getInt("EID"), rs.getString("Nachname"), rs.getString("Vorname")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error employee list", e);
			this.app.displayErrorLayer().setErrorDetails("5 - 009", "Die Liste der Mitarbeiter konnte nicht geladen werden.");
		}

		dbconn.disconnect();
		dbconn = null;
		
		return employees;
	}

	public ObservableList<ListPermissions> getPermissionsList() {
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
			this.app.log.LogError("mysql error permissions list", e);
			this.app.displayErrorLayer().setErrorDetails("5 - 010", "Die Liste der Vollmachten konnte nicht geladen werden.");
		}

		dbconn.disconnect();
		dbconn = null;
		
		return permissions;
	}

	public ObservableList<ListDoctors> getDoctorsList() {
		ObservableList<ListDoctors> doctors = FXCollections.observableArrayList();
		
		doctors.add(new ListDoctors(0, "Keine Zuordnung", ""));
		
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
			this.app.log.LogError("mysql error doctors list", e);
			this.app.displayErrorLayer().setErrorDetails("5 - 011", "Die Liste der Kinderärzte konnte nicht geladen werden.");
		}

		dbconn.disconnect();
		dbconn = null;
		
		return doctors;
	}

}
