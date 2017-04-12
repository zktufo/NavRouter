package com.leozheng.navroutermaster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by zhengkaituo on 2017/4/11.
 */

public class SubTestActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getBundleExtra("bundle");

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        setContentView(linearLayout);

        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        linearLayout.addView(textView);

        ArrayList<Integer> intList =bundle.getIntegerArrayList("extra");

        StringBuilder stringBuilder = new StringBuilder();

        for(int item:intList){
            stringBuilder.append(item+" ");
        }

        textView.setText("The bundle param is : year -> list:{" +stringBuilder+ "}" );
    }
}
