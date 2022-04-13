package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import jdk.jshell.execution.Util;
import byow.Core.*;
import org.junit.Test;

import java.io.*;
import java.util.*;

import static byow.Core.Utils.*;


public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;
    public static final int MaxRoomNum = 20;

    private Random seed;

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, both of these calls:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {
        //ArrayList<Random> seedGenerated = new ArrayList<>();
        ter.initialize(WIDTH,HEIGHT);
        // TODO: Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.
        // TODO: Change the argument from type String to Long
        String temp = input;
        File save_and_load = new File("save.txt");
        if ((temp.contains("l") || temp.contains("L")) && save_and_load.exists()){
            String original = readContentsAsString(save_and_load);
            temp = original;
        }
        TETile [][] finalWorldFrame = generateWorldFirstTime(temp);
        if (input.contains(":q") || input.contains(":Q")){
            writeContents(save_and_load, temp);
        }
        ter.renderFrame(finalWorldFrame);
        return finalWorldFrame;
    }

    private TETile [][] generateWorldFirstTime(String input){
        char [] inArray = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : inArray){
            if (Character.isDigit(c) && sb.length() < 38) {
                sb.append(c);
            }
            else if (c == 's' && sb.length() > 0) {
                break;
            }
        }

        Long l = Long.parseLong(sb.toString());
        System.out.println(l);
        Random seed = new Random(l);

        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        initialiseFrame(finalWorldFrame);
        int rndNumWidth = RandomUtils.uniform(seed, 0, WIDTH - 1);

        int rndNumOfRoom =RandomUtils.uniform(seed,0, MaxRoomNum);
        //System.out.println(rndNumOfRoom);

        //System.out.println(rndNumWidth);
        int rndNumHeight = RandomUtils.uniform(seed, 0, HEIGHT - 1);

        //System.out.println(rndNumHeight);
        buildSquare(finalWorldFrame, rndNumWidth ,rndNumHeight, rndNumOfRoom);
        //buildSquare(finalWorldFrame, rndNumWidth, rndNumHeight);
        buildRoom(finalWorldFrame, rndNumWidth, rndNumHeight);

        return finalWorldFrame;
    }

    private void initialiseFrame (TETile [][] finalWorldFrame){
        for (int i = 0; i < WIDTH; i++){
            for (int j = 0; j < HEIGHT; j++){
                finalWorldFrame[i][j] = Tileset.NOTHING;
            }
        }
    }

    private void buildSquare(TETile [][] w, int col, int row, int roomNum){

        for (int i = 0; i < WIDTH; i++){
            for (int j = 0; j < HEIGHT; j++){
                if (i >= col && i <= col + 8){
                    w[i][j] = Tileset.WALL;
                }
                if (j >= row && j <= row + 8){
                    w[i][j] = Tileset.FLOOR;
                    w[roomNum][roomNum] = Tileset.FLOOR;
                    w[roomNum][j] = Tileset.FLOOR;
                }
            }
        }

    }

    private void buildRoom(TETile [][] w, int col, int row){
        w[col][row] = Tileset.FLOWER;
    }
}
