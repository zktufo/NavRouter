package com.leozheng.navroutermaster;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.leozheng.navrouter.NavWebViewClient;
import com.leozheng.navrouter.ParamUtils;

/**
 * Created by zhengkaituo on 2017/4/10.
 */

public class BrowserActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        setContentView(linearLayout);

        WebView webView = new WebView(this);
        webView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new NavWebViewClient(this));

        linearLayout.addView(webView);

        String url  = Uri.decode(ParamUtils.getStringExtra(appLinkData,"loadUrl"));

        webView.loadUrl(url);

    }
}
