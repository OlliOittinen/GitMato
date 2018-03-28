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

    public Shield() {
        init();
    }
    
    public void shield(Worm worm, int time) {
        //play the corresponding music
        Music.shield.play();
        //add points to the one who picked up the powerup
        worm.setPoints(worm.getPoints()+100);
        //set private boolean to be true
        worm.setShield(true);

        //after this timer, set the boolean as false again
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                worm.setShield(false);
            }
        }, time); //aika (ms), joka odotetaan
    }

    public boolean isActive(Worm worm) {
        return worm.getShield(worm);
    }
    
}
