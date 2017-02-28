package com.andy.infrastructure.demos.listview;

import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.infrastructure.R;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ArrayAdapterDemo extends BaseActivity {
    @BindView(R.id.lv_act_array_adapter_content)
    ListView lvDemo4ArrayAdapter;
    private SoftReference<List<String>> datas;

    @Override
    protected int getLayoutId() {
        return R.layout.act_array_adapter_demo;
    }

    @Override
    protected void initData() {
        List<String> dataList = new ArrayList<>();
        datas = new SoftReference<List<String>>(dataList);
        for (int i=0; i<10 * 2; i++) {
            dataList.add("item " + ( i + 1));
        }
    }

    @Override
    protected void initViews() {
        BaseAdapter adapter = new ArrayAdapter<String>(this, R.layout.item_layout_array_adapter, datas.get());

        lvDemo4ArrayAdapter.setAdapter(adapter);
    }
}
