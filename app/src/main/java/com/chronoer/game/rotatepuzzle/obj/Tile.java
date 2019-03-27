package com.chronoer.game.rotatepuzzle.obj;

import android.graphics.Point;

import com.chronoer.game.rotatepuzzle.env.GameConfig;
import com.chronoer.game.rotatepuzzle.utils.Utils;

import java.util.concurrent.TimeUnit;

/**
 * Created by chronoer on 2019/3/18.
 */

abstract public class Tile extends BaseTile implements Runnable{

    public Tile(int shape){
        setShape(shape);
    }

    @Override
    protected void createPoint() {
        createShapePoint();
    }

    abstract protected void createShapePoint();

    @Override
    public void setTileState(int tileState) {
        super.setTileState(tileState);
    }

    @Override
    public void updateMatrix(int[][] tileMatrix, int[][] freezeMatrix) {
        if(getTileState() == BaseTile.STATE_FREEZE){
            return;
        }

        if(isConflict(freezeMatrix)){
            synchronized (this){
                setCurPointFromLastPoint();

                if(canMoveDown(freezeMatrix)){
                    setTileState(STATE_NORMAL);
                }else{
                    setTileState(STATE_FREEZE);
                }

                if(puzzleView != null){
                    puzzleView.updatePuzzleView(this);
                }
            }
        }else{
            performAction(tileMatrix);
        }
    }

    private boolean isConflict(int[][] matrix){
        Point[] points = getCurPoints();
        for(Point point : points){
//            point.x >= 0 && point.y >=0
//                    && point.x < GameConfig.GAME_COLUMNS && point.y < GameConfig.GAME_ROWS
//                    &&
            if(point.x < 0 || point.y < 0){
                continue;
            }

            if(point.x >= GameConfig.GAME_COLUMNS || point.y >= GameConfig.GAME_ROWS){
                return true;
            }

            if(matrix[point.x][point.y] == BaseTile.SHAPE_BASE) {

            }else{
                return true;
            }
        }

        return false;
    }

    private boolean canMoveDown(int[][] matrix){
        Point[] points = getCurPoints().clone();
        //move down
        for(Point point : points){
            point.y = point.y + 1;
            if(point.y >= GameConfig.GAME_ROWS){
                return false;
            }

            if(matrix[point.x][point.y] != BaseTile.SHAPE_BASE){
                return false;
            }
        }

        return true;
    }

    private void performAction(int[][] matrix){
        Point[] lastPoints = getLastPoints();
        for(Point point : lastPoints){
            if(point.x >= 0 && point.y >=0
                    && point.x < GameConfig.GAME_COLUMNS && point.y < GameConfig.GAME_ROWS) {
                matrix[point.x][point.y] = BaseTile.SHAPE_BASE;
            }
        }

        Point[] points = getCurPoints();
        for(Point point : points){
            if(point.x >= 0 && point.y >=0
                    && point.x < GameConfig.GAME_COLUMNS && point.y < GameConfig.GAME_ROWS) {
                matrix[point.x][point.y] = getShape();
            }
        }
    }

    @Override
    synchronized public void move(int move) {
        if(getTileState() == STATE_FREEZE || getTileState() == STATE_PAUSED){
            return;
        }

        synchronized(this){
            setLastPointFromCurPoint();
        }

        Point[] points = getCurPoints();

        if(move == MOVE_UP){
            //wont handle
        }else if(move == MOVE_LEFT){
            Point minXPoint = Utils.findMinXPoint(points);
            if(minXPoint.x - 1 < 0){

            }else{
                for (Point point : points) {
                    if (point.x - 1 >= 0) {
                        point.x = point.x - 1;
                        setTileState(STATE_NORMAL);
                    }
                }
            }

        }else if(move == MOVE_DOWN){
            Point maxYPoint = Utils.findMaxYPoint(points);
            if(maxYPoint.y + 1 >= GameConfig.GAME_ROWS) {
                setTileState(STATE_FREEZE);
            }else {
                for (Point point : points) {
                    if (point.y + 1 < GameConfig.GAME_ROWS) {
                        point.y = point.y + 1;
                        setTileState(STATE_NORMAL);
                    } else {
                        setTileState(STATE_FREEZE);
                    }
                }
            }
        }else if(move == MOVE_RIGHT){
            Point maxXPoint = Utils.findMaxXPoint(points);
            if(maxXPoint.x + 1 >= GameConfig.GAME_COLUMNS){

            }else {
                for (Point point : points) {
                    if (point.x + 1 < GameConfig.GAME_COLUMNS) {
                        point.x = point.x + 1;
                        setTileState(STATE_NORMAL);
                    }
                }
            }
        }else if(move == MOVE_DOWN_DOWN){
//            Point maxYPoint = Utils.findMaxYPoint(points);
//            if(maxYPoint.y + 1 >= GameConfig.GAME_ROWS) {
//                setTileState(STATE_FREEZE);
//            }else{
//                for (Point point : points) {
//                    int moveY = GameConfig.GAME_ROWS - point.y -1;
//                    point.y = point.y + moveY;
//                }
//            }
        }

        setCurPoints(points);

        if(puzzleView != null){
            puzzleView.updatePuzzleView(this);
        }
    }

    @Override
    synchronized public void rotate() {
        if(getTileState() == STATE_FREEZE){
            return;
        }

        synchronized(this){
            setLastPointFromCurPoint();
        }
    }

    @Override
    public void run() {
        while(getTileState() == STATE_NORMAL || getTileState() == STATE_PAUSED) {
            if(isAlive() == true && isInterrupted() == false) {
                timeTick();
                move(MOVE_DOWN);
            }
        }

        //wait the thread to die
        try {
            join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void timeTick(){
        try {
            TimeUnit.MILLISECONDS.sleep(getFallTick());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setLastPointFromCurPoint(){
        Point[] lastPoints = getLastPoints();
        Point[] points = getCurPoints();

        lastPoints[0].x = points[0].x;
        lastPoints[0].y = points[0].y;

        lastPoints[1].x = points[1].x;
        lastPoints[1].y = points[1].y;

        lastPoints[2].x = points[2].x;
        lastPoints[2].y = points[2].y;

        lastPoints[3].x = points[3].x;
        lastPoints[3].y = points[3].y;
    }

    private void setCurPointFromLastPoint(){
        Point[] curPoints = getCurPoints();
        Point[] LastPoints = getLastPoints();

        curPoints[0].x = LastPoints[0].x;
        curPoints[0].y = LastPoints[0].y;

        curPoints[1].x = LastPoints[1].x;
        curPoints[1].y = LastPoints[1].y;

        curPoints[2].x = LastPoints[2].x;
        curPoints[2].y = LastPoints[2].y;

        curPoints[3].x = LastPoints[3].x;
        curPoints[3].y = LastPoints[3].y;
    }

    protected Point[] adjustPoint(Point[] points){
        Point minX = Utils.findMinXPoint(points);
        Point maxX = Utils.findMaxXPoint(points);

        if(minX.x < 0){
            int moveRight = Math.abs(0-minX.x);
            for(Point point : points){
                point.x = point.x + moveRight;
            }
        }

        if(maxX.x > GameConfig.GAME_COLUMNS -1){
            int moveLeft = Math.abs(maxX.x - (GameConfig.GAME_COLUMNS -1));
            for(Point point : points){
                point.x = point.x - moveLeft;
            }
        }


        return points;
    }
}
