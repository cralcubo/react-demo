package com.chris.demo;

import com.chris.demo.view.RootLayoutInitializer;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
	private RootLayoutInitializer rootLayoutInitializer;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		rootLayoutInitializer = new RootLayoutInitializer(primaryStage);
		rootLayoutInitializer.initialize();
		
		rootLayoutInitializer.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
