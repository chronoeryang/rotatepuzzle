package com.chronoer.game.rotatepuzzle.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.chronoer.game.rotatepuzzle.env.GameConfig;
import com.chronoer.game.rotatepuzzle.helper.GameHelper;
import com.chronoer.game.rotatepuzzle.listener.OnGameStateListener;
import com.chronoer.game.rotatepuzzle.obj.BaseTile;
import com.chronoer.game.rotatepuzzle.obj.Tile;
import com.chronoer.game.rotatepuzzle.utils.LogUtils;
import com.chronoer.game.rotatepuzzle.utils.Utils;

/**
 * Created by chronoer on 2019/3/18.
 */

public class PuzzleView extends SurfaceView implements SurfaceHolder.Callback {

    private final static String TAG = PuzzleView.class.getSimpleName();

    private Context mContent;

    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;

    private Tile mCurrentTile;

    private int mWidth = 0;
    private int mHeight = 0;
    private int mTileWidth = 0;
    private int mTileHeight = 0;
    private int mWidthRemind = 0;
    private int mHeightRemind = 0;

    private int[][] mFreezeTileMatrix;
    private int[][] mCurrentTileMatrix;

    private OnGameStateListener mOnGameStateListener;

    public PuzzleView(Context context) {
        super(context);
        init(context, null);
    }

    public PuzzleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PuzzleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public PuzzleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        mContent = context;
        LogUtils.i(TAG, "");

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GRAY);

        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);

        initFreezeMatrix();

        initCurrentTileMatrix();
    }

    private void initFreezeMatrix(){
        mFreezeTileMatrix = new int[GameConfig.GAME_COLUMNS][GameConfig.GAME_ROWS];
        for(int x = 0; x < GameConfig.GAME_COLUMNS; x++){
            for(int y = 0; y < GameConfig.GAME_ROWS; y++){
                mFreezeTileMatrix[x][y] = BaseTile.SHAPE_BASE;
            }
        }
    }

    private void initCurrentTileMatrix(){
        mCurrentTileMatrix = new int[GameConfig.GAME_COLUMNS][GameConfig.GAME_ROWS];
        for(int x = 0; x < GameConfig.GAME_COLUMNS; x++){
            for(int y = 0; y < GameConfig.GAME_ROWS; y++){
                mCurrentTileMatrix[x][y] = BaseTile.SHAPE_BASE;
            }
        }
    }

    private void resetCurrentTileMatrix(){
        for(int x = 0; x < GameConfig.GAME_COLUMNS; x++){
            for(int y = 0; y < GameConfig.GAME_ROWS; y++){
                mCurrentTileMatrix[x][y] = BaseTile.SHAPE_BASE;
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mWidth = getWidth();
        mHeight = getHeight();

        mTileWidth = mWidth / GameConfig.GAME_COLUMNS;
        mTileHeight = mHeight / GameConfig.GAME_ROWS;

        mWidthRemind = mWidth % GameConfig.GAME_COLUMNS;
        mHeightRemind = mHeight % GameConfig.GAME_ROWS;

        mWidth = mWidth - mWidthRemind;
        mHeight = mHeight - mHeightRemind;

        draw(holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mSurfaceHolder.removeCallback(this);
        mSurfaceHolder = null;
    }

    private void draw(SurfaceHolder holder) {
        if(holder != null) {
            synchronized (holder) {
                Canvas canvas = holder.lockCanvas();

                if(canvas != null) {
                    drawTiles(canvas);
                    drawCurrentTile(canvas);

                    drawBgLine(canvas);

                    canvas.restore();
                    holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    private void drawBgLine(Canvas canvas){

        mPaint.setColor(Color.GRAY);
        for(int x=0; x <= mWidth; x += mTileWidth){
            canvas.drawLine(x, 0, x, mHeight, mPaint);
        }

        mPaint.setColor(Color.GRAY);
        for(int y=0; y <= mHeight; y += mTileHeight){
            canvas.drawLine(0, y, mWidth, y, mPaint);
        }

        canvas.save();
    }

    private void drawTiles(Canvas canvas){
        for(int x = 0; x < GameConfig.GAME_COLUMNS; x++){
            for(int y = 0; y < GameConfig.GAME_ROWS; y++){
                if(mFreezeTileMatrix[x][y] != BaseTile.SHAPE_BASE){
                    int color = BaseTile.COLORS[mFreezeTileMatrix[x][y]];
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
                    int color = BaseTile.COLORS[0];
                    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                    paint.setColor(color);

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

    private void drawCurrentTile(Canvas canvas){
        for(int x = 0; x < GameConfig.GAME_COLUMNS; x++){
            for(int y = 0; y < GameConfig.GAME_ROWS; y++){
                if(mCurrentTileMatrix[x][y] != BaseTile.SHAPE_BASE){
                    int color = BaseTile.COLORS[mCurrentTileMatrix[x][y]];
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
                    paint.setColor(Color.TRANSPARENT);

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

    private void update(){
        draw(mSurfaceHolder);
    }

    public void updatePuzzleView(Tile tile){
        mCurrentTile = tile;
        mCurrentTile.updateMatrix(mCurrentTileMatrix, mFreezeTileMatrix);

        int gameState = GameHelper.getInstance(mContent).getGameState();

        if(mCurrentTile.getTileState() == BaseTile.STATE_FREEZE && gameState == GameHelper.RUNNING){
            for(int x = 0; x < GameConfig.GAME_COLUMNS; x++) {
                for (int y = 0; y < GameConfig.GAME_ROWS; y++) {
                    mFreezeTileMatrix[x][y] = mFreezeTileMatrix[x][y] | mCurrentTileMatrix[x][y];
                }
            }

            resetCurrentTileMatrix();

            int comboLines = Utils.changeMatrixHasLine(mFreezeTileMatrix);
            if(comboLines > 0 && mOnGameStateListener != null){
                mOnGameStateListener.onComboLines(comboLines);
            }

            Point minY = Utils.findMinYPoint(mCurrentTile.getCurPoints());
            if(mOnGameStateListener != null){
                if(minY.y <= 0){
                    mOnGameStateListener.onGameStateChange(GameHelper.OVER);
                }else{
                    mOnGameStateListener.onNeedTile();
                }
            }
        }

        update();
    }

    public void setOnGameStateListener(OnGameStateListener gameStateListener){
        mOnGameStateListener = gameStateListener;
    }
}
