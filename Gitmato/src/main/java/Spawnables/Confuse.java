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
 * @author Olli
 */
public class Confuse extends AbstractSpawnables {


    /**
     * Sets the enemy worm to move in the opposite direction.
     * Uses Worm States to achieve this.
     * @param worm the object (worm) that picked up (collided with) the icon for this powerup
     * @param worm2 the object (worm) that is to move in the opposite direction
     * @see Model.WormStates.WormConfuse
     */
    public void confuse(Worm worm, Worm worm2) {
        Music.reverse.play();
        worm.setPoints(worm.getPoints()+100);
        if(!worm2.getShield()){
            worm2.confuse();
        }else{
            worm2.setShield(false);
        }
    }

    /**
     * Class constructor, calls on init();
     * @see AbstractSpawnables
     */
    //constructor calls the init() from superclass
    public Confuse() {
        init();
    }

}
