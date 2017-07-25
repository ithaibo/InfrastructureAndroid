package com.andy.infrastructure.demos.map;

import com.andy.baselibrary.activity.DataBindActivity;
import com.andy.baselibrary.map_nav.DirectionBean;
import com.andy.baselibrary.map_nav.OutSideNavUtils;
import com.andy.infrastructure.R;
import com.andy.infrastructure.databinding.MapDirectionBinding;

/**
 * Created by Andy on 2017/7/24.
 */

public class ThirdMapDirection extends DataBindActivity<MapDirectionBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.map_direction;
    }

    @Override
    protected void initData() { }

    @Override
    protected void initViews() {
        mDataBind.setClickAction(this.simpleAction);
    }

    private SimpleAction simpleAction = new SimpleAction() {
        @Override
        public void action(final DirectionBean direction) {
            new OutSideNavUtils(mContext).onNavOutSide(121.450212, 31.199436, "肇家浜路地铁站");
        }
    };

    public interface SimpleAction{
        void action(DirectionBean direction);
    }
}
