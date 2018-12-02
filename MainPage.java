package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MainPage extends Application{
	
	private Button startgame, resumegame, help, leaderboard;
	private Button Exit;
	private playgame1 gameplay;
	private LeaderBoard LeaderBoardPage;
	private Help HelpPage;
	private boolean unfinishedgame;
	
	public MainPage() {
		gameplay = new playgame1();
		LeaderBoardPage = new LeaderBoard();
		HelpPage = new Help();
		//recordedgame = deserialize();
	}
	
	//add methods serialize and deserialize
	
	public static void main(String[] args) {		
		launch(args);
	}
	
	public void setunfinishedgame(boolean k) {
		unfinishedgame = k;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Home");
		
		System.out.println("mainpage");
		startgame = new Button("New Game");
		help = new Button("How to Play");
		leaderboard = new Button("Leader Board");
		Exit = new Button("Close Application");
		
		startgame.setStyle("-fx-font: 12 arial; -fx-base:#4c3ee0; -fx-text-fill: WHITE");
		help.setStyle("-fx-font: 12 arial; -fx-base:#4c3ee0; -fx-text-fill: WHITE");
		leaderboard.setStyle("-fx-font: 12 arial; -fx-base:#4c3ee0; -fx-text-fill: WHITE");
		Exit.setStyle("-fx-font: 12 arial; -fx-base:#4c3ee0; -fx-text-fill: WHITE");
		
		Label l = new Label("Snake Vs. Block\nGame");
		l.setTextAlignment(TextAlignment.CENTER);
		l.setStyle("-fx-font: 30 arial; -fx-text-fill: WHITE;");
		HBox h0 = new HBox(l);
		HBox h1 = new HBox(startgame);
		if(unfinishedgame) {
			resumegame = new Button("Resume");
			h1.getChildren().add(resumegame);
			h1.setSpacing(50);
		}
		HBox h2 = new HBox(leaderboard);
		HBox h3 = new HBox(help);
		HBox h4 = new HBox(Exit);
		
		h0.setAlignment(Pos.CENTER);
		h1.setAlignment(Pos.CENTER);
		h2.setAlignment(Pos.CENTER);
		h3.setAlignment(Pos.CENTER);
		h4.setAlignment(Pos.CENTER);
		
		startgame.setOnAction(e -> {
			try {
				gameplay.start(primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		/*resumegame.setOnAction(e -> {
			recordedgame.start(primaryStage);
		});*/
		
		leaderboard.setOnAction(e -> {
			try {
				LeaderBoardPage.start(primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		help.setOnAction(e -> {
			try {
				HelpPage.start(primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		Exit.setOnAction(e -> {
			Platform.exit();
			System.exit(0);
		});
		
		VBox V = new VBox(h0, h1, h2, h3);
		V.setPadding(new Insets(10, 20, 10, 20));
		V.setSpacing(10);
		V.setAlignment(Pos.CENTER);
		V.setBackground(new Background(
				new BackgroundFill(Color.BLACK, new CornerRadii(0), null)));
		
		Scene scene = new Scene(V, 300, 500);
		scene.setFill(Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});
		primaryStage.show();
	}

}
