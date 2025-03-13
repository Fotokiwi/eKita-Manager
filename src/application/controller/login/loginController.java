package application.controller.login;

import application.EKitaManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class loginController {

	@FXML
	private Button login_button_login;
	@FXML
	private Button login_button_info;
	@FXML
	private Button login_button_user;
	@FXML
	private Button login_button_quit;
	@FXML
	private TextField login_textfield_username;
	@FXML
	private PasswordField login_passwordfield_password;
	@FXML
	private MenuBar login_menubar_language;
	@FXML
	private Menu login_menu_language;
	@FXML
	private ImageView login_icon_language;
	@FXML
	private CheckBox login_checkbox_savedata;
	@FXML
	private Label login_label_error;
	
	private boolean saveLoginData = false;
	
	private EKitaManager app;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public loginController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		
		this.app = EKitaManager.getInstance();
		
		this.login_menu_language.getItems().addAll(this.app.languageList);
		
		String[] loginData = this.app.settings.getLoginAutoFillData();
		this.login_textfield_username.setText(loginData[0]);
		this.login_passwordfield_password.setText(loginData[1]);
		if(!loginData[0].equalsIgnoreCase("") && !loginData[1].equalsIgnoreCase("")) {
			this.login_checkbox_savedata.setSelected(true);
		}

		//int index = languageList.indexOf(Main.getInstance().appConfig.getLanguageDescription());
		//this.login_choicebox_language.getSelectionModel().select(languageList.get(index));
		
		this.login_button_quit.setOnAction((event) -> {
			Platform.exit();
		});
		
		this.login_button_info.setOnAction((event) -> {
			this.app.loginLogicC.displayInfoLayer();
		});

		this.login_button_login.setOnAction((event) -> {
			if(login_checkbox_savedata.isSelected()) {
				this.saveLoginData = true;
			} else {
				this.saveLoginData = false;
			}
			if(this.login_textfield_username.getText().equalsIgnoreCase("")) {
				this.app.showTooltip(this.app.primaryStage, this.login_textfield_username, this.app.resourceBundle.getString("key.login_tooltip_fillin_username"), null);
				return;
			} else if(this.login_passwordfield_password.getText().equalsIgnoreCase("")) {
				this.app.showTooltip(this.app.primaryStage, this.login_passwordfield_password, this.app.resourceBundle.getString("key.login_tooltip_fillin_password"), null);
				return;
			}
			if(!this.app.loginLogicC.checkLogin(this.login_textfield_username, this.login_textfield_username.getText(), this.login_passwordfield_password.getText(), this.saveLoginData)) {
				this.app.showTooltip(this.app.primaryStage, this.login_button_login, this.app.resourceBundle.getString("key.login_tooltip_error_login"), null);
			} else {
				//this.app.showTooltip(this.app.primaryStage, this.login_button_login, "Juhu es hat geklappt!", null);
				//EKitaManager.loadRoot();
			}
		});
		
		this.app.languageList.forEach(item -> {
			item.setOnAction((event) -> {
				this.app.loginLogicC.setLanguage(item.getText());
			});
		});
		
		this.login_icon_language.setImage(new Image("/resources/icons/flags/" + this.app.languageIcon.get(0) + ".png"));
		ImageView addUser = new ImageView(new Image("/resources/icons/application/appbar.settings.png"));
		addUser.setFitHeight(17);
		addUser.setFitWidth(17);
		this.login_button_user.setGraphic(addUser);
		this.login_button_user.setTooltip(new Tooltip(this.app.resourceBundle.getString("key.login_tooltip_adduser")));
		
		this.login_button_user.setOnAction((event) -> {
			this.app.loginLogicC.displaySetupServerLayer();
		});
		
	}

}