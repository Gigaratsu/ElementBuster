package view;

import model.Tile;
import javax.swing.JButton;
import java.awt.Color;

public class TileView extends JButton{
	
	private static final long serialVersionUID = 1L;
	private Tile tile;
	private BoardView boardView;
	
	public TileView(){
		super();
		tile = null;
		boardView = null;
	}
	
	public TileView(Tile tile, BoardView boardView){
		super("  ");
		this.tile = tile;
		this.boardView = boardView;
		changeButtonUI();
		if(tile.isSpecial()){
			setText(" X ");
		}
	}
	
	public void changeButtonUI(){
		switch(tile.getElement()){
			case "Earth":
				this.setUI(new CustomizedUI(new Color(139, 69, 19), new Color(210, 105, 30), new Color(165, 42, 42)));
				break;
				
			case "Fire":
				this.setUI(new CustomizedUI(new Color(255, 0, 0), new Color(220, 20, 60), new Color(178, 34, 34)));
				break;
				
			case "Air":
				this.setUI(new CustomizedUI(new Color(211, 211, 211), new Color(220, 220, 220), new Color(105, 105, 105)));
				break;
				
			case "Water":
				this.setUI(new CustomizedUI(new Color(0, 191, 255), new Color(173, 216, 230), new Color(65, 105, 225)));
				break;
				
			default:
				this.setUI(new CustomizedUI(Color.BLACK, Color.BLACK, Color.BLACK));
		}
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}
	
	public BoardView getBoardView(){
		return boardView;
	}
}
