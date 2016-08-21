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


  enum API
  {
    SEARCH("a8457610b68381085a3fff38d6a36337:6:74255139"),
    BOOKS("0108552e648ca00e60e0158320a61979:15:74255139"),
    COMMUNITY("7227600a59795c72dac910e8219140a7:7:74255139"),
    CONGRESS("a3ccd79fa9a7b117c2105500bbecd4e8:5:74255139"),
    GEO("b18f88f60a91226dc17e62178065e7e0:3:74255139"),
    MOST_POPULAR("001e7f19c6d0a256c82acef00b0029aa:9:74255139"),
    MOVIE_REVIEWS("b05c090ae54bc65d1921373a6717f048:14:74255139"),
    SEMANTIC("ea0ae2eee488612ac394a952b8366860:9:74255139"),
    TIMES_NEWSWIRE("6ef2b315b02cc50b44d2f7b3ae102bbe:7:74255139"),
    TIMES_TAG("8eadbda7bc90b5066cd7aec94f0b4ddc:6:74255139"),
    TOP_STORIES("340fe1949bbc2b893c4a336bb072412a:18:74255139");

    String apiKey;


    API(String apiKey)
    {
      this.apiKey = apiKey;
    }


    public String getApiKey()
    {
      return this.apiKey;
    }
  }

}
