package com.chronoer.game.rotatepuzzle.obj;

import com.chronoer.game.rotatepuzzle.env.GameConfig;

import java.util.Random;

/**
 * Created by chronoer on 2019/3/18.
 */

public class FactoryTile {

    public static Tile getRandomTile(int level){
        Random random = new Random();
        int shapeIndex = random.nextInt(BaseTile.SHAPES.length -1) + 1;//dont return zero
        int shape = BaseTile.SHAPES[shapeIndex];
        //shape = BaseTile.SHAPE_L;
        Tile tile;
        if(shape == BaseTile.SHAPE_BASE){
            //wont be here
            tile = getTileB();
        }else if(shape == BaseTile.SHAPE_I){
            tile = getTileI();
        }else if(shape == BaseTile.SHAPE_J){
            tile = getTileJ();
        }else if(shape == BaseTile.SHAPE_L){
            tile = getTileL();
        }else if(shape == BaseTile.SHAPE_O){
            tile = getTileO();
        }else if(shape == BaseTile.SHAPE_S){
            tile = getTileS();
        }else if(shape == BaseTile.SHAPE_T){
            tile = getTileT();
        }else if(shape == BaseTile.SHAPE_Z){
            tile = getTileZ();
        }else{
            tile = getTileB();
        }

        tile.setFallTick(GameConfig.GAME_TILE_FALL_SPEED_DEFAULT / level);
        return tile;
    }

    private static Tile getTileB(){
        return new TileB();
    }

    public static Tile getTileI(){
        return new TileI();
    }

    public static Tile getTileJ(){
        return new TileJ();
    }

    public static Tile getTileL(){
        return new TileL();
    }

    public static Tile getTileO(){
        return new TileO();
    }

    public static Tile getTileS(){
        return new TileS();
    }

    public static Tile getTileT(){
        return new TileT();
    }

    public static Tile getTileZ(){
        return new TileZ();
    }
}
