package com.chronoer.game.rotatepuzzle.obj;

import android.graphics.Point;

/**
 * Created by chronoer on 2019/3/19.
 */

public class TileJ extends Tile{

    public TileJ() {
        super(BaseTile.SHAPE_J);
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
         *  xxx
         *    x
         *
         * */
        Point[] points = new Point[4];

        points[0] = new Point(4, 0);
        points[1] = new Point(5, 0);
        points[2] = new Point(6, 0);
        points[3] = new Point(6, 1);

        setCurPoints(points);

        Point[] lastPoints = new Point[4];

        lastPoints[0] = new Point(4, 0);
        lastPoints[1] = new Point(5, 0);
        lastPoints[2] = new Point(6, 0);
        lastPoints[3] = new Point(6, 1);

        setLastPoints(lastPoints);
    }

    private void rotateShape(){
        Point[] points = getCurPoints();

        int curRotate = getRotate();
        Point centerPoint = points[2];

        if(curRotate == ROTATE_0){
            /**
             *
             *     x
             *     x
             *    xx
             *
             * */
            setRotate(ROTATE_90);

            for(int i = 0; i<points.length; i++){
                if(i == 0 || i == 1){
                    points[i].x = centerPoint.x;
                    points[i].y = centerPoint.y - (i+1);
                }else if(i == 2){
                    points[i].x = centerPoint.x;
                    points[i].y = centerPoint.y;
                }else if(i == 3){
                    points[i].x = centerPoint.x - 1;
                    points[i].y = centerPoint.y;
                }
            }

        }else if(curRotate == ROTATE_90){
            /**
             *
             *    x
             *    xxx
             *
             * */
            setRotate(ROTATE_180);

            for(int i = 0; i<points.length; i++){
                if(i == 0 || i == 1){
                    points[i].x = centerPoint.x + (i+1);
                    points[i].y = centerPoint.y;
                }else if(i == 2){
                    points[i].x = centerPoint.x;
                    points[i].y = centerPoint.y;
                }else if(i == 3){
                    points[i].x = centerPoint.x;
                    points[i].y = centerPoint.y - 1;
                }
            }

        }else if(curRotate == ROTATE_180){
            /**
             *
             *   xx
             *   x
             *   x
             *
             * */
            setRotate(ROTATE_270);

            for(int i = 0; i<points.length; i++){
                if(i == 0 || i == 1){
                    points[i].x = centerPoint.x;
                    points[i].y = centerPoint.y + (i+1);
                }else if(i == 2){
                    points[i].x = centerPoint.x;
                    points[i].y = centerPoint.y;
                }else if(i == 3){
                    points[i].x = centerPoint.x + 1;
                    points[i].y = centerPoint.y;
                }
            }

        }else if(curRotate == ROTATE_270){
            /**
             *
             *  xxx
             *    x
             *
             * */
            setRotate(ROTATE_0);

            for(int i = 0; i<points.length; i++){
                if(i == 0 || i == 1){
                    points[i].x = centerPoint.x - (i+1);
                    points[i].y = centerPoint.y;
                }else if(i == 2){
                    points[i].x = centerPoint.x;
                    points[i].y = centerPoint.y;
                }else if(i == 3){
                    points[i].x = centerPoint.x;
                    points[i].y = centerPoint.y + 1;
                }
            }

        }

        points = adjustPoint(points);
        setCurPoints(points);
    }

}
