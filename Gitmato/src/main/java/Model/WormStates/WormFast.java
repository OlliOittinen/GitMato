package Model.WormStates;

import Model.Worm;

import java.util.Timer;
import java.util.TimerTask;

public class WormFast extends WormState {

    //create a singleton instance of this class
    private static WormFast instance;

    private WormFast() {}

    /**
     * Class 'constructor'. Retrieves this class's instance.
     * This class is defined as a singleton, so it is only ever created once.
     * @return the instance of this class
     */
    public static WormFast getInstance() {
        if (instance == null) {
            instance = new WormFast();
        }
        return instance;
    }

    /**
     * Makes the <code>Worm</code> move quicker temporarily.
     * @param worm the <code>Worm</code>  that's supposed to move quicker
     */
    @Override
    public void action(Worm worm) {
        worm.setSpeed(worm.getSpeed()+2);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                worm.changeState(WormStateNormal.getInstance());
            }
        }, 5000);
    }

}
