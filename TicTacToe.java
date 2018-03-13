public class TicTacToe
{  
	public static int turn=0;	// Track the number of turns that have been played to access X or O
  
   public void Initialise(char[][] board)// Sets all the elements of the array to a single space for display purposes and equality checking(winner method).
  {
   for(int k=0;k<3;k++)
		 {
			 for(int i=0;i<3;i++)
			 {
			  board[k][i] = ' ';
			 }
		 }
  }
  
   public void Display( char[][] board)//Displays the board on the screen.
   { 
     for(int r=0;r<3;r++)
     {
       for(int c=0;c<3;c++)
       {
         System.out.print("["+board[r][c]+"] ");  
       }     
        System.out.println();
     }
	  System.out.println();
   }
	
	public void Enter( char[][] board, char XorO)//Enter either a X or O using the char parameter at their desired position(only if its a blank space on the board)
	{
	     Scanner kb = new Scanner(System.in);
		  System.out.print("Enter a row (1, 2, or 3) for player "+XorO+": ");
		  int row = kb.nextInt()-1;
		  System.out.print("Enter a column (1, 2, or 3) for player "+XorO+": ");
		  int column = kb.nextInt()-1; 
		  System.out.println();//For display purposes
		  if(row<3&&column<3)//User validation to avoid ArrayIndexOutOfBoundsException
		  {
		   if(board[row][column]!='X'&&board[row][column]!='O')//User validation to enter in a blank space only
			{
				board[row][column]= XorO;
				turn++;
			}
			else
			{
			 System.out.println("ERROR:Please enter in one of the empty spaces on the board!");
			}
		  }
		  else
		  {
		  	System.out.println("ERROR:Please enter in one of the empty spaces on the board!");
		  }	
	}
	
	public boolean Winner(char[][] board)//Checks the board to see whether theres a winner and returns true if found otherwise false
	{
		if((board[0][0]=='X'&&board[1][1]=='X'&&board[2][2]=='X')||(board[0][0]=='O'&&board[1][1]=='O'&&board[2][2]=='O'))// If main diagonal is equal
		{
		  return true;
		}
		else if((board[0][2]=='X'&&board[1][1]=='X'&&board[2][0]=='X')||(board[0][2]=='O'&&board[1][1]=='O'&&board[2][0]=='O')) //If minor diagonal is equal
		{
		 return true;
		}
		else
		{
		  for(int r=0;r<3;r++)// If a row is equal
		  {
		   if((board[r][0]=='X'&&board[r][1]=='X'&&board[r][2]=='X')||(board[r][0]=='O'&&board[r][1]=='O'&&board[r][2]=='O'))
		   {
		    return true;
		   }
		  }
		  
		  for(int c=0;c<3;c++)// If a column is equal
		  {
		  	if((board[0][c]=='X'&&board[1][c]=='X'&&board[2][c]=='X')||(board[0][c]=='O'&&board[1][c]=='O'&&board[2][c]=='O'))
			{
			 return true;
			}
		  }
		}
		
		return false;
	}	
} 
