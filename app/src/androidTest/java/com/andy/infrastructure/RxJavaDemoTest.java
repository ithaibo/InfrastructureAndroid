package com.andy.infrastructure;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;


import com.andy.infrastructure.demos.rxjava.DemoRxJavaActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Andy on 2016/12/22.
 */

@RunWith(AndroidJUnit4.class)
public class RxJavaDemoTest
        extends ActivityInstrumentationTestCase2<DemoRxJavaActivity> {
    private DemoRxJavaActivity mActivity;

    public RxJavaDemoTest(){
        super(DemoRxJavaActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        // Injecting the Instrumentation instance is required
        // for your test to run with AndroidJUnitRunner.
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mActivity = getActivity();
    }

    @Test
    public void requestNetAndShowData() {
        TouchUtils.clickView(this, mActivity.findViewById(R.id.btnShowText));
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

}
