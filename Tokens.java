package application;

public abstract class Tokens {
	
	private int x, y; // x and y coordinates
	
	public int getx() {
		return x;
	}
	
	public int gety() {
		return y;
	}
	
	public abstract int getval();
	
	public abstract void destroy();

}