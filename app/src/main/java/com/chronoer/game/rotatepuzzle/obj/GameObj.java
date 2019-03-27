package com.chronoer.game.rotatepuzzle.obj;

/**
 * Created by chronoer on 2019/3/25.
 */

public class GameObj {
    private int currentLine = 0;
    private int currentLevel = 1;
    private int currentScore = 0;

    public int getCurrentLine() {
        return currentLine;
    }

    public void setCurrentLine(int currentLine) {
        this.currentLine = currentLine;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }
}
