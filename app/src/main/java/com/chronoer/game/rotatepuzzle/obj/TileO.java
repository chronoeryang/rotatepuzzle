package com.chronoer.game.rotatepuzzle.obj;

import android.graphics.Point;

/**
 * Created by chronoer on 2019/3/19.
 */

public class TileO extends Tile{

    public TileO() {
        super(BaseTile.SHAPE_O);
    }

    @Override
    protected void createShapePoint() {
        createShape();
    }

    @Override
    synchronized public void rotate() {
        super.rotate();

        rotateShape();

        if(puzzleView != null){
            puzzleView.updatePuzzleView(this);
        }
    }

    private void createShape(){
        /**
         *
         *  xx
         *  xx
         *
         * */
        Point[] points = new Point[4];

        points[0] = new Point(4, 0);
        points[1] = new Point(5, 0);
        points[2] = new Point(4, 1);
        points[3] = new Point(5, 1);

        setCurPoints(points);

        Point[] lastPoints = new Point[4];

        lastPoints[0] = new Point(4, 0);
        lastPoints[1] = new Point(5, 0);
        lastPoints[2] = new Point(4, 1);
        lastPoints[3] = new Point(5, 1);

        setLastPoints(lastPoints);
    }

    private void rotateShape(){
        Point[] points = getCurPoints();

        int curRotate = getRotate();

        if(curRotate == ROTATE_0){
            setRotate(ROTATE_90);

        }else if(curRotate == ROTATE_90){
            setRotate(ROTATE_180);

        }else if(curRotate == ROTATE_180){
            setRotate(ROTATE_270);

        }else if(curRotate == ROTATE_270){
            setRotate(ROTATE_0);

        }

        points = adjustPoint(points);
        setCurPoints(points);
    }
}
