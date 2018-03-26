/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spawnables;

import Model.Worm;
import Sound.Music;
import javafx.scene.image.Image;

import java.util.Timer;
import java.util.TimerTask;



/**
 *
 * @author Olli
 */
public class Slower extends AbstractSpawnables{

    public void slower(Worm worm, Worm worm2) {
        Music.slowerPowerup.play();
        worm.setPoints(worm.getPoints()+100);
        worm2.setNopeus(1);
        
        //säätää nopeuden väliaikseks
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                worm2.setNopeus(2);
            }
        }, 5000); //aika (ms), joka odotetaan
    }
    
    public Slower() {
        init();
    }

}
