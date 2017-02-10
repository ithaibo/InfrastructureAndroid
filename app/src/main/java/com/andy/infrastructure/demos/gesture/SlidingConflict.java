package com.andy.infrastructure.demos.gesture;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.infrastructure.R;
import com.andy.infrastructure.adapter.SlidingConflictAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class SlidingConflict extends BaseActivity {
//    @BindView(R.id.lv_act_sliding_conflict)
//    ListView lvActSlidingConflict;
//    private ArrayList<String> strDemos;
    @BindView(R.id.activity_sliding_conflict)
    LinearLayout activitySlidingConflict;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sliding_conflict;
    }

    @Override
    protected void initData() {
//        strDemos = new ArrayList<>();
//        for (int i=0; i<10; i++) {
//            strDemos.add( "A" + i);
//        }

    }

    @Override
    protected void initViews() {
        /*SlidingConflictAdapter adapter = new SlidingConflictAdapter(this);
        adapter.refreshData(strDemos);
        lvActSlidingConflict.setAdapter(adapter);*/

    }
}
