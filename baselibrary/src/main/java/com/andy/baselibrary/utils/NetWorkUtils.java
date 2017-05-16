package com.andy.baselibrary.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.annotations.NonNull;
import com.andy.baselibrary.bean.InternetCheckDataBean;
import com.andy.baselibrary.interfaces.CheckInterNetAcccessCallback;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Andy on 2017/3/21.
 * 该类主要是用于检查网络连接状态、网络是否可用等。
 * <p>
 * <ul>方法列表
 * <li>isNetworkConnected 检查连接状态</li>
 * <li></li>
 * </ul>
 */

public class NetWorkUtils {

    /**
     * 判断网络是否连通
     */
    public static boolean isNetworkConnected(Context context) {
        try {
            if (context != null) {
                @SuppressWarnings("static-access")
                ConnectivityManager cm = (ConnectivityManager) context
                        .getSystemService(context.CONNECTIVITY_SERVICE);
                NetworkInfo info = cm.getActiveNetworkInfo();
                return info != null && info.isConnected();
            } else {
                /**如果context为空，就返回false，表示网络未连接*/
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

    /**
     * 通过Ping命令检查Internet是否已经连接。注：这种方式在网络已连接，但是没有连接到Internet时，会比较耗时。
     *
     */
    public static boolean isInternetAvailableByPing() {
        LogUtil.i("NetWorkUtils.isInternetAvailableByPing");
        ExecutorService pingExecutor = Executors.newSingleThreadExecutor();

        Future<Boolean> pingTask = pingExecutor.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                InternetCheckDataBean data = new InternetCheckDataBean();
                data.setStartTime(System.currentTimeMillis());

                boolean isValid = false;
                try {
                    if (Runtime.getRuntime()
                            .exec("ping -c 1 -i 0.2 -W 1 " + "www.baidu.com")
                            .waitFor() == 0) {
                        return true;
                    } else {
                        return false;
                    }
                } catch (IOException ioError) {
                    ioError.printStackTrace();
                    return false;
                } catch (InterruptedException i) {
                    return false;
                } finally {
                    data.setIsinternetAccess(isValid);
                    data.setStopTime(System.currentTimeMillis());
                    LogUtil.i("NetWorkUtils-isInternetAvailableByPing-complete. start time: "
                            + data.getStartTime() + " end time: " +
                            data.getStopTime() + "isValid: " + isValid);
                }
            }
        });

        try {
            return pingTask.get().booleanValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void isInternetAvailableByIntAdddr(@NonNull final CheckInterNetAcccessCallback<InternetCheckDataBean> callBack) {
        LogUtil.i("NetWorkUtils.isInternetAvailableByIntAdddr");
        final WeakReference<CheckInterNetAcccessCallback<InternetCheckDataBean>> callbackWeakReference =
                new WeakReference<CheckInterNetAcccessCallback<InternetCheckDataBean>>(callBack);
        Thread interNetAccessThread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        InternetCheckDataBean data = new InternetCheckDataBean();
                        data.setStartTime(System.currentTimeMillis());
                        boolean isAccess = false;
                        try {
                            InetAddress ipAddr = InetAddress.getByName("baidu.com");
                            LogUtil.i("ipAddr: " + ipAddr.getAddress());
                            isAccess = !"".equals(ipAddr);
                        } catch (UnknownHostException ue) {
                            ue.printStackTrace();
                        } finally {
                            data.setIsinternetAccess(isAccess);
                            data.setStopTime(System.currentTimeMillis());
                            if (callbackWeakReference.get() != null) {
                                callbackWeakReference.get().onCallback(data);
                            }
                            LogUtil.i("NetWorkUtils-isInternetAvailableByIntAdddr-complete. start time: " +
                                    data.getStartTime() + " end time: " +
                                    data.getStopTime() + "network access: " + isAccess);
                        }
                    }
                });
        interNetAccessThread.start();
    }

}
