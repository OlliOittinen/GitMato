/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spawnables;

import Model.Worm;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Olli
 */
public class Laser extends AbstractDamagingSpawnables {

    private int xe2;
    private int ye2;
    private int xe3;
    private int ye3;
    private boolean horizontal = false;
    private Rectangle beam = new Rectangle(-1000, -1000, 1, 1);

    /**
     * Class constructor
     */
    public Laser() {
        init();
    }


    /**
     * On constructing this Object, hides the icon and horizontal and vertical lasers.
     */
    @Override
    public void init() {
        setX(-100);
        setY(-100);
        setX2(-1000);
        setY2(-1000);
        setX3(-1000);
        setY3(-1000);
    }

    /**
     * Awards the <code>Worm</code>  object with points, uses the second <code>Worm</code>'s location to place a laser.
     * Gets the second <code>Worm</code>s' x and y-coordinates and places a vertical or
     * a horizontal laser on top of these, based on pseudo-random modifier.
     * Plays corresponding music files on both collision with icon and when the laser is being fired.
     * @param worm the <code>Worm</code>  to be awarded with points
     * @param worm2 the <code>Worm</code>  to be targeted by a laser
     */
    public void onPickup(Worm worm, Worm worm2) {
        worm.setPoints(worm.getPoints() + 100);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int wormLocX = worm2.getX();
                int wormLocY = worm2.getY();

                //horisontaalinen vai vertikaalinen säde, random arvo 0...1
                double r = Math.random();
                if (r < 0.5) {
                    //vertical
                    setX2(wormLocX - 30);
                    setY2(0);
                    setBoundsB(wormLocX - 30, 0, 100, 600);
                    horizontal = false;
                } else {
                    //horizontal
                    setX3(0);
                    setY3(wormLocY - 30);
                    setBoundsB(0, wormLocY - 30, 800, 100);
                    horizontal = true;
                }

            }
        }, 1000); //aika (ms), joka odotetaan
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                lethal = true;
                Sound.Music.laserShot.play();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        hide();
                        lethal = false;
                    }
                }, 2000);
            }
        }, 1800); //aika (ms), joka odotetaanF

    }

    /**
     * Returns the rectangle laser beam
     * @return <code>Rectangle</code> object
     * @see Rectangle
     */
    public Rectangle getBoundsB() {
        return beam;
    }

    /**
     * Checks whether the laser should be vertical or horizontal.
     * @return boolean value
     */
    public boolean getHorizontal() {
        return horizontal;
    }

    /**
     * Gets the x-coordinate for vertical beam
     * @return integer value of the x-coordinate for the vertical beam
     */
    public int getX2() {
        return xe2;
    }

    /**
     * Gets the y-coordinate for vertical beam
     * @return integer value of the y-coordinate for the vertical beam
     */
    public int getY2() {
        return ye2;
    }

    /**
     * Gets the x-coordinate for horizontal beam
     * @return integer value of the x-coordinate for the horizontal beam
     */
    public int getX3() {
        return xe3;
    }

    /**
     * Gets the y-coordinate for horizontal beam
     * @return integer value of the y-coordinate for the horizontal beam
     */
    public int getY3() {
        return ye3;
    }

    /**
     * Sets the vertical laser's x-coordinate according to parameter x.
     * @param x integer value of the x-coordinate for the vertical beam.
     */
    private void setX2(int x) {
        this.xe2 = x;
    }

    /**
     * Sets the horizontal laser's x-coordinate according to parameter x.
     * @param x integer value of the x-coordinate for the horizontal beam.
     */
    private void setX3(int x) {
        this.xe3 = x;
    }

    /**
     * Sets the vertical laser's y-coordinate according to parameter y.
     * @param y integer value of the y-coordinate for the vertical beam.
     */
    private void setY2(int y) {
        this.ye2 = y;
    }

    /**
     * Sets the horizontal laser's y-coordinate according to parameter y.
     * @param y integer value of the y-coordinate for the horizontal beam.
     */
    private void setY3(int y) {
        this.ye3 = y;
    }

    /**
     * Sets the laser beam on map according to parameters.
     * @param x the x-coordinate of the upper left corner of this beam
     * @param y the y-coordinate of the upper left corner of this beam
     * @param w the width of this beam
     * @param h the height of this beam
     */
    private void setBoundsB(int x, int y, int w, int h){
        beam.setX(x);
        beam.setY(y);
        beam.setWidth(w);
        beam.setHeight(h);
    }

    /**
     * Gets the lethality boolean value for this beam
     * @return boolean for deadliness
     */
    public boolean getLethal() {
        return lethal;
    }

    /**
     * Hides the beam away from the user after it's finished displaying.
     */
    private void hide() {
        setX2(-1000);
        setY2(-1000);
        setX3(-1000);
        setY3(-1000);
        setBoundsB(-1000, -1000, 1, 1);
    }

}
