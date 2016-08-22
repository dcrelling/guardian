package com.example.dcrelling.guardian.ui;

import com.example.dcrelling.guardian.services.GuardianService;

/**
 * Created by dcrelling on 8/21/16.
 */

public interface GuardianPresenter
{
  void initialize();


  void loadDefaultArticles();


  void onDestroy();


  void loadArticles(GuardianService.ApiType apiType);

}
