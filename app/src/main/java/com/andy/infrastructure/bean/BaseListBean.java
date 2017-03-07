package com.andy.infrastructure.bean;

import java.util.List;

/**
 * Created by Andy on 2017/3/7.
 */

public class BaseListBean<T> extends Bean {
    private List<T> results;

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
