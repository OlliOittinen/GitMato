/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import GUI.Matopeli;
import Sound.Music;
import Spawnables.*;
import java.util.ArrayList;
import java.util.TimerTask;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Ellipse;

/**
 *
 * @author maxki, Olli, Eero, Ged
 */
public class Board {

    private ArrayList<Worm> worms;
    private Worm worm;
    private Worm worm2;
/*
    private PlayerController control;
*/
    private Tail tail;
   private Tail tail2;
/*    private Timeline timer;*/
    private Snack snack;
    private Faster faster;
    private Slower slower;
    private Confuse reverse;
    private Life HP;
    private Shield shield;
    private Bombs bombs;
    private Laser laser;
    private String gameMode;
    private Matopeli engine;
    private Bot bot;
    private DBConnection connection = new DBConnection();

    //Lista Tail paloista
    private ArrayList<Tail> tailList;
    private ArrayList<Tail> tailList2;
    private int tailNro = 0;
    private int tailNro2 = 0;
    private ArrayList<Spawnables> pickableList;
    private ArrayList<Point2D> coordinates;
    private ArrayList<Point2D> coordinates2;
    // Wormin locaatio muuttujat:
    private Point2D p;
    private Point2D p2;// coordinaatit

// ------------------------------------------en tiiä näistä ------------------------------------
    public String getGameMode() {
        return gameMode;
    }
    public void setGameMode(String gameMode) {this.gameMode = gameMode;}
    public Worm getWorm() {
        return worm;
    }
    public Worm getWorm2() {
        return worm2;
    }
    public Tail getTail() {
        return tail;
    }
    public Tail getTail2() {
        return tail2;
    }
    public int getTailNro2() {
        return tailNro2;
    }
    public ArrayList<Tail> getTailList() {
        return tailList;
    }
    public ArrayList<Tail> getTailList2() {
        return tailList2;
    }
    public ArrayList<Spawnables> getPickableList() {
        return pickableList;
    }
    public ArrayList getWorms() {
        return worms;
    }

 // --------------------------------------------------------------------------------------------


 //    -------------------------- nää saa ehkä jäädä ----------------------------
    private void powerUpCD() {

        faster.setX(-100);
        faster.setY(-100);
        slower.setX(-100);
        slower.setY(-100);
        reverse.setX(-100);
        reverse.setY(-100);
        HP.setX(-100);
        HP.setY(-100);
        shield.setX(-100);
        shield.setY(-100);
        bombs.setY(-100);
        bombs.setX(-100);
        for (int i = 1; i < 7; i++) {
            bombs.setXBombs(i, -1000);
            bombs.setYBombs(i, -1000);
        }
        laser.setY(-100);
        laser.setX(-100);

        java.util.Timer timer2 = new java.util.Timer();
        timer2.schedule(new TimerTask() {

            @Override
            public void run() {

                int n = (int) (Math.random() * 7);

                switch (n) {
                    case 0:
                        shield.randomizeIconLocation();
                        break;
                    case 1:
                        faster.randomizeIconLocation();
                        break;
                    case 2:
                        slower.randomizeIconLocation();
                        break;
                    case 3:
                        reverse.randomizeIconLocation();
                        break;
                    case 4:
                        HP.randomizeIconLocation();
                        break;
                    case 5:
                        bombs.randomizeIconLocation();
                        break;
                    case 6:
                        laser.randomizeIconLocation();
                        break;
                }

            }
        }, 5000); //aika (ms), joka odotetaan
    }

    public void submitHighscore(int score, String name) {
                if (name != null) {
                    connection.submitScore(score, name, gameMode);
                    ArrayList<String> scores = connection.showHighscore(gameMode);
                    engine.createHighscoreTableScene(scores);
            }

    }

    public boolean isIngame() {
        return ingame;
    }

    public void setIngame(boolean ingame) {
        this.ingame = ingame;
    }

    private boolean ingame;

    //-------------------------------------------------------------------------------------

    public Board(Matopeli e, String gameMode) {

        this.engine = e;
        this.gameMode = gameMode;

        //alustetaan listat
        pickableList = new ArrayList<>();
        worms = new ArrayList<>();

        this.coordinates = new ArrayList<>();
        this.tailList = new ArrayList<>();
        this.p = new Point2D(0, 0);

        this.coordinates2 = new ArrayList<>();
        this.tailList2 = new ArrayList<>();
        this.p2 = new Point2D(0, 0);

        initBoard();
    }

