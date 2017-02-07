package com.andy.infrastructure.demos.gesture;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.infrastructure.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScrollerDemoActivity extends BaseActivity {
    @BindView(R.id.scroll_to_btn)
    Button scrollToBtn;
    @BindView(R.id.scroll_by_btn)
    Button scrollByBtn;
    @BindView(R.id.layout)
    LinearLayout layout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scroller_demo;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

    }

    @OnClick({R.id.scroll_to_btn,R.id.scroll_by_btn})
    void onClick(View view) {
        switch (view.getId()){
            case R.id.scroll_to_btn:
                layout.scrollTo((int)getResources().getDimension(R.dimen.x_scroll),
                        (int)getResources().getDimension(R.dimen.y_scroll));
                break;
            case R.id.scroll_by_btn:
                layout.scrollBy((int)getResources().getDimension(R.dimen.x_scroll),
                        (int)getResources().getDimension(R.dimen.y_scroll));
                break;
        }
    }
}
