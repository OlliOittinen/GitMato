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
 *
 * @author maxki, Olli
 */
public class Shield extends AbstractSpawnables{

    /**
     * Class constructor, calls on init();
     *@see AbstractSpawnables
     */
    public Shield() {
        init();
    }

    /**
     * Awards the worm object with points.
     * Plays the corresponding music and awards the worm with points.
     * Uses Worm State to apply shield effect.
     * @param worm the worm to be awarded
     * @see Model.WormStates.WormShield
     */
    public void shield(Worm worm) {
        //play the corresponding music
        Music.shield.play();
        //add points to the one who picked up the powerup
        worm.setPoints(worm.getPoints()+100);
        //call on state change for the worm
        worm.shield();
    }

    /**
     * Checks the boolean for this worm's shield effect.
     * @param worm the worm object to be checked
     * @return boolean value of this worm's shield
     */
    public boolean isActive(Worm worm) {
        return worm.getShield();
    }
    
}
