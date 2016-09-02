package com.example.dcrelling.guardian.ui;

import com.example.dcrelling.guardian.services.GuardianService;

/**
 * Created by dcrelling on 8/21/16.
 */

public interface GuardianPresenter
{

  void loadDefaultArticles();


  void onDestroy();


  void loadArticlesByApi(GuardianService.ApiType apiType);

}
