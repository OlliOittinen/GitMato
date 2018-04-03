package Model.WormStates;

import Model.Worm;

import java.util.Timer;
import java.util.TimerTask;

public class WormShield extends WormState{

    //create a singleton instance of this class
    private static WormShield instance;

    private WormShield() {}

    /**
     * Class 'constructor'. Retrieves this class's instance.
     * This class is defined as a singleton, so it is only ever created once.
     * @return the instance of this class
     */
    public static WormShield getInstance() {
        if (instance == null) {
            instance = new WormShield();
        }
        return instance;
    }

    /**
     * Shields this worm object temporarily.
     * @param worm the worm that is to be shielded
     */
    @Override
    public void action(Worm worm) {
        //set the shield for this worm to be true
        worm.setShield(true);

        //create timer
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //set shield to be false again so no permanent shielding
                worm.setShield(false);
            }
        }, 5000); //after this delay (in ms)
    }
}
