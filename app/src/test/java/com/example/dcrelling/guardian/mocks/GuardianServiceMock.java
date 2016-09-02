package com.example.dcrelling.guardian.mocks;

import java.util.Map;

import com.example.dcrelling.guardian.services.GuardianArticleResponse;
import com.example.dcrelling.guardian.services.GuardianService;
import com.google.gson.Gson;
import retrofit2.http.QueryMap;
import retrofit2.mock.BehaviorDelegate;
import rx.Observable;

/**
 * Created by dcrelling on 9/1/16.
 */

public class GuardianServiceMock implements GuardianService
{

  public static final String PATH = "path";

  private final BehaviorDelegate<GuardianService> _delegate;
  private Gson _gson = new Gson();
  private MockResponseGenerator _mockResponseGenerator;


  public GuardianServiceMock(BehaviorDelegate<GuardianService> delegate)
  {
    _delegate = delegate;
    _mockResponseGenerator = new MockResponseGenerator();
  }


  @Override
  public Observable<GuardianArticleResponse> getArticles(@QueryMap Map<String, String> queryParams)
  {
    GuardianArticleResponse response;
    if (queryParams.get(PATH) != null)
    {
      String json = _mockResponseGenerator.generate(queryParams.get(PATH));
      response = _gson.fromJson(json, GuardianArticleResponse.class);
    }
    else
    {
      response = new GuardianArticleResponse();
    }

    return _delegate.returningResponse(response).getArticles(queryParams);
  }

}
