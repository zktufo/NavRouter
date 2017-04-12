package com.leozheng.navrouter;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

/**
 * This is a easy tool for using uri to call activity from both web and native
 * <p>
 * Created by zhengkaituo on 2017/4/5.
 */

public class NavRouter {

    private Context mContext;
    private Intent mIntent;
    private static final String TAG = "NavRouter";

    public NavRouter(Context context) {

        mContext = context;
        mIntent = new Intent(Intent.ACTION_VIEW);
    }

    public NavRouter putExtra(String key, Bundle bundle) {
        mIntent.putExtra(key, bundle);
        return this;
    }


    /**
     * In convenience, parse the url to uri
     *
     * @param uri
     * @return
     */
    public boolean toUri(String uri) {
        if (TextUtils.isEmpty(uri)) {
            return false;
        } else {
            return toUri(Uri.parse(uri));
        }
    }


    /**
     * The easiest way to use the navrouter @_@
     *
     * @param uri
     * @return
     */
    public boolean toUri(Uri uri) {
        return toUri(uri, -1);
    }


    /**
     * In convenience,carry the uri and requestCode for navigation
     *
     * @param uri
     * @param requestCode
     * @return
     */
    public boolean toUri(Uri uri, int requestCode) {
        return toUri(uri, requestCode, registerCallBack(mContext));
    }

    /**
     * In convenience,carry the uriString and requestCode for navigation
     *
     * @param uri
     * @param requestCode
     * @return
     */
    public boolean toUri(String uri, int requestCode) {
        return toUri(Uri.parse(uri), requestCode, registerCallBack(mContext));

    }

    public boolean toUri(String uri, int requestCode, NavRouterCallBack navRouterCallBack) {
        return toUri(Uri.parse(uri), requestCode, navRouterCallBack);
    }

    /**
     * Carry the uri and judge whether the activity is found
     *
     * @param uri
     * @param requestCode
     * @return
     */
    public boolean toUri(Uri uri, int requestCode, NavRouterCallBack navRouterCallBack) {


        mIntent.setPackage(mContext.getPackageName());

        //Convert the scheme to lower case.
        // From the source code, we know that matching the scheme in manifest must be lowercase
        mIntent.setData(uri.normalizeScheme());

        PackageManager packageManager = mContext.getPackageManager();

        ResolveInfo resolveInfo = packageManager.resolveActivity(mIntent, PackageManager.MATCH_DEFAULT_ONLY);

        try {
            if (resolveInfo == null) {
                throw new ActivityNotFoundException("There is no activity matched for " + mIntent);
            } else {
                mIntent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
            }

            if (navRouterCallBack == null) {
                throw new NullPointerException("There is no callback registered");
            } else {
                if (navRouterCallBack.beforeOpen()) {

                    if (!(mContext instanceof Activity)) {
                        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    } else if (mContext instanceof Activity) {
                        ((Activity) mContext).startActivityForResult(mIntent, requestCode);
                    }
                    navRouterCallBack.afterOpen();
                    Log.d(TAG, "Router was success");
                    return true;
                } else {
                    Log.d(TAG, "Router was rejected");
                    return false;
                }

            }


        } catch (ActivityNotFoundException e) {
            navRouterCallBack.onError(mIntent);
            Log.d(TAG, e.getMessage());
            return false;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }


    }


    /**
     * Build the NavRouter Instance
     *
     * @param context
     * @return
     */
    public static NavRouter from(Context context) {
        return new NavRouter(context);
    }

    private NavRouterCallBack registerCallBack(Context context) {
        if (context.getApplicationContext() instanceof NavRouterCallBackFactory) {
            return ((NavRouterCallBackFactory) context.getApplicationContext()).provideNavRouterCallBack();
        }

        return null;
    }

}
