package GUI;

import Controller.PlayerController;
import Sound.Music;
import javafx.animation.AnimationTimer;
import Model.*;
import Spawnables.*;
import java.util.ArrayList;

import javafx.application.*;
import javafx.fxml.FXML;
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

    Image background = new Image("images/BlueBG800x600.png");
    Image snackicon = new Image("images/Apple(800x600).png");
    Image bombicon = new Image("images/Bombs(800-600).png");
    Image bombtarget = new Image("images/Target2.png");
    Image bombfire = new Image("images/firestorm2.png");
    Image confuseicon = new Image("images/Reverse.png");
    Image confuseEffect = new Image("images/confusion.png");
    Image fastericon = new Image("images/SpeedUp.png");
    Image lasericon = new Image("images/Lasercannon.png");
    Image laserH = new Image("images/LazerH.png");
    Image laserV = new Image("images/lazerV.png");
    Image lasersightV = new Image("images/lasersightV.png");
    Image lasersightH = new Image("images/lasersightH.png");
    Image lifeicon = new Image("images/Life_1.png");
    Image shieldicon = new Image("images/Shield.png");
    Image shieldeffect = new Image("images/ShieldEffect.png");
    Image slowericon = new Image("images/SlowDown.png");
    Image redwormleft = new Image("images/RedWormLeft(800x600).png");
    Image redwormright = new Image("images/RedWormRight(800x600).png");
    Image redwormup = new Image("images/RedWormUp(800x600).png");
    Image redwormdown = new Image("images/RedWormDown(800x600).png");
    Image bluewormleft = new Image("images/BlueWormLeft(800x600).png");
    Image bluewormright = new Image("images/BlueWormRight(800x600).png");
    Image bluewormup = new Image("images/BlueWormUp(800x600).png");
    Image bluewormdown = new Image("images/BlueWormDown(800x600).png");

    Image wormImage = new Image("images/RedWormUp(800x600).png");
    Image worm2Image = new Image("images/BlueWormUp(800x600).png");
    Image wormtail = new Image("images/RedWormTail(800x600).png");
    Image wormtail2 = new Image("images/BlueWormTail(800x600).png");

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
    private boolean vsAIDone;
    private boolean versusDone;
    private boolean spDone;

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
        layout5.setAlignment(Pos.CENTER);
        Label gameOver = new Label("GAME OVER");
        Button restart = new Button("Restart");
        Button backToSS = new Button("Main menu");
        Button highscore = new Button("Submit highscore");
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
                    paint(gc);
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

    private void paint(GraphicsContext g) {

        if (board.isIngame()) {
            draw(g);
        } else {
            Music.death.play();
            window.setScene(gameoverScene);
            board.setIngame(false);
            timer.stop();
            reset();
        }
    }

    private void draw(GraphicsContext g) {

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

    }

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

        String FPS = "FPS: " + board.FPS();
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
    /*
    public void paintComponent(GraphicsContext g) {
        if (board.isIngame()) {
            Paint p = Color.BLACK;
            g.setFill(p);
            g.fillRect(0, 0, window.getWidth(), window.getHeight());
            doDrawing(g);
        } else {
            //drawGameOver(g);
            board.setIngame(false);
        }
    }

    private void doDrawing(GraphicsContext g) {

        drawPoints(g);
        int tailsize = board.getTailList().size();
        int tailsize2 = board.getTailList2().size();
        List<Tail> body = board.getTailList();
        List<Tail> body2 = board.getTailList2();
        //tarkistetaan onko häntiä piirrettäväksi
        if (tailsize > 0) {
            for (int i = 0; i < tailsize; i++) {
                // pidetään huoli että jokainen "tail" tulee piirrettyä per frame
                g.drawImage(body.get(i).getImage(), body.get(i).getX(), body.get(i).getY());
                //System.out.println("tätä tehdään");
            }
        }

        if (tailsize2 > 0) {
            for (int i = 0; i < tailsize2; i++) {
                // pidetään huoli että jokainen "tail" tulee piirrettyä per frame
                g.drawImage(body2.get(i).getImage(), body2.get(i).getX(), body2.get(i).getY());
                //System.out.println("tätä tehdään");
            }
        }
        // piirretään power-upit matojen päälle, jotta ne ovat helpommit nähtävissä
        g.drawImage(snack.getImage(), snack.getX(), snack.getY());
        g.drawImage(faster.getImage(), faster.getX(), faster.getY());
        g.drawImage(slower.getImage(), slower.getX(), slower.getY());
        g.drawImage(reverse.getImage(), reverse.getX(), reverse.getY());
        g.drawImage(HP.getImage(), HP.getX(), HP.getY());
        g.drawImage(shield.getImage(), shield.getX(), shield.getY());
        g.drawImage(bombs.getImage(1), bombs.getX(), bombs.getY());
        g.drawImage(bombs.getImage(2), bombs.getXBombs(1), bombs.getYBombs(1));
        g.drawImage(bombs.getImage(3), bombs.getXBombs(2), bombs.getYBombs(2));
        g.drawImage(bombs.getImage(2), bombs.getXBombs(3), bombs.getYBombs(3));
        g.drawImage(bombs.getImage(3), bombs.getXBombs(4), bombs.getYBombs(4));
        g.drawImage(bombs.getImage(2), bombs.getXBombs(5), bombs.getYBombs(5));
        g.drawImage(bombs.getImage(3), bombs.getXBombs(6), bombs.getYBombs(6));
        g.drawImage(laser.getImage(), laser.getX(), laser.getY());
        if (!laser.getLethal()) {
            g.drawImage(laser.getlasersightH(), laser.getX3(), laser.getY3());
            g.drawImage(laser.getLasersightV(), laser.getX2(), laser.getY2());

        } else {
            g.drawImage(laser.getImageHori(), laser.getX3(), laser.getY3());
            g.drawImage(laser.getImageVert(), laser.getX2(), laser.getY2());
        }
        g.drawImage(worm.getImage(), worm.getX(), worm.getY());
        if (window.getScene() != spScene) {
            g.drawImage(worm2.getImage(), worm2.getX(), worm2.getY());
        }
        if (worm.getShield(worm)) {
            g.drawImage(shield.getShieldImage(), worm.getX() - 5, worm.getY() - 4);
        }
        if (worm2.getShield(worm2)) {
            g.drawImage(shield.getShieldImage(), worm2.getX() - 5, worm2.getY() - 4);
        }
        if (worm.getLife() <= 0 || worm2.getLife() <= 0) {
            //drawGameOver(g);
        }
        if (worm.getReverse(worm)) {
            g.drawImage(reverse.getConfusionImage(), worm.getX() - 5, worm.getY() - 4);
        }
        if (worm2.getReverse(worm2)) {
            g.drawImage(reverse.getConfusionImage(), worm2.getX() - 5, worm2.getY() - 4);
        }

    }

    private void drawPoints(GraphicsContext g) {
        Text red = new Text();
        Text blue = new Text();
        Text white = new Text();

        red.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        red.setFill(Color.RED);
        red.setTextAlignment(TextAlignment.LEFT);

        String hp = "HP: " + board.getWorm().getLife();
        String pt = "Pisteet: " + board.getWorm().getPoints();
        red.setText(hp);
        red.setText(pt);

        if (window.getScene() != spScene) {
            blue.setFill(Color.BLUE);
            String hp2 = "HP: " + board.getWorm2().getLife();
            String pt2 = "Pisteet: " + board.getWorm2().getPoints();
            blue.setText(hp2);
            blue.setText(pt2);
            blue.setTextAlignment(TextAlignment.RIGHT);
        }

        String FPS = "FPS: " + board.FPS();
        white.setText(FPS);
        white.setFill(Color.WHITE);
        white.setFont(Font.font(10));
        white.setTextAlignment(TextAlignment.CENTER);
    }
     */
