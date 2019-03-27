package com.chronoer.game.rotatepuzzle.obj;

import android.graphics.Point;

/**
 * Created by chronoer on 2019/3/19.
 */

public class TileB extends Tile{

    public TileB() {
        super(BaseTile.SHAPE_BASE);
    }

    @Override
    protected void createShapePoint() {
        createShapeBase();
    }

    @Override
    synchronized public void rotate() {
        super.rotate();
        return;
    }

    private void createShapeBase(){
        Point[] points = new Point[4];

        points[0] = new Point(0, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(0, 0);
        points[3] = new Point(0, 0);

        setCurPoints(points);

        setLastPoints(points);
    }
}
