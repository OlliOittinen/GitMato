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

/**
 * @author maxki, Olli
 */
public class Life extends AbstractSpawnables {
    /**
     * Class constructor, calls on init();
     *
     * @see AbstractSpawnables
     */
    public Life() {
        init();
    }


    /**
     * Adds a life to the object (worm) that picked up the icon for this powerup.
     * Plays the corresponding music and awards the worm with points.
     *
     * @param worm the worm Object to be awarded with life and points
     */

    public static void addLife(Worm worm) {
        //add points to this worm
        worm.setPoints(worm.getPoints() + 100);
        //play the corresponding music
        Music.addLife.play();
        //add points to the one who picked up
        worm.setLife(worm.getLife() + 1);
    }


    /**
     * Removes a life from the object (worm).
     * Plays the corresponding music and sets a shield for this worm for 1000 milliseconds.
     * Static due to bombs and lasers.
     *
     * @param worm the worm that is to lose a life.
     * @see Bombs
     * @see Laser
     */
    //needs to be static so laser & bomb spawnables can access this
    public static void loseLife(Worm worm) {
        //check whether or not the shield is active; if not, this worm loses a life
        if (!worm.getShield()) {
            //play the corresponding music
            Music.loseLife.play(); //remove a life from this worm's life pool
            worm.setLife(worm.getLife() - 1);
            //give this worm a shield for a second to prevent instant loss of all lives
            worm.setShield(true);
            worm.setTransparencyChange(true);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    worm.setShield(false);
                    worm.setTransparencyChange(false);
                }
            }, 1000);
        }
    }

}
