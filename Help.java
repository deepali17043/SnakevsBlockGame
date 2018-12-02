package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Help extends Application {
	
	private Button Home, Exit;
	private MainPage HomePage;
	
	public Help(MainPage m) {
		HomePage = m;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Help");
		
		TextArea t = new TextArea();
		t.setText("Instructions to play the game\n"
				+ "The snake is a collection of n balls arranged linearly. (n >= 0)\n" + 
				"2) Snake can move in 3 directions: forward, left, and right.\n" + 
				"3) The snake always moves forward until it encounters a block.\n" + 
				"4) Blocks and Walls appear randomly during the gameplay.\n" + 
				"5) Each block has a value, which represents the points made by the player when the snake\n" + 
				"eats it. After eating the block the length of the snake decreases by the value of the block\n" + 
				"6) Blocks may occur independently, or in a chain. Atleast one of the block in a chain should\n" + 
				"have a value less than the length of the snake.\n" + 
				"7) Walls may occur in different lengths, but should not divide the screen in two halves at\n" + 
				"any time. That is, length of the wall should be strictly less than the length of screen.\n" + 
				"8) Snake should not be able to move across the wall.\n" + 
				"9) A snake may encounter 4 types of tokens during the gameplay.\n" + 
				"a) Ball: A ball has a value written over it. The length of the snake increases by the\n" + 
				"value of the ball if it eats the ball.\n" + 
				"b) Destroy Blocks: Destroy all the blocks present on the screen. Value of each block\n" + 
				"destroyed is added to the score of the player.\n" + 
				"c) Shield: A shield lets the player eat any block without decreasing the snake's\n" + 
				"length. Again, the value of the block eaten by snake is added to the total score of\n" + 
				"the player. The shield remains with snake for 5 seconds only.\n" + 
				"d) Magnet: A magnet allows the snake to collect coins which are within a certain\n" + 
				"distance from the head of the snake. The distance should be chosen by the\n" + 
				"programmer but should not equal the width of the screen.\n"
				+ "The tokens are in the following avatars:\n"
				+ "Ball - Yellow\n"
				+ "Magnet - Purple\n"
				+ "Shield - Blue\n"
				+ "DestroyBlocks - Red\n"
				+ "To pause the game, press the Enter key.\n"
				+ "Use arrow keys to control the movement of the snake.");
		t.setEditable(false);
		//addtext
		
		Home = new Button("Home");
		Exit = new Button("Close Application");
		Home.setStyle("-fx-font: 12 arial; -fx-base:#d3ffe4; -fx-font-weight: Bold");
		Exit.setStyle("-fx-font: 12 arial; -fx-base:#d3ffe4; -fx-font-weight: Bold");
		BackgroundFill B = new BackgroundFill(Color.BLACK, new CornerRadii(0), null);
		
		Home.setOnAction(e -> {
			try {
				HomePage.start(primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		Exit.setOnAction(e -> {
			Platform.exit();
			System.exit(0);
		});
		
		HBox H = new HBox(Home, Exit);
		H.setPadding(new Insets(10, 0, 10, 0));
		H.setSpacing(130);
		VBox v = new VBox(t);
		v.getChildren().add(H);
		t.setBackground(new Background(B));
		t.setFont(Font.font("Verdana", 15));
		t.setStyle("-fx-control-inner-background:BLACK;"
				+  "-fx-text-fill: WHITE; ");
		v.setBackground(new Background(B));
		
		Scene scene = new Scene(v, 300, 250);
		scene.setFill(Color.BLUEVIOLET);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
