package com.chronoer.game.rotatepuzzle.fragment.main;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chronoer.game.rotatepuzzle.R;
import com.chronoer.game.rotatepuzzle.fragment.base.BaseCommonFragment;
import com.chronoer.game.rotatepuzzle.helper.DialogHelper;
import com.chronoer.game.rotatepuzzle.helper.GameHelper;
import com.chronoer.game.rotatepuzzle.listener.OnGamePadListener;
import com.chronoer.game.rotatepuzzle.listener.OnGameStateListener;
import com.chronoer.game.rotatepuzzle.listener.OnSwipeListener;
import com.chronoer.game.rotatepuzzle.obj.BaseTile;
import com.chronoer.game.rotatepuzzle.obj.Tile;
import com.chronoer.game.rotatepuzzle.utils.LogUtils;
import com.chronoer.game.rotatepuzzle.views.GamePadView;
import com.chronoer.game.rotatepuzzle.views.PuzzleView;
import com.chronoer.game.rotatepuzzle.views.SimpleTileView;

/**
 * Created by chronoer on 2019/3/18.
 */

public class GameFragment extends BaseCommonFragment {
    private final static String TAG = GameFragment.class.getSimpleName();

    private TextView mScoreView;
    private TextView mLevelView;
    private TextView mLineView;
    private PuzzleView mPuzzleView;
    private SimpleTileView mSimpleTileView;
    private GamePadView mGamePadView;

    private GameHelper mGameHelper;

    public static GameFragment newInstance() {
        GameFragment f = new GameFragment();
        return f;
    }

