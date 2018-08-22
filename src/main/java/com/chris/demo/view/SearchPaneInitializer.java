package com.chris.demo.view;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SearchPaneInitializer extends Initializable<Pane> {
	private final Stage primaryStage;

	public SearchPaneInitializer(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public String getLocationFile() {
		return "SearchPane.fxml";
	}

	@Override
	public Stage getPrimaryStage() {
		return primaryStage;
	}

}
