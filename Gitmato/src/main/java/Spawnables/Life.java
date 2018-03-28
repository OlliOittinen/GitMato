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
    
    public Life() {
        init();
    }

    //needs to be static so laser & bomb spawnables can access this
    public static void addLife (Worm worm) {
        //add points to this worm
        worm.setPoints(worm.getPoints()+100);
        //play the corresponding music
        Music.addLife.play();
        //add points to the one who picked up
        worm.setLife(worm.getLife()+1);
    }

    //needs to be static so laser & bomb spawnables can access this
    public static void loseLife(Worm worm) {
        //play the corresponding music
        Music.loseLife.play();
        //remove a life from this worm's life pool
        worm.setLife(worm.getLife()-1);
        //give this worm a shield for a second to prevent instant loss of all lives
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
