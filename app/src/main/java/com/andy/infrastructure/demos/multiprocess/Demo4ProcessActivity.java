package com.andy.infrastructure.demos.multiprocess;

import android.widget.TextView;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.infrastructure.R;

import butterknife.BindView;

/**
 * Created by Andy on 2017/1/4.
 */

public class Demo4ProcessActivity extends BaseActivity {
    @BindView(R.id.tv_simple)
    TextView mTvSimple;
    @Override
    protected int getLayoutId() {
        return R.layout.act_demo_process;
    }

    @Override
    protected void initData() {
        mTvSimple.setText("modify ...");
    }

    @Override
    protected void initViews() {

    }
}
