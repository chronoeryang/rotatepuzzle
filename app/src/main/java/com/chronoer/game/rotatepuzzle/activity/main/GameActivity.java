package com.chronoer.game.rotatepuzzle.activity.main;

import android.os.Bundle;

import com.chronoer.game.rotatepuzzle.R;
import com.chronoer.game.rotatepuzzle.activity.base.BaseCommonDialogActivity;
import com.chronoer.game.rotatepuzzle.fragment.main.GameFragment;
import com.chronoer.game.rotatepuzzle.helper.GameHelper;
import com.chronoer.game.rotatepuzzle.utils.LogUtils;

import androidx.annotation.Nullable;

/**
 * Created by chronoer on 2019/3/18.
 */

public class GameActivity extends BaseCommonDialogActivity {

    private final static String TAG = GameActivity.class.getSimpleName();

    private GameFragment mGameFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i(TAG, "" );
        setContentView(R.layout.activity_game);

        setView();
    }

    @Override
    public void onBackPressed() {
        if(mGameFragment != null){
            GameHelper.getInstance(this).setGameState(GameHelper.PAUSE);
            mGameFragment.updateGameStateView();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected void setView() {
        mGameFragment = GameFragment.newInstance();
        replaceFragment(mGameFragment);
    }
}
