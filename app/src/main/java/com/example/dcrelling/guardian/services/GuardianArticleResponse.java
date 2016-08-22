package com.example.dcrelling.guardian.services;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dcrelling on 8/21/16.
 */

public class GuardianArticleResponse
{
  public Response response;


  public Response getResponse()
  {
    return this.response;
  }


  public class Response
  {

    @SerializedName("results")
    List<Article> _articleList;


    public Response()
    {
      _articleList = new ArrayList<>();
    }


    public List<Article> getArticleList()
    {
      return _articleList;
    }

  }


  public class Article
  {
    public String id;
    public String type;
    public String sectionId;
    public String section;
    public String webPublicationDate;
    public String webTitle;
    public String webUrl;
    public String apiUrl;
  }


}
