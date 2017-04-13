package com.andy.baselibrary.utils;

import android.content.Context;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Andy on 2017/4/10.
 */

public class ResourceUtils {
    private static final String jquery_2_1_4 = "jquery.2.1.4.min.js";

    private static final int RESOURCE_TYPE_ASSETS = 1;
    private static final int RESOURCE_TYPE_EXTERNAL_STORAGE = 2;

    public static void loadJqueryFromAssets(final WebView webView) {
        webView.loadUrl("javascript:" +
                ResourceUtils.loadResourceStringFromAssets(webView.getContext(), jquery_2_1_4));
    }

    public static String loadResourceStringFromAssets(final Context c, final String path){
       return file2Str(c, path, RESOURCE_TYPE_ASSETS);
    }

    public static String loadResourceStringFromExternalStorage(final Context c, final String absolutePath){
        return file2Str(c, absolutePath, RESOURCE_TYPE_EXTERNAL_STORAGE);
    }

    public static String file2Str(Context c, String urlStr, int resourceType) {
        InputStream in = null;
        try {
            switch (resourceType) {
                case RESOURCE_TYPE_ASSETS:
                    in = getInSFromAssets(c, urlStr);
                    break;
                case RESOURCE_TYPE_EXTERNAL_STORAGE:
                    in = getInSExternalStorage(c, urlStr);
                    break;
                default:
                    return null;
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            StringBuilder sb = new StringBuilder();
            do {
                line = bufferedReader.readLine();
                if (line != null && !line.matches("^\\s*\\/\\/.*")) {
                    sb.append(line);
                }
            } while (line != null);

            bufferedReader.close();
            in.close();

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

    public static InputStream getInSExternalStorage(Context context, String path) {
        InputStream in = null;

        return in;
    }

    public static InputStream getInSFromAssets(Context c, String urlStr){
        InputStream in = null;
        try {
            in = c.getAssets().open(urlStr);
        } catch (Exception e) {
            LogUtil.e(e.toString());
        }

        return in;
    }

}
