package com.chris.demo.service;

import java.io.IOException;

import com.chris.demo.model.Album;

import io.reactivex.Observable;

public class ReactiveAlbumFinder {
	private static final String LASTFM_KEY = "1742b8af939810b56dea71f6f060208d";

	private static final String SEARCHALBUM_QUERY = "http://ws.audioscrobbler.com/2.0/"//
			+ "?method=artist.gettopalbums"//
			+ "&artist=%s"//
			+ "&format=json" + "&api_key=" + LASTFM_KEY;

	private static final String ALBUMINFO_QUERY = "http://ws.audioscrobbler.com/2.0/"//
			+ "?method=album.getInfo"//
			+ "&album=%s"//
			+ "&artist=%s"//
			+ "&autocorrect=1"//
			+ "&format=json" + "&api_key=" + LASTFM_KEY;

	public static Observable<Album> findAlbums(String artistName) throws IOException {
		return Observable.just(String.format(SEARCHALBUM_QUERY, artistName))//
				.flatMap(ReactiveHttpUtils::doGet)//
				.flatMap(LastFmParser::parseSearchAlbum);
	}

	public static Observable<Album> getAlbumInfo(String artist, String albumName) throws IOException {
		return Observable.just(String.format(ALBUMINFO_QUERY, albumName, artist))//
				.flatMap(ReactiveHttpUtils::doGet)//
				.map(LastFmParser::parseAlbumInfo);
	}
}
