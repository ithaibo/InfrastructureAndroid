package com.example.launch_mode;


import android.content.Intent;
import android.util.Log;

import com.andy.baselibrary.activity.DataBindActivity;
import com.example.launch_mode.databinding.LaunchModeActStartBinding;
import com.example.launch_mode.vm.StartVM;

/**
 * Created by Smily on 2017/11/26.
 */

public class StartActivity extends DataBindActivity<LaunchModeActStartBinding> {
	@Override
	protected int getLayoutId() {
		return R.layout.launch_mode_act_start;
	}

	@Override
	protected void initData() {
		StartVM vm = new StartVM();
		vm.label.set(StartActivity.class.getSimpleName());
		mDataBind.setVm(new StartVM());
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
	}

	@Override
	protected void initViews() {

	}
}
