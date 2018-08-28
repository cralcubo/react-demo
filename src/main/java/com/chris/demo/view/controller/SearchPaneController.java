package com.chris.demo.view.controller;

import com.chris.demo.api.Searcheable;
import com.chris.demo.model.Album;
import com.chris.demo.model.Artist;
import com.chris.demo.model.Streamable;

import io.reactivex.Observable;
import javafx.scene.control.TextArea;

public class SearchPaneController implements Controllable {

	private final TextArea searchText;
	private Searcheable searcher;

	public SearchPaneController(TextArea searchText, Searcheable searcher) {
		this.searchText = searchText;
		this.searcher = searcher;
	}

	@Override
	public void initialize() {
		searchText.clear();
	}

	public void clearText() {
		searchText.clear();
	}

	@Override
	public void clear() {
		searchText.clear();
	}

	public Observable<Artist> searchArtist() {
		return searcher.getArtistInfo(searchText.getText());
	}

	public Observable<Album> searchAlbums() {
		return searcher.getArtistAlbums(searchText.getText());
	}

	public Observable<Streamable> returnAllSearchedInfo() {
		return searcher.getSearchedEntities(searchText.getText());
	}

}
