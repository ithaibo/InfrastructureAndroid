package com.andy.infrastructure.demos.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.andy.baselibrary.utils.CommonUtils;
import com.andy.baselibrary.utils.LogUtil;
import com.andy.infrastructure.BR;
import com.andy.infrastructure.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andy on 2017/9/5.
 */

public abstract class TagAdapter<T, DB extends ViewDataBinding> extends BaseAdapter {
    private final int DEFAULT_SINGLE_PICK = 1;
    private final int DEFAULT_MAX_PICK = 2;
    private int canPick = DEFAULT_MAX_PICK;//how many can pick.
    private List<TagData<T>> items;
    private LinkedList<Integer> selectedIndex;
    private SelectedManager selectedManager;

    public TagAdapter() {
        items = new ArrayList<>();
        selectedIndex = new LinkedList<>();
        selectedManager = defaultSelectedManager;
    }

    public TagAdapter(List<TagData<T>> items, LinkedList<Integer> selectedIndex, SelectedManager selectedManager) {
        this.items = items;
        this.selectedIndex = selectedIndex;
        this.selectedManager = selectedManager;
    }

    /**
     * 初始化选中tag
     *
     * @param initSelected 选中的tag position
     */
    public void initSelectedTags(LinkedList<Integer> initSelected) {
        if (selectedIndex != null) {
            this.selectedIndex = initSelected;
        }
    }

    public void setCanPick(int canPick) {
        if (canPick > DEFAULT_SINGLE_PICK) {
            this.canPick = canPick;
        } else {
            throw new IllegalArgumentException("count pick cannot less than 1.");
        }
    }

    public SelectedManager getSelectedManager() {
        return selectedManager;
    }

    public void setSelectedManager(SelectedManager selectedManager) {
        this.selectedManager = selectedManager;
    }

    @Override
    public int getCount() {
        return items == null || items.size() <= 0 ? 0 : items.size();
    }

    @Override
    public TagData<T> getItem(int position) {
        return items == null || items.size() <= 0 ? null : items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        DB itemBinding = DataBindingUtil.getBinding(convertView);
        if (itemBinding == null) {
            itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getItemLayout(), parent, false);
        }
        onBindView(itemBinding, getItem(position), position);

        final int index = position;
        itemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedIndex != null) {
                    if (selectedIndex.size() > 0) {
                        if (selectedManager.isItemSelected(index)) {
                            selectedManager.clearSelected(index);
                        } else {
                            selectedManager.setSelected(index);
                        }
                    } else {
                     //   no selected
                        selectedManager.setSelected(index);
                    }
                    LogUtil.i(TagAdapter.class.getSimpleName(), "data list: " + items);
                }
            }
        });
        return itemBinding.getRoot();
    }

    protected abstract int getItemLayout();

    /**
     * do something about view.
     * @param itemBind
     * @param data
     * @param position
     */
    protected abstract void onBindView(DB itemBind, TagData<T> data, int position);

    public void refreshItems(List<TagData<T>> datas) {
        if (datas == null) {
            return;
        }
        items = datas;
        notifyDataSetChanged();
    }

    public void appendItem(List<TagData<T>> datas) {
        if (CommonUtils.isListEmpty(datas)) {
            return;
        }
        if (items == null) {
            items = new ArrayList<>();
        }
        items.addAll(datas);
        notifyDataSetChanged();
    }

    private boolean isItemSelected(final int position) {
        if (selectedIndex == null || selectedIndex.size() <= 0) {
            return false;
        } else {
            for (Integer index : this.selectedIndex) {
                if (index.equals(position)) {
                    return true;
                }
            }
            return false;
        }
    }

    private SelectedManager defaultSelectedManager = new SelectedManager() {
        @Override
        public List<Integer> getSelected() {
            return TagAdapter.this.selectedIndex;
        }

        @Override
        public void setSelected(Integer position) {
            if(TagAdapter.this.selectedIndex!=null) {
                if (DEFAULT_SINGLE_PICK >= maxSelect()) {

                    clearAll();
                } else {
                    //出队列
                    if(TagAdapter.this.selectedIndex.size()>=maxSelect()) {
                        Integer polled = selectedIndex.pollFirst();
                        LogUtil.i(TagAdapter.this.getClass().getSimpleName(), "polled = " + polled);

                        if(polled!=null) {
                            items.get(polled).selected.set(false);
                        }
                    }
                }
                TagAdapter.this.selectedIndex.offerLast(position);
                getItem(position).selected.set(true);
                notifyDataSetChanged();
            }
        }

        @Override
        public void clearSelected(Integer position) {
            TagAdapter.this.selectedIndex.remove(position);
            getItem(position).selected.set(false);
            notifyDataSetChanged();
        }

        @Override
        public void clearAll() {
            selectedIndex = new LinkedList<>();
            for (int i=0; i<items.size(); i++) {
                items.get(i).selected.set(false);
            }
        }

        @Override
        public boolean isItemSelected(Integer position) {
            if (TagAdapter.this.selectedIndex == null || TagAdapter.this.selectedIndex.size() <= 0) {
                return false;
            } else {
                if (TagAdapter.this.selectedIndex.contains(position)){
                    return true;
                } else{
                    return false;
                }
            }
        }

        @Override
        public int maxSelect() {
            return TagAdapter.this.canPick;
        }
    };

    public interface SelectedManager {
        List<Integer> getSelected();

        void setSelected(Integer position);

        void clearSelected(Integer position);

        void clearAll();

        boolean isItemSelected(Integer position);

        int maxSelect();
    }
}