    private void initBoard() {

        faster = new Faster();
        slower = new Slower();
        reverse = new Confuse();
        HP = new Life();
        shield = new Shield();
        bombs = new Bombs();
        laser = new Laser();
        snack = new Snack();

        pickableList.add(faster);
        pickableList.add(slower);
        pickableList.add(reverse);
        pickableList.add(HP);
        pickableList.add(shield);
        pickableList.add(bombs);
        pickableList.add(laser);
        pickableList.add(snack);
        worms.add(worm = new Worm(1)); //lista worm olioista
        worms.add(worm2 = new Worm(2));
        bot = new Bot(this);

        ingame = true;
        if (gameMode.equals("vs AI")) {
            bot.BotTurnDown();
        }
        if (gameMode.equals("sp")) {
            worm2.setX(-1000);
            worm2.setY(-1000);
            worm.setLife(1);
        }

        if (!gameMode.equals("sp")) {
            powerUpCD(); //piilottaa powerupit alussa
        }
    }

    private void checkCollisions() {

        Bounds Matokuutio = worm.getBounds();
        Bounds Matokuutio2 = worm2.getBounds();

        Bounds s = snack.getBoundsForIcon();
        Bounds pf = faster.getBoundsForIcon();
        Bounds ps = slower.getBoundsForIcon();
        Bounds pr = reverse.getBoundsForIcon();
        Bounds pl = HP.getBoundsForIcon();
        Bounds psh = shield.getBoundsForIcon();
        Bounds pb = bombs.getBoundsForIcon();
        Ellipse pb2 = bombs.getBoundsBombs(2);
        Ellipse pb3 = bombs.getBoundsBombs(4);
        Ellipse pb4 = bombs.getBoundsBombs(6);
        Bounds pla = laser.getBoundsForIcon();
        Rectangle beam = laser.getBoundsB();

        for (Tail aTailList : tailList) {
            Bounds Matotail = aTailList.getBounds();
            if (Matokuutio2.intersects(Matotail) && !shield.isActive(worm2) && !gameMode.equals("sp")) {
                if (worm2.getLife() > 1) {
                    shield.shield(worm2);
                    worm2.randomizeXY();
                    if (gameMode.equals("vs AI")) {
                        bot.BotTurnDown();
                    }
                }
                Life.loseLife(worm2);
            }
        }

        for (Tail aTailList2 : tailList2) {
            Bounds Matotail2 = aTailList2.getBounds();
            if (Matokuutio.intersects(Matotail2) && !shield.isActive(worm)) {
                if (worm.getLife() > 1) {
                    shield.shield(worm);
                    worm.randomizeXY();
                    worm.setDirectionAdv(0);
                    worm.setDirection(0);
                }
                Life.loseLife(worm);
            }
        }
        if (gameMode.equals("sp")) {
            for (int i = 3; i < tailList.size(); i++) {
                Bounds Matotail2 = tailList.get(i).getBounds();
                if (Matokuutio.intersects(Matotail2) && !shield.isActive(worm)) {
                    if (worm.getLife() > 1) {
                        shield.shield(worm);
                        worm.randomizeXY();
                        worm.setDirectionAdv(0);
                        worm.setDirection(0);
                    }
                    Life.loseLife(worm);
                }
            }
        }

        //mato 1 collisions
        if (s.intersects(Matokuutio)) {
            Music.snack.play();
            snack.randomizeIconLocation();
            worm.setPoints(worm.getPoints() + 100);
            spawnTail(1);
        }

        if (pf.intersects(Matokuutio)) {
            faster.faster(worm);
            powerUpCD();
        }

        if (ps.intersects(Matokuutio)) {
            slower.slower(worm, worm2);
            powerUpCD();
        }

        if (pr.intersects(Matokuutio)) {
            reverse.confuse(worm, worm2);
            powerUpCD();
        }

        if (pl.intersects(Matokuutio)) {
            Life.addLife(worm);
            powerUpCD();
        }

        if (psh.intersects(Matokuutio)) {
            shield.shield(worm);
            powerUpCD();
        }
        if (pb.intersects(Matokuutio)) {
            bombs.bombs(worm);
            bombs.bombZone();
            powerUpCD();
        }

        if (pb2.intersects(Matokuutio) || pb3.intersects(Matokuutio) || pb4.intersects(Matokuutio) && !shield.isActive(worm)) {
            bombs.damage(worm);
        }
        if (pla.intersects(Matokuutio)) {
            laser.onPickup(worm, worm2);
            beam = laser.getBoundsB();
            powerUpCD();
        }
        if (beam.intersects(Matokuutio) && !shield.isActive(worm)) {
            laser.damage(worm);
        }

        if (worm.getX() < 5 || worm.getX() > 760 || worm.getY() < 5 || worm.getY() > 550) {
            if (worm.getLife() > 1) {
                worm.randomizeXY();
                worm.setDirectionAdv(0);
                worm.setDirection(0);
            }
            Life.loseLife(worm);
        }

        //mato 2 collisions
        if (s.intersects(Matokuutio2)) {
            Music.snack.play();
            snack.randomizeIconLocation();
            worm2.setPoints(worm2.getPoints() + 100);
            spawnTail(2);
        }

        if (pf.intersects(Matokuutio2)) {
            faster.faster(worm2);
            powerUpCD();
        }

        if (ps.intersects(Matokuutio2)) {
            slower.slower(worm2, worm);
            powerUpCD();
        }

        if (pr.intersects(Matokuutio2)) {
            reverse.confuse(worm2, worm);
            powerUpCD();
        }

        if (pl.intersects(Matokuutio2)) {
            Life.addLife(worm2);
            powerUpCD();
        }

        if (psh.intersects(Matokuutio2)) {
            shield.shield(worm2);
            powerUpCD();
        }
        if (pb.intersects(Matokuutio2)) {
            bombs.bombs(worm2);
            bombs.bombZone();
            powerUpCD();
        }

        if (pb2.intersects(Matokuutio2) || pb3.intersects(Matokuutio2) || pb4.intersects(Matokuutio2) && !shield.isActive(worm2)) {
            bombs.damage(worm2);
        }
        if (pla.intersects(Matokuutio2)) {
            laser.onPickup(worm2, worm);
            beam = laser.getBoundsB();
            powerUpCD();
        }
        if (beam.intersects(Matokuutio2) && !shield.isActive(worm2)) {
            laser.damage(worm2);
        }
        if ((worm2.getX() < 5 || worm2.getX() > 760 || worm2.getY() < 5 || worm2.getY() > 550) && !gameMode.equals("sp")) {
            if (worm2.getLife() > 1) {
                worm2.randomizeXY();
                if (gameMode.equals("vs AI")) {
                    bot.BotTurnDown();
                } else {
                    worm2.setDirectionAdv(0);
                    worm2.setDirection(0);
                }
            }
            Life.loseLife(worm2);
        }
    }

