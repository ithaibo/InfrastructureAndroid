package com.andy.infrastructure;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;


import com.andy.infrastructure.demos.rxjava.DemoRxJavaActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;

/**
 * Created by Andy on 2016/12/22.
 */
@RunWith(AndroidJUnit4.class)
public class DemoRxJavaActivityTest {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule(DemoRxJavaActivity.class);

    @Test
    public void testInputAndDisplay() {
        Espresso.
                onView(ViewMatchers.withId(R.id.etInput))
                .perform(typeText("13812340000"), closeSoftKeyboard());
    }
}
