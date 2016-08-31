package com.example.dcrelling.guardian.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
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
import com.example.dcrelling.guardian.R;
import com.example.dcrelling.guardian.adapters.ArticleAdapter;
import com.example.dcrelling.guardian.services.GuardianArticleResponse;
import com.example.dcrelling.guardian.services.GuardianService;

public class GuardianController extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, GuardianView
{

  private GuardianPresenter _presenter;
  private GuardianModel _model;
  private ProgressBar _progressBar;
  private ListView _articleListView;
  private RelativeLayout _listContainer;


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

    _progressBar = (ProgressBar) findViewById(R.id.article_progress);
    _listContainer = (RelativeLayout) findViewById(R.id.content_main);
    _articleListView = (ListView) _listContainer.findViewById(R.id.article_list);

    _model = new GuardianModel();
    _presenter = new GuardianPresenterImpl(this, _model);
    _presenter.initialize();
    _presenter.loadDefaultArticles();
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
      _presenter.loadArticles(GuardianService.ApiType.US_NEWS);
    }
    else if (id == R.id.music)
    {
      _presenter.loadArticles(GuardianService.ApiType.MUSIC);
    }
    else if (id == R.id.business)
    {
      _presenter.loadArticles(GuardianService.ApiType.BUSINESS);
    }
    else if (id == R.id.technology)
    {
      _presenter.loadArticles(GuardianService.ApiType.TECHNOLOGY);
    }
    else if (id == R.id.world)
    {
      _presenter.loadArticles(GuardianService.ApiType.WORLD);
    }
    else if (id == R.id.politics)
    {
      _presenter.loadArticles(GuardianService.ApiType.POLITICS);
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
}
