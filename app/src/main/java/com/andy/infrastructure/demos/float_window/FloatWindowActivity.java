package com.andy.infrastructure.demos.float_window;

import android.content.Intent;
import android.widget.CompoundButton;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.baselibrary.activity.DataBindActivity;
import com.andy.baselibrary.utils.LogUtil;
import com.andy.infrastructure.R;
import com.andy.infrastructure.databinding.FloatWindowActFloatWindowBinding;

/**
 * Created by Andy on 2017/3/3.
 */

public class FloatWindowActivity extends DataBindActivity<FloatWindowActFloatWindowBinding> implements CompoundButton.OnCheckedChangeListener {

    private Intent shotcutService;

    @Override
    protected int getLayoutId() {
        return R.layout.float_window_act_float_window;
    }

    @Override
    protected void initData() {

        shotcutService = new Intent(this, FloatWindowService.class);
        mDataBind.swtActFloatWindow.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        LogUtil.i("Switch isChecked == " + isChecked);
        if (isChecked) {
            showFloatWindow();
        } else {
            hideFloatWindow();
        }
    }

    /**
     * 关闭悬浮窗口
     */
    private void hideFloatWindow() {
        stopService(shotcutService);
    }

    /**
     * 显示悬浮窗口
     */
    private void showFloatWindow() {
        startService(shotcutService);
    }
}
