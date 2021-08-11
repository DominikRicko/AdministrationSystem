package com.samuraiDigital.adminsystem.web.services;

import java.util.Optional;

import com.samuraiDigital.adminsystem.web.model.UrlDataModel;

public interface RandomUrlService {

	public String getRandomUrl();

	public boolean urlExists(String url);

	public void bindModelToUrl(String url, UrlDataModel model);

	public Optional<UrlDataModel> getModelFromUrl(String url);

	public void removeDataModel(String url);

}
