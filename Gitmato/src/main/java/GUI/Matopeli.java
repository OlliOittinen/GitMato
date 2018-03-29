package GUI;

import Controller.PlayerController;
import Sound.Music;
import javafx.animation.AnimationTimer;
import Model.*;
import Spawnables.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Matopeli extends Application {

    private static Stage window;
    private Scene mainMenuScene, gameScene, gameOverScene, highscoreScene, highscoreTableScene;

    private Image background = new Image("images/BlueBG800x600.png");
    private Image snackicon = new Image("images/Apple(800x600).png");
    private Image bombicon = new Image("images/Bombs(800-600).png");
    private Image bombtarget = new Image("images/Target2.png");
    private Image bombfire = new Image("images/firestorm2.png");
    private Image confuseicon = new Image("images/Reverse.png");
    private Image confuseEffect = new Image("images/confusion.png");
    private Image fastericon = new Image("images/SpeedUp.png");
    private Image lasericon = new Image("images/Lasercannon.png");
    private Image laserH = new Image("images/LazerH.png");
    private Image laserV = new Image("images/lazerV.png");
    private Image lasersightV = new Image("images/lasersightV.png");
    private Image lasersightH = new Image("images/lasersightH.png");
    private Image lifeicon = new Image("images/Life_1.png");
    private Image shieldicon = new Image("images/Shield.png");
    private Image shieldeffect = new Image("images/ShieldEffect.png");
    private Image slowericon = new Image("images/SlowDown.png");
    private Image redwormleft = new Image("images/RedWormLeft(800x600).png");
    private Image redwormright = new Image("images/RedWormRight(800x600).png");
    private Image redwormup = new Image("images/RedWormUp(800x600).png");
    private Image redwormdown = new Image("images/RedWormDown(800x600).png");
    private Image bluewormleft = new Image("images/BlueWormLeft(800x600).png");
    private Image bluewormright = new Image("images/BlueWormRight(800x600).png");
    private Image bluewormup = new Image("images/BlueWormUp(800x600).png");
    private Image bluewormdown = new Image("images/BlueWormDown(800x600).png");
    private Image wormImage = new Image("images/RedWormUp(800x600).png");
    private Image worm2Image = new Image("images/BlueWormUp(800x600).png");
    private Image wormtail = new Image("images/RedWormTail(800x600).png");
    private Image wormtail2 = new Image("images/BlueWormTail(800x600).png");

    private static int width = 800;
    private static int height = 600;
    private Worm worm, worm2;
    private int tailsize = 0;
    private int tailsize2 = 0;
    private Board board;
    private ArrayList<Tail> body;
    private ArrayList<Tail> body2;
    private String gameMode = "versus";
    private PlayerController pc;
    private AnimationTimer timer;
    private Faster faster;
    private Slower slower;
    private Confuse confuse;
    private Life life;
    private Shield shield;
    private Bombs bombs;
    private Laser laser;
    private Snack snack;
    private ArrayList<Worm> worms;
    private ArrayList<Spawnables> powerups;
    private long currentTime = 0; // current time (ms)
    private long previousTime = 0; // time since last frame (ms)
    private double timeCounter = 0; // counts time (sec)
    private int frameCounter = 0;
    private double theRealFpsCounter = 0; // constantly shows the FPS
    private int score = 0;
    private Label winner_label = new Label();
    private Text scenetitle = new Text();
    private String username;
    private boolean hs_submitted;
    private Button highscore_button;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //initialize all the variables needed
        board = new Board(this, gameMode);
        pc = new PlayerController(board, gameMode);
        worms = new ArrayList<>();
        powerups = board.getPickableList();
        faster = (Faster) powerups.get(0);
        slower = (Slower) powerups.get(1);
        confuse = (Confuse) powerups.get(2);
        life = (Life) powerups.get(3);
        shield = (Shield) powerups.get(4);
        bombs = (Bombs) powerups.get(5);
        laser = (Laser) powerups.get(6);
        snack = (Snack) powerups.get(7);
        worms.add(worm = board.getWorm());
        worms.add(worm2 = board.getWorm2());

        //set window as primary stage
        window = primaryStage;
        window.setTitle("Gitmato");

        //don't allow the user to resize the screen
        window.setResizable(false);

        //loop the background music
        Sound.Music.backgroundMusic.loop();

        //create a new group that binds canvas and scenes together
        Group root = new Group();

        //create a canvas based on width and height parameters
        Canvas canvas = new Canvas(width, height);

        //get the graphics context for this canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //add everything related to this canvas to the group
        root.getChildren().add(canvas);

        //create a scene based on the group, but don't show it yet
        gameScene = new Scene(root);


        //----GAME MODE SELECTOR SCENE AKA MAIN MENU-----------

        //Button for playing against AI
        Button button1 = new Button("Player VS AI");
        //when clicked
        button1.setOnAction(e
                -> {
            //set the game mode as such, set the current scene as game scene, start drawing in animationLoop(gc)
            board.setGameMode("vs AI");
            gameMode = "vs AI";
            window.setScene(gameScene);
            animationLoop(gc);
        });

        //identical to the vs AI, but with changes to versus
        //Button for Versus
        Button button2 = new Button("Versus");
        button2.setId("Versus");
        button2.setOnAction(e
                -> {
            board.setGameMode("versus");
            gameMode = "versus";
            window.setScene(gameScene);
            animationLoop(gc);
        });

        //see above
        //button for single player
        Button button3 = new Button("Single player");
        button3.setOnAction(e
                -> {
            board.setGameMode("sp");
            gameMode = "sp";
            window.setScene(gameScene);
            animationLoop(gc);
        });

        //------------Main Menu Scene - Game mode selector --------------

        //create a new vertical box, center it, and add buttons to it
        VBox menuLayout = new VBox(20);
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.getChildren().addAll(button2, button1, button3);

        //main menu scene is a new scene based on above layout
        mainMenuScene = new Scene(menuLayout, width, height);

        //set id for css, get the styling from the correct file
        menuLayout.setId("main_menu");
        mainMenuScene.getStylesheets().add("Styling/styling.css");


        //-----------------------------------


        //Display main scene first
        window.setScene(mainMenuScene);

        //if user clicks the x for the window, exit
        window.setOnCloseRequest(e -> System.exit(0));
        window.setTitle("Gitmato");

        //shows this window to user
        window.show();
    }

    private void animationLoop(GraphicsContext gc) {
        //timer handles all the drawing in a loop
        timer = new AnimationTimer() {
            //fps-throttle
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                //if time between now and last update is more than 10M nanoseconds (=1 millisecond)
                if (now - lastUpdate >= 10_000_000) {
                    //let the controller handle inputs
                    gameScene.setOnKeyPressed((KeyEvent event) -> {
                        pc.keyPressed(event);
                    });
                    //update board based on controller input
                    board.updateBoard();
                    //draw the GUI based on board's parameters
                    draw(gc);
                }
            }
        };
        timer.start();
    }

    public void setWormImage() {
        //red (left) worm
        if (worm.getDirection() == 1) {
            wormImage = redwormleft;
        }

        if (worm.getDirection() == 2) {
            wormImage = redwormright;
        }

        if (worm.getDirection() == 3) {
            wormImage = redwormup;
        }

        if (worm.getDirection() == 4) {
            wormImage = redwormdown;
        }

        //blue (right) worm
        if (worm2.getDirection() == 1) {
            worm2Image = bluewormleft;
        }

        if (worm2.getDirection() == 2) {
            worm2Image = bluewormright;
        }

        if (worm2.getDirection() == 3) {
            worm2Image = bluewormup;
        }

        if (worm2.getDirection() == 4) {
            worm2Image = bluewormdown;
        }
    }

    private void draw(GraphicsContext g) {
        //as long as board tells GUI to be active; the game hasn't ended
        if (board.isIngame()) {

            //update worm bodies and sizes of the tail, get these from the board
            body = board.getTailList();
            body2 = board.getTailList2();
            tailsize = body.size();
            tailsize2 = body2.size();

            //keep drawing the background
            g.drawImage(background, 0, 0);

            //if worm #1's tail is bigger than 1 (exists)
            if (tailsize > 0) {
                //in a loop, draw these tail pieces
                for (int i = 0; i < tailsize; i++) {
                    g.drawImage(wormtail, body.get(i).getX(), body.get(i).getY());
                }
            }
            //identical to above, but for worm #2
            if (tailsize2 > 0) {
                for (int i = 0; i < tailsize2; i++) {
                    g.drawImage(wormtail2, body2.get(i).getX(), body2.get(i).getY());
                }
            }

            //draw powerups last so they're on top and easily visible to the viewer
            g.drawImage(snackicon, snack.getX(), snack.getY());
            g.drawImage(fastericon, faster.getX(), faster.getY());
            g.drawImage(slowericon, slower.getX(), slower.getY());
            g.drawImage(confuseicon, confuse.getX(), confuse.getY());
            g.drawImage(lifeicon, life.getX(), life.getY());
            g.drawImage(shieldicon, shield.getX(), shield.getY());
            g.drawImage(bombicon, bombs.getX(), bombs.getY());
            g.drawImage(bombtarget, bombs.getXBombs(1), bombs.getYBombs(1));
            g.drawImage(bombfire, bombs.getXBombs(2), bombs.getYBombs(2));
            g.drawImage(bombtarget, bombs.getXBombs(3), bombs.getYBombs(3));
            g.drawImage(bombfire, bombs.getXBombs(4), bombs.getYBombs(4));
            g.drawImage(bombtarget, bombs.getXBombs(5), bombs.getYBombs(5));
            g.drawImage(bombfire, bombs.getXBombs(6), bombs.getYBombs(6));
            g.drawImage(lasericon, laser.getX(), laser.getY());

            //if laser's private boolean 'lethal' is not true, draw the laser sight for it
            if (!laser.getLethal()) {
                g.drawImage(lasersightH, laser.getX3(), laser.getY3());
                g.drawImage(lasersightV, laser.getX2(), laser.getY2());

                //if lethal is true, draw the laser itself
            } else {
                g.drawImage(laserH, laser.getX3(), laser.getY3());
                g.drawImage(laserV, laser.getX2(), laser.getY2());
            }

            //keep drawing worm #1
            g.drawImage(wormImage, worm.getX(), worm.getY());

            //if the game mode is not single player, draw the second worm too
            //could this just be passed once and not be constantly checked?
            if (!gameMode.equals("sp")) {
                g.drawImage(worm2Image, worm2.getX(), worm2.getY());
            }
            //draw shield effects on top of worm if shield boolean is true
            if (worm.getShield(worm)) {
                g.drawImage(shieldeffect, worm.getX() - 5, worm.getY() - 4);
            }
            //identical, but for worm #2
            if (worm2.getShield(worm2)) {
                g.drawImage(shieldeffect, worm2.getX() - 5, worm2.getY() - 4);
            }
            //draw confusion effects on top of worm if confuse boolean is true
            if (worm.getReverse(worm)) {
                g.drawImage(confuseEffect, worm.getX() - 5, worm.getY() - 4);
            }
            //identical for worm #2
            if (worm2.getReverse(worm2)) {
                g.drawImage(confuseEffect, worm2.getX() - 5, worm2.getY() - 4);
            }
            //always draw points
            drawPoints(g);

            //if board is inactive
        } else {
            //stop the background music, play death music
            if (worm.getLife() < worm2.getLife() && !gameMode.equals("sp")) {
                score = worm2.getPoints();
                winner_label.setText("BLUE WON");
                winner_label.setTextFill(Color.BLUE);
            } else if (worm.getLife() > worm2.getLife() && !gameMode.equals("sp")) {
                score = worm.getPoints();
                winner_label.setText("RED WON");
                winner_label.setTextFill(Color.RED);
            } else {
                score = worm.getPoints();
            }
            winner_label.setId("winner_label");
            Music.backgroundMusic.stop();
            Music.death.play();
            createGameOverScene();
            createHighscoreScene();
            //set scene to be game over
            window.setScene(gameOverScene);
            //stop the timer that handles drawing
            timer.stop();
            //reset the board
            reset();
        }
    }

    private void drawPoints(GraphicsContext g) {

        //set fonts and colors to be used for drawing the points
        g.setFont(Font.font("Helvetica", FontWeight.BOLD, 22));
        g.setFill(Color.RED);
        g.setStroke(Color.BLACK);

        //initialise string to be drawn
        String hp = "HP: " + worm.getLife();
        String pt = "Pisteet: " + worm.getPoints();

        //draw the text on the GUI based on these values
        g.fillText(hp, 50, 30);
        g.fillText(pt, 50, 60);

        //if game mode is not single player, draw the points for the second worm
        if (!gameMode.equals("sp")) {
            g.setFill(Color.BLUE);
            String hp2 = "HP: " + worm2.getLife();
            String pt2 = "Pisteet: " + worm2.getPoints();
            g.fillText(hp2, 650, 30);
            g.fillText(pt2, 650, 60);
        }

        //------------------FPS counter-------------------
        //get the current time from the system
        currentTime = System.currentTimeMillis();
        //delta time is difference between current and previous time divided by 1k
        double deltaTime = (double) (currentTime - previousTime) / 1_000;
        // 1/deltaTime; <- tells the current fps

        //interval tells how often fps should be drawn; currently at 0.5 seconds
        double interval = 0.5;

        //if current time is greater  than the interval
        if (timeCounter > interval) {
            //reset the counters, save current frame counts on the one that's shown to the user
            theRealFpsCounter = frameCounter;
            frameCounter = 0;
            timeCounter = 0;
        } else {
            //if current time is less than interval, add difference between times to the time counter
            timeCounter += deltaTime;
            //add  1/interval to the current frame counter
            //why?
            frameCounter += (int) (1 / interval);
        }
        //save previous time as current time so when this is called again, it can use the new values
        previousTime = currentTime;

        //draw the fps on board on top of everything else (drawn last)
        String FPS = "FPS: " + theRealFpsCounter;
        g.setFill(Color.WHITE);
        g.setFont(Font.font(15));
        g.fillText(FPS, 400, 30);
    }

    private void reset() {
        //start the background music
        Music.backgroundMusic.loop();

        //tell the board it's ingame again
        board.setIngame(true);

        hs_submitted = false;

        //clear the internal arraylists for reinitialization
        worms.clear();
        body.clear();
        body2.clear();
        powerups.clear();

        //reset the tailsizes to be 0 for no conflicts
        tailsize = 0;
        tailsize = 0;

        //create a new Board based on the game mode
        board = new Board(this, gameMode);

        //create a new controller using the new board and game mode
        pc = new PlayerController(board, gameMode);

        //get powerups from the board
        powerups = board.getPickableList();
        faster = (Faster) powerups.get(0);
        slower = (Slower) powerups.get(1);
        confuse = (Confuse) powerups.get(2);
        life = (Life) powerups.get(3);
        shield = (Shield) powerups.get(4);
        bombs = (Bombs) powerups.get(5);
        laser = (Laser) powerups.get(6);
        snack = (Snack) powerups.get(7);

        //add worms from the board to local worms list
        worms.add(worm = board.getWorm());
        worms.add(worm2 = board.getWorm2());
    }

    private void createGameOverScene() {
        //create a new vertical box, center it, and add buttons to it, set id for css
        VBox gameOverLayout = new VBox(20);
        gameOverLayout.setId("gameOver");
        gameOverLayout.setAlignment(Pos.CENTER);

        //create a label for game over, handled in css
        Label gameOverLabel = new Label("GAME OVER");
        gameOverLabel.setId("gameOverLabel");


        //buttons
        Button restart = new Button("Restart");
        Button backToSS = new Button("Main menu");
        highscore_button = new Button("Submit\nhighscore");
        restart.setOnAction(e -> {
            reset();
            window.setScene(gameScene);
            timer.start();
        });

        //handle button clicks
        backToSS.setOnAction(e -> window.setScene(mainMenuScene));
        highscore_button.setOnAction(e -> {
            if (!hs_submitted) {
                window.setScene(highscoreScene);
                scenetitle.setText("You got " + score + " points ");
            } else {
                window.setScene(highscoreTableScene);

            }


        });

        //add buttons to the layout
        gameOverLayout.getChildren().addAll(gameOverLabel, winner_label, restart, backToSS, highscore_button);

        //create game over scene based on this layout
        gameOverScene = new Scene(gameOverLayout, width, height);
        //add css to this scene
        gameOverScene.getStylesheets().add("Styling/styling.css");
    }

    private void createHighscoreScene() {
        //create a gridpane
        GridPane grid = new GridPane();

        //align it center
        grid.setAlignment(Pos.CENTER);

        //set horizontal gap
        grid.setHgap(10);

        //set vertical gap
        grid.setVgap(10);

        //set padding around grid
        grid.setPadding(new Insets(25, 25, 25, 25));
        scenetitle.setId("highscore_title");
        scenetitle.setFill(Color.WHITE);
        grid.add(scenetitle, 0, 0, 2, 1);
        // create label for username
        Label userName = new Label("Username: ");

        userName.setTextFill(Color.WHITE);
        userName.setId("username");
        grid.add(userName, 0, 10);
        //create textfield for usernames
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 10);

        //create buttons
        Button submit = new Button("Submit");
        Button cancel = new Button("Cancel");

        // handlers for buttons
        cancel.setOnAction(e -> window.setScene(gameOverScene));
        submit.setOnAction(e -> {
            if ((userTextField.getText() != null && !userTextField.getText().isEmpty())) {
                hs_submitted = true;
                username = userTextField.getText();
                board.submitHighscore(score, username);
                window.setScene(highscoreTableScene);
            } else {
                userName.setText("Please enter\nyour username");
            }
        });
        //create Horizontal Box for buttons
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(submit, cancel);
        grid.add(hbBtn, 1, 20);

        highscoreScene = new Scene(grid, width, height);
        grid.setId("highscore_scene");
        //get stylesheets for highscore scene
        highscoreScene.getStylesheets().add("Styling/styling.css");
    }

    public void createHighscoreTableScene(ArrayList<String> scorelist) {
        //search the index of of the score from the sorted list from database
        int indexOf = scorelist.indexOf(username + " " + score) + 1;
        String highscore = "";
        //go through the results list and attach top 10 index, score and name values to new string "highscore"
        int i = 0;
        for (String s : scorelist) {
            i++;
            if (i <= 10) {
                highscore += +(i) + ". " + s + '\n';
            }
        }
        //create a gridpane
        GridPane grid = new GridPane();

        //align it center
        grid.setAlignment(Pos.CENTER);

        //set horizontal gap
        grid.setHgap(10);

        //set vertical gap
        grid.setVgap(10);

        VBox vbox = new VBox(40);
        vbox.setAlignment(Pos.CENTER);
        Text headline = new Text("You placed: #" + indexOf + " with " + score + " points!\n\n\n\n\nTop 10");
        headline.setFill(Color.WHITE);
        headline.setId("highscoretable_headline");
        Label highscores = new Label(highscore);
        highscores.setId("top_10_highscores");
        Button okbutton = new Button("OK");
        //handeler for ok button
        okbutton.setOnAction(e ->{
            window.setScene(gameOverScene);
            highscore_button.setText("Show\nhighscore");
        });
        vbox.getChildren().addAll(headline, highscores, okbutton);
        grid.getChildren().add(vbox);
        grid.setId("highscoretable_scene");

        highscoreTableScene = new Scene(grid, width, height);
        highscoreTableScene.getStylesheets().add("Styling/styling.css");
    }
}