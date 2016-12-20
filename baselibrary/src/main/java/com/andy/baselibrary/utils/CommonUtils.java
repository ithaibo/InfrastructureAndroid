package com.andy.baselibrary.utils;

import java.util.List;

/**
 * Created by Andy on 2016/11/15.
 */

public class CommonUtils {
    public static boolean isListEmpty(List list) {
        if (list == null) {
            return true;
        } else if (list.size() <= 0) {
            return true;
        } else {
            return false;
        }
    }
}
