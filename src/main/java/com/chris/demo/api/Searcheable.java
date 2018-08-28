package com.chris.demo.api;

import com.chris.demo.model.Album;
import com.chris.demo.model.Artist;
import com.chris.demo.model.Streamable;

import io.reactivex.Observable;

public interface Searcheable {

	Observable<Album> getAlbumInfo(String artistName, String albumName);

	Observable<Artist> getArtistInfo(String artistName);

	Observable<Album> getArtistAlbums(String artistName);

	default Observable<Streamable> getSearchedEntities(String artistName) {
		return getArtistInfo(artistName)//
				.map(Streamable.class::cast)//
				.mergeWith(getArtistAlbums(artistName)//
						.map(Streamable.class::cast));
	}

}
