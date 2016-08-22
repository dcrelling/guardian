package com.example.dcrelling.guardian.services;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by dcrelling on 8/21/16.
 */

public interface GuardianService
{
  String BASE_URL = "http://content.guardianapis.com";
  String API_KEY = "b6d0b5ee-b57e-4a5b-8fc7-a0880bf79d38";


  @GET("/{path}")
  Observable<GuardianArticleResponse> getArticles(
      @Path("path") String path, @QueryMap Map<String, String> queryParams
  );


  enum ApiType
  {
    CONTENT("search"),
    SECTION("section");

    private String path;


    ApiType(String path)
    {
      this.path = path;
    }


    public String getPath()
    {
      return path;
    }
  }

}
