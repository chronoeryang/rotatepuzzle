package com.chronoer.game.rotatepuzzle.obj;

import android.graphics.Point;

import com.chronoer.game.rotatepuzzle.env.GameConfig;
import com.chronoer.game.rotatepuzzle.utils.Utils;

/**
 * Created by chronoer on 2019/3/19.
 */

public class TileI extends Tile{

    public TileI() {
        super(BaseTile.SHAPE_I);
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
         *  xxxx
         *
         * */
        Point[] points = new Point[4];

        points[0] = new Point(3, 0);
        points[1] = new Point(4, 0);
        points[2] = new Point(5, 0);
        points[3] = new Point(6, 0);

        setCurPoints(points);

        Point[] lastPoints = new Point[4];

        lastPoints[0] = new Point(3, 0);
        lastPoints[1] = new Point(4, 0);
        lastPoints[2] = new Point(5, 0);
        lastPoints[3] = new Point(6, 0);

        setLastPoints(lastPoints);
    }

    private void rotateShape(){
        Point[] points = getCurPoints();

        int curRotate = getRotate();

        Point minX = Utils.findMinXPoint(points);
        Point maxX = Utils.findMaxXPoint(points);
        Point maxY = Utils.findMaxYPoint(points);

        Point centerPoint = points[0];
        boolean add = true;

        if(maxY.y + 3 >= GameConfig.GAME_ROWS){
            if(curRotate == ROTATE_0 || curRotate == ROTATE_180) {
                return;
            }
        }

        if(minX.x == 0){
            centerPoint = minX;
            add = true;
        }else if(maxX.x == GameConfig.GAME_COLUMNS - 1){
            centerPoint = maxX;
            add = false;
        }

        if(curRotate == ROTATE_0){
            /**
             *
             *  x
             *  x
             *  x
             *  x
             *
             * */
            setRotate(ROTATE_90);

            for(int i = 0; i<points.length; i++){
                points[i].x = centerPoint.x;
                if(add) {
                    points[i].y = centerPoint.y + i;
                }else{
                    points[i].y = centerPoint.y - i;
                }
            }

        }else if(curRotate == ROTATE_90){
            /**
             *
             *  xxxx
             *
             * */
            setRotate(ROTATE_180);

            for(int i = 0; i<points.length; i++){
                if(add) {
                    points[i].x = centerPoint.x + i;
                }else{
                    points[i].x = centerPoint.x - i;
                }
                points[i].y = centerPoint.y;
            }

        }else if(curRotate == ROTATE_180){
            /**
             *
             *  x
             *  x
             *  x
             *  x
             *
             * */
            setRotate(ROTATE_270);

            for(int i = 0; i<points.length; i++){
                points[i].x = centerPoint.x;
                if(add) {
                    points[i].y = centerPoint.y + i;
                }else{
                    points[i].y = centerPoint.y - i;
                }
            }

        }else if(curRotate == ROTATE_270){
            /**
             *
             *  xxxx
             *
             * */
            setRotate(ROTATE_0);

            for(int i = 0; i<points.length; i++){
                if(add) {
                    points[i].x = centerPoint.x + i;
                }else{
                    points[i].x = centerPoint.x - i;
                }
                points[i].y = centerPoint.y;
            }

        }

        setCurPoints(points);
    }
}
