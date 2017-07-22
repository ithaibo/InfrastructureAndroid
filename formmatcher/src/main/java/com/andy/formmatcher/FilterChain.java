package com.andy.formmatcher;

import android.os.AsyncTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    private Map<String, MatchStrategy> filterMap;
    private Map<String, Boolean> result;

    public static class Builder {
        private FilterChain filterChain;
        public Builder() {
            filterChain = new FilterChain();
            filterChain.filterMap = new HashMap<>();
        }

        public Builder addFilter(String filterKey, MatchStrategy filter) {
            if(filterKey!=null && filter!=null) {
                filterChain.filterMap.put(filterKey, filter);
            }
            return this;
        }

        public FilterChain build() {
            return filterChain;
        }
    }

    private FilterCallBack callBack;

    private FilterChain() {}

    public void startFilter(FilterCallBack callBack) {
        this.callBack = callBack;
        new MatchTask().execute(filterMap);
    }

    private void startFilter() {
        new MatchTask().execute(filterMap);
    }

    public void addFilter(String filterKey, MatchStrategy filter) {
        if(filterMap!= null && filterKey!=null && filter!=null) {
            this.filterMap.put(filterKey, filter);
        }
        startFilter();
    }

    private class MatchTask extends AsyncTask<Map<String, MatchStrategy>, Integer, Map<String, Boolean>>{
        @Override
        protected Map<String, Boolean> doInBackground(Map<String, MatchStrategy>... params) {
            if (filterMap == null || filterMap.size()<=0) {
                return null;
            }

            Set<String> filterKeySet = filterMap.keySet();
            if (filterKeySet == null || filterKeySet.size()<=0) {
                return null;
            }

            Map<String, Boolean> result = new HashMap<>();
            for (String key : filterKeySet) {
                MatchStrategy strategy = filterMap.get(key);
                if (strategy !=null) {
                    result.put(key, strategy.doMatchAction());
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(Map<String, Boolean> stringBooleanMap) {
            super.onPostExecute(stringBooleanMap);
            result = stringBooleanMap;
            if (callBack !=null) {
                callBack.onFilterCompleted(result);
            }
        }
    }

    public interface FilterCallBack{
        void onFilterCompleted(Map<String, Boolean> result);
    }
}
