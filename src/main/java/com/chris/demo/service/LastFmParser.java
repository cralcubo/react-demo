package com.chris.demo.service;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.chris.demo.map.AlbumInfoMapper;
import com.chris.demo.map.ArtistInfoMapper;
import com.chris.demo.map.LastFmAlbum;
import com.chris.demo.map.LastFmAlbum.Tracks;
import com.chris.demo.map.LastFmAlbum.Wiki;
import com.chris.demo.map.LastFmImage;
import com.chris.demo.map.TopAlbumMapper;
import com.chris.demo.model.Album;
import com.chris.demo.model.Artist;
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

		List<Album> allAlbums = albums.stream().map(LastFmParser::convertLastFmAlbumToAlbum)
				.collect(Collectors.toList());

		return allAlbums;
	}

	public static Album parseAlbumInfo(String jsonResponse) {
		AlbumInfoMapper albumInfo = gsonParser.fromJson(jsonResponse, AlbumInfoMapper.class);
		return convertLastFmAlbumToAlbum(albumInfo.album);
	}

	public static Artist parseArtistInfo(String json) {
		ArtistInfoMapper artistInfo = gsonParser.fromJson(json, ArtistInfoMapper.class);
		return convertLastFmArtist(artistInfo);
	}

	private static Artist convertLastFmArtist(ArtistInfoMapper artistInfo) {
		ArtistInfoMapper.Artist artist = artistInfo.artist;
		if (artist == null) {
			return null;
		}

		return new Artist.Builder()//
				.name(artist.name)//
				.bio(ofNullable(artist.bio).map(b -> b.summary).orElse(null))//
				.pictureUrl(findCoverUrl(artist.image))//
				.build();
	}

	private static Album convertLastFmAlbumToAlbum(LastFmAlbum album) {
		if (album == null) {
			return null;
		}
		// No track info
		return new Album.Builder()//
				.artistName(album.artist)//
				.name(album.name)//
				.coverUrl(findCoverUrl(album.image))//
				.tracks(parseTracks(album.tracks))//
				.info(parseWiki(album.wiki))//
				.build();
	}

	private static String parseWiki(Wiki wiki) {
		if (wiki == null) {
			return null;
		}
		return wiki.summary;
	}

	private static List<String> parseTracks(Tracks tracks) {
		if (tracks == null) {
			return Collections.emptyList();
		}
		return tracks.track.stream()//
				.map(t -> t.name)//
				.collect(toList());
	}

	private static String findCoverUrl(List<LastFmImage> images) {
		for (LastFmImage i : images) {
			if (i == null) {
				continue;
			}
			if (i.size.equals(LARGE_IMAGE)) {
				return imageCleaner(i.url);
			}
		}
		return null;
	}

	private static String imageCleaner(String imageUrl) {
		return imageUrl != null ? imageUrl.replaceAll("\\s+", "") : null;
	}
}
