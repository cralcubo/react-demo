package com.chris.demo.model;

public class Album {
	private String songName;
	private String artistName;
	private String name;
	private String coverUrl;

	private Album(Builder builder) {
		this.name = builder.name;
		this.songName = builder.songName;
		this.artistName = builder.artistName;
		this.coverUrl = builder.coverUrl;
	}
	
	@Override
	public String toString() {
		return "Album [songName=" + songName + ", artistName=" + artistName + ", name=" + name + ", coverUrl="
				+ coverUrl + "]";
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
