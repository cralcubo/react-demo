package com.chris.demo.view.controller;

import java.util.List;

import com.chris.demo.model.Album;
import com.chris.demo.model.Artist;
import com.chris.demo.model.SearchAlbumEntity;

import io.reactivex.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;

public class RootLayoutController implements Controllable {
	
	// List here all the fxml elements to be controlled
	/*
	 * Search Pane
	 */
	@FXML
	private TextArea searchText;
	@FXML
	private Button searchButton;
	/*
	 * Info Pane
	 */
	@FXML
	private ImageView imagePane;
	@FXML
	private TextFlow wikiPane;
	/*
	 * Albums Pane
	 */
	@FXML
	private AnchorPane albumsPane;
	
	// Controllers
	private SearchPaneController searchPaneController;
	private InfoPaneController infoPaneController;
	private AlbumsPaneController albumsPaneController;
	
	@FXML
	public void initialize() {
		searchPaneController = new SearchPaneController(searchText, searchButton);
		searchPaneController.initialize();
		
		infoPaneController = new InfoPaneController(imagePane, wikiPane);
		infoPaneController.initialize();
		
		albumsPaneController = new AlbumsPaneController(albumsPane);
		albumsPaneController.initialize();
	}
	
	/*
	 * Search Pane Actions
	 */
	@FXML
	private void searchAction() {
		SearchAlbumEntity entity = searchPaneController.searchAlbums();
		// Handle artist
		updateArtist(entity.getArtist());
		// Albums
		updateAlbums(entity.getAlbums());
	}
	
	@FXML
	private void clearTextAction() {
		searchPaneController.clearText();
	}
	
	
	private void updateAlbums(List<Album> albums) {
		// TODO Auto-generated method stub
		
	}

	private void updateArtist(Artist artist) {
		infoPaneController.updateArtist(artist);
	}

	
	

}
