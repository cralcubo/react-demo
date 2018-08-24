package com.chris.demo.view.controller;

import com.chris.demo.model.SearchAlbumEntity;
import com.chris.demo.view.utils.Mock;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class SearchPaneController implements Controllable {
	private final SearchAlbumEntity mock = Mock.loadEntity();

	private final TextArea searchText;
	private final Button searchButton;

	public SearchPaneController(TextArea searchText, Button searchButton) {
		this.searchText = searchText;
		this.searchButton = searchButton;
	}

	@Override
	public void initialize() {
		searchText.clear();
	}
	
	SearchAlbumEntity searchAlbums() {
		System.out.println("Searching:" + searchText.getText());
		return mock;
	}

	void clearText() {
		searchText.clear();
	}

	@Override
	public void clear() {
		searchText.clear();
	}
	
	

}
