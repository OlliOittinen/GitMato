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
     * Class constructor
     *
     * @see AbstractSpawnables
     */
    public Life() {
        init();
    }


    /**
     * Adds a life to the <code>Worm</code>  that picked up the icon for this power-up.
     * Plays the corresponding music and awards the worm with points.
     * @param worm the <code>Worm</code>  to be awarded with life and points
     */

    public static void addLife(Worm worm) {
        worm.setPoints(worm.getPoints() + 100);
        Music.addLife.play();
        worm.setLife(worm.getLife() + 1);
    }


    /**
     * Removes a life from the <code>Worm</code> .
     * Plays the corresponding music and sets a shield for this <code>Worm</code>  for 1000 milliseconds.
     * Static due to AbstractDamagingSpawnables.
     * @param worm the <code>Worm</code>  that is to lose a life.
     * @see AbstractDamagingSpawnables
     */

    //needs to be static so laser & bomb spawnables can access this
    public static void loseLife(Worm worm) {
        if (!worm.getShield()) {
            Music.loseLife.play();
            worm.setLife(worm.getLife() - 1);
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
