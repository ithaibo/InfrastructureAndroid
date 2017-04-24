package com.andy.baselibrary.activity;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by andy on 17-4-4.
 */

public abstract class BaseWebActtivity extends DataBindActivity {
    protected abstract WebView getWebView();
    protected WebChromeClient webChromeClient;

    @Override
    protected void initViews() {
        webChromeClient = getWebChromeClinet();

        initWebView(getWebView());
    }

    private void initWebView(WebView webView) {
        webView.setNetworkAvailable(true);
        webView.setWebChromeClient(getWebChromeClinet());
    }

    protected WebChromeClient getWebChromeClinet() {
        WebChromeClient client = new WebChromeClient() {

        };

        return client;
    }
}
