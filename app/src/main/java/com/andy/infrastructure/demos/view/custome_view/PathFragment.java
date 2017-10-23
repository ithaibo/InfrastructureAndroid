package com.andy.infrastructure.demos.view.custome_view;

import android.view.View;

import com.andy.baselibrary.fragment.DataBindFrgment;
import com.andy.infrastructure.R;

/**
 * Created by Andy on 2017/10/23.
 */

public class PathFragment extends DataBindFrgment {
    @Override
    protected int getFrgLayoutId() {
        return R.layout.frg_draw_text;
    }

    @Override
    protected void initView(View rootView) {

    }
}
