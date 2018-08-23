package com.chris.demo.map;

import java.util.ArrayList;
import java.util.List;

public class LastFmAlbum {
	public String name;
	public String artist;
	public List<LastFmImage> image = new ArrayList<>();
	public Wiki wiki;
	public Tracks tracks;
	
	public static class Tracks {
		public List<Track> track = new ArrayList<>();
	} 
	
	public static class Track {
		public String name;
	}
	
	public static class Wiki {
		public String summary;
		public String content;
	}
}
