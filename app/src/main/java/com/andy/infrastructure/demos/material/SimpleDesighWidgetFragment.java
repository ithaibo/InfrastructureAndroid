package com.andy.infrastructure.demos.material;

import android.view.View;

import com.andy.baselibrary.fragment.DataBindFrgment;
import com.andy.infrastructure.R;
import com.andy.infrastructure.bean.DataFrg;
import com.andy.infrastructure.presenter.MaterialPresenter;
import com.andy.infrastructure.demos.material.DataFrgBind;

/**
 * Created by Andy on 2017/3/8.
 */

public class SimpleDesighWidgetFragment extends DataBindFrgment {
    private DataFrgBind mDataBinder;

    @Override
    protected int getFrgLayoutId() {
        return R.layout.frg_material_simple_widget;
    }

    @Override
    protected void initView(View rootView) {
        mDataBinder = (DataFrgBind) dataBinder;

        DataFrg mDataInstance = new DataFrg();
        mDataInstance.setCbText("Text -- 1");

//        mDataBinder.setDataFrg(mDataInstance);
//        mDataBinder.setPresenter(new MaterialPresenter());
    }
}
