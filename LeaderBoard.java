package application;

import java.util.Date;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LeaderBoard extends Application{
	
	private int[] HighScores;
	private Date[] dates;
	private int n;
	private Button Home, Exit;
	
	public LeaderBoard() {
		HighScores = new int[10];
		dates = new Date[10];
		n = 0;
	}
	
	public void addnewscore(int x, Date y) {
		for(int i = n-1; i >= 0; i--) {
			if(i == 0 && HighScores[i] < x) {
				for(int j = i+1; j < 10; j++) {
					HighScores[j] = HighScores[j - 1];
					dates[j] = dates[j-1];
				}
				HighScores[i] = x;
				dates[i] = y;
				return;
			}
			if(i != 0 && HighScores[i] < x && x < HighScores[i-1]) {
				for(int j = i+1; j < 10; j++) {
					HighScores[j] = HighScores[j - 1];
					dates[j] = dates[j - 1];
				}
				HighScores[i] = x;
				dates[i] = y;
				return;
			}
		}
		if(n < 10) {
			HighScores[n] = x;
			dates[n++] = y;
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		// for testing
		long something = 100;
		for(int i = 0; i < 10; i++) 
			addnewscore(10-i, new Date(something*i));
		
		for(int i = 0; i < n ; i++)
			System.out.println(HighScores[i]);
		
		primaryStage.setTitle("Leader Board");
		VBox list = new VBox();
		list.setSpacing(5);
		
		Label l = new Label();
		l.setText("HIGH SCORES");
		l.setStyle("-fx-font: 20 Calibri; -fx-text-fill: ALICEBLUE;");
		l.setTextAlignment(TextAlignment.CENTER);
		list.getChildren().add(l);
		
		String[] c = new String[2];
		c[0] = "#dcf4f1";
		c[1] = "#c8f7e8";
				
		for(int i = 0; i < n; i++) {
			HBox h = new HBox();
			h.setAlignment(Pos.CENTER);
			
			TextField t1 = new TextField();
			t1.setEditable(false);
			t1.setText("" + HighScores[i]);
			t1.setAlignment(Pos.CENTER);
			t1.setStyle("-fx-background-color: " + c[(i+1)%2] + ";");
			h.getChildren().add(t1);
			
			TextField t2 = new TextField();
			t2.setEditable(false);
			t2.setText(dates[i].toString());
			t2.setAlignment(Pos.CENTER);
			t2.setStyle("-fx-background-color:" + c[i%2] + ";");
			h.getChildren().add(t2);
			
			list.getChildren().add(h);
		}
		
		Home = new Button("Home");
		Exit = new Button("Close Application");
		
		Home.setStyle("-fx-font: 12 calibri; -fx-base: #97ede7;");
		Exit.setStyle("-fx-font: 12 calibri; -fx-base: #97ede7;");

		HBox H = new HBox(Home, Exit);
		H.setSpacing(175);
		
		VBox V = new VBox(list, H);
		V.setSpacing(20);
		V.setPadding(new Insets(10, 0, 20, 0));
		V.setAlignment(Pos.CENTER);
		V.setBackground(new Background(
				new BackgroundFill(Color.BLACK, new CornerRadii(0), null)));
		
		Scene scene = new Scene(V);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main (String[] args) {
		
		launch(args);
	}
}
