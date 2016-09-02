package com.example.dcrelling.guardian;

import java.util.HashMap;
import java.util.Map;

import com.example.dcrelling.guardian.factories.ParametersFactory;
import com.example.dcrelling.guardian.mocks.GuardianServiceMock;
import com.example.dcrelling.guardian.services.GuardianService;
import com.example.dcrelling.guardian.transformers.ObserveImmediately;
import com.example.dcrelling.guardian.ui.GuardianModel;
import com.example.dcrelling.guardian.ui.GuardianPresenter;
import com.example.dcrelling.guardian.ui.GuardianPresenterImpl;
import com.example.dcrelling.guardian.ui.GuardianView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by dcrelling on 9/1/16.
 */

@RunWith(MockitoJUnitRunner.class)
public class GuardianPresenterTests
{

  @Mock
  private GuardianView _view;
  @Mock
  private GuardianModel _model;
  @Mock
  private ParametersFactory _parametersFactory;

  private GuardianPresenter _presenter;

  private final NetworkBehavior behavior = NetworkBehavior.create();

  private GuardianService mockGuardianService;


  @Before
  public void setUp()
  {

    Retrofit retrofit = new Retrofit.Builder()
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .baseUrl("http://mock.com").build();

    MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit)
        .networkBehavior(behavior).build();

    final BehaviorDelegate<GuardianService> delegate = mockRetrofit.create(GuardianService.class);

    mockGuardianService = new GuardianServiceMock(delegate);
    _presenter = new GuardianPresenterImpl(_view, _model, mockGuardianService, _parametersFactory, new ObserveImmediately());

  }


  @Test
  public void testValidResponse()
  {
    Map<String, String> queryParams = new HashMap<>();
    queryParams.put(GuardianServiceMock.PATH, "valid_response.json");
    when(_parametersFactory.getParameters(any(GuardianService.ApiType.class))).thenReturn(queryParams);
    _presenter.loadDefaultArticles();
    verify(_view).onShowProgress();
    verify(_view).onDropProgress();
    verify(_view).onDisplayArticleList();
  }
  
}
