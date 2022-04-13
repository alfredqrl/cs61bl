package PlusWorld;
import org.junit.Test;
import static org.junit.Assert.*;

import byowTools.TileEngine.TERenderer;
import byowTools.TileEngine.TETile;
import byowTools.TileEngine.Tileset;

import java.util.Arrays;
import java.util.Random;

/**
 * Draws a world consisting of plus shaped regions.
 */
public class PlusWorld {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    public static void addPlus(int size, TETile [][] myPlusWorld){
        for (int i = 0; i < WIDTH; i++){
            for (int j = 0; j < HEIGHT; j++){
                myPlusWorld[i][j] = Tileset.NOTHING;
            }
        }

        int widplus = size / 3;
        int newsize = widplus *3;
        for (int i = 0; i < newsize; i++){
            for (int j = 0; j < newsize; j++){
                if (i == widplus){
                    for (int l = 0; l < widplus; l++){
                        myPlusWorld[i + l][j] = Tileset.WALL;
                    }
                }else if (j == widplus){
                    for (int l = 0; l < widplus; l++){
                        myPlusWorld[i][j + l] = Tileset.WALL;
                    }
                }
            }
        }
    }

    public static void main(String [] args){
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile [][] plusWorld = new TETile[WIDTH][HEIGHT];
        addPlus(Integer.parseInt(args[0]), plusWorld);
        ter.renderFrame(plusWorld);
    }
}
