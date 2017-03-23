package com.andy.infrastructure.demos.ping;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.baselibrary.bean.InternetCheckDataBean;
import com.andy.baselibrary.interfaces.CheckInterNetAcccessCallback;
import com.andy.baselibrary.utils.LogUtil;
import com.andy.baselibrary.utils.NetWorkUtils;
import com.andy.infrastructure.R;
import com.andy.infrastructure.bean.PingViewStatusBean;
import com.andy.infrastructure.databinding.ActCheckNetValidByPingBinding;
import com.andy.infrastructure.presenter.NetCheckData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2017/3/21.
 */

public class CheckNetValidByCheckInternetActivity extends BaseActivity implements View.OnClickListener {

    private CheckInterNetAcccessCallback<InternetCheckDataBean> pingCallback;
    private List<InternetCheckDataBean> pingDataList;
    private List<InternetCheckDataBean> intAddrDataList;
    private CheckInterNetAcccessCallback<InternetCheckDataBean> intAddrCallback;
    private ActCheckNetValidByPingBinding pingBinding;
    private static final int TIME_NUMBER_CHECK = 10 * 2;
    private PingViewStatusBean pingData;
    private PingViewStatusBean intAddrData;

    private static final String KEY_SAVE_INSTANCE_PING = "pingCallback";
    private static final String KEY_SAVE_INSTANCE_INTA = "intAddrCallback";

    @Override
    protected int getLayoutId() {
        return R.layout.act_check_net_valid_by_ping;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != savedInstanceState) {
            deParceInstanceState(savedInstanceState);
        } else {
            pingCallback = createCheckInternetCallback(pingDataList, pingData);
            intAddrCallback = createCheckInternetCallback(intAddrDataList, intAddrData);
        }
    }

    @Override
    protected void initData() {
        pingBinding = (ActCheckNetValidByPingBinding) mDataBind;

        NetCheckData netCheckData = new NetCheckData();
        pingBinding.setNetCheckData(netCheckData);
        pingBinding.setClickNetCheck(this);

        pingData = new PingViewStatusBean();
        pingData.setShowPingPb(View.GONE);
        netCheckData.setPingStatueData(pingData);

        intAddrData = new PingViewStatusBean();
        intAddrData.setShowPingPb(View.GONE);
        netCheckData.setIntAddrStatueData(intAddrData);

        pingDataList = new ArrayList<>();
        intAddrDataList = new ArrayList<>();

        InternetCheckDataBean testStorData = new InternetCheckDataBean();
        testStorData.setStopTime(System.currentTimeMillis());
        testStorData.setStartTime(System.currentTimeMillis());
        testStorData.setIsinternetAccess(true);

        pingDataList.add(testStorData);

    }


    private CheckInterNetAcccessCallback<InternetCheckDataBean> createCheckInternetCallback(
            final List<InternetCheckDataBean> dataSet, final PingViewStatusBean viewData) {
        return new CheckInterNetAcccessCallback<InternetCheckDataBean>() {
            private int countDown = TIME_NUMBER_CHECK;

            /**
             * 在工作线程回调
             * @param data
             */
            @Override
            public void onCallback(InternetCheckDataBean data) {
                synchronized (this) {
                    if (data != null) {
                        dataSet.add(data);
                    }
                    countDown--;
                    LogUtil.i("倒计时：" + countDown);
                }
                if (countDown <=0) {
                    viewData.setShowPingPb(View.GONE);
                    viewData.setPingTimeCost(calculateTimeCost(dataSet) + "");
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ping_trigger:
                checkByPing();
                break;
            case R.id.btn_intaddr_trigger:
                checkByIntAddr();
                break;
        }
    }

    private void checkByPing() {
        pingData.setShowPingPb(View.VISIBLE);
        for (int i = 0; i < TIME_NUMBER_CHECK; i++) {
            NetWorkUtils.isInternetAvailableByPing(pingCallback);
        }
    }

    private void checkByIntAddr() {
        intAddrData.setShowPingPb(View.VISIBLE);
        for (int i = 0; i < TIME_NUMBER_CHECK; i++) {
            NetWorkUtils.isInternetAvailableByIntAdddr(intAddrCallback);
        }
    }

    private long calculateTimeCost(List<InternetCheckDataBean> dataSet) {
        long startTotal = 0;
        long stopTotal = 0;
        for (int i = 0; i < dataSet.size(); i++) {
            startTotal += dataSet.get(i).getStartTime();
            stopTotal += dataSet.get(i).getStopTime();
        }

        dataSet.clear();
        return stopTotal - startTotal;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        synchronized (CheckNetValidByCheckInternetActivity.this) {
            outState.putSerializable(KEY_SAVE_INSTANCE_INTA, intAddrCallback);
//            outState.putParcelableArrayList("pingDataList", pingDataList);
        }

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        deParceInstanceState(savedInstanceState);
    }

    /**
     * 解包onRestoreInstanceState保存的数据.
     * 在{@link CheckNetValidByCheckInternetActivity#onCreate(android.os.Bundle)}onCreate()或
     * {@link CheckNetValidByCheckInternetActivity#onRestoreInstanceState(android.os.Bundle)}方法中调用
     * @param savedInstanceState 待解包数据
     */
    private void deParceInstanceState(Bundle savedInstanceState) {
        CheckInterNetAcccessCallback<InternetCheckDataBean> pingCallbackFromRestor =
                (CheckInterNetAcccessCallback<InternetCheckDataBean>) savedInstanceState.getSerializable(KEY_SAVE_INSTANCE_PING);
        CheckInterNetAcccessCallback<InternetCheckDataBean> intaCallbackFromRestor =
                (CheckInterNetAcccessCallback<InternetCheckDataBean>) savedInstanceState.getSerializable(KEY_SAVE_INSTANCE_INTA);
        if (pingCallbackFromRestor !=null) {
            pingCallback = pingCallbackFromRestor;
        }

        if (intaCallbackFromRestor !=null) {
            intAddrCallback = intaCallbackFromRestor;
        }
    }

    @Override
    protected void initViews() {

    }
}
