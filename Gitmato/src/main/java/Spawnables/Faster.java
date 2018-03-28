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

public class Faster extends AbstractSpawnables {

    public void faster(Worm worm) {
        //add points to the worm who picked up
        worm.setPoints(worm.getPoints()+100);

        //add speed for this worm
        worm.setSpeed(worm.getSpeed()+2);
        //play the corresponding music
        Music.fasterPowerup.play();
        
        //adjusts speed to be temporary
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //after delay, reset the worm speed to be normal
                worm.setSpeed(worm.getSpeed()-2);
            }
        }, 5000); //delay in ms
    }
    
    public Faster() {
        init();
    }

}
