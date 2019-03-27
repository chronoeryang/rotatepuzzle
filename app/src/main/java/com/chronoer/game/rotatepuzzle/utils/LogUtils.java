package com.chronoer.game.rotatepuzzle.utils;

import android.util.Log;

import com.chronoer.game.rotatepuzzle.env.AppConfig;

/**
 * Created by chronoer on 2019/3/18.
 */

public class LogUtils {
    public static void v(String tag, String message){
        if (AppConfig.OPEN_LOG) {
            Log.v(tag, message);
        }
    }

    public static void i(String tag, String message){
        if (AppConfig.OPEN_LOG) {
            Log.i(tag, message);
        }
    }

    public static void w(String tag, String message){
        if (AppConfig.OPEN_LOG) {
            Log.w(tag, message);
        }
    }

    public static void e(String tag, String message){
        if (AppConfig.OPEN_LOG) {
            Log.e(tag, message);
        }
    }

    public static void d(String tag, String message) {
        if (AppConfig.OPEN_LOG) {
            Log.d(tag, message);
        }
    }
}
