package com.andy.baselibrary.utils;

import com.android.annotations.NonNull;
import com.andy.baselibrary.bean.InternetCheckDataBean;
import com.andy.baselibrary.interfaces.CheckInterNetAcccessCallback;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

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
     * @return
     */
    public static boolean isNetworkConnected() {
        boolean isConnected = false;

        return isConnected;
    }

    /**
     * 通过Ping命令检查Internet是否已经连接。注：这种方式在网络已连接，但是没有连接到Internet时，会比较耗时。
     *
     * @param callBack
     */
    public static void isInternetAvailableByPing(@NonNull final CheckInterNetAcccessCallback<InternetCheckDataBean> callBack) {
        LogUtil.i("NetWorkUtils.isInternetAvailableByPing");
        final Thread pingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                InternetCheckDataBean data = new InternetCheckDataBean();
                data.setStartTime(System.currentTimeMillis());

                boolean isValid = false;
                Process p = null;
                try {
                    p = Runtime.getRuntime().exec("ping -c 1 -i 0.2 -W 1 " + "www.baidu.com");
                    int status = p.waitFor();
                    if (status == 0) {
                        isValid = true;
                    } else {
                        isValid = false;
                    }
                } catch (IOException ioError) {
                    ioError.printStackTrace();
                } catch (InterruptedException i) {
                    i.printStackTrace();
                } finally {
                    data.setIsinternetAccess(isValid);
                    data.setStopTime(System.currentTimeMillis());
                    callBack.onCallback(data);
                    LogUtil.i("NetWorkUtils-isInternetAvailableByPing-complete. start time: " + data.getStartTime() + " end time: " + data.getStopTime() + "isValid: " +isValid);
                }
            }
        });

        pingThread.start();
    }

    public static void isInternetAvailableByIntAdddr(@NonNull final CheckInterNetAcccessCallback<InternetCheckDataBean> callBack) {
        LogUtil.i("NetWorkUtils.isInternetAvailableByIntAdddr");
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
                            callBack.onCallback(data);
                            LogUtil.i("NetWorkUtils-isInternetAvailableByIntAdddr-complete. start time: " + data.getStartTime() + " end time: " + data.getStopTime() + "network access: " + isAccess);
                        }
                    }
                });
        interNetAccessThread.start();
    }

}
