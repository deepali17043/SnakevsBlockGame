package application;

import java.util.Random;

public class Block {
	
	private int value, pos_x, pos_y;
	private String colour;
	
	public Block(int max) {
		Random r = new Random();
		value = r.nextInt(max);
		pos_x = r.nextInt(5);
		pos_y = - 50 - r.nextInt(50);
	}
	
	public int getvalue() {
		return value;
	}
	
	public int getX() {
		return pos_x;
	}
	
	public int getY() {
		return pos_y;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

}