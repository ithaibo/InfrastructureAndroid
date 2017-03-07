package com.andy.infrastructure.demos.mvp;

import android.os.Bundle;
import android.widget.ImageView;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.infrastructure.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Andy on 2017/3/7.
 */

public class MeiziDescripActivity extends BaseActivity {
    @BindView(R.id.iv_meizi_full)
    ImageView ivMeiziFull;

    @Override
    protected int getLayoutId() {
        return R.layout.act_meizi_descrip;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

    }
}
