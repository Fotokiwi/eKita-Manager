package application.controller.root;

import application.EKitaManager;
import customclasses.ListPermissions;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class permissionsController {

	@FXML
	private ListView<ListPermissions> permissions_listview_overview;
	@FXML
	private Label permissions_label_number;
	@FXML
	private CheckBox permissions_checkbox_aktiv;
	@FXML
	private TextField permissions_textfield_name;
	@FXML
	private TextArea permissions_textarea_description;
	@FXML
	private TextField permissions_textfield_shortdescription;
	@FXML
	private Button permissions_button_save;
	@FXML
	private Button permissions_button_delete;
	
	private boolean firstListViewClick = true;
	
	private ListPermissions permissionData = null;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public permissionsController() {
		
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		
		updatePermissionsListView();
		
		this.permissions_listview_overview.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	            if(firstListViewClick) {
	            	enableAllFields();
	            }
				resetPermissionsFields();
				if(permissions_listview_overview.getSelectionModel().getSelectedItem() != null)
					fillFields(permissions_listview_overview.getSelectionModel().getSelectedItem().getID());
	        }
	    });
		
		this.permissions_button_delete.setOnAction((event) -> {
			resetPermissionsFields();
		});
		
		this.permissions_button_save.setOnAction((event) -> {
			savePermissionsFields();
		});
		
	}

	private void savePermissionsFields() {
		this.permissionData.setActive(this.permissions_checkbox_aktiv.isSelected());
		this.permissionData.setName(this.permissions_textfield_name.getText());
		this.permissionData.setDescription(this.permissions_textarea_description.getText());
		this.permissionData.setShortDescription(this.permissions_textfield_shortdescription.getText());
		
		EKitaManager.getInstance().permissionsLogicC.savePermission(this.permissionData);
		updatePermissionsListView();
	}

	private void updatePermissionsListView() {
		ObservableList<ListPermissions> lefts = EKitaManager.getInstance().permissionsLogicC.generatePermissionsList();
		
		this.permissions_listview_overview.setItems(lefts);
		this.permissions_listview_overview.setCellFactory(new Callback<ListView<ListPermissions>, ListCell<ListPermissions>>(){
			 
            @Override
            public ListCell<ListPermissions> call(ListView<ListPermissions> g) {
                 
                ListCell<ListPermissions> cell = new ListCell<ListPermissions>(){
 
                    @Override
                    protected void updateItem(ListPermissions t, boolean bln) {
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

	private void enableAllFields() {
		this.permissions_checkbox_aktiv.setDisable(false);
		this.permissions_textfield_name.setDisable(false);
		this.permissions_textarea_description.setDisable(false);
		this.permissions_textfield_shortdescription.setDisable(false);
		this.permissions_button_save.setDisable(false);
		this.permissions_button_delete.setDisable(false);
	}

	private void fillFields(String id) {
		if(id == null || id.equalsIgnoreCase(""))
			return;
		
		this.permissionData = EKitaManager.getInstance().permissionsLogicC.openPermission(id);
		
		if(this.permissionData == null)
			return;
		
		this.permissions_label_number.setText(this.permissionData.getID().split("_")[1]);
		this.permissions_checkbox_aktiv.setSelected(this.permissionData.getActive());
		this.permissions_textfield_name.setText(this.permissionData.getName());
		this.permissions_textarea_description.setText(this.permissionData.getDescription());
		this.permissions_textfield_shortdescription.setText(this.permissionData.getShortDescription());
		
	}

	private void resetPermissionsFields() {
		this.permissions_checkbox_aktiv.setSelected(false);
		this.permissions_textfield_name.setText("");
		this.permissions_textarea_description.setText("");
		this.permissions_textfield_shortdescription.setText("");
	}
	
}
