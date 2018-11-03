package application;

import java.io.Serializable;

import javafx.application.Application;
import javafx.stage.Stage;

public class Player extends Application implements Serializable {
	
	private MainPage Home;
	
	public Player() {
		Home = new MainPage();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Home.start(primaryStage);
	}

}