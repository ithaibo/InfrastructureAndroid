package com.ithaibo.sample.fragment;

import android.view.View;

import com.andy.baselibrary.fragment.DataBindFrgment;
import com.ithaibo.module_view.R;
import com.ithaibo.module_view.databinding.FrgMatrixBinding;

/**
 * Created by Andy on 2017/11/3.
 */

public class MatrixFragment extends DataBindFrgment<FrgMatrixBinding> {
    @Override
    protected int getFrgLayoutId() {
        return R.layout.frg_matrix;
    }

    @Override
    protected void initView(View rootView) {

    }
}
