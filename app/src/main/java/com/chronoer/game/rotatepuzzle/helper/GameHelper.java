package com.chronoer.game.rotatepuzzle.helper;

import android.content.Context;

import com.chronoer.game.rotatepuzzle.obj.BaseTile;
import com.chronoer.game.rotatepuzzle.obj.FactoryTile;
import com.chronoer.game.rotatepuzzle.obj.GameObj;
import com.chronoer.game.rotatepuzzle.obj.Tile;

/**
 * Created by chronoer on 2019/3/26.
 */

public class GameHelper {
    public final static int RUNNING = 1;
    public final static int PAUSE = 2;
    public final static int OVER = 3;

    private static GameHelper sSingleton;
    private Context mContext;

    private GameHelper(Context context) {
        mContext = context;
    }

    public static GameHelper getInstance(Context context) {
        if (sSingleton == null) {
            synchronized (GameHelper.class) {
                sSingleton = new GameHelper(context);
            }
        }
        return sSingleton;
    }

    private Tile currentTile;
    private Tile nextTile;

    private int gameState = RUNNING;
    private GameObj gameObj;

    public void initGame(){
        gameState = RUNNING;
        gameObj = new GameObj();
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public Tile getNextTile() {
        return nextTile;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public GameObj getGameObj() {
        return gameObj;
    }

    public void onLinesChange(int lines){
        setLine(lines);

        setScore(lines);

        setLevel();
    }

    public void gameResume(){
        if(currentTile != null){
            currentTile.setTileState(BaseTile.STATE_NORMAL);
        }
    }

    public void gamePause(){
        if(currentTile != null){
            currentTile.setTileState(BaseTile.STATE_PAUSED);
        }
    }

    public void gameOver(){
        invalidCurrentTile();
        invalidNextTile();
    }

    public void invalidCurrentTile(){
        if(currentTile != null){
            currentTile.interrupt();
            currentTile = null;
        }
    }

    public void invalidNextTile(){
        if(nextTile != null){
            nextTile.interrupt();
            nextTile = null;
        }
    }

    private void setLine(int lines){
        gameObj.setCurrentLine(gameObj.getCurrentLine() + lines);
    }

    private void setScore(int lines){
        int score = gameObj.getCurrentScore();
        score = score + (lines * 100);
        if(lines > 1){
            score = score + (lines * 50);
        }
        gameObj.setCurrentScore(score);
    }

    private void setLevel(){
        int lines = gameObj.getCurrentLine();
        if(lines > 0){
            int level = (gameObj.getCurrentLine() / 10) +1;
            gameObj.setCurrentLevel(level);
        }else{
            gameObj.setCurrentLevel(1);
        }
    }

    synchronized public Tile createTile(){
        if(nextTile != null){
            currentTile = nextTile;
        }else {
            currentTile = FactoryTile.getRandomTile(getGameObj().getCurrentLevel());
        }

        createNextTile();
        return currentTile;
    }

    synchronized private void createNextTile(){
        nextTile = FactoryTile.getRandomTile(getGameObj().getCurrentLevel());
    }
}
