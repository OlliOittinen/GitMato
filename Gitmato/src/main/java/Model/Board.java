/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import GUI.Matopeli;
import Sound.Music;
import Spawnables.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.TimerTask;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * @author maxki, Olli, Eero, Ged
 */
public class Board {

    private ArrayList<Worm> worms;
    private Worm worm;
    private Worm worm2;
    private Tail tail;
    private Tail tail2;
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
    private Matopeli engine;
    private Bot bot;
    private DBConnection connection = new DBConnection();
    private boolean ingame;

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
    private int x;
    private int y;
    private int x2;
    private int y2;


    /**
     * Class constructor
     *
     * @param e        the engine/GUI that this model will send info to
     * @param gameMode the current game mode to be used
     */
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
     * Sets the game mode to be used.
     *
     * @param gameMode the game mode to be played
     */
    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
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
     * Used by the bot class.
     *
     * @return the number of tail pieces for the second worm; how many snacks has the second worm gathered.
     */
    public int getTailNro2() {
        return tailNro2;
    }

    public int getTailNro() {
        return tailNro;
    }

    public void setTailNro(int i){
        tailNro = i;
    }

    public void setTailNro2(int i){
        tailNro2 = i;
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
        //go trough pickable list and hide powerups
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
                int n = (int) (Math.random() * (pickableList.size() - 1) + 1);
                pickableList.get(n).randomizeIconLocation();

            }
        }, 5000); //aika (ms), joka odotetaan
    }

    /**
     * Submits the score to the database, retrieves current scores from the database.
     * Tells the engine to form a high score table based on the current scores.
     *
     * @param score the winning worm's score
     * @param name  name of the winning worm to be submitted to the database
     * @see DBConnection
     */
    public void submitHighscore(int score, String name) {
        if (name != null) {
            connection.submitScore(score, name, gameMode);
            ArrayList<String> scores = connection.showHighscore(gameMode);
            engine.createHighscoreTableScene(scores);
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
                worm.turnAround();
            }
            Life.loseLife(worm);
        }

        if (sc.intersects(Matokuutio)) {
            cut.cut(this, 2);
            powerUpCD();
        }

        //mato 2 collisions
        if (s.intersects(Matokuutio2)) {
            Music.snack.play();
            snack.randomizeIconLocation(this);
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
                worm2.turnAround();
                if (gameMode.equals("vs AI")) {
                    bot.BotTurnDown();
                }
            }
            Life.loseLife(worm2);
        }

        if (sc.intersects(Matokuutio2)) {
            cut.cut(this, 1);
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
        x = worm.getX();
        y = worm.getY();
        x2 = worm2.getX();
        y2 = worm2.getY();
        p = new Point2D(x, y);
        p2 = new Point2D(x2, y2);
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

        setTailCoordinates(tailList, coordinates);
        setTailCoordinates(tailList2, coordinates2);

        if (worm.getLife() <= 0 || worm2.getLife() <= 0) {
            setIngame(false);
        }

        engine.setWormImage();

        //botti ja sen toiminta
        if (gameMode.equals("vs AI")) {
            bot.runBotRun();
        }

    }

    private void setTailCoordinates(ArrayList<Tail> taillist, ArrayList<Point2D> coordinates) {
        for (Tail tail : taillist) {
            int f = tail.getCoordinateInt();
            p = coordinates.get(f);
            x = (int) p.getX();
            y = (int) p.getY();
            tail.setX(x);
            tail.setY(y);
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

    /**
     * Getter for database connection
     * @return reference to database connection object
     */
    public Connection getConnection() {
        return connection.getCon();
    }
}