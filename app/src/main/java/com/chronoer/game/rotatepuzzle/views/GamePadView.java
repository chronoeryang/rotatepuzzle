package com.chronoer.game.rotatepuzzle.views;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.chronoer.game.rotatepuzzle.R;
import com.chronoer.game.rotatepuzzle.listener.OnGamePadListener;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;

/**
 * Created by chronoer on 2019/3/27.
 */

public class GamePadView extends LinearLayoutCompat {

    private final static int EVENT_DELAY_TIME = 60; //millsecond

    private final static int BUTTON_LEFT = 0;
    private final static int BUTTON_RIGHT = 1;
    private final static int BUTTON_DOWN = 2;
    private final static int BUTTON_DOWNDOWN = 3;
    private final static int BUTTON_ROTATE = 4;

    private ImageView mLeftButtonView;
    private ImageView mRightButtonView;
    private ImageView mDownButtonView;
    private ImageView mDownDownButtonView;
    private ImageView mRotateButtonView;

    private OnGamePadListener mListener;

    private Handler mHandler;
    private Runnable mRunnable;

    public GamePadView(Context context) {
        super(context);
        init(context, null);
    }

    public GamePadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public GamePadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet){
        View.inflate(context, R.layout.view_gamepad, this);

        mHandler = new Handler();

        mLeftButtonView = findViewById(R.id.view_gamepad_button_left);
        mLeftButtonView.setOnTouchListener(new OnLongTouchListener(BUTTON_LEFT));

        mRightButtonView = findViewById(R.id.view_gamepad_button_right);
        mRightButtonView.setOnTouchListener(new OnLongTouchListener(BUTTON_RIGHT));

        mDownButtonView = findViewById(R.id.view_gamepad_button_down);
        mDownButtonView.setOnTouchListener(new OnLongTouchListener(BUTTON_DOWN));

        mDownDownButtonView = findViewById(R.id.view_gamepad_button_downdown);
        mDownDownButtonView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onDownDown();
                }
            }
        });

        mRotateButtonView = findViewById(R.id.view_gamepad_button_rotate);
        mRotateButtonView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onRotate();
                }
            }
        });
    }

    public void setListener(OnGamePadListener listener){
        mListener = listener;
    }

    private void triggerEvent(final int button){
        if(mListener == null){
            return;
        }

        mRunnable = new Runnable() {
            @Override
            public void run() {
                if(button == BUTTON_LEFT){
                    mListener.onLeft();
                }else if(button == BUTTON_RIGHT){
                    mListener.onRight();
                }else if(button == BUTTON_DOWN){
                    mListener.onDown();
                }
//                else if(button == BUTTON_DOWNDOWN){
//                    mListener.onDownDown();
//                }else if(button == BUTTON_ROTATE){
//                    mListener.onRotate();
//                }

                mHandler.postDelayed(mRunnable, EVENT_DELAY_TIME);
            }
        };

        mHandler.postDelayed(mRunnable, 0);
    }

    private void stopEvent(){
        if(mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
        }
    }

    private class OnLongTouchListener implements View.OnTouchListener{

        private int mButton;
        public OnLongTouchListener(int button){
            mButton = button;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                triggerEvent(mButton);
            }else if(event.getAction() == MotionEvent.ACTION_UP){
                stopEvent();
            }

            return true;
        }
    }
}
