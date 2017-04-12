package com.leozheng.navroutermaster;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.leozheng.navrouter.NavRouterCallBack;
import com.leozheng.navrouter.NavRouterCallBackFactory;

/**
 * Created by zhengkaituo on 2017/4/10.
 */

public class RouterApplication extends Application implements NavRouterCallBackFactory {

    @Override
    public NavRouterCallBack provideNavRouterCallBack() {
        return new NavRouterCallBack() {
            @Override
            public boolean beforeOpen() {
                Log.d("NavRouter", "beforeOpen from Application");

                return true;
            }

            @Override
            public void afterOpen() {
                Log.d("NavRouter", "afterOpen from Application");
            }

            @Override
            public void onError(Intent intent) {
                Toast.makeText(getApplicationContext(), "There is no activity matched for " + intent, Toast.LENGTH_SHORT).show();
            }
        };
    }
}
