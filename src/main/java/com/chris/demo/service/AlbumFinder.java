package com.chris.demo.service;

import static com.chris.demo.service.HttpUtils.doGet;

import java.io.IOException;
import java.util.List;

import com.chris.demo.model.Album;

public class AlbumFinder {
	private static final String LASTFM_KEY = "1742b8af939810b56dea71f6f060208d";
	
	private static final String SEARCHALBUM_QUERY = "http://ws.audioscrobbler.com/2.0/"//
			+ "?method=artist.gettopalbums"//
			+ "&artist=%s"//
			+ "&format=json"
			+ "&api_key=" + LASTFM_KEY;
	
	private static final String ALBUMINFO_QUERY = "http://ws.audioscrobbler.com/2.0/"//
			+ "?method=album.getInfo"//
			+ "&album=%s"//
			+ "&artist=%s"//
			+ "&autocorrect=1"//
			+ "&format=json"
			+ "&api_key=" + LASTFM_KEY;
	
	public List<Album> findAlbum(String artistName) throws IOException {
		String requestQuery = String.format(SEARCHALBUM_QUERY, artistName);
		return LastFmParser.parseSearchAlbum(doGet(requestQuery));
	}
	
	public Album getAlbumInfo(String artist, String albumName) throws IOException {
		String requestQuery = String.format(ALBUMINFO_QUERY, albumName, artist);
		return LastFmParser.parseAlbumInfo(doGet(requestQuery));
	}
	
	public static void main(String[] args) throws IOException {
		AlbumFinder af = new AlbumFinder();
		System.out.println(af.getAlbumInfo("Nirvana", "Nevermind"));
	}
}
