package GUI;

import Controller.PlayerController;
import Sound.Music;
import javafx.animation.AnimationTimer;
import Model.*;
import Spawnables.*;
import java.util.ArrayList;

import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Matopeli extends Application {

    private static Stage window;
    private Scene mainMenuScene, gameScene, gameoverScene;

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
    private String pelimoodi = "versus";
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
    private long currentTime = 0; // nykyinen aika (ms)
    private long previousTime = 0; // viime framen aika (ms)
    private double timeCounter = 0; // aikalaskuri (sec)
    private int frameCounter = 0;
    private double theRealFpsCounter = 0; // näyttää jatkuvasti oikean fps:n

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        board = new Board(this, pelimoodi);
        pc = new PlayerController(board, pelimoodi);
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

        worms.add(worm = board.getWorm()); //lista worm olioista
        worms.add(worm2 = board.getWorm2());
        window = primaryStage;
        window.setTitle("Gitmato");
        window.setResizable(false);
        Sound.Music.backgroundMusic.loop();

        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        gameScene = new Scene(root);
        //----GAME MODE SELECTOR SCENE-----------
        //Button for Single player
        Button button1 = new Button("Player VS AI");
        button1.setOnAction(e
                -> {
            board.setPelimoodi("vsAI");
            pelimoodi = "vs AI";
            window.setScene(gameScene);
            animate(gc);
        });

        //Button for Versus
        Button button2 = new Button("Versus");
        button2.setId("Versus");
        button2.setOnAction(e
                -> {
            board.setPelimoodi("versus");
            pelimoodi = "versus";
            window.setScene(gameScene);
            animate(gc);
        });
        Button button3 = new Button("Single player");
        button3.setOnAction(e
                -> {
            board.setPelimoodi("sp");
            pelimoodi = "sp";
            window.setScene(gameScene);
            animate(gc);
        });

        //Layout 1 - Game mode selector
        VBox layout1 = new VBox(20);
        layout1.setAlignment(Pos.CENTER);
        layout1.getChildren().addAll(button2, button1, button3);
        mainMenuScene = new Scene(layout1, 800, 590);
        layout1.setId("pane");
        mainMenuScene.getStylesheets().add("Styling/styling.css");

        //---------------------------------
        //------SINGLEPLAYER SCENE---------------
        //Layout 3 - Singleplayer
        //-----------------------------------
        //------VERSUS SCENE---------------
        //Layout 2 - Versus
        //------GAME OVER SCENE---------------
        VBox layout5 = new VBox(20);
        layout5.setId("pane2");
        layout5.setAlignment(Pos.CENTER);
        Label gameOver = new Label("GAME OVER");
        gameOver.setId("gameover");
        Button restart = new Button("Restart");
        Button backToSS = new Button("Main menu");
        Button highscore = new Button("Submit\nhighscore");
        restart.setOnAction(e -> {
            reset();
            window.setScene(gameScene);
            timer.start();
        });
        backToSS.setOnAction(e -> window.setScene(mainMenuScene));
        highscore.setOnAction(e -> board.submitHighscore());
        layout5.getChildren().addAll(gameOver, restart, backToSS, highscore);
        gameoverScene = new Scene(layout5, width, height);
        gameoverScene.getStylesheets().add("Styling/styling.css");
        //-----------------------------------
        //Display main scene first
        window.setScene(mainMenuScene);
        window.setOnCloseRequest(e -> System.exit(0));
        window.setTitle("Gitmato");
        window.show();
    }

    private void animate(GraphicsContext gc) {
        timer = new AnimationTimer() {
            //fps-throttle
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 10_000_000) {

                    gameScene.setOnKeyPressed((KeyEvent event) -> {
                        pc.keyPressed(event);
                    });
                    board.updateBoard();
                    draw(gc);
                }
            }
        };
        timer.start();
    }

    public void setWormImage() {
        //punanen mato
        if (worm.getSuunta() == 1) {
            wormImage = redwormleft;
        }

        if (worm.getSuunta() == 2) {
            wormImage = redwormright;
        }

        if (worm.getSuunta() == 3) {
            wormImage = redwormup;
        }

        if (worm.getSuunta() == 4) {
            wormImage = redwormdown;
        }

        //sininen mato
        if (worm2.getSuunta() == 1) {
            worm2Image = bluewormleft;
        }

        if (worm2.getSuunta() == 2) {
            worm2Image = bluewormright;
        }

        if (worm2.getSuunta() == 3) {
            worm2Image = bluewormup;
        }

        if (worm2.getSuunta() == 4) {
            worm2Image = bluewormdown;
        }
    }

    private void draw(GraphicsContext g) {
        if (board.isIngame()) {

            body = board.getTailList();
            body2 = board.getTailList2();
            tailsize = body.size();
            tailsize2 = body2.size();
            g.drawImage(background, 0, 0);
            if (tailsize > 0) {
                for (int i = 0; i < tailsize; i++) {
                    // pidetään huoli että jokainen "tail" tulee piirrettyä per frame
                    g.drawImage(wormtail, body.get(i).getX(), body.get(i).getY());
                }
            }

            if (tailsize2 > 0) {
                for (int i = 0; i < tailsize2; i++) {
                    // pidetään huoli että jokainen "tail" tulee piirrettyä per frame
                    g.drawImage(wormtail2, body2.get(i).getX(), body2.get(i).getY());
                }
            }

            // piirretään power-upit matojen päälle, jotta ne ovat helpommit nähtävissä
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

            if (!laser.getLethal()) {
                g.drawImage(lasersightH, laser.getX3(), laser.getY3());
                g.drawImage(lasersightV, laser.getX2(), laser.getY2());

            } else {
                g.drawImage(laserH, laser.getX3(), laser.getY3());
                g.drawImage(laserV, laser.getX2(), laser.getY2());
            }

            g.drawImage(wormImage, worm.getX(), worm.getY());

            if (pelimoodi != "sp") {
                g.drawImage(worm2Image, worm2.getX(), worm2.getY());
            }
            if (worm.getShield(worm)) {
                g.drawImage(shieldeffect, worm.getX() - 5, worm.getY() - 4);
            }
            if (worm2.getShield(worm2)) {
                g.drawImage(shieldeffect, worm2.getX() - 5, worm2.getY() - 4);
            }
            if (worm.getLife() <= 0 || worm2.getLife() <= 0) {
                //drawGameOver(g);
            }
            if (worm.getReverse(worm)) {
                g.drawImage(confuseEffect, worm.getX() - 5, worm.getY() - 4);
            }
            if (worm2.getReverse(worm2)) {
                g.drawImage(confuseEffect, worm2.getX() - 5, worm2.getY() - 4);
            }
            drawPoints(g);
        } else {
            Music.death.play();
            window.setScene(gameoverScene);
            board.setIngame(false);
            timer.stop();
            reset();
        }
    }

    //also handles FPS-counter
    private void drawPoints(GraphicsContext g) {

        g.setFont(Font.font("Helvetica", FontWeight.BOLD, 22));
        g.setFill(Color.RED);
        g.setStroke(Color.BLACK);

        String hp = "HP: " + worm.getLife();
        String pt = "Pisteet: " + worm.getPoints();
        g.fillText(hp, 50, 30);
        g.fillText(pt, 50, 60);

        if (pelimoodi != "sp") {
            g.setFill(Color.BLUE);
            String hp2 = "HP: " + worm2.getLife();
            String pt2 = "Pisteet: " + worm2.getPoints();
            g.fillText(hp2, 650, 30);
            g.fillText(pt2, 650, 60);
        }

        currentTime = System.currentTimeMillis();
        double deltaTime = (double) (currentTime - previousTime) / 1_000;
        // 1/deltaTime); <- kertoo nykyisen fps joka frame.
        double interval = 0.5;

        if (timeCounter > interval) {
            theRealFpsCounter = frameCounter;
            frameCounter = 0;
            timeCounter = 0;
        } else {
            timeCounter += deltaTime;
            frameCounter = frameCounter + (int) (1 / interval);
        }
        previousTime = currentTime;

        String FPS = "FPS: " + theRealFpsCounter;
        g.setFill(Color.WHITE);
        g.setFont(Font.font(15));
        g.fillText(FPS, 400, 30);
    }

    public String getPelimoodi() {
        return pelimoodi;
    }

    private void reset() {
        board.setIngame(true);
        worms.clear();
        body.clear();
        body2.clear();
        tailsize = 0;
        tailsize = 0;
        powerups.clear();
        board = new Board(this, pelimoodi);
        pc = new PlayerController(board, pelimoodi);
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

        worms.add(worm = board.getWorm()); //lista worm olioista
        worms.add(worm2 = board.getWorm2());
    }
}