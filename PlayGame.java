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
			b[j].setStyle("-fx-font: 12 arial; -fx-base: #f4cc86; -fx-weight: bold;"
					+ "-fx-text-fill: BLACK;");
			//System.out.println(y.getvalue());
		}
		return b;
	}
	
	private Group genobjects() {
		Group root = new Group();
		for(int i = 0; i < 9; i++) {
			//when i-> even generate a object
			//when i-> odd, generate a wall
		}
		return root;
	}
	
	private Circle[] gentokens() {
		Group root = new Group();
		
		Circle[] c = new Circle[4];
		c[0] = new Circle();
		c[0].setLayoutX(25);
		c[0].setFill(Color.RED); //shield\
		root.getChildren().add(c[0]);
	
		c[1] = new Circle();
		c[1].setLayoutX(75);
		c[1].setFill(Color.BISQUE); //magnet
		root.getChildren().add(c[1]);
		
		c[2] = new Circle();
		c[2].setLayoutX(125);
		c[2].setFill(Color.YELLOW); //ball
		root.getChildren().add(c[2]);
		c[3] = new Circle();
		c[3].setLayoutX(175);
		c[3].setFill(Color.BLUE); //Destroy Blocks
		root.getChildren().add(c[3]);
		
		
		return c;
	}
	
	private Group genwalls() {
		Group root = new Group();
		for(int i = 0; i < 4; i++) {
			//generate walls at all times
			
		}
		return root;
	}
	
	private void move(HBox H, TranslateTransition t) {
		H.setLayoutY(-50);
		t.setNode(H);
		t.setAutoReverse(false);
		t.setByY(600);
		t.setCycleCount(1);
		t.setDuration(Duration.millis(6000));
		t.play();
	}
	
	private HBox displayblocks() {
		TranslateTransition t = new TranslateTransition();
		HBox h = new HBox();
		h.getChildren().addAll(genblocks());
		move(h, t);
		return h;
	}
	
	private HBox displayobjects() {
		TranslateTransition t = new TranslateTransition();
		HBox h = new HBox();
		h.getChildren().addAll(genobjects());
		move(h,t);
		return h;
	}
	
	private HBox displaytokens() {
		TranslateTransition t = new TranslateTransition();
		HBox h = new HBox();
		h.getChildren().addAll(gentokens());
		move(h,t);
		return h;
	}
	
	private HBox displaywalls() {
		TranslateTransition t = new TranslateTransition();
		HBox h = new HBox();
		h.getChildren().addAll(genwalls());
		move(h,t);
		return h;
	}
	
	//private static TranslateTransition[] tb;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Snake vs. Block Game");
		
		AnchorPane A = new AnchorPane();
		HBox[] H = new HBox[5];
		for(int i = 0; i < 5; i++) 
			H[i] = new HBox();
		timeline = new Timeline(
			new KeyFrame(Duration.millis(0), e-> {
				//tokens 
				//A.getChildren().remove(H[0]);
				H[0] = displayblocks();
				A.getChildren().add(H[0]);
			}),
			new KeyFrame(Duration.millis(2000), e -> {
				//blocks
				A.getChildren().remove(H[1]);
				H[1] = displayblocks();
				A.getChildren().add(H[1]);
			})/*,
			new KeyFrame(Duration.millis(2000), e -> {
				//tokens
				A.getChildren().remove(H[2]);
				H[2] = displaytokens();
				A.getChildren().add(H[2]);
			}),
			new KeyFrame(Duration.millis(3000), e-> {
				//walls
				A.getChildren().remove(H[3]);
				H[3] = displaywalls();
				A.getChildren().add(H[3]);
			}),
			new KeyFrame(Duration.millis(4000), e -> {
				// tokens or individual blocks
				A.getChildren().remove(H[4]);
				H[4] = displayobjects();
				A.getChildren().add(H[4]);
			})
			/*new KeyFrame(Duration.millis(2000), e -> {
				A.getChildren().remove(H[1]); H[1] = fn(); A.getChildren().add(H[1]);}),
			new KeyFrame(Duration.millis(4000), e -> {
				A.getChildren().remove(H[2]); H[2] = fn(); A.getChildren().add(H[2]);}),
			new KeyFrame(Duration.millis(6000), e -> {
				A.getChildren().remove(H[3]); H[3] = fn(); A.getChildren().add(H[3]); }),
			new KeyFrame(Duration.millis(8000), e -> {
				A.getChildren().remove(H[4]); H[4] = fn(); A.getChildren().add(H[4]);}),
			new KeyFrame(Duration.millis(10000), e -> {
				A.getChildren().remove(H[5]); H[5] = fn(); A.getChildren().add(H[5]);})*/
			
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
		
		Scene scene = new Scene(A, 250, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}

