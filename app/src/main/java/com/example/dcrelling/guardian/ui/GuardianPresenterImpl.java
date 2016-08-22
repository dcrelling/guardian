package com.example.dcrelling.guardian.ui;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;
import com.example.dcrelling.guardian.factories.ServiceFactory;
import com.example.dcrelling.guardian.services.ArticleSearchResponse;
import com.example.dcrelling.guardian.services.GuardianService;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by dcrelling on 8/21/16.
 */

public class GuardianPresenterImpl implements GuardianPresenter
{

  private GuardianView _view;
  private GuardianModel _model;
  private GuardianService _guardianService;


  public GuardianPresenterImpl(GuardianView view, GuardianModel model)
  {
    _view = view;
    _model = model;
  }


  @Override
  public void initialize()
  {
    _guardianService = ServiceFactory.getInstance().createService(GuardianService.class, GuardianService.BASE_URL);

  }


  @Override
  public void loadDefaultArticles()
  {
    Map<String, String> params = new HashMap<String, String>();
    params.put("api-key", GuardianService.API_KEY);
    params.put("format", "json");
    params.put("from-date", "2016-08-21");
    params.put("page", "1");
    params.put("page-size", "10");

    _guardianService.searchArticles(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<ArticleSearchResponse>()
    {
      @Override
      public void call(ArticleSearchResponse articleSearchResponse)
      {
        Log.d("got here", "got here");
        _model.setArticleList(articleSearchResponse.getResponse().getArticleList());
        _view.onDisplayArticleList();
      }
    });
  }


  @Override
  public void onDestory()
  {
    _model = null;
    _view = null;
  }
}
