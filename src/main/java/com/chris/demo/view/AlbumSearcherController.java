package com.chris.demo.view;

import java.util.List;

import com.chris.demo.model.Album;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class AlbumSearcherController {

	@FXML
	private TextField searchText;

	@FXML
	private AnchorPane viewer;

	@FXML
	private void initialize() {
		searchText.clear();
	}

	@FXML
	private void handleSearch() {
		String artist = searchText.getText();
		System.out.println("Search: " + artist);
		loadAlbums(AlbumsMock.loadAlbums());
		searchText.clear();
	}

	private void loadAlbums(List<Album> loadAlbums) {
		VBox box = new VBox();
		ScrollPane scrollPane = new ScrollPane();
		for (int i = 0; i < loadAlbums.size(); i++) {
			Album a = loadAlbums.get(i);
			ImageView view = new ImageView();
			view.setImage(new Image(a.getCoverUrl(), 100, 100, false, false));
			Label label = new Label();
			label.setText(a.getAlbumName());
			box.getChildren().add(view);			
			scrollPane.setContent(box);
		}
		viewer.getChildren().add(scrollPane);
	}

}
