package com.andy.infrastructure.demos.custome_view;


import com.andy.baselibrary.activity.BaseActivity;
import com.andy.baselibrary.activity.DataBindActivity;
import com.andy.infrastructure.DataBind;
import com.andy.infrastructure.R;

import java.util.ArrayList;

/**
 * Created by Smily on 2017/2/19.
 */

public class CustomeViewActivity extends DataBindActivity<DataBind> {

    private ArrayList<PieData> datas;

    @Override
    protected int getLayoutId() {
        return R.layout.act_custome_view;
    }

    @Override
    protected void initData() {

        datas = new ArrayList<>();
        for (int i=0; i<5; i++) {
            PieData item = new PieData("Item - " + (i+1), 10 * (i+1));
            datas.add(item);
        }

        mDataBind.pieView.setData(datas);

    }

    @Override
    protected void initViews() {
         datas.get(0).setValue(70);
    }
}