//    private void drawGameOver(GraphicsContext g, Scene s) {
//        Music.death.play();
//        laser.hide();
//        filter = filtteri.getImage();
//        String msg = null;
//        String msg2;
//        String msg3;
//        Graphics2D g3 = (Graphics2D) g;
//        g3.drawImage(filter, 0, 0, null);
//
//        Font small = new Font("Helvetica", Font.BOLD, 20);
//        Font big = new Font("Helvetica", Font.BOLD, 30);
//        FontMetrics fm2 = getFontMetrics(small);
//        FontMetrics fm = getFontMetrics(big);
//        g3.setFont(big);
//
//        Music.backgroundMusic.stop();
//        ingame = false;
//        if (worm.getLife() <= 0) {
//            if (s != spScene) {
//                score = worm2.getPoints();
//                msg = "BLUE Won!";
//                g3.setColor(Color.blue);
//            } else {
//                score = worm.getPoints();
//                msg = "GAME OVER!";
//                g3.setColor(Color.white);
//            }
//        } else if (worm2.getLife() <= 0) {
//            score = worm.getPoints();
//            msg = "RED Won!";
//            g3.setColor(Color.red);
//        }
//        msg2 = "Press SPACE to play again.";
//        msg3 = "Press H to submit your highscore";
//        g3.drawString(msg, (806 - fm.stringWidth(msg)) / 2, 270);
//        g3.setFont(small);
//        g3.setColor(Color.white);
//        g3.drawString(msg2, (806 - fm2.stringWidth(msg2)) / 2, 600 / 2);
//        g3.drawString(msg3, (806 - fm2.stringWidth(msg3)) / 2, 320);
//    }
}
