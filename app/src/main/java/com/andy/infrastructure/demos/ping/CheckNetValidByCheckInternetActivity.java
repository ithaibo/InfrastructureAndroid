package com.andy.infrastructure.demos.ping;

import android.view.View;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.baselibrary.bean.InternetCheckDataBean;
import com.andy.baselibrary.interfaces.CheckInterNetAcccessCallback;
import com.andy.baselibrary.utils.NetWorkUtils;
import com.andy.infrastructure.R;
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
    private NetCheckData netCheckData;

    @Override
    protected int getLayoutId() {
        return R.layout.act_check_net_valid_by_ping;
    }

    @Override
    protected void initData() {
        pingBinding = (ActCheckNetValidByPingBinding) mDataBind;

        netCheckData = new NetCheckData();
        netCheckData.setShowPingPb(View.GONE);
        netCheckData.setShowIntAPb(View.GONE);

        pingBinding.setNetCheckData(netCheckData);
        pingBinding.setClickNetCheck(this);

        pingDataList = new ArrayList<>();
        intAddrDataList = new ArrayList<>();

        pingCallback = new CheckInterNetAcccessCallback<InternetCheckDataBean>() {
            private int countDown = TIME_NUMBER_CHECK;

            @Override
            public void onCallback(InternetCheckDataBean data) {
                synchronized (this) {
                    if (data != null) {
                        pingDataList.add(data);
                        countDown--;
                    }
                }
                if (countDown <= 0) {
                    final long pingTimeCost = calculateTimeCost(pingDataList);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            netCheckData.setShowPingPb(View.GONE);
                            netCheckData.setPingTimeCost(pingTimeCost + "");
                        }
                    });
                }
            }
        };
        intAddrCallback = new CheckInterNetAcccessCallback<InternetCheckDataBean>() {
            private int countDown = TIME_NUMBER_CHECK;

            @Override
            public void onCallback(InternetCheckDataBean data) {
                synchronized (this) {
                    if (data != null) {
                        intAddrDataList.add(data);
                        countDown--;
                    }
                }
                if (countDown <= 0) {
                    final long intAddrTimeCost = calculateTimeCost(intAddrDataList);
                    netCheckData.setShowIntAPb(View.GONE);
                    netCheckData.setIntATimeCost(intAddrTimeCost + "");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            netCheckData.setIntATimeCost(intAddrTimeCost);
//                            pingBinding.tvIntAddrTimeCost.setText(intAddrTimeCost + "");
                        }
                    });
                }
            }
        };

    }

    private CheckInterNetAcccessCallback<InternetCheckDataBean> createCheckInternetCallback(final List<InternetCheckDataBean> dataSet) {
        return new CheckInterNetAcccessCallback<InternetCheckDataBean>() {
            @Override
            public void onCallback(InternetCheckDataBean data) {
                if (dataSet != null) {
                    dataSet.add(data);
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

    private void checkByIntAddr() {
        netCheckData.setShowIntAPb(View.VISIBLE);
        for (int i = 0; i < TIME_NUMBER_CHECK; i++) {
            NetWorkUtils.isInternetAvailableByIntAdddr(intAddrCallback);
        }
    }

    private void checkByPing() {
        netCheckData.setShowPingPb(View.VISIBLE);
        for (int i = 0; i < TIME_NUMBER_CHECK; i++) {
            NetWorkUtils.isInternetAvailableByPing(pingCallback);
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
    protected void initViews() {

    }
}
