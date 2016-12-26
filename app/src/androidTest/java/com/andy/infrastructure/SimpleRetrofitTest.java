package com.andy.infrastructure;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.andy.infrastructure.demos.retrofit.SimpleRetrofit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Andy on 2016/12/26.
 */
@RunWith(AndroidJUnit4.class)
public class SimpleRetrofitTest {

    @Rule
    ActivityTestRule<SimpleRetrofit> rule = new ActivityTestRule<>(SimpleRetrofit.class);

    @Test void testOnClick() {
        Espresso.onView(ViewMatchers.withId(R.id.btn_get_net_act_simple_retrofit))
                .perform(ViewActions.click());
    }
}
