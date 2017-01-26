package view;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import controller.GameController;
import model.Board;
import model.Tile;

public class GameView extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private int width, height;
	
	private BoardView gameBoard;
	private JButton newGameButton;	
	private GameController control;
	private ScoreView score;
	
	public GameView(String title, ActionListener newGameListener, ActionListener boardListener){
		super(title);
		setResizable(true);
		
		width = 400;
		height = 500;
		
		setBounds(100, 50, width, height);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		newGameButton = new JButton("New Game");
		newGameButton.addActionListener(newGameListener);
		
		score = new ScoreView();
		
		gameBoard = new BoardView(new Board("First Board", 3, 3), this, boardListener);
		
		setLayout(new BorderLayout());
		add(score, BorderLayout.NORTH);
		add(gameBoard, BorderLayout.CENTER);
		add(newGameButton, BorderLayout.SOUTH );
		setVisible(true);
	}

	public GameController getControl() {
		return control;
	}

	public void setControl(GameController control) {
		this.control = control;
	}
	
	public void sendToBoard(int x, int y){
		Tile[][] elmntCollection = gameBoard.getBoard().findNextElement(x, y);
		if(elmntCollection != null){
			int scored = 10*(gameBoard.getBoard().bustElement(elmntCollection));
			score.addScore(scored);
			
			gameBoard.updateBoard();
		}
	}

}
