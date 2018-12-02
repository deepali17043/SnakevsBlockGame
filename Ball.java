package application;

import java.util.Random;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Ball extends Tokens {
	
	private int value;
	
	public Ball(boolean set, PlayGame g) {
		super(set, g);
		Random r = new Random();
		value = r.nextInt(10) + 1;
	}
	
	public int getvalue() {
		return value;
	}

	@Override
	public void destroy() {
		if(!getvisibility())
			return;
		setvisibility(false);
		//GamePlay.incsnakelength(value);
	}

	@Override
	public StackPane getToken() {
		Circle c = new Circle();
		c.setRadius(10);
		c.setFill(Color.YELLOW);
		
		Text t = new Text();
		t.setText("" + value);
		
		addToPane(c, t);
		
		return getPane();
	}

}
