package com.andy.formmatcher.matcher;

/**
 * Created by Andy on 2017/7/21.
 */

public interface TextLengthMatcher {
    int TYPE_LENGTH_REGULAR_MIN = 1;
    int TYPE_LENGTH_REGULAR_MAX = 2;
    int TYPE_LENGTH_REGULAR_BOTH = 4;

    boolean matched(CharSequence value, int lenMin, int lenMax, int type);
}
