package com.chris.demo.model;

import static java.util.Collections.emptyList;

import java.util.List;

public class Album {
	private String songName;
	private String artistName;
	private String name;
	private String coverUrl;
	private List<String> tracks;
	private String info;

	private Album(Builder builder) {
		this.name = builder.name;
		this.songName = builder.songName;
		this.artistName = builder.artistName;
		this.coverUrl = builder.coverUrl;
		this.info = builder.info;
		this.tracks = builder.tracks;
	}

	@Override
	public String toString() {
		return "Album [artistName=" + artistName + ", name=" + name + ", coverUrl=" + coverUrl + ", tracks=" + tracks
				+ ", info=" + info + "]";
	}

	public String getSongName() {
		return songName;
	}
	
	public String getArtistName() {
		return artistName;
	}
	
	public String getAlbumName() {
		return name;
	}
	
	public String getCoverUrl() {
		return coverUrl;
	}

	public static class Builder {
		private String artistName;
		private String songName;
		private String name;
		private String coverUrl;
		private List<String> tracks;
		private String info;
		
		public Builder info(String val) {
			this.info = nullIsEmpty(val);
			return this;
		}
		
		public Builder tracks(List<String> val) {
			this.tracks = (val ==  null) ? emptyList() : val;
			return this;
		}

		public Builder name(String val) {
			name = nullIsEmpty(val);
			return this;
		}

		public Builder coverUrl(String val) {
			coverUrl = val;
			return this;
		}
		
		public Builder songName(String val) {
			songName = nullIsEmpty(val);
			return this;
		}
		
		public Builder artistName(String val) {
			artistName = nullIsEmpty(val);
			return this;
		}

		public Album build() {
			return new Album(this);
		}
		
		private String nullIsEmpty(String val) {
			return val == null ? "" : val;
		}
	}
}
