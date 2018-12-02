package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall {
	
	private int length, breadth;
	private boolean visible;
	
	public Wall(boolean v) {
		visible = v;
		length = 115;
		breadth = 4;
	}
	
	public Rectangle getWall() {
		Rectangle r = new Rectangle();
		
		r.setHeight(length);
		r.setWidth(breadth);
		r.setVisible(visible);
		r.setFill(Color.WHITE);
		
		return r;
	}

}
