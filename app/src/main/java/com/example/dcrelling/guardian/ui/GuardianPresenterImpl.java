package com.example.dcrelling.guardian.ui;

import java.io.IOException;
import java.util.Map;

import android.util.Log;
import com.example.dcrelling.guardian.factories.ParametersFactory;
import com.example.dcrelling.guardian.services.GuardianArticleResponse;
import com.example.dcrelling.guardian.services.GuardianService;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by dcrelling on 8/21/16.
 */

public class GuardianPresenterImpl implements GuardianPresenter
{

  private GuardianView _view;
  private GuardianModel _model;
  private GuardianService _guardianService;
  private ParametersFactory _articleSearchParametersFactory;
  private Observable.Transformer _transformer;
  private String TAG = GuardianPresenterImpl.class.getName();


  public GuardianPresenterImpl(GuardianView view, GuardianModel model, GuardianService guardianService, ParametersFactory parametersFactory, Observable.Transformer transformer)
  {
    _view = view;
    _model = model;
    _guardianService = guardianService;
    _articleSearchParametersFactory = parametersFactory;
    _transformer = transformer;
  }


  @Override
  public void loadDefaultArticles()
  {
    loadArticlesByApi(GuardianService.ApiType.SEARCH);
  }


  @Override
  public void loadArticlesByApi(final GuardianService.ApiType apiType)
  {
    Map<String, String> params = _articleSearchParametersFactory.getParameters(apiType);
    loadArticles(apiType, params);
  }

  //todo this section needs to be refactored. The processing of the response can happen in another class. we are directly using the retrofit
  //GuardianService class instead we shoudl wrap that class with a NetworkService class or something that can intercept the response from retrofit and so some processing
  //to the response checking for response errors and such. Also that would allow the presenter to get the Observable and cache it so that when the orientation of the device changes
  //we can unsubscribe then resubscribe. This should be the next refactor.
  private void loadArticles(final GuardianService.ApiType apiType, Map<String, String> params)
  {
    _view.onShowProgress();
    Observable<GuardianArticleResponse> observable;
    observable = _guardianService.getArticles(params);
    observable.compose(_transformer).subscribe(new Action1<GuardianArticleResponse>()
    {
      @Override
      public void call(GuardianArticleResponse articleResponse)
      {
        processSuccessfulResponse(articleResponse);

      }


      private void processSuccessfulResponse(GuardianArticleResponse articleResponse)
      {
        if (_view != null)
        {
          if (articleResponse.getResponse() != null && (articleResponse.getResponse().getArticleList() != null || !articleResponse.getResponse().getArticleList().isEmpty()))
          {
            _model.setArticleList(articleResponse.getResponse().getArticleList());
            _view.onDropProgress();
            _view.onDisplayArticleList();
          }
          else
          {
            _view.onError(GuardianService.Errors.HTTP.getMsg(), apiType);
          }
        }
        else
        {
          Log.e(TAG, "view is null");
        }
      }
    }, new Action1<Throwable>()
    {
      @Override
      public void call(Throwable error)
      {
        processError(error);
      }


      private void processError(Throwable error)
      {
        String errorMsg;
        if (error instanceof HttpException)
        {
          HttpException exception = (HttpException) error;
          Log.e(TAG, exception.getMessage());
          errorMsg = GuardianService.Errors.HTTP.getMsg();
        }
        else if (error instanceof IOException)
        {
          IOException exception = (IOException) error;
          Log.e(TAG, exception.getMessage());
          errorMsg = GuardianService.Errors.IO.getMsg();
        }
        else
        {
          errorMsg = GuardianService.Errors.UNKNOWN.getMsg();
        }

        if (_view != null)
        {
          _view.onError(errorMsg, apiType);
        }
        else
        {
          Log.e(TAG, "view is null");
        }
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
