package com.andy.infrastructure.demos.map;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andy on 2017/7/25.
 */

public class MapDataBuilder {
    String schemeHost;
    private final Map<String, String> data;

    public MapDataBuilder(String schemeHost) {
        this.schemeHost = schemeHost;
        data = new HashMap<>();
    }

    public MapDataBuilder addParamter(String paramKey, String paramValue){
        if (!TextUtils.isEmpty(paramKey) && !TextUtils.isEmpty(paramValue)) {
            data.put(paramKey, paramValue);
        }
        return this;
    }

    public String build() {
        StringBuilder builder = new StringBuilder();
        String paramBuild = "";

        if (data.size()>0) {
            Set<String> keySet = data.keySet();
            for (String key : keySet) {
                builder.append(key);
                builder.append("=");
                builder.append(data.get(key));
                builder.append("&");
            }
            builder.deleteCharAt(builder.length() - 1);
            paramBuild = builder.toString();
        }

        return schemeHost + "?" +paramBuild;
    }
}
