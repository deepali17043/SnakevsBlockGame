package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

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
	private PlayGame gameplay;
	private LeaderBoard LeaderBoardPage;
	private Help HelpPage;
	private boolean unfinishedgame;
	
	static class PlayGameSerialise implements Serializable {
		private int score;
		private int snakelength;
		
		PlayGameSerialise(PlayGame g) {
			score = g.getscore();
			snakelength = g.getSnakeLength();
		}
		
		int getscore() {
			return score;
		}
		
		int getSnakeLength() {
			return snakelength;
		}
	}
	
	public MainPage() {
		gameplay = new PlayGame(this);
		LeaderBoardPage = new LeaderBoard(this);
		HelpPage = new Help(this);
		//recordedgame = deserialize();
	}
	
	//add methods serialize and deserialize
	
	public static void main(String[] args) {		
		launch(args);
	}
	
	public void serialize() throws IOException {
		PlayGameSerialise P = new PlayGameSerialise(gameplay);
		
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream( new FileOutputStream("prevgame.txt"));
			out.writeObject(P);
		}
		finally {
			out.close();
		}
	}
	
	public PlayGame deserialize() throws IOException, ClassNotFoundException {
			PlayGameSerialise P;
			
		    ObjectInputStream in = null;
		    try {
		    		in = new ObjectInputStream( new FileInputStream("prevgame.txt") );
		    		P = (PlayGameSerialise)in.readObject();
		    } finally {
		    		in.close();
		    }
		    PlayGame g = new PlayGame(P.getSnakeLength(), P.getscore(), this);
		    return g;
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
