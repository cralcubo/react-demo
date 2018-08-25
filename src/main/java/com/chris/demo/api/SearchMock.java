package com.chris.demo.api;

import java.util.List;

import com.chris.demo.model.Album;
import com.chris.demo.model.Artist;
import com.chris.demo.view.utils.Mock;

import io.reactivex.Observable;

public class SearchMock implements Searcheable {
	
	private final List<Album> albums = Mock.loadAlbums();
	private final Artist artist = Mock.loadArtist();

	@Override
	public Observable<Album> getAlbumInfo(String artistName, String albumName) {
		return Observable.just(albums.get(0));
	}

	@Override
	public Observable<Artist> getArtistInfo(String artistName) {
		return Observable.just(artist);
	}

	@Override
	public Observable<Album> getArtistAlbums(String artistName) {
		return Observable.create(emitter -> {
			albums.forEach(emitter::onNext);
			emitter.onComplete();
		});
	}

}
