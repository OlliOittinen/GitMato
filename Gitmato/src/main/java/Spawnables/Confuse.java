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

    //requires both worms to set effects correctly
    public void confuse(Worm worm, Worm worm2) {
        //play the corresponding music
        Music.reverse.play();
        //add points to the one who picked up the icon
        worm.setPoints(worm.getPoints()+100);
        //set the speed of the opposing worm to be the opposite
        worm2.confuse();
    }

    //constructor calls the init() from superclass
    public Confuse() {
        init();
    }

}
