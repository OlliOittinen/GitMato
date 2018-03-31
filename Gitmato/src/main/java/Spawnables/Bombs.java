/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spawnables;

import Model.Worm;
import Sound.Music;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.image.Image;
import javafx.scene.shape.*;

/**
 *
 * @author maxki, Olli, Eero
 */
public class Bombs extends AbstractSpawnables{

    private int x2, y2, x3, y3, x4, y4, x5, y5, x6, y6, x7, y7;

    private Image image, image2, image3;

    private boolean lethal = false;

    private int xlist[] = new int[]{x, x2, x3, x4, x5, x6, x7};
    private int ylist[] = new int[]{y, y2, y3, y4, y5, y6, y7};

    /**
     * Awards the object with 100 points.
     *
     * @param worm the object that is awarded points
     */
    public void bombs(Worm worm) {
        worm.setPoints(worm.getPoints() + 100);
    }

    /**
     * Checks if this object should lose a life.
     * Randomizes the worm's location on the map and sets it's current direction (facing of) to 0.
     * In other words, randomizes this Object's location and completely halts its movement forcibly.
     * @param worm the Worm object that is being checked
     */
    public void damage(Worm worm) {
        if (lethal) {
            if (worm.getLife() > 1) {
                worm.randomizeXY();
                worm.setDirectionAdv(0);
                worm.setDirection(0);
            }
            Life.loseLife(worm);
        }
    }

    /**
     * Class constructor, calls on init();
     */
    public Bombs() {
        init();
    }

    /**
     * Hides the icon and targeting ellipses - along with the actual damaging zones - away from the user's vision.
     * This method is only called once, during the initialization process.
     */
    @Override
    public void init() {
        setX(-100);
        setY(-100);
        for (int i = 0; i < xlist.length; i++) {
            setXBombs(i, -1000);
            setYBombs(i, -1000);
        }
    }

    /**
     * Handles the bomb zones that are displayed.
     * Firstly, it randomizes locations for the targets to appear shortly.
     * Secondly, it randomizes locations for three targets using this class's private integer lists, and displays them.
     * Thirdly, places the actual damaging areas on top of the previously randomized locations,
     * removes the targets from the map, and displays the damaging areas.
     */
    public void bombZone() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //arpoo kentältä 3 sijaintia, johon pommit laitetaan
                randomizeXYBombs();
            }
        }, 1000); //aika (ms), joka odotetaan ENNEN targetteja
        //uusi ajastin pommeille ja targeteille
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Music.bombs.play();
                //laitetaan targetit kohdalleen
                for (int i = 2; i < xlist.length; i = i + 2) {
                    setXBombs(i, xlist[i - 1]);
                    setYBombs(i, ylist[i - 1]);
                }
                //piilotetaan targetit kentältä
                for (int i = 1; i <= 5; i = i + 2) {
                    setXBombs(i, -1000);
                    setYBombs(i, -1000);
                }
                //laitetaan targettien/pommien paikat kuolettaviksi
                lethal = true;
                //uusi ajastin
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        //piilota pommit kentältä
                        for (int i = 2; i <=6; i = i + 2) {
                            setXBombs(i, -1000);
                            setYBombs(i, -1000);
                        }
                    }
                }, 1000000000); //tulialueet
            }
        }, 2500); //targetit
    }

    /**
     * Returns the ellipse used by bomb zone.
     * @param n the index of the target or bomb
     * @return a new Ellipse with predetermined draw size
     * @see Ellipse
     */
    public Ellipse getBoundsBombs(int n) {
        return new Ellipse(xlist[n] + 100, ylist[n] + 100, 100, 100);
    }

    /**
     * Returns the x-coordinate of this target or bomb.
     * @param n index of the wanted target or bomb
     * @return the x-coordinate of this bomb or target
     */
    public int getXBombs(int n) {
        return xlist[n];
    }

    /**
     * Returns the y-coordinate of this target or bomb.
     * @param n index of the wanted target or bomb
     * @return the y-coordinate of this bomb or target
     */
    public int getYBombs(int n) {
        return ylist[n];
    }

    /**
     * Sets x-coordinate of this target or bomb.
     * @param n the index of the wanted target or bomb
     * @param value the x-coordinate of bomb or target at index n
     */
    public void setXBombs(int n, int value) {
        xlist[n] = value;
    }

    /**
     * Sets y-coordinate of this target or bomb.
     * @param n the index of the wanted target or bomb
     * @param value the y-coordinate of bomb or target at index n
     */
    public void setYBombs(int n, int value) {
        ylist[n] = value;
    }

    /**
     * Randomizes the locations for the targets.
     * Uses Math.random() to randomize.
     */
    public void randomizeXYBombs() {
        for (int i = 1; i < xlist.length; i+= 2) {
            setXBombs(i, (int) (Math.random() * 600));
            setYBombs(i, (int) (Math.random() * 400));
        }
    }
}
