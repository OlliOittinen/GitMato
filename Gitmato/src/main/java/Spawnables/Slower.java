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
public class Slower extends AbstractSpawnables{

    //requires both worms for correct application
    public void slower(Worm worm, Worm worm2) {
        //play the corresponding music
        Music.slowerPowerup.play();
        //add points to the one who picked up
        worm.setPoints(worm.getPoints()+100);

        //slow down the opposing worm temporarily
        worm2.setSpeed(worm2.getSpeed()-2);
        
        //säätää nopeuden väliaikseks
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //normalize the speed again
                worm2.setSpeed(worm2.getSpeed()+2);
            }
        }, 5000); //how long the powerup is in effect
    }
    
    public Slower() {
        init();
    }

}
