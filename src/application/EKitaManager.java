package application;
	
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.YearMonth;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.text.html.ImageView;

import application.container.user.Account;
import application.controller.calendar.Controller;
import application.controller.error.errorController;
import application.controller.login.setupServerController;
import application.controller.root.kidsController;
import application.controller.root.kidsHoursController;
import application.controller.root.rootController;
import application.logic.login.loginLogic;
import application.logic.login.setupServer;
import application.logic.root.doctorsLogic;
import application.logic.root.employeesLogic;
import application.logic.root.groupsLogic;
import application.logic.root.kidsHoursLogic;
import application.logic.root.kidsLogic;
import application.logic.root.permissionsLogic;
import application.logic.root.rootLogic;
import configuration.settings;
import customclasses.FullCalendarView;
import customclasses.ListDoctors;
import customclasses.ListDoctorsConverter;
import customclasses.ListEmployee;
import customclasses.ListEmployeeConverter;
import customclasses.ListGender;
import customclasses.ListGenderConverter;
import customclasses.ListGroup;
import customclasses.ListGroupConverter;
import customclasses.ListPermissions;
import customclasses.ListPsbConverter;
import database.mysql.MySQL;
import database.sqlite.SQLite;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.encryption.FileEncryptor;
import util.localization.initLanguages;
import util.logging.Log;
import yaml.file.FileConfiguration;

/**
* <h1>Dienstplan Software</h1>
* Die Dienstplan Software soll es pädagogischen Einrichtungen
* und kleinen Firmen erleichtern, die regelmäßige Pflege
* von Dienstplänen durch eine geeignete Software zu erleichtern.
* <p>
* <b>Hinweis:</b> Wir sind stets darauf aus, unseren Code zu kommentieren
* und so dritten zu ermöglichen, diesen nachzuvollziehen.
*
* @author  Jan-Eric Dreßler
* @version 0.0.2
* @since   2014-08-14
*/

public class EKitaManager extends Application {
	
	/**
	 * Initialisierung der Programm-Konstanten
	 */
	public final String CREDIT_AUTHOR 			= "Jan-Eric Dreßler";
	public final String CREDIT_ORGANIZATION 	= "Digi-Kita GmbH";
	public final String CREDIT_VERSION 			= "0.0.1";
	public final String DATABASE_DIRECTORY		= "settings";
	public final String DATABASE_FILE			= "settings.db";
	public final String SAVE_DIRECTORY			= "saves";
	public final String PASSWORD_SALT			= "g867Rzu7657F6tdf7d758F687RfHfr4fgFDhD56ffdZtZr67R7RFGFGUGzuTRDTGUt2TGT";
	
	public final String APPLICATION_TITLE 		= "Digi-Kita - Kita-Manager 2018";
	public final String APPLICATION_ICON 		= "/resources/icons/application/blue-folder.png";
	
	public final Boolean DEBUG_MYSQL			= false;
	public final Boolean ERROR_MYSQL			= true;
	
	/**
	 * Spracheinstellungen werden über die gesamte Laufzeit allen Fenstern zugänglich gemacht
	 */
	// TODO Getter und Setter einbauen
	public ResourceBundle resourceBundle = null;

	/**
	 * Die Log-Klasse wird zum Start initialisiert und steht allen Klassen zur Verfügung
	 */
	// TODO Getter und Setter einbauen
	public Log log = null;

	// Configuration Files
	public FileConfiguration config = null;
	public File configFile = null;
	
	// H2 Database class
	public SQLite sqliteDatabase = null;
	// MySQL Database class
	public MySQL mysqlDatabase = null;
	
	// User class
	public Account account = null;
	
	// Import aller Logic-Klassen
	public loginLogic loginLogicC = null;
	public setupServer setupServerLogicC = null;
	public rootLogic rootLogicC = null;
	public kidsLogic kidsLogicC = null;
	public kidsHoursLogic kidsHoursLogicC = null;
	public groupsLogic groupsLogicC = null;
	public employeesLogic employeesLogicC = null;
	public permissionsLogic permissionsLogicC = null;
	public doctorsLogic doctorsLogicC = null;

	// Import aller benötigten Controller-Klassen
	public errorController errorController = null;
	public kidsController kidsController = null;
	public kidsHoursController kidsHoursController = null;
	public setupServerController setupServerController = null;
	public rootController rootController = null;
	
	//Import aller Converter Klassen
	public ListGenderConverter genderConverter = null;
	public ListGroupConverter groupConverter = null;
	public ListEmployeeConverter employeeConverter = null;
	public ListDoctorsConverter doctorsConverter = null;
	public ListPsbConverter psbConverter = null;

