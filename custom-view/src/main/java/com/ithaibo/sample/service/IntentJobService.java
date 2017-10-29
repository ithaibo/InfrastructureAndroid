package com.ithaibo.sample.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by Andy on 2017/9/15.
 */

public class IntentJobService extends IntentService {
    public IntentJobService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
