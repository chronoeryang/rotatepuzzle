package com.chronoer.game.rotatepuzzle.obj;

import android.graphics.Point;

/**
 * Created by chronoer on 2019/3/19.
 */

public class TileT extends Tile{

    public TileT() {
        super(BaseTile.SHAPE_T);
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
         *   x
         *
         * */
        Point[] points = new Point[4];

        points[0] = new Point(2, 0);
        points[1] = new Point(3, 0);
        points[2] = new Point(4, 0);
        points[3] = new Point(3, 1);

        setCurPoints(points);

        Point[] lastPoints = new Point[4];

        lastPoints[0] = new Point(2, 0);
        lastPoints[1] = new Point(3, 0);
        lastPoints[2] = new Point(4, 0);
        lastPoints[3] = new Point(3, 1);

        setLastPoints(lastPoints);
    }

    private void rotateShape(){
        Point[] points = getCurPoints();

        int curRotate = getRotate();
        Point centerPoint = points[1];

        if(curRotate == ROTATE_0){
            setRotate(ROTATE_90);

            for(int i = 0; i<points.length; i++){
                if(i == 0){
                    points[i].x = centerPoint.x;
                    points[i].y = centerPoint.y - 1;
                }else if(i == 1){
                    points[i].x = centerPoint.x;
                    points[i].y = centerPoint.y;
                }else if(i == 2){
                    points[i].x = centerPoint.x;
                    points[i].y = centerPoint.y + 1;
                }else if(i == 3){
                    points[i].x = centerPoint.x - 1;
                    points[i].y = centerPoint.y;
                }
            }

        }else if(curRotate == ROTATE_90){
            setRotate(ROTATE_180);

            for(int i = 0; i<points.length; i++){
                if(i == 0){
                    points[i].x = centerPoint.x + 1;
                    points[i].y = centerPoint.y;
                }else if(i == 1){
                    points[i].x = centerPoint.x;
                    points[i].y = centerPoint.y;
                }else if(i == 2){
                    points[i].x = centerPoint.x - 1;
                    points[i].y = centerPoint.y;
                }else if(i == 3){
                    points[i].x = centerPoint.x;
                    points[i].y = centerPoint.y - 1;
                }
            }

        }else if(curRotate == ROTATE_180){
            setRotate(ROTATE_270);

            for(int i = 0; i<points.length; i++){
                if(i == 0){
                    points[i].x = centerPoint.x;
                    points[i].y = centerPoint.y + 1;
                }else if(i == 1){
                    points[i].x = centerPoint.x;
                    points[i].y = centerPoint.y;
                }else if(i == 2){
                    points[i].x = centerPoint.x;
                    points[i].y = centerPoint.y - 1;
                }else if(i == 3){
                    points[i].x = centerPoint.x + 1;
                    points[i].y = centerPoint.y;
                }
            }

        }else if(curRotate == ROTATE_270){
            setRotate(ROTATE_0);

            for(int i = 0; i<points.length; i++){
                if(i == 0){
                    points[i].x = centerPoint.x - 1;
                    points[i].y = centerPoint.y;
                }else if(i == 1){
                    points[i].x = centerPoint.x;
                    points[i].y = centerPoint.y;
                }else if(i == 2){
                    points[i].x = centerPoint.x + 1;
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
