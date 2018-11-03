package application;

import java.util.ArrayList;

import javafx.animation.TranslateTransition;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Snake {
	
	private int length, speed, pos_x, pos_y;
	private ArrayList<Circle> c;
	
	public Snake() {
		length = 0;
		speed = 1; //(5 units/sec, forward)
		c = new ArrayList<Circle>();
		pos_x = 125; pos_y = 250;
		for(int i = 0; i < 4; i++, pos_y = pos_y + 14, length++) {
			c.add( new Circle(pos_x, pos_y, 7) );
			c.get(i).setFill(Color.BLUEVIOLET);
		}
	}
	
	public void inclength(int x) {
		for(int i = 0; i < x; i++, pos_y = pos_y + 14, length++) {
			c.add(new Circle(pos_x, pos_y, 7));
			c.get(i).setFill(Color.BLUEVIOLET);
		}
		incspeed();
	}
	
	public ArrayList<TranslateTransition> moveleft() {
		ArrayList<TranslateTransition> t = new ArrayList<TranslateTransition>();
		for(int i = 0; i < length; i++) {
			t.add(new TranslateTransition());
			t.get(i).setNode(c.get(i));
			t.get(i).setAutoReverse(false);
			t.get(i).setByX(-5);
			t.get(i).setDuration(Duration.millis(10));
			t.get(i).setCycleCount(1);
		}
		return t;
	}
	
	public ArrayList<TranslateTransition> moveright() {
		ArrayList<TranslateTransition> t = new ArrayList<TranslateTransition>();
		for(int i = 0; i < length; i++) {
			t.add(new TranslateTransition());
			t.get(i).setNode(c.get(i));
			t.get(i).setAutoReverse(false);
			t.get(i).setByX(5);
			t.get(i).setDuration(Duration.millis(10));
			t.get(i).setCycleCount(1);
		}
		return t;
	}
	
	public ArrayList<Circle> getsnake() {
		return c;
	}
	
	public void incspeed() {
		speed = 1 + length / 10 ;
	}
	
	public int getlength() {
		return length;
	}
	
	public int getspeed() {
		return speed;
	}

}