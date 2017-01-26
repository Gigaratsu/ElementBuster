package view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Board;

public class BoardView extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JLabel boardTitle;
	private TileView[][] views;
	private int boardWidth;
	private int boardHeight;
	private Board board;
	private GameView gameView;
	
	public BoardView(){
		super(new BorderLayout());
		views = null;
		boardWidth = -1;
		boardHeight = -1;
		board = null;
		boardTitle = new JLabel("No Board");
		add(boardTitle);
	}
	
	public BoardView(Board board, GameView gameView, ActionListener boardListener){
		super();
		this.board = board;
		boardWidth = this.board.getBoardWidth();
		boardHeight = this.board.getBoardHeight();
		views = new TileView[boardWidth][boardHeight];
		boardTitle = new JLabel(this.board.getBoardName());
		
		this.setGameView(gameView);
		
		add(boardTitle, BorderLayout.NORTH);
		this.board.shuffleBoard();
		for(int i = 0; i < boardWidth; i++){
			for(int j = 0; j < boardHeight; j++){
				views[i][j] = new TileView(this.board.getGameBoard()[i][j], this);
				views[i][j].addActionListener(boardListener);
				//System.out.println(views[i][j].getTile().getElement());
				add(views[i][j], BorderLayout.CENTER);
			}
			add(new JLabel(" "), "span, grow");
		}
	}

	public TileView[][] getViews() {
		return views;
	}

	public void setViews(TileView[][] views) {
		this.views = views;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public GameView getGameView() {
		return gameView;
	}

	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}
	
	public void updateBoard(){
		for(int i = 0; i < boardWidth; i++){
			for(int j = 0; j < boardHeight; j++){
				views[i][j].setTile(board.getGameBoard()[i][j]);
				views[i][j].changeButtonUI();
			}
		}
	}

}
