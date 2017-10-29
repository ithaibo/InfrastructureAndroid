package com.ithaibo.sample.fragment;

import android.view.View;

import com.andy.baselibrary.fragment.DataBindFrgment;
import com.ithaibo.module_view.R;
import com.ithaibo.module_view.databinding.FrgDrawTextBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2017/10/23.
 */

public class PathFragment extends DataBindFrgment<FrgDrawTextBinding> {
    @Override
    protected int getFrgLayoutId() {
        return R.layout.frg_draw_text;
    }

    @Override
    protected void initView(View rootView) {
        List<Double> realList = new ArrayList<>();
        for (int i=0; i<dataBinder.levelView.getSides(); i++) {
            realList.add(i*5 + 24D);
        }
        dataBinder.levelView.setMaxValues(60D);
        dataBinder.levelView.setRealValues(realList);
    }
}
