package com.example.dcrelling.guardian.ui;

import com.example.dcrelling.guardian.services.GuardianService;

/**
 * Created by dcrelling on 8/21/16.
 */

public interface GuardianView
{
  void onDisplayArticleList();


  void onShowProgress();


  void onDropProgress();

  void onError(String message, GuardianService.ApiType apiType);
}
