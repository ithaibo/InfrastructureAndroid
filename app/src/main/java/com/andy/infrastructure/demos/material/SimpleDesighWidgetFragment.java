package com.andy.infrastructure.demos.material;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.andy.baselibrary.fragment.DataBindFrgment;
import com.andy.infrastructure.R;
import com.andy.infrastructure.bean.DataFrg;
import com.andy.infrastructure.presenter.MaterialPresenter;
import com.andy.infrastructure.demos.material.DataFrgBind;
import com.andy.infrastructure.presenter.ShowSnackPresenter;

/**
 * Created by Andy on 2017/3/8.
 */

public class SimpleDesighWidgetFragment extends DataBindFrgment implements ShowSnackPresenter {
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

        mDataBinder.setDataFrg(mDataInstance);
        mDataBinder.setSnackPresenter(this);
        mDataBinder.setPresenter(new MaterialPresenter());
    }

    @Override
    public void showSnackBar(View view) {
        switch (view.getId()) {
            case R.id.btn_show_snack:
                Snackbar.make(getView(), R.string.text_show_snackbar_simple, Snackbar.LENGTH_LONG).show();
                break;
            case R.id.btn_show_snack_action:
                Snackbar.make(getView(), R.string.text_show_snackbar_action, Snackbar.LENGTH_LONG)
                        .setAction(R.string.label_text_action_snack, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showToast("你点击了SnackBar的Action按钮");
                            }
                        })
                        .setActionTextColor(getContext().getResources().getColor(R.color.colorAccent))
                        .show();
                break;
        }
    }
}
