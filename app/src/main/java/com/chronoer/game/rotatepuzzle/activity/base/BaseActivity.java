package com.chronoer.game.rotatepuzzle.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.chronoer.game.rotatepuzzle.application.RPApplication;
import com.chronoer.game.rotatepuzzle.utils.AppUtils;
import com.chronoer.game.rotatepuzzle.utils.LogUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * Created by chronoer on 2019/3/18.
 */

public class BaseActivity extends AppCompatActivity {
    private final static String TAG = BaseActivity.class.getSimpleName();

    private FragmentManager.FragmentLifecycleCallbacks mFragmentLifecycleCallbacks;

    protected FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i(TAG, "");
        LogUtils.v(TAG, "onCreate:"+ this.getClass().getSimpleName());


        ((RPApplication)getApplication()).onActivityCreated(this, savedInstanceState);
        registerFragmentCallBack();

        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.v(TAG, "onStart:"+ this.getClass().getSimpleName());


        ((RPApplication)getApplication()).onActivityStarted(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.v(TAG, "onResume:"+ this.getClass().getSimpleName());

        ((RPApplication)getApplication()).onActivityResumed(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.v(TAG, "onPause:"+ this.getClass().getSimpleName());

        ((RPApplication)getApplication()).onActivityPaused(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.v(TAG, "onStop:"+ this.getClass().getSimpleName());


        ((RPApplication)getApplication()).onActivityStopped(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.v(TAG, "onDestroy:"+ this.getClass().getSimpleName());


        ((RPApplication)getApplication()).onActivityDestroyed(this);
        unRegisterFragmentCallBack();
    }

    //====fragment callback
    protected void registerFragmentCallBack(){
        mFragmentLifecycleCallbacks = new FragmentManager.FragmentLifecycleCallbacks() {
            @Override
            public void onFragmentPreAttached(FragmentManager fm, Fragment f, Context context) {
                super.onFragmentPreAttached(fm, f, context);
            }

            @Override
            public void onFragmentAttached(FragmentManager fm, Fragment f, Context context) {
                super.onFragmentAttached(fm, f, context);
            }

            @Override
            public void onFragmentPreCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
                super.onFragmentPreCreated(fm, f, savedInstanceState);
            }

            @Override
            public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
                super.onFragmentCreated(fm, f, savedInstanceState);
            }

            @Override
            public void onFragmentActivityCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
                super.onFragmentActivityCreated(fm, f, savedInstanceState);
            }

            @Override
            public void onFragmentViewCreated(FragmentManager fm, Fragment f, View v, Bundle savedInstanceState) {
                super.onFragmentViewCreated(fm, f, v, savedInstanceState);
            }

            @Override
            public void onFragmentStarted(FragmentManager fm, Fragment f) {
                super.onFragmentStarted(fm, f);
            }

            @Override
            public void onFragmentResumed(FragmentManager fm, Fragment f) {
                super.onFragmentResumed(fm, f);
            }

            @Override
            public void onFragmentPaused(FragmentManager fm, Fragment f) {
                super.onFragmentPaused(fm, f);
            }

            @Override
            public void onFragmentStopped(FragmentManager fm, Fragment f) {
                super.onFragmentStopped(fm, f);
            }

            @Override
            public void onFragmentSaveInstanceState(FragmentManager fm, Fragment f, Bundle outState) {
                super.onFragmentSaveInstanceState(fm, f, outState);
            }

            @Override
            public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
                super.onFragmentViewDestroyed(fm, f);
            }

            @Override
            public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
                super.onFragmentDestroyed(fm, f);
            }

            @Override
            public void onFragmentDetached(FragmentManager fm, Fragment f) {
                super.onFragmentDetached(fm, f);
            }
        };

        getSupportFragmentManager().registerFragmentLifecycleCallbacks(mFragmentLifecycleCallbacks, true);
    }

    protected void unRegisterFragmentCallBack(){
        getSupportFragmentManager().unregisterFragmentLifecycleCallbacks(mFragmentLifecycleCallbacks);
    }

    //====protected method
//    protected Fragment getCurrentFragment(){
//        return mFragmentManager.findFragmentById(R.id.fragment_layout);
//    }

    protected void clearFragmentBackStack(){
        int backStackCount = getSupportFragmentManager().getBackStackEntryCount();
        for (int i = 0; i < backStackCount; i++) {
            int backStackId = mFragmentManager.getBackStackEntryAt(i).getId();
            mFragmentManager.popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    protected void showKeyboard(View view){
        AppUtils.showKeyboard(this, view);
    }

    protected void hideKeyboard(){
        AppUtils.hideKeyboard(this);
    }
}
