package com.andy.infrastructure.base;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;

import com.andy.baselibrary.fragment.DataBindFrgment;
import com.andy.infrastructure.CommonWebFragmentBind;
import com.andy.infrastructure.R;

/**
 * Created by Andy on 2017/3/15.
 */

public class BaseWebFragment extends DataBindFrgment {

    private WebSettings webSettings;

    @Override
    protected int getFrgLayoutId() {
        return R.layout.frg_common_web;
    }

    @Override
    protected void initView(View rootView) {
        CommonWebFragmentBind webFragmentBind = (CommonWebFragmentBind) dataBinder;

        initWebSet(webFragmentBind);
    }

    private void initWebSet(CommonWebFragmentBind bind) {
        webSettings = bind.webViewCommon.webViewCommon.getSettings();
        webSettings.setAllowContentAccess(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setJavaScriptEnabled(true);

        bind.webViewCommon.webViewCommon.setWebChromeClient(initWebChromeCLient());
    }

    public WebSettings getWebSettings() {
        return webSettings;
    }

    protected WebChromeClient initWebChromeCLient() {
        WebChromeClient client = new WebChromeClient();

        return client;
    }
}
