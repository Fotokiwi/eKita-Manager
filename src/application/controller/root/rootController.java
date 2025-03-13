package application.controller.root;

import application.EKitaManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import printer.TEST;

public class rootController {
	
	// Menu - Datei
	@FXML
	private MenuItem menuStart;
	@FXML
	private MenuItem menuQuit;
	
	// Menu Einrichtung
	@FXML
	private MenuItem menuKita;
	@FXML
	private MenuItem menuGroups;
	@FXML
	private MenuItem menuPermissions;
	@FXML
	private MenuItem menuStatistics;
	@FXML
	private MenuItem menuPrints;
	
	// Menu Mitarbeiter
	@FXML
	private MenuItem menuEmployees;
	@FXML
	private MenuItem menuDevices;
	
	// Menu Einrichtung
	@FXML
	private MenuItem menuDoctors;
	
	
	@FXML
	private MenuItem menuFields;
	@FXML
	private MenuItem menuOptions;
	@FXML
	private MenuItem menuLanguage;

	@FXML
	private Button toggleKinder;
	@FXML
	private Button toggleGruppen;
	@FXML
	private Button toggleZeiterfassung;
	@FXML
	private Button toggleStatistiken;
	@FXML
	private Button toggleAusdrucke;

	@FXML
	private Button togglePrintTest;
	
	@FXML
	private ProgressBar root_progress_bar;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public rootController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		
		EKitaManager.getInstance().rootController = this;
		
		checkPrivileges();	
		

		this.togglePrintTest.setOnAction((event) -> {
			new TEST(EKitaManager.getInstance());
		});
		
		// Datei
		this.menuStart.setOnAction((event) -> {
			EKitaManager.getInstance().loadStartScreen();
		});
		
		this.menuQuit.setOnAction((event) -> {
			EKitaManager.getInstance().stop();
		});
		
		// Einrichtung
		
		this.menuGroups.setOnAction((event) -> {
			EKitaManager.getInstance().loadGroupsPane();
		});
		
		this.menuPermissions.setOnAction((event) -> {
			EKitaManager.getInstance().loadPermissionsPane();
		});
		
		// Mitarbeiter		
		
		this.menuEmployees.setOnAction((event) -> {
			EKitaManager.getInstance().loadEmployeesPane();
		});
		
		// Externe	
		
		this.menuDoctors.setOnAction((event) -> {
			EKitaManager.getInstance().loadDoctorsPane();
		});
		
		this.menuFields.setOnAction((event) -> {
			EKitaManager.getInstance().loadFieldsSetupPane();
		});
		
		this.toggleKinder.setOnAction((event) -> {
			EKitaManager.getInstance().loadKidsPane();
		});
		
		this.toggleGruppen.setOnAction((event) -> {
			EKitaManager.getInstance().loadGroupsPane();
		});
		
		this.root_progress_bar.setVisible(false);
		
	}
	
	private void checkPrivileges() {
		
		switch(EKitaManager.getInstance().account.getAccounttype()) {
			case "Master":
				//Einrichtung
	            this.menuKita.setDisable(false);
	            this.menuGroups.setDisable(false);
	            this.menuPermissions.setDisable(false);
	            this.menuStatistics.setDisable(false);
	            this.menuPrints.setDisable(false);
	            this.toggleGruppen.setDisable(false);
	            this.toggleStatistiken.setDisable(false);
	            this.toggleAusdrucke.setDisable(false);
	            
	            //Mitarbeiter	            
				this.menuEmployees.setDisable(false);				
				this.menuDevices.setDisable(false);
	            
	            //Externe	            
				this.menuDoctors.setDisable(false);
				
				//Einstellungen
				this.menuFields.setDisable(false);
				this.menuOptions.setDisable(false);
				this.menuLanguage.setDisable(false);
				
	            this.toggleKinder.setDisable(false);
	            this.toggleZeiterfassung.setDisable(false);
	            break;
			case "Admin":
				//Einrichtung
	            this.menuKita.setDisable(false);
	            this.menuGroups.setDisable(false);
	            this.menuPermissions.setDisable(false);
	            this.menuStatistics.setDisable(false);
	            this.menuPrints.setDisable(false);
	            this.toggleGruppen.setDisable(false);
	            this.toggleStatistiken.setDisable(false);
	            this.toggleAusdrucke.setDisable(false);
	            
	            //Mitarbeiter
				this.menuEmployees.setDisable(false);				
				this.menuDevices.setDisable(false);
	            
	            //Externe	            
				this.menuDoctors.setDisable(false);
				
				//Einstellungen
				this.menuFields.setDisable(false);
				this.menuOptions.setDisable(false);
				this.menuLanguage.setDisable(false);
				
	            this.toggleKinder.setDisable(false);
	            this.toggleZeiterfassung.setDisable(false);
	            break;
	        default:
				//Einrichtung
	            this.menuKita.setDisable(true);
	            this.menuGroups.setDisable(true);
	            this.menuPermissions.setDisable(true);
	            this.menuStatistics.setDisable(false);
	            this.menuPrints.setDisable(false);
	            this.toggleGruppen.setDisable(true);
	            this.toggleStatistiken.setDisable(false);
	            this.toggleAusdrucke.setDisable(false);
	            
	            //Mitarbeiter
				this.menuEmployees.setDisable(true);				
				this.menuDevices.setDisable(true);
	            
	            //Externe	            
				this.menuDoctors.setDisable(true);
				
				//Einstellungen
				this.menuFields.setDisable(true);
				this.menuOptions.setDisable(false);
				this.menuLanguage.setDisable(false);
				
	            this.toggleKinder.setDisable(false);
	            this.toggleZeiterfassung.setDisable(true);
		}
		
	}
	
	public void activateProgressBar(boolean value) {
		
		this.root_progress_bar.setVisible(value);
		
	}

}
