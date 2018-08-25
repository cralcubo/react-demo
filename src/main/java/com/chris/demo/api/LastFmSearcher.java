package com.chris.demo.api;

import com.chris.demo.model.Album;
import com.chris.demo.model.Artist;
import com.chris.demo.service.ReactiveAlbumFinder;
import com.chris.demo.service.ReactiveArtistFinder;

import io.reactivex.Observable;

public class LastFmSearcher implements Searcheable {

	@Override
	public Observable<Album> getAlbumInfo(String artistName, String albumName) {
		return ReactiveAlbumFinder.getAlbumInfo(artistName, albumName);
	}

	@Override
	public Observable<Artist> getArtistInfo(String artistName) {
		return ReactiveArtistFinder.getArtistInfo(artistName);
	}

	@Override
	public Observable<Album> getArtistAlbums(String artistName) {
		return ReactiveAlbumFinder.findAlbums(artistName);
	}

}
