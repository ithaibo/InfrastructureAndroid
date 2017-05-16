package com.andy.infrastructure;

import android.test.AndroidTestCase;

import com.andy.baselibrary.utils.NetWorkUtils;


/**
 * Created by Andy on 2017/5/16.
 */

public class WifiValidTest extends AndroidTestCase {
    public void testWifiValid(){
        assertTrue(NetWorkUtils.isInternetAvailableByPing());
    }
}
