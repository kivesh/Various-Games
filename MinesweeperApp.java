import java.util.Scanner;
public class MinesweeperApp
{
	public static void main(String[] args)
	{ 
	  Scanner input = new Scanner(System.in);
	   String playAgain ="";//Variable that will store the users response to play again.
	   do //This loop will run the game at least once then ask the player to play again.
		{
		   System.out.println("\n__________________________MINESWEEPER__________________________\n");
		   
			//Process A -Obtain size of board from player.   
			System.out.print("Enter the size of the board: ");
			int size = input.nextInt();
			 while(size<2)//User validation for the correct size of the board it must be greater than 1.
			 {
			  System.out.println("Please note that the size of the board must be greather than 1!");
			  System.out.print("Enter the size of the board: ");
			  size = input.nextInt();
			 }
			Minesweeper game = new Minesweeper(size);
			//End of Process A
			
			/*Process B - Obtain the number of mines from the player.
			              Please note that according to the rules of minesweeper there must be atleast be one cell with no mine.
			              So the range of mines are 1 to (size*size -1) for eg board of size 3 will range 1 to 8 for mines.
			*/
			int rangeLower = 1;
			int rangeUpper = (size*size)-1;
			System.out.print("Enter the number of mines to put into the board in the range ("+rangeLower+" to "+rangeUpper+ "): ");
			int numOfMines = input.nextInt();
			 while(numOfMines<rangeLower||numOfMines>rangeUpper)//If the number of mines is not in the specified range it will reiterate until it is in the range
			 {
			  System.out.println("\nPlease enter in the range provided!");
			  System.out.print("Enter the number of mines to put into the board in the range ("+rangeLower+" to "+rangeUpper+ "): ");
			  numOfMines = input.nextInt();
			 }
			game.fillMines(numOfMines);//This method fills the mines onto the board and calls the calculatePoints() method.
			//End of Process B
			
			game.displayBoard();//Display the board.
			
			while(game.gameWon()==false&&game.openedMine()==false)//While the player has not won and not opened a mine > select tiles to open or flag.
			{
						
			//Process C - obtain which tile to open or flag.
				System.out.print("Do you want to (open or flag) a tile: ");
				String operation = input.next().toLowerCase();
				 while(!(operation.equals("open")||operation.equals("flag")))//It will iterate until the correct operation has been entered
				 {
				  System.out.println("\nPlease enter one of the operations in the brackets below.");
				  System.out.print("Do you want to (open or flag) a tile: ");
				  operation = input.next().toLowerCase();
				 }
				 
				//Obtain new status of the tile
				 int newStatus = 2;// I have assigned its default value to flag the tile
				 if(operation.equals("open"))
				 {
				 	newStatus = 1;//Which means that it will open the tile.
				 }
				  
				/*The while loop below will continue to run until: 
				  1. The row and column are in the specified range  and,  
				  2. The tile selected is closed(which means it has not been opened already)
				*/
				while(true)//This loop will break when a tile is opened or flagged successfully.
				{
				   // The player will now enter the specified position of the tile.
				   System.out.print("Enter the row in the range (1 to "+size+"): ");
				   int row = input.nextInt();
				   System.out.print("Enter the column in the range (1 to "+size+"): ");
				   int column = input.nextInt();
					 	         
					while(row<1||row>size||column<1||column>size)//If the column or range is out of the board range it will iterate until correct values are entered/
				   {
					 System.out.println("\nIncorrect values please re-enter a value in the range provided in the brackets.");
				    System.out.print("Enter the row in the range (1 to "+size+"): ");
				     row = input.nextInt();
				    System.out.print("Enter the column in the range (1 to "+size+"): ");
				     column = input.nextInt();
				   }
					//Since the player enters the row & column from 1 - to the array Length. I have to decrement row and column to access the correct positions.
					 row--;
					 column--;
					 
				   //I will now check whether the tile has been opened
				   if(game.getTileStatus(row,column)==0)//If tile is closed to open the tile and break out of the while loop
				   {
				     game.markTile(row,column,newStatus);
					  break;//Break out of the infinite loop and proceed
				   }
					System.out.println("\nThe tile is already opened please select another.");			
			  }
			 game.displayBoard();
		 //End of Process C - Successfully opened or flagged a tile.  
			
		  }
		  
		//Proces D - Deduce whether the player has won or lost
		 if(game.gameWon())//Player has won
		 {
		  System.out.println("Congratulations you have Won!!!\n");
		 }
		 else//Player has selected a mine - openedMine()==true
		 {
	 	  System.out.println("Game over!\n");
		 }
		//End of Process D
		 
	 /*Process E - Ask the player to play again
	    The playAgain variable is the loop controller for the do while loop.
	  */
		  System.out.print("Do you want to play another game (yes/no): ");
		  playAgain = input.next();		  
		  while(!(playAgain.equalsIgnoreCase("yes")||playAgain.equalsIgnoreCase("no")))
		  {
		    System.out.println("Please enter one of the options in the brackets.");
			 playAgain = input.next();
		  }
		//End of Process E
		}
		while(playAgain.equalsIgnoreCase("yes"));
		
		System.out.println("Thanks for playing.");
	}
}
