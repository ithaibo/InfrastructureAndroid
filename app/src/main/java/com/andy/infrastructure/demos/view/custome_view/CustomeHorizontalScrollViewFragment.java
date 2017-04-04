package com.andy.infrastructure.demos.view.custome_view;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.andy.baselibrary.fragment.BaseFragment;
import com.andy.baselibrary.fragment.DataBindFrgment;
import com.andy.infrastructure.CustomeHorizontalViewBinding;
import com.andy.infrastructure.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by andy on 17-4-4.
 */

public class CustomeHorizontalScrollViewFragment extends DataBindFrgment {
    @Override
    protected int getFrgLayoutId() {
        return R.layout.frg_custome_view_horizontal_scroll_view;
    }

    @Override
    protected void initView(View rootView) {
        final int screenWidth = mContext.getResources().getDisplayMetrics().widthPixels;
        final int screenHeight = mContext.getResources().getDisplayMetrics().heightPixels;

        CustomeHorizontalViewBinding horizontalViewBinding = (CustomeHorizontalViewBinding) dataBinder;
        horizontalViewBinding.hsCustom.removeAllViews();

        for (int i=0; i<3; i++) {
            ViewGroup child = (ViewGroup) mContext.getLayoutInflater()
                    .inflate(R.layout.layout_content_scroll_item,
                            horizontalViewBinding.hsCustom, false);
            child.getLayoutParams().width = screenWidth;
            TextView tvTitle = (TextView) child.findViewById(R.id.title);
            tvTitle.setText("Page: " + (i+1));
            child.setBackgroundColor(Color.rgb(255/(i+1), 255/(i+1), 0));
            createList(child);
            horizontalViewBinding.hsCustom.addView(child);
        }
    }

    private void createList(ViewGroup child) {
        ListView listView = (ListView) child.findViewById(R.id.list);
        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            datas.add("name " + i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext,
                R.layout.content_list_item, R.id.name, datas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(mContext, "click item",
                        Toast.LENGTH_SHORT).show();

            }
        });
    }
}
