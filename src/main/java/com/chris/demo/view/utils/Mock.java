package com.chris.demo.view.utils;

import java.util.Arrays;
import java.util.List;

import com.chris.demo.model.Album;
import com.chris.demo.model.Artist;
import com.chris.demo.model.SearchAlbumEntity;
import com.chris.demo.model.Wiki;

public class Mock {
	
	public static SearchAlbumEntity loadEntity() {
		Artist artist = new Artist.Builder()
				.name("Nirvana")
				.pictureUrl("http://dazedimg.dazedgroup.netdna-cdn.com/786/azure/dazed-prod/1220/9/1229141.jpg")
				.wiki(new Wiki.Builder()
						.content("This is a mocked content")
						.summary("This is a summary =D")
						.build())
				.build();
		
		return new SearchAlbumEntity(loadAlbums(), artist);
	}
	
	public static Artist loadArtist() {
		return new Artist.Builder()
		.name("Nirvana")
		.pictureUrl("http://dazedimg.dazedgroup.netdna-cdn.com/786/azure/dazed-prod/1220/9/1229141.jpg")
		.wiki(new Wiki.Builder()
				.content("This is a mocked content")
				.summary("This is a summary =D")
				.build())
		.build();
	}

	public static List<Album> loadAlbums() {
		Album a1 = new Album.Builder()//
				.wiki(new Wiki.Builder()//
						.content("This is a mocked content")
						.summary("This is a summary =D")
						.build())
				.artistName("Nirvana")//
				.name("In Utero")//
				.coverUrl("https://www.junerecords.com/images/product/v/vinyl-rock-and-pop-alternative-rock-nirvana-in-utero-(2013-m-256px-256px.jpg")//
				.build();
		Album a2 = new Album.Builder()//
				.wiki(new Wiki.Builder()//
						.content("This is a mocked content")
						.summary("This is a summary =D")
						.build())//
				.artistName("Metallica")//
				.name("Ride the Lightning")//
				.coverUrl("https://vignette.wikia.nocookie.net/rockband/images/5/59/Ride_the_Lightning.png/revision/latest?cb=20130201005152")//
				.build();

		return Arrays.asList(a1, a2);
	}
	
	

}
