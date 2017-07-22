package com.andy.baselibrary.activity;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.andy.baselibrary.R;

/**
 * Created by Andy on 2016/11/15.
 */

public  class ListActivity<T> extends DataBindActivity<com.andy.baselibrary.CommonListActivityBind> {
    private RecyclerView.Adapter contentAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.comm_act_list;
    }

    @Override
    protected void initData() {
        this.contentAdapter = newAdapter();
    }

    @Override
    protected void initViews() {


        initHeaderView();
    }

    private void initAdapterWrapper() {

    }

    protected RecyclerView.Adapter newAdapter() {
        return null;
    }

    public RecyclerView.Adapter getContentAdapter() {
        return contentAdapter;
    }

    protected  void initHeaderView() {}

    protected  void initFooterView() {}

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
