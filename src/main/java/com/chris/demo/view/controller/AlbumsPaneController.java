package com.chris.demo.view.controller;

import java.util.Collections;
import java.util.List;

import com.chris.demo.model.Album;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class AlbumsPaneController implements Controllable {

	private AnchorPane albumsPane;

	public AlbumsPaneController(AnchorPane albumsPane) {
		this.albumsPane = albumsPane;
	}

	@Override
	public void initialize() {
		albumsPane.getChildren().clear();
	}

	@Override
	public void clear() {
		albumsPane.getChildren().clear();
	}

	public void loadAlbums(List<Album> albums) {
		FlowPane flow = new FlowPane();
		flow.setPadding(new Insets(5, 0, 5, 0));
	    flow.setVgap(4);
	    flow.setHgap(4);
	    flow.setPrefWrapLength(200); // preferred width allows for two columns
		
		albums.stream()//
				.map(AlbumPane::new)
				.map(AlbumPane::createPane)
				.forEach(p -> flow.getChildren().add(p));
		
		albumsPane.getChildren().add(flow);
	}

	private static class AlbumPane {
		private final String imageUrl;
		private final List<String> tracks;
		private final String albumInfo;

		public AlbumPane(Album a) {
			this.imageUrl = a.getCoverUrl();
			this.tracks = Collections.emptyList();
			this.albumInfo = a.getWiki().getSummary();
		}

		SplitPane createPane() {
			SplitPane splitPane = new SplitPane();
			splitPane.setPrefWidth(400);
			splitPane.setPrefHeight(200);
			
			AnchorPane leftAnchor = new AnchorPane();
			ImageView image = new ImageView(new Image(imageUrl, 200, 200, false, false));
			leftAnchor.getChildren().add(image);
			splitPane.getItems().add(leftAnchor);

			AnchorPane rightAnchor = new AnchorPane();
			ScrollPane scrollPane = new ScrollPane();
			scrollPane.setPrefWidth(200);
			rightAnchor.getChildren().add(scrollPane);
			
			AnchorPane infoAnchor = new AnchorPane();
			scrollPane.setContent(infoAnchor);
			TextFlow text = new TextFlow(new Text(albumInfo));
			infoAnchor.getChildren().add(text);
			splitPane.getItems().add(rightAnchor);

			return splitPane;
		}

	}

}
