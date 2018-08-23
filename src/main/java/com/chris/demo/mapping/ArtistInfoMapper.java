package com.chris.demo.mapping;

import java.util.List;

public class ArtistInfoMapper {
	public Artist artist;

	public static class Artist {
		public String name;
		public List<LastFmImage> image;
		public Bio bio;
	}

	public static class Bio {
		public String summary;
		public String content;
	}

}
