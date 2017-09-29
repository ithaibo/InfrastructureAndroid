package com.andy.infrastructure.demos.lifecycle;

import com.andy.baselibrary.activity.DataBindActivity;
import com.andy.infrastructure.R;
import com.andy.infrastructure.databinding.ActLifeCycleBinding;

/**
 * Created by Andy on 2017/9/29.
 */

public class LifecycleActivity extends DataBindActivity<ActLifeCycleBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.act_life_cycle;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        getSupportFragmentManager().beginTransaction()
                .add(mDataBind.container.getId(), new ProductListFragment(), "productList")
        .commit();
    }
}
