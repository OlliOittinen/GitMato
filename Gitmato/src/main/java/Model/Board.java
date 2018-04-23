/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import GUI.Matopeli;
import Sound.Music;
import Spawnables.*;

import java.awt.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.TimerTask;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * @author maxki, Olli, Eero, Ged
 */
public class Board {

    private ArrayList<Worm> worms;
    private Worm worm;
    private Worm worm2;
    private Snack snack;
    private Faster faster;
    private Slower slower;
    private Confuse reverse;
    private Life HP;
    private Shield shield;
    private Bombs bombs;
    private Laser laser;
    private Cut cut;
    private String gameMode;
    private Matopeli GUI;
    private Bot bot;
    private DBConnection connection = new DBConnection();
    private boolean ingame;

    //Lista Tail paloista
    private ArrayList<Tail> tailList;
    private ArrayList<Tail> tailList2;
    private ArrayList<Spawnables> pickableList;
    private ArrayList<Point2D> coordinates;
    private ArrayList<Point2D> coordinates2;


    /**
     * Class constructor
     *
     * @param matopeli  the GUI/view that this model will send info to
     * @param gameMode the current game mode to be used
     */
    public Board(Matopeli matopeli, String gameMode) {

        this.GUI = matopeli;
        this.gameMode = gameMode;

        //alustetaan listat
        pickableList = new ArrayList<>();
        worms = new ArrayList<>();

        this.coordinates = new ArrayList<>();
        this.tailList = new ArrayList<>();

        this.coordinates2 = new ArrayList<>();
        this.tailList2 = new ArrayList<>();

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
        cut = new Cut();

        pickableList.add(snack);
        pickableList.add(faster);
        pickableList.add(slower);
        pickableList.add(reverse);
        pickableList.add(HP);
        pickableList.add(shield);
        pickableList.add(bombs);
        pickableList.add(laser);
        pickableList.add(cut);

        worms.add(worm = new Worm(1)); //lista worm olioista
        worms.add(worm2 = new Worm(2));
        tailList = worm.getTails();
        tailList2 = worm2.getTails();
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

    /**
     * Gets the game mode as a String
     *
     * @return the current game mode as a String
     */
    public String getGameMode() {
        return gameMode;
    }

    /**
     * Returns the first worm object; first player
     *
     * @return the worm object of the (first) player
     */
    public Worm getWorm() {
        return worm;
    }

    /**
     * Returns the second worm object; second player or bot
     *
     * @return the worm object of the second player or bot
     */
    public Worm getWorm2() {
        return worm2;
    }

    /**
     * Gets the entire tail list linked the first worm.
     *
     * @return the first worm objects' tail as an ArrayList
     */
    public ArrayList<Tail> getTailList() {
        return tailList;
    }

    /**
     * Gets the entire tail list linked to the second worm object.
     *
     * @return the second worm objects' tail as an ArrayList
     */
    public ArrayList<Tail> getTailList2() {
        return tailList2;
    }

    /**
     * Retrieves all the power-ups.
     *
     * @return All the power-ups as an ArrayList. The power-ups are always in the same order.
     */
    public ArrayList<Spawnables> getPickableList() {
        return pickableList;
    }

    /**
     * Retrieves all current worm objects as an ArrayList.
     *
     * @return All the worms used by the game mode.
     */
    public ArrayList getWorms() {
        return worms;
    }

    /**
     * Checks whether or not this game mode is active; is the game still being played.
     *
     * @return true if game is being played, false if not.
     */
    public boolean isIngame() {
        return ingame;
    }

    /**
     * Tells the model to either keep drawing the game or stop.
     *
     * @param ingame true if game is still wanted to keep going, false if not
     */
    public void setIngame(boolean ingame) {
        this.ingame = ingame;
    }

    private void powerUpCD() {
        //go through pickable list and hide powerups, start at 1 because 0th index is snack
        for (int i = 1; i < pickableList.size(); i++) {
            pickableList.get(i).init();
        }
        for (int i = 1; i < 7; i++) {
            bombs.setXBombs(i, -1000);
            bombs.setYBombs(i, -1000);
        }

        java.util.Timer timer2 = new java.util.Timer();
        timer2.schedule(new TimerTask() {

            @Override
            public void run() {
                //snack is first index and we dont need to randomize it
                int n = 8;//(int) (Math.random() * (pickableList.size() - 1) + 1);
                pickableList.get(n).randomizeIconLocation();

            }
        }, 5000); //aika (ms), joka odotetaan
    }

    /**
     * Submits the score to the database, retrieves current scores from the database.
     * Tells the GUI to form a high score table based on the current scores.
     *
     * @param score the winning worm's score
     * @param name  name of the winning worm to be submitted to the database
     * @see DBConnection
     */
    public void submitHighscore(int score, String name) {
        if (name != null) {
            connection.submitScore(score, name, gameMode);
            ArrayList<String> scores = connection.showHighscore(gameMode);
            GUI.createHighscoreTableScene(scores);
        }

    }

    private void checkCollisions() {

        //Bounds returns a square object
        Bounds Matokuutio = worm.getBounds();
        Bounds Matokuutio2 = worm2.getBounds();

        Bounds s = snack.getBoundsForIcon();
        Bounds pf = faster.getBoundsForIcon();
        Bounds ps = slower.getBoundsForIcon();
        Bounds pr = reverse.getBoundsForIcon();
        Bounds pl = HP.getBoundsForIcon();
        Bounds psh = shield.getBoundsForIcon();
        Bounds pb = bombs.getBoundsForIcon();
        Circle pb2 = bombs.getBoundsBombs(2);
        Circle pb3 = bombs.getBoundsBombs(4);
        Circle pb4 = bombs.getBoundsBombs(6);
        Bounds pla = laser.getBoundsForIcon();
        Rectangle beam = laser.getBoundsB();
        Bounds sc = cut.getBoundsForIcon();

        for (Tail aTailList : tailList) {
            Bounds Matotail = aTailList.getBounds();
            if (Matokuutio2.intersects(Matotail) && !shield.isActive(worm2) && !gameMode.equals("sp")) {
                if (worm2.getLife() > 1) {
                    worm2.turnAround();
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
                    worm.turnAround();
                }
                Life.loseLife(worm);
            }
        }
        if (gameMode.equals("sp")) {
            for (int i = 3; i < tailList.size(); i++) {
                Bounds Matotail2 = tailList.get(i).getBounds();
                if (Matokuutio.intersects(Matotail2) && !shield.isActive(worm)) {
                    if (worm.getLife() > 1) {
                        worm.turnAround();
                    }
                    Life.loseLife(worm);
                }
            }
        }

        //mato 1 collisions
        if (s.intersects(Matokuutio)) {
            Music.snack.play();
            snack.randomizeIconLocation(this);
            worm.addTail();
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
                worm.turnAround();
            }
            Life.loseLife(worm);
        }

        if (sc.intersects(Matokuutio)) {
            cut.cut(worm, worm2);
            powerUpCD();
        }

        //mato 2 collisions
        if (s.intersects(Matokuutio2)) {
            Music.snack.play();
            snack.randomizeIconLocation(this);
            worm2.addTail();
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
                worm2.turnAround();
                if (gameMode.equals("vs AI")) {
                    bot.BotTurnDown();
                }
            }
            Life.loseLife(worm2);
        }

        if (sc.intersects(Matokuutio2)) {
            cut.cut(worm2, worm);
            powerUpCD();
        }
    }

    /**
     * Constantly updates itself of the current situation.
     */
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

        updateTailCoordinates(tailList, coordinates);
        updateTailCoordinates(tailList2, coordinates2);

        if (worm.getLife() <= 0 || worm2.getLife() <= 0) {
            setIngame(false);
        }

        GUI.setWormImage();

        //botti ja sen toiminta
        if (gameMode.equals("vs AI")) {
            bot.runBotRun();
        }

    }

    private void updateTailCoordinates(ArrayList<Tail> taillist, ArrayList<Point2D> coordinates) {
        for (Tail tail : taillist) {
            int f = tail.getCoordinateInt();
            Point2D p = coordinates.get(f);
            int x = (int) p.getX();
            int y = (int) p.getY();
            tail.setX(x);
            tail.setY(y);
        }
    }

    /**
     * Getter for database connection
     *
     * @return reference to database connection object
     */
    public Connection getConnection() {
        return connection.getCon();
    }
}