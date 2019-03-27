package com.chronoer.game.rotatepuzzle.activity.other;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.chronoer.game.rotatepuzzle.R;
import com.chronoer.game.rotatepuzzle.activity.base.BaseCommonDialogActivity;
import com.chronoer.game.rotatepuzzle.activity.main.MainActivity;
import com.chronoer.game.rotatepuzzle.utils.LogUtils;

import androidx.annotation.Nullable;

/**
 * Created by chronoer on 2019/3/18.
 */

public class SplashActivity extends BaseCommonDialogActivity {

    private final static String TAG = SplashActivity.class.getSimpleName();

    private final static long DISPLAY_TIME = 2*1000; //second

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i(TAG, "" );
        setContentView(R.layout.activity_splash);

        Handler splashHandler = new Handler();
        splashHandler.postDelayed(new SplashRunnable(), DISPLAY_TIME);
    }

    @Override
    public void setView() {

    }

    private class SplashRunnable implements Runnable{
        public void run(){
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);

            finish();
            return;
        }
    }
}
