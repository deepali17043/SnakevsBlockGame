package application;

import java.util.ArrayList;

import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Snake {
	
	private int length, pos_x, pos_y;
	private ArrayList<Circle> c;
	
	public Snake() {
		length = 0;
		//speed = 1; //(5 units/sec, forward)
		c = new ArrayList<Circle>();
		pos_x = 150; pos_y = 275;
		for(int i = 0; i < 4; i++, pos_y = pos_y + 10, length++) {
			c.add( new Circle(pos_x, pos_y, 5) );
			c.get(i).setFill(Color.AQUAMARINE);
		}
	}
	
	public void inclength(int x) {
		for(int i = 0; i < x; i++, pos_y = pos_y + 10, length++) {
			c.add(new Circle(pos_x, pos_y, 5));
			c.get(i).setFill(Color.AQUAMARINE);
			System.out.println(c.get(i).getFill());
		}
		//incspeed();
	}
	
	public void declength(int x) {
		for(int i = c.size() - 1; i >= length - x; i--, pos_y -= 10) {
			c.get(i).setVisible(false);
			c.remove(i);
		}
		length = length - x;
	}
	
	public ArrayList<TranslateTransition> moveleft() {
		ArrayList<TranslateTransition> t = new ArrayList<TranslateTransition>();
		for(int i = 0; i < length; i++) {
			t.add(new TranslateTransition());
			t.get(i).setNode(c.get(i));
			t.get(i).setAutoReverse(false);
			t.get(i).setByX(-5);
			t.get(i).setDuration(Duration.millis(20));
			t.get(i).setCycleCount(1);
		}
		pos_x -= 5;
		return t;
	}
	
	public ArrayList<TranslateTransition> moveright() {
		ArrayList<TranslateTransition> t = new ArrayList<TranslateTransition>();
		for(int i = 0; i < length; i++) {
			t.add(new TranslateTransition());
			t.get(i).setNode(c.get(i));
			t.get(i).setAutoReverse(false);
			t.get(i).setByX(5);
			t.get(i).setDuration(Duration.millis(20));
			t.get(i).setCycleCount(1);
		}
		pos_x += 5;
		return t;
	}
	
	public int getPositionX() {
		return pos_x;
	}
	
	public ArrayList<Circle> getsnake() {
		return c;
	}
	
//	public void incspeed() {
//		speed = 1 + length / 10 ;
//	}
	
	public int getlength() {
		return length;
	}
	
	public Bounds getBoundsInParent() {
		return c.get(0).getBoundsInParent();
	}
	
//	public int getspeed() {
//		return speed;
//	}

}
