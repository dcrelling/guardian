package com.example.dcrelling.guardian;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by dcrelling on 9/2/16.
 */

public class ObserveImmediately<T> implements Observable.Transformer<T, T>
{

  public ObserveImmediately()
  {

  }


  @Override
  public Observable<T> call(Observable<T> observable)
  {
    return observable.subscribeOn(Schedulers.immediate())
        .observeOn(Schedulers.immediate());
  }
}