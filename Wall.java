package application;

import java.util.Random;

public class Wall {
	
	private int length, pos_x, pos_y;
	
	Wall(int l, int x, int y) {
		assignlength(l);
		pos_x = x;
		pos_y = y;
	}
	
	public void assignlength(int l) {
		Random r = new Random();
		length = r.nextInt(l);
	}
	
	public int getlocationX() {
		return pos_x;
	}
	
	public int getlocationY() {
		return pos_y;
	}
	
	public int getlength() {
		return length;
	}

}