	// Import aller Config-Dateien
	//public appConfig appConfig = null;
	public settings settings = null;
	
	public initLanguages initLanguage;

	FXMLLoader loader = null;
	AnchorPane rootLayout = null;

	private Stage hiddenStage = null;
	public Stage errorStage = null;
	public Stage programStage = null;
	public Stage primaryStage = null;
	public Stage setupServerStage = null;
	public Stage setupFieldStage = null;
	public Stage kidsBetreuungszeitStage = null;
	public Stage printTestStage = null;
	
	private static EKitaManager instance;
	
	public Thread serverCheckThread = null;
	
	// TODO Observables in eigene Pseudo-Klasse unterbringen (auslagern)
	public ObservableList<MenuItem> languageList = FXCollections.observableArrayList();
	public ObservableList<String> languageIcon = FXCollections.observableArrayList();
	
	public ObservableList<ListGender> gender = FXCollections.observableArrayList();

	public ObservableList<ListGroup> groups = FXCollections.observableArrayList();
	public ObservableList<ListEmployee> employees = FXCollections.observableArrayList();
	public ObservableList<ListDoctors> doctors = FXCollections.observableArrayList();
	public ObservableList<ListPermissions> permissions = FXCollections.observableArrayList();
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {

		this.log = new Log();
		
		if(checkAlreadyRunning()) {
			log.LogWarning("Der Prozess läuft bereits!");
			displayErrorLayer().setErrorDetails("0 - 001", "Das Programm kann nicht gestartet werden, da es bereits im Hintergrund läuft oder die Einstellungen-Datenbank in Benutzung ist.");
			return;
			//System.exit(0);
		}
		
		
		log.LogInfo("Decrypting database...");
		try {
			FileEncryptor.decryptFile(getDataFolder() + File.separator + DATABASE_DIRECTORY + File.separator + DATABASE_FILE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			displayErrorLayer().setErrorDetails("0 - 002", "Die Einstellungen-Datenbank konnte nicht gelesen werden.");
			return;
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			displayErrorLayer().setErrorDetails("0 - 003", "Es sind Probleme bei der Entschlüsselung der Einstellungen-Datenbank aufgetreten.");
			return;
		}

		EKitaManager.instance = this;
		
		this.hiddenStage = new Stage();
		this.hiddenStage.initStyle(StageStyle.TRANSPARENT);
		
		this.primaryStage = primaryStage;
		this.sqliteDatabase = new SQLite(this);
		//this.mysqlDatabase = new MySQL(this);
		//this.appConfig = new appConfig(this);
		this.initLanguage = new initLanguages(this);
		
		//this.mysqlDatabase.testConnection();
		
		if(initLanguage.initiateLanguages()){
			log.LogInfo("Languages initialized");
		} else {
			log.LogError("error: Languages couldn't be initiated.");
			return;
		}
		
		this.settings = new settings(this);

		/*if(appConfig.initiateConfig()){
			log.LogInfo("initialized: config.yml");
		} else {
			log.LogError("error: config.yml couldn't be initiated.");
		}*/

		this.loginLogicC = new loginLogic(this);
		this.setupServerLogicC = new setupServer(this);
		this.rootLogicC = new rootLogic(this);
		this.doctorsLogicC = new doctorsLogic(this);
		this.kidsLogicC = new kidsLogic(this);
		this.kidsHoursLogicC = new kidsHoursLogic(this);
		this.groupsLogicC = new groupsLogic(this);
		this.employeesLogicC = new employeesLogic(this);
		this.permissionsLogicC = new permissionsLogic(this);
		
		this.groupConverter = new ListGroupConverter(this);
		this.employeeConverter = new ListEmployeeConverter(this);
		this.genderConverter = new ListGenderConverter(this);
		this.doctorsConverter = new ListDoctorsConverter(this);

		this.gender.add(new ListGender(0, "keine Angabe"));
		this.gender.add(new ListGender(1,"männlich"));
		this.gender.add(new ListGender(2,"weiblich"));
		this.gender.add(new ListGender(3,"divers"));
		
		loader = new FXMLLoader(this.getClass().getResource("/resources/fxml/login/login.fxml"));

		this.resourceBundle = ResourceBundle.getBundle("resources.localisation.local", new Locale(settings.getLanguageLanguage(), settings.getLanguageCountry()));
		loader.setResources(this.resourceBundle);
		languageIcon.add(0, settings.getLanguageCountry());

		try {
			rootLayout = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
			displayErrorLayer().setErrorDetails("0 - 004", "Das Programm ist beschädigt und kann nicht gestartet werden.");
			return;
		}

		Scene scene = new Scene(rootLayout);

		this.primaryStage.initStyle(StageStyle.UNDECORATED);
		this.primaryStage.setScene(scene);
		this.primaryStage.setTitle(this.APPLICATION_TITLE);
		//primaryStage.getIcons().add(new Image("/resources/icons/app-icon.png"));
		//primaryStage.getIcons().add(new Image("/resources/icons/favicon_32.png"));
		//primaryStage.getIcons().add(new Image("/resources/icons/favicon_64.png"));
		//this.primaryStage.getIcons().add(new Image(this.APPLICATION_ICON));
		this.primaryStage.show();
		
		//Thread filler = new Thread( new DataFiller());
		//filler.start();
		
		/*try {
			TESTloadCalendar();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

	@Override
	public void stop() {
		shutdownApplication();
	}

	// static method to get instance of view
	public static EKitaManager getInstance() {
	        return instance;
	}

	public File getDataFolder() {
		File currentDirectory = new File(new File("").getAbsolutePath());
		return (new File(currentDirectory.toString()));
	}

	public void setAppLanguage(String language, String country) {
		reloadLogin(new Locale(language, country));
		log.LogInfo("Changed language to: " + language);
	}
	
	public void showTooltip(Stage owner, Control control, String tooltipText, ImageView tooltipGraphic) {
		javafx.geometry.Point2D p = control.localToScene(0.0, 25.0);

		final Tooltip customTooltip = new Tooltip();
		customTooltip.setText(tooltipText);

		control.setTooltip(customTooltip);
		customTooltip.setAutoHide(true);

		customTooltip.show(owner, p.getX() + control.getScene().getX() + control.getScene().getWindow().getX(), p.getY() + control.getScene().getY() + control.getScene().getWindow().getY());

	}

	private void reloadLogin(Locale locale) {
		AnchorPane pane = null;
		FXMLLoader fxmlLoader = null;
		fxmlLoader = new FXMLLoader(this.getClass().getResource("/resources/fxml/login/login.fxml"));
		this.resourceBundle = ResourceBundle.getBundle("resources.localisation.local", locale);
		fxmlLoader.setResources(this.resourceBundle);
		try {
			pane = fxmlLoader.load();
		} catch (Exception ex) {
			log.LogError("can't load /resources/fxml/login/login.fxml", ex);
			displayErrorLayer().setErrorDetails("0 - 005", "Das Programm ist beschädigt und kann nicht gestartet werden.");
			return;
		}

		Scene scene = new Scene(pane);
		this.primaryStage.setScene(scene);

	}
	
	public void loadStartScreen() {
		
		this.primaryStage.show();
		this.programStage.hide();
		
	}
	
	public void TESTloadCalendar() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/calendar/fullCalendar.fxml"));
        primaryStage.setTitle("Full Calendar FXML Example");
        primaryStage.setScene(new Scene(loader.load()));
        // Get the controller and add the calendar view to it
        Controller controller = loader.getController();
        controller.calendarPane.getChildren().add(new FullCalendarView(YearMonth.now()).getView());
        primaryStage.show();
		/*primaryStage.setTitle("Full Calendar Example");
        primaryStage.setScene(new Scene(new FullCalendarView(YearMonth.now()).getView()));
        primaryStage.show();*/
	}
	
	public void loadRoot() {
		BorderPane pane = null;
		FXMLLoader fxmlLoader = null;
		fxmlLoader = new FXMLLoader(this.getClass().getResource("/resources/fxml/main/root.fxml"));
		this.resourceBundle = ResourceBundle.getBundle("resources.localisation.local", new Locale(settings.getLanguageLanguage(), settings.getLanguageCountry()));
		fxmlLoader.setResources(this.resourceBundle);
		try {
			pane = fxmlLoader.load();
		} catch (Exception ex) {
			log.LogError("can't load /resources/fxml/main/root.fxml", ex);
			displayErrorLayer().setErrorDetails("0 - 006", "Das Programm ist beschädigt und kann nicht gestartet werden.");
			return;
		}

		//this.primaryStage.hide();
		Scene scene = new Scene(pane);
		this.primaryStage.hide();
		this.programStage = new Stage();
		this.programStage.initStyle(StageStyle.DECORATED);
		this.programStage.setTitle(this.APPLICATION_TITLE);
		//this.programStage.getIcons().add(new Image(this.APPLICATION_ICON));
		this.programStage.setMinHeight(pane.getMinHeight() + 50);
		this.programStage.setMinWidth(pane.getMinWidth() + 50);
		this.programStage.setScene(scene);
		this.programStage.show();
		
		this.rootLogicC.loadKids(pane);
	}
	
	public void loadEmployeesPane() {
		BorderPane pane = (BorderPane) this.programStage.getScene().getRoot();
		this.rootLogicC.loadEmployees(pane);
	}
	
	public void loadFieldsSetupPane() {
		BorderPane pane = (BorderPane) this.programStage.getScene().getRoot();
		this.rootLogicC.loadFieldsSetup(pane);
	}
	
	public void loadKidsPane() {
		BorderPane pane = (BorderPane) this.programStage.getScene().getRoot();
		this.rootLogicC.loadKids(pane);
	}
	
	public void loadGroupsPane() {
		BorderPane pane = (BorderPane) this.programStage.getScene().getRoot();
		this.rootLogicC.loadGroups(pane);
	}
	
	public void loadDoctorsPane() {
		BorderPane pane = (BorderPane) this.programStage.getScene().getRoot();
		this.rootLogicC.loadDoctors(pane);
	}
	
	public void loadPermissionsPane() {
		BorderPane pane = (BorderPane) this.programStage.getScene().getRoot();
		this.rootLogicC.loadPermissions(pane);
	}

	private void shutdownApplication() {
		
		if(this.checkAlreadyRunning())
			System.exit(0);
		
		log.LogInfo("Shutting down application...");
		this.hiddenStage.hide();
		if(serverCheckThread != null) {
			serverCheckThread.interrupt();
		}
		
		log.LogInfo("Encrypting database...");
		try {
			FileEncryptor.encryptFile(getDataFolder() + File.separator + DATABASE_DIRECTORY + File.separator + DATABASE_FILE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			displayErrorLayer().setErrorDetails("0 - 007", "Die Einstellungen-Datenbank konnte nicht gelesen werden.");
			return;
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			displayErrorLayer().setErrorDetails("0 - 008", "Es sind Probleme bei der Verschlüsselung der Einstellungen-Datenbank aufgetreten.");
			return;
		}
		
		log.LogInfo("Shutdown successfull!");
		System.exit(0);
		/*
		this.planOverview.forEach(item -> {
			File inputFile = new File(this.getDataFolder(), "saves" + File.separator + item);
			File encryptedFile = new File(this.getDataFolder(), "saves" + File.separator + item + ".enc");

			if(item.equalsIgnoreCase("Neuer Dienstplan")) {
			} else {
				try {
					FileEncryptor.encrypt("JEDressler", "Juhu", inputFile, encryptedFile);
					try{
			    		if(inputFile.delete()){
			    			System.out.println(inputFile.getName() + " is deleted!");
			    		}else{
			    			System.out.println("Delete operation is failed.");
			    		}				 
			    	}catch(Exception e){				 
			    		e.printStackTrace();				 
			    	}
				} catch (CryptoException ex) {
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
			
		});
		*/
	}
	
	private boolean checkAlreadyRunning() {
		
		File skip = new File(getDataFolder() + File.separator + DATABASE_DIRECTORY + File.separator + "skip.txt");
		if(skip.exists())
			return false;
		
		File encryptedFile = new File(getDataFolder() + File.separator + DATABASE_DIRECTORY + File.separator + DATABASE_FILE + ".encrypted");
		File decryptedFile = new File(getDataFolder() + File.separator + DATABASE_DIRECTORY + File.separator + DATABASE_FILE);
        
        if(!encryptedFile.exists() && decryptedFile.exists()) {
        	return true;
        }
		
		return false;
		
	}
	
	public errorController displayErrorLayer() {
		Stage dialog = new Stage();
		this.errorStage = dialog;
		dialog.initStyle(StageStyle.DECORATED);
		
		AnchorPane pane = null;
		FXMLLoader fxmlLoader = null;
		fxmlLoader = new FXMLLoader(this.getClass().getResource("/resources/fxml/login/error.fxml"));
		fxmlLoader.setResources(this.resourceBundle);
		try {
			pane = fxmlLoader.load();
		} catch (Exception ex) {
			this.log.LogError("can't load /resources/fxml/login/error.fxml", ex);
		}
		
		Scene scene = new Scene(pane);
		dialog.setScene(scene);
		dialog.setResizable(false);
		dialog.setTitle(this.APPLICATION_TITLE);
		//dialog.getIcons().add(new Image(this.APPLICATION_ICON));
		dialog.show();
		
		return (errorController) fxmlLoader.getController();
	}
	
}
