package com.chronoer.game.rotatepuzzle.listener;

/**
 * Created by chronoer on 2019/3/25.
 */

public interface OnGameStateListener {
    void onComboLines(int lines);
    void onGameStateChange(int state);
    void onNeedTile();
}
