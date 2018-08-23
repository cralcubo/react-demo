package com.chris.demo.model;

import java.util.List;

public class SearchAlbumEntity {
	
	private final List<Album> albums;
	private final Artist artist;
	
	public SearchAlbumEntity(List<Album> albums, Artist artist) {
		this.albums = albums;
		this.artist = artist;
	}
	
	public List<Album> getAlbums() {
		return albums;
	}
	
	public Artist getArtist() {
		return artist;
	}

}
