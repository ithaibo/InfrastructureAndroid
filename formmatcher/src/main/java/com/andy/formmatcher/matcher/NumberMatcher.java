package com.andy.formmatcher.matcher;

/**
 * Created by Andy on 2017/7/21.
 */

public interface NumberMatcher {
    int TYPE_MIN = 1;
    int TYPE_MAX = 2;
    int TYPE_BOTH = 4;
    int TYPE_EQUAL = 8;
    boolean matched(Number number, Number minValue, Number maxValue, Number equalValue, int type);
}
