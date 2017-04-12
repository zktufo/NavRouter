package com.leozheng.navrouter;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * This a custom webviewclient to manage the url
 * <p>
 * Created by zhengkaituo on 2017/4/10.
 */

public class NavWebViewClient extends WebViewClient {

    private Context mContext;

    public NavWebViewClient(Context context) {
        mContext = context;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        if (url.trim().contains("isForceWap=true")) {
            view.loadUrl(url);
        } else {
            if (!NavRouter.from(mContext).toUri(url)) {
                view.loadUrl(url);
            }
        }
        return true;
    }
}
