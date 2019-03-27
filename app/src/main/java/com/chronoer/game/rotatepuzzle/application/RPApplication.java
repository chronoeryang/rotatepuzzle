package com.chronoer.game.rotatepuzzle.application;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.os.Bundle;

import com.chronoer.game.rotatepuzzle.utils.LogUtils;

/**
 * Created by chronoer on 2019/3/18.
 */

public class RPApplication extends Application implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {

    private final static String TAG = RPApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.i(TAG, "");

        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}