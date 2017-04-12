package com.leozheng.navroutermaster;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leozheng.navrouter.ParamUtils;

/**
 * Created by zhengkaituo on 2017/4/10.
 */

public class TestMultiParamActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        setContentView(linearLayout);

        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        linearLayout.addView(textView);

        int year = ParamUtils.getIntExtra(appLinkData, "year", 0);
        double amount = ParamUtils.getDoubleExtra(appLinkData, "amount", 0.0);
        String name = ParamUtils.getStringExtra(appLinkData, "name");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("The param is : year -> " + String.valueOf(year) + "\n");
        stringBuilder.append("The param is : amount -> " + String.valueOf(amount) + "\n");
        stringBuilder.append("The param is : name -> " + name + "\n");

        textView.setText(stringBuilder);
    }
}
