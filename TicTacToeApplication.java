import java.util.Scanner;
public class TicTacToeApplication
{
   public static void main(String[] args)
   {
      char [][] board = new char[3][3];
      TicTacToe game = new TicTacToe();//Creates a game object of class TicTacToe
      game.Initialise(board);    
		game.Display(board); 
		
	   String playerOrder = "XOXOXOXOX";//Used in the enter method to Determine either X or O
		
		while(game.Winner(board)==false&&TicTacToe.turn<9)//The loop will end when a winner is found or both players have finished all their turns.
		{ 
        game.Enter(board,playerOrder.charAt(TicTacToe.turn));
		  game.Display(board);	
		}
		
		if(game.Winner(board))//If true a winner is found, they are then Displayed using the playerOrder string I had created.
		{
		 System.out.println(playerOrder.charAt(TicTacToe.turn-1)+" Player wins");
		}
		else
		{
		 System.out.println("It a draw!");
		}
   }
} 
