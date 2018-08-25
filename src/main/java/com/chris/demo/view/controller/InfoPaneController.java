package com.chris.demo.view.controller;

import static java.util.Optional.ofNullable;

import com.chris.demo.model.Artist;
import com.chris.demo.model.Wiki;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class InfoPaneController implements Controllable {
	private static final String defaultImage = "http://mikestratton.net/images/java_duke.png";

	private ImageView imagePane;
	private TextFlow wikiPane;

	public InfoPaneController(ImageView imagePane, TextFlow wikiPane) {
		this.imagePane = imagePane;
		this.wikiPane = wikiPane;
	}

	@Override
	public void initialize() {
		// clear image pane
	}
	
	public void loadDefaultArtist(String artistName) {
		imagePane.setImage(new Image(defaultImage));
		wikiPane.getChildren().clear();
		wikiPane.getChildren().add(new Text("The Artist:" + artistName + " could not be found."));
	}

	public void updateArtist(Artist artist) {
		// upate image
		ofNullable(artist.getPictureUrl())//
				.filter(url -> url.trim().matches("^https?:\\/\\/.+$"))//
				.map(url -> new Image(url))//
				.ifPresent(imagePane::setImage);

		// update text info
		ofNullable(artist.getWiki())//
				.map(Wiki::getContent)//
				.map(Text::new)//
				.ifPresent(text -> wikiPane.getChildren().add(text));
	}

	@Override
	public void clear() {
		imagePane.setImage(null);
		wikiPane.getChildren().clear();
	}

}
