package Spawnables;

import Model.Worm;
import Sound.Music;

import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @author Max
 */
public class Switcher extends AbstractSpawnables{

    /**
     * Awards worm object with points and changes positions
     * @param worm the worm to be awarded with points
     * @param worm2 the worm that switches positions with the original worm
     */
    //requires both worms for correct application
    public void switcher(Worm worm, Worm worm2) {
        int x;
        int y;
        //add points to the one who picked up
        worm.setPoints(worm.getPoints()+100);
        if(!worm2.getShield()){
            worm.setShield(true);
            worm2.setShield(true);

            x = worm.getX();
            y = worm.getY();

            worm.setX(worm2.getX());
            worm.setY(worm2.getY());

            worm2.setX(x);
            worm2.setY(y);



            //create timer
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    //set shield to be false again so no permanent shielding
                    worm2.setShield(false);
                    worm.setShield(false);
                }
            }, 2000);
        }else{
            //pop goes the bubble
            worm2.setShield(false);
        }

    }

    /**
     * Class constructor, calls on init();
     * @see AbstractSpawnables
     */
    public Switcher() {
        init();
    }

}