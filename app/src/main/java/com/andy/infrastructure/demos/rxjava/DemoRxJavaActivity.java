package com.andy.infrastructure.demos.rxjava;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.baselibrary.decoration.SpaceItemDecoration;
import com.andy.baselibrary.interfaces.RecyclerViewOnClickListener;
import com.andy.baselibrary.net.GenServiceUtil;
import com.andy.baselibrary.utils.LogUtil;
import com.andy.infrastructure.R;
import com.andy.infrastructure.adapter.DemoRxJavaAdapter;
import com.andy.infrastructure.bean.Customer;
import com.andy.infrastructure.bean.DemoRxJavaBean;
import com.andy.infrastructure.service.CustomerService;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class DemoRxJavaActivity extends BaseActivity {
    private RxJavaInfoDialog dialog;

    @BindView(R.id.rvDemos_act_rxjava)
    RecyclerView rvDemosActRxJava;

    private LinearLayoutManager llm;
    private DemoRxJavaAdapter adapter;
    private List<DemoRxJavaBean> demoList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo_rx_java;
    }

    @Override
    protected void initData() {
        demoList = new ArrayList<>();
        demoList.add(new DemoRxJavaBean()
                .setDemoTitle("distinct selector")
                .setObservable(
                        Observable.create(new Observable.OnSubscribe<String>() {
                            @Override
                            public void call(Subscriber<? super String> subscriber) {
                                subscriber.onNext("First");
                                subscriber.onNext("Sirst");
                                subscriber.onNext("Second");
                                subscriber.onNext("Third");
                                subscriber.onNext("Fourth");
                                subscriber.onNext("Fifth");
                                subscriber.onCompleted();
                            }
                        }).distinct(new Func1<String, Character>() {
                            @Override
                            public Character call(String s) {
                                return s.charAt(0);
                            }
                        })
                ).setObserver(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.d("Distinct Selector -- Complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.d("Distinct Selector -- onError:" + e);
                    }

                    @Override
                    public void onNext(String s) {
                        LogUtil.d("Distinct Selector -- onNext: " + s);
                    }
                }));
        demoList.add(new DemoRxJavaBean().setDemoTitle("filter demo")
                .setObserver(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.d("Simple Filter : completed!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.d("Simple Filter : Error" + e);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtil.d("Simple Filter: " + integer);
                    }
                })
                .setObservable(Observable.range(0, 10)
                        .filter(new Func1<Integer, Boolean>() {
                            @Override
                            public Boolean call(Integer integer) {
                                if (integer.intValue() % 2 == 0) {
                                    return true;
                                }
                                return false;
                            }
                        })));
        demoList.add(new DemoRxJavaBean().setDemoTitle("show image")
                .setObservable(Observable.create(new Observable.OnSubscribe<Drawable>() {
                    @Override
                    public void call(Subscriber<? super Drawable> subscriber) {
                        Drawable drawable = getResources().getDrawable(R.mipmap.tab_bar_icon_home_pressed);
                        subscriber.onNext(drawable);
                        subscriber.onCompleted();
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()))
                .setObserver(new Observer<Drawable>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(DemoRxJavaActivity.this, "加载完毕", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        ImageView iv = new ImageView(mContext);
                        Glide.with(mContext).
                                load("http://img1.imgtn.bdimg.com/it/u=1480985633,1206349730&fm=21&gp=0.jpg")
                                .into(iv);
                        showDialog(iv);
                    }
                }));
        demoList.add(new DemoRxJavaBean().setDemoTitle("print String Array").setObservable(Observable.from(new String[]{
                "Smily", "Andy"
        })).setObserver(new Observer<String>() {
            @Override
            public void onCompleted() {
                LogUtil.d("printStringArray completed.");
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.d("printStringArray error.");
            }

            @Override
            public void onNext(String s) {
                LogUtil.d(s);
            }
        }));
        demoList.add(new DemoRxJavaBean().setDemoTitle("just Distinct")
                .setObservable(Observable.create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        subscriber.onNext(1);
                        subscriber.onNext(1);
                        subscriber.onNext(2);
                        subscriber.onNext(3);
                        subscriber.onNext(2);
                        subscriber.onCompleted();
                    }
                }).distinct())
                .setObserver(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.d("Distinct -- completed!");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        LogUtil.d("Distinct -- onError: " + throwable);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtil.d("Distinct -- onNext : " + integer);
                    }
                }));
        demoList.add(new DemoRxJavaBean()
                .setDemoTitle("simple http request")
                .setObservable(Observable.create(new Observable.OnSubscribe<Customer>() {
                    @Override
                    public void call(Subscriber<? super Customer> subscriber) {
                        Response<Customer> bean = null;
                        try {
                            //请求服务器
                            bean = GenServiceUtil.genInstance("http://192.168.1.24:3000/", DemoRxJavaActivity.this)
                                    .createService(CustomerService.class)
                                    .getCustomerInfo().execute();
                            LogUtil.d("requestCustomerInfo call..." + bean.body());
                            //处理结果
                            subscriber.onNext(bean.body());
                        } catch (Exception e) {
                            e.printStackTrace();
                            subscriber.onError(e);
                        }
                        subscriber.onCompleted();
                    }
                }).subscribeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread()))
                .setObserver(new Observer<Customer>() {
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
                })
        );

        DataContact dataContact = new DataContact(this);
        demoList.add(new DemoRxJavaBean()
                .setDemoTitle("data concat")
        .setObservable(dataContact.getConcatObservable())
        .setObserver(dataContact.getObserver()));

        demoList.add(new DemoRxJavaBean()
        .setDemoTitle("groupby concat")
        .setObservable(dataContact.getGroupConcatObservable())
        .setObserver(dataContact.getObserver()));

        demoList.add(new DemoRxJavaBean()
        .setDemoTitle("Merge Data")
        .setObservable(dataContact.getMergeObservable())
        .setObserver(dataContact.getObserver()));
        adapter = new DemoRxJavaAdapter(this);
        adapter.initData(demoList);
    }

    @Override
    protected void initViews() {
        llm = new LinearLayoutManager(this);
        rvDemosActRxJava.setLayoutManager(llm);
        rvDemosActRxJava.setAdapter(adapter);
        rvDemosActRxJava.addItemDecoration(new SpaceItemDecoration(this, 0, 0, 0, 10));

        rvDemosActRxJava.
                addOnItemTouchListener(
                        new RecyclerViewOnClickListener(rvDemosActRxJava,
                                new RecyclerViewOnClickListener.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        try {
                                            DemoRxJavaBean bean = adapter.getItem(position);
                                            if (bean != null && bean.getObservable() != null && bean.getObserver() != null) {
                                                bean.getObservable().subscribe(bean.getObserver());
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onItemLongClick(View view, int position) {
                                    }
                                }));
    }


    private void setCustomerView(Customer data) {
        if (data != null && !TextUtils.isEmpty(data.getName())) {
            TextView tvCustomerInfo = new TextView(mContext);
            tvCustomerInfo.setText(data.getName());
            showDialog(tvCustomerInfo);
        }
    }

    private void showDialog(View contentView) {
        if (dialog == null) {
            dialog = new RxJavaInfoDialog();
        }
        if (dialog.getDialog() != null && dialog.getDialog().isShowing()) {
            dialog.dismiss();
        }
        dialog.setContentView(
                contentView);
        dialog.setConfirmListener(null);
        dialog.show(getSupportFragmentManager(), "RxJavaInfo");
    }
}
