package com.leozheng.navrouter;

import android.net.Uri;

/**
 * This util is used for converting the param
 *
 * Created by zhengkaituo on 2017/4/9.
 */

public class ParamUtils {

    public static String getStringExtra(Uri uri,String key){
        String value = null;
        value = uri.getQueryParameter(key);
        return value;
    }

    public static int getIntExtra(Uri uri,String key,int defaultValue){
        int value = defaultValue;
        value = uri.getQueryParameter(key)!=null?Integer.valueOf(uri.getQueryParameter(key)):value;
        return  value;
    }

    public static double getDoubleExtra(Uri uri, String key, double defaultValue) {
        double value = defaultValue;
        value = uri.getQueryParameter(key)!=null?Double.valueOf(uri.getQueryParameter(key)):value;
        return value;
    }

    public static float getFloatExtra(Uri uri, String key, float defaultValue) {
        float value = defaultValue;
        value = uri.getQueryParameter(key)!=null?Float.valueOf(uri.getQueryParameter(key)):value;
        return value;
    }

    public static long getLongExtra(Uri uri, String key, long defaultValue) {
        long value = defaultValue;
        value = uri.getQueryParameter(key)!=null?Long.valueOf(uri.getQueryParameter(key)):value;
        return value;
    }


}
