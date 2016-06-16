package org.alexwan.searchword.utils;

import android.util.Log;

/**
 * LogUtils
 * Created by alexwan on 16/6/15.
 */
public class LogUtils {
    private final static boolean DEBUG = true;
    private final static String TAG = LogUtils.class.getSimpleName();

    public static void e(String e) {
        if (DEBUG) {
            Log.e(TAG, e, null);
        }
    }

    public static void e(Throwable throwable) {
        if (DEBUG) {
            Log.e(TAG, "Error", throwable);
        }
    }

    public static void e(String tag, String msg, Throwable throwable) {
        if (DEBUG) {
            Log.e(tag, msg, throwable);
        }
    }

    public static void i(String msg) {
        if (DEBUG) {
            i(TAG, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }
    }

}
