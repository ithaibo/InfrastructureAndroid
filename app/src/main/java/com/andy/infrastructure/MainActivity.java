package com.andy.infrastructure;

import android.content.Intent;
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
import com.andy.infrastructure.demos.animotion.AnimationActivity;
import com.andy.infrastructure.demos.constraint_layout.ConstraintLayoutActivity;
import com.andy.infrastructure.demos.custome_view.CustomeViewActivity;
import com.andy.infrastructure.demos.databinding.DataBindActivity;
import com.andy.infrastructure.demos.float_window.FloatWindowActivity;
import com.andy.infrastructure.demos.gesture.ScrollerDemoActivity;
import com.andy.infrastructure.demos.gesture.SlidingConflict;
import com.andy.infrastructure.demos.listview.ArrayAdapterDemo;
import com.andy.infrastructure.demos.multiprocess.Demo4ProcessActivity;
import com.andy.infrastructure.demos.daemon.KeepLiveService;
import com.andy.infrastructure.demos.mvp.MvpDemoActivity;
import com.andy.infrastructure.demos.other.PackageInfoActivity;
import com.andy.infrastructure.demos.permission.ManualRequestPermissionActivity;
import com.andy.infrastructure.demos.retrofit.SimpleRetrofit;
import com.andy.infrastructure.demos.rxjava.DemoRxJavaActivity;
import com.andy.infrastructure.demos.rxjava.RxJavaDataBindActivity;
import com.andy.infrastructure.demos.material.Material1Activity;
import com.andy.infrastructure.demos.web.WebViewActivity;


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

        demoClasses.add(new DemoActivityBean()
                .setName("Multi process")
                .setDesc("Multi process")
                .setClassName(Demo4ProcessActivity.class));

        demoClasses.add(new DemoActivityBean()
                .setName("Data Binding")
                .setDesc("Data Binding")
                .setClassName(DataBindActivity.class));

        demoClasses.add(new DemoActivityBean()
        .setName("Animation Activity")
        .setDesc("Animation Activity")
        .setClassName(AnimationActivity.class));

        demoClasses.add(new DemoActivityBean()
                .setName("Scroller Demo Activity")
                .setDesc("Scroller Demo Activity")
                .setClassName(ScrollerDemoActivity.class));

        demoClasses.add(new DemoActivityBean()
        .setName("Sliding Conflict")
        .setDesc("Sliding Conflict")
        .setClassName(SlidingConflict.class));

        demoClasses.add(new DemoActivityBean()
        .setName("Permission Manual")
        .setDesc("Permission Manual")
        .setClassName(ManualRequestPermissionActivity.class));

        demoClasses.add(new DemoActivityBean()
                .setName("Custom View")
                .setDesc("Custom View")
                .setClassName(CustomeViewActivity.class));

        demoClasses.add(new DemoActivityBean()
        .setName("ArrayAdapter : 自动找到其中的TextView并赋值")
        .setClassName(ArrayAdapterDemo.class)
        .setDesc("ArrayAdapter : 自动找到其中的TextView并赋值"));

        demoClasses.add(new DemoActivityBean()
        .setName("PackageInfo DataBinding in RecyclerView ")
        .setClassName(PackageInfoActivity.class)
        .setDesc("PackageInfoActivity PackageInfo DataBinding in RecyclerView"));

        demoClasses.add(new DemoActivityBean()
        .setClassName(ConstraintLayoutActivity.class)
        .setName("Constraint Layout")
        .setDesc("Constraint Layout"));

        demoClasses.add(new DemoActivityBean()
                .setClassName(FloatWindowActivity.class)
                .setName("Float Window")
                .setDesc("Float Window"));

        demoClasses.add(new DemoActivityBean()
                .setClassName(MvpDemoActivity.class)
                .setName("Mvp Demo")
                .setDesc("Mvp Demo"));

        demoClasses.add(new DemoActivityBean()
                .setClassName(WebViewActivity.class)
                .setName("WebViewActivity")
                .setDesc("WebViewActivity"));

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
        return false;
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
        switch (id) {
            case R.id.nav_camera:
                break;
            case R.id.nav_gallery:
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_manage:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Intent intent = new Intent(this, KeepLiveService.class);
        startService(intent);
    }
}
