package application;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Magnet extends Tokens {
	//collects all the balls in the same line as magnet.

	public Magnet(boolean set, PlayGame g) {
		super(set, g);
	}
	
	@Override
	public void destroy() {
		if(!getvisibility()) 
			return;
		setvisibility(false);
		//GamePlay.collectcoins();
	}

	@Override
	public StackPane getToken() {
		Circle c = new Circle();
		c.setRadius(10);
		c.setFill(Color.DARKORCHID);
		
		addToPane(c);
		
		return getPane();
	}
	
}
