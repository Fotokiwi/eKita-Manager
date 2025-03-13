package application.controller.root;

import application.EKitaManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class fieldSetupController {
	
	@FXML
	private CheckBox fieldSetup_kid_nachname;
	@FXML
	private CheckBox fieldSetup_kid_vorname;
	@FXML
	private CheckBox fieldSetup_kid_geburtsdatum;
	@FXML
	private CheckBox fieldSetup_kid_adresse;
	@FXML
	private CheckBox fieldSetup_kid_vertragsbeginn;
	@FXML
	private CheckBox fieldSetup_kid_vertragsende;
	@FXML
	private CheckBox fieldSetup_kid_nationalitaet;
	@FXML
	private CheckBox fieldSetup_kid_muttersprache;
	@FXML
	private CheckBox fieldSetup_kid_krankenkasse;
	@FXML
	private CheckBox fieldSetup_kid_allergien;
	@FXML
	private CheckBox fieldSetup_kid_krankheiten;
	@FXML
	private CheckBox fieldSetup_kid_besonderheiten;
	@FXML
	private CheckBox fieldSetup_person_nachname;
	@FXML
	private CheckBox fieldSetup_person_vorname;
	@FXML
	private CheckBox fieldSetup_person_geburtsdatum;
	@FXML
	private CheckBox fieldSetup_person_adresse;
	@FXML
	private CheckBox fieldSetup_person_telefon;
	@FXML
	private CheckBox fieldSetup_person_handy;
	@FXML
	private CheckBox fieldSetup_person_arbeit;
	@FXML
	private CheckBox fieldSetup_person_mail;
	
	@FXML
	private Button fieldSetup_button_save;
	@FXML
	private Button fieldSetup_button_reset;
	
	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public fieldSetupController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		
		this.fieldSetup_button_save.setOnAction((event) -> {
			saveFields();
		});
		
		this.fieldSetup_button_reset.setOnAction((event) -> {
			resetFieldsToDefault();
		});
		
		loadFields();
		
	}
	
	private void loadFields() {
		
		String defaultValues = EKitaManager.getInstance().settings.getDefaults();
		String[] splitValues = defaultValues.split(",");
		
		fieldSetup_kid_nachname.setSelected(Boolean.parseBoolean(splitValues[0]));
		fieldSetup_kid_vorname.setSelected(Boolean.parseBoolean(splitValues[1]));
		fieldSetup_kid_geburtsdatum.setSelected(Boolean.parseBoolean(splitValues[2]));
		fieldSetup_kid_adresse.setSelected(Boolean.parseBoolean(splitValues[3]));
		fieldSetup_kid_vertragsbeginn.setSelected(Boolean.parseBoolean(splitValues[4]));
		fieldSetup_kid_vertragsende.setSelected(Boolean.parseBoolean(splitValues[5]));
		fieldSetup_kid_nationalitaet.setSelected(Boolean.parseBoolean(splitValues[6]));
		fieldSetup_kid_muttersprache.setSelected(Boolean.parseBoolean(splitValues[7]));
		fieldSetup_kid_krankenkasse.setSelected(Boolean.parseBoolean(splitValues[8]));
		fieldSetup_kid_allergien.setSelected(Boolean.parseBoolean(splitValues[9]));
		fieldSetup_kid_krankheiten.setSelected(Boolean.parseBoolean(splitValues[10]));
		fieldSetup_kid_besonderheiten.setSelected(Boolean.parseBoolean(splitValues[11]));
		fieldSetup_person_nachname.setSelected(Boolean.parseBoolean(splitValues[12]));
		fieldSetup_person_vorname.setSelected(Boolean.parseBoolean(splitValues[13]));
		fieldSetup_person_geburtsdatum.setSelected(Boolean.parseBoolean(splitValues[14]));
		fieldSetup_person_adresse.setSelected(Boolean.parseBoolean(splitValues[15]));
		fieldSetup_person_telefon.setSelected(Boolean.parseBoolean(splitValues[16]));
		fieldSetup_person_handy.setSelected(Boolean.parseBoolean(splitValues[17]));
		fieldSetup_person_arbeit.setSelected(Boolean.parseBoolean(splitValues[18]));
		fieldSetup_person_mail.setSelected(Boolean.parseBoolean(splitValues[19]));
		
	}
	
	private void saveFields() {
		
		EKitaManager.getInstance().settings.updateDefaults(fieldSetup_kid_nachname.isSelected(), fieldSetup_kid_vorname.isSelected(), fieldSetup_kid_geburtsdatum.isSelected(), fieldSetup_kid_adresse.isSelected(), fieldSetup_kid_vertragsbeginn.isSelected(), fieldSetup_kid_vertragsende.isSelected(), fieldSetup_kid_nationalitaet.isSelected(), fieldSetup_kid_muttersprache.isSelected(), fieldSetup_kid_krankenkasse.isSelected(), fieldSetup_kid_allergien.isSelected(), fieldSetup_kid_krankheiten.isSelected(), fieldSetup_kid_besonderheiten.isSelected(), fieldSetup_person_nachname.isSelected(), fieldSetup_person_vorname.isSelected(), fieldSetup_person_geburtsdatum.isSelected(), fieldSetup_person_adresse.isSelected(), fieldSetup_person_telefon.isSelected(), fieldSetup_person_handy.isSelected(), fieldSetup_person_arbeit.isSelected(), fieldSetup_person_mail.isSelected());
		
	}
	
	private void resetFieldsToDefault() {
		
		fieldSetup_kid_nachname.setSelected(true);
		fieldSetup_kid_vorname.setSelected(true);
		fieldSetup_kid_geburtsdatum.setSelected(true);
		fieldSetup_kid_adresse.setSelected(true);
		fieldSetup_kid_vertragsbeginn.setSelected(true);
		fieldSetup_kid_vertragsende.setSelected(true);
		fieldSetup_kid_nationalitaet.setSelected(false);
		fieldSetup_kid_muttersprache.setSelected(false);
		fieldSetup_kid_krankenkasse.setSelected(true);
		fieldSetup_kid_allergien.setSelected(false);
		fieldSetup_kid_krankheiten.setSelected(false);
		fieldSetup_kid_besonderheiten.setSelected(false);
		fieldSetup_person_nachname.setSelected(true);
		fieldSetup_person_vorname.setSelected(true);
		fieldSetup_person_geburtsdatum.setSelected(false);
		fieldSetup_person_adresse.setSelected(false);
		fieldSetup_person_telefon.setSelected(false);
		fieldSetup_person_handy.setSelected(false);
		fieldSetup_person_arbeit.setSelected(false);
		fieldSetup_person_mail.setSelected(false);
		
	}

}
