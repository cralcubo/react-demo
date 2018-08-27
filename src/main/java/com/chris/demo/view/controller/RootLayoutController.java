package com.chris.demo.view.controller;

import java.util.ArrayList;
import java.util.List;

import com.chris.demo.api.LastFmSearcher;
import com.chris.demo.api.Searcheable;
import com.chris.demo.model.Album;
import com.chris.demo.model.Artist;
import com.chris.demo.model.Wiki;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
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

	// private final Searcheable searcher = new SearchMock();
	private final Searcheable searcher = new LastFmSearcher();

	private List<Controllable> controllers = new ArrayList<>();

	@FXML
	public void initialize() {
		searchPaneController = new SearchPaneController(searchText, searcher);
		controllers.add(searchPaneController);
		infoPaneController = new InfoPaneController(imagePane, wikiPane);
		controllers.add(infoPaneController);
		albumsPaneController = new AlbumsPaneController(albumsPane);
		controllers.add(albumsPaneController);
		// initialize all sub-controllers
		controllers.forEach(Controllable::initialize);
	}

	/*
	 * Search Pane Actions
	 */
	@FXML
	private void searchAction() {
		String defaultImage = "http://mikestratton.net/images/java_duke.png";

		// Search Artist
		searchPaneController.searchArtist()//
				.defaultIfEmpty(new Artist.Builder().name("Unknown")//
						.pictureUrl(defaultImage)//
						.wiki(new Wiki.Builder()//
								.content("Artist not found")//
								.summary("Artist not found")//
								.build())
						.build())//
				.onErrorResumeNext(e -> {
					System.err.println("ERROR:" + e.getMessage());
					return Observable.empty();
				})//
				.observeOn(JavaFxScheduler.platform())//
				.subscribe(infoPaneController::updateArtist);

		// Search artist albums
		Observable<Album> albums = searchPaneController.searchAlbums();
		albumsPaneController.loadAlbums(albums);
	}

	@FXML
	private void clearTextAction() {
		clear();
	}

	@Override
	public void clear() {
		controllers.forEach(Controllable::clear);
	}

}
