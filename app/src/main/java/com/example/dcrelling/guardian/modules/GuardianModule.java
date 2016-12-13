package com.example.dcrelling.guardian.modules;

import com.example.dcrelling.guardian.factories.ParametersFactory;
import com.example.dcrelling.guardian.services.GuardianService;
import com.example.dcrelling.guardian.transformers.ObserveOnMainTransformer;
import com.example.dcrelling.guardian.ui.GuardianContract;
import com.example.dcrelling.guardian.ui.GuardianModel;
import com.example.dcrelling.guardian.ui.GuardianPresenter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by davidcrelling on 12/12/16.
 */

@Module
public class GuardianModule
{

    private GuardianContract.GuardianController controller;
    private GuardianContract.GuardianView view;

    public GuardianModule(GuardianContract.GuardianController controller, GuardianContract.GuardianView view)
    {
        this.controller = controller;
        this.view = view;
    }


    @Provides
    GuardianContract.GuardianController providesController()
    {
        return controller;
    }

    @Provides
    GuardianContract.GuardianView providesView()
    {
        return view;
    }

    @Provides
    GuardianModel providesModel()
    {
        return new GuardianModel();
    }

    @Provides
    ParametersFactory providesParameterFactory()
    {
        return new ParametersFactory();
    }

    @Provides
    Observable.Transformer providesTransformer()
    {
        return new ObserveOnMainTransformer();
    }

    @Provides
    GuardianService providesGuardianService(Retrofit retrofit)
    {
        return retrofit.create(GuardianService.class);

    }

    @Provides
    GuardianContract.GuardianPresenter providesPresenter(GuardianModel model, GuardianService service, ParametersFactory parametersFactory, Observable.Transformer transformer)
    {
        return new GuardianPresenter(controller, model, service, parametersFactory, transformer, view);
    }
}
