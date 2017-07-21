package com.andy.formmatcher.matcher;

/**
 * Created by Andy on 2017/7/21.
 */

public interface TextRegularMatcher {
    boolean matched(CharSequence value, String regular);
}
