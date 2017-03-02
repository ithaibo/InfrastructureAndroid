package com.andy.infrastructure.demos.constraint_layout;

import android.widget.Toast;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.infrastructure.R;

/**
 * Created by Andy on 2017/3/1.
 */

public class ConstraintLayoutActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.constraint_act_constraint_layout;
    }

    @Override
    protected void initData() {
        Toast.makeText(getApplicationContext(), "test toast context instance of getApplicationContext(), \nSUCCESS!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void initViews() {

    }
}
