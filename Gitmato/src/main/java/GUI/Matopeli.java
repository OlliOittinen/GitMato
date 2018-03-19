package GUI;

import Controller.PlayerController;
import Model.Board;
import Sound.Music;
import javafx.application.*;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Matopeli extends Application {

    Stage mainMenu;
    Scene mainMenuScene, vsAIScene, versusScene, spScene;
    ImageView kuvamato = new ImageView("src/main/resources/images/BlueBG800x600.png");
    Image filter = new Image("src/main/resources/images/BlackFilter.png");
    ImageView filtteri = new ImageView(filter);

    private static Board board;
    private static PlayerController pc;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        mainMenu = primaryStage;
        mainMenu.setTitle("Gitmato");
        Sound.Music.sound1.loop();

        //----GAME MODE SELECTOR SCENE-----------
        //Button to Single player
        Button button1 = new Button("Player VS AI");
        button1.setOnAction(e
                -> {
            StackPane layout2 = new StackPane();
            vsAIScene = new Scene(layout2, 800, 590);
            mainMenu.setScene(vsAIScene);
        });

        //Button to Versus
        Button button2 = new Button("Versus");
        button2.setOnAction(e
                -> {
            StackPane layout3 = new StackPane();
            versusScene = new Scene(layout3, 800, 590);
            mainMenu.setScene(versusScene);
        });
        Button button3 = new Button("Single player");
        button3.setOnAction(e
                -> {
            StackPane layout4 = new StackPane();
            spScene = new Scene(layout4, 800, 590);
            mainMenu.setScene(spScene);
        });

        //Layout 1 - Game mode selector
        VBox layout1 = new VBox(20);
        layout1.setAlignment(Pos.CENTER);
        layout1.getChildren().addAll(button2, button1, button3);
        mainMenuScene = new Scene(layout1, 800, 506);
        layout1.setId("pane");
        mainMenuScene.getStylesheets().add("Styling/styling.css");
        //---------------------------------

        //------VERSUS SCENE---------------
        //Layout 3 - Singleplayer
        //-----------------------------------
        //------VERSUS SCENE---------------
        //Layout 2 - Versus
        //------GAME OVER SCENE---------------
        //Layout 3- Game over
        /*
        VBox layout3 = new VBox(20);
        layout3.setAlignment(Pos.CENTER);
       
        Label label3 = new Label("GAME OVER");
        Button restart = new Button("Restart");
        
        Button backToSS = new Button("Back to Main menu");
        backToSS.setOnAction(e -> setScene(1));
        
        layout3.getChildren().addAll(label3, restart, backToSS); // Adding swing node
        versusScene = new Scene(layout3, 800, 600);
        versusScene.getStylesheets().add("Styling/styling.css");
        //-----------------------------------
         */
        //Display scene 1 at first
        mainMenu.setScene(mainMenuScene);
        mainMenu.setOnCloseRequest(e -> System.exit(0));
        mainMenu.setTitle("Gitmato");
        mainMenu.show();
    }

    public void paintComponent(Graphics g) {

        if (ingame) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            g2.setPaint(Color.BLACK);
            g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
            g.drawImage(this.background, 0, 0, null);
            doDrawing(g);

            Toolkit.getDefaultToolkit().sync();
        } else {
            drawGameOver(g);
            inGame();
        }
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        drawPisteet(g);

        //tarkistetaan onko häntiä piirrettäväksi
        if (tailNro > 0) {
            for (int i = 0; i < body.size(); i++) {
                // pidetään huoli että jokainen "tail" tulee piirrettyä per frame
                g2d.drawImage(body.get(i).getImage(), body.get(i).getX(), body.get(i).getY(), this);
                //System.out.println("tätä tehdään");
            }
        }

        if (tailNro2 > 0) {
            for (int i = 0; i < body2.size(); i++) {
                // pidetään huoli että jokainen "tail" tulee piirrettyä per frame
                g2d.drawImage(body2.get(i).getImage(), body2.get(i).getX(), body2.get(i).getY(), this);
                //System.out.println("tätä tehdään");
            }
        }
        // piirretään power-upit matojen päälle, jotta ne ovat helpommit nähtävissä
        g2d.drawImage(snack.getImage(), snack.getX(), snack.getY(), this);
        g2d.drawImage(faster.getImage(), faster.getX(), faster.getY(), this);
        g2d.drawImage(slower.getImage(), slower.getX(), slower.getY(), this);
        g2d.drawImage(reverse.getImage(), reverse.getX(), reverse.getY(), this);
        g2d.drawImage(HP.getImage(), HP.getX(), HP.getY(), this);
        g2d.drawImage(shield.getImage(), shield.getX(), shield.getY(), this);
        g2d.drawImage(bombs.getImage(1), bombs.getX(), bombs.getY(), this);
        g2d.drawImage(bombs.getImage(2), bombs.getXBombs(1), bombs.getYBombs(1), this);
        g2d.drawImage(bombs.getImage(3), bombs.getXBombs(2), bombs.getYBombs(2), this);
        g2d.drawImage(bombs.getImage(2), bombs.getXBombs(3), bombs.getYBombs(3), this);
        g2d.drawImage(bombs.getImage(3), bombs.getXBombs(4), bombs.getYBombs(4), this);
        g2d.drawImage(bombs.getImage(2), bombs.getXBombs(5), bombs.getYBombs(5), this);
        g2d.drawImage(bombs.getImage(3), bombs.getXBombs(6), bombs.getYBombs(6), this);
        g2d.drawImage(laser.getImage(), laser.getX(), laser.getY(), this);
        if (!laser.getLethal()) {
            g2d.drawImage(laser.getlasersightH(), laser.getX3(), laser.getY3(), this);
            g2d.drawImage(laser.getLasersightV(), laser.getX2(), laser.getY2(), this);

        } else {
            g2d.drawImage(laser.getImageHori(), laser.getX3(), laser.getY3(), this);
            g2d.drawImage(laser.getImageVert(), laser.getX2(), laser.getY2(), this);
        }
        g2d.drawImage(worm.getImage(), worm.getX(), worm.getY(), this);
        if (pelimoodi != "sp") {
            g2d.drawImage(worm2.getImage(), worm2.getX(), worm2.getY(), this);
        }
        if (worm.getShield(worm)) {
            g2d.drawImage(shield.getShieldImage(), worm.getX() - 5, worm.getY() - 4, this);
        }
        if (worm2.getShield(worm2)) {
            g2d.drawImage(shield.getShieldImage(), worm2.getX() - 5, worm2.getY() - 4, this);
        }
        if (worm.getLife() <= 0 || worm2.getLife() <= 0) {
            drawGameOver(g);
        }
        if (worm.getReverse(worm)) {
            g2d.drawImage(reverse.getConfusionImage(), worm.getX() - 5, worm.getY() - 4, this);
        }
        if (worm2.getReverse(worm2)) {
            g2d.drawImage(reverse.getConfusionImage(), worm2.getX() - 5, worm2.getY() - 4, this);
        }

    }

    private void drawPisteet(Graphics g) {
        Font small = new Font("Helvetica", Font.BOLD, 20);
        Font smaller = new Font("Helvetica", Font.PLAIN, 15);
        FontMetrics fm = getFontMetrics(small);
        FontMetrics fm2 = getFontMetrics(smaller);
        String pt3 = "FPS: " + theRealFpsCounter;

        g.setColor(Color.RED);
        g.setFont(small);
        String hp = "HP: " + worm.getLife();
        String pt = "Pisteet: " + worm.getPoints();
        g.drawString(hp, 10, 25);
        g.drawString(pt, 10, 50);

        if (pelimoodi != "sp") {
            g.setColor(Color.BLUE);
            String hp2 = "HP: " + worm2.getLife();
            String pt2 = "Pisteet: " + worm2.getPoints();

            g.drawString(hp2, (790 - fm.stringWidth(hp2)), 25);
            g.drawString(pt2, (790 - fm.stringWidth(pt2)), 50);
        }
        g.setColor(Color.WHITE);
        g.setFont(smaller);
        g.drawString(pt3, ((790 - fm2.stringWidth(pt3)) / 2), 20);//piirtää fps

    }



    private void drawGameOver(Graphics g) {
        Music.sound4.play();
        laser.hide();
        filter = filtteri.getImage();
        String msg = null;
        String msg2;
        String msg3;
        Graphics2D g3 = (Graphics2D) g;
        g3.drawImage(filter, 0, 0, null);

        Font small = new Font("Helvetica", Font.BOLD, 20);
        Font big = new Font("Helvetica", Font.BOLD, 30);
        FontMetrics fm2 = getFontMetrics(small);
        FontMetrics fm = getFontMetrics(big);
        g3.setFont(big);

        Music.sound1.stop();
        ingame = false;
        if (worm.getLife() <= 0) {
            if (pelimoodi != "sp") {
                score = worm2.getPoints();
                msg = "BLUE Won!";
                g3.setColor(Color.blue);
            } else {
                score = worm.getPoints();
                msg = "GAME OVER!";
                g3.setColor(Color.white);
            }
        } else if (worm2.getLife() <= 0) {
            score = worm.getPoints();
            msg = "RED Won!";
            g3.setColor(Color.red);
        }
        msg2 = "Press SPACE to play again.";
        msg3 = "Press H to submit your highscore";
        g3.drawString(msg, (806 - fm.stringWidth(msg)) / 2, 270);
        g3.setFont(small);
        g3.setColor(Color.white);
        g3.drawString(msg2, (806 - fm2.stringWidth(msg2)) / 2, 600 / 2);
        g3.drawString(msg3, (806 - fm2.stringWidth(msg3)) / 2, 320);
    }

}
/*
public class Matopeli{
    
    private static MainFrame peli;
    private static UIController uic;
    private static PlayerController pc;

   
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {     
                /*lpane = new JLayeredPane();
                peli = new MainFrame();
                peli.setLayout(new BorderLayout());
                peli.add(lpane, BorderLayout.CENTER);
                peliOhi = new GameOver(peli);

                peliOhi.setOpaque(true);

                lpane.add(board, 0, 0);
                lpane.add(peliOhi, 1, 0);

                peli.pack();
                peli.setVisible(true);
                uic = new UIController();
                pc = new PlayerController();
                peli = new MainFrame(uic);
                //peli.pack();
                peli.setVisible(true);
            }
        });         
    } 
}
 */
