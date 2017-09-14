package com.andy.infrastructure.demos.artiche;

import android.content.Intent;
import android.view.View;

import com.andy.baselibrary.activity.DataBindActivity;
import com.andy.infrastructure.R;
import com.andy.infrastructure.databinding.ModuleNavigateBinding;
import com.ithaibo.module_view.ModuleDevelopActivity;

/**
 * Created by Andy on 2017/9/14.
 */

public class NavigateToModuleActivity extends DataBindActivity<ModuleNavigateBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.module_navigate;
    }

    @Override
    protected void initData() {
        mDataBind.setIntentData("Hello, this is "+ this.getClass().getName());

        mDataBind.setClicker(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigateToModuleActivity.this, ModuleDevelopActivity.class);
                intent.putExtra("data", mDataBind.getIntentData());
                startActivityForResult(intent, 110);
            }
        });
    }

    @Override
    protected void initViews() { }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 110 && resultCode == RESULT_OK) {
            if (data.hasExtra("result")) {
                mDataBind.setResult(data.getStringExtra("result"));
            } else {
                showToast("no result return.");
            }
        }
    }
}
