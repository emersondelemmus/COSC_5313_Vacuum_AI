package main;

import main.*;
import java.io.*;
import java.util.Random;
/**
 *
 * @author Emerson de Lemmus, 
 */


public class actions 
{
    // I REALIZED THAT THIS METHOD WON'T ACTUALLY USE THE ENTIRE BOARD TO GIVE BACK A NEW POSITION.
    // IT JUST NEEDS THE CURRENT LOCATION COORDINATES
    public static void Simple_Reflex_Agent_Actions(int[][] board) 
    {
        
        /* Actions possible as defined by the assignment
        - Go up
        – Go down
        – Move left
        – Move right
        – Suck
        – Shut off (vacuum agent shuts off)
        */
      
            // Randomizer (0 <-> 6) will randomly choose an action
           int randomAction = new Random().nextInt(6);
           switch (randomAction)
           {
                case 0:
                   goUp(board); break;
                case 1:
                   goDown(board); break;
                case 2:
                    moveLeft(board); break;
                case 3:
                    moveRight(board); break;
                case 4:
                    suck(board); break;
                case 5:
                    shutOff(); break;
           }
                   
     }

    
    public static int[][] goUp(int[][] board)
    {   
        System.out.println("**TEST** Action Called: go up");
        return board;
    }
    
    public static int[][] goDown(int[][] board)
    {   
        System.out.println("**TEST** Action Called: go down");
        return board;
    }
    
    public static int[][] moveLeft(int[][] board)
    {   
        System.out.println("**TEST** Action Called: move left");
        return board;

    }
    
    public static int[][] moveRight(int[][] board)
    {   
        System.out.println("**TEST** Action Called: move right");
        return board;
    }
    
    public static int[][] suck(int[][] board)
    {   
        System.out.println("**TEST** Action Called: succ");
        return board;

    }
    
    public static void shutOff()
    {   
        
        System.out.println("shutOff(); has been called "
                + "This AI has been termimated");
        System.exit(0);
        
    }
}