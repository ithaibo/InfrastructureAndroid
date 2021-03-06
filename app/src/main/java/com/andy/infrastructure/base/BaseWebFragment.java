package com.andy.infrastructure.base;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;

import com.andy.baselibrary.fragment.DataBindFrgment;
import com.andy.baselibrary.utils.LogUtil;
import com.andy.infrastructure.R;

/**
 * Created by Andy on 2017/3/15.
 */

public abstract class BaseWebFragment extends DataBindFrgment<com.andy.infrastructure.CommonWebFragmentBind> {

    private WebSettings webSettings;

    @Override
    protected int getFrgLayoutId() {
        return R.layout.frg_common_web;
    }

    @Override
    protected void initView(View rootView) {
        initWebSet();
    }

    private void initWebSet() {
        webSettings = dataBinder.webViewCommon.webViewCommon.getSettings();
        webSettings.setAllowContentAccess(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setJavaScriptEnabled(true);//允许加载JS
        webSettings.setSupportZoom(false);  //禁止缩放
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        dataBinder.webViewCommon.webViewCommon.setWebChromeClient(initWebChromeCLient());

        dataBinder.webViewCommon.webViewCommon.loadUrl(getHtmlUrl());
    }

    public WebSettings getWebSettings() {
        return webSettings;
    }

    protected WebChromeClient initWebChromeCLient() {
        int screenDensity = getResources().getDisplayMetrics().densityDpi ;

        LogUtil.i("屏幕分辨率--" + screenDensity);

        WebChromeClient client = new WebChromeClient();

        return client;
    }

    protected abstract String getHtmlUrl();
}
