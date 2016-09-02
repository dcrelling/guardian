package com.example.dcrelling.guardian.util;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UIObservable
{

  private static UIObservable instance;


  private UIObservable()
  {

  }


  public static <T> Observable.Transformer<T, T> applySchedulers()
  {
    return new Observable.Transformer<T, T>()
    {
      @Override
      public Observable<T> call(Observable<T> observable)
      {
        return observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
      }
    };
  }


  public static <T> Observable<T> observeOnMain(Observable<T> observable)
  {
    return observable.observeOn(AndroidSchedulers.mainThread());
  }

}
