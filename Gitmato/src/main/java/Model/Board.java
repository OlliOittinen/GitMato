/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import GUI.Matopeli;
import Spawnables.*;
import java.util.ArrayList;
import java.util.List;
import Controller.PlayerController;
import java.util.Optional;
import java.util.TimerTask;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.control.TextInputDialog;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Ellipse;

/**
 *
 * @author maxki
 */
public final class Board {

    private static List<Worm> worms;
    private final int DELAY = 10;

    //Lista Tail paloista
    private final List<Tail> tailList;
    private final List<Tail> tailList2;
    private final List<Spawnables> pickableList;
    private final List<Point2D> coordinates;
    private final List<Point2D> coordinates2;
    // Wormin locaatio muuttujat:
    Point2D p;
    Point2D p2;// coordinaatit

    private Worm worm;
    private Worm worm2;
    private PlayerController control;
    private Tail tail;
    private Tail tail2;

    private Timeline timer;
    private Snack snack;
    private Faster faster;
    private Slower slower;
    private Confuse reverse;
    private Life HP;
    private Shield shield;
    private Bombs bombs;
    private Laser laser;

    public boolean isIngame() {
        return ingame;
    }

    public void setIngame(boolean ingame) {
        this.ingame = ingame;
    }

    private boolean ingame;
    private main.java.Model.Bot bot;

    //pidetään lukua kuinka monta Tail objektia on.
    private int tailNro = 0;
    private int tailNro2 = 0;
    private int x, y;
    private int x2, y2;

    public String getPelimoodi() {
        return pelimoodi;
    }

    private String pelimoodi = "versus";
    private Matopeli engine;
    private Highscore hscore = new Highscore();
    private int score;
    DBConnection connection = new DBConnection();
    private long currentTime = 0; // nykyinen aika (ms)
    private long previousTime = 0; // viime framen aika (ms)
    private double timeCounter = 0; // aikalaskuri (sec)
    private int frameCounter = 0;
    private double theRealFpsCounter = 0; // näyttää jatkuvasti oikean fps:n

    public List<Spawnables> getPickableList() {
        return pickableList;
    }

    public List<Point2D> getCoordinates() {
        return coordinates;
    }

    public List<Point2D> getCoordinates2() {
        return coordinates2;
    }

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

    public Board(Matopeli e, String pelimoodi) {
        this.engine = e;
        this.pelimoodi = pelimoodi;

        //alustetaan listat
        pickableList = new ArrayList<>();
        Board.worms = new ArrayList<>();

        this.coordinates = new ArrayList<>();
        this.tailList = new ArrayList<>();
        this.p = new Point2D(0, 0);

        this.coordinates2 = new ArrayList<>();
        this.tailList2 = new ArrayList<>();
        this.p2 = new Point2D(0, 0);

        initBoard();

    }

    public static List getWorms() {
        return worms;
    }

    private void initBoard() {
        //TODO: Tähän täytyy tehdä kaikki mahdolliset pelimuodot

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

        ingame = true;

        control = new PlayerController(); //
        control.updateWorms(); // Worms-lista liitetään playercontrolleriin
        control.yksinPeli(pelimoodi);

        if (pelimoodi == "vs AI") {
            bot.BotTurnDown();
        }
        if (pelimoodi == "sp") {
            worm2.setX(-1000);
            worm2.setY(-2000); //läpäl
            worm.setLife(1);
        }

        if (pelimoodi != "sp") {
            powerUpCD(); //piilottaa powerupit alussa
        }
    }

    public void restartGame() {
        if (!ingame) {
            snack.init();
            ingame = true;

            worms.clear();

            worms.add(worm = new Worm(1)); //lista worm olioista
            if (pelimoodi != "sp") {

                worms.add(worm2 = new Worm(2));
                powerUpCD();
                coordinates2.clear();
                tailList2.clear();
                tailNro2 = 0;
            }

            control.updateWorms();

            coordinates.clear();

            tailList.clear();

            tailNro = 0;

            Sound.Music.sound1.loop();
            if (pelimoodi == "vs AI") {
                bot.BotTurnDown();
            }

            if (pelimoodi == "sp") {
                worm2.setX(-1000);
                worm2.setY(-2000);
                worm.setLife(1);
            }
        }

    }

    private void inGame() {
        if (!ingame) {
            timer.stop();
        }
    }

