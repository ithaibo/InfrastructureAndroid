package com.andy.infrastructure.demos.material;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.infrastructure.R;
import com.andy.infrastructure.bean.DataFrg;
import com.andy.infrastructure.demos.material.DataFrgBind;
import com.andy.infrastructure.presenter.MaterialPresenter;

/**
 * Created by Smily on 2017/1/2.
 */

public class Material1Activity extends BaseActivity {

    private DataFrg mDataInstance;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataFrgBind dataFrgBind = (DataFrgBind) mDataBind;

        mDataInstance = new DataFrg();
        mDataInstance.setCbText("Text -- 1");

        dataFrgBind.setDataFrg(mDataInstance);
        dataFrgBind.setPresenter(new MaterialPresenter());

    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_material_1;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

    }
}
