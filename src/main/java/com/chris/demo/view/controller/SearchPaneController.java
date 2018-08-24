package com.chris.demo.view.controller;

import com.chris.demo.model.Album;
import com.chris.demo.model.Artist;
import com.chris.demo.model.SearchAlbumEntity;
import com.chris.demo.service.ReactiveAlbumFinder;
import com.chris.demo.service.ReactiveArtistFinder;
import com.chris.demo.view.utils.Mock;

import io.reactivex.Observable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class SearchPaneController implements Controllable {
	private final SearchAlbumEntity mock = Mock.loadEntity();

	private final TextArea searchText;

	public SearchPaneController(TextArea searchText, Button searchButton) {
		this.searchText = searchText;
	}

	@Override
	public void initialize() {
		searchText.clear();
	}
	
	Observable<Album> searchAlbums() {
		return ReactiveAlbumFinder.findAlbums(searchText.getText());
	}

	void clearText() {
		searchText.clear();
	}

	@Override
	public void clear() {
		searchText.clear();
	}

	public Observable<Artist> searchArtist() {
		return ReactiveArtistFinder.getArtistInfo(searchText.getText());
	}
	
	

}
