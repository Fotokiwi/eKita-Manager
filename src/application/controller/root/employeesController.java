package application.controller.root;

import application.EKitaManager;
import customclasses.ListEmployee;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import util.stringcheck.StringCheck;

public class employeesController {
	
	@FXML
	public ChoiceBox<?> employees_choicebox_anrede;
	@FXML
	public ChoiceBox<?> employees_choicebox_titel;
	@FXML
	public TextField employees_textfield_nachname;
	@FXML
	public TextField employees_textfield_vorname;
	@FXML
	public TextField employees_textfield_adresse;
	@FXML
	public TextField employees_textfield_plz;
	@FXML
	public TextField employees_textfield_ort;
	@FXML
	public DatePicker employees_datepicker_geburtsdatum;
	@FXML
	public TextField employees_textfield_telefon;
	@FXML
	public TextField employees_textfield_arbeit;
	@FXML
	public TextField employees_textfield_mobil;
	@FXML
	public TextField employees_textfield_email;
	@FXML
	public TextField employees_textfield_personalnummer;
	@FXML
	public TextField employees_textfield_login_name;
	@FXML
	public TextField employees_textfield_login_passwort;
	
	@FXML
	public RadioButton employees_radiobutton_role_employee;
	@FXML
	public RadioButton employees_radiobutton_role_leader;
	@FXML
	public RadioButton employees_radiobutton_role_admin;
	
	//Container der Mitarbeiterdaten
	private ListEmployee employeeData = null;
	
	//Listenansicht
	@FXML
	private ListView<ListEmployee> employees_listview_overview;
	@FXML
	private Button employees_button_delete;
	@FXML
	private Button employees_button_save;
	
	private boolean firstListViewClick = true;
	
	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public employeesController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		
		updateEmployeesListview();
		
		this.employees_listview_overview.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	            if(firstListViewClick) {
	            	enableAllFields();
	            }
				resetFields();
				if(employees_listview_overview.getSelectionModel().getSelectedItem() != null)
					fillFields(employees_listview_overview.getSelectionModel().getSelectedItem().getEID());
	        }
	    });
		
		this.employees_button_delete.setOnAction((event) -> {
			EKitaManager.getInstance().employeesLogicC.deleteEmployee(this.employeeData);		
			updateEmployeesListview();
			resetFields();
		});
		
		this.employees_button_save.setOnAction((event) -> {
			saveFields();
		});
		
	}

	private void updateEmployeesListview() {
		ObservableList<ListEmployee> lefts = EKitaManager.getInstance().employeesLogicC.generateEmployeesList();
		
		this.employees_listview_overview.setItems(lefts);
		this.employees_listview_overview.setCellFactory(new Callback<ListView<ListEmployee>, ListCell<ListEmployee>>(){
			 
            @Override
            public ListCell<ListEmployee> call(ListView<ListEmployee> g) {
                 
                ListCell<ListEmployee> cell = new ListCell<ListEmployee>(){
 
                    @Override
                    protected void updateItem(ListEmployee t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getFullName());
                        }
                    }
 
                };
                 
                return cell;
            }
        });
	}
	
	private void fillFields(int id) {
		if(id == -1)
			return;
		
		this.employeeData = EKitaManager.getInstance().employeesLogicC.openEmployee(id);
		
		if(id == 0) {
			resetFields();
			return;
		}		
		
		if(employeeData == null)
			return;
		
		this.employees_textfield_nachname.setText(this.employeeData.getLastname());
		this.employees_textfield_vorname.setText(this.employeeData.getFirstname());
		this.employees_textfield_adresse.setText(this.employeeData.getAdresse());
		this.employees_textfield_plz.setText(this.employeeData.getPlz());
		this.employees_textfield_ort.setText(this.employeeData.getOrt());
		this.employees_datepicker_geburtsdatum.setValue(this.employeeData.getGeburtsdatum());
		this.employees_textfield_telefon.setText(this.employeeData.getTelefon());
		this.employees_textfield_arbeit.setText(this.employeeData.getArbeit());
		this.employees_textfield_mobil.setText(this.employeeData.getMobil());
		this.employees_textfield_email.setText(this.employeeData.getEmail());
		this.employees_textfield_personalnummer.setText(this.employeeData.getPersonalnummer());
		this.employees_textfield_login_name.setText(this.employeeData.getLoginName());
		this.employees_textfield_login_passwort.setText(this.employeeData.getLoginPasswort());
		
	}
	
	private void enableAllFields() {
		
		this.employees_choicebox_anrede.setDisable(false);
		this.employees_choicebox_titel.setDisable(false);
		this.employees_textfield_nachname.setDisable(false);
		this.employees_textfield_vorname.setDisable(false);
		this.employees_textfield_adresse.setDisable(false);
		this.employees_textfield_plz.setDisable(false);
		this.employees_textfield_ort.setDisable(false);
		this.employees_datepicker_geburtsdatum.setDisable(false);
		this.employees_textfield_telefon.setDisable(false);
		this.employees_textfield_arbeit.setDisable(false);
		this.employees_textfield_mobil.setDisable(false);
		this.employees_textfield_email.setDisable(false);
		this.employees_textfield_personalnummer.setDisable(false);
		this.employees_textfield_login_name.setDisable(false);
		this.employees_textfield_login_passwort.setDisable(false);
		
	}
	
	private void resetFields() {
		
		this.employees_choicebox_anrede.setValue(null);
		this.employees_choicebox_titel.setValue(null);
		this.employees_textfield_nachname.setText("");
		this.employees_textfield_vorname.setText("");
		this.employees_textfield_adresse.setText("");
		this.employees_textfield_plz.setText("");
		this.employees_textfield_ort.setText("");
		this.employees_datepicker_geburtsdatum.setValue(null);
		this.employees_textfield_telefon.setText("");
		this.employees_textfield_arbeit.setText("");
		this.employees_textfield_mobil.setText("");
		this.employees_textfield_email.setText("");
		this.employees_textfield_personalnummer.setText("");
		this.employees_textfield_login_name.setText("");
		this.employees_textfield_login_passwort.setText("");
		
	}
	
	private void saveFields() {
		
		if(!checkFields())
			return;
		
		this.employeeData.setLastname(this.employees_textfield_nachname.getText());
		
		EKitaManager.getInstance().employeesLogicC.saveEmployee(this.employeeData);		
		updateEmployeesListview();
		
	}

	private boolean checkFields() {

		if(StringCheck.containsIllegals(this.employees_textfield_nachname.getText()) || this.employees_textfield_vorname.getText().equalsIgnoreCase("")) {
			EKitaManager.getInstance().showTooltip(EKitaManager.getInstance().programStage, this.employees_textfield_nachname, EKitaManager.getInstance().resourceBundle.getString("key.error_wrongchars"), null);
			return false;
		}
		
		return true;
		
	}
	
}
