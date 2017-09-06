package com.andy.infrastructure.adapter;

import com.andy.infrastructure.R;
import com.andy.infrastructure.databinding.ItemTagBinding;
import com.andy.baselibrary.adapter.TagAdapter;

/**
 * Created by Andy on 2017/9/5.
 */

public class GridTagAdapter extends TagAdapter<String, ItemTagBinding> {
    @Override
    protected int getItemLayout() {
        return R.layout.item_tag;
    }

    @Override
    protected void onBindView(final ItemTagBinding itemBind, final TagAdapter.TagData<String> data, final int position) {
        itemBind.tagLabel.setText(data.getData());
        itemBind.setTagData(data);
    }
}
