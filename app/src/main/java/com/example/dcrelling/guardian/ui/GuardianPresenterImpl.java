package com.example.dcrelling.guardian.ui;

import java.util.Map;

import com.example.dcrelling.guardian.factories.ParametersFactory;
import com.example.dcrelling.guardian.factories.ServiceFactory;
import com.example.dcrelling.guardian.services.GuardianArticleResponse;
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
  private ParametersFactory _articleSearchParametersFactory;


  public GuardianPresenterImpl(GuardianView view, GuardianModel model)
  {
    _view = view;
    _model = model;
  }


  @Override
  public void initialize()
  {
    _guardianService = ServiceFactory.getInstance().createService(GuardianService.class, GuardianService.BASE_URL);
    _articleSearchParametersFactory = new ParametersFactory();
  }


  @Override
  public void loadDefaultArticles()
  {
    loadArticles(GuardianService.ApiType.SEARCH);
  }


  @Override
  public void loadArticles(GuardianService.ApiType apiType)
  {
    _view.onShowProgress();
    Map<String, String> params = _articleSearchParametersFactory.getParameters(apiType);
    _guardianService.getArticles(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<GuardianArticleResponse>()
    {
      @Override
      public void call(GuardianArticleResponse articleResponse)
      {
        _model.setArticleList(articleResponse.getResponse().getArticleList());
        _view.onDropProgress();
        _view.onDisplayArticleList();
      }
    });
  }


  @Override
  public void onDestroy()
  {
    _model = null;
    _view = null;
  }
}
