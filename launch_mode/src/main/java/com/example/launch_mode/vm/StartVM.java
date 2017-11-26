package com.example.launch_mode.vm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import com.example.launch_mode.R;
import com.example.launch_mode.SingleInstanceActivity;
import com.example.launch_mode.SingleTaskActivity;
import com.example.launch_mode.SingleTopActivity;
import com.example.launch_mode.StandardActivity;


/**
 * Created by Smily on 2017/11/26.
 */

public class StartVM {
	public final ObservableField<View.OnClickListener> btnClicker = new ObservableField<>();
	public final ObservableField<String> label = new ObservableField<>("");
	public final ObservableField<RadioGroup.OnCheckedChangeListener> checkedChangeListener = new ObservableField<>();

	private Class<? extends Activity> targetAct;

	public StartVM() {
		btnClicker.set(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (targetAct == null) {
					return;
				}
				startActivity(v.getContext(), targetAct);
			}
		});

		checkedChangeListener.set(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				Log.i("Launch Mode", "radio button id: " + checkedId);

				switch (checkedId) {
					case R.id.rb1:
						targetAct = StandardActivity.class;
						break;
					case R.id.rb2:
						targetAct = SingleTopActivity.class;
						break;
					case R.id.rb3:
						targetAct = SingleTaskActivity.class;
						break;
					case R.id.rb4:
						targetAct = SingleInstanceActivity.class;
						break;
				}
			}
		});
	}

	private void startActivity(Context context, Class<? extends Activity> act) {
		context.startActivity(new Intent(context, act));
	}
}
