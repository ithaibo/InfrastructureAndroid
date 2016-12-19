package com.andy.infrastructure;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.andy.baselibrary.utils.GenServiceUtil;
import com.andy.baselibrary.utils.LogUtil;
import com.andy.infrastructure.bean.Customer;
import com.andy.infrastructure.net.CustomerService;


import java.io.IOException;
import java.net.SocketException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.btn_click)
    Button btn_click;
    @BindView(R.id.tv_customer_name)
    TextView tv_customer_name;

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

    @OnClick({R.id.btn_click})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_click:
//                Toast.makeText(this, "Thanks a lot, ButterKnife.", Toast.LENGTH_SHORT).show();
                requestCustomerInfo();
                break;
        }
    }

    private void requestCustomerInfo() {
        LogUtil.d("requestCustomerInfo()");
        CustomerService service = GenServiceUtil.createService(CustomerService.class);
        final Call<Customer> call = service.getCustomerInfo();
        final Observable myObserable = Observable.create(new Observable.OnSubscribe<Customer>() {
            @Override
            public void call(rx.Subscriber<? super Customer> subscriber) {
                Response<Customer> bean = null;
                try {
                    LogUtil.d("requestCustomerInfo call...");
                    bean = call.execute();
                    subscriber.onNext(bean.body());
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }

                subscriber.onCompleted();
            }
        });

        myObserable
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Customer, Customer>() {
                    @Override
                    public Customer call(Customer customer) {
                        if (customer != null && TextUtils.isEmpty(customer.getName())){
                            customer.setName("nothing!");
                        }
                        LogUtil.d("call...");
                        return customer;
                    }
                })
                .subscribe(new Subscriber<Customer>(){
                    @Override
                    public void onNext(Customer customer) {
                        setCustomerView(customer);
                        LogUtil.d("onNext...");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onCompleted() {
                        LogUtil.d("onCompleted...");
                    }
                });
    }

    private void setCustomerView(Customer data) {
        if (data!=null && !TextUtils.isEmpty(data.getName())) {
            tv_customer_name.setText(data.getName());
        }
    }
}
