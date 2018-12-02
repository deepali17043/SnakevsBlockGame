package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DropDownMenu {
	
	private Button restart, resume, home;
	private PlayGame gameplay;
	private MainPage HomePage;
	
	public DropDownMenu(PlayGame g, MainPage m) {
		gameplay = g;
		HomePage = m;
	}

	public Scene getMenu(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		restart = new Button("New Game");
		home = new Button("Home");
		resume = new Button("Resume Game");
		
		restart.setPrefSize(Double.MAX_VALUE, 30);
		home.setPrefSize(Double.MAX_VALUE, 30);
		resume.setPrefSize(Double.MAX_VALUE, 30);
		
		restart.setStyle("-fx-font: 12 arial; -fx-base: cornflowerblue; -fx-font-weight: Bold");
		home.setStyle("-fx-font: 12 arial; -fx-base:#d3ffe4; -fx-font-weight: Bold");
		resume.setStyle("-fx-font: 12 arial; -fx-base: cornflowerblue; -fx-font-weight: Bold");
		
		restart.setOnAction(e -> {
			gameplay = new PlayGame(HomePage);
			try {
				gameplay.start(primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		home.setOnAction(e -> {
			//serialize
			try {
				HomePage.start(primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		resume.setOnAction(e -> {
			gameplay.getHideMenu().fire();
		});
		
		VBox v = new VBox();
		v.setPadding(new Insets(40, 50, 50, 50));
		v.setSpacing(60);
		v.getChildren().addAll(restart, home, resume);
		v.setBackground(new Background(
				new BackgroundFill(Color.BLACK, new CornerRadii(1), null)));
		
		Scene scene = new Scene(v, 300, 500);
		return scene;
	}
	
}
