package com.chris.demo.model;

public class Artist {
	private String name;
	private String pictureUrl;
	private Wiki wiki;
	

	private Artist(Builder b) {
		this.name = b.name;
		this.pictureUrl = b.pictureUrl;
		this.wiki = b.wiki;
	}

	public String getName() {
		return name;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public Wiki getWiki() {
		return wiki;
	}

	@Override
	public String toString() {
		return "Artist [name=" + name + ", pictureUrl=" + pictureUrl + ", wiki=" + wiki + "]";
	}

	public static class Builder {
		private String name;
		private String pictureUrl;
		private Wiki wiki;

		public Builder name(String val) {
			name = nullIsEmpty(val);
			return this;
		}

		public Builder pictureUrl(String val) {
			pictureUrl = nullIsEmpty(val);
			return this;
		}

		public Builder wiki(Wiki wiki) {
			this.wiki = wiki;
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
