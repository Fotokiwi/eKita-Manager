package application.controller.root;

import application.EKitaManager;
import customclasses.ListEmployee;
import customclasses.ListGroup;
import customclasses.ListPerson;
import customclasses.PersonKid;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import util.stringcheck.StringCheck;

public class groupsController {
	
	@FXML
	public Label groups_label_hilfe;
	
	@FXML
	public TextField groups_textfield_name;
	@FXML
	public TextArea groups_textarea_description;
	@FXML
	public ChoiceBox<ListEmployee> groups_choicebox_erzieher;
	@FXML
	public TextField groups_textfield_room;

	@FXML
	public Button groups_button_save;
	@FXML
	public Button groups_button_delete;
	
	//Listenansicht aller Kinder
	@FXML
	private ListView<ListGroup> groups_listview_overview;
	@FXML
	private ListView<ListPerson> groups_kids_listview_overview;
	
	@FXML
	private Label groups_kids_label_lastname;
	@FXML
	private Label groups_kids_label_firstname;
	@FXML
	private Label groups_kids_label_address;
	@FXML
	private Label groups_kids_label_plz;
	@FXML
	private Label groups_kids_label_town;
	@FXML
	private Label groups_kids_label_group;
	@FXML
	private Label groups_kids_label_birthday;
	
	//Container der Kinderdaten
	private ListGroup groupData = null;
	private PersonKid kidData = null;
	
	private boolean firstListViewClick = true;
	
	
	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public groupsController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		
		updateGroupsListView();
		
		this.groups_listview_overview.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	            if(firstListViewClick) {
	            	enableAllFields();
	            }
	            if(groups_listview_overview.getSelectionModel().getSelectedItem() != null) {
	            	fillFields(groups_listview_overview.getSelectionModel().getSelectedItem().getGroupID());
	            	updateKidsListView(groups_listview_overview.getSelectionModel().getSelectedItem().getGroupID());
	            }
	            
				resetKidsFields();
	        }
	    });
		
		this.groups_kids_listview_overview.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {	            
	        	if(groups_kids_listview_overview.getSelectionModel().getSelectedItem() != null)
	        		fillKidsFields(groups_kids_listview_overview.getSelectionModel().getSelectedItem().getId());
	        }
	    });
		
		this.groups_button_delete.setOnAction((event) -> {
			EKitaManager.getInstance().groupsLogicC.deleteGroup(this.groupData);		
			updateGroupsListView();
			resetFields();
		});
		
		this.groups_button_save.setOnAction((event) -> {
			saveFields();
		});

		
		this.groups_choicebox_erzieher.setConverter(EKitaManager.getInstance().employeeConverter);
		this.groups_choicebox_erzieher.setItems(EKitaManager.getInstance().employees);
		this.groups_choicebox_erzieher.setValue(EKitaManager.getInstance().employees.get(0));
		
	}

	private void updateGroupsListView() {
		ObservableList<ListGroup> lefts = EKitaManager.getInstance().groupsLogicC.generateGroupsList();
		
		this.groups_listview_overview.setItems(lefts);
		this.groups_listview_overview.setCellFactory(new Callback<ListView<ListGroup>, ListCell<ListGroup>>(){
			 
            @Override
            public ListCell<ListGroup> call(ListView<ListGroup> g) {
                 
                ListCell<ListGroup> cell = new ListCell<ListGroup>(){
 
                    @Override
                    protected void updateItem(ListGroup t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getGroupName());
                        }
                    }
 
                };
                 
                return cell;
            }
        });
	}

	private void updateKidsListView(int groupID) {
		ObservableList<ListPerson> lefts = EKitaManager.getInstance().groupsLogicC.generateKidsList(groupID);
		
		this.groups_kids_listview_overview.setItems(lefts);
		this.groups_kids_listview_overview.setCellFactory(new Callback<ListView<ListPerson>, ListCell<ListPerson>>(){
			 
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
		
		this.groupData = EKitaManager.getInstance().groupsLogicC.openGroup(id);
		
		if(id == 0) {
			resetFields();
			return;
		}		
		
		if(groupData == null)
			return;
		
		this.groups_textfield_name.setText(groupData.getGroupName());
		this.groups_textarea_description.setText(groupData.getGroupDescription());
		this.groups_textfield_room.setText(groupData.getGroupRoom());
		
		for(int i = 0; i < EKitaManager.getInstance().employees.size(); i++) {
			if(((int) EKitaManager.getInstance().employees.get(i).getEID()) == groupData.getGroupEmployee()) {
				this.groups_choicebox_erzieher.setValue(EKitaManager.getInstance().employees.get(i));
				break;
			} else {
				this.groups_choicebox_erzieher.setValue(EKitaManager.getInstance().employees.get(0));
			}
		}
		
	}
	
	private void fillKidsFields(int id) {
		if(id == -1)
			return;
		
		this.kidData = EKitaManager.getInstance().kidsLogicC.openPerson(id);
		
		if(id == 0) {
			resetFields();
			return;
		}		
		
		if(kidData == null)
			return;
		
		this.groups_kids_label_lastname.setText(kidData.getNachname());
		this.groups_kids_label_firstname.setText(kidData.getVorname());
		this.groups_kids_label_address.setText(kidData.getStrasse() + " " + kidData.getHausnummer());
		this.groups_kids_label_plz.setText(kidData.getPlz());
		this.groups_kids_label_town.setText(kidData.getOrt());
		this.groups_kids_label_group.setText(kidData.getGruppe().getGroupName());
		this.groups_kids_label_birthday.setText(kidData.getGeburtsdatum(true));
	}
	
	private void resetFields() {	
		
		this.groups_textfield_name.setText("");
		this.groups_textarea_description.setText("");
		this.groups_textfield_room.setText("");
		
	}
	
	private void resetKidsFields() {	
		
		this.groups_kids_label_lastname.setText("");
		this.groups_kids_label_firstname.setText("");
		this.groups_kids_label_address.setText("");
		this.groups_kids_label_plz.setText("");
		this.groups_kids_label_town.setText("");
		this.groups_kids_label_group.setText("");
		this.groups_kids_label_birthday.setText("");
		
	}
	
	private void enableAllFields() {
		
		this.groups_textfield_name.setDisable(false);
		this.groups_textarea_description.setDisable(false);
		this.groups_choicebox_erzieher.setDisable(false);
		this.groups_textfield_room.setDisable(false);
		
	}
	
	private void saveFields() {
		
		if(!checkFields())
			return;
		
		this.groupData.setGroupName(this.groups_textfield_name.getText());
		this.groupData.setGroupDescription(this.groups_textarea_description.getText());
		this.groupData.setGroupEmployee(this.groups_choicebox_erzieher.getValue().getEID());
		this.groupData.setGroupRoom(this.groups_textfield_room.getText());
		
		EKitaManager.getInstance().groupsLogicC.saveGroup(this.groupData);		
		updateGroupsListView();
		
	}

	private boolean checkFields() {

		if(StringCheck.containsIllegals(this.groups_textfield_name.getText()) || this.groups_textfield_name.getText().equalsIgnoreCase("")) {
			EKitaManager.getInstance().showTooltip(EKitaManager.getInstance().programStage, this.groups_textfield_name, EKitaManager.getInstance().resourceBundle.getString("key.error_wrongchars"), null);
			return false;
		}
		
		return true;
		
	}

}
