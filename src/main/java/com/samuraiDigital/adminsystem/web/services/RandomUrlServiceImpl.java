package com.samuraiDigital.adminsystem.web.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.samuraiDigital.adminsystem.web.model.UrlDataModel;

@Service
public class RandomUrlServiceImpl implements RandomUrlService {

	private HashSet<String> urls = new HashSet<>();

	private HashMap<String, UrlDataModel> urlMap = new HashMap<>();

	@Override
	public String getRandomUrl() {

		String randomUrl = RandomStringUtils.random(32);

		while (urlExists(randomUrl)) {
			randomUrl = RandomStringUtils.random(32);
		}

		urls.add(randomUrl);
		return randomUrl;
	}

	@Override
	public boolean urlExists(String url) {
		return urls.contains(url);
	}

	@Override
	public void bindModelToUrl(String url, UrlDataModel model) {

		urlMap.put(url, model);

	}

	@Override
	public Optional<UrlDataModel> getModelFromUrl(String url) {
		return Optional.ofNullable(urlMap.get(url));
	}

	@Override
	public void RemoveDataModel(String url) {
		urlMap.remove(url);
	}

}
