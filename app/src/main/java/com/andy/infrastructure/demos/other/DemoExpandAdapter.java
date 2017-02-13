package com.andy.infrastructure.demos.other;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.andy.infrastructure.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Andy on 2017/2/13.
 */

public class DemoExpandAdapter implements ExpandableListAdapter {
    private ArrayList<String> groupList;
    private HashMap<String, ArrayList<Game>> childMap;
    private Context context;

    private ArrayList<HashMap<String, String>> childList;
    private HashMap<String, String> childItem;

    public DemoExpandAdapter(ArrayList<String> groupList, HashMap<String, ArrayList<Game>> childMap, Context context) {
        this.groupList = groupList;
        this.childMap = childMap;
        this.context = context;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childMap.get(groupList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childMap.get(groupList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * 设置Group Item View
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = null;
        GroupViewHolder groupViewHolder = null;
        if(convertView==null){
            view = (View) View.inflate(context, R.layout.item_group_layout, null);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.groupName = (TextView) view.findViewById(R.id.groupName);
            view.setTag(groupViewHolder);
        }else {
            view = convertView;
            groupViewHolder = (GroupViewHolder) view.getTag();
        }
        groupViewHolder.groupName.setText(groupList.get(groupPosition));
        return view;
    }

    /**
     * 设置某个组的Child View
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = null;
        ChildViewHolder childViewHolder = null;
        if (convertView == null) {
            view = View.inflate(context, R.layout.order_item_layout, null);
            childViewHolder = new ChildViewHolder();
            childViewHolder.tag = (TextView) view.findViewById(R.id.tag);
            childViewHolder.price = (TextView) view.findViewById(R.id.price);
            view.setTag(childViewHolder);
        }else {
            view = convertView;
            childViewHolder = (ChildViewHolder) view.getTag();
        }
        String key = groupList.get(groupPosition);
        ArrayList<Game> groupItem = childMap.get(key);
        Game childItem = groupItem.get(childPosition);
        String tag = childItem.getTag();
        String price = childItem.getPrice();
        childViewHolder.tag.setText(tag);
        childViewHolder.price.setText(price);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

    private class ChildViewHolder {
        TextView tag;
        TextView price;
    }

    private class GroupViewHolder {
        TextView groupName;
//        ImageView iv;
    }
}
