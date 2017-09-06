package com.andy.baselibrary.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andy on 2017/9/5.
 */

public abstract class TagAdapter<T, DB extends ViewDataBinding> extends BaseAdapter {
    private final int SINGLE_PICK = 1;
    private int canPick = SINGLE_PICK;//how many can pick.
    private List<TagData<T>> items;
    private LinkedList<Integer> selectedIndex;
    private SelectedManager selectedManager;

    public TagAdapter() {
        items = new ArrayList<>();
        selectedIndex = new LinkedList<>();
        selectedManager = createDefaultManager();
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
        if (canPick > SINGLE_PICK) {
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

        itemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedIndex != null) {
                    if (selectedIndex.size() > 0) {
                        if (selectedManager.isItemSelected(position)) {
                            selectedManager.clearSelected(position);
                        } else {
                            selectedManager.setSelected(position);
                        }
                    } else {
                        //   no selected
                        selectedManager.setSelected(position);
                    }
                    Log.i(TagAdapter.class.getSimpleName(), "data list: " + items);
                }
            }
        });
        return itemBinding.getRoot();
    }

    protected abstract int getItemLayout();

    /**
     * do something about view.
     *
     * @param itemBind
     * @param data
     * @param position
     */
    protected abstract void onBindView(final DB itemBind, final TagData<T> data, final int position);

    public void initItems(List<TagData<T>> initData) {
        refreshItems(initData);
    }

    public void refreshItems(List<TagData<T>> data) {
        if (data == null) {
            return;
        }
        items = data;
        notifyDataSetChanged();
    }

    public void appendItems(List<TagData<T>> data) {
        if (data == null || data.size() <= 0) {
            return;
        }
        if (items == null) {
            items = new ArrayList<>();
        }
        items.addAll(data);
        notifyDataSetChanged();
    }

    private boolean isItemSelected(final int position) {
        return selectedIndex == null &&
                selectedIndex.size() <= 0 &&
                selectedIndex.contains(position);
    }

    private SelectedManager createDefaultManager() {
        return new SelectedManager<T>() {
            @Override
            public List<TagData<T>> getSelected() {

                if (TagAdapter.this.selectedIndex == null || TagAdapter.this.selectedIndex.size() <= 0)
                    return null;
                else {
                    List<TagData<T>> selectedTag = new ArrayList<>();
                    for (Integer index : TagAdapter.this.selectedIndex) {
                        selectedTag.add(TagAdapter.this.getItem(index));
                    }
                    return selectedTag;
                }
            }

            @Override
            public void setSelected(Integer position) {
                if (TagAdapter.this.selectedIndex != null) {
                    if (SINGLE_PICK >= maxSelect()) {
                        clearAll();
                    } else {
                        //如果已满则将第一个出队列
                        if (TagAdapter.this.selectedIndex.size() >= maxSelect()) {
                            Integer polled = selectedIndex.pollFirst();
                            Log.i(TagAdapter.this.getClass().getSimpleName(), "polled = " + polled);

                            if (polled != null) {
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
                for (int i = 0; i < items.size(); i++) {
                    items.get(i).selected.set(false);
                }
            }

            @Override
            public boolean isItemSelected(Integer position) {
                return TagAdapter.this.selectedIndex != null &&
                        TagAdapter.this.selectedIndex.size() > 0 &&
                        TagAdapter.this.selectedIndex.contains(position);
            }

            @Override
            public int maxSelect() {
                return TagAdapter.this.canPick;
            }
        };
    }

    public interface SelectedManager<T> {
        List<TagData<T>> getSelected();

        void setSelected(Integer position);

        void clearSelected(Integer position);

        void clearAll();

        boolean isItemSelected(Integer position);

        int maxSelect();
    }

    public static class TagData<T> {
        private T data;
        final public ObservableBoolean selected = new ObservableBoolean(false);

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "TagData{" +
                    "data=" + data +
                    ", selected=" + selected.get() +
                    '}';
        }

        public final TagData<T> wapper(T source, boolean selected) {
            TagData<T> target = new TagData<>();
            target.setData(source);
            target.selected.set(selected);
            return target;
        }

        public final T DeWrapper() {
            return getData();
        }
    }
}
