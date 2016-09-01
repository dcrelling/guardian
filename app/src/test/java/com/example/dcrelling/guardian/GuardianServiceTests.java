package com.example.dcrelling.guardian;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.dcrelling.guardian.mocks.GuardianServiceMock;
import com.example.dcrelling.guardian.services.GuardianArticleResponse;
import com.example.dcrelling.guardian.services.GuardianService;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;
import rx.observers.TestSubscriber;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class GuardianServiceTests
{

  private final NetworkBehavior behavior = NetworkBehavior.create();
  private final rx.observers.TestSubscriber<GuardianArticleResponse> testSubscriber = TestSubscriber.create();
  private GuardianService mockGuardianService;


  @Before
  public void setUp()
  {
    Retrofit retrofit = new Retrofit.Builder()
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .baseUrl("http://example.com").build();

    MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit)
        .networkBehavior(behavior).build();

    final BehaviorDelegate<GuardianService> delegate = mockRetrofit.create(GuardianService.class);

    mockGuardianService = new GuardianServiceMock(delegate);
  }


  @Test
  public void testSuccessResponse()
  {
    givenNetworkFailurePercentIs(0);
    Map<String, String> queryParams = new HashMap<>();
    mockGuardianService.getArticles(queryParams).subscribe(testSubscriber);

    List<GuardianArticleResponse> responseList = testSubscriber.getOnNextEvents();
    testSubscriber.assertCompleted();
  }

  @Test
  public void testFailureResponse() throws Exception {
    givenNetworkFailurePercentIs(100);

    Map<String, String> queryParams = new HashMap<>();
    mockGuardianService.getArticles(queryParams).subscribe(testSubscriber);

    testSubscriber.assertNoValues();
    testSubscriber.assertError(IOException.class);
  }


  private void givenNetworkFailurePercentIs(int failurePercent)
  {
    behavior.setDelay(0, MILLISECONDS);
    behavior.setVariancePercent(0);
    behavior.setFailurePercent(failurePercent);
  }
}
