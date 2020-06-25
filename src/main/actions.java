package main;

import main.*;
import java.io.*;
import java.util.Random;
/**
 *
 * @author Adrien Fabian, Emerson de Lemmus
 */


public class actions 
{
    public static void Simple_Reflex_Agent_Actions(int[] loc) 
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
           int randomAction = new Random().nextInt(4);  
           switch (randomAction)
           {
                case 0:
                   goUp(loc); break;
                case 1:
                   goDown(loc); break;
                case 2:
                    moveLeft(loc); break;
                case 3:
                    moveRight(loc); break;
                case 4:
                    suck(loc); break;
                case 5:
                    shutOff(); break;
           }     
     }
    
    public static int[] goUp(int[] loc)
    {   
        //Adrien's addition
        if(sensors.bumped(loc, 1))
        {
            System.out.println("The Agent bumped into the wall trying to go up");
        }
        else
        {
            loc[0] = loc[0] - 1;
            System.out.println("The Agent moved up to row " + (loc[0] + 1) + " and column " + (loc[1] + 1));
        }
        return loc;
    }
    
    public static int[] goDown(int[] loc)
    {   
        //Adrien's addition
        if(sensors.bumped(loc, 2))
        {
            System.out.println("The Agent bumped into the wall trying to go down");
        }
        else
        {
            loc[0] = loc[0] + 1;
            System.out.println("The Agent moved down to row " + (loc[0] + 1) + " and column " + (loc[1] + 1));    
        }
        return loc;
    }
    
    public static int[] moveLeft(int[] loc)
    {   
        //Adrien's addition
        if(sensors.bumped(loc, 3))
        {
            System.out.println("The Agent bumped into the wall trying to go left");
        }
        else
        {
            loc[1] = loc[1] - 1;
            System.out.println("The Agent moved left to row " + (loc[0] + 1) + " and column " + (loc[1] + 1));
        }
        return loc;
    }
    
    public static int[] moveRight(int[] loc)
    {   
        //Adrien's addition
        if(sensors.bumped(loc, 4))
        {
            System.out.println("The Agent bumped into the wall trying to go right");
        }
        else
        {
            loc[1] = loc[1] + 1;
            System.out.println("The Agent moved right to row " + (loc[0] + 1) + " and column " + (loc[1] + 1));
        }
        return loc;
    }
    
    public static int[] suck(int[] loc)
    {   
        System.out.println("**TEST** Action Called: succ");
        return loc;
    }
    
    public static void shutOff()
    {   
        System.out.println("shutOff(); has been called "
                + "This AI has been termimated");
        System.exit(0);
    }
}