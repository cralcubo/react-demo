package com.chris.demo.service;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.chris.demo.mapping.AlbumInfoMapper;
import com.chris.demo.mapping.ArtistInfoMapper;
import com.chris.demo.mapping.LastFmAlbum;
import com.chris.demo.mapping.LastFmImage;
import com.chris.demo.mapping.TopAlbumMapper;
import com.chris.demo.mapping.LastFmAlbum.Tracks;
import com.chris.demo.model.Album;
import com.chris.demo.model.Artist;
import com.google.gson.Gson;

import io.reactivex.Observable;

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
	private static final String IMAGE_SIZE = "extralarge";

	public static Observable<Album> parseSearchAlbum(String jsonResponse) {
		System.out.println(".:. Response:" + jsonResponse);
		return Observable.just(jsonResponse)//
				.map(json -> gsonParser.fromJson(json, TopAlbumMapper.class))//
				.map(mapper -> mapper.topalbums)//
				.filter(Objects::nonNull)//
				.flatMap(albums -> Observable.<LastFmAlbum>create(emitter -> {
					albums.album.stream()//
							.filter(Objects::nonNull)//
							.forEach(emitter::onNext);
					emitter.onComplete();
				}))//
				.map(LastFmParser::convertLastFmAlbumToAlbum);
	}

	public static Album parseAlbumInfo(String jsonResponse) {
		System.out.println(".:. Response:" + jsonResponse);
		return ofNullable(gsonParser.fromJson(jsonResponse, AlbumInfoMapper.class))//
				.map(mapper -> mapper.album)//
				.filter(Objects::nonNull)//
				.map(LastFmParser::convertLastFmAlbumToAlbum)//
				.orElse(null);
	}

	public static Artist parseArtistInfo(String jsonResp) {
		System.out.println(".:. Response:" + jsonResp);
		ArtistInfoMapper fromJson = gsonParser.fromJson(jsonResp, ArtistInfoMapper.class);
		System.out.println(fromJson);
		return ofNullable(gsonParser.fromJson(jsonResp, ArtistInfoMapper.class))//
				.map(mapper -> mapper.artist)//
				.filter(Objects::nonNull)
				.map(artist -> new Artist.Builder()//
						.name(artist.name)//
						.wiki(ofNullable(artist.bio)//
								.map(b -> new com.chris.demo.model.Wiki.Builder()//
										.content(b.content)//
										.summary(b.summary)//
										.build())//
								.orElse(null))//
						.pictureUrl(findCoverUrl(artist.image))//
						.build())
				.orElse(null);
	}

	private static Album convertLastFmAlbumToAlbum(LastFmAlbum album) {
		// No track info
		return new Album.Builder()//
				.name(album.name)//
				.coverUrl(findCoverUrl(album.image))//
				.tracks(parseTracks(album.tracks))//
				.wiki(ofNullable(album.wiki).map(w -> new com.chris.demo.model.Wiki.Builder()//
						.content(w.content)//
						.summary(w.summary)//
						.build())//
						.orElse(null))//
				.build();
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
			if (i.size.equals(IMAGE_SIZE)) {
				return imageCleaner(i.url);
			}
		}
		return null;
	}

	private static String imageCleaner(String imageUrl) {
		return imageUrl != null ? imageUrl.replaceAll("\\s+", "") : null;
	}
}
