package application.controller.root;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

import application.EKitaManager;
import customclasses.ListBerechtigte;
import customclasses.ListDoctors;
import customclasses.ListGender;
import customclasses.ListGroup;
import customclasses.ListPermissions;
import customclasses.ListPerson;
import customclasses.ListPersonensorgeberechtigte;
import customclasses.ListPsbConverter;
import customclasses.Permission;
import customclasses.PersonBerechtigte;
import customclasses.PersonKid;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import util.stringcheck.StringCheck;

public class kidsController {

	@FXML
	private Button kids_button_delete;
	@FXML
	private Button kids_button_delete_accept;
	@FXML
	private Button kids_button_save;
	
	@FXML
	public Label kids_label_hilfe;
	
	@FXML
	private TabPane kids_tabpane_main;
	
	//Listenansicht aller Kinder
	@FXML
	private ListView<ListPerson> kids_listview_overview;
	
	@FXML
	private RadioButton kids_radiobutton_active;
	@FXML
	private RadioButton kids_radiobutton_all;
	@FXML
	private RadioButton kids_radiobutton_inactive;
	
	//Listenansicht aller Abholberechtigten
	@FXML
	private ListView<ListBerechtigte> kids_listview_berechtigte;
	//Allgemeine Felder der Profilansicht
	@FXML
	private TextField kids_textfield_nachname;
	@FXML
	private TextField kids_textfield_vorname;
	@FXML
	private TextField kids_textfield_strasse;
	@FXML
	private TextField kids_textfield_hausnummer;
	@FXML
	private TextField kids_textfield_plz;
	@FXML
	private TextField kids_textfield_ort;
	@FXML
	private DatePicker kids_datepicker_geburtsdatum;
	@FXML
	private ChoiceBox<ListGender> kids_choicebox_geschlecht;
	@FXML
	private ChoiceBox<ListGroup> kids_choicebox_gruppe;
	@FXML
	private CheckBox kids_checkbox_aktiv;
	//Vertragsdaten
	@FXML
	private DatePicker kids_datepicker_vertragsbeginn;
	@FXML
	private DatePicker kids_datepicker_vertragsende;
	@FXML
	private Slider kids_slider_betreuungszeit;
	@FXML
	private Button kids_button_betreuungszeit;
	@FXML
	private TextField kids_textfield_nationalitaet;
	@FXML
	private TextField kids_textfield_muttersprache;
	@FXML
	private RadioButton kids_radiobutton_krippe;
	@FXML
	private RadioButton kids_radiobutton_kiga;
	@FXML
	private RadioButton kids_radiobutton_hort;
	
	
	
	//Container der Kinderdaten
	private PersonKid kidData = null;
	//Container der Berechtigtendaten
	private PersonBerechtigte berechtigtenData = null;
	
	private boolean firstListViewClick = true;
	
	public ObservableList<ListPersonensorgeberechtigte> psbList = FXCollections.observableArrayList();
	

	@FXML
	private Button kids_button_delete_berechtigte_accept;
	@FXML
	private Button kids_button_delete_berechtigte;
	@FXML
	private Button kids_button_save_berechtigte;
	
	@FXML
	private TextField kids_textfield_nachname_berechtigte;
	@FXML
	private TextField kids_textfield_vorname_berechtigte;
	@FXML
	private DatePicker kids_datepicker_geburtsdatum_berechtigte;
	@FXML
	private CheckBox kids_checkbox_sorgeberechtigt;
	@FXML
	private CheckBox kids_checkbox_kontaktperson;
	@FXML
	private TextField kids_textfield_strasse_berechtigte;
	@FXML
	private TextField kids_textfield_hausnummer_berechtigte;
	@FXML
	private TextField kids_textfield_plz_berechtigte;
	@FXML
	private TextField kids_textfield_ort_berechtigte;
	@FXML
	private TextField kids_textfield_privat_berechtigte;
	@FXML
	private TextField kids_textfield_arbeit_berechtigte;
	@FXML
	private TextField kids_textfield_mobil_berechtigte;
	@FXML
	private TextField kids_textfield_mail_berechtigte;	

	@FXML
	private TextField kids_textfield_krankenkasse;
	@FXML
	private ChoiceBox<ListPersonensorgeberechtigte> kids_choicebox_versichert;
	@FXML
	private TextArea kids_textarea_krankheiten;
	@FXML
	private TextArea kids_textarea_allergien;
	@FXML
	private TextArea kids_textarea_besonderheiten;
	@FXML 
	private ChoiceBox<ListDoctors> kids_choicebox_doctor;
	
	@FXML
	private CheckBox kids_checkbox_permission_1;
	@FXML
	private CheckBox kids_checkbox_permission_2;
	@FXML
	private CheckBox kids_checkbox_permission_3;
	@FXML
	private CheckBox kids_checkbox_permission_4;
	@FXML
	private CheckBox kids_checkbox_permission_5;
	@FXML
	private CheckBox kids_checkbox_permission_6;
	@FXML
	private CheckBox kids_checkbox_permission_7;
	@FXML
	private CheckBox kids_checkbox_permission_8;
	@FXML
	private CheckBox kids_checkbox_permission_9;
	@FXML
	private CheckBox kids_checkbox_permission_10;
	@FXML
	private CheckBox kids_checkbox_permission_11;
	@FXML
	private CheckBox kids_checkbox_permission_12;
	@FXML
	private CheckBox kids_checkbox_permission_13;
	@FXML
	private CheckBox kids_checkbox_permission_14;
	@FXML
	private CheckBox kids_checkbox_permission_15;
	@FXML
	private CheckBox kids_checkbox_permission_16;
	@FXML
	private CheckBox kids_checkbox_permission_17;
	@FXML
	private CheckBox kids_checkbox_permission_18;
	@FXML
	private CheckBox kids_checkbox_permission_19;
	@FXML
	private CheckBox kids_checkbox_permission_20;
	
	EKitaManager app;
	
