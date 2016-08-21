package com.example.dcrelling.guardian.factories;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by dcrelling on 8/21/16.
 */

public class ServiceFactory
{
  private static ServiceFactory instance;
  private static Retrofit.Builder retrofitBuilder;
  private static HttpLoggingInterceptor interceptor;
  private static OkHttpClient client;


  private ServiceFactory()
  {
    retrofitBuilder = new Retrofit.Builder();
    interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
  }


  public static ServiceFactory getInstance()
  {
    if (instance != null)
    {
      return instance;
    }
    else
    {
      return new ServiceFactory();
    }
  }


  public static <S> S createService(Class<S> serviceClass, String baseUrl)
  {
    Retrofit retrofit = retrofitBuilder.baseUrl(baseUrl).client(client).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io())).build();
    return retrofit.create(serviceClass);

  }

}
