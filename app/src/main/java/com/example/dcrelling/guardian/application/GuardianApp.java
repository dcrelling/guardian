package com.example.dcrelling.guardian.application;

import android.app.Application;

import com.example.dcrelling.guardian.modules.AppModule;
import com.example.dcrelling.guardian.modules.DaggerNetComponent;
import com.example.dcrelling.guardian.modules.NetComponent;
import com.example.dcrelling.guardian.modules.NetModule;
import com.example.dcrelling.guardian.services.GuardianService;

/**
 * Created by davidcrelling on 12/12/16.
 */

public class GuardianApp extends Application
{
    private NetComponent mNetComponent;


    @Override
    public void onCreate()
    {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(GuardianService.BASE_URL))
                .build();
    }

    public NetComponent getNetComponent()
    {
        return mNetComponent;
    }
}
