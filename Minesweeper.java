/*   1. getClue()- Will calculate the clue then return it.
     2. openedMine() - Will return true if a player has opened a mine.
*/
public class Minesweeper
{
	int[][] mines;
	int[][] tiles;
	
	public Minesweeper(int size)
	{
	    mines = new int[size][size];
		 tiles = new int[size][size]; //No need to initialise the tiles array because it default value is 0-(closed tile).
	}

	public void fillMines(int num)//fill the board with a specified number of mines and then call calculateClues() method
	{
       int minesPlaced =0;//Variable used as a loop controller to count the number of mines placed.
		 while(minesPlaced<num)//Loop will terminate when correct number of mines have been placed.
		 {
		  int randomRow = (int)(Math.random()*mines.length);//Creates a random number from 0 to mines.length-1 .
		  int randomCol = (int)(Math.random()*mines.length);//Creates a random number from 0 to mines.length-1 .
		  if(mines[randomRow][randomCol]!=9)//Checks that the random location does not contain a mine.
		  {
		    mines[randomRow][randomCol]=9;
			 minesPlaced++;
		  }
		 }
		 
		 calculateClues();//Calls the calculateClues method to fill the mines array with clues
	}
	
	public void calculateClues()
	{  
	   /* Please note that this method uses the getClue() method below
		   Please familarise yourself with the IF STATEMENT MAPPING.
	      Which requires parameters: 1. row of current cell
			                           2. column of current cell
			                           3. casePositons of type string which contains all adjacent
												   cells to be tested int the format, for eg 125 represent which if statements to use.
		*/ 
		for(int row=0;row<mines.length;row++)
		{
			for(int col=0;col<mines[row].length;col++)
			{
           if(mines[row][col]!=9)//If the cell does not contain a mine then calculate clue
			  { 
				 //For the corner cells - They will have 3 adjacent cells
				 if(row==0&&col==0)//Top left-hand corner
				 {
				   mines[row][col] = getClue(row,col,"578");//The string specifies which if statements to test
				 }			
			    else if(row==0&&col==mines.length-1)//Top right-hand corner
				 {
				  mines[row][col] = getClue(row,col,"467");
				 } 
				 else if(row==mines.length-1&&col==0)//Bottom left-hand corner
				 {
				  mines[row][col] = getClue(row,col,"235");
				 }
				 else if(row==mines.length-1&&col==mines.length-1)//Bottom right-hand corner
				 {
				  mines[row][col]  = getClue(row,col,"124");
				 }
				 
				 //For the border cells (between the corner cells)- They will have 5 adjacent cells
				 else if(row==0&&(col>0&&col<mines.length-1))//Between top corners
				 {
				  mines[row][col] = getClue(row,col,"45678");//The string specifies which if statements to test
				 }
				 else if(row==mines.length-1&&(col>0&&col<mines.length-1))//Between bottom corners
				 {
				  mines[row][col] = getClue(row,col,"12345");
				 }
				 else if(col==0&&(row>0&&row<mines.length-1))//Between left corners
				 {
				  mines[row][col] = getClue(row,col,"23578");
				 }
				 else if(col==mines.length-1&&(row>0&&row<mines.length-1))//Between Right corners
				 {
				  mines[row][col] = getClue(row,col,"12467");
				 }
				 //For all the cells in the centre(not the borders or corners)- they have 8 adjacent cells
				 else if((row>0&&row<mines.length-1)&&(col>0&&col<mines.length-1))
				 {
				  mines[row][col] = getClue(row,col,"12345678"); //The string specifies which if statements to test
				 }
			  }
			}
		}
		
	}
	
	 public int getClue(int row, int col, String casePositions)//Additional method - Will return the number of mines in adjacent cells
	 {
		  /* 
		        IF STATEMENT MAPPING
		       
			@- represents the index in which the clue will go into
		  	
			 1-9 represent the different if statements used in the switch statement below
			 eg 1 represents if(mines[row-1][col-1]==9)
			  			
						1 2 3
						4 @ 5
						6 7 8
					
			Uses this map to search the adjacent postions for mines
		  */
		  
       /* 
		   Please note that @ can be at the borders and corners for eg
		   
			 @ 5     		       4 @ 5              1 2
          7 8      and         6 7 8      and     4 @
			                                         6 7 
			   
		 */
		 
		 int noOfMines=0;
		 
		 for(int numCases=0; numCases<casePositions.length();numCases++)
		 {
		  char ifCase = casePositions.charAt(numCases);//Obtains which if statement to test from numCases string.
		  switch(ifCase)
		 {
		   case '1': if(mines[row-1][col-1]==9)
			          { noOfMines++; } 
					    break;				  
		   case '2': if(mines[row-1][col]==9)
			          { noOfMines++; }
					    break;
		   case '3': if(mines[row-1][col+1]==9)
			          { noOfMines++; }
					    break;
			case '4': if(mines[row][col-1]==9)
					    { noOfMines++; }
					    break;
			case '5': if(mines[row][col+1]==9)
					    { noOfMines++; }
					    break;
			case '6': if(mines[row+1][col-1]==9)
			          { noOfMines++; }
					    break;
			case '7': if(mines[row+1][col]==9)
			          { noOfMines++; }
					    break;
			case '8': if(mines[row+1][col+1]==9)
					    { noOfMines++; }
					    break;
					  
			default: break;				
		 }
		 
		 }	   		 
		 
		 return noOfMines;//Returns the clue 
	  }
	
	public void markTile(int row , int col, int status)//Changes the status of the tile.
	{
			tiles[row][col]= status;
	}
	
	public int getTileStatus(int row, int col)//Return the tile status for display purposes
	{
	  return tiles[row][col];
	}
	
	public void displayBoard()
	{
	  System.out.println();//Leave a line before the board.
	  for(int row=0;row<tiles.length;row++)
	  { 
	  	for(int col=0;col<tiles[row].length;col++)
		{
			if(tiles[row][col]==0)//Tile is closed display [ ]
			{
				System.out.print("[ ]");		
			}
			else if(tiles[row][col]==1)//Tile is open so display: a clue or a mine(*)
			{
			   if(mines[row][col]==9)//If tile is a mine.
				{
				  System.out.print("[*]");
				}
				else//Display clue value
				{
				  System.out.print("[" + mines[row][col]+"]");
				}
			}
			else//Tile is flagged by the user.
			{
				System.out.print("[#]");
			}
			
		}
		System.out.println();
	  }
	  System.out.println();//Leave a line after the board.
	}
	
	public boolean gameWon()
	{
		// The user wins if they open all tiles with no mines
		for(int row=0;row<mines.length;row++)
		{
			for(int col=0;col<mines[row].length;col++)
			{
				if(mines[row][col]!=9&&(tiles[row][col]==0))//If one of the cells is not a mine and it is not opened, it returns false
				{
					return false;
				}
			}
		}
		return true;//All non-mine cells have been opened and the player wins.
	}
   
	public boolean openedMine()//Additional method - That will check whether the user has opened a mine
	{
		for(int row=0;row<mines.length;row++)
		{
			for(int col=0;col<mines[row].length;col++)
			{
				if(mines[row][col]==9&&tiles[row][col]==1)//This means that one of the cells containing a mine has been opened the we will return true
				{
				   return true;
				}
			}
		}
		return false;
	}
}
