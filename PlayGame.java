
package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
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
	private Timeline timeline;
	private Label sc; // scores
	
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
	
	private HBox genblocks() {
		HBox h = new HBox();
		
		Random r = new Random();
		StackPane[] s = new StackPane[5];
		for(int j = 0; j < 5; j++) {
			int x = r.nextInt(2);
			Block b = new Block(j*60, (x==1));
			blocks.add(b);
			s[j] = b.getBlock();
		}
		h.getChildren().addAll(s);
		return h;
	}
	
	private HBox gentokens() {
		HBox h = new HBox();
		Circle[] c = new Circle[4];
		
		c[0] = new Circle();
		c[0].setFill(Color.RED); //shield
		c[0].setRadius(10);
		
		c[1] = new Circle();
		c[1].setFill(Color.BISQUE); //magnet
		c[1].setRadius(10);
		
		c[2] = new Circle();
		c[2].setFill(Color.YELLOW); //ball
		c[2].setRadius(10);
		
		c[3] = new Circle();
		c[3].setFill(Color.BLUE); //Destroy Blocks
		c[3].setRadius(10);
		
		h.getChildren().addAll(c);
		h.setSpacing(40);
		return h;
	}
	
	private Group genwalls() {
		Group root = new Group();
		for(int i = 0; i < 4; i++) {
			//generate walls at all times
		}
		return root;
	}
	
	private void move(HBox H) {
		TranslateTransition t = new TranslateTransition();
		H.setLayoutY(-50);
		t.setNode(H);
		t.setAutoReverse(false);
		t.setByY(510);
		t.setCycleCount(Animation.INDEFINITE);
		t.setDuration(Duration.millis(6000));
		t.play();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Snake vs. Block Game");
		
		AnchorPane A = new AnchorPane();
		HBox[] H = new HBox[5];
		for(int i = 0; i < 5; i++) 
			H[i] = new HBox();
		HBox[] H1 = new HBox[5];
		for(int i = 0; i < 5; i++) 
			H1[i] = new HBox();
		timeline = new Timeline(
				new KeyFrame(Duration.millis(10), e-> {
					H1[0] = gentokens();
					H1[1] = genblocks();
					H1[2] = gentokens();
					H1[3] = genblocks();
					H1[4] = gentokens();
				}),
				new KeyFrame(Duration.millis(1000), e -> {
					A.getChildren().remove(H[0]);
					H[0] = H1[0];
					A.getChildren().add(H[0]);
					move(H[0]);
				}),
				new KeyFrame(Duration.millis(2000), e -> {
					A.getChildren().remove(H[1]);
					H[1] = H1[1];
					A.getChildren().add(H[1]);
					move(H[1]);
				}),
				new KeyFrame(Duration.millis(3000), e -> {
					A.getChildren().remove(H[2]);
					H[2] = H1[2];
					A.getChildren().add(H[2]);
					move(H[2]);
				}),
				new KeyFrame(Duration.millis(4000), e -> {
					A.getChildren().remove(H[3]);
					H[3] = H1[3];
					A.getChildren().add(H[3]);
					move(H[3]);
				}),
				new KeyFrame(Duration.millis(5000), e -> {
					A.getChildren().remove(H[4]);
					H[4] = H1[4];
					A.getChildren().add(H[4]);
					move(H[4]);
				})
		);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
		HBox h1 = new HBox();
		showMenu = new Button("Options");
		showMenu.setAlignment(Pos.BASELINE_RIGHT);
		showMenu.setStyle("-fx-font: 12 arial; -fx-base:#4c3ee0; -fx-text-fill: WHITE;");
		sc = new Label();
		sc.setText("Score: " + score);
		sc.setStyle("-fx-font: 12 calibri; -fx-text-fill: #9d96e8;");
		sc.setAlignment(Pos.BASELINE_CENTER);
		Exit = new Button("Close Application");
		Exit.setAlignment(Pos.BASELINE_LEFT);
		Exit.setStyle("-fx-font: 12 arial; -fx-base:#4c3ee0; -fx-text-fill: WHITE;");
		h1.getChildren().add(Exit);
		h1.getChildren().add(sc);
		h1.getChildren().add(showMenu);
		h1.setPadding(new Insets(470, 0, 0, 0));
		h1.setSpacing(40);
		
		
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
		
		Scene scene = new Scene(A, 300, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
