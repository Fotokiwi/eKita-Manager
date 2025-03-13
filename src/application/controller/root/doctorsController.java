package application.controller.root;

import application.EKitaManager;
import customclasses.ListDoctors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class doctorsController {
	
	@FXML
	private ListView<ListDoctors> doctors_listview_overview;
	@FXML
	private ChoiceBox<String> doctors_choicebox_anrede;
	@FXML
	private ChoiceBox<String> doctors_choicebox_titel;
	@FXML
	private TextField doctors_textfield_nachname;
	@FXML
	private TextField doctors_textfield_vorname;
	@FXML
	private TextField doctors_textfield_adresse;
	@FXML
	private TextField doctors_textfield_plz;
	@FXML
	private TextField doctors_textfield_ort;
	@FXML
	private TextField doctors_textfield_praxis;
	@FXML
	private TextField doctors_textfield_telefon;
	@FXML
	private TextField doctors_textfield_fax;
	@FXML
	private TextField doctors_textfield_email;
	@FXML
	private Button doctors_button_save;
	@FXML
	private Button doctors_button_delete;
	@FXML
	private Button doctors_button_delete_accept;
	
	
	private boolean firstListViewClick = true;
	
	private ListDoctors doctorsData;
		

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public doctorsController() {
		
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		
		ObservableList<String> anredeListe = FXCollections.observableArrayList();
		anredeListe.add("");
		anredeListe.add("Herr");
		anredeListe.add("Frau");
		this.doctors_choicebox_anrede.setItems(anredeListe);
		
		ObservableList<String> titelListe = FXCollections.observableArrayList();
		titelListe.add("");
		titelListe.add("Dr.");
		titelListe.add("Dr. med.");
		titelListe.add("Dipl.-Med.");
		this.doctors_choicebox_titel.setItems(titelListe);
		
		updateDoctorsListView();
		
		this.doctors_listview_overview.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	            if(firstListViewClick) {
	            	enableAllFields();
	            }
				resetFields();
				if(doctors_listview_overview.getSelectionModel().getSelectedItem() != null)
					fillFields(doctors_listview_overview.getSelectionModel().getSelectedItem().getDID());
	        }
	    });
		
		this.doctors_button_save.setOnAction((event) -> {
			saveFields();
			updateDoctorsListView();
			resetFields();
		});
		
		this.doctors_button_delete.setOnAction((event) -> {
			this.doctors_button_delete_accept.setDisable(false);
			this.doctors_button_delete_accept.setVisible(true);
			this.doctors_button_delete_accept.setManaged(true);
			this.doctors_button_delete.setDisable(true);
			this.doctors_button_delete.setVisible(false);
			this.doctors_button_delete.setManaged(false);
		});
		

		this.doctors_button_delete_accept.setManaged(false);
		this.doctors_button_delete_accept.setOnAction((event) -> {
			EKitaManager.getInstance().doctorsLogicC.deleteDoctor(this.doctorsData);		
			updateDoctorsListView();
			resetFields();
			this.doctors_button_delete_accept.setDisable(true);
			this.doctors_button_delete_accept.setVisible(false);
			this.doctors_button_delete_accept.setManaged(false);
			this.doctors_button_delete.setDisable(false);
			this.doctors_button_delete.setVisible(true);
			this.doctors_button_delete.setManaged(true);
		});
		
	}

	private void updateDoctorsListView() {
		
		ObservableList<ListDoctors> lefts = EKitaManager.getInstance().doctorsLogicC.generateDoctorsList();
		
		this.doctors_listview_overview.setItems(lefts);
		this.doctors_listview_overview.setCellFactory(new Callback<ListView<ListDoctors>, ListCell<ListDoctors>>(){
			 
            @Override
            public ListCell<ListDoctors> call(ListView<ListDoctors> p) {
                 
                ListCell<ListDoctors> cell = new ListCell<ListDoctors>(){
 
                    @Override
                    protected void updateItem(ListDoctors t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getDisplayName());
                        }
                    }
 
                };
                 
                return cell;
            }
        });
	}
	
	private void enableAllFields() {
		
		this.doctors_choicebox_anrede.setDisable(false);
		this.doctors_choicebox_titel.setDisable(false);
		this.doctors_textfield_nachname.setDisable(false);
		this.doctors_textfield_vorname.setDisable(false);
		this.doctors_textfield_adresse.setDisable(false);
		this.doctors_textfield_plz.setDisable(false);
		this.doctors_textfield_ort.setDisable(false);
		this.doctors_textfield_praxis.setDisable(false);
		this.doctors_textfield_telefon.setDisable(false);
		this.doctors_textfield_fax.setDisable(false);
		this.doctors_textfield_email.setDisable(false);
		this.doctors_button_save.setDisable(false);
		this.doctors_button_delete.setDisable(false);
		
	}
	
	private void resetFields() {
		
		this.doctors_choicebox_anrede.setValue("");
		this.doctors_choicebox_titel.setValue("");
		this.doctors_textfield_nachname.setText("");
		this.doctors_textfield_vorname.setText("");
		this.doctors_textfield_adresse.setText("");
		this.doctors_textfield_plz.setText("");
		this.doctors_textfield_ort.setText("");
		this.doctors_textfield_praxis.setText("");
		this.doctors_textfield_telefon.setText("");
		this.doctors_textfield_fax.setText("");
		this.doctors_textfield_email.setText("");
		this.doctors_button_delete_accept.setDisable(true);
		this.doctors_button_delete_accept.setVisible(false);
		this.doctors_button_delete_accept.setManaged(false);
		this.doctors_button_delete.setDisable(false);
		this.doctors_button_delete.setVisible(true);
		this.doctors_button_delete.setManaged(true);
		
	}
	
	private void fillFields(int id) {
		
		if(id == -1)
			return;
		
		this.doctorsData = EKitaManager.getInstance().doctorsLogicC.openDoctor(id);
		
		if(id == 0) {
			resetFields();
			return;
		}		
		
		if(doctorsData == null)
			return;
		
		this.doctors_choicebox_anrede.setValue(this.doctorsData.getAnrede());
		this.doctors_choicebox_titel.setValue(this.doctorsData.getTitel());
		this.doctors_textfield_nachname.setText(this.doctorsData.getNachname());
		this.doctors_textfield_vorname.setText(this.doctorsData.getVorname());
		this.doctors_textfield_adresse.setText(this.doctorsData.getAdresse());
		this.doctors_textfield_plz.setText(this.doctorsData.getPlz());
		this.doctors_textfield_ort.setText(this.doctorsData.getOrt());
		this.doctors_textfield_praxis.setText(this.doctorsData.getPraxis());
		this.doctors_textfield_telefon.setText(this.doctorsData.getTelefon());
		this.doctors_textfield_fax.setText(this.doctorsData.getFax());
		this.doctors_textfield_email.setText(this.doctorsData.getEmail());
		
	}
	
	private void saveFields() {
		
		this.doctorsData.setAnrede(this.doctors_choicebox_anrede.getValue());
		this.doctorsData.setTitel(this.doctors_choicebox_titel.getValue());
		this.doctorsData.setNachname(this.doctors_textfield_nachname.getText());
		this.doctorsData.setVorname(this.doctors_textfield_vorname.getText());
		this.doctorsData.setAdresse(this.doctors_textfield_adresse.getText());
		this.doctorsData.setPlz(this.doctors_textfield_plz.getText());
		this.doctorsData.setOrt(this.doctors_textfield_ort.getText());
		this.doctorsData.setPraxis(this.doctors_textfield_praxis.getText());
		this.doctorsData.setTelefon(this.doctors_textfield_telefon.getText());
		this.doctorsData.setFax(this.doctors_textfield_fax.getText());
		this.doctorsData.setEmail(this.doctors_textfield_email.getText());
		
		EKitaManager.getInstance().doctorsLogicC.saveDoctor(this.doctorsData);
		
	}

}
