package com.andy.baselibrary.fragment;

import android.view.View;

import com.andy.baselibrary.R;
import com.andy.baselibrary.adapter.AdapterDataManager;
import com.andy.baselibrary.adapter.BaseRecyclerViewAdapter;
import com.andy.baselibrary.databinding.LayoutListBinding;
import com.andy.baselibrary.utils.CommonUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2017/5/23.
 */

public abstract class BaseListFragment<T extends Serializable> extends DataBindFrgment<LayoutListBinding> implements AdapterDataManager<T> {

    private BaseRecyclerViewAdapter adapter;
    private List<T> dataList = new ArrayList<>();

    @Override
    protected int getFrgLayoutId() {
        return R.layout.layout_list;
    }

    @Override
    protected void initView(View rootView) {
        adapter = createAdapter();
        if (adapter!=null) {
            dataBinder.xrvList.setAdapter(adapter);
        }
    }

    protected abstract void loadData();

    protected BaseRecyclerViewAdapter createAdapter(){
        return null;
    }

    @Override
    public void initDataSet(List<T> dataSet) {
        if (CommonUtils.isListEmpty(dataSet)){
            return;
        }
        dataList = dataSet;
        adapter.clear();
        adapter.addAll(dataList);

        dataBinder.xrvList.setAdapter(adapter);
    }

    @Override
    public void clearData(){
        dataList.clear();
        dataListRefreshed();
    }

    @Override
    public void refreshData(List<T> dataSet) {
        if (CommonUtils.isListEmpty(dataSet)){
            return;
        }

        dataList = dataSet;
        dataListRefreshed();
    }

    @Override
    public void appendData(List<T> dataPend) {
        if (CommonUtils.isListEmpty(dataPend)) {
            return;
        }
        if (dataList==null){
            return;
        }
        dataList.addAll(dataPend);
        dataListRefreshed();
    }

    private void dataListRefreshed() {
        if (adapter!=null) {
            adapter.notifyDataSetChanged();
        }
    }
}
