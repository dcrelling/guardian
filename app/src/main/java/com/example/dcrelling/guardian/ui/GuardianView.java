package com.example.dcrelling.guardian.ui;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dcrelling.guardian.R;
import com.example.dcrelling.guardian.adapters.ArticleAdapter;
import com.example.dcrelling.guardian.services.GuardianArticleResponse;
import com.example.dcrelling.guardian.services.GuardianService;

import javax.inject.Inject;


public class GuardianView extends CoordinatorLayout implements GuardianContract.GuardianView
{

    private View rootView;
    private ProgressBar _progressBar;
    private ListView _articleListView;
    public GuardianContract.GuardianPresenter _presenter;
    public GuardianModel _model;

    public GuardianView(Context context)
    {
        super(context);
        init();
    }

    public GuardianView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    private void init()
    {
        rootView = inflate(getContext(), R.layout.custom_guardian_view, this);
        _progressBar = (ProgressBar) rootView.findViewById(R.id.article_progress);
        _articleListView = (ListView) rootView.findViewById(R.id.article_list);
    }

    @Override
    public void onDisplayArticleList()
    {
        //todo dcrelling need to find a way to check for null on the model
        ArticleAdapter articleAdapter = new ArticleAdapter(getContext(), _model.getArticleList());
        _articleListView.setAdapter(articleAdapter);
        _articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                GuardianArticleResponse.Article article = (GuardianArticleResponse.Article) adapterView.getItemAtPosition(i);
                _presenter.onArticleItemClicked(article);
            }
        });
    }

    @Override
    public void onShowProgress()
    {
        _progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDropProgress()
    {
        _progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String message, final GuardianService.ApiType apiType)
    {
        onDropProgress();
        Snackbar snackbar;
        snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
                .setAction(R.string.retry, new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        _presenter.loadArticlesByApi(apiType);
                    }
                });
        snackbar.setActionTextColor(Color.YELLOW);
        View snackBarView = snackbar.getView();
        TextView snackText = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        snackText.setTextColor(Color.RED);
        snackbar.show();
    }

    @Override
    public void setPresenter(GuardianContract.GuardianPresenter presenter)
    {
        _presenter = presenter;
    }

    @Override
    public void setModel(GuardianModel model)
    {
        _model = model;
    }
}
