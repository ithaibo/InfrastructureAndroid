package com.andy.infrastructure.adapter;

import com.andy.infrastructure.R;
import com.andy.infrastructure.databinding.ItemTagBinding;
import com.andy.infrastructure.demos.view.TagAdapter;
import com.andy.infrastructure.demos.view.TagData;

/**
 * Created by Andy on 2017/9/5.
 */

public class GridTagAdapter extends TagAdapter<String, ItemTagBinding> {
    @Override
    protected int getItemLayout() {
        return R.layout.item_tag;
    }

    @Override
    protected void onBindView(ItemTagBinding itemBind, TagData<String> data, int position) {
        itemBind.tagLabel.setText(data.getData());
        itemBind.setTagData(data);
    }
}
