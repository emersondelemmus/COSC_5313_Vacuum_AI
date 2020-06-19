package main;

//@Author Adrien

import java.util.*;


public class Simple_Reflex_Agent
{
	static int steps = 0; //change to any value above 1000 to stop the program at checkSteps
	static int dirtSucked = 19; // currently set to 19, that way if dirt is sucked goToLobby is triggered
	static int[] loc = {0, 7}; //these are the coordinates of the top right number, change to get different location values to test goToLobby. NOTE!: loc is a 1x2 array, 0 holds the row, 1 holds the column information
	//int[] loc = {7, 0}; these are the coordinates of the lobby

	public static void main(String[] args)
	{
		int[][] board = new int[8][8]; //creates the 8x8 board
                
		populate(board); //populates it
		printBoard(board); // Print Board 1st time
                printDirtLocation(board); // Print Dirt Location
                
                // THIS IS A TEST
                actions.Simple_Reflex_Agent_Actions(board); // Syntax is class["actions"].methodName["Simple_Reflex_Agent_Actions"](Parameter)
                                                            // this calls a random action for the AI to make. 100% works
                
                
   	/*	dirtSuck(loc, board); //determines if there is dirt in the location set in loc above, if present sucks it up

   		if((dirtSucked == 20) && (!checkSteps(steps))) //checks if it sucked up dirt 20 times, if true and number of steps is below 1000 it goes to the lobby.
		   		//should probably change this to see if its above 984 steps to allow goToLobby to finish
		{
			goToLobby(loc);
		}

   		System.out.println("The Agent's score is: " + performance(loc)); //takes care of the score at the end
   	*/	System.out.println();
                
                printBoard(board);
	}
  
        public static int[][] populate(int[][] board)
	{
            int numDirts = 0; //the starting number of dirt squares in the board, currently 0
            Random random = new Random(); //randomly placed dirt, eh?

            while(numDirts < 20) //keeps going until 20 dirts are placed
            {
                int x = random.nextInt(board.length); //chooses a row
                int y = random.nextInt(board[0].length); // chooses a column
                if(board[x][y] == 0) //checks to make sure it doesn't place dirt where there is already dirt
                {
                    board[x][y] = 1; //changes the value of 0 (no dirt) to 1 (dirt)
                    numDirts++; //increments the number of dirts on the board
                }
            }
            return board;
	}
        
        // Emerson's Additions: created a method to print out the board
        public static void printBoard(int[][] board) 
        {
            for(int i = 0; i < board.length; i++) 
            {
                for(int j = 0; j < board[i].length; j++)
                {
                    System.out.printf("%5d ", board[i][j]);
                }
                    System.out.println();
            }
                System.out.println();
        }
        
        //Emerson's Addition: created a method to print out the location of dirt at the start 
        public static void printDirtLocation(int[][] board)
        {
            for(int i = 0; i < board.length; i++) 
            {
                for(int j = 0; j < board[i].length; j++)   
                {
                    if (board[i][j] == 1)
                    {
                        // Had to incrememt i,j by 1 because humans count from ONE not ZERO
                        System.out.println("There is Dirt at coordinate [" + (i+1) + "," + (j+1) + "]");
                    }
                }
            }
            System.out.println();
        }

        
/*	public static int[] goToLobby(int[] loc)
	{
		int locX = loc[0]; //copies current row
		int locY = loc[1]; //copies current column

		System.out.println("The Agent is going to the lobby from row " + locX + " and column " + locY); //tells you where the agent currently is (see loc in the global variables, should match)

		if(!isLobby(loc)) //checks to see if the agent is in the lobby or not
			for(int i = locX; i < 7; i++) //sets row number to current row, increases until it hits the bottom row, 7
			{
				locX++; //increases the row its on
				steps++; //increases step count
				System.out.println("The Agent moved one step down, and is now in row: " + locX);
			}

			for(int j = locY; j > 0; j--) //sets column number to current column, decreases until it hits the leftmost column, 0
			{
				locY--; //decreases the column its on
				steps++;//increases step count
				System.out.println("The Agent moved one step left, and is now in column: " + locY);
			}

		System.out.println("The Agent is now in row " + locX + " and column " + locY); //tells you where it is after moving
		loc[0] = locX; //modifies the row its on to return
		loc[1] = locY; //modifies the column its on to return
		return loc;
	}
*/
	public static boolean checkSteps(int steps)
	{
		if(steps >= 1000) //check step count to find out if its equal or over 1000
		{
			//stop; make this part work
			System.out.println("The program has stopped due to too many steps");
			return true; //uh oh, its over 1000, RIP score
		}
		else
		{
			return false; //huzzah, its below, hopefully we can use goToLobby
		}
	}

	public static int[][] dirtSuck(int[] loc, int[][] board)
	{
		int locX = loc[0]; //copies current row
		int locY = loc[1]; //copies current column

		if(board[locX][locY] == 1) //checks to see if there is dirt at its location
		{
			board[locX][locY] = 0; //sets where there was dirt to 0 to indicate there is no dirt remaining
			steps++; //increase step count
			dirtSucked++; //increases the number of dirts sucked up
			System.out.println("The Agent sucked up dirt at row " + locX + " and column " + locY);
			return board; //returns new board
		}
		else
		{
			System.out.println("No dirt was found at row " + locX + " and column " + locY);
			return board; //apparently this needed a return too, so it returns the unchanged board.
		}
	}

	public static int performance(int[] loc)
	{
		int score = 0; //start score off at 0
		score = dirtSucked * 100 - steps * 10; //gain 100 for sucking dirt, lost 10 per step taken

		if(isLobby(loc) == false) //determines if the agent is in the lobby for score purposes
		{
			score = score - 1000; //oof, not in the lobby. RIP score
			System.out.println("The Agent is not in lobby");
		}
		else
		{
			System.out.println("The Agent is in lobby");
		}

		return score;
	}

	public static boolean isLobby(int[] loc)
	{
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
             
