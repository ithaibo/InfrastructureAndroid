package com.andy.infrastructure.demos.rxjava;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.baselibrary.net.GenServiceUtil;
import com.andy.baselibrary.utils.LogUtil;
import com.andy.infrastructure.R;
import com.andy.infrastructure.bean.Customer;
import com.andy.infrastructure.net.CustomerService;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class DemoRxJavaActivity extends BaseActivity {
    @BindView(R.id.btnShowText)
    Button btnShowText;
    @BindView(R.id.tvTextFromNet)
    TextView tvTextFromNet;
    @BindView(R.id.ivSimple)
    ImageView ivSimple;

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
//                simpleAction();
//                showImages();
                break;
        }
    }

    public void requestCustomerInfo() {
        LogUtil.d("requestCustomerInfo()");
        final Call<Customer> call = new GenServiceUtil("http://192.168.1.24:3000/")
                .createService(CustomerService.class)
                .getCustomerInfo(1);

        final Observable myObserable = Observable.create(new Observable.OnSubscribe<Customer>() {
            @Override
            public void call(Subscriber<? super Customer> subscriber) {
                Response<Customer> bean = null;
                try {
                    //请求服务器
                    bean = call.execute();
                    LogUtil.d("requestCustomerInfo call..." + bean.body());
                    //处理结果
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
                .subscribe(new Subscriber<Customer>(){
                    @Override
                    public void onNext(Customer customer) {
                        setCustomerView(customer);
                        LogUtil.d("onNext...");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e("error!");
                    }

                    @Override
                    public void onCompleted() {
                        LogUtil.d("onCompleted...");
                    }
                });
    }

    private void setCustomerView(Customer data) {
        LogUtil.d("--setCustomerView");
        if (data!=null && !TextUtils.isEmpty(data.getName())) {
            this.tvTextFromNet.setText(data.getName());
        }
    }


    private void simpleAction() {
        /**
         * 打印字符串数组
         */
        String[] names = new String[]{
                "Smily", "Andy"
        };
        Observable.from(names)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        LogUtil.i("Item name is : " + s);
                    }
                });

        /**
         * 打印多个字符
         */
        Observable.just("Xiong", "Wu")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        LogUtil.i("Family name is : " + s);
                    }
                });
    }

    private void showImages() {
        final int ivId = R.mipmap.tab_bar_icon_home_pressed;

        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getResources().getDrawable(ivId);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Drawable>() {
            @Override
            public void onCompleted() {
                Toast.makeText(DemoRxJavaActivity.this, "加载完毕", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable e) { }

            @Override
            public void onNext(Drawable drawable) {
                ivSimple.setImageDrawable(drawable);
            }
        });
    }
}
