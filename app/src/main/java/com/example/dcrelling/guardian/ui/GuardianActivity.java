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

import com.example.dcrelling.guardian.R;
import com.example.dcrelling.guardian.application.GuardianApp;
import com.example.dcrelling.guardian.modules.DaggerGuardianComponent;
import com.example.dcrelling.guardian.modules.GuardianModule;
import com.example.dcrelling.guardian.services.GuardianService;

import javax.inject.Inject;

public class GuardianActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GuardianContract.GuardianController
{

    @Inject
    public GuardianContract.GuardianPresenter _presenter;


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

        initMVP();

        _presenter.loadDefaultArticles();
    }


    private void initMVP()
    {
        GuardianContract.GuardianView view = (GuardianContract.GuardianView) findViewById(R.id.custom_guardian_view);
        DaggerGuardianComponent.builder().netComponent(((GuardianApp) getApplicationContext()).getNetComponent()).guardianModule(new GuardianModule(this, view)).build().inject(this);
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
    public void showNewsItem(String articleUrl)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(articleUrl));
        startActivity(intent);
    }
}
