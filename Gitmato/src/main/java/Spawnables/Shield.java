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

import javafx.scene.image.Image;

/**
 *
 * @author maxki
 */
public class Shield extends AbstractSpawnables{

    public Shield() {
        init();
    }
    
    public void shield(Worm worm, int time) {
        Music.shield.play();
        worm.setPoints(worm.getPoints()+100);
        worm.setShield(true);
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
