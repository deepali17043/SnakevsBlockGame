package application;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Shield extends Tokens {
	
	public static int time = 5;
	//shield remains with the snake for five seconds

	public Shield(boolean set, PlayGame g) {
		super(set, g);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		if(!getvisibility())
			return;
		setvisibility(false);
		//GamePlay.sheildsnake();
	}

	@Override
	public StackPane getToken() {
		Circle c = new Circle();
		c.setRadius(10);
		c.setFill(Color.DARKTURQUOISE);
		
		addToPane(c);
		
		return getPane();
	}

}
