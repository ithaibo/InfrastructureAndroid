package com.andy.infrastructure.demos.form_valid;

import com.andy.baselibrary.activity.DataBindActivity;
import com.andy.infrastructure.R;
import com.andy.infrastructure.databinding.FormActMatchBinding;

/**
 * Created by Smily on 2017/7/22.
 */

public class FormActivity extends DataBindActivity<FormActMatchBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.form_act_match;
    }

    @Override
    protected void initData() {
        mDataBind.setMv(new FormModuleView());
    }

    @Override
    protected void initViews() {

    }
}
