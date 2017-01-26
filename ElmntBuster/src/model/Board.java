package model;
public class Board {

	private Tile[][] gameBoard;
	private String boardName;
	private int boardWidth;
	private int boardHeight;
	
	public Board() {
		gameBoard = null;
		boardName = "null";
		boardWidth = -1;
		boardHeight = -1;
	}
	
	public Board(String boardName, int boardWidth, int boardHeight){
		this.boardName = boardName;
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		gameBoard = new Tile[boardWidth][boardHeight];
	}
	
	public Board(Tile[][] gameBoard, String boardName, int boardWidth, int boardHeight){
		this.gameBoard = gameBoard;
		this.boardName = boardName;
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
	}
	
	public void shuffleBoard(){
		Tile[][] newGameBoard = new Tile[boardWidth][boardHeight];
		int exactMatch = 0;
		if(gameBoard == null){
			gameBoard = new Tile[boardWidth][boardHeight];
			for(int i = 0; i < boardWidth; i++){
				for(int j = 0; j < boardHeight; j++){
					gameBoard[i][j] = null;
				}
			}
		}
		
		for(int i = 0; i < boardWidth; i++){
			for(int j = 0; j < boardHeight; j++){
				newGameBoard[i][j] = new Tile();
				newGameBoard[i][j].setPosX(i);
				newGameBoard[i][j].setPosY(j);
				newGameBoard[i][j].randomizeElement();
				newGameBoard[i][j].randomizeSpecial();
			}
		}
		
		for(int i = 0; i < boardWidth; i++){
			for(int j = 0; j < boardHeight; j++){
				if(gameBoard[i][j]!= null)
					if(newGameBoard[i][j].getElement().equals(gameBoard[i][j].getElement()))
					{
						exactMatch += 1;
					}
			}
		}
		
		if(exactMatch == (boardWidth * boardHeight)){
			shuffleBoard();
		}	else {
			gameBoard = newGameBoard;
		}
	}
	
	public Tile[][] findNextElement(int posX, int posY){
		Tile aTile = new Tile();
		aTile = gameBoard[posX][posY];
		Tile[][] elementCollection = new Tile[boardWidth][boardHeight];
		for(int i = 0; i < boardWidth; i++){
			for(int j = 0; j < boardHeight; j++){
				elementCollection[i][j] = null;
			}
		}
		elementCollection[0][0] = aTile;
		
			if(posX-1 >= 0){
				if(gameBoard[posX-1][posY].getElement().equals(aTile.getElement())){
					elementCollection = findNextElement(posX-1, posY, elementCollection);
				}
			}
			if(posX+1 < boardWidth){
				if(gameBoard[posX+1][posY].getElement().equals(aTile.getElement())){
					elementCollection = findNextElement(posX+1, posY, elementCollection);
				}
			}
			if(posY-1 >= 0){
				if(gameBoard[posX][posY-1].getElement().equals(aTile.getElement())){
					elementCollection = findNextElement(posX, posY-1, elementCollection);
				}
			}
			if(posY+1 < boardHeight){
				if(gameBoard[posX][posY+1].getElement().equals(aTile.getElement())){
					elementCollection = findNextElement(posX, posY+1, elementCollection);
				}
			}
		return elementCollection;
	}
	
	private Tile[][] findNextElement(int posX, int posY, Tile[][] elementCollection){
		Tile aTile = new Tile();
		aTile = gameBoard[posX][posY];
		if(!aTile.getElement().equals(elementCollection[0][0].getElement())){
			return elementCollection;
		}
		for(int i = 0; i < boardWidth; i++){
			for(int j = 0; j < boardHeight; j++){
				if(elementCollection[i][j] != null){
					if(aTile.getPosX() == elementCollection[i][j].getPosX() &&
					aTile.getPosY() == elementCollection[i][j].getPosY()){
						return elementCollection;
					}
				}
			}
		}
		
		search:
		for(int i = 0; i < boardWidth; i++){
			for(int j = 0; j < boardHeight; j++){
				if(elementCollection[i][j] == null){
					elementCollection[i][j] = aTile;
					break search;
				}
			}
		}
		
		if(posX-1 >= 0){
			if(gameBoard[posX-1][posY].getElement().equals(aTile.getElement())){
				elementCollection = findNextElement(posX-1, posY, elementCollection);
			}
		}
		if(posX+1 < boardWidth){
			if(gameBoard[posX+1][posY].getElement().equals(aTile.getElement())){
				elementCollection = findNextElement(posX+1, posY, elementCollection);
			}
		}
		if(posY-1 >= 0){
			if(gameBoard[posX][posY-1].getElement().equals(aTile.getElement())){
			elementCollection = findNextElement(posX, posY-1, elementCollection);
			}
		}
		if(posY+1 < boardHeight){
			if(gameBoard[posX][posY+1].getElement().equals(aTile.getElement())){
			elementCollection = findNextElement(posX, posY+1, elementCollection);
			}
		}
		
		return elementCollection;
	}
	
	public int bustElement(Tile[][] elementCollection){
		int numOfBusted = 0;
		for(int i = 0; i < boardWidth; i++){
			for(int j = 0; j < boardHeight; j++){
				if(elementCollection[i][j] != null){
					gameBoard[elementCollection[i][j].getPosX()][elementCollection[i][j].getPosY()] = null;
					numOfBusted += 1;
				}
			}
		}
		if(numOfBusted > 1){
			collapseColumns();
			fillNull();
		} else {
			numOfBusted = 0;
		}
		return numOfBusted;
	}

	public void collapseColumns() {
		Tile[] saveCol = new Tile[boardHeight];
		for(int i = 0; i < boardHeight; i++){
			saveCol[i] = null;
		}
		
		for(int i = 0; i < boardWidth; i++){
			nextX:
			for(int j = 0; j < boardHeight; j++){
				if(gameBoard[i][j] == null && (j+1) < boardHeight){
					for(int y = (j+1); y < boardHeight; y++){
						saveCol[y] = gameBoard[i][y];
						gameBoard[i][y] = null;
					}
					for(int y = j; y < boardHeight; y++){
						if(saveCol[y] != null){
							gameBoard[i][y] = saveCol[y];
							saveCol[y] = null;
						}
					}
					break nextX;
				}
			}
		}
		
	}
	
	public void fillNull(){
		for(int i = 0; i < boardWidth; i++){
			for(int j = 0; j < boardHeight; j++){
				if(gameBoard[i][j] == null){
					gameBoard[i][j] = new Tile();
					gameBoard[i][j].setPosX(i);
					gameBoard[i][j].setPosY(j);
					gameBoard[i][j].randomizeElement();
					gameBoard[i][j].randomizeSpecial();
				}
			}
		}
	}

	public Tile[][] getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(Tile[][] gameBoard) {
		this.gameBoard = gameBoard;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public int getBoardWidth() {
		return boardWidth;
	}

	public void setBoardWidth(int boardWidth) {
		this.boardWidth = boardWidth;
	}

	public int getBoardHeight() {
		return boardHeight;
	}

	public void setBoardHeight(int boardHeight) {
		this.boardHeight = boardHeight;
	}

}
