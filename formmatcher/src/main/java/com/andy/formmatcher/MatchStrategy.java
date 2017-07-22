package com.andy.formmatcher;

import com.andy.formmatcher.strategy.BaseStrategy;

/**
 * Created by Andy on 2017/7/22.
 */

public class MatchStrategy<T, ST extends BaseStrategy> {
    private T data;
    private ST strategy;

    public MatchStrategy(T data, ST strategy) {
        this.data = data;
        this.strategy = strategy;
    }

    public boolean doMatchAction() {
        return strategy.match(data);
    }
}
