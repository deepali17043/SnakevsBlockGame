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
					move(H[0], 0);
				}),
				new KeyFrame(Duration.millis(2000), e -> {
					A.getChildren().remove(H[1]);
					H[1] = H1[1];
					A.getChildren().add(H[1]);
					move(H[1], 1);
				}),
				new KeyFrame(Duration.millis(3000), e -> {
					A.getChildren().remove(H[2]);
					H[2] = H1[2];
					A.getChildren().add(H[2]);
					move(H[2], 2);
				}),
				new KeyFrame(Duration.millis(4000), e -> {
					A.getChildren().remove(H[3]);
					H[3] = H1[3];
					A.getChildren().add(H[3]);
					move(H[3], 3);
				}),
				new KeyFrame(Duration.millis(5000), e -> {
					A.getChildren().remove(H[4]);
					H[4] = H1[4];
					A.getChildren().add(H[4]);
					move(H[4], 4);
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
		
		showMenu.setOnAction(e -> {
			pausetransitions();
			timeline.pause();
			time = timeline.getCurrentTime();
			try {
				primaryStage.setScene(menu.getMenu(primaryStage));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		Exit.setOnAction(e -> {
			Platform.exit();
			System.exit(0);
		});
		
		h1.getChildren().add(Exit);
		h1.getChildren().add(sc);
		h1.getChildren().add(showMenu);
		h1.setPadding(new Insets(470, 0, 0, 0));
		h1.setSpacing(40);
		
		A.setBackground(new Background(
				new BackgroundFill(Color.BLACK, new CornerRadii(0), null)));
		A.getChildren().addAll(snake.getsnake());
		
		StackPane s = new StackPane(A, h1);
		
		s.setOnKeyPressed((KeyEvent e) -> {
			ArrayList<TranslateTransition> tc;
			if(e.getCode().equals(KeyCode.LEFT) && snake.getPositionX() >= 10)
				tc = snake.moveleft();
			else if(e.getCode().equals(KeyCode.RIGHT) && snake.getPositionX() <= 290) 
				tc = snake.moveright();
			else 
				tc = new ArrayList<TranslateTransition>();
			for(int i = 0; i < tc.size(); i++)
				tc.get(i).play();
		});
		
		Scene scene = new Scene(s, 300, 500);
		primaryStage.setScene(scene);
		
		HideMenu.setOnAction(e -> {
			primaryStage.setScene(scene);
			timeline.playFrom(time);
			resumetransitions();
			time = Duration.millis(0);
		});
		
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
