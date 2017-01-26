package model;
import java.util.Random;


public class Tile {

	private int posX;
	private int posY;
	private String element;
	private boolean isSpecial;
	
	public Tile() {
		posX = -1;
		posY = -1;
		element = null;
		isSpecial = false;
	}
	
	public Tile(int posX, int posY, String element, boolean isSpecial){
		this.posX = posX;
		this.posY = posY;
		this.element = element;
		this.isSpecial = isSpecial;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public String getElement() {
		return element;
	}

	public void setelement(String element) {
		this.element = element;
	}
	
	public void randomizeElement(){
		Random rand = new Random();
		int randomNumber;
		randomNumber = (rand.nextInt(99)+1);
		if(randomNumber <= 25){
			element = "Fire";
		}else if(randomNumber > 25 && randomNumber <=50){
			element = "Water";
		}else if(randomNumber > 50 && randomNumber <= 75){
			element = "Earth";
		}else if(randomNumber > 75 && randomNumber <= 100){
			element = "Air";
		}
	}
	
	public void randomizeSpecial(){
		int randomNumber;
		Random rand = new Random();
		randomNumber = rand.nextInt(99)+1;
		if(randomNumber <= 30){
			isSpecial = true;
		}
	}

	public boolean isSpecial() {
		return isSpecial;
	}

	public void setSpecial(boolean isSpecial) {
		this.isSpecial = isSpecial;
	}

}
