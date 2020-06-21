package main;

//@Author Adrien Fabian

import java.util.*;


public class Simple_Reflex_Agent
{
        // Global Variables
	static int steps = 0; //change to any value above 1000 to stop the program at checkSteps
	static int dirtSucked = 0; // currently set to 19, that way if dirt is sucked goToLobby is triggered
	//static int[] loc = {0, 7}; //these are the coordinates of the top right number, change to get different location values to test goToLobby. 
	//NOTE!: loc is a 1x2 array, 0 holds the row, 1 holds the column information
	static int[] loc = {7, 0}; //these are the coordinates of the lobby where the Agent starts

	public static void main(String[] args)
	{
		int[][] board = new int[8][8]; //creates the 8x8 board
                
		populate(board); //populates it
		printBoard(board); // Print Board 1st time
                printDirtLocation(board); // Print Dirt Location
		
		System.out.println("The Agent is in row " + (loc[0] + 1) + " and column " + (loc[1] + 1) + ", also known as the lobby");

		// THIS IS A TEST
		for(int i = 0; i < 1000; i++) //made this a for loop because it was easy, we can change this to a while loop or any other way of doing it
		{
                    actions.Simple_Reflex_Agent_Actions(loc); // Syntax is class["actions"].methodName["Simple_Reflex_Agent_Actions"](Parameter)
                                                    // this calls a random action for the AI to make. 100% works
			
                    steps++; 
                    System.out.println("The Agent has taken " + steps + " steps");
                    sensors.seesDirt(loc, board);

			if((dirtSucked == 20) || (checkSteps(steps, board))) //checks if it sucked up dirt 20 times or if the number of steps is 1000 or above to start end-of-script functions
			{
				if(steps < 984) //checks if we have enough steps remaining to make it back to the lobby
				{
					goToLobby(loc); //takes us back to the lobby
				}

				performance(loc,board); //prints performance information
   				System.out.println();
                                printBoard(board); //prints the board as it currently is
				endAgent(); //ends the program
			}
			checkSteps(steps, board); //checks the number of steps the Agent has taken
		}
                
   		//dirtSuck(loc, board); //determines if there is dirt in the location set in loc above, if present sucks it up
   		//if((dirtSucked >= 20) && (!checkSteps(steps))) //checks if it sucked up dirt 20 times, if true and number of steps is below 1000 it goes to the lobby.
		//{
		//	goToLobby(loc);
		//}
		   
		//performance(loc);
   		//System.out.println();
        //printBoard(board);
	}
	
	public static void endAgent()
	{
		System.exit(0);
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
                    System.out.println("There is Dirt at coordinates [" + (i + 1) + "," + (j + 1) + "]");
                }
            }
        }
        System.out.println();
    }
        
	public static int[] goToLobby(int[] loc)
	{
		int locX = loc[0]; //copies current row
		int locY = loc[1]; //copies current column
		
		System.out.println();
		if(!sensors.isLobby(loc) && (steps < 984)) //checks to see if the agent is in the lobby or not and step count can let it go to the lobby
		{
			System.out.println("The Agent is going to the lobby from row " + (locX + 1) + " and column " + (locY + 1) + " and has taken " + steps + " steps so far");
			//tells you where the agent currently is (see loc in the global variables, should match)
			for(int i = locX; i < 7; i++) //sets row number to current row, increases until it hits the bottom row, 7
			{	
				locX++; //increases the row its on
				steps++; //increases step count
				System.out.println("The Agent moved down to row " + (locX + 1) + " and column " + (locY + 1));
				System.out.println("The Agent has taken " + steps + " steps");
			}
			for(int j = locY; j > 0; j--) //sets column number to current column, decreases until it hits the leftmost column, 0
			{
				locY--; //decreases the column its on
				steps++;//increases step count
				System.out.println("The Agent moved left to row " + (locX + 1) + " and column " + (locY + 1));
				System.out.println("The Agent has taken " + steps + " steps");
			}
		}
		System.out.println("The Agent is now in row " + (locX + 1) + " and column " + (locY + 1)); //tells you where it is after moving
		loc[0] = locX; //modifies the row its on to return
		loc[1] = locY; //modifies the column its on to return
		return loc;
	}

	public static boolean checkSteps(int step, int[][] board)
	{
		if(steps >= 1000) //check step count to find out if its equal or over 1000
		{
			System.out.println("The program has stopped due to too many steps");
			performance(loc, board);
			System.exit(0);
			return true; //Bad news for our score
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

		board[locX][locY] = 0; //sets where there was dirt to 0 to indicate there is no dirt remaining
		steps++; //increase step count
		dirtSucked++; //increases the number of dirts sucked up
		System.out.println("\tThe Agent sucked up dirt at row " + (locX + 1) + " and column " + (locY + 1));
		System.out.println("\tThe Agent has sucked up " + dirtSucked + " dirt");
		System.out.println("The Agent has taken " + steps + " steps");
		return board; //returns new board
	}

	public static void performance(int[] loc, int[][] board)
	{
		int score = 0; //start score off at 0
		int dirtScore = dirtSucked * 100; //calculates score for sucking dirt
		int stepScore = steps * 10; //calculates score for taking steps
		score = dirtScore - stepScore; //totals the score
		System.out.println();

		if(sensors.isLobby(loc) == false) //determines if the agent is in the lobby for score purposes
		{
			score = score - 1000; //deducts points for not being in the lobby
			System.out.println("The Agent is not in lobby; The Agent loses 1000 points");
                        System.out.println();
                        
                        printBoard(board);
                        // Scans the entire board again for the remaining dirt
                        for(int i = 0; i < board.length; i++) 
                        {
                            for(int j = 0; j < board[i].length; j++)   
                            {
                                if (board[i][j] == 1)
                                {
                                    System.out.println("There is remaining dirt at coordinates [" + (i + 1) + "," + (j + 1) + "]");
                                    System.out.println();
                                }
                            }
                        }       
                }
		else
		{
			System.out.println("The Agent is in the lobby");
		}

		System.out.println("The Agent's score is: " + score); //takes care of the score at the end
		System.out.println("The Agent earned " + dirtScore + " points for successfully sucking " + dirtSucked + " dirt and lost " + stepScore + " points for taking " + steps + " steps");
	}
}