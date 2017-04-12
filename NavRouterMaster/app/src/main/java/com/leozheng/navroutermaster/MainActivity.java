package com.leozheng.navroutermaster;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.leozheng.navrouter.NavRouter;
import com.leozheng.navrouter.NavRouterCallBack;

import java.util.ArrayList;


/**
 * Show how to user NavCallBack in activity
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn_nav1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * Show the custom NavRouterCallBack.Compared with default callback {@link RouterApplication}
                 */
                NavRouter.from(MainActivity.this).toUri(((TextView) v).getText().toString(), -1, new NavRouterCallBack() {
                    @Override
                    public boolean beforeOpen() {
                        Log.d("NavRouter", "beforeOpen from " + MainActivity.class.getSimpleName());
                        Toast.makeText(MainActivity.this, MainActivity.class.getSimpleName() + " was reject in Activity", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public void afterOpen() {
                        Log.d("NavRouter", "afterOpen from " + MainActivity.class.getSimpleName());

                    }

                    @Override
                    public void onError(Intent intent) {
                        Log.d("NavRouter", "beforeOpen from " + MainActivity.class.getSimpleName());

                    }
                });
            }
        });

        findViewById(R.id.btn_nav2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavRouter.from(MainActivity.this).toUri(((TextView) v).getText().toString());
            }
        });

        findViewById(R.id.btn_nav3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavRouter.from(MainActivity.this).toUri(((TextView) v).getText().toString());
            }
        });

        findViewById(R.id.btn_nav4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                ArrayList<Integer> intList = new ArrayList();
                intList.add(1);
                intList.add(3);
                bundle.putIntegerArrayList("extra",intList);
                NavRouter.from(MainActivity.this).putExtra("bundle",bundle).toUri(((TextView) v).getText().toString());
            }
        });

        findViewById(R.id.btn_nav5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavRouter.from(MainActivity.this).toUri(((TextView) v).getText().toString());
            }
        });

        findViewById(R.id.btn_nav6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder finalUrl =new StringBuilder();
                finalUrl.append(((TextView) v).getText().toString());
                finalUrl.append("?loadUrl=");
                finalUrl.append(Uri.encode("file:///android_asset/testHtml.html"));
                NavRouter.from(MainActivity.this).toUri(finalUrl.toString());
            }
        });


    }
}
