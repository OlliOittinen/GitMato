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

    //requires both worms for correct application
    public void slower(Worm worm, Worm worm2) {

        //play the corresponding music
        Music.slowerPowerup.play();
        //add points to the one who picked up
        worm.setPoints(worm.getPoints()+100);
        //change state of the opposing worm
        worm2.slowerSpeed();
    }
    
    public Slower() {
        init();
    }

}
