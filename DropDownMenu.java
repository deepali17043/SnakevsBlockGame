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

public class DropDownMenu extends Application {
	
	private Button restart, resume, exit;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Menu");
		
		restart = new Button("New Game");
		exit = new Button("Home");
		resume = new Button("Resume Game");
		
		restart.setMaxWidth(Double.MAX_VALUE);
		exit.setMaxWidth(Double.MAX_VALUE);
		resume.setMaxWidth(Double.MAX_VALUE);
		
		restart.setStyle("-fx-font: 12 arial; -fx-base: cornflowerblue; -fx-font-weight: Bold");
		exit.setStyle("-fx-font: 12 arial; -fx-base:#d3ffe4; -fx-font-weight: Bold");
		resume.setStyle("-fx-font: 12 arial; -fx-base: cornflowerblue; -fx-font-weight: Bold");
		
		VBox v = new VBox();
		v.setPadding(new Insets(10, 50, 50, 50));
		v.setSpacing(10);
		v.getChildren().addAll(restart, exit, resume);
		v.setBackground(new Background(
				new BackgroundFill(Color.BLACK, new CornerRadii(1), null)));
		
		Scene scene = new Scene(v);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}