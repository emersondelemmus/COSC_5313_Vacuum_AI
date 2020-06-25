package main;

import main.*;
import java.util.*;

import javax.lang.model.util.ElementScanner14;

//import jdk.internal.agent.resources.agent;
/**
 *
 * @author Adrien Fabian, Emerson de Lemmus
 */
public class sensors {
    
    /*  bumped()tells whether or not the vacuum just bumped into a wall
        seesDirt(): tells whether or not the current square is dirty.
        isLobby(): tells whether or not the current square is the lobby square.
    */
    public static boolean bumped(int[] loc, int dir)
    {
        //Adrien's addition, includes all caseDirection methods
        if(dir == 1)
        {
            return caseUp(loc);
        }
        else if(dir == 2)
        {
            return caseDown(loc);
        }
        else if(dir == 3)
        {
            return caseLeft(loc);
        }
        else
        {
            return caseRight(loc);
        }
    }

    public static boolean caseUp(int[] loc)
    {
        if(loc[0] - 1 == -1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean caseDown(int[] loc)
    {
        if(loc[0] + 1 == 8)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean caseLeft(int[] loc)
    {
        if(loc[1] - 1 == -1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean caseRight(int[] loc)
    {
        if(loc[1] + 1 == 8)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public static void seesDirt(int[] loc, int[][] board)
    {
        //Adrien's addition: migrated the dirt detection part of dirtSuck to here
        //left the sucking part in SRA, but can migrate that to another method here if needed
        int locX = loc[0]; //copies current row
        int locY = loc[1]; //copies current column

        if(board[locX][locY] == 1) //checks to see if there is dirt at its location
	{
            Simple_Reflex_Agent.dirtSuck(loc, board);
        }
    }
    
    public static boolean isLobby(int[] loc)
    {
        //Adrien's addition: migrated isLobby from SRA to here
        int[] lobby = {7, 0}; //hardcoded lobby location
		if(Arrays.equals(loc, lobby)) //checks to see if the coordinate pairs match up. Array location 0 holds row information, array location 1 holds column information
		{
			return true;
		}
		else
		{
			return false;
		}
    }
}