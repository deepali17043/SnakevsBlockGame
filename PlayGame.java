package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
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
	private Snake snake;
	private ArrayList<Tokens> tokens;
	private DropDownMenu menu;
	private ArrayList<Block> blocks;
	private ArrayList<Wall> walls;
	private int score;
	private Timeline timeline;
	private Label sc; // scores
	private MainPage HomePage;
	
	public PlayGame() {
		tokens = new ArrayList<Tokens>();
		blocks = new ArrayList<Block>();
		walls = new ArrayList<Wall>();
		HomePage = new MainPage();
		menu = new DropDownMenu(this, HomePage);
		score = 0;
		snake = new Snake();
	}
	
	public PlayGame(MainPage m) {
		tokens = new ArrayList<Tokens>();
		blocks = new ArrayList<Block>();
		walls = new ArrayList<Wall>();
		HomePage = m;
		menu = new DropDownMenu(this, m);
		score = 0;
		snake = new Snake();
	}
	
	public PlayGame(int snakelength, int scores, MainPage m) {
		tokens = new ArrayList<Tokens>();
		blocks = new ArrayList<Block>();
		walls = new ArrayList<Wall>();
		HomePage = m;
		menu = new DropDownMenu(this, m);
		score = scores;
		snake = new Snake();
		snake.inclength(snakelength-4);
	}
	
	public int getscore() {
		return score;
	}
	
	public Snake getSnake() {
		return snake;
	}
	
	private HBox genblocks() {
		HBox h = new HBox();
		
		Random r = new Random();
		StackPane[] s = new StackPane[5];
		int y = r.nextInt(5) + 1;
		for(int j = 0; j < 5; j++) {
			int x = r.nextInt(2);
			Block b = new Block((x==1),
					((j+1)%y == 0) ? s.length : 50);
			blocks.add(b);
			s[j] = b.getBlock();
		}
		h.getChildren().addAll(s);
		return h;
	}
	
	private HBox gentokens() {
		HBox h = new HBox();
		HBox wall = new HBox();
		
		for(int i = 0; i < 5; i++) {
			Random r = new Random();
			Tokens t;
			int n = r.nextInt(23);
			int x = r.nextInt(4);
			if(n < 1) 
				t = new Magnet(x!=0, this);
			else if(n < 21)
				t = new Ball(x!=0, this);
			else if(n < 22)
				t = new Shield(x!=0, this);
			else 
				t = new DestroyBlocks(x!=0, this);
			tokens.add(t);
			h.getChildren().add(t.getToken());
			
			if(i < 4) {
				int y = r.nextInt(5);
				Wall w = new Wall(y==0);
				walls.add(w);
				wall.getChildren().add(w.getWall());
			}
		}
		
		h.setSpacing(40);
		h.setPadding(new Insets(0, 20, 0, 20));
		
		wall.setSpacing(56);
		wall.setPadding(new Insets(0, 58, 0, 58));
		
		StackPane x = new StackPane();
		x.getChildren().addAll(h, wall);
		
		HBox h1 = new HBox(x);
		
		return h1;
	}
	
	
	private void move(HBox H) {
		TranslateTransition t = new TranslateTransition();
		H.setLayoutY(-115);
		t.setNode(H);
		t.setAutoReverse(false);
		t.setByY(550);
		t.setCycleCount(1);
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
				new KeyFrame(Duration.millis(0), e-> {
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
		A.getChildren().addAll(snake.getsnake());
		
		StackPane s = new StackPane(A, h1);
		
		A.setOnKeyPressed((KeyEvent e) -> {
			ArrayList<TranslateTransition> tc;
			if(e.getCode().equals(KeyCode.LEFT))
				tc = snake.moveleft();
			else if(e.getCode().equals(KeyCode.RIGHT)) 
				tc = snake.moveright();
			else 
				tc = new ArrayList<TranslateTransition>();
			for(int i = 0; i < tc.size(); i++)
				tc.get(i).play();
		});
		
		Scene scene = new Scene(s, 300, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
