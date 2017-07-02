package controller;

import java.net.URL;
import java.util.ResourceBundle;

import helpers.DBHelper;
import helpers.FileHandler;
import helpers.ToasMessage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import model.Properties;

public class MainLayoutController implements Initializable{
	
	@FXML
	Label MainLayoutToastLabel;
	
	@FXML
	TabPane MainLaoyoutTabPane;
	
	@FXML
	Tab MainLayoutOrderTab;
	
	@FXML
	Tab MainLayoutCustomerTab;
	
	@FXML
	Tab MainLayoutMeasurementTab;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FileHandler.readProperties();
		if(Properties.IS_INITIAL) {
			if (DBHelper.initializeDB()) {
				Properties.IS_INITIAL = false;
				FileHandler.writeProperties();
			} else {
				System.out.println("App cannot bt initialized");
				ToasMessage.makeText(MainLayoutToastLabel, "App cannot bt initialized", null);	
				Stage stage = (Stage) MainLayoutToastLabel.getScene().getWindow();
				stage.close();
				return;
			}
		}
		if (!Properties.IS_MEASUREMENT_SET) {
			ToasMessage.makeText(MainLayoutToastLabel, "Add measurements to proceed further", null);
			SingleSelectionModel<Tab> selectionModel = MainLaoyoutTabPane.getSelectionModel();
			selectionModel.select(2);
			MainLayoutOrderTab.setDisable(true);
			MainLayoutCustomerTab.setDisable(true);
		}
	}

}
