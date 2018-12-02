package application;

public class DestroyBlocks extends Tokens {
	
	private int radius;
	
	@Override
	public int getval() {
		return radius;
	}package application;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DestroyBlocks extends Tokens {
	
	public DestroyBlocks(boolean set, PlayGame g) {
		super(set,g);
	}

	@Override
	public StackPane getToken() {
		
		Circle c = new Circle();
		c.setRadius(10);
		c.setFill(Color.RED);
		
		addToPane(c);
		
		return getPane();
	}

	@Override
	public void destroy() {
		if(!getvisibility())
			return;
		setvisibility(false);
		//GamePlay.resetblocks();
	}

}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
