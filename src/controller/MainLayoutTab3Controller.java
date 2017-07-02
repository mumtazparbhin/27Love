package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.util.Callback;
import model.Properties;

public class MainLayoutTab3Controller implements Initializable {

	@FXML
	ListView<String> MainLayoutTab3ListView;

	@FXML
	TextField MainLayoutTab3MeasurementNameTextField;

	@FXML
	Button MainLayoutTab3AddButton;

	ObservableList<String> list = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bindActions();
		if(Properties.IS_MEASUREMENT_SET) {
			loadMeasurementsfrmDB();
		}
	}

	private void bindActions() {		
		//Add button
		MainLayoutTab3AddButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				addMeasuremets(event);
			}
		});

		//text field binding
		BooleanBinding textBoxEmptyBinder = new BooleanBinding() {
			{
				super.bind(MainLayoutTab3MeasurementNameTextField.textProperty());
			}
			@Override
			protected boolean computeValue() {
				return MainLayoutTab3MeasurementNameTextField.getText().trim().equals("");
			}
		};
		MainLayoutTab3AddButton.disableProperty().bind(textBoxEmptyBinder);
	}
	
	private void loadMeasurementsfrmDB() {
		
	}
	
	private void addMeasuremets(ActionEvent event) {
		System.out.println("Add in add measurements");
		list.add(MainLayoutTab3MeasurementNameTextField.getText());
		MainLayoutTab3ListView.setItems(list);
		MainLayoutTab3ListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> param) {
				return new XCell();
			}
		});
	}

	static class XCell extends ListCell<String> {
		HBox hbox = new HBox();
		Label label = new Label();
		Pane pane = new Pane();
		// Image edit = new
		// Image("file:///F:/eclipse_ws/27thLove/resources/edit.png");
		Button editBtn = new Button("Edit");
		Button deleteBtn = new Button("delete");
		String lastItem;

		public XCell() {
			super();
			// button.setGraphic(new ImageView(edit));
			// button.setMaxSize(10d,10d);
			hbox.getChildren().addAll(label, pane, editBtn, deleteBtn);
			HBox.setHgrow(pane, Priority.ALWAYS);
			editBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					System.out.println(lastItem + " : " + event);
				}
			});

			deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					System.out.println(lastItem + " : " + event);
				}
			});
		}

		@Override
		protected void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
			setText(null); // No text in label of super class
			if (empty) {
				lastItem = null;
				setGraphic(null);
			} else {
				lastItem = item;
				label.setText(item != null ? item : "<null>");
				setGraphic(hbox);
			}
		}
	}

}
