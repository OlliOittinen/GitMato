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
        worm.setPoints(worm.getPoints()+100);
        worm.setNopeus(3);
        Music.fasterPowerup.play();
        
        //säätää nopeuden väliaikseks
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                worm.setNopeus(2);
            }
        }, 5000); //aika (ms), joka odotetaan
    }
    
    public Faster() {
        init();
    }

}
