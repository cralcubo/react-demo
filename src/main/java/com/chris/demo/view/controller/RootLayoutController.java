package com.chris.demo.view.controller;

import java.util.ArrayList;
import java.util.List;

import com.chris.demo.api.LastFmSearcher;
import com.chris.demo.api.Searcheable;
import com.chris.demo.model.Album;
import com.chris.demo.model.Artist;
import com.chris.demo.model.Streamable;
import com.chris.demo.model.Wiki;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;

public class RootLayoutController implements Controllable {
	private static final Artist ARTIST_NOT_FOUND = new Artist.Builder().name("Unknown")//
			.pictureUrl("http://mikestratton.net/images/java_duke.png")//
			.wiki(new Wiki.Builder()//
					.content("Artist not found")//
					.summary("Artist not found")//
					.build())
			.build();

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
		doChattySearch();
	}

	private void doChattySearch() {
		// Search Artist
		searchPaneController.searchArtist()//
				.defaultIfEmpty(ARTIST_NOT_FOUND)//
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

	private void doReservedSearch() {
		ConnectableObservable<Streamable> connectable = searchPaneController.returnAllSearchedInfo().publish();

		connectable.filter(Artist.class::isInstance)//
				.cast(Artist.class)//
				.observeOn(JavaFxScheduler.platform())//
				.subscribe(infoPaneController::updateArtist);

		albumsPaneController.loadAlbums(connectable.filter(Album.class::isInstance)//
				.cast(Album.class));

		connectable.connect();
	}

	private void doReservedSearch2() {
		searchPaneController.returnAllSearchedInfo()//
				.groupBy(e -> (e instanceof Album) ? "album" : "artist")//
				.observeOn(JavaFxScheduler.platform())//
				.subscribe(g -> {
					if (g.getKey().equals("album")) {
						albumsPaneController.loadAlbums(g.map(Album.class::cast));
					} else {
						g.map(Artist.class::cast)//
								.subscribe(infoPaneController::updateArtist);
					}
				});
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
