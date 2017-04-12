package com.leozheng.navrouter;

import android.content.Intent;

/**
 * Callback for showing the process of NavRouter
 * <p>
 * Created by zhengkaituo on 2017/4/10.
 */

public interface NavRouterCallBack {
    boolean beforeOpen();

    void afterOpen();

    void onError(Intent intent);
}
