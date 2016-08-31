package com.example.dcrelling.guardian.services;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;


public interface GuardianService
{
  String BASE_URL = "http://content.guardianapis.com";
  String API_KEY = "b6d0b5ee-b57e-4a5b-8fc7-a0880bf79d38";


  @GET("/search")
  Observable<GuardianArticleResponse> getArticles(
      @QueryMap Map<String, String> queryParams
  );


  enum ApiType
  {
    SEARCH(""),
    US_NEWS("us-news"),
    MUSIC("music"),
    BUSINESS("business"),
    TECHNOLOGY("technology"),
    WORLD("world"),
    POLITICS("politics");


    ApiType(String query)
    {
      this.query = query;
    }


    private String query;


    public String getQuery()
    {
      return this.query;
    }


  }


  enum Errors
  {
    HTTP("No Articles Returned"),
    IO("Can't Connect To Server"),
    UNKNOWN("Something Went Wrong");

    private String msg;


    Errors(String msg)
    {
      this.msg = msg;
    }


    public String getMsg()
    {
      return msg;
    }
  }

}
