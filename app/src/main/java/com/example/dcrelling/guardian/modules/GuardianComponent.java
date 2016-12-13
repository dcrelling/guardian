package com.example.dcrelling.guardian.modules;

import com.example.dcrelling.guardian.ui.GuardianActivity;
import com.example.dcrelling.guardian.util.CustomScope;

import dagger.Component;


@CustomScope
@Component(dependencies = NetComponent.class, modules = GuardianModule.class)
public interface GuardianComponent
{

    void inject(GuardianActivity activity);
}
