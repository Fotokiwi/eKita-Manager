package application.controller.root;

import application.EKitaManager;
import customclasses.Betreuungszeit;
import customclasses.ListBetreuungszeit;
import customclasses.PersonKid;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class kidsHoursController {
	
	PersonKid kidData;
	
	public ObservableList<ListBetreuungszeit> lefts = null; 
	
	@FXML
	private ListView<ListBetreuungszeit> kidHours_listview_stunden;
	@FXML 
	private Label kidHors_label_kindername;
	@FXML
	private DatePicker kidHours_datepicker_beginn;
	@FXML
	private DatePicker kidHours_datepicker_ende;
	@FXML
	private Slider kidHours_slider_stunden;
	@FXML
	private Button kidHours_button_save;
	@FXML
	private Button kidHours_button_delete;
	@FXML
	private Button kidHours_button_quit;
	@FXML
	private CheckBox kidHours_checkbox_autoedit;
	@FXML
	private TextArea kidHours_textarea_autoedit;
	
	private boolean firstListViewClick = true;
	
	private int selectedIndex;
	
	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public kidsHoursController() {
		
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		
		EKitaManager.getInstance().kidsHoursController = this;
		
		this.kidData = EKitaManager.getInstance().kidsController.getKidData();
				
		updateBetreuungszeitenListView();
		
		this.kidHours_listview_stunden.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	        	if(firstListViewClick) {
	            	enableAllFields();
	            	firstListViewClick = false;
	            }
	        	if(kidHours_listview_stunden.getSelectionModel().getSelectedItem() == null)
	        		return;
	        	selectedIndex = kidHours_listview_stunden.getSelectionModel().getSelectedIndex();
	        	fillFieldsBetreuungszeit(kidHours_listview_stunden.getSelectionModel().getSelectedItem().getZid());
	        }
	    });
		
		this.kidHours_button_save.setOnAction((event) -> {
			save();
		});
		
		this.kidHours_button_delete.setOnAction((event) -> {
			delete();
		});
		
		this.kidHours_button_quit.setOnAction((event) -> {
			quit();
		});
		
		this.kidHors_label_kindername.setText(this.kidData.getNachname() + ", " + this.kidData.getVorname());
		
	}
	
	private void enableAllFields() {
		
		this.kidHours_datepicker_beginn.setDisable(false);
		this.kidHours_datepicker_ende.setDisable(false);
		this.kidHours_slider_stunden.setDisable(false);
		this.kidHours_button_save.setDisable(false);
		this.kidHours_button_delete.setDisable(false);
		
	}

	private void fillFieldsBetreuungszeit(int zid) {
		
		if(selectedIndex == 0) {
			this.kidHors_label_kindername.setText(this.kidData.getNachname() + ", " + this.kidData.getVorname());
			this.kidHours_datepicker_beginn.setValue(null);
			this.kidHours_datepicker_ende.setValue(null);
			this.kidHours_slider_stunden.setValue(9);
			return;
		}

		this.kidHors_label_kindername.setText(this.kidData.getNachname() + ", " + this.kidData.getVorname());
		this.kidHours_datepicker_beginn.setValue(this.kidHours_listview_stunden.getItems().get(selectedIndex).getBeginnRaw());
		this.kidHours_datepicker_ende.setValue(this.kidHours_listview_stunden.getItems().get(selectedIndex).getEndeRaw());
		this.kidHours_slider_stunden.setValue(this.kidHours_listview_stunden.getItems().get(selectedIndex).getStunden());
		
	}

	private void updateBetreuungszeitenListView() {
		
		lefts = EKitaManager.getInstance().kidsHoursLogicC.generateBetreuungszeitenList(this.kidData);
		
		this.kidHours_listview_stunden.setItems(lefts);
		this.kidHours_listview_stunden.setCellFactory(new Callback<ListView<ListBetreuungszeit>, ListCell<ListBetreuungszeit>>(){
			 
            @Override
            public ListCell<ListBetreuungszeit> call(ListView<ListBetreuungszeit> p) {
                 
                ListCell<ListBetreuungszeit> cell = new ListCell<ListBetreuungszeit>(){
 
                    @Override
                    protected void updateItem(ListBetreuungszeit t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getFormatted());
                        }
                    }
 
                };
                 
                return cell;
            }
        });
		
	}
	
	private void localUpdate() {
		
		this.kidHours_listview_stunden.setItems(lefts);
		this.kidHours_listview_stunden.setCellFactory(new Callback<ListView<ListBetreuungszeit>, ListCell<ListBetreuungszeit>>(){
			 
            @Override
            public ListCell<ListBetreuungszeit> call(ListView<ListBetreuungszeit> p) {
                 
                ListCell<ListBetreuungszeit> cell = new ListCell<ListBetreuungszeit>(){
 
                    @Override
                    protected void updateItem(ListBetreuungszeit t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getFormatted());
                        }
                    }
 
                };
                 
                return cell;
            }
        });
		
	}

	private void save() {
		
		if(checkFields() == false)
			return;
		
		if(EKitaManager.getInstance().kidsHoursLogicC.analyseDateRedundancy(this.kidHours_listview_stunden.getItems().get(selectedIndex).getZid(), this.kidHours_datepicker_beginn.getValue(), this.kidHours_datepicker_ende.getValue(), this.kidHours_listview_stunden.getItems(), this.kidHours_checkbox_autoedit.isSelected()) == false) {
			this.kidHours_checkbox_autoedit.setDisable(false);
			this.kidHours_checkbox_autoedit.setVisible(true);
			this.kidHours_textarea_autoedit.setVisible(true);
			return;
		}
			
		if(selectedIndex == 0) {
			this.kidHours_listview_stunden.getItems().add(new ListBetreuungszeit(-1, this.kidData.getPID(), this.kidHours_datepicker_beginn.getValue().toString(), this.kidHours_datepicker_ende.getValue().toString(), (int) this.kidHours_slider_stunden.getValue()));
		} else {
			this.kidHours_listview_stunden.getItems().get(selectedIndex).setBeginn(this.kidHours_datepicker_beginn.getValue());
			this.kidHours_listview_stunden.getItems().get(selectedIndex).setEnde(this.kidHours_datepicker_ende.getValue());
			this.kidHours_listview_stunden.getItems().get(selectedIndex).setStunden((int) this.kidHours_slider_stunden.getValue());
		}
		
		resetFields();		
		localUpdate();
		
	}
	
	private void transfer() {
		this.kidData.cleanBetreuungszeit();
		for(int i = 1; i < kidHours_listview_stunden.getItems().size(); i++) {
			this.kidData.addBetreuungszeit(new Betreuungszeit(this.kidHours_listview_stunden.getItems().get(i).getZid(), this.kidHours_listview_stunden.getItems().get(i).getBeginnData(), this.kidHours_listview_stunden.getItems().get(i).getEndeData(), this.kidHours_listview_stunden.getItems().get(i).getStunden()));
		}
		
	}
	
	private void delete() {
		
		resetFields();
		//ListBetreuungszeit tmp = this.kidHours_listview_stunden.getItems().get(selectedIndex);
		if(selectedIndex > 0)
			this.lefts.remove(selectedIndex);
		localUpdate();
		//EKitaManager.getInstance().kidsHoursLogicC.zeiten.set(selectedIndex, tmp);
		
	}
	
	private void resetFields() {
		
		this.kidHors_label_kindername.setText("");
		this.kidHours_datepicker_beginn.setValue(null);
		this.kidHours_datepicker_ende.setValue(null);
		this.kidHours_slider_stunden.setValue(9);
		this.kidHours_checkbox_autoedit.setDisable(true);
		this.kidHours_checkbox_autoedit.setVisible(false);
		this.kidHours_checkbox_autoedit.setSelected(false);
		this.kidHours_textarea_autoedit.setVisible(false);
		
	}
	
	private void quit() {
		
		transfer();
		EKitaManager.getInstance().kidsController.updateKidData(kidData);
		EKitaManager.getInstance().kidsBetreuungszeitStage.close();
		
	}
	
	private boolean checkFields() {
		
		if(this.kidHours_datepicker_beginn.getValue() == null) {
			EKitaManager.getInstance().showTooltip(EKitaManager.getInstance().kidsBetreuungszeitStage, this.kidHours_datepicker_beginn, EKitaManager.getInstance().resourceBundle.getString("key.kids_tooltip_fillin_hoursbegin"), null);
			return false;
		}
		if(this.kidHours_datepicker_ende.getValue() == null) {
			EKitaManager.getInstance().showTooltip(EKitaManager.getInstance().kidsBetreuungszeitStage, this.kidHours_datepicker_ende, EKitaManager.getInstance().resourceBundle.getString("key.kids_tooltip_fillin_hoursend"), null);
			return false;
		}
		
		return true;
		
	}

}
