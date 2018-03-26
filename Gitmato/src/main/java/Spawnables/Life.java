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
public class Life extends AbstractSpawnables{
    
    public void Life(Worm worm) {
        worm.setPoints(worm.getPoints()+100);
        addLife(worm);
    }
    
    public Life() {
        init();
    }
    
    public static void addLife (Worm worm) {
        Music.addLife.play();
        worm.setLife(worm.getLife()+1);
    }
    
    public static void loseLife(Worm worm) {
        Music.loseLife.play();
        worm.setLife(worm.getLife()-1);
        worm.setShield(true);
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                worm.setShield(false);
                }
            }, 1000);
    }

}
