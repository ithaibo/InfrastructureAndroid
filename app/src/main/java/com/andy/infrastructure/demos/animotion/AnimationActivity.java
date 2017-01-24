package com.andy.infrastructure.demos.animotion;


import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.infrastructure.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimationActivity extends BaseActivity {
    @BindView(R.id.btn_animation)
    Button btnAnimation;
    @BindView(R.id.activity_animation)
    RelativeLayout activityAnimation;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_animation;
    }

    @Override
    protected void initData() {
        final float centerX = activityAnimation.getWidth() / 2.0f;
        final float centerY = activityAnimation.getHeight() / 2.0f;

        final Rotate3dAnimation rotation = new Rotate3dAnimation(0, 90, centerX, centerY, 310.0f, true);
        rotation.setDuration(500);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    protected void initViews() {

    }
}
