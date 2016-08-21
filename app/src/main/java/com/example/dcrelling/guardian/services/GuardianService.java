package com.example.dcrelling.guardian.services;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by dcrelling on 8/21/16.
 */

public interface GuardianService
{
  String BASE_URL = "http://content.guardianapis.com";
  String API_KEY = "b6d0b5ee-b57e-4a5b-8fc7-a0880bf79d38";


  @GET("/search")
  Observable<ArticleSearchResponse> searchArticles(
      @QueryMap Map<String, String> queryParams
  );

}
