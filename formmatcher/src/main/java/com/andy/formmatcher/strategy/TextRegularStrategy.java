package com.andy.formmatcher.strategy;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Created by Andy on 2017/7/21.
 */

public class TextRegularStrategy implements BaseStrategy<String> {
    private String regular;

    public TextRegularStrategy(String regular) {
        this.regular = regular;
    }

    @Override
    public boolean match(String value) {
        if (TextUtils.isEmpty(regular) || TextUtils.isEmpty(value)){
            return false;
        }
        return Pattern.matches(regular, value);
    }
}
