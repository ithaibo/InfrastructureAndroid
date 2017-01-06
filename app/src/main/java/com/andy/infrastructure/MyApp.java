package com.andy.infrastructure;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by Andy on 2017/1/4.
 */

public class MyApp extends TinkerApplication {
    public MyApp() {
        super(ShareConstants.TINKER_ENABLE_ALL,
                "com.andy.infrastructure.MyApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader",
                false);
    }


}
