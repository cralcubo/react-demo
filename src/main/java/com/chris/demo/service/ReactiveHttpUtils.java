package com.chris.demo.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import io.reactivex.Observable;

public class ReactiveHttpUtils {

	public static final String UTF_8 = StandardCharsets.UTF_8.name();

	private static final int CONNECTION_TIMEOUT_MS = 5 * 1000;

	private static final Pattern PARAMETERS_PATTERN = Pattern.compile("(?<==).+?(?=&\\w+=)");

	public static Observable<String> doGet(String url) {
		/**
		 * <code>
		 * Stream diagram
		 * encoded stream:   ------------encodedUrl-------------------|->
		 * 					 vvvvvvvvvvv flatMap(executeRequest) vvvvv 
		 * response stream:  ----------------jsonResp-----------------|->
		 * </code>
		 */
		return encodeParameters(url)//
				.flatMap(ReactiveHttpUtils::executeRequest);//
	}

	private static Observable<String> executeRequest(String url) throws ClientProtocolException {
		return Observable.create(emitter -> {
			HttpClient httpClient = HttpClientBuilder.create().build();

			// Timeout configurations
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(CONNECTION_TIMEOUT_MS) //
					.setConnectTimeout(CONNECTION_TIMEOUT_MS) //
					.setSocketTimeout(CONNECTION_TIMEOUT_MS) //
					.build();
			HttpGet get = new HttpGet(url);
			get.setConfig(requestConfig);
			try {
				String response = httpClient.execute(get, new MyResponseHandler());
				emitter.onNext(response);
				emitter.onComplete();
			} catch (IOException e) {
				emitter.onError(e);
			}
		});
	}

	private static class MyResponseHandler implements ResponseHandler<String> {

		public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
			int status = response.getStatusLine().getStatusCode();

			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				return entity != null ? EntityUtils.toString(entity, StandardCharsets.UTF_8) : "";
			} else {
				throw new ClientProtocolException("Unexpected status=" + status);
			}
		}

	}

	public static Observable<String> encodeParameters(String url) {
		String encodedUrl = url;
		Matcher m = PARAMETERS_PATTERN.matcher(url + "&end=");
		// UTF-8 encoding chartset
		while (m.find()) {
			try {
				String param = m.group();
				/*
				 * Percent-encode values according the RFC 3986. The built-in Java URLEncoder
				 * does not encode according to the RFC, so we make the extra replacements.
				 */
				String encParam = URLEncoder.encode(param, UTF_8)//
						.replace("+", "%20")//
						.replace("*", "%2A")//
						.replace("%7E", "~");
				encodedUrl = encodedUrl.replace(param, encParam);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(String
						.format("Totally unexpected, %s is supposed to be an accepted character encoding.", UTF_8), e);
			}
		}

		return Observable.just(encodedUrl);
	}

}