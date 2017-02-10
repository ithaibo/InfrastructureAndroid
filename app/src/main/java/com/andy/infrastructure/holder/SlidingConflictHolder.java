package com.andy.infrastructure.holder;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.andy.baselibrary.holder.BaseHolder;
import com.andy.infrastructure.R;

import butterknife.BindView;


/**
 * Created by Andy on 2017/2/9.
 */

public class SlidingConflictHolder extends BaseHolder {
    public SlidingConflictHolder(View rootView) {
        super(rootView);
    }

    @BindView(R.id.tv_inner_item_conflict)
    public TextView tvInnerItemConflict;
}
