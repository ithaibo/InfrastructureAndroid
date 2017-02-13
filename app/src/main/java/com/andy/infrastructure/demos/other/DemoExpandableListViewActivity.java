package com.andy.infrastructure.demos.other;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.andy.infrastructure.R;

import java.util.ArrayList;
import java.util.HashMap;

public class DemoExpandableListViewActivity extends AppCompatActivity {

    private ExpandableListView epls;
    private ExpandableListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_expandable_list_view);
        epls = (ExpandableListView) findViewById(R.id.epls);
        initData();             //初始化数据
        initAdapter();          //设置Adapter
        epls.expandGroup(0);    //将第一个组展开
    }

    private void initAdapter() {
        adapter = new DemoExpandAdapter(groupList, childMap, this);
        epls.setAdapter(adapter);
    }

    private ArrayList<String> groupList; //订单组
    private HashMap<String, ArrayList<Game>> childMap;

    private ArrayList<Game> gameList_1;
    private ArrayList<Game> gameList_2;
    private ArrayList<Game> gameList_3;

    private void initData() {

        groupList = new ArrayList<String>();
        childMap = new HashMap<String, ArrayList<Game>>();

        gameList_1 = new ArrayList<Game>();
        gameList_2 = new ArrayList<Game>();
        gameList_3 = new ArrayList<Game>();

        groupList.add("今天");
        groupList.add("明天");
        groupList.add("后天");

        gameList_1.add(new Game("篮球", "5"));
        gameList_1.add(new Game("足球", "10"));
        gameList_1.add(new Game("篮球", "5"));
        gameList_1.add(new Game("足球", "10"));
        gameList_1.add(new Game("篮球", "5"));
        gameList_1.add(new Game("足球", "10"));
        gameList_1.add(new Game("篮球", "5"));
        gameList_1.add(new Game("足球", "10"));
        gameList_1.add(new Game("篮球", "5"));
        gameList_1.add(new Game("足球", "10"));
        gameList_1.add(new Game("篮球", "5"));
        gameList_1.add(new Game("足球", "10"));
        gameList_1.add(new Game("篮球", "5"));
        gameList_1.add(new Game("足球", "10"));
        gameList_1.add(new Game("篮球", "5"));
        gameList_1.add(new Game("足球", "10"));
        gameList_1.add(new Game("篮球", "5"));
        gameList_1.add(new Game("足球", "10"));
        gameList_1.add(new Game("篮球", "5"));
        gameList_1.add(new Game("足球", "10"));
        gameList_1.add(new Game("篮球", "5"));
        gameList_1.add(new Game("足球", "10"));
        gameList_1.add(new Game("篮球", "5"));
        gameList_1.add(new Game("足球", "10"));
        gameList_1.add(new Game("篮球", "5"));
        gameList_1.add(new Game("足球", "10"));

        gameList_2.add(new Game("篮球", "6"));
        gameList_2.add(new Game("足球", "11"));

        gameList_3.add(new Game("篮球", "7"));
        gameList_3.add(new Game("足球", "12"));


        childMap.put("今天", gameList_1);
        childMap.put("明天", gameList_2);
        childMap.put("后天", gameList_3);
    }


    private ExpandableListView.OnGroupClickListener onGroupClickListener;

    private void initGroupClickListenner () {
        onGroupClickListener = new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                selectedGourpId = groupPosition;
                return false;
            }
        };
    }

    private int selectedGourpId;

    public int getSelectedGroupId() {
        return selectedGourpId;
    }
}
