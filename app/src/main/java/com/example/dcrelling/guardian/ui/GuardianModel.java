package com.example.dcrelling.guardian.ui;

import java.util.List;

import com.example.dcrelling.guardian.services.ArticleSearchResponse;

/**
 * Created by dcrelling on 8/21/16.
 */

public class GuardianModel
{
  private List<ArticleSearchResponse.Article> _articleList;


  public List<ArticleSearchResponse.Article> getArticleList()
  {
    return _articleList;
  }


  public void setArticleList(List<ArticleSearchResponse.Article> articleList)
  {
    _articleList = articleList;
  }

}
