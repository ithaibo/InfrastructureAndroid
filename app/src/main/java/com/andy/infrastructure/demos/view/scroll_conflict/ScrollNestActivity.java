package com.andy.infrastructure.demos.view.scroll_conflict;

import android.view.View;
import android.widget.Toast;

import com.andy.baselibrary.activity.DataBindActivity;
import com.andy.infrastructure.R;
import com.andy.infrastructure.databinding.ActScrollNestBinding;

/**
 * Created by Andy on 2017/9/11.
 */

public class ScrollNestActivity extends DataBindActivity<ActScrollNestBinding> implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.act_scroll_nest;
    }

    @Override
    protected void initData() {
        mDataBind.setClicker(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "button clicked, id = " + v.getId(), Toast.LENGTH_SHORT).show();
    }
}
