package com.andy.baselibrary.decoration;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andy.baselibrary.utils.DensityUtil;

/**
 * Created by Andy on 2016/12/6.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int top;
    private int left;
    private int right;
    private int bottom;

    public SpaceItemDecoration(Context context, int top, int left, int right, int bottom) {
        this.top = DensityUtil.dip2px(context, top);
        this.left = DensityUtil.dip2px(context, left);
        this.right = DensityUtil.dip2px(context, right);
        this.bottom = DensityUtil.dip2px(context, bottom);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = this.left;
        outRect.top = this.top;
        outRect.right = this.right;
        outRect.bottom = this.bottom;
    }
}
