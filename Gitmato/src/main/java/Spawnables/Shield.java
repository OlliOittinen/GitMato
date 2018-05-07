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
 * @author maxki, Olli
 */
public class Shield extends AbstractSpawnables{

    /**
     * Class constructor
     *@see AbstractSpawnables
     */
    public Shield() {
        init();
    }

    /**
     * Awards the <code>Worm</code>  with points.
     * Plays the corresponding music and awards the <code>Worm</code>  with points.
     * Uses Worm State to apply shield effect.
     * @param worm <code>Worm</code>  to be awarded
     * @see Model.WormStates.WormShield
     */
    public void shield(Worm worm) {
        Music.shield.play();
        worm.setPoints(worm.getPoints()+100);
        worm.shield();
    }

    /**
     * Checks the boolean for this <code>Worm</code> 's shield effect.
     * @param worm the <code>Worm</code>  to be checked
     * @return boolean value of this <code>Worm</code>'s shield
     */
    public boolean isActive(Worm worm) {
        return worm.getShield();
    }
    
}
