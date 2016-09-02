package com.example.dcrelling.guardian.services;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dcrelling on 9/2/16.
 */

public class ObserveOnMainTransformer<T> implements Observable.Transformer<T,T>
{

  public ObserveOnMainTransformer(){

  }

  @Override
  public Observable<T> call(Observable<T> observable)
  {
    return observable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
