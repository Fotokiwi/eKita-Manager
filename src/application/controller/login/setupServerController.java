package application.controller.login;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import util.servercheck.ServerCheck;
import application.EKitaManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class setupServerController {	

	@FXML
	private Button setupserver_button_exit;
	@FXML
	private ToggleButton setupserver_togglebutton_sqlite;
	@FXML
	private ToggleButton setupserver_togglebutton_mysql;
	@FXML
	private TextField setupserver_textfield_databasename;
	@FXML
	private Label setupserver_label_databasename;
	@FXML
	private TextField setupserver_textfield_databaseurl;
	@FXML
	private Label setupserver_label_databaseurl;
	@FXML
	private ToggleButton setupserver_togglebutton_protocol_http;
	@FXML
	private ToggleButton setupserver_togglebutton_protocol_https;
	@FXML
	private Button setupserver_button_searchserver;
	@FXML
	private TextField setupserver_textfield_databaseuser;
	@FXML
	private Label setupserver_label_databaseuser;
	@FXML
	private PasswordField setupserver_textfield_databasepassword;
	@FXML
	private Label setupserver_label_databasepassword;
	@FXML
	private Button setupserver_button_saveserver;
	@FXML
	private Button setupserver_button_resetserver;
	
	private String databaseType;
	private String protocol;
	
	private EKitaManager app;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public setupServerController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize(){
		
		this.app = EKitaManager.getInstance();
		
		ImageView quit = new ImageView(new Image("/resources/icons/application/appbar.close.png"));
		quit.setFitHeight(17);
		quit.setFitWidth(17);
		this.setupserver_button_exit.setGraphic(quit);
		
		fillForm();
		
		this.databaseType = "Remote";
		
		if(this.databaseType.equalsIgnoreCase("Locale")) {
			this.setupserver_textfield_databasename.setVisible(false);
			this.setupserver_textfield_databaseuser.setVisible(false);
			this.setupserver_textfield_databasepassword.setVisible(false);
		}
		
		this.setupserver_togglebutton_sqlite.setOnAction((event) -> {
			this.databaseType = "Locale";
			this.setupserver_label_databasename.setText(this.setupserver_label_databasename.getText().replace("*", ""));
			this.setupserver_label_databaseurl.setText(this.setupserver_label_databaseurl.getText().replace("*", ""));
			this.setupserver_label_databaseuser.setText(this.setupserver_label_databaseuser.getText().replace("*", ""));
			this.setupserver_label_databasepassword.setText(this.setupserver_label_databasepassword.getText().replace("*", ""));
			this.setupserver_textfield_databasename.setVisible(false);
			this.setupserver_textfield_databaseuser.setVisible(false);
			this.setupserver_textfield_databasepassword.setVisible(false);
			fillForm();
		});
		
		this.setupserver_togglebutton_mysql.setOnAction((event) -> {
			this.databaseType = "Remote";
			this.setupserver_label_databasename.setText(this.setupserver_label_databasename.getText().replace("*", "") + "*");
			this.setupserver_label_databaseurl.setText(this.setupserver_label_databaseurl.getText().replace("*", "") + "*");
			this.setupserver_label_databaseuser.setText(this.setupserver_label_databaseuser.getText().replace("*", "") + "*");
			this.setupserver_label_databasepassword.setText(this.setupserver_label_databasepassword.getText().replace("*", "") + "*");
			this.setupserver_textfield_databasename.setVisible(true);
			this.setupserver_textfield_databaseuser.setVisible(true);
			this.setupserver_textfield_databasepassword.setVisible(true);
			this.setupserver_textfield_databasename.setText("");
			this.setupserver_textfield_databaseuser.setText("");
			fillForm();
		});
		
		this.setupserver_button_searchserver.setOnAction((event) -> {
			if(this.app.serverCheckThread == null) {
				//Platform.runLater(new Thread(new ServerCheck(this.setupserver_textfield_databaseurl)));
				this.app.serverCheckThread = new Thread( new ServerCheck(getMyIP(), this.setupserver_textfield_databaseurl) );
				this.app.serverCheckThread.start();
				this.setupserver_button_searchserver.setText(this.app.resourceBundle.getString("key.setupserver_cancelsearchserver"));
			} else {
				this.app.serverCheckThread.interrupt();
				this.app.serverCheckThread = null;
				this.setupserver_button_searchserver.setText(this.app.resourceBundle.getString("key.setupserver_searchserver"));
			}
			//this.setupserver_button_searchserver.setDisable(true);
		});
		
		this.setupserver_button_saveserver.setOnAction((event) -> {
			if(this.setupserver_togglebutton_protocol_https.isSelected()) {
				protocol = "https";
			} else {
				protocol = "http";
			}
			if(checkFields()) {
				//System.out.println(this.databaseType);
				//System.out.println(protocol);
				//System.out.println(this.setupserver_textfield_databasename.getText());
				//System.out.println(this.setupserver_textfield_databaseurl.getText());
				//System.out.println(this.setupserver_textfield_databaseuser.getText());
				//System.out.println(this.setupserver_textfield_databasepassword.getText());
				if(this.app.setupServerLogicC.saveServer(this.databaseType, this.setupserver_textfield_databasename.getText(), this.setupserver_textfield_databaseurl.getText(), protocol, this.setupserver_textfield_databaseuser.getText(), this.setupserver_textfield_databasepassword.getText())) {
					this.app.setupServerStage.close();
				} else {
					return;
				}
			} else {
				return;
			}
		});
		
		this.setupserver_button_resetserver.setOnAction((event) -> {
			this.resetForm();
		});
		
		this.setupserver_button_exit.setOnAction((event) -> {
			this.app.setupServerStage.close();
		});
	
	}
	
	private void fillForm() {
		
		String[] serverData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		this.setupserver_textfield_databasename.setText(serverData[0]);
		this.setupserver_textfield_databaseurl.setText(serverData[1]);
		this.setupserver_textfield_databaseuser.setText(serverData[2]);
		this.setupserver_textfield_databasepassword.setText(serverData[3]);
		
	}

	private void resetForm() {
	
		this.setupserver_textfield_databasename.setText("");
		this.setupserver_textfield_databaseurl.setText("");
		this.setupserver_textfield_databaseuser.setText("");
		this.setupserver_textfield_databasepassword.setText("");
		if(this.databaseType.equalsIgnoreCase("Locale")) {
			this.setupserver_textfield_databasename.setVisible(false);
			this.setupserver_textfield_databaseuser.setVisible(false);
			this.setupserver_textfield_databasepassword.setVisible(false);
		}
		
	}
	
	private boolean checkFields() {
		if(this.setupserver_textfield_databaseurl.getText().equalsIgnoreCase("")) {
			this.app.showTooltip(this.app.setupServerStage, this.setupserver_textfield_databaseurl, this.app.resourceBundle.getString("key.login_tooltip_fillin_databaseurl"), null);
			return false;
		}
		if(this.databaseType.equalsIgnoreCase("Remote")) {
			if(this.setupserver_textfield_databasename.getText().equalsIgnoreCase("")) {
				this.app.showTooltip(this.app.setupServerStage, this.setupserver_textfield_databasename, this.app.resourceBundle.getString("key.login_tooltip_fillin_databasename"), null);
				return false;
			} else if(this.setupserver_textfield_databaseuser.getText().equalsIgnoreCase("")) {
				this.app.showTooltip(this.app.setupServerStage, this.setupserver_textfield_databaseuser, this.app.resourceBundle.getString("key.login_tooltip_fillin_databaseuser"), null);
				return false;
			} else if(this.setupserver_textfield_databasepassword.getText().equalsIgnoreCase("")) {
				this.app.showTooltip(this.app.setupServerStage, this.setupserver_textfield_databasepassword, this.app.resourceBundle.getString("key.login_tooltip_fillin_databasepassword"), null);
				return false;
			}
		}
		return true;
	}
	
	private String getMyIP() {
		String ip = "";
	    try {
	        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
	        while (interfaces.hasMoreElements()) {
	            NetworkInterface iface = interfaces.nextElement();
	            // filters out 127.0.0.1 and inactive interfaces
	            if (iface.isLoopback() || !iface.isUp())
	                continue;

	            Enumeration<InetAddress> addresses = iface.getInetAddresses();
	            while(addresses.hasMoreElements()) {
	                InetAddress addr = addresses.nextElement();
	                ip = addr.getHostAddress();
	                //System.out.println(iface.getDisplayName() + " " + ip);
	                return ip;
	            }
	        }
	    } catch (SocketException e) {
			this.app.displayErrorLayer().setErrorDetails("3 - 001", "Es konnte keine Geräte-IP ermittelt werden.");
	        throw new RuntimeException(e);
	    }
		return ip;
	}
}