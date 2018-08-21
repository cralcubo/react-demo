package com.chris.demo.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AlbumSearcher {
	private static final String FXML_PATH = "AlbumSearcher.fxml";
	
	private AnchorPane rootLayout;
	private final Stage primaryStage;
	
	public AlbumSearcher(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public void initialize() {
		try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AlbumSearcher.class.getResource(FXML_PATH));
            rootLayout = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void show() {
		primaryStage.show();
	}

}
