package application.logic.login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.mysql.MySQL;
import util.properties.propertiesFile;
import application.EKitaManager;
import application.container.user.Account;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class loginLogic {

	// Reference to the main application
	private EKitaManager app;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public loginLogic(EKitaManager app) {
		this.app = app;
	}

	public void displayInfoLayer() {
		Stage dialog = new Stage();
		this.app.setupServerStage = dialog;
		dialog.initStyle(StageStyle.UTILITY);
		AnchorPane pane = null;
		FXMLLoader fxmlLoader = null;
		fxmlLoader = new FXMLLoader(this.getClass().getResource("/resources/fxml/login/help.fxml"));
		fxmlLoader.setResources(this.app.resourceBundle);
		try {
			pane = fxmlLoader.load();
		} catch (Exception ex) {
			this.app.log.LogError("can't load /resources/fxml/login/help.fxml", ex);
			this.app.displayErrorLayer().setErrorDetails("1 - 001", "Das Programm ist beschädigt und kann nicht gestartet werden.");
			return;
		}
		
		Scene scene = new Scene(pane);
		dialog.setScene(scene);
		dialog.setResizable(false);
		//dialog.setTitle(this.app.APPLICATION_TITLE);
		//dialog.getIcons().add(new Image(this.app.APPLICATION_ICON));
		dialog.show();
		
		return;
	}

	public void displaySetupServerLayer() {
		Stage dialog = new Stage();
		this.app.setupServerStage = dialog;
		dialog.initStyle(StageStyle.UNDECORATED);
		
		AnchorPane pane = null;
		FXMLLoader fxmlLoader = null;
		fxmlLoader = new FXMLLoader(this.getClass().getResource("/resources/fxml/login/setupServer.fxml"));
		fxmlLoader.setResources(this.app.resourceBundle);
		try {
			pane = fxmlLoader.load();
		} catch (Exception ex) {
			this.app.log.LogError("can't load /resources/fxml/login/setupServer.fxml", ex);
			this.app.displayErrorLayer().setErrorDetails("1 - 002", "Das Programm ist beschädigt und kann nicht gestartet werden.");
			return;
		}
		
		Scene scene = new Scene(pane);
		dialog.setScene(scene);
		dialog.setResizable(false);
		dialog.setTitle(this.app.APPLICATION_TITLE);
		dialog.getIcons().add(new Image(this.app.APPLICATION_ICON));
		dialog.show();
		
		return;
	}
	
	public String generatePasswordHash(String password) {
		String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(this.app.PASSWORD_SALT.getBytes());
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
			this.app.log.LogError("can't generate Password Hash", e);
			this.app.displayErrorLayer().setErrorDetails("1 - 003", "Ihr System unterstützt die genutzte Verschlüsselung nicht.");
        }
        return generatedPassword;
	}
	
	public boolean checkLogin(TextField textfield, String username, String password, boolean saveLoginData) {
		
		if(saveLoginData) {
			this.app.settings.setLoginAutoFillData(username, password);
		} else {
			this.app.settings.setLoginAutoFillData("", "");
		}
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(this.app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return false;
		
		ResultSet rs = dbconn.select("SELECT * FROM `Security_User`;");
		
		try {
			if (!rs.isBeforeFirst() ) {    
				this.app.showTooltip(this.app.primaryStage, textfield, this.app.resourceBundle.getString("key.login_tooltip_error_nouser"), null);
				dbconn.disconnect();
				dbconn = null;
				return false;
			} else {
				while(rs.next()) {
					//System.out.println(generatePasswordHash(password));
					if((rs.getString("UserLogin").equalsIgnoreCase(username)) && (rs.getString("PIN").equalsIgnoreCase(generatePasswordHash(password)))) {
					//if((rs.getString("UserLogin").equalsIgnoreCase(username)) && (rs.getString("PIN").equalsIgnoreCase(password))) {
						this.app.account = new Account(rs.getString("UserLogin"), rs.getString("Username"), rs.getString("Accounttyp"));
						//System.out.println(this.app.account.getLogin() + " - " + this.app.account.getUsername() + " - " + this.app.account.getAccounttype());
						this.app.loadRoot();
						return true;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			this.app.displayErrorLayer().setErrorDetails("1 - 004", "Die Logindaten konnten nicht überprüft werden. Die Datenbankabfrage war fehlerhaft.");
			e.printStackTrace();
		}

		dbconn.disconnect();
		dbconn = null;
		return false;
		
	}
	
	public void setLanguage(String language) {
		if(language.equalsIgnoreCase("Deutsch")) {
			String languageCode = "de";
			String countryCode = "DE";
			//this.app.appConfig.setLanguage(languageCode, countryCode, "Deutsch");
			this.app.settings.setLanguage(languageCode, countryCode, "Deutsch");
			this.app.languageIcon.set(0, countryCode);
			app.setAppLanguage(languageCode, countryCode);
		} else if(language.equalsIgnoreCase("English")){
			String languageCode = "en";
			String countryCode = "GB";
			//this.app.appConfig.setLanguage(languageCode, countryCode, "English");
			this.app.settings.setLanguage(languageCode, countryCode, "English");
			this.app.languageIcon.set(0, countryCode);
			app.setAppLanguage(languageCode, countryCode);
		} else if(language.equalsIgnoreCase("Français")){
			String languageCode = "fr";
			String countryCode = "FR";
			//this.app.appConfig.setLanguage(languageCode, countryCode, "Français");
			this.app.settings.setLanguage(languageCode, countryCode, "Français");
			this.app.languageIcon.set(0, countryCode);
			app.setAppLanguage(languageCode, countryCode);
		}  else {
			propertiesFile prop = new propertiesFile(language + ".properties");
			String languageCode = prop.getPropertyValue("key.init_languagecode");
			String countryCode = prop.getPropertyValue("key.init_country");
			this.app.settings.setLanguage(languageCode, countryCode, prop.getPropertyValue("key.init_description"));
			app.setAppLanguage(languageCode, countryCode);
		}
	}

}