    public void updateBoard() {
            
        checkCollisions();
        worm.move();
        worm.moveCont();
        worm2.move();
        worm2.moveCont();
        //tallennnetaan wormin coordinaatit yhteen 2D muuttujaan
        int x = worm.getX();
        int y = worm.getY();
        int x2 = worm2.getX();
        int y2 = worm2.getY();
        Point2D p = new Point2D(x, y);
        Point2D p2 = new Point2D(x2, y2);
        //Lisätään coortinaatit listan cordinates alkuun (0).
        //siirtää automaattisesti taulukon arvot yhden eteenpäin, 0->1

        coordinates.add(0, p);
        coordinates2.add(0, p2);

        //jos lista liian suuri poistetaan viimeinen
        if (coordinates.size() >= 10000) {
            coordinates.remove(coordinates.size() - 1);
        }

        if (coordinates2.size() >= 10000) {
            coordinates2.remove(coordinates2.size() - 1);
        }

        for (Tail aTailList : tailList) {
            int f = aTailList.getCoordinateInt();
            p = coordinates.get(f);
            x = (int) p.getX();
            y = (int) p.getY();
            aTailList.setX(x);
            aTailList.setY(y);
        }

        for (Tail aTailList2 : tailList2) {
            int f = aTailList2.getCoordinateInt();
            p2 = coordinates2.get(f);
            x2 = (int) p2.getX();
            y2 = (int) p2.getY();
            aTailList2.setX(x2);
            aTailList2.setY(y2);
        }

        if (worm.getLife()<=0 || worm2.getLife()<=0) {
            setIngame(false);
        }

        engine.setWormImage();

        //botti ja sen toiminta
        if (gameMode.equals("vs AI")) {
            bot.BlueAIBot();
        }

    }

    private void spawnTail(int n) {
        //tulee yksi Tail pala lisää
        switch (n) {
            case 1:
                tailNro++;
                tailList.add(tail = new Tail(tailNro * 8, 1));
                break;
            case 2:
                tailNro2++;
                tailList2.add(tail = new Tail(tailNro2 * 8, 2));
        }
    }

}