package application.controller.error;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class errorController {
	
	//private EKitaManager app;
	
	@FXML
	private Label error_label_code;
	@FXML
	private Label error_label_description;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public errorController() {
		
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		
		//this.app = EKitaManager.getInstance();
		
	}
	
	public void setErrorDetails(String errorCode, String errorDescription) {
		this.error_label_code.setText(errorCode);
		this.error_label_description.setText(errorDescription);
	}

}
