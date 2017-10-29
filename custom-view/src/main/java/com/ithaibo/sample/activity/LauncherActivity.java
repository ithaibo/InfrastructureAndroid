package com.ithaibo.sample.activity;

import com.andy.baselibrary.activity.DataBindActivity;
import com.ithaibo.module_view.R;
import com.ithaibo.module_view.databinding.ActLauncherBinding;
import com.ithaibo.sample.vm.LauncherVM;

/**
 * Created by Smily on 2017/10/29.
 */

public class LauncherActivity extends DataBindActivity<ActLauncherBinding> {
	@Override
	protected int getLayoutId() {
		return R.layout.act_launcher;
	}

	@Override
	protected void initData() {
		mDataBind.setLauncherVM(new LauncherVM());
	}

	@Override
	protected void initViews() {

	}
}
