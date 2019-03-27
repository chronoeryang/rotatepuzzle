package com.chronoer.game.rotatepuzzle.utils;

import android.content.Context;

/**
 * Created by chronoer on 2019/3/18.
 */

public class UIUtils {

    public static int getScreenWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context){
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
