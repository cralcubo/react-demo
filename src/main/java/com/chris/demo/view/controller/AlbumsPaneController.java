package com.chris.demo.view.controller;

import java.util.Objects;

import com.chris.demo.model.Album;

import io.reactivex.Observable;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

public class AlbumsPaneController implements Controllable {

	private AnchorPane albumsPane;
	private FlowPane flowPane;

	public AlbumsPaneController(AnchorPane albumsPane) {
		this.albumsPane = albumsPane;
	}

	@Override
	public void initialize() {
		flowPane = new FlowPane();
		flowPane.setPadding(new Insets(5, 0, 5, 0));
		flowPane.setVgap(4);
		flowPane.setHgap(4);
		flowPane.setPrefWrapLength(400); // preferred width allows for two columns
	}

	@Override
	public void clear() {
		albumsPane.getChildren().clear();
		flowPane.getChildren().clear();
	}

	public void loadAlbums(Observable<Album> albums) {
		albums.filter(Objects::nonNull)//
				.map(Album::getCoverUrl)//
				.filter(Objects::nonNull)//
				.filter(url -> url.trim().matches("^https?:\\/\\/.+$"))//
				.map(imageUrl -> new ImageView(new Image(imageUrl, 300, 300, false, false)))//
				.take(4)//
				.subscribe(image -> {flowPane.getChildren().add(image);},// 
						e -> System.out.println(e.getMessage()));
		
		albumsPane.getChildren().add(flowPane);
	}

}
