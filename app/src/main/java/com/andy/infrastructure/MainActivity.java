package com.andy.infrastructure;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.andy.baselibrary.adapter.BaseRecyclerdapter;

import com.andy.infrastructure.adapter.Demo4RecyclerAdapter;
import com.andy.infrastructure.bean.DemoActivityBean;
import com.andy.infrastructure.demos.retrofit.SimpleRetrofit;
import com.andy.infrastructure.demos.rxjava.DemoRxJavaActivity;
import com.andy.infrastructure.demos.rxjava.RxJavaDataBindActivity;
import com.andy.infrastructure.material.Material1Activity;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.rlDemoList)
    RecyclerView rlDemoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /**初始化数据**/
        BaseRecyclerdapter adapter = new Demo4RecyclerAdapter(this);
        adapter.initData(initDemoActivityList());
        if (rlDemoList == null) {
            rlDemoList = (RecyclerView) findViewById(R.id.rlDemoList);
        }
        rlDemoList.setLayoutManager(new LinearLayoutManager(this));
        rlDemoList.setAdapter(adapter);
    }

    private List<DemoActivityBean> initDemoActivityList() {
        List<DemoActivityBean> demoClasses = new ArrayList<>();
        demoClasses.add(new DemoActivityBean()
                .setName("RxJava")
                .setDesc("RxJava基本使用")
                .setClassName(DemoRxJavaActivity.class)
        );  //Rxjava

        demoClasses.add(new DemoActivityBean()
                .setName("Simple Retrofit")
                .setDesc("Simple Retrofit基本使用")
                .setClassName(SimpleRetrofit.class)
        );

        demoClasses.add(new DemoActivityBean()
        .setName("RxJava Data Bind")
        .setDesc("RxJava Data Bind")
        .setClassName(RxJavaDataBindActivity.class));

        demoClasses.add(new DemoActivityBean()
        .setName("Material 1")
        .setDesc("Material 1")
        .setClassName(Material1Activity.class));

        return demoClasses;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
