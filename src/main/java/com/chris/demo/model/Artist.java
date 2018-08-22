package com.chris.demo.model;

public class Artist {
	private String name;
	private String pictureUrl;
	private String bio;

	private Artist(Builder b) {
		this.name = b.name;
		this.pictureUrl = b.pictureUrl;
		this.bio = b.bio;
	}

	public String getName() {
		return name;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public String getBio() {
		return bio;
	}

	@Override
	public String toString() {
		return "Artist [name=" + name + ", pictureUrl=" + pictureUrl + ", bio=" + bio.substring(0, 50) + "]";
	}

	public static class Builder {
		private String name;
		private String pictureUrl;
		private String bio;

		public Builder name(String val) {
			name = nullIsEmpty(val);
			return this;
		}

		public Builder pictureUrl(String val) {
			pictureUrl = nullIsEmpty(val);
			return this;
		}

		public Builder bio(String val) {
			bio = nullIsEmpty(val);
			return this;
		}
		
		public Artist build() {
			return new Artist(this);
		}

		private String nullIsEmpty(String val) {
			return val == null ? "" : val;
		}
	}

}
