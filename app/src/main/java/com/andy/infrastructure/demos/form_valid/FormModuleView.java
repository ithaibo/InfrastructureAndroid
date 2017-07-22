package com.andy.infrastructure.demos.form_valid;

import android.databinding.ObservableField;
import android.text.TextUtils;
import android.view.View;

import com.andy.formmatcher.FilterChain;
import com.andy.formmatcher.MatchStrategy;
import com.andy.formmatcher.strategy.NumberStrategy;
import com.andy.formmatcher.strategy.TextRegularStrategy;

import java.util.Map;

/**
 * Created by Andy on 2017/7/22.
 */

public class FormModuleView {
    public final ObservableField<String> mobile = new ObservableField<>("");
    public final ObservableField<String> number = new ObservableField<>("");

    public final ObservableField<String> mobileResult = new ObservableField<>("");
    public final ObservableField<String> numberResult = new ObservableField<>("");

    public final Action matchAction = new Action() {
        public final String KEY_MOBILE = "MOBILE";
        public final String KEY_NUMBER = "NUMBER";

        @Override
        public void action(View view) {
            String mobileRegular = "1[356789]\\d{9}";
            new FilterChain.Builder()
                    .addFilter(KEY_MOBILE, new MatchStrategy(mobile.get(), new TextRegularStrategy(mobileRegular)))
                    .addFilter(KEY_NUMBER, new MatchStrategy((TextUtils.isEmpty(number.get())?0:Integer.valueOf(number.get())),
                            new NumberStrategy(1, 5, NumberStrategy.TYPE_BOTH)))
                    .build()
                    .startFilter(new FilterChain.FilterCallBack() {
                        @Override
                        public void onFilterCompleted(Map<String, Boolean> result) {
                            mobileResult.set("value: " + mobile.get().toString() + ", result: " + result.get(KEY_MOBILE));
                            numberResult.set("value: " + (TextUtils.isEmpty(number.get())?"":Integer.valueOf(number.get())) + ", result: " + result.get(KEY_NUMBER));
                        }
                    });
        }
    };


    public interface Action{
        void action(View view);
    }
}
