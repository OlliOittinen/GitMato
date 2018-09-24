package Model.WormStates;

import Model.Worm;
import Sound.Music;

import java.util.Timer;
import java.util.TimerTask;

public class WormShield extends WormState {

    //create a singleton instance of this class
    private static WormShield instance;

    private WormShield() {
    }

    /**
     * Class 'constructor'. Retrieves this class's instance.
     * This class is defined as a singleton, so it is only ever created once.
     *
     * @return the instance of this class
     */
    public static WormShield getInstance() {
        if (instance == null) {
            instance = new WormShield();
        }
        return instance;
    }

    /**
     * Shields this <code>Worm</code> temporarily.
     *
     * @param worm the <code>Worm</code>  that is to be shielded
     */
    @Override
    public void action(Worm worm) {
        worm.setShield(true);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Music.shieldpop.play();
                worm.setShield(false);
            }
        }, 10000);
    }
}
