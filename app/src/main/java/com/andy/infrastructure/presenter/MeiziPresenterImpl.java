package com.andy.infrastructure.presenter;

import android.content.Context;
import android.widget.Toast;

import com.andy.baselibrary.net.GenServiceUtil;
import com.andy.baselibrary.utils.LogUtil;
import com.andy.infrastructure.bean.BaseListBean;
import com.andy.infrastructure.bean.MeiziData;
import com.andy.infrastructure.bean.MeiziDataList;
import com.andy.infrastructure.interfaces.IMeiziFragment;
import com.andy.infrastructure.service.MeiziService;
import com.google.gson.Gson;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Andy on 2017/3/7.
 */

public class MeiziPresenterImpl extends BasePresenterImpl implements MeiziPresenter {
    private Gson gson = new Gson();
    private Context mContext;
    private IMeiziFragment meiziFragment;

    public MeiziPresenterImpl(Context context, IMeiziFragment meiziFragment) {
        this.mContext = context;
        this.meiziFragment = meiziFragment;
    }

    @Override
    public void getMeiziData(int index) {
        //显示progressbar
        final Subscription subscription = GenServiceUtil
                .genInstance("http://gank.io")
                .createService(MeiziService.class)
                .getMeiziData(index)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MeiziDataList>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(mContext, "加载完毕", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(mContext, "出错了...", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(MeiziDataList meiziData) {
                        LogUtil.i("request http://gank.io/api/data/福利/10/{1} success. result: " + meiziData);
                        meiziFragment.updateMeiziData(meiziData.getResults());
                    }
                });

        addSubscription(subscription);
    }
}
