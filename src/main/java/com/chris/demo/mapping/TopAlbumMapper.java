package com.chris.demo.mapping;

import java.util.ArrayList;
import java.util.List;

public class TopAlbumMapper extends Errorable {
	public TopAlbums topalbums;

	public static class TopAlbums {
		public List<LastFmAlbum> album = new ArrayList<>();
	}
}
