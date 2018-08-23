package com.chris.demo.model;

import java.util.function.Function;

public class Wiki {

	private final String summary;
	private final String content;

	private Wiki(Builder b) {
		this.summary = b.summary;
		this.content = b.content;
	}
	
	public String getSummary() {
		return summary;
	}
	
	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return "Wiki [summary=" + summary + ", content=" + content + "]";
	}

	public static class Builder {
		private Function<String, String> nullIsEmpty = s -> (s == null) ? "" : s;
		private String summary;
		private String content;

		public Builder summary(String summary) {
			this.summary = nullIsEmpty.apply(summary);
			return this;
		}

		public Builder content(String content) {
			this.content = nullIsEmpty.apply(content);
			return this;
		}

		public Wiki build() {
			return new Wiki(this);
		}

	}

}
