/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spawnables;

import Model.Worm;
import Sound.Music;

/**
 *
 * @author Olli
 */

public class Faster extends AbstractSpawnables {

    /**
     * Makes this object (worm) move faster.
     * Uses Worm States to achieve this.
     * Also plays the corresponding music and awards the worm with points.
     * @param worm the worm that picked up this icon
     * @see Model.WormStates.WormFast
     */
    public void faster(Worm worm) {
        //add points to the worm who picked up
        worm.setPoints(worm.getPoints()+100);
        //play the corresponding music
        Music.fasterPowerup.play();
        //set the state of this worm to be faster
        worm.fasterSpeed();
    }
    
    public Faster() {
        init();
    }

}
