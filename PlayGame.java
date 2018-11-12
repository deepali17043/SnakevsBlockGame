package application;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PlayGame extends Application {
	
	private Button showMenu, Exit;
	private Snake s;
	private ArrayList<? extends Tokens> tokens;
	private DropDownMenu menu;
	private ArrayList<Block> blocks;
	private ArrayList<Wall> walls;
	private int score;
	
	public PlayGame() {
		tokens = new ArrayList();
		blocks = new ArrayList<Block>();
		walls = new ArrayList<Wall>();
		s = new Snake();
		menu = new DropDownMenu();
	}
	
	public int getscore() {
		return score;
	}
	
	private Button[] genblocks() {
		Random r = new Random();
		Button[] b = new Button[5];
		for(int j = 0; j < 5; j++) {
			b[j] = new Button();
			b[j].setDisable(true);
			b[j].setPrefSize(50, 50);
			int x = r.nextInt(2);
			if(x == 0) {
				b[j].setVisible(false);
				continue;
			}
			Block y = new Block(50);
			b[j].setText("" + y.getvalue());
			b[j].setStyle("-fx-font: 12 arial; -fx-base: #f4cc86; -fx-weight: BOLd;"
					+ "-fx-text-fill: BLACK;");
		}
		return b;
	}
	
	private void declare(HBox H, TranslateTransition t) {
		H.getChildren().addAll(genblocks());
		H.setLayoutY(-50);
		t.setNode(H);
		t.setAutoReverse(false);
		t.setByY(600);
		t.setCycleCount(Animation.INDEFINITE);
		t.setDuration(Duration.millis(5000));
	}
	
	private void translatingblocks(HBox[] H, TranslateTransition[] t) {
		for(int i = 0; i < 6; i++) {
			H[i] = new HBox();
			t[i] = new TranslateTransition();
			declare(H[i], t[i]);
		}
	}
	
	private void fn(TranslateTransition t, HBox h) {
		declare(h, t);
		t.play();
	}
	
	private static TranslateTransition[] tb;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Snake vs. Block Game");
		
		AnchorPane A = new AnchorPane();
		HBox[] H = new HBox[6];
		tb = new TranslateTransition[6];
		translatingblocks(H, tb);
		Timeline timeline = new Timeline(
			new KeyFrame(Duration.millis(0), e -> { 
				A.getChildren().removeAll(H[0]);
				translatingblocks(H, tb);
				A.getChildren().addAll(H);
				fn(tb[0], H[0]);}),
			new KeyFrame(Duration.millis(1000), e -> {
				A.getChildren().remove(H[1]); fn(tb[1], H[1]);}),
			new KeyFrame(Duration.millis(2000), e -> {
				A.getChildren().remove(H[2]); fn(tb[2], H[2]);}),
			new KeyFrame(Duration.millis(3000), e -> {
				A.getChildren().remove(H[3]); fn(tb[3], H[3]);}),
			new KeyFrame(Duration.millis(4000), e -> {
				A.getChildren().remove(H[4]); fn(tb[4], H[4]);}),
			new KeyFrame(Duration.millis(5000), e -> {
				A.getChildren().remove(H[5]); fn(tb[5], H[5]);})
			
		);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
		HBox h1 = new HBox();
		showMenu = new Button("Options");
		showMenu.setAlignment(Pos.BASELINE_RIGHT);
		showMenu.setStyle("-fx-font: 12 arial; -fx-base:#4c3ee0; -fx-text-fill: WHITE;");
		Label sc = new Label();
		sc.setText("Score: " + score);
		sc.setStyle("-fx-font: 12 calibri; -fx-text-fill: #9d96e8;");
		sc.setAlignment(Pos.BASELINE_CENTER);
		Exit = new Button("Close Application");
		Exit.setAlignment(Pos.BASELINE_LEFT);
		Exit.setStyle("-fx-font: 12 arial; -fx-base:#4c3ee0; -fx-text-fill: WHITE;");
		h1.getChildren().add(Exit);
		h1.getChildren().add(sc);
		h1.getChildren().add(showMenu);
		h1.setAlignment(Pos.BASELINE_RIGHT);
		h1.setPadding(new Insets(470, 0, 0, 0));
		h1.setSpacing(10);
		
		
		A.setBackground(new Background(
				new BackgroundFill(Color.BLACK, new CornerRadii(0), null)));
		A.getChildren().addAll(s.getsnake());
		A.getChildren().add(h1);
		
		A.setOnKeyPressed((KeyEvent e) -> {
			ArrayList<TranslateTransition> tc;
			if(e.getCode().equals(KeyCode.LEFT))
				tc = s.moveleft();
			else if(e.getCode().equals(KeyCode.RIGHT)) 
				tc = s.moveright();
			else 
				tc = new ArrayList<TranslateTransition>();
			for(int i = 0; i < tc.size(); i++)
				tc.get(i).play();
		});
		
		//t.play();
		Scene scene = new Scene(A, 250, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
