package com.chris.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.chris.demo.map.LastFmAlbum;
import com.chris.demo.map.TopAlbumMapper;
import com.chris.demo.model.Album;
import com.chris.demo.model.LastFmImage;
import com.google.gson.Gson;



/**
 * Parse a LastFm json response to create a Pimped Radio Album. <br/>
 * The conditions to create an album are:
 * <ul>
 * <li>Album must have a name</li>
 * <li>Album must have a valid cover art</li>
 * </ul>
 * A valid cover art must have at least a large picture.
 * 
 * @author croman
 *
 */
public class LastFmParser {
	
	private static final Gson gsonParser = new Gson();
	private static final String LARGE_IMAGE = "extralarge";
	
	public static List<Album> parseSearchAlbum(String jsonResponse) {
		TopAlbumMapper albumMapper = gsonParser.fromJson(jsonResponse, TopAlbumMapper.class);
		List<LastFmAlbum> albums = Optional.ofNullable(albumMapper.topalbums)//
				.map(topAlbum -> topAlbum.album)//
				.orElseGet(() -> Collections.emptyList());
		
		List<Album> allAlbums = albums.stream()
									  .map(LastFmParser::convertLastFmAlbumToAlbum)
									  .collect(Collectors.toList());
		
		return allAlbums;
	}
	
	private static Album convertLastFmAlbumToAlbum(LastFmAlbum album) {
		if(album == null) {
			return null;
		}
		// No track info
		return new Album.Builder()
						.name(album.name)
						.coverUrl(findCoverUrl(album.image))
						.build();
	}
	
	private static String findCoverUrl(List<LastFmImage> images) {
		for (LastFmImage i : images) {
			if (i == null) {
				continue;
			}
			if(i.size.equals(LARGE_IMAGE)) {
				return imageCleaner(i.url);
			}
		}
		return null;
	}
	
	private static String imageCleaner(String imageUrl) {
		return imageUrl != null ? imageUrl.replaceAll("\\s+", "") : null;
	}
}
