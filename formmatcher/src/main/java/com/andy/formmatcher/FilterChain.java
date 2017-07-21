package com.andy.formmatcher;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import java.util.List;

/**
 * Created by Andy on 2017/7/21.
 */

public class FilterChain {
    /**
     * add multiple Filter, such as:
     * 1. regular filter
     * 2. changes filter between current and history
     * ... and so on.
     */

    private List<BaseMatchFilter> filterList;
    private ArrayMap<String, BaseMatchFilter> filterMap;

    public static class Builder {
        private FilterChain filterChain;
        public Builder() {
            filterChain = new FilterChain();
            filterChain.filterMap = new ArrayMap<>();
        }

        public Builder addFilter(String filterKey, BaseMatchFilter filter) {
            if(!TextUtils.isEmpty(filterKey) && filter!=null) {
                filterChain.filterMap.put(filterKey, filter);
            }
            return this;
        }

        public FilterChain build() {
            return filterChain;
        }
    }

    private FilterChain() {}

    public void startFilte() {
        //
    }



    public void matchItem(){

    }
}
