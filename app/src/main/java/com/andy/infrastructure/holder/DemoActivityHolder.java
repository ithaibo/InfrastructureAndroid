package com.andy.infrastructure.holder;

import android.view.View;
import android.widget.TextView;

import com.andy.baselibrary.holder.BaseRecyclerHolder;
import com.andy.infrastructure.R;

import butterknife.BindView;

/**
 * Created by Andy on 2016/12/20.
 */

public class DemoActivityHolder extends BaseRecyclerHolder {
    @BindView(R.id.tvDescDemo)
    public TextView tvDescDemo;

    public DemoActivityHolder(View itemView) {
        super(itemView);
    }
}
