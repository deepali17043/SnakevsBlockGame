package application;

import javafx.application.Application;
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

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Help");
		
		TextArea t = new TextArea();
		t.setText("Instructions to play the game");
		t.setEditable(false);
		//addtext
		
		Home = new Button("Home");
		Exit = new Button("Close Application");
		Home.setStyle("-fx-font: 12 arial; -fx-base:#d3ffe4; -fx-font-weight: Bold");
		Exit.setStyle("-fx-font: 12 arial; -fx-base:#d3ffe4; -fx-font-weight: Bold");
		BackgroundFill B = new BackgroundFill(Color.BLACK, new CornerRadii(0), null);
		
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
