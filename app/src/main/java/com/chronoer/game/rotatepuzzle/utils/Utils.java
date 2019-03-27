package com.chronoer.game.rotatepuzzle.utils;

import android.graphics.Point;

import com.chronoer.game.rotatepuzzle.env.GameConfig;
import com.chronoer.game.rotatepuzzle.obj.BaseTile;

/**
 * Created by chronoer on 2019/3/19.
 */

public class Utils {

    public static int changeMatrixHasLine(int[][] matrix){
        int line = 0;
        for (int y = 0; y < GameConfig.GAME_ROWS; y++) {
            int hasLine = -1;
            for(int x = 0; x < GameConfig.GAME_COLUMNS; x++) {
                hasLine = y;
                if(matrix[x][y] == BaseTile.SHAPE_BASE){
                    hasLine = -1;
                    break;
                }
            }

            if(hasLine > -1){
                line = line + 1;
                for(int x = 0; x < GameConfig.GAME_COLUMNS; x++) {
                    for (int z = hasLine; z >= 1; z--) {
                        matrix[x][z] = matrix[x][z - 1];
                        matrix[x][z - 1] = BaseTile.SHAPE_BASE;
                    }
                }
            }
        }

        return line;
    }

    public static Point findMaxXPoint(Point[] points){
        Point maxXPoint = null;
        for(Point point : points){
            if(maxXPoint == null){
                maxXPoint = point;
            }else{
                if(maxXPoint.x < point.x){
                    maxXPoint = point;
                }
            }
        }

        return maxXPoint;
    }

    public static Point findMaxYPoint(Point[] points){
        Point maxYPoint = null;
        for(Point point : points){
            if(maxYPoint == null){
                maxYPoint = point;
            }else{
                if(maxYPoint.y < point.y){
                    maxYPoint = point;
                }
            }
        }

        return maxYPoint;
    }

    public static Point findMinXPoint(Point[] points){
        Point minXPoint = null;
        for(Point point : points){
            if(minXPoint == null){
                minXPoint = point;
            }else{
                if(minXPoint.x > point.x){
                    minXPoint = point;
                }
            }
        }

        return minXPoint;
    }

    public static Point findMinYPoint(Point[] points){
        Point minYPoint = null;
        for(Point point : points){
            if(minYPoint == null){
                minYPoint = point;
            }else{
                if(minYPoint.y > point.y){
                    minYPoint = point;
                }
            }
        }

        return minYPoint;
    }
}
