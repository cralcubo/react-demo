package com.chris.demo.service;

import com.chris.demo.model.Album;
import com.chris.demo.view.utils.PropertiesReader;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ReactiveAlbumFinder {
	private static final String LASTFM_KEY = PropertiesReader.get("api.key");

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

	public static Observable<Album> findAlbums(String artistName) {
		return Observable.just(String.format(SEARCHALBUM_QUERY, artistName))//
				.subscribeOn(Schedulers.io())//
				.flatMap(ReactiveHttpUtils::doGet)//
				.flatMap(LastFmParser::parseSearchAlbum);
	}

	public static Observable<Album> getAlbumInfo(String artist, String albumName) {
		return Observable.just(String.format(ALBUMINFO_QUERY, albumName, artist))//
				.subscribeOn(Schedulers.io())//
				.flatMap(ReactiveHttpUtils::doGet)//
				.flatMap(LastFmParser::parseAlbumInfo);
	}
}
