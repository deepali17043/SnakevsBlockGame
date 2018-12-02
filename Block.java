package application;

import java.util.Random;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Block { 
	
	private int value;
	private boolean visible;
	private Rectangle shape;
	private Text val;
	
	public Block(boolean set, int x) {
		Random r = new Random();
		value = r.nextInt(x) + 1;
		visible = set;
		if(!visible) 
			value = 0;
		shape = new Rectangle();
		shape.setLayoutX(50);
		shape.setWidth(60);
		shape.setHeight(50);
		shape.setFill(Color.CORNFLOWERBLUE);
		shape.setArcHeight(11);
		shape.setArcWidth(11);
		shape.setLayoutY(100);
	}
	
	public Rectangle getRectangle() {
		return shape;
	}
	
	public void destroy() {
		shape.setVisible(false);
		val.setVisible(false);
		value = 0;
		visible = false;
	}
	
	public StackPane getBlock() {
		StackPane s = new StackPane();
		
		val = new Text();
		val.setText("" + value);
		val.setStyle("-fx-font: 12 arial; -fx-base: #f4cc86; -fx-weight: bold;"
					+ "-fx-text-fill: BLACK;");
		
		s.getChildren().addAll(shape, val);
		s.setVisible(visible);
		return s;
	}
	
	public int getValue() {
		return value;
	}
}
