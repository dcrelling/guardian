package com.example.dcrelling.guardian.modules;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by davidcrelling on 12/12/16.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent
{
    Retrofit retrofit();
}
