package com.chris.demo;

import com.chris.demo.view.AlbumSearcher;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
	private AlbumSearcher searcher;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		searcher = new AlbumSearcher(primaryStage);
		searcher.initialize();
		
		searcher.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
