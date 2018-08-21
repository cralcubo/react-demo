package com.chris.demo.view;

import java.util.Arrays;
import java.util.List;

import com.chris.demo.model.Album;

public class AlbumsMock {

	public static List<Album> loadAlbums() {
		Album a1 = new Album.Builder()//
				.artistName("Nirvana")//
				.name("In Utero")//
				.coverUrl("https://www.junerecords.com/images/product/v/vinyl-rock-and-pop-alternative-rock-nirvana-in-utero-(2013-m-256px-256px.jpg")//
				.build();
		Album a2 = new Album.Builder()//
				.artistName("Metallica")//
				.name("Ride the Lightning")//
				.coverUrl("https://vignette.wikia.nocookie.net/rockband/images/5/59/Ride_the_Lightning.png/revision/latest?cb=20130201005152")//
				.build();

		return Arrays.asList(a1, a2);

	}

}
