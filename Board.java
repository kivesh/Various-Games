import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends JPanel {

	 private Square[][] squares = new Square[3][3];
	 private int size; 
	 private int turn =1;//Tracks which players turn it is
	 private int turnsPlayed =0;
	 public Board(int size)
	 { 
		 this.size = size;
		 
		 initialiseSquares();
		 
		 this.addMouseListener(new SquareClicked());
		 //
	 }
	 
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.pink);
		for(int row=0;row<3;row++)
		 {
			 for(int col=0;col<3;col++)
			 {
				 squares[row][col].draw(g); 
			 }
		 }
	}
	
	public class SquareClicked implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent m) {
			int xMouse = m.getX();
			int yMouse = m.getY();

			if(checkWinner()==0)
			{
				for(int row=0;row<3;row++)
				 {
					 for(int col=0;col<3;col++)
					 {
						 if(squares[row][col].isSelected(xMouse, yMouse))
						 {
							 squares[row][col].changeState(turn); 
							 if(turn==1)
							 {
								 turn++;
							 }
							 else
							 {
								 turn--;
							 }
							 turnsPlayed++;
							 repaint();
							
							   if(checkWinner()==1)
								{
									JOptionPane.showMessageDialog(null, "X wins");
								}
								else if(checkWinner()==2)
								{
									JOptionPane.showMessageDialog(null, "0 wins");
								}
							
					     		else if(turnsPlayed==9)
						    	{
								JOptionPane.showMessageDialog(null, "Its a draw!!");
						    	}
							 
						 }
						 
					 }
				 }
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	 
	//Additional Methods!!!!!
	
	public void initialiseSquares()
	 {
		 int  XCoordinate=0;
		 int YCoordinate=0;
		 int SquareSize = size/3;
		 for(int row=0;row<3;row++)
		 {
			  XCoordinate=0;

			 for(int col=0;col<3;col++)
			 {
				 squares[row][col] = new Square(SquareSize, XCoordinate, YCoordinate);
				 XCoordinate+= SquareSize;
			 }
			 YCoordinate+=SquareSize;
		 }
	 }
	 

	 public int checkWinner()//Checks whether a player has won or not
	  {
		  
		  if((squares[0][0].getState()>0)&&(squares[0][0].getState()==squares[0][1].getState())&&(squares[0][0].getState()==squares[0][2].getState()))
		  {
			  return squares[0][0].getState();
		  }
		  else   if((squares[1][0].getState()>0)&&(squares[1][0].getState()==squares[1][1].getState())&&(squares[1][0].getState()==squares[1][2].getState()))
		  {
			  return squares[1][0].getState();
		  }
		  else   if((squares[2][0].getState()>0)&&(squares[2][0].getState()==squares[2][1].getState())&&(squares[2][0].getState()==squares[2][2].getState()))
		  {
			  return squares[2][0].getState();
		  }
		  
		  else   if((squares[0][0].getState()>0)&&(squares[0][0].getState()==squares[1][0].getState())&&(squares[0][0].getState()==squares[2][0].getState()))
		  {
			  return squares[0][0].getState();
		  }
		  else   if((squares[0][1].getState()>0)&&(squares[0][1].getState()==squares[1][1].getState())&&(squares[0][1].getState()==squares[2][1].getState()))
		  {
			  return squares[0][1].getState();
		  }
		  else   if((squares[0][2].getState()>0)&&(squares[0][2].getState()==squares[1][2].getState())&&(squares[0][2].getState()==squares[2][2].getState()))
		  {
			  return squares[0][2].getState();
		  }
		  
		  else   if((squares[0][0].getState()>0)&&(squares[0][0].getState()==squares[1][1].getState())&&(squares[0][0].getState()==squares[2][2].getState()))
		  {
			  return squares[0][0].getState();
		  }
		  else   if((squares[0][2].getState()>0)&&(squares[0][2].getState()==squares[1][1].getState())&&(squares[0][2].getState()==squares[2][0].getState()))
		  {
			  return squares[0][2].getState();
		  }
		  else
		  {
			  return 0;
		  }
	  }
}
