package com.chris.demo.mapping;

import java.util.ArrayList;
import java.util.List;

public class TopAlbumMapper {
	public TopAlbums topalbums;

	public static class TopAlbums {
		public List<LastFmAlbum> album = new ArrayList<>();
	}
}
