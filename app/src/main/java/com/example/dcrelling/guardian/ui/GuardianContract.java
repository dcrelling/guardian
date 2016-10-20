package com.example.dcrelling.guardian.ui;

import com.example.dcrelling.guardian.framework.Presenter;
import com.example.dcrelling.guardian.framework.View;
import com.example.dcrelling.guardian.services.GuardianArticleResponse;
import com.example.dcrelling.guardian.services.GuardianService;


public interface GuardianContract
{
    interface GuardianView extends View<GuardianContract.GuardianPresenter, GuardianModel>
    {
        void onDisplayArticleList();


        void onShowProgress();


        void onDropProgress();

        void onError(String message, GuardianService.ApiType apiType);
    }

    interface GuardianPresenter extends Presenter
    {

        void loadDefaultArticles();


        void onDestroy();


        void loadArticlesByApi(GuardianService.ApiType apiType);

        void onArticleItemClicked(GuardianArticleResponse.Article article);

    }

    interface GuardianController
    {
        void showNewsItem(String articleUrl);
    }
}