    public GameFragment(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private void initVariable(){
        mGameHelper = GameHelper.getInstance(getContext());
        mGameHelper.initGame();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i(TAG, "");
    }

    @SuppressWarnings("all")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initVariable();
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        mScoreView = view.findViewById(R.id.fragment_game_score);
        mLevelView = view.findViewById(R.id.fragment_game_lv);
        mLineView = view.findViewById(R.id.fragment_game_line);
        mPuzzleView = view.findViewById(R.id.fragment_game_puzzle);
        mSimpleTileView = view.findViewById(R.id.fragment_game_simpletile);
        mGamePadView = view.findViewById(R.id.fragment_game_gamepad);
        mGamePadView.setListener(new OnGamePadListener() {
            @Override
            public void onLeft() {
                if(mGameHelper.getGameState() == GameHelper.RUNNING) {
                    mGameHelper.getCurrentTile().move(BaseTile.MOVE_LEFT);
                }
            }

            @Override
            public void onRight() {
                if(mGameHelper.getGameState() == GameHelper.RUNNING) {
                    mGameHelper.getCurrentTile().move(BaseTile.MOVE_RIGHT);
                }
            }

            @Override
            public void onDown() {
                if(mGameHelper.getGameState() == GameHelper.RUNNING) {
                    mGameHelper.getCurrentTile().move(BaseTile.MOVE_DOWN);
                }
            }

            @Override
            public void onDownDown() {
                if(mGameHelper.getGameState() == GameHelper.RUNNING) {
                    mGameHelper.getCurrentTile().move(BaseTile.MOVE_DOWN_DOWN);
                }
            }

            @Override
            public void onRotate() {
                if(mGameHelper.getGameState() == GameHelper.RUNNING) {
                    mGameHelper.getCurrentTile().rotate();
                }
            }
        });

//        view.setOnTouchListener(new OnSwipeListener(getContext()){
//            @Override
//            public void onSingleTap() {
//                super.onSingleTap();
//                if(mGameHelper.getGameState() == GameHelper.RUNNING) {
//                    mGameHelper.getCurrentTile().rotate();
//                }
//            }
//
//            @Override
//            public void onSwipeRight() {
//                super.onSwipeRight();
//                if(mGameHelper.getGameState() == GameHelper.RUNNING) {
//                    mGameHelper.getCurrentTile().move(BaseTile.MOVE_RIGHT);
//                }
//            }
//
//            @Override
//            public void onSwipeLeft() {
//                super.onSwipeLeft();
//                if(mGameHelper.getGameState() == GameHelper.RUNNING) {
//                    mGameHelper.getCurrentTile().move(BaseTile.MOVE_LEFT);
//                }
//            }
//
//            @Override
//            public void onSwipeTop() {
//                super.onSwipeTop();
//                //dont handle
//            }
//
//            @Override
//            public void onSwipeBottom() {
//                super.onSwipeBottom();
//                if(mGameHelper.getGameState() == GameHelper.RUNNING) {
//                    mGameHelper.getCurrentTile().move(BaseTile.MOVE_DOWN);
//                }
//            }
//        });

        mPuzzleView.setOnGameStateListener(new OnGameStateListener() {
            @Override
            public void onComboLines(int lines) {
                mGameHelper.onLinesChange(lines);
                updateGameView();
            }

            @Override
            public void onGameStateChange(int state) {
                mGameHelper.setGameState(state);
                updateGameStateView();
            }

            @Override
            public void onNeedTile() {
                mGameHelper.invalidCurrentTile();
                if(mGameHelper.getGameState() == GameHelper.RUNNING) {
                    setCurrentTile();
                }
            }
        });

        setCurrentTile();

        updateGameView();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        int gameState = mGameHelper.getGameState();
        if(gameState == GameHelper.PAUSE){
            mGameHelper.setGameState(GameHelper.RUNNING);
            mGameHelper.gameResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        gameOver();
    }

    public void updateGameStateView(){
        int gameState = mGameHelper.getGameState();
        if(gameState == GameHelper.RUNNING){
            gameResume();
        }else if(gameState == GameHelper.PAUSE){
            gamePause();
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DialogHelper.confirmDialog(getContext(), getString(R.string.game_pause), "", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            getActivity().finish();
                        }
                    }, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            onResume();
                        }
                    }).show();
                }
            });
        }else if(gameState == GameHelper.OVER){
            gameOver();
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DialogHelper.warningDialog(getContext(), getString(R.string.game_over), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            getActivity().finish();
                        }
                    }).show();
                }
            });
        }
    }

    private void updateGameView(){
        updateLines();
        updateScore();
        updateLevel();
    }

    private void updateLines(){
        mLineView.post(new Runnable() {
            @Override
            public void run() {
                mLineView.setText(getString(R.string.game_line, String.valueOf(mGameHelper.getGameObj().getCurrentLine())));
            }
        });
    }

    private void updateScore(){
        mScoreView.post(new Runnable() {
            @Override
            public void run() {
                mScoreView.setText(String.valueOf(mGameHelper.getGameObj().getCurrentScore()));
            }
        });
    }

    private void updateLevel(){
        mLevelView.post(new Runnable() {
            @Override
            public void run() {
                mLevelView.setText(getString(R.string.game_level, String.valueOf(mGameHelper.getGameObj().getCurrentLevel())));
            }
        });
    }

    private void gameResume(){
        mGameHelper.gameResume();
    }

    private void gamePause(){
        mGameHelper.gamePause();
    }

    private void gameOver(){
        mGameHelper.gameOver();
    }

    private void setCurrentTile(){
        Tile tile = mGameHelper.createTile();

        mPuzzleView.updatePuzzleView(tile);
        tile.setPuzzleView(mPuzzleView);

        if(mGameHelper.getGameState() == GameHelper.RUNNING) {
            setNextTile();
            tile.setTileState(BaseTile.STATE_NORMAL);
            tile.start();
        }
    }

    private void setNextTile(){
        mSimpleTileView.post(new Runnable() {
            @Override
            public void run() {
                Tile tile = mGameHelper.getNextTile();
                if(tile != null) {
                    mSimpleTileView.setTile(tile);
                }
            }
        });
    }
}



