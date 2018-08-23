package com.chris.demo.service;

import com.chris.demo.model.Artist;

import io.reactivex.Observable;

public class ReactiveArtistFinder {
	private static final String LASTFM_KEY = "1742b8af939810b56dea71f6f060208d";

	private static final String ARTISTINFO_QUERY = "http://ws.audioscrobbler.com/2.0/"//
			+ "?method=artist.getInfo"//
			+ "&artist=%s"//
			+ "&autocorrect=1"//
			+ "&format=json" + "&api_key=" + LASTFM_KEY;

	public static Observable<Artist> getArtistInfo(String artistName) {
		return Observable.just(String.format(ARTISTINFO_QUERY, artistName))//
				.flatMap(ReactiveHttpUtils::doGet)//
				.map(LastFmParser::parseArtistInfo);
	}

}
