package com.ithaibo.sample.fragment;

import android.view.View;

import com.andy.baselibrary.fragment.BaseFragment;
import com.ithaibo.module_view.R;
import com.ithaibo.view.PieChart;
import com.ithaibo.view.AnmCheckView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Smily on 2017/4/3.
 */

public class CircleViewFragment extends BaseFragment {


    @Override
    protected int getFrgLayoutId() {
        return R.layout.frg_layout_custome;
    }

    @Override
    protected void initView(View rootView) {
        PieChart pieChart = (PieChart) rootView.findViewById(R.id.piChart);

        List<PieChart.PieData> data = new LinkedList<>();
        for (int i= 0; i<5; i++) {
            data.add(new PieChart.PieData((i+1)*100, "item_"+i));
        }
        pieChart.setDataList(data);

        final AnmCheckView checkView = (AnmCheckView) rootView.findViewById(R.id.checkView);
        checkView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkView.getChecked()) {
                    checkView.unCheck();
                } else {
                    checkView.check();
                }
            }
        });
    }
}
