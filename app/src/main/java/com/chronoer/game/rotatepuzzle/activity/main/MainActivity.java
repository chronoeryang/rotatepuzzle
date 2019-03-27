package com.chronoer.game.rotatepuzzle.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chronoer.game.rotatepuzzle.R;
import com.chronoer.game.rotatepuzzle.activity.base.BaseCommonDialogActivity;
import com.chronoer.game.rotatepuzzle.utils.LogUtils;

import androidx.annotation.Nullable;

/**
 * Created by chronoer on 2019/3/27.
 */

public class MainActivity extends BaseCommonDialogActivity {
    private final static String TAG = MainActivity.class.getSimpleName();

    private TextView mStartView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i(TAG, "" );
        setContentView(R.layout.activity_main);

        setView();
    }

    @Override
    protected void setView() {
        mStartView = findViewById(R.id.activity_main_start);

        mStartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });
    }
}
