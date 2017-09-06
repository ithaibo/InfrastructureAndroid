package com.andy.infrastructure.demos.view.official;

import com.andy.baselibrary.activity.DataBindActivity;
import com.andy.infrastructure.R;
import com.andy.infrastructure.adapter.GridTagAdapter;
import com.andy.infrastructure.databinding.ActViewGridTagBinding;
import com.andy.baselibrary.adapter.TagAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2017/9/5.
 */

public class GridTagActivity extends DataBindActivity<ActViewGridTagBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.act_view_grid_tag;
    }

    @Override
    protected void initData() {
        mDataBind.setAdapter(new GridTagAdapter());

        List<TagAdapter.TagData<String>> tagList = new ArrayList<>();
        String[] tags = new String[]{
                "餐饮",
                "生鲜果蔬",
                "超市商品",
                "鲜花蛋糕",
                "其他"
        };

        for (int i = 0; i < tags.length; i++) {
            TagAdapter.TagData<String> item = new TagAdapter.TagData<>();
            item.setData(tags[i]);
            tagList.add(item);
        }

        mDataBind.getAdapter().refreshItems(tagList);
    }

    @Override
    protected void initViews() {

    }
}
