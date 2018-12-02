package application;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public abstract class Tokens {
	
	private boolean visible;
	private StackPane s;
	protected PlayGame GamePlay;
	
	public Tokens(boolean set, PlayGame g) {
		visible = set;
		s = new StackPane();
		GamePlay = g;
	}
	
	protected void addToPane(Circle c) {
		s.getChildren().add(c);
		s.setVisible(visible);
	}
	
	protected void addToPane(Circle c, Text t) {
		s.getChildren().addAll(c, t);
		s.setVisible(visible);
	}
	
	protected StackPane getPane() {
		return s;
	}
	
	protected boolean getvisibility() {
		return visible;
	}
	
	protected void setvisibility(boolean t) {
		visible = t; 
		s.setVisible(t);
	}
	
	public abstract StackPane getToken();
	
	public abstract void destroy();

}
