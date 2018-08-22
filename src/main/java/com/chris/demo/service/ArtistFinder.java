package com.chris.demo.service;

import static com.chris.demo.service.HttpUtils.doGet;

import java.io.IOException;

import com.chris.demo.model.Artist;

public class ArtistFinder {
	private static final String LASTFM_KEY = "1742b8af939810b56dea71f6f060208d";
	
	private static final String ARTISTINFO_QUERY = "http://ws.audioscrobbler.com/2.0/"//
			+ "?method=artist.getInfo"//
			+ "&artist=%s"//
			+ "&autocorrect=1"//
			+ "&format=json"
			+ "&api_key=" + LASTFM_KEY;
	
	public static Artist getArtistInfo(String artistName) throws IOException {
		String requestQuery = String.format(ARTISTINFO_QUERY, artistName);
		return LastFmParser.parseArtistInfo(doGet(requestQuery));
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(getArtistInfo("Nirvana"));
	}

}
