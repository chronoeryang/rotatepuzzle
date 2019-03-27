package com.chronoer.game.rotatepuzzle.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import com.chronoer.game.rotatepuzzle.obj.BaseTile;
import com.chronoer.game.rotatepuzzle.obj.Tile;

import androidx.annotation.Nullable;

/**
 * Created by chronoer on 2019/3/25.
 */

public class SimpleTileView extends View{

    private final static int SIMPLE_WIDTH = 4;
    private final static int SIMPLE_HEIGHT = 3;

    private int[][] mTileMatrix;
    private Tile mTile;

    private int mWidth = 0;
    private int mHeight = 0;
    private int mTileWidth = 0;
    private int mTileHeight = 0;

    public SimpleTileView(Context context) {
        super(context);
        init(context, null);
    }

    public SimpleTileView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SimpleTileView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public SimpleTileView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet){

        mTileMatrix = new int[SIMPLE_WIDTH][SIMPLE_HEIGHT];
        for(int x = 0; x < SIMPLE_WIDTH; x++){
            for(int y = 0; y < SIMPLE_HEIGHT; y++){
                mTileMatrix[x][y] = BaseTile.SHAPE_BASE;
            }
        }
    }

    public void setTile(Tile tile){
        for(int x = 0; x < SIMPLE_WIDTH; x++){
            for(int y = 0; y < SIMPLE_HEIGHT; y++){
                mTileMatrix[x][y] = BaseTile.SHAPE_BASE;
            }
        }

        mTile = tile;
        Point[] points = new Point[4];
        points[0] = new Point(0, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(0, 0);
        points[3] = new Point(0, 0);

        if(mTile.getShape() == BaseTile.SHAPE_BASE){

        }else if(mTile.getShape() == BaseTile.SHAPE_I){
            /**
             *
             *  xxxx
             *
             * */
            points[0].x = 0;
            points[0].y = 1;

            points[1].x = 1;
            points[1].y = 1;

            points[2].x = 2;
            points[2].y = 1;

            points[3].x = 3;
            points[3].y = 1;
        }else if(mTile.getShape() == BaseTile.SHAPE_J){
            /**
             *
             *  xxx
             *    x
             *
             * */
            points[0].x = 0;
            points[0].y = 0;

            points[1].x = 1;
            points[1].y = 0;

            points[2].x = 2;
            points[2].y = 0;

            points[3].x = 2;
            points[3].y = 1;
        }else if(mTile.getShape() == BaseTile.SHAPE_L){
            points[0].x = 0;
            points[0].y = 0;

            points[1].x = 1;
            points[1].y = 0;

            points[2].x = 2;
            points[2].y = 0;

            points[3].x = 0;
            points[3].y = 1;
        }else if(mTile.getShape() == BaseTile.SHAPE_O){
            points[0].x = 1;
            points[0].y = 0;

            points[1].x = 2;
            points[1].y = 0;

            points[2].x = 1;
            points[2].y = 1;

            points[3].x = 2;
            points[3].y = 1;
        }else if(mTile.getShape() == BaseTile.SHAPE_S){
            points[0].x = 0;
            points[0].y = 1;

            points[1].x = 1;
            points[1].y = 1;

            points[2].x = 1;
            points[2].y = 0;

            points[3].x = 2;
            points[3].y = 0;
        }else if(mTile.getShape() == BaseTile.SHAPE_Z){
            points[0].x = 0;
            points[0].y = 0;

            points[1].x = 1;
            points[1].y = 0;

            points[2].x = 1;
            points[2].y = 1;

            points[3].x = 2;
            points[3].y = 1;
        }else if(mTile.getShape() == BaseTile.SHAPE_T){
            points[0].x = 0;
            points[0].y = 0;

            points[1].x = 1;
            points[1].y = 0;

            points[2].x = 2;
            points[2].y = 0;

            points[3].x = 1;
            points[3].y = 1;
        }

        for(Point point : points){
            mTileMatrix[point.x][point.y] = tile.getShape();
        }

        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        mWidth = getWidth();
//        mHeight = getHeight();
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mTileWidth = mWidth / SIMPLE_WIDTH;
        mTileHeight = mHeight / SIMPLE_HEIGHT;

        invalidate();
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(mWidth == 0 || mHeight == 0){
            return;
        }

        drawCurrentTile(canvas);
        canvas.restore();
    }

    private void drawCurrentTile(Canvas canvas){
        for(int x = 0; x < SIMPLE_WIDTH; x++){
            for(int y = 0; y < SIMPLE_HEIGHT; y++){
                if(mTileMatrix[x][y] != BaseTile.SHAPE_BASE){
                    int color = BaseTile.COLORS[mTileMatrix[x][y]];
                    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                    paint.setColor(color);

                    Paint strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                    strokePaint.setStyle(Paint.Style.STROKE);
                    strokePaint.setColor(Color.BLACK);
                    strokePaint.setStrokeWidth(5);

                    int left = x * mTileWidth;
                    int top = y * mTileHeight;
                    int right = left + mTileWidth;
                    int bottom = top + mTileHeight;

                    canvas.drawRect(left, top, right, bottom, paint);
                    canvas.drawRect(left, top, right, bottom, strokePaint);
                }else{
                    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                    paint.setColor(Color.BLACK);

                    int left = x * mTileWidth;
                    int top = y * mTileHeight;
                    int right = left + mTileWidth;
                    int bottom = top + mTileHeight;

                    canvas.drawRect(left, top, right, bottom, paint);
                }
            }
        }

        canvas.save();
    }
}