    public void checkCollisions() {
        Bounds Matokuutio = worm.getBounds();
        Bounds Matokuutio2 = worm2.getBounds();

        Bounds s = snack.getBounds();
        Bounds pf = faster.getBounds();
        Bounds ps = slower.getBounds();
        Bounds pr = reverse.getBounds();
        Bounds pl = HP.getBounds();
        Bounds psh = shield.getBounds();
        Bounds pb = bombs.getBounds();
        Ellipse pb2 = bombs.getBoundsBombs(2);
        Ellipse pb3 = bombs.getBoundsBombs(4);
        Ellipse pb4 = bombs.getBoundsBombs(6);
        Bounds pla = laser.getBounds();
        Rectangle beam = laser.getBoundsB();

        for (int i = 0; i < tailList.size(); i++) {
            Bounds Matotail = tailList.get(i).getBounds();
            if (Matokuutio2.intersects(Matotail) && !shield.isActive(worm2) && pelimoodi != "sp") {
                if (worm2.getLife() > 1) {
                    shield.shield(worm2, 50);
                    worm2.randomizeXY();
                    if (pelimoodi == "vs AI") {
                        bot.BotTurnDown();
                    }

                }
                Life.loseLife(worm2);
            }
        }

        for (int i = 0; i < tailList2.size(); i++) {
            Bounds Matotail2 = tailList2.get(i).getBounds();
            if (Matokuutio.intersects(Matotail2) && !shield.isActive(worm)) {
                if (worm.getLife() > 1) {
                    shield.shield(worm, 50);
                    worm.randomizeXY();
                    worm.setSuuntaAdv(0);
                    worm.setSuunta(0);
                }
                Life.loseLife(worm);
            }
        }
        if (pelimoodi == "sp") {
            for (int i = 2; i < tailList.size(); i++) {
                Bounds Matotail2 = tailList.get(i).getBounds();
                if (Matokuutio.intersects(Matotail2) && !shield.isActive(worm)) {
                    if (worm.getLife() > 1) {
                        shield.shield(worm, 50);
                        worm.randomizeXY();
                        worm.setSuuntaAdv(0);
                        worm.setSuunta(0);
                    }
                    Life.loseLife(worm);
                }
            }
        }

        //mato 1 collisions
        if (s.intersects(Matokuutio)) {
            snack.randomizePowerUpLocation();
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
            HP.Life(worm);
            powerUpCD();
        }

        if (psh.intersects(Matokuutio)) {
            shield.shield(worm, 10000);
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
                worm.setSuuntaAdv(0);
                worm.setSuunta(0);
            }
            Life.loseLife(worm);
            worm.setPoints(worm.getPoints() - 100);
        }

        //mato 2 collisions
        if (s.intersects(Matokuutio2)) {
            snack.randomizePowerUpLocation();
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
            HP.Life(worm2);
            powerUpCD();
        }

        if (psh.intersects(Matokuutio2)) {
            shield.shield(worm2, 10000);
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
        if ((worm2.getX() < 5 || worm2.getX() > 760 || worm2.getY() < 5 || worm2.getY() > 550) && pelimoodi != "sp") {
            if (worm2.getLife() > 1) {
                worm2.randomizeXY();
                if (pelimoodi == "vs AI") {
                    bot.BotTurnDown();
                } else {
                    worm2.setSuuntaAdv(0);
                    worm2.setSuunta(0);
                }

            }
            Life.loseLife(worm2);
            worm2.setPoints(worm2.getPoints() - 100);
        }
    }

    private void spawnTail(int n) {
        //tulee yksi Tail pala lisää
        switch (n) {
            case 1:
                tailNro++;
                tailList.add(tail = new Tail(tailNro * 15, 1));
                break;
            case 2:
                tailNro2++;
                tailList2.add(tail = new Tail(tailNro2 * 15, 2));
        }
    }

    public void powerUpCD() {

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
                        shield.randomizePowerUpLocation();
                        break;
                    case 1:
                        faster.randomizePowerUpLocation();
                        break;
                    case 2:
                        slower.randomizePowerUpLocation();
                        break;
                    case 3:
                        reverse.randomizePowerUpLocation();
                        break;
                    case 4:
                        HP.randomizePowerUpLocation();
                        break;
                    case 5:
                        bombs.randomizePowerUpLocation();
                        break;
                    case 6:
                        laser.randomizePowerUpLocation();
                        break;
                }

            }
        }, 5000); //aika (ms), joka odotetaan
    }

    public void yksinpeliTrue() {
        this.pelimoodi = "sp";
    }

    public void submitHighscore() {
        if (!ingame) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    TextInputDialog dialog = new TextInputDialog("Type your name here!");
                    dialog.setTitle("Highscore");
                    dialog.setHeaderText("Submit your highscore!\n " + score);
                    dialog.setContentText("Please enter your name:");

                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()) {
                        hscore.setHighscore(score);
                        hscore.setName(result.get());
                        connection.submitScore(hscore.getHighscore(), hscore.getName(), pelimoodi);
                        connection.showHighscore(pelimoodi);

                    }
                }
            });
        }

    }


    public double FPS() {
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
        return theRealFpsCounter;
    }

    public List<Tail> getTailList() {
        return tailList;
    }

    public List<Tail> getTailList2() {
        return tailList2;
    }
}