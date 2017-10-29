package com.ithaibo.sample.vm;

import android.content.Intent;
import android.view.View;

import com.ithaibo.sample.activity.FragmentContainerActivity;

/**
 * Created by Smily on 2017/10/29.
 */

public class LauncherVM {
	public void toFrgContainer(View view) {
		Intent intent = new Intent(view.getContext(), FragmentContainerActivity.class);
		view.getContext().startActivity(intent);
	}
}
