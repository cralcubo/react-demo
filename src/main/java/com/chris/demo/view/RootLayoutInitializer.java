package com.chris.demo.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RootLayoutInitializer {
	private static final String FXML_FILE = "RootLayout.fxml";
	
	private AnchorPane rootLayout;
	private final Stage primaryStage;
	
	public RootLayoutInitializer(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	
	public void initialize() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(FXML_FILE));
			rootLayout = loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Reactive Album Searcher");
			primaryStage.setResizable(false);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void show() {
		primaryStage.show();
		
	}

}
