package com.leozheng.navroutermaster;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by zhengkaituo on 2017/4/9.
 */

public class BaseActivity extends AppCompatActivity {

    protected Uri appLinkData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getClass().getSimpleName());

        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        appLinkData = appLinkIntent.getData();

        Log.d("appLinkInfo", appLinkAction);

    }
}
