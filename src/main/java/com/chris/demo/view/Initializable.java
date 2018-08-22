package com.chris.demo.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Initializable<T extends Parent> {
	
	protected T rootLayout;
	
	public void initialize() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Initializable.class.getResource(getLocationFile()));
			rootLayout = (T) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			getPrimaryStage().setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void show() {
		getPrimaryStage().show();
	}
	
	public abstract String getLocationFile();
	
	public abstract Stage getPrimaryStage();

}
