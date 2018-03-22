package GUI;

import Controller.PlayerController;
import javafx.animation.AnimationTimer;
import Model.*;
import Spawnables.*;
import java.util.ArrayList;
import java.util.List;
import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class Matopeli extends Application {

    Stage window;
    Scene mainMenuScene, vsAIScene, versusScene, spScene, gameoverScene;
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
    Image wormImage = new Image("images/RedWormUp(800x600).png");
    Image worm2Image = new Image("images/BlueWormUp(800x600).png");
    Image wormtail = new Image("images/RedWormTail(800x600).png");
    Image wormtail2 = new Image("images/BlueWormTail(800x600).png");
    

    private static int width = 800;
    private static int height = 600;
    private Worm worm, worm2;
    private Faster faster;
    private Slower slower;
    private Life HP;
    private Shield shield;
    Bombs bombs;
    Laser laser;
    Snack snack;
    Confuse reverse;
    private int tailsize;
    private int tailsize2;
    private Board board;
    private ArrayList<Tail> body;
    private ArrayList<Tail> body2;
    private String pelimoodi="sp";

    private PlayerController pc;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        board = new Board(this, pelimoodi);
        pc = new PlayerController(board, pelimoodi);
        faster = new Faster();
        slower = new Slower();
        reverse = new Confuse();
        HP = new Life();
        shield = new Shield();
        bombs = new Bombs();
        laser = new Laser();
        snack = new Snack();
        ArrayList<Worm> worms = new ArrayList();
        worms.add(worm = new Worm(1)); //lista worm olioista
        worms.add(worm2 = new Worm(2));
        window = primaryStage;
        window.setTitle("Gitmato");
        Sound.Music.sound1.loop();

        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        //----GAME MODE SELECTOR SCENE-----------
        //Button for Single player
        Button button1 = new Button("Player VS AI");
        button1.setOnAction(e
                -> {
            vsAIScene = new Scene(root);
            window.setScene(vsAIScene);
            new AnimationTimer() {
                @Override
                public void handle(long now) {
                    board.updateBoard(e);
                    paint(gc);
                }
            }.start();
        });

        //Button for Versus
        Button button2 = new Button("Versus");
        button2.setOnAction(e
                -> {
            versusScene = new Scene(root);
            window.setScene(versusScene);
            new AnimationTimer() {
                @Override
                public void handle(long now) {
                    board.updateBoard(e);
                    paint(gc);
                }
            }.start();
        });
        Button button3 = new Button("Single player");
        button3.setOnAction(e
                -> {
            spScene = new Scene(root);
            window.setScene(spScene);
            new AnimationTimer() {
                @Override
                public void handle(long now) {
                    spScene.setOnKeyPressed((KeyEvent event) -> {
                        pc.keyPressed(event);
                        System.out.println(event.toString());
                    });
                    board.updateBoard(e);
                    paint(gc);
                }
            }.start();

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
        //Layout 3- Game over
/*
        VBox layout5 = new VBox(20);
        layout5.setAlignment(Pos.CENTER);
        Label label3 = new Label("GAME OVER");
        Button restart = new Button("Restart");
        Button backToSS = new Button("Back to Main menu");
        backToSS.setOnAction(e -> window.setScene(mainMenuScene));
        layout5.getChildren().addAll(label3, restart, backToSS);
        gameoverScene = new Scene(layout5, 800, 590);
        gameoverScene.getStylesheets().add("Styling/styling.css");
         */
        //-----------------------------------
        //Display main scene first
        window.setScene(mainMenuScene);
        window.setOnCloseRequest(e -> System.exit(0));
        window.setTitle("Gitmato");
        window.show();
    }

    public void paint(GraphicsContext g) {
        if (true) {
            Paint p = Color.BLACK;
            g.setFill(p);
            g.fillRect(0, 0, window.getWidth(), window.getHeight());
            draw(g);
        } else {
            //drawGameOver(g);
            //board.setIngame(false);
        }
    }

    public void draw(GraphicsContext g) {
        body = board.getTailList();
        body2 = board.getTailList2();

        if (tailsize > 0) {
            for (int i = 0; i < tailsize; i++) {
                // pidetään huoli että jokainen "tail" tulee piirrettyä per frame
                g.drawImage(wormtail, body.get(i).getX(), body.get(i).getY());
                //System.out.println("tätä tehdään");
            }
        }

        if (tailsize2 > 0) {
            for (int i = 0; i < tailsize2; i++) {
                // pidetään huoli että jokainen "tail" tulee piirrettyä per frame
                g.drawImage(wormtail2, body2.get(i).getX(), body2.get(i).getY());
                //System.out.println("tätä tehdään");
            }
        }

        // piirretään power-upit matojen päälle, jotta ne ovat helpommit nähtävissä
        g.drawImage(background, 0, 0);
        g.drawImage(snackicon, snack.getX(), snack.getY());
        g.drawImage(fastericon, faster.getX(), faster.getY());
        g.drawImage(slowericon, slower.getX(), slower.getY());
        g.drawImage(confuseicon, reverse.getX(), reverse.getY());
        g.drawImage(lifeicon, HP.getX(), HP.getY());
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
            g.drawImage(lasersightH, laser.getX2(), laser.getY2());

        } else {
            g.drawImage(laserH, laser.getX3(), laser.getY3());
            g.drawImage(laserV, laser.getX2(), laser.getY2());
        }
        g.drawImage(wormImage, worm.getX(), worm.getY());
        if (window.getScene() != spScene) {
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

    }

    public String getPelimoodi() {
        return pelimoodi;
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
//        Music.sound4.play();
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
//        Music.sound1.stop();
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
