package com.chronoer.game.rotatepuzzle.obj;

import android.graphics.Point;

import com.chronoer.game.rotatepuzzle.env.GameConfig;
import com.chronoer.game.rotatepuzzle.views.PuzzleView;

/**
 * Created by chronoer on 2019/3/18.
 */

abstract public class BaseTile extends Thread{
    /**
     * x
     */
    public final static int SHAPE_BASE = 0;
    public final static int COLOR_BASE = 0xFF000000;

    /**
    * xxxx
    */
    public final static int SHAPE_I = 1;
    public final static int COLOR_I = 0xFF00FFFF;

    /**
     * x
     * xxx
     */
    public final static int SHAPE_J = 2;
    public final static int COLOR_J = 0xFF0000FF;

    /**
     *   x
     * xxx
     */
    public final static int SHAPE_L = 3;
    public final static int COLOR_L = 0xFFFF9900;

    /**
     * xx
     * xx
     */
    public final static int SHAPE_O = 4;
    public final static int COLOR_O = 0xFFFFFF00;

    /**
     *  xx
     * xx
     */
    public final static int SHAPE_S = 5;
    public final static int COLOR_S = 0xFF00FF00;

    /**
     *  x
     * xxx
     */
    public final static int SHAPE_T = 6;
    public final static int COLOR_T = 0xFFBF00FF;

    /**
     * xx
     *  xx
     */
    public final static int SHAPE_Z = 7;
    public final static int COLOR_Z = 0xFFFF0000;

    public final static int[] SHAPES = new int[]{
            SHAPE_BASE,
            SHAPE_I,
            SHAPE_J,
            SHAPE_L,
            SHAPE_O,
            SHAPE_S,
            SHAPE_T,
            SHAPE_Z
    };

    public final static int[] COLORS = new int[]{
            COLOR_BASE,
            COLOR_I,
            COLOR_J,
            COLOR_L,
            COLOR_O,
            COLOR_S,
            COLOR_T,
            COLOR_Z
    };

    private int shape;

    public int getShape() {
        return shape;
    }

    public void setShape(int shape) {
        this.shape = shape;
        if(this.shape == SHAPE_BASE){
            setColor(COLOR_BASE);
        }else if(this.shape == SHAPE_I){
            setColor(COLOR_I);
        }else if(this.shape == SHAPE_J){
            setColor(COLOR_J);
        }else if(this.shape == SHAPE_L){
            setColor(COLOR_L);
        }else if(this.shape == SHAPE_O){
            setColor(COLOR_O);
        }else if(this.shape == SHAPE_S){
            setColor(COLOR_S);
        }else if(this.shape == SHAPE_T){
            setColor(COLOR_T);
        }else if(this.shape == SHAPE_Z){
            setColor(COLOR_Z);
        }

        createPoint();
    }

    private int color;

    public int getColor() {
        return color;
    }

    private void setColor(int color) {
        this.color = color;
    }

    public final static int ROTATE_0   = 0;
    public final static int ROTATE_90  = 1;
    public final static int ROTATE_180 = 2;
    public final static int ROTATE_270 = 3;
    private int rotate = ROTATE_0;
    abstract public void rotate();

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }

    private Point[] curPoints;
    public Point[] getCurPoints() {
        return curPoints;
    }
    protected void setCurPoints(Point[] curPoints) {
        this.curPoints = curPoints;
    }


    private Point[] lastPoints;
    protected Point[] getLastPoints() {
        return lastPoints;
    }
    protected void setLastPoints(Point[] lastPoints) {
        this.lastPoints = lastPoints;
    }

    abstract protected void createPoint();

    abstract public void updateMatrix(int[][] tileMatrix, int[][] freezeMatrix);

    public final static int MOVE_LEFT = 0;
    public final static int MOVE_UP = 1;
    public final static int MOVE_RIGHT = 2;
    public final static int MOVE_DOWN = 3;
    public final static int MOVE_DOWN_DOWN = 4;
    abstract public void move(int move);

    public final static int STATE_FREEZE = 0;
    public final static int STATE_NORMAL = 1;
    public final static int STATE_PAUSED = 2;

    private int tileState = STATE_PAUSED;
    public int getTileState() {
        return tileState;
    }
    public void setTileState(int tileState) {
        this.tileState = tileState;
    }

    private long fallTick = GameConfig.GAME_TILE_FALL_SPEED_DEFAULT;//default is 1 sec
    public long getFallTick() {
        return fallTick;
    }
    public void setFallTick(long fallTick) {
        this.fallTick = fallTick;
    }

    protected PuzzleView puzzleView;
    public void setPuzzleView(PuzzleView puzzleView) {
        this.puzzleView = puzzleView;
    }
}
