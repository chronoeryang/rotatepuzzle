package com.chronoer.game.rotatepuzzle.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.chronoer.game.rotatepuzzle.BuildConfig;

/**
 * Created by chronoer on 2019/3/18.
 */

public class AppUtils {
    private final static String TAG = AppUtils.class.getSimpleName();

    public static final boolean isDebugMode(){
        return BuildConfig.DEBUG;
    }

    public static void showKeyboard(Activity activity, View view){
        try{
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }catch(Exception e){
            LogUtils.e(TAG, "show keyboard error:"+e.toString());
        }
    }

    public static void hideKeyboard(Activity activity){
        try{
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
//            if(imm.isActive()){
//                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
//            }
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }catch(Exception e){
            LogUtils.e(TAG, "hide keyboard error:"+e.toString());
        }
    }

    public static String getVersion(Context context){
        String version="";

        try {
            version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        return version;
    }
}
