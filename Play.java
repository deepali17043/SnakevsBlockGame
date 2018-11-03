package application;
//delete
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.event.*;

public class Play {
	
	private AnchorPane A;
	private Rectangle R;
	private int x;
	
	public Play() {
		R = new Rectangle();
		x = 1;
		R.setX(0);
		R.setY(0);
		R.setWidth(50);
		R.setHeight(40);
		R.setFill(Color.BEIGE);
		A = new AnchorPane();
		A.getChildren().add(R);
		setupTimeline();
	}
	
	private void setRect(int x) {
		x = x*50;
		R.setX(x);
		R.setY(0);
	}
	
	public void updatex(int a) {
		x = a/10;
	}
	
	public AnchorPane getA() {
		return A;
	}
	
	public void setupTimeline() {
		KeyFrame k = new KeyFrame( Duration.millis(1000), new Handler());
		Timeline t = new Timeline(k);
		t.setCycleCount(Animation.INDEFINITE);
		t.play();
	}
	
	private class Handler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			Random r = new Random();
			if(R.getY() >= 475) 
				setRect(r.nextInt(5));
			else 
				R.setTranslateY(x);
		}
		
	}

}