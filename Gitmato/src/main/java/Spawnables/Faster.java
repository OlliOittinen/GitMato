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
     * Makes this <code>Worm</code> move faster.
     * Uses Worm States to achieve this.
     * Also plays the corresponding music and awards the <code>Worm</code>  with points.
     * @param worm the <code>Worm</code>  that picked up this icon
     * @see Model.WormStates.WormFast
     */
    public void faster(Worm worm) {
        worm.setPoints(worm.getPoints()+100);
        Music.fasterPowerup.play();
        worm.fasterSpeed();
    }

    /**
     * Class constructor
     * @see AbstractSpawnables
     */
    public Faster() {
        init();
    }

}
