package com.ithaibo.sample.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.andy.baselibrary.activity.DataBindActivity;
import com.ithaibo.module_view.R;
import com.ithaibo.module_view.databinding.ModuleDevBinding;

/**
 * Created by Andy on 2017/9/14.
 */

public class ModuleDevelopActivity extends DataBindActivity<ModuleDevBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.module_dev;
    }

    @Override
    protected void initData() {
        if(getIntent().hasExtra("data")){
            mDataBind.setFromData(getIntent().getStringExtra("data"));
        }


        mDataBind.setClicker(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent();
                if (TextUtils.isEmpty(mDataBind.getFromData())) {
                    back.putExtra("result", "hei, I did'nt get your data.");
                } else {
                    back.putExtra("result", mDataBind.tvResult.getText());
                }
                setResult(RESULT_OK, back);
                finish();
            }
        });
    }

    @Override
    protected void initViews() {

    }
}
