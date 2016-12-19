package com.andy.baselibrary.activity;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Andy on 2016/11/15.
 */

public abstract class BaseListViewActivity<T> extends BaseActivity {


    @Override
    protected void initData() {
        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.addItemDecoration(new MyDecoration());
    }

    @Override
    protected void initViews() {
        initHeaderView();
    }

    protected abstract void initHeaderView();

    protected abstract void initFooterView();

    class MyDecoration extends RecyclerView.ItemDecoration{
        int oritation;
        public MyDecoration() {
            super();
        }

        public void setOritation(int oritation){
            if (oritation!= LinearLayout.HORIZONTAL && oritation!=LinearLayout.VERTICAL){
                return;
            }
            this.oritation = oritation;
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent) {
            super.onDraw(c, parent);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent) {
            super.onDrawOver(c, parent);
        }

        @Override
        public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
            super.getItemOffsets(outRect, itemPosition, parent);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
        }
    }
}
