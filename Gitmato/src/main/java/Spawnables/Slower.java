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
public class Slower extends AbstractSpawnables{

    /**
     * Awards <code>Worm</code> object with points, slows down the other <code>Worm</code> .
     * Plays the corresponding music.
     * Uses States to achieve slow effect.
     * @param worm the <code>Worm</code>  to be awarded with points
     * @param worm2 the <code>Worm</code>  to be slowed down; the <code>Worm</code>  that has a slower speed temporarily
     * @see Model.WormStates.WormSlow
     */
    public void slower(Worm worm, Worm worm2) {
        Music.slowerPowerup.play();
        worm.setPoints(worm.getPoints()+100);
        if(!worm2.getShield()){
            worm2.slowerSpeed();
        }else{
            worm2.setShield(false);
        }
    }

    /**
     * Class constructor
     * @see AbstractSpawnables
     */
    public Slower() {
        init();
    }

}
