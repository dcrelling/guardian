package com.example.dcrelling.guardian.ui;

import java.util.List;

import com.example.dcrelling.guardian.services.GuardianArticleResponse;

/**
 * Created by dcrelling on 8/21/16.
 */

public class GuardianModel
{
  private List<GuardianArticleResponse.Article> _articleList;


  public List<GuardianArticleResponse.Article> getArticleList()
  {
    return _articleList;
  }


  public void setArticleList(List<GuardianArticleResponse.Article> articleList)
  {
    _articleList = articleList;
  }

}
