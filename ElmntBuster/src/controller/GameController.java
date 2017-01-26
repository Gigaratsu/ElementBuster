package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GameView;
import view.TileView;

public class GameController {
	
	private GameView gameView;

	public GameController() {
		java.awt.EventQueue.invokeLater(new Runnable(){

			public void run() {
				setGameView(new GameView("Element Buster", 
						new NewGameListener(), new BoardListener()));
			}
		});
	}

	public GameView getGameView() {
		return gameView;
	}

	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}

}

class NewGameListener implements ActionListener {
	public void actionPerformed(ActionEvent event) {
		System.out.println("Starting new game...");
	}
	
}

class BoardListener implements ActionListener {
	public void actionPerformed(ActionEvent event) {
		System.out.println("Board has been touched...");
		
		TileView eventTile = (TileView)event.getSource();
		GameView eventGameView = eventTile.getBoardView().getGameView();
		System.out.println("Tile: "+ (eventTile.getTile().getPosX()+1)+ "," + (eventTile.getTile().getPosY()+1)
				+ " was touched.");
		
		System.out.println("Sending data to Board");
		eventGameView.sendToBoard(eventTile.getTile().getPosX(), eventTile.getTile().getPosY());
		
	}
}
