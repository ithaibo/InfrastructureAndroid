package com.andy.infrastructure.demos.mvp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.infrastructure.R;
import com.andy.infrastructure.adapter.MeiziAdapter2;
import com.andy.infrastructure.bean.MeiziData;
import com.andy.infrastructure.demos.mvp.presenter.MeiziPresenter;
import com.andy.infrastructure.demos.mvp.presenter.MeiziPresenterImpl;
import com.andy.infrastructure.interfaces.IMeiziFragment;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Andy on 2017/3/7.
 */

public class MvpDemoActivity extends BaseActivity implements IMeiziFragment {
    @BindView(R.id.rcl_meizi)
    RecyclerView rclMeizi;
    private MeiziPresenter meiziPresenter;
    private MeiziAdapter2 meiziAdapter;
    private LinearLayoutManager layoutManager;


    @Override
    protected int getLayoutId() {
        return R.layout.act_mvp;
    }

    @Override
    protected void initData() {
        meiziPresenter = new MeiziPresenterImpl(this, this);
        meiziAdapter = new MeiziAdapter2(this, R.layout.item_meizi_mvp);
    }

    @Override
    protected void initViews() {
        layoutManager = new LinearLayoutManager(this);
        rclMeizi.setLayoutManager(layoutManager);
        rclMeizi.setAdapter(meiziAdapter);
        //触发网络请求
        meiziPresenter.getMeiziData(1);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hidProgressDialog() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void updateMeiziData(List<MeiziData> list) {
        if (meiziAdapter.getItemCount() <=0) {
            meiziAdapter.refreshData(list);
        } else {
            meiziAdapter.appendData(list);
        }
    }

    @Override
    protected void onDestroy() {
        ((MeiziPresenterImpl)meiziPresenter).unsubscribe();
        super.onDestroy();
    }
}
