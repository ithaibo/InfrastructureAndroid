package com.andy.formmatcher;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.andy.formmatcher.strategy.NumberStrategy;
import com.andy.formmatcher.strategy.TextRegularStrategy;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        final String text = "18221545218";
        int intV = 4;

        String mobileRegular = "1[356789]\\d{9}";

        new FilterChain.Builder().addFilter("text", new MatchStrategy(text, new TextRegularStrategy(mobileRegular)))
                .addFilter("intV", new MatchStrategy(intV, new NumberStrategy(1, 5, NumberStrategy.TYPE_BOTH)))
                .build()
                .startFilter(new FilterChain.FilterCallBack() {
                    @Override
                    public void onFilterCompleted(Map<String, Boolean> result) {
                        assert(true == result.get("text"));
                        assert (result.get("intV"));
                    }
                });

    }
}
