package com.andy.formmatcher.strategy;

/**
 * Created by Andy on 2017/7/22.
 */

public interface BaseStrategy<T> {
    boolean match(T value);
}
