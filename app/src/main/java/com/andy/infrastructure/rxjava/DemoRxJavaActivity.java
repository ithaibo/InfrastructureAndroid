package com.andy.infrastructure.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.baselibrary.utils.GenServiceUtil;
import com.andy.baselibrary.utils.LogUtil;
import com.andy.infrastructure.R;
import com.andy.infrastructure.bean.Customer;
import com.andy.infrastructure.net.CustomerService;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class DemoRxJavaActivity extends BaseActivity {

    @BindView(R.id.btnShowText)
    Button btnShowText;
    @BindView(R.id.tvTextFromNet)
    TextView tvTextFromNet;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo_rx_java;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

    }

    @OnClick({R.id.btnShowText})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnShowText:
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
            this.tvTextFromNet.setText(data.getName());
        }
    }
}
