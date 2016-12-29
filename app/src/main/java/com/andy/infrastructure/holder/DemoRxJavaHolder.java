package com.andy.infrastructure.holder;

import android.view.View;
import android.widget.TextView;

import com.andy.baselibrary.holder.BaseRecyclerHolder;
import com.andy.infrastructure.R;

import butterknife.BindView;

/**
 * Created by Andy on 2016/12/28.
 */

public class DemoRxJavaHolder extends BaseRecyclerHolder {
    @BindView(R.id.tv_title)
    public TextView tvTitle;

    public DemoRxJavaHolder(View itemView) {
        super(itemView);
    }
}