	Thread thread = null;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public kidsController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() throws InterruptedException {
		//ObservableList<ListPerson> lefts = FXCollections.observableArrayList(new ListPerson(0, "Jan-Eric Dreßler"), new ListPerson(1, "Katja Dreßler"), new ListPerson(2, "Florian Dreßler"));
		
		this.app = EKitaManager.getInstance();
		
		app.kidsController = this;
		
		app.permissions = this.app.rootLogicC.getPermissionsList();		

		app.doctors = this.app.rootLogicC.getDoctorsList();
		
		/*thread = new Thread(() -> {
			if(thread != null) {
				updateKidsListView();
			}
		});
		thread.start();*/
		updateKidsListView();
		
		this.kids_listview_overview.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	            if(firstListViewClick) {
	            	enableAllFields();
	            }
	            SingleSelectionModel<Tab> selectionModel = kids_tabpane_main.getSelectionModel();
	            selectionModel.select(0);
	            resetFields();
	            resetFieldsBerechtigte();
	            disableBerechtigtenFields();
	            if(kids_listview_overview.getSelectionModel().getSelectedItem() != null)
	            		fillFields(kids_listview_overview.getSelectionModel().getSelectedItem().getId());
	        }
	    });
		
		this.kids_listview_berechtigte.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	        	if(kids_listview_berechtigte.getSelectionModel().getSelectedItem() == null)
	        		return;
	        	enableBerechtigtenFields();
	        	fillFieldsBerechtigte(kids_listview_berechtigte.getSelectionModel().getSelectedItem().getId());
	        }
	    });
		
		this.kids_radiobutton_active.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	        	updateKidsListView();
	        }
	    });
		
		this.kids_radiobutton_all.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	        	updateKidsListView();
	        }
	    });
		
		this.kids_radiobutton_inactive.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	        	updateKidsListView();
	        }
	    });
		
		this.kids_button_delete.setOnAction((event) -> {
			this.kids_button_delete_accept.setDisable(false);
			this.kids_button_delete_accept.setVisible(true);
			this.kids_button_delete_accept.setManaged(true);
			this.kids_button_delete.setDisable(true);
			this.kids_button_delete.setVisible(false);
			this.kids_button_delete.setManaged(false);
		});
		

		this.kids_button_delete_accept.setManaged(false);
		this.kids_button_delete_accept.setOnAction((event) -> {
			app.kidsLogicC.deletePerson(this.kidData);		
			updateKidsListView();
			resetFields();
			this.kids_button_delete_accept.setDisable(true);
			this.kids_button_delete_accept.setVisible(false);
			this.kids_button_delete_accept.setManaged(false);
			this.kids_button_delete.setDisable(false);
			this.kids_button_delete.setVisible(true);
			this.kids_button_delete.setManaged(true);
		});
		
		this.kids_button_save.setOnAction((event) -> {
			saveFields();
		});
		
		this.kids_button_delete_berechtigte.setOnAction((event) -> {
			this.kids_button_delete_berechtigte_accept.setDisable(false);
			this.kids_button_delete_berechtigte_accept.setVisible(true);
			this.kids_button_delete_berechtigte_accept.setManaged(true);
			this.kids_button_delete_berechtigte.setDisable(true);
			this.kids_button_delete_berechtigte.setVisible(false);
			this.kids_button_delete_berechtigte.setManaged(false);
		});
		
		this.kids_button_delete_berechtigte_accept.setManaged(false);
		this.kids_button_delete_berechtigte_accept.setOnAction((event) -> {
			app.kidsLogicC.deletePerson(this.berechtigtenData);	
			updateBerechtigtenListView(this.kidData.getPID());
			resetFieldsBerechtigte();
			this.kids_button_delete_berechtigte_accept.setDisable(true);
			this.kids_button_delete_berechtigte_accept.setVisible(false);
			this.kids_button_delete_berechtigte_accept.setManaged(false);
			this.kids_button_delete_berechtigte.setDisable(false);
			this.kids_button_delete_berechtigte.setVisible(true);
			this.kids_button_delete_berechtigte.setManaged(true);
		});
		
		this.kids_button_save_berechtigte.setOnAction((event) -> {
			saveFieldsBerechtigte();
		});
		
		this.kids_button_betreuungszeit.setOnAction((event) -> {
			preSetKidData();
			app.kidsLogicC.displayBetreuungszeitLayer(this.kidData);
		});
		
		this.kids_tabpane_main.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
		    	if(newValue.intValue() == 0) {
		    		
		    		kids_label_hilfe.setText("Vertragsbeginn, Vertragsende und Betreuungszeit sollten ausgefüllt werden. Nationalität und Muttersprache sind nicht essentiell.");
		    		
		    		kids_button_delete_berechtigte.setVisible(false);
		    		kids_button_delete_berechtigte_accept.setVisible(false);
	    			kids_button_save_berechtigte.setVisible(false);
		    	}
		    	if(newValue.intValue() == 1) {
		    		if(kidData != null) {
		    			updateBerechtigtenListView(kidData.getPID());
		    		}
		    			kids_button_save_berechtigte.setVisible(true);
		    			kids_button_save_berechtigte.setManaged(true);
		    			kids_button_save_berechtigte.setDisable(false);
		    			kids_button_delete_berechtigte.setVisible(true);
		    			kids_button_delete_berechtigte.setManaged(true);
		    			kids_button_delete_berechtigte.setDisable(false);
			    		kids_button_delete_berechtigte_accept.setVisible(false);
			    		kids_button_delete_berechtigte_accept.setManaged(false);
			    		kids_button_delete_berechtigte_accept.setDisable(true);
		    		//kids_label_hilfe.setText("Vertragsbeginn, Vertragsende und Betreuungszeit sollten ausgefüllt werden. Nationalität und Muttersprache sind nicht essentiell.");
		    	}
		    	if(newValue.intValue() == 2) {
		    		//kids_label_hilfe.setText("Vertragsbeginn, Vertragsende und Betreuungszeit sollten ausgefüllt werden. Nationalität und Muttersprache sind nicht essentiell.");
		    		
		    		kids_choicebox_doctor.setConverter(app.doctorsConverter);
		    		kids_choicebox_doctor.setItems(app.doctors);
		    		kids_choicebox_doctor.setValue(app.doctors.get(0));
		    		
		    		if(kidData != null) {
		    			psbList = app.kidsLogicC.generatePersonensorgeberechtigteList(kidData.getPID());
		    			kids_choicebox_versichert.setConverter(new ListPsbConverter(psbList));
		    			kids_choicebox_versichert.setItems(psbList);
		    			for(int i = 0; i < psbList.size(); i++) {
		    				if(((int) psbList.get(i).getId()) == kidData.getVersicherter()) {
		    					kids_choicebox_versichert.setValue(psbList.get(i));
		    					break;
		    				} else {
		    					kids_choicebox_versichert.setValue(psbList.get(0));
		    				}
		    			}
		    			for(int i = 0; i < app.doctors.size(); i++) {
		    				if(app.doctors.get(i).getDID() == kidData.getKinderarzt()) {
		    					kids_choicebox_doctor.setValue(app.doctors.get(i));
		    					break;
		    				} else {
		    					kids_choicebox_doctor.setValue(app.doctors.get(0));
		    				}
		    			}
		    		}
		    		
		    		kids_button_delete_berechtigte.setVisible(false);
		    		kids_button_delete_berechtigte_accept.setVisible(false);
		    		kids_button_save_berechtigte.setVisible(false);
		    	}
		    	if(newValue.intValue() == 3) {
		    		//kids_label_hilfe.setText("Vertragsbeginn, Vertragsende und Betreuungszeit sollten ausgefüllt werden. Nationalität und Muttersprache sind nicht essentiell.");
		    		kids_button_delete_berechtigte.setVisible(false);
		    		kids_button_delete_berechtigte_accept.setVisible(false);
	    			kids_button_save_berechtigte.setVisible(false);
	    			if(app.permissions.size() == 0)
	    				app.permissions = app.rootLogicC.getPermissionsList();
		    		List<ListPermissions> list = app.permissions;
		    		for(int i = 0; i < list.size(); i++) {
		    			setupPermissionFields(list, i);		    			
		    		}
		    	}
		    }
		}); 
		
		this.kids_choicebox_gruppe.setConverter(app.groupConverter);
		this.kids_choicebox_gruppe.setItems(app.groups);
		this.kids_choicebox_gruppe.setValue(app.groups.get(0));

		this.kids_choicebox_geschlecht.setConverter(app.genderConverter);
		this.kids_choicebox_geschlecht.setItems(app.gender);
		this.kids_choicebox_geschlecht.setValue(app.gender.get(0));
		
		fillFields(-1);
		
	}

	private void updateBerechtigtenListView(int id) {
		ObservableList<ListBerechtigte> lefts = this.app.kidsLogicC.generateBerechtigtenList(id);
		
		if(lefts == null)
			return;
		
		this.kids_listview_berechtigte.setItems(lefts);
		this.kids_listview_berechtigte.setCellFactory(new Callback<ListView<ListBerechtigte>, ListCell<ListBerechtigte>>(){
			 
            @Override
            public ListCell<ListBerechtigte> call(ListView<ListBerechtigte> p) {
                 
                ListCell<ListBerechtigte> cell = new ListCell<ListBerechtigte>(){
 
                    @Override
                    protected void updateItem(ListBerechtigte t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getName());
                        }
                    }
 
                };
                 
                return cell;
            }
        });
	}

	private void updateKidsListView() {
		int kinderstatus = 1;
		
		if (this.kids_radiobutton_active.isSelected()) {
			kinderstatus = 1;
		} else if (this.kids_radiobutton_inactive.isSelected()) {
			kinderstatus = 0;
		} else {
			kinderstatus = 2;
		}
		
		ObservableList<ListPerson> lefts = this.app.kidsLogicC.generateKidsList(kinderstatus);
		
		this.kids_listview_overview.setItems(lefts);
		this.kids_listview_overview.setCellFactory(new Callback<ListView<ListPerson>, ListCell<ListPerson>>(){
			 
            @Override
            public ListCell<ListPerson> call(ListView<ListPerson> p) {
                 
                ListCell<ListPerson> cell = new ListCell<ListPerson>(){
 
                    @Override
                    protected void updateItem(ListPerson t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getName());
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
		
		this.kidData = this.app.kidsLogicC.openPerson(id);
		
		if(id == 0) {
			resetFields();
			return;
		}		
		
		if(kidData == null)
			return;
		
		this.kids_textfield_nachname.setText(this.kidData.getNachname());
		this.kids_textfield_vorname.setText(this.kidData.getVorname());
		this.kids_textfield_strasse.setText(this.kidData.getStrasse());
		this.kids_textfield_hausnummer.setText(this.kidData.getHausnummer());
		this.kids_textfield_plz.setText(this.kidData.getPlz());
		this.kids_textfield_ort.setText(this.kidData.getOrt());
		
		if(this.kidData.getGeburtsdatum(false).equalsIgnoreCase("1-1-1")) {
			this.kids_datepicker_geburtsdatum.setValue(null);
		} else {
			this.kids_datepicker_geburtsdatum.setValue(this.kidData.getGeburtsdatum());
		}		
		this.kids_choicebox_geschlecht.setValue(this.kidData.getGeschlecht());
		
		if(this.kidData.getGruppe() != null)
			this.kids_choicebox_gruppe.setValue(this.kidData.getGruppe());
		this.kids_checkbox_aktiv.setSelected(this.kidData.getAktiv());
		
		if(this.kidData.getVertragsbeginn(false).equalsIgnoreCase("1-1-1")) {
			this.kids_datepicker_vertragsbeginn.setValue(null);
		} else {
			this.kids_datepicker_vertragsbeginn.setValue(this.kidData.getVertragsbeginn());
		}
		
		if(this.kidData.getVertragsende(false).equalsIgnoreCase("1-1-1")) {
			this.kids_datepicker_vertragsende.setValue(null);
		} else {
			this.kids_datepicker_vertragsende.setValue(this.kidData.getVertragsende());
		}
		
		this.kids_slider_betreuungszeit.setValue(this.kidData.getBetreuungszeit());
		
		this.kids_textfield_nationalitaet.setText(this.kidData.getNationalitaet());
		this.kids_textfield_muttersprache.setText(this.kidData.getMuttersprache());
		
		switch(this.kidData.getBetreuungsart()) {
	        case 0: this.kids_radiobutton_krippe.setSelected(true);
	                 break;
	        case 1: this.kids_radiobutton_kiga.setSelected(true);
	                 break;
	        case 2: this.kids_radiobutton_hort.setSelected(true);
	                 break;
	        default: this.kids_radiobutton_krippe.setSelected(true);
	                 break;
		}
		
		this.kids_textfield_krankenkasse.setText(this.kidData.getKrankenkasse());
		
		this.kids_textarea_krankheiten.setText(this.kidData.getKrankheiten());
		this.kids_textarea_allergien.setText(this.kidData.getAllergien());
		this.kids_textarea_besonderheiten.setText(this.kidData.getBesonderheiten());
		
		fillPermissionFields();
		
	}

	private void fillFieldsBerechtigte(int id) {
		if(id == -1)
			return;
		
		this.berechtigtenData = this.app.kidsLogicC.openPersonBerechtigte(id);
		
		if(id == 0) {
			resetFieldsBerechtigte();
			return;
		}		
		
		if(berechtigtenData == null)
			return;
		
		this.kids_textfield_nachname_berechtigte.setText(this.berechtigtenData.getNachname());
		this.kids_textfield_vorname_berechtigte.setText(this.berechtigtenData.getVorname());
		
		if(this.berechtigtenData.getGeburtsdatum(false).equalsIgnoreCase("1-1-1")) {
			this.kids_datepicker_geburtsdatum_berechtigte.setValue(null);
		} else {
			this.kids_datepicker_geburtsdatum_berechtigte.setValue(this.berechtigtenData.getGeburtsdatum());
		}
		
		this.kids_checkbox_sorgeberechtigt.setSelected(this.berechtigtenData.getSorgeberechtigt());
		this.kids_checkbox_kontaktperson.setSelected(this.berechtigtenData.getKontaktperson());
		
		this.kids_textfield_strasse_berechtigte.setText(this.berechtigtenData.getStrasse());
		this.kids_textfield_hausnummer_berechtigte.setText(this.berechtigtenData.getHausnummer());
		this.kids_textfield_plz_berechtigte.setText(this.berechtigtenData.getPlz());
		this.kids_textfield_ort_berechtigte.setText(this.berechtigtenData.getOrt());
		
		this.kids_textfield_privat_berechtigte.setText(this.berechtigtenData.getPrivat());
		this.kids_textfield_arbeit_berechtigte.setText(this.berechtigtenData.getArbeit());
		this.kids_textfield_mobil_berechtigte.setText(this.berechtigtenData.getMobil());
		this.kids_textfield_mail_berechtigte.setText(this.berechtigtenData.getMail());
	}
	
	private void resetFields() {	
		this.kids_button_delete_accept.setDisable(true);
		this.kids_button_delete_accept.setVisible(false);
		this.kids_button_delete_accept.setManaged(false);
		this.kids_button_delete.setDisable(false);
		this.kids_button_delete.setVisible(true);
		this.kids_button_delete.setManaged(true);		
		this.kids_textfield_nachname.setText("");
		this.kids_textfield_vorname.setText("");
		this.kids_textfield_strasse.setText("");
		this.kids_textfield_hausnummer.setText("");
		this.kids_textfield_plz.setText("");
		this.kids_textfield_ort.setText("");
		this.kids_datepicker_geburtsdatum.setValue(null);
		this.kids_choicebox_geschlecht.setValue(this.app.gender.get(0));
		this.kids_choicebox_gruppe.setValue(this.app.groups.get(0));
		this.kids_choicebox_doctor.setValue(this.app.doctors.get(0));
		this.kids_checkbox_aktiv.setSelected(true);
		this.kids_datepicker_vertragsbeginn.setValue(null);
		this.kids_datepicker_vertragsende.setValue(null);
		this.kids_radiobutton_kiga.setSelected(true);
		this.kids_slider_betreuungszeit.setValue(9);
		this.kids_textfield_nationalitaet.setText("");
		this.kids_textfield_muttersprache.setText("");
		this.kids_checkbox_permission_1.setSelected(false);
		this.kids_checkbox_permission_2.setSelected(false);
		this.kids_checkbox_permission_3.setSelected(false);
		this.kids_checkbox_permission_4.setSelected(false);
		this.kids_checkbox_permission_5.setSelected(false);
		this.kids_checkbox_permission_6.setSelected(false);
		this.kids_checkbox_permission_7.setSelected(false);
		this.kids_checkbox_permission_8.setSelected(false);
		this.kids_checkbox_permission_9.setSelected(false);
		this.kids_checkbox_permission_10.setSelected(false);
		this.kids_checkbox_permission_11.setSelected(false);
		this.kids_checkbox_permission_12.setSelected(false);
		this.kids_checkbox_permission_13.setSelected(false);
		this.kids_checkbox_permission_14.setSelected(false);
		this.kids_checkbox_permission_15.setSelected(false);
		this.kids_checkbox_permission_16.setSelected(false);
		this.kids_checkbox_permission_17.setSelected(false);
		this.kids_checkbox_permission_18.setSelected(false);
		this.kids_checkbox_permission_19.setSelected(false);
		this.kids_checkbox_permission_20.setSelected(false);
	}
	
	private void resetFieldsBerechtigte() {	
		this.kids_button_delete_berechtigte_accept.setDisable(true);
		this.kids_button_delete_berechtigte_accept.setVisible(false);
		this.kids_button_delete_berechtigte_accept.setManaged(false);
		this.kids_button_delete_berechtigte.setDisable(true);
		this.kids_button_delete_berechtigte.setVisible(false);
		this.kids_button_delete_berechtigte.setManaged(false);			
		this.kids_textfield_nachname_berechtigte.setText("");
		this.kids_textfield_vorname_berechtigte.setText("");
		this.kids_datepicker_geburtsdatum_berechtigte.setValue(null);
		this.kids_checkbox_sorgeberechtigt.setSelected(false);
		this.kids_checkbox_kontaktperson.setSelected(false);
		this.kids_textfield_strasse_berechtigte.setText("");
		this.kids_textfield_hausnummer_berechtigte.setText("");
		this.kids_textfield_plz_berechtigte.setText("");
		this.kids_textfield_ort_berechtigte.setText("");
		this.kids_textfield_privat_berechtigte.setText("");
		this.kids_textfield_arbeit_berechtigte.setText("");
		this.kids_textfield_mobil_berechtigte.setText("");
		this.kids_textfield_mail_berechtigte.setText("");
	}
	
	private boolean checkFields() {
		
		String defaultValues = this.app.settings.getDefaults();
		String[] splitValues = defaultValues.split(",");
		
		if(this.kids_textfield_nationalitaet.getText() == null)
			this.kids_textfield_nationalitaet.setText("");
		if(this.kids_textfield_muttersprache.getText() == null)
			this.kids_textfield_muttersprache.setText("");
		if(this.kids_textfield_krankenkasse.getText() == null)
			this.kids_textfield_krankenkasse.setText("");
		if(this.kids_textarea_krankheiten.getText() == null)
			this.kids_textarea_krankheiten.setText("");
		if(this.kids_textarea_besonderheiten.getText() == null)
			this.kids_textarea_besonderheiten.setText("");
		if(this.kids_textarea_allergien.getText() == null)
			this.kids_textarea_allergien.setText("");
		
		if(StringCheck.containsIllegals(this.kids_textfield_nachname.getText())) {
			this.app.showTooltip(this.app.programStage, this.kids_textfield_nachname, this.app.resourceBundle.getString("key.error_wrongchars"), null);
			return false;
		}
		if(StringCheck.containsIllegals(this.kids_textfield_vorname.getText())) {
			this.app.showTooltip(this.app.programStage, this.kids_textfield_vorname, this.app.resourceBundle.getString("key.error_wrongchars"), null);
			return false;
		}
		if(StringCheck.containsIllegals(this.kids_textfield_strasse.getText())) {
			this.app.showTooltip(this.app.programStage, this.kids_textfield_strasse, this.app.resourceBundle.getString("key.kids_tooltip_fillin_street"), null);
			return false;
		}
		if(StringCheck.containsIllegals(this.kids_textfield_hausnummer.getText())) {
			this.app.showTooltip(this.app.programStage, this.kids_textfield_hausnummer, this.app.resourceBundle.getString("key.kids_tooltip_fillin_house"), null);
			return false;
		}
		if(StringCheck.containsIllegals(this.kids_textfield_plz.getText())) {
			this.app.showTooltip(this.app.programStage, this.kids_textfield_plz, this.app.resourceBundle.getString("key.kids_tooltip_fillin_postcode"), null);
			return false;
		}
		if(StringCheck.containsIllegals(this.kids_textfield_ort.getText())) {
			this.app.showTooltip(this.app.programStage, this.kids_textfield_ort, this.app.resourceBundle.getString("key.kids_tooltip_fillin_town"), null);
			return false;
		}
		
		if(this.kids_textfield_nachname.getText().equalsIgnoreCase("") && splitValues[0].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_textfield_nachname, this.app.resourceBundle.getString("key.kids_tooltip_fillin_lastname"), null);
			return false;
		}
		if(this.kids_textfield_vorname.getText().equalsIgnoreCase("") && splitValues[1].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_textfield_vorname, this.app.resourceBundle.getString("key.kids_tooltip_fillin_firstname"), null);
			return false;
		}
		if(this.kids_textfield_strasse.getText().equalsIgnoreCase("") && splitValues[3].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_textfield_strasse, this.app.resourceBundle.getString("key.kids_tooltip_fillin_street"), null);
			return false;
		}
		if(this.kids_textfield_hausnummer.getText().equalsIgnoreCase("") && splitValues[3].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_textfield_hausnummer, this.app.resourceBundle.getString("key.kids_tooltip_fillin_house"), null);
			return false;
		}
		if(this.kids_textfield_plz.getText().equalsIgnoreCase("") && splitValues[3].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_textfield_plz, this.app.resourceBundle.getString("key.kids_tooltip_fillin_postcode"), null);
			return false;
		}
		if(this.kids_textfield_ort.getText().equalsIgnoreCase("") && splitValues[3].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_textfield_ort, this.app.resourceBundle.getString("key.kids_tooltip_fillin_town"), null);
			return false;
		}
		if(this.kids_datepicker_geburtsdatum.getEditor().getText().equalsIgnoreCase("") && splitValues[2].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_datepicker_geburtsdatum, this.app.resourceBundle.getString("key.kids_tooltip_fillin_birthday"), null);
			return false;
		}
		if(this.kids_datepicker_vertragsbeginn.getEditor().getText().equalsIgnoreCase("") && splitValues[4].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_datepicker_vertragsbeginn, this.app.resourceBundle.getString("key.kids_tooltip_fillin_contractbegin"), null);
			return false;
		}
		if(this.kids_datepicker_vertragsende.getEditor().getText().equalsIgnoreCase("") && splitValues[5].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_datepicker_vertragsende, this.app.resourceBundle.getString("key.kids_tooltip_fillin_contractend"), null);
			return false;
		}
		if(this.kids_textfield_nationalitaet.getText().equalsIgnoreCase("") && splitValues[6].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_textfield_nationalitaet, this.app.resourceBundle.getString("key.kids_tooltip_fillin_nationality"), null);
			return false;
		}
		if(this.kids_textfield_muttersprache.getText().equalsIgnoreCase("") && splitValues[7].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_textfield_muttersprache, this.app.resourceBundle.getString("key.kids_tooltip_fillin_mothertongue"), null);
			return false;
		}
		if(this.kids_textfield_krankenkasse.getText().equalsIgnoreCase("") && splitValues[8].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_textfield_krankenkasse, this.app.resourceBundle.getString("key.kids_tooltip_fillin_krankenkasse"), null);
			return false;
		}
		if(this.kids_textarea_krankheiten.getText().equalsIgnoreCase("") && splitValues[9].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_textarea_krankheiten, this.app.resourceBundle.getString("key.kids_tooltip_fillin_krankheiten"), null);
			return false;
		}
		if(this.kids_textarea_allergien.getText().equalsIgnoreCase("") && splitValues[10].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_textarea_allergien, this.app.resourceBundle.getString("key.kids_tooltip_fillin_allergien"), null);
			return false;
		}
		if(this.kids_textarea_besonderheiten.getText().equalsIgnoreCase("") && splitValues[11].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_textarea_besonderheiten, this.app.resourceBundle.getString("key.kids_tooltip_fillin_besonderheiten"), null);
			return false;
		}
		
		if(!this.kids_textfield_nachname_berechtigte.isDisabled() && this.kids_textfield_nachname_berechtigte.getText().equalsIgnoreCase("") && splitValues[12].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_textfield_nachname_berechtigte, this.app.resourceBundle.getString("key.kids_tooltip_fillin_lastname"), null);
			return false;
		}
		if(!this.kids_textfield_vorname_berechtigte.isDisabled() && this.kids_textfield_vorname_berechtigte.getText().equalsIgnoreCase("") && splitValues[13].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_textfield_vorname_berechtigte, this.app.resourceBundle.getString("key.kids_tooltip_fillin_firstname"), null);
			return false;
		}
		if(!this.kids_textfield_strasse_berechtigte.isDisabled() && this.kids_textfield_strasse_berechtigte.getText().equalsIgnoreCase("") && splitValues[15].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_textfield_strasse_berechtigte, this.app.resourceBundle.getString("key.kids_tooltip_fillin_street"), null);
			return false;
		}
		if(!this.kids_textfield_hausnummer_berechtigte.isDisabled() && this.kids_textfield_hausnummer_berechtigte.getText().equalsIgnoreCase("") && splitValues[15].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_textfield_hausnummer_berechtigte, this.app.resourceBundle.getString("key.kids_tooltip_fillin_house"), null);
			return false;
		}
		if(!this.kids_textfield_plz_berechtigte.isDisabled() && this.kids_textfield_plz_berechtigte.getText().equalsIgnoreCase("") && splitValues[15].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_textfield_plz_berechtigte, this.app.resourceBundle.getString("key.kids_tooltip_fillin_postcode"), null);
			return false;
		}
		if(!this.kids_textfield_ort_berechtigte.isDisabled() && this.kids_textfield_ort_berechtigte.getText().equalsIgnoreCase("") && splitValues[15].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_textfield_ort_berechtigte, this.app.resourceBundle.getString("key.kids_tooltip_fillin_town"), null);
			return false;
		}
		if(!this.kids_datepicker_geburtsdatum_berechtigte.isDisabled() && this.kids_datepicker_geburtsdatum_berechtigte.getEditor().getText().equalsIgnoreCase("") && splitValues[14].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_datepicker_geburtsdatum_berechtigte, this.app.resourceBundle.getString("key.kids_tooltip_fillin_birthday"), null);
			return false;
		}
		if(!this.kids_textfield_privat_berechtigte.isDisabled() && this.kids_textfield_privat_berechtigte.getText().equalsIgnoreCase("") && splitValues[16].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_textfield_privat_berechtigte, this.app.resourceBundle.getString("key.kids_tooltip_fillin_privatephone"), null);
			return false;
		}
		if(!this.kids_textfield_mobil_berechtigte.isDisabled() && this.kids_textfield_mobil_berechtigte.getText().equalsIgnoreCase("") && splitValues[17].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_textfield_mobil_berechtigte, this.app.resourceBundle.getString("key.kids_tooltip_fillin_mobilephone"), null);
			return false;
		}
		if(!this.kids_textfield_arbeit_berechtigte.isDisabled() && this.kids_textfield_arbeit_berechtigte.getText().equalsIgnoreCase("") && splitValues[18].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_textfield_arbeit_berechtigte, this.app.resourceBundle.getString("key.kids_tooltip_fillin_workphone"), null);
			return false;
		}
		if(!this.kids_textfield_mail_berechtigte.isDisabled() && this.kids_textfield_mail_berechtigte.getText().equalsIgnoreCase("") && splitValues[19].contains("true")) {
			this.app.showTooltip(this.app.programStage, this.kids_textfield_mail_berechtigte, this.app.resourceBundle.getString("key.kids_tooltip_fillin_mail"), null);
			return false;
		}
		
		return true;
	}
	
	private void preSetKidData() {
		
		this.kidData.setNachname(this.kids_textfield_nachname.getText());
		this.kidData.setVorname(this.kids_textfield_vorname.getText());
		this.kidData.setStrasse(this.kids_textfield_strasse.getText());
		this.kidData.setHausnummer(this.kids_textfield_hausnummer.getText());
		this.kidData.setPlz(this.kids_textfield_plz.getText());
		this.kidData.setOrt(this.kids_textfield_ort.getText());
		
		if(this.kids_datepicker_geburtsdatum.getValue() != null) {
			this.kidData.setGeburtsdatum(this.kids_datepicker_geburtsdatum.getValue());
		} else if(!this.kids_datepicker_geburtsdatum.getEditor().getText().equalsIgnoreCase("")) {
			String tmpString = this.kids_datepicker_geburtsdatum.getEditor().getText();
			String[] tmpDate = tmpString.split("\\.");
			if(tmpDate.length == 3) {
				try {
					LocalDate tmpLocalDate = LocalDate.of(Integer.parseInt(tmpDate[2]), Integer.parseInt(tmpDate[1]), Integer.parseInt(tmpDate[0]));
					this.kids_datepicker_geburtsdatum.setValue(tmpLocalDate);
					this.kidData.setGeburtsdatum(this.kids_datepicker_geburtsdatum.getValue());
				} catch (DateTimeException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					this.app.log.LogError("preSetKidData error parsing Date from Integer", e);
					this.app.displayErrorLayer().setErrorDetails("6 - 001", "Es ist ein Fehler bei der Berechnung des Geburtsdatums aufgetreten.");
				}				
			}
		}
		if(this.kids_choicebox_geschlecht.getValue() != null)
			this.kidData.setGeschlecht(this.kids_choicebox_geschlecht.getValue());
		
		if(this.kids_choicebox_gruppe.getValue() != null)
			this.kidData.setGruppe(this.kids_choicebox_gruppe.getValue());
		this.kidData.setAktiv(this.kids_checkbox_aktiv.isSelected());
		
		if(this.kids_datepicker_vertragsbeginn.getValue() != null)
			this.kidData.setVertragsbeginn(this.kids_datepicker_vertragsbeginn.getValue());
		
		if(this.kids_datepicker_vertragsende.getValue() != null)
			this.kidData.setVertragsende(this.kids_datepicker_vertragsende.getValue());
		
		if(this.kids_radiobutton_krippe.isSelected()) {
			this.kidData.setBetreuungsart(0);
		} else if(this.kids_radiobutton_kiga.isSelected()) {
			this.kidData.setBetreuungsart(1);
		} else {
			this.kidData.setBetreuungsart(2);
		}
		
		//this.kidData.setBetreuungszeit(this.kids_slider_betreuungszeit.getValue());
		this.kidData.setNationalitaet(this.kids_textfield_nationalitaet.getText());
		this.kidData.setMuttersprache(this.kids_textfield_muttersprache.getText());
		
		this.kidData.setKrankenkasse(this.kids_textfield_krankenkasse.getText());
		if(this.kids_choicebox_versichert.getValue() != null)
			this.kidData.setVersicherter(this.kids_choicebox_versichert.getValue().getId());
		this.kidData.setKrankheiten(this.kids_textarea_krankheiten.getText());
		this.kidData.setAllergien(this.kids_textarea_allergien.getText());
		this.kidData.setBesonderheiten(this.kids_textarea_besonderheiten.getText());
		this.kidData.setKinderarzt(this.kids_choicebox_doctor.getValue().getDID());
		
		saveFieldsPermissions();
		
	}
	
	private void saveFields() {
		
		if(!checkFields())
			return;
		
		this.kidData.setNachname(this.kids_textfield_nachname.getText());
		this.kidData.setVorname(this.kids_textfield_vorname.getText());
		this.kidData.setStrasse(this.kids_textfield_strasse.getText());
		this.kidData.setHausnummer(this.kids_textfield_hausnummer.getText());
		this.kidData.setPlz(this.kids_textfield_plz.getText());
		this.kidData.setOrt(this.kids_textfield_ort.getText());
		
		if(this.kids_datepicker_geburtsdatum.getValue() != null) {
			this.kidData.setGeburtsdatum(this.kids_datepicker_geburtsdatum.getValue());
		} else if(!this.kids_datepicker_geburtsdatum.getEditor().getText().equalsIgnoreCase("")) {
			String tmpString = this.kids_datepicker_geburtsdatum.getEditor().getText();
			String[] tmpDate = tmpString.split("\\.");
			if(tmpDate.length == 3) {
				try {
					LocalDate tmpLocalDate = LocalDate.of(Integer.parseInt(tmpDate[2]), Integer.parseInt(tmpDate[1]), Integer.parseInt(tmpDate[0]));
					this.kids_datepicker_geburtsdatum.setValue(tmpLocalDate);
					this.kidData.setGeburtsdatum(this.kids_datepicker_geburtsdatum.getValue());
				} catch (DateTimeException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					this.app.log.LogError("saveFields error parsing Date from Integer", e);
					this.app.displayErrorLayer().setErrorDetails("6 - 002", "Es ist ein Fehler bei der Berechnung des Geburtsdatums aufgetreten.");
				}				
			}
		}
		if(this.kids_choicebox_geschlecht.getValue() != null)
			this.kidData.setGeschlecht(this.kids_choicebox_geschlecht.getValue());
		
		if(this.kids_choicebox_gruppe.getValue() != null)
			this.kidData.setGruppe(this.kids_choicebox_gruppe.getValue());
		this.kidData.setAktiv(this.kids_checkbox_aktiv.isSelected());
		
		if(this.kids_datepicker_vertragsbeginn.getValue() != null)
			this.kidData.setVertragsbeginn(this.kids_datepicker_vertragsbeginn.getValue());
		
		if(this.kids_datepicker_vertragsende.getValue() != null)
			this.kidData.setVertragsende(this.kids_datepicker_vertragsende.getValue());
		
		if(this.kids_radiobutton_krippe.isSelected()) {
			this.kidData.setBetreuungsart(0);
		} else if(this.kids_radiobutton_kiga.isSelected()) {
			this.kidData.setBetreuungsart(1);
		} else {
			this.kidData.setBetreuungsart(2);
		}
		
		//this.kidData.setBetreuungszeit(this.kids_slider_betreuungszeit.getValue());
		this.kidData.setNationalitaet(this.kids_textfield_nationalitaet.getText());
		this.kidData.setMuttersprache(this.kids_textfield_muttersprache.getText());
		
		this.kidData.setKrankenkasse(this.kids_textfield_krankenkasse.getText());
		if(this.kids_choicebox_versichert.getValue() != null)
			this.kidData.setVersicherter(this.kids_choicebox_versichert.getValue().getId());
		this.kidData.setKrankheiten(this.kids_textarea_krankheiten.getText());
		this.kidData.setAllergien(this.kids_textarea_allergien.getText());
		this.kidData.setBesonderheiten(this.kids_textarea_besonderheiten.getText());
		this.kidData.setKinderarzt(this.kids_choicebox_doctor.getValue().getDID());
		
		saveFieldsPermissions();
		
		this.app.kidsLogicC.savePerson(this.kidData);		
		updateKidsListView();
	}
	
	private void saveFieldsBerechtigte() {
		this.berechtigtenData.setNachname(this.kids_textfield_nachname_berechtigte.getText());
		this.berechtigtenData.setVorname(this.kids_textfield_vorname_berechtigte.getText());
		this.berechtigtenData.setStrasse(this.kids_textfield_strasse_berechtigte.getText());
		this.berechtigtenData.setHausnummer(this.kids_textfield_hausnummer_berechtigte.getText());
		this.berechtigtenData.setPlz(this.kids_textfield_plz_berechtigte.getText());
		this.berechtigtenData.setOrt(this.kids_textfield_ort_berechtigte.getText());
		
		if(this.kids_datepicker_geburtsdatum_berechtigte.getValue() != null)
			this.berechtigtenData.setGeburtsdatum(this.kids_datepicker_geburtsdatum_berechtigte.getValue());
		
		this.berechtigtenData.setSorgeberechtigt(this.kids_checkbox_sorgeberechtigt.isSelected());
		this.berechtigtenData.setKontaktperson(this.kids_checkbox_kontaktperson.isSelected());
		
		this.berechtigtenData.setPrivat(this.kids_textfield_privat_berechtigte.getText());
		this.berechtigtenData.setArbeit(this.kids_textfield_arbeit_berechtigte.getText());
		this.berechtigtenData.setMobil(this.kids_textfield_mobil_berechtigte.getText());
		this.berechtigtenData.setMail(this.kids_textfield_mail_berechtigte.getText());
		this.app.kidsLogicC.savePersonBerechtigte(this.berechtigtenData);		
		this.app.kidsLogicC.savePBIndexTable(this.kidData.getPID(), this.berechtigtenData.getBID());
		updateBerechtigtenListView(this.kidData.getPID());
	}
	
	private void saveFieldsPermissions() {
		List<Permission> list = this.kidData.getVollmachten();
		if(list.size() == 0)
			list = initiatePermissionList(list);
		list.get(0).setGiven(this.kids_checkbox_permission_1.isSelected());
		list.get(1).setGiven(this.kids_checkbox_permission_2.isSelected());
		list.get(2).setGiven(this.kids_checkbox_permission_3.isSelected());
		list.get(3).setGiven(this.kids_checkbox_permission_4.isSelected());
		list.get(4).setGiven(this.kids_checkbox_permission_5.isSelected());
		list.get(5).setGiven(this.kids_checkbox_permission_6.isSelected());
		list.get(6).setGiven(this.kids_checkbox_permission_7.isSelected());
		list.get(7).setGiven(this.kids_checkbox_permission_8.isSelected());
		list.get(8).setGiven(this.kids_checkbox_permission_9.isSelected());
		list.get(9).setGiven(this.kids_checkbox_permission_10.isSelected());
		list.get(10).setGiven(this.kids_checkbox_permission_11.isSelected());
		list.get(11).setGiven(this.kids_checkbox_permission_12.isSelected());
		list.get(12).setGiven(this.kids_checkbox_permission_13.isSelected());
		list.get(13).setGiven(this.kids_checkbox_permission_14.isSelected());
		list.get(14).setGiven(this.kids_checkbox_permission_15.isSelected());
		list.get(15).setGiven(this.kids_checkbox_permission_16.isSelected());
		list.get(16).setGiven(this.kids_checkbox_permission_17.isSelected());
		list.get(17).setGiven(this.kids_checkbox_permission_18.isSelected());
		list.get(18).setGiven(this.kids_checkbox_permission_19.isSelected());
		list.get(19).setGiven(this.kids_checkbox_permission_20.isSelected());
		this.kidData.setVollmachten(list);
	}
	
	private List<Permission> initiatePermissionList(List<Permission> list) {
		for(int i = 0; i < 20; i++) {
			list.add(new Permission());
		}
		return list;
	}
	
	private void enableAllFields() {
		this.kids_textfield_nachname.setDisable(false);
		this.kids_textfield_vorname.setDisable(false);
		this.kids_textfield_strasse.setDisable(false);
		this.kids_textfield_hausnummer.setDisable(false);
		this.kids_textfield_plz.setDisable(false);
		this.kids_textfield_ort.setDisable(false);
		this.kids_datepicker_geburtsdatum.setDisable(false);
		this.kids_choicebox_geschlecht.setDisable(false);
		this.kids_choicebox_gruppe.setDisable(false);
		this.kids_checkbox_aktiv.setDisable(false);
		this.kids_datepicker_vertragsbeginn.setDisable(false);
		this.kids_datepicker_vertragsende.setDisable(false);
		//this.kids_slider_betreuungszeit.setDisable(false);
		this.kids_button_betreuungszeit.setDisable(false);
		this.kids_textfield_nationalitaet.setDisable(false);
		this.kids_textfield_muttersprache.setDisable(false);
		this.kids_radiobutton_krippe.setDisable(false);
		this.kids_radiobutton_kiga.setDisable(false);
		this.kids_radiobutton_hort.setDisable(false);
		this.kids_button_delete.setDisable(false);
		this.kids_button_save.setDisable(false);
		this.kids_button_delete_berechtigte.setDisable(false);
		this.kids_button_save_berechtigte.setDisable(false);
		
		this.kids_listview_berechtigte.setDisable(false);
		this.kids_textfield_nachname_berechtigte.setDisable(false);
		this.kids_textfield_vorname_berechtigte.setDisable(false);
		this.kids_datepicker_geburtsdatum_berechtigte.setDisable(false);
		this.kids_checkbox_kontaktperson.setDisable(false);
		this.kids_checkbox_sorgeberechtigt.setDisable(false);
		this.kids_textfield_strasse_berechtigte.setDisable(false);
		this.kids_textfield_hausnummer_berechtigte.setDisable(false);
		this.kids_textfield_plz_berechtigte.setDisable(false);
		this.kids_textfield_ort_berechtigte.setDisable(false);
		this.kids_textfield_privat_berechtigte.setDisable(false);
		this.kids_textfield_arbeit_berechtigte.setDisable(false);
		this.kids_textfield_mobil_berechtigte.setDisable(false);
		this.kids_textfield_mail_berechtigte.setDisable(false);
		
		this.kids_textfield_krankenkasse.setDisable(false);
		this.kids_choicebox_versichert.setDisable(false);
		this.kids_choicebox_doctor.setDisable(false);
		this.kids_textarea_krankheiten.setDisable(false);
		this.kids_textarea_allergien.setDisable(false);
		this.kids_textarea_besonderheiten.setDisable(false);
		
		this.kids_checkbox_permission_1.setDisable(false);
		this.kids_checkbox_permission_2.setDisable(false);
		this.kids_checkbox_permission_3.setDisable(false);
		this.kids_checkbox_permission_4.setDisable(false);
		this.kids_checkbox_permission_5.setDisable(false);
		this.kids_checkbox_permission_6.setDisable(false);
		this.kids_checkbox_permission_7.setDisable(false);
		this.kids_checkbox_permission_8.setDisable(false);
		this.kids_checkbox_permission_9.setDisable(false);
		this.kids_checkbox_permission_10.setDisable(false);
		this.kids_checkbox_permission_11.setDisable(false);
		this.kids_checkbox_permission_12.setDisable(false);
		this.kids_checkbox_permission_13.setDisable(false);
		this.kids_checkbox_permission_14.setDisable(false);
		this.kids_checkbox_permission_15.setDisable(false);
		this.kids_checkbox_permission_16.setDisable(false);
		this.kids_checkbox_permission_17.setDisable(false);
		this.kids_checkbox_permission_18.setDisable(false);
		this.kids_checkbox_permission_19.setDisable(false);
		this.kids_checkbox_permission_20.setDisable(false);
		
		this.firstListViewClick = false;
	}
	
	private void enableBerechtigtenFields() {
		this.kids_textfield_nachname_berechtigte.setDisable(false);
		this.kids_textfield_vorname_berechtigte.setDisable(false);
		this.kids_datepicker_geburtsdatum_berechtigte.setDisable(false);
		this.kids_checkbox_kontaktperson.setDisable(false);
		this.kids_checkbox_sorgeberechtigt.setDisable(false);
		this.kids_textfield_strasse_berechtigte.setDisable(false);
		this.kids_textfield_hausnummer_berechtigte.setDisable(false);
		this.kids_textfield_plz_berechtigte.setDisable(false);
		this.kids_textfield_ort_berechtigte.setDisable(false);
		this.kids_textfield_privat_berechtigte.setDisable(false);
		this.kids_textfield_arbeit_berechtigte.setDisable(false);
		this.kids_textfield_mobil_berechtigte.setDisable(false);
		this.kids_textfield_mail_berechtigte.setDisable(false);
	}
	
	private void disableBerechtigtenFields() {
		this.kids_textfield_nachname_berechtigte.setDisable(true);
		this.kids_textfield_vorname_berechtigte.setDisable(true);
		this.kids_datepicker_geburtsdatum_berechtigte.setDisable(true);
		this.kids_checkbox_kontaktperson.setDisable(true);
		this.kids_checkbox_sorgeberechtigt.setDisable(true);
		this.kids_textfield_strasse_berechtigte.setDisable(true);
		this.kids_textfield_hausnummer_berechtigte.setDisable(true);
		this.kids_textfield_plz_berechtigte.setDisable(true);
		this.kids_textfield_ort_berechtigte.setDisable(true);
		this.kids_textfield_privat_berechtigte.setDisable(true);
		this.kids_textfield_arbeit_berechtigte.setDisable(true);
		this.kids_textfield_mobil_berechtigte.setDisable(true);
		this.kids_textfield_mail_berechtigte.setDisable(true);
	}
	
	private void setupPermissionFields(List<ListPermissions> list, int i) {
		switch(list.get(i).getID()) {
		case "Vollmacht_1":	if(list.get(i).getActive()) {
								kids_checkbox_permission_1.setVisible(true);
								kids_checkbox_permission_1.setManaged(true);
							} else {
								kids_checkbox_permission_1.setVisible(false);
								kids_checkbox_permission_1.setManaged(false);
							}
							kids_checkbox_permission_1.setText(list.get(i).getDescriptionOrName());
							break;
		case "Vollmacht_2":	if(list.get(i).getActive()) {
								kids_checkbox_permission_2.setVisible(true);
								kids_checkbox_permission_2.setManaged(true);
							} else {
								kids_checkbox_permission_2.setVisible(false);
								kids_checkbox_permission_2.setManaged(false);
							}
							kids_checkbox_permission_2.setText(list.get(i).getDescriptionOrName());
							break;
		case "Vollmacht_3":	if(list.get(i).getActive()) {
								kids_checkbox_permission_3.setVisible(true);
								kids_checkbox_permission_3.setManaged(true);
							} else {
								kids_checkbox_permission_3.setVisible(false);
								kids_checkbox_permission_3.setManaged(false);
							}
		kids_checkbox_permission_3.setText(list.get(i).getDescriptionOrName());
							break;
		case "Vollmacht_4":	if(list.get(i).getActive()) {
								kids_checkbox_permission_4.setVisible(true);
								kids_checkbox_permission_4.setManaged(true);
							} else {
								kids_checkbox_permission_4.setVisible(false);
								kids_checkbox_permission_4.setManaged(false);
							}
		kids_checkbox_permission_4.setText(list.get(i).getDescriptionOrName());
							break;
		case "Vollmacht_5":	if(list.get(i).getActive()) {
								kids_checkbox_permission_5.setVisible(true);
								kids_checkbox_permission_5.setManaged(true);
							} else {
								kids_checkbox_permission_5.setVisible(false);
								kids_checkbox_permission_5.setManaged(false);
							}
		kids_checkbox_permission_5.setText(list.get(i).getDescriptionOrName());
							break;
		case "Vollmacht_6":	if(list.get(i).getActive()) {
								kids_checkbox_permission_6.setVisible(true);
								kids_checkbox_permission_6.setManaged(true);
							} else {
								kids_checkbox_permission_6.setVisible(false);
								kids_checkbox_permission_6.setManaged(false);
							}
		kids_checkbox_permission_6.setText(list.get(i).getDescriptionOrName());
							break;
		case "Vollmacht_7":	if(list.get(i).getActive()) {
								kids_checkbox_permission_7.setVisible(true);
								kids_checkbox_permission_7.setManaged(true);
							} else {
								kids_checkbox_permission_7.setVisible(false);
								kids_checkbox_permission_7.setManaged(false);
							}
		kids_checkbox_permission_7.setText(list.get(i).getDescriptionOrName());
							break;
		case "Vollmacht_8":	if(list.get(i).getActive()) {
								kids_checkbox_permission_8.setVisible(true);
								kids_checkbox_permission_8.setManaged(true);
							} else {
								kids_checkbox_permission_8.setVisible(false);
								kids_checkbox_permission_8.setManaged(false);
							}
		kids_checkbox_permission_8.setText(list.get(i).getDescriptionOrName());
							break;
		case "Vollmacht_9":	if(list.get(i).getActive()) {
								kids_checkbox_permission_9.setVisible(true);
								kids_checkbox_permission_9.setManaged(true);
							} else {
								kids_checkbox_permission_9.setVisible(false);
								kids_checkbox_permission_9.setManaged(false);
							}
		kids_checkbox_permission_9.setText(list.get(i).getDescriptionOrName());
							break;
		case "Vollmacht_10":	if(list.get(i).getActive()) {
								kids_checkbox_permission_10.setVisible(true);
								kids_checkbox_permission_10.setManaged(true);
							} else {
								kids_checkbox_permission_10.setVisible(false);
								kids_checkbox_permission_10.setManaged(false);
							}
		kids_checkbox_permission_10.setText(list.get(i).getDescriptionOrName());
							break;
		case "Vollmacht_11":	if(list.get(i).getActive()) {
								kids_checkbox_permission_11.setVisible(true);
								kids_checkbox_permission_11.setManaged(true);
							} else {
								kids_checkbox_permission_11.setVisible(false);
								kids_checkbox_permission_11.setManaged(false);
							}
		kids_checkbox_permission_11.setText(list.get(i).getDescriptionOrName());
							break;
		case "Vollmacht_12":	if(list.get(i).getActive()) {
								kids_checkbox_permission_12.setVisible(true);
								kids_checkbox_permission_12.setManaged(true);
							} else {
								kids_checkbox_permission_12.setVisible(false);
								kids_checkbox_permission_12.setManaged(false);
							}
		kids_checkbox_permission_12.setText(list.get(i).getDescriptionOrName());
							break;
		case "Vollmacht_13":	if(list.get(i).getActive()) {
								kids_checkbox_permission_13.setVisible(true);
								kids_checkbox_permission_13.setManaged(true);
							} else {
								kids_checkbox_permission_13.setVisible(false);
								kids_checkbox_permission_13.setManaged(false);
							}
		kids_checkbox_permission_13.setText(list.get(i).getDescriptionOrName());
							break;
		case "Vollmacht_14":	if(list.get(i).getActive()) {
								kids_checkbox_permission_14.setVisible(true);
								kids_checkbox_permission_14.setManaged(true);
							} else {
								kids_checkbox_permission_14.setVisible(false);
								kids_checkbox_permission_14.setManaged(false);
							}
		kids_checkbox_permission_14.setText(list.get(i).getDescriptionOrName());
							break;
		case "Vollmacht_15":	if(list.get(i).getActive()) {
								kids_checkbox_permission_15.setVisible(true);
								kids_checkbox_permission_15.setManaged(true);
							} else {
								kids_checkbox_permission_15.setVisible(false);
								kids_checkbox_permission_15.setManaged(false);
							}
		kids_checkbox_permission_15.setText(list.get(i).getDescriptionOrName());
							break;
		case "Vollmacht_16":	if(list.get(i).getActive()) {
								kids_checkbox_permission_16.setVisible(true);
								kids_checkbox_permission_16.setManaged(true);
							} else {
								kids_checkbox_permission_16.setVisible(false);
								kids_checkbox_permission_16.setManaged(false);
							}
		kids_checkbox_permission_16.setText(list.get(i).getDescriptionOrName());
							break;
		case "Vollmacht_17":	if(list.get(i).getActive()) {
								kids_checkbox_permission_17.setVisible(true);
								kids_checkbox_permission_17.setManaged(true);
							} else {
								kids_checkbox_permission_17.setVisible(false);
								kids_checkbox_permission_17.setManaged(false);
							}
		kids_checkbox_permission_17.setText(list.get(i).getDescriptionOrName());
							break;
		case "Vollmacht_18":	if(list.get(i).getActive()) {
								kids_checkbox_permission_18.setVisible(true);
								kids_checkbox_permission_18.setManaged(true);
							} else {
								kids_checkbox_permission_18.setVisible(false);
								kids_checkbox_permission_18.setManaged(false);
							}
		kids_checkbox_permission_18.setText(list.get(i).getDescriptionOrName());
							break;
		case "Vollmacht_19":	if(list.get(i).getActive()) {
								kids_checkbox_permission_19.setVisible(true);
								kids_checkbox_permission_19.setManaged(true);
							} else {
								kids_checkbox_permission_19.setVisible(false);
								kids_checkbox_permission_19.setManaged(false);
							}
		kids_checkbox_permission_19.setText(list.get(i).getDescriptionOrName());
							break;
		case "Vollmacht_20":	if(list.get(i).getActive()) {
								kids_checkbox_permission_20.setVisible(true);
								kids_checkbox_permission_20.setManaged(true);
							} else {
								kids_checkbox_permission_20.setVisible(false);
								kids_checkbox_permission_20.setManaged(false);
							}
		kids_checkbox_permission_20.setText(list.get(i).getDescriptionOrName());
							break;
		}
	}
	
	private void fillPermissionFields() {
		
		this.kids_checkbox_permission_1.setSelected(this.kidData.getVollmachten().get(0).getGiven());
		this.kids_checkbox_permission_2.setSelected(this.kidData.getVollmachten().get(1).getGiven());
		this.kids_checkbox_permission_3.setSelected(this.kidData.getVollmachten().get(2).getGiven());
		this.kids_checkbox_permission_4.setSelected(this.kidData.getVollmachten().get(3).getGiven());
		this.kids_checkbox_permission_5.setSelected(this.kidData.getVollmachten().get(4).getGiven());
		this.kids_checkbox_permission_6.setSelected(this.kidData.getVollmachten().get(5).getGiven());
		this.kids_checkbox_permission_7.setSelected(this.kidData.getVollmachten().get(6).getGiven());
		this.kids_checkbox_permission_8.setSelected(this.kidData.getVollmachten().get(7).getGiven());
		this.kids_checkbox_permission_9.setSelected(this.kidData.getVollmachten().get(8).getGiven());
		this.kids_checkbox_permission_10.setSelected(this.kidData.getVollmachten().get(9).getGiven());
		this.kids_checkbox_permission_11.setSelected(this.kidData.getVollmachten().get(10).getGiven());
		this.kids_checkbox_permission_12.setSelected(this.kidData.getVollmachten().get(11).getGiven());
		this.kids_checkbox_permission_13.setSelected(this.kidData.getVollmachten().get(12).getGiven());
		this.kids_checkbox_permission_14.setSelected(this.kidData.getVollmachten().get(13).getGiven());
		this.kids_checkbox_permission_15.setSelected(this.kidData.getVollmachten().get(14).getGiven());
		this.kids_checkbox_permission_16.setSelected(this.kidData.getVollmachten().get(15).getGiven());
		this.kids_checkbox_permission_17.setSelected(this.kidData.getVollmachten().get(16).getGiven());
		this.kids_checkbox_permission_18.setSelected(this.kidData.getVollmachten().get(17).getGiven());
		this.kids_checkbox_permission_19.setSelected(this.kidData.getVollmachten().get(18).getGiven());
		this.kids_checkbox_permission_10.setSelected(this.kidData.getVollmachten().get(19).getGiven());
		
	}
	
	public void updateKidData(PersonKid data) {
		this.kidData = data;
	}
	
	public PersonKid getKidData() {
		return this.kidData;
	}
	
	public void helpNachname() {
		this.kids_label_hilfe.setText("Der Nachname des Kindes sollte in jedem Fall angegeben werden um eine Zuordnung innerhalb des Datenbestandes zu erleichtern.");
	}
	
	public void helpVorname() {
		this.kids_label_hilfe.setText("Es sollte einrichtungsintern geklärt werden, ob der Rufname oder der vollständige Vorname des Kindes angegeben wird.");
	}
	
	public void helpGeburtsdatum() {
		this.kids_label_hilfe.setText("Das Geburtsdatum bei manueller Angabe bitte mit vollständigem Jahr angeben! (YYYY) Tage und Monate können ohne führende 0 eingetragen werden.");
	}
	
	public void helpGeschlecht() {
		this.kids_label_hilfe.setText("Aus statistischen Gründen sollte das Geschlecht immer angegeben werden.");
	}
	
	public void helpStrasse() {
		this.kids_label_hilfe.setText("Bei getrennt lebenden Eltern sollte der primäre Wohnsitz, wie er im Betreuugnsvertrag steht, angegeben werden.");
	}
	
	public void helpNummer() {
		this.kids_label_hilfe.setText("Für die Hausnummer sind auch Angaben mit Buchstaben, bspw. 14b zulässig.");
	}
	
	public void helpPlz() {
		this.kids_label_hilfe.setText("Die Postleitzahl immer als fünfstellige Zahl angeben, auch wenn eine 0 die führende Zahl ist.");
	}
	
	public void helpOrt() {
		this.kids_label_hilfe.setText("Der Ort sollte ohne Angabe des Stadtteils eingetragen werden, da sich Stadtteile aus der Postleitzahl ergeben.");
	}
	
	public void helpGruppe() {
		this.kids_label_hilfe.setText("Es wird empfohlen Kinder immer einer Organisationseinheit zuzuordnen, auch wenn es sich um eine offene Arbeit handelt. Etagen, Räume oder Bezugserzieher können alternativ genutzt werden. Gruppen sind für statistische Berechnungen eine essenzielle Angabe!");
	}
	
	public void helpAktiv() {
		this.kids_label_hilfe.setText("Der Status 'aktiv' bezeichnet Kinder mit einem aktuellen Betreuungsvertrag. 'inaktiv' sind ehemalige Kinder oder künftige Kinder.");
	}
	
	public void helpVertragsbeginn() {
		this.kids_label_hilfe.setText("Bei manueller Eingabe das Jahr bitte in vollständiger Schreibweise angeben! (YYYY) Tage und Monate können ohne führende 0 eingetragen werden.");
	}
	
	public void helpVertragsende() {
		this.kids_label_hilfe.setText("Bei manueller Eingabe das Jahr bitte in vollständiger Schreibweise angeben! (YYYY) Tage und Monate können ohne führende 0 eingetragen werden.");
	}
	
	public void helpBetreuungszeit() {
		this.kids_label_hilfe.setText("Dieser Wert sollte unbedingt in der Tabelle eingetragen werden, da statistische Berechnungen des Personalschlüssels und das Zeiterfassungssystem für das Kind nicht funktionieren!");
	}
	
	public void helpNationalitaet() {
		this.kids_label_hilfe.setText("Die Nationalität bitte in Kleinschreibweise eintragen. Der Wert dient statistischen Erhebungen.");
	}
	
	public void helpMuttersprache() {
		this.kids_label_hilfe.setText("Die Muttersprache bitte in Großschreibweise eintragen. Die Muttersprache ist wichtig für die Einschätzung der Sprachentwicklung.");
	}
	
	public void helpBetreuungsart() {
		this.kids_label_hilfe.setText("Bitte auswählen ob das Kind einen Krippen-, Kita- oder Hortvertrag hat. (Berechnung des Personalschlüssels)");
	}
	
	public void helpKindSpeichern() {
		this.kids_label_hilfe.setText("Mit dem Klick auf den 'Speichern' Knopf werden alle Daten des aktuell ausgewählten Kindes in die Datenbank übertragen.");
	}
	
	public void helpKindLoeschen() {
		this.kids_label_hilfe.setText("Mit dem Klick auf den 'Löschen' Knopf werden alle Daten des aktuell ausgewählten Kindes aus der Datenbank gelöscht. Der Löschvorgang muss durch einen erneuten Klick bestätigt werden!");
	}
	
	// Hilfe zur Betreuugnsperson
	
	public void helpBetreuungspersonNachname() {
		this.kids_label_hilfe.setText("Der Nachname der Kontaktperson sollte angegeben werden um einen Abgleich mit dem Personalausweis zu ermöglichen.");
	}
	
	public void helpBetreuungspersonVorname() {
		this.kids_label_hilfe.setText("Der Vorname der Kontaktperson sollte angegeben werden um einen Abgleich mit dem Personalausweis zu ermöglichen.");
	}
	
	public void helpBetreuungspersonGeburtsdatum() {
		this.kids_label_hilfe.setText("Die Angabe eines Geburtsdatum ist nicht zwingend nötig, kann aber den Abgleich der Personalien erleichtern.");
	}
	
	public void helpBetreuungspersonAdresse() {
		this.kids_label_hilfe.setText("Die Adresse der Kontaktperson kann einen Abgleich mit dem Personalausweis erleichtern. Bei getrennt lebenden Eltern dient sie der schriftlichen Kontaktaufnahme.");
	}
	
	public void helpBetreuungspersonPlz() {
		this.kids_label_hilfe.setText("Die Postleitzahl der Kontaktperson kann einen Abgleich mit dem Personalausweis erleichtern. Bei getrennt lebenden Eltern dient sie der schriftlichen Kontaktaufnahme.");
	}
	
	public void helpBetreuungspersonOrt() {
		this.kids_label_hilfe.setText("Der Wohnort der Kontaktperson kann einen Abgleich mit dem Personalausweis erleichtern. Bei getrennt lebenden Eltern dient sie der schriftlichen Kontaktaufnahme.");
	}
	
	public void helpBetreuungspersonSorgeberechtigt() {
		this.kids_label_hilfe.setText("Die Angabe der Personensorgeberechtigung ist sehr wichtig für rechtliche Fragen. Personensorgeberechtigte erscheinen im Reiter 'Gesundheitsdaten' als Versicherungnehmer.");
	}
	
	public void helpBetreuungspersonKontakt() {
		this.kids_label_hilfe.setText("Nicht jede berechtigte Person ist gleichzeitig eine Kontaktperson für Notfälle. Mit der Auswahl des Feldes erscheinen Personen gezielt als Kontaktperson.");
	}
	
	public void helpBetreuungspersonPrivat() {
		this.kids_label_hilfe.setText("In diesem Feld wird die private Telefonnummer der Person angegeben.");
	}
	
	public void helpBetreuungspersonMobil() {
		this.kids_label_hilfe.setText("In diesem Feld wird die Handynummer der Person angegeben.");
	}
	
	public void helpBetreuungspersonArbeit() {
		this.kids_label_hilfe.setText("In diesem Feld wird die dienstliche Telefonnummer der Person angegeben.");
	}
	
	public void helpBetreuungspersonEmail() {
		this.kids_label_hilfe.setText("In diesem Feld wird die Emailadresse der Person angegeben.");
	}
	
	public void helpBetreuungspersonSpeichern() {
		this.kids_label_hilfe.setText("Mit dem Klick auf den 'Speichern' Knopf werden alle Daten der aktuell ausgewählten Person in die Datenbank übertragen.");
	}
	
	public void helpBetreuungspersonLoeschen() {
		this.kids_label_hilfe.setText("Mit dem Klick auf den 'Löschen' Knopf werden alle Daten der aktuell ausgewählten Person aus der Datenbank gelöscht. Der Löschvorgang muss durch einen erneuten Klick bestätigt werden!");
	}
	
	public void helpKrankenkasse() {
		this.kids_label_hilfe.setText("Die Angabe der Krankenkasse kann im Falles eines Unfalls hilfreich für die behandelnden Ärzte sein.");
	}
	
	public void helpVersichert() {
		this.kids_label_hilfe.setText("Zusätzlich zur Angabe der Krankenkasse benötigen Ärzte und Sanitäter oft die Angabe der Person über die das Kind versichert ist.");
	}
	
	public void helpAllergien() {
		this.kids_label_hilfe.setText("Ist das Kind allergisch auf Insektenstiche, Lebensmittel oder klassisch Pollen? Gibt es Notfallmedikamente? Bitte hier vermerken!");
	}
	
	public void helpKrankheiten() {
		this.kids_label_hilfe.setText("Wenn das Kind Krankheiten hat, die relevant für den Alltag sind sollten diese heir vermerkt werden.");
	}
	
	public void helpBesonderheiten() {
		this.kids_label_hilfe.setText("Das Kind betreffende Besonderheiten oder Hinweise können hier vermerkt werden.");
	}
	
	public void helpKinderarzt() {
		this.kids_label_hilfe.setText("Der Kinderarzt lässt sich nicht direkt bearbeiten. Bitte erst über die Eingabemaske 'Externe Anbieter' einen Arzt eingeben und dann hier auswählen.");
	}
	
	

}
