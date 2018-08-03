import javax.swing.JFrame;

public class TicTacToeApp {

	public static void main(String[] args) {
		
		JFrame myGame = new JFrame();
		myGame.setLocationRelativeTo(null);
		myGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myGame.setTitle("TicTacToe");
		myGame.setResizable(false);;
		myGame.setSize(603,639);
		myGame.add(new Board(600));
		myGame.setVisible(true);

	}

}
