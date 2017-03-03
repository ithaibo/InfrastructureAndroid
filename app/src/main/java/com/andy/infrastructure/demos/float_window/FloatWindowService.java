package com.andy.infrastructure.demos.float_window;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;

import com.andy.baselibrary.utils.LogUtil;

public class FloatWindowService extends Service {

	/**
	 * 用于在线程中创建或移除悬浮窗。
	 */
	private Handler handler = new Handler();

	/**
	 * 定时器，定时进行检测当前应该创建还是移除悬浮窗。
	 */
	private Timer timer;

	private ScreenShotReceiver receiver;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		LogUtil.i("悬浮Service已经打开");

		// 开启定时器，每隔0.5秒刷新一次
		if (timer == null) {
			timer = new Timer();
			timer.scheduleAtFixedRate(new RefreshTask(), 0, 500);
		}

		if (receiver == null) {
			receiver = new ScreenShotReceiver();
		}

		IntentFilter intentFilter = new IntentFilter("com.andy.SCREENSHOT");

		registerReceiver(receiver, intentFilter);

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		LogUtil.i("悬浮Service已经关闭");
		// Service被终止的同时也停止定时器继续运行
		timer.cancel();
		timer = null;
		if (receiver!=null) {
			unregisterReceiver(receiver);
		}

		MyWindowManager.removeSmallWindow(getApplicationContext());

		super.onDestroy();
	}

	class RefreshTask extends TimerTask {

		@Override
		public void run() {
			// 当前界面是桌面，且没有悬浮窗显示，则创建悬浮窗。
			if (!MyWindowManager.isWindowShowing()) {
				handler.post(new Runnable() {
					@Override
					public void run() {
						MyWindowManager.createSmallWindow(getApplicationContext());
					}
				});
			}
		}

	}


}
