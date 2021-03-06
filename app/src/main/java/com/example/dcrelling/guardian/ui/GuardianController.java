package com.example.dcrelling.guardian.ui;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.dcrelling.guardian.R;
import com.example.dcrelling.guardian.adapters.ArticleAdapter;
import com.example.dcrelling.guardian.factories.ParametersFactory;
import com.example.dcrelling.guardian.factories.ServiceFactory;
import com.example.dcrelling.guardian.services.GuardianArticleResponse;
import com.example.dcrelling.guardian.services.GuardianService;
import com.example.dcrelling.guardian.transformers.ObserveOnMainTransformer;

public class GuardianController extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, GuardianView
{

  private GuardianPresenter _presenter;
  private GuardianModel _model;
  private ProgressBar _progressBar;
  private ListView _articleListView;
  private RelativeLayout _listContainer;
  private CoordinatorLayout _coordinatorLayout;


  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    _coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
    _progressBar = (ProgressBar) findViewById(R.id.article_progress);
    _listContainer = (RelativeLayout) findViewById(R.id.content_main);
    _articleListView = (ListView) _listContainer.findViewById(R.id.article_list);

    initPresenter();

    _presenter.loadDefaultArticles();
  }


  //todo dependency injection would be nice
  private void initPresenter()
  {
    GuardianService guardianService = ServiceFactory.getInstance().createService(GuardianService.class, GuardianService.BASE_URL);
    ParametersFactory parametersFactory = new ParametersFactory();

    _model = new GuardianModel();
    _presenter = new GuardianPresenterImpl(this, _model, guardianService, parametersFactory, new ObserveOnMainTransformer());
  }


  @Override
  public void onBackPressed()
  {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START))
    {
      drawer.closeDrawer(GravityCompat.START);
    }
    else
    {
      super.onBackPressed();
    }
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings)
    {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }


  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item)
  {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.us_news)
    {
      _presenter.loadArticlesByApi(GuardianService.ApiType.US_NEWS);
    }
    else if (id == R.id.music)
    {
      _presenter.loadArticlesByApi(GuardianService.ApiType.MUSIC);
    }
    else if (id == R.id.business)
    {
      _presenter.loadArticlesByApi(GuardianService.ApiType.BUSINESS);
    }
    else if (id == R.id.technology)
    {
      _presenter.loadArticlesByApi(GuardianService.ApiType.TECHNOLOGY);
    }
    else if (id == R.id.world)
    {
      _presenter.loadArticlesByApi(GuardianService.ApiType.WORLD);
    }
    else if (id == R.id.politics)
    {
      _presenter.loadArticlesByApi(GuardianService.ApiType.POLITICS);
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }


  @Override
  protected void onDestroy()
  {
    _presenter.onDestroy();
    super.onDestroy();
  }


  @Override
  public void onDisplayArticleList()
  {
    //todo dcrelling need to find a way to check for null on the model
    ArticleAdapter articleAdapter = new ArticleAdapter(getApplicationContext(), _model.getArticleList());
    _articleListView.setAdapter(articleAdapter);
    _articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
      {
        GuardianArticleResponse.Article article = (GuardianArticleResponse.Article) adapterView.getItemAtPosition(i);
        String articleUrl = article.webUrl;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(articleUrl));
        startActivity(intent);
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
    snackbar = Snackbar.make(_coordinatorLayout, message, Snackbar.LENGTH_LONG)
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
}
