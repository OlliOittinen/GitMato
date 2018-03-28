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
        worm2.setSpeed(worm2.getSpeed()*-1);

        //set the boolean for drawing the effect on top of the worm to be true
        worm2.setReverse(true);

        //sets this powerup to be temporary
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //after the delay, set the speed to be opposing of opposing (normal) again
                worm2.setSpeed(worm2.getSpeed()*-1);
                //set the boolean false again, so don't draw the effect anymore
                worm2.setReverse(false);
            }
        }, 5000); //this marks the delay ie. the time after all the stuff under timer.schedule is done
    }

    //constructor calls the init() from superclass
    public Confuse() {
        init();
    }

}
