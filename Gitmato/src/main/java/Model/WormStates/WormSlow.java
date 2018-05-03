package Model.WormStates;

import Model.Worm;

import java.util.Timer;
import java.util.TimerTask;

public class WormSlow extends WormState {

    //create a singleton instance of this class
    private static WormSlow instance = null;

    private WormSlow() {}

    /**
     * Class 'constructor'. Retrieves this class's instance.
     * This class is defined as a singleton, so it is only ever created once.
     * @return the instance of this class
     */
    public static WormSlow getInstance() {
        if (instance == null) {
            instance = new WormSlow();
        }
        return instance;
    }

    /**
     * Slows down the <code>Worm</code>  temporarily.
     * @param worm the <code>Worm</code>  that is supposed to be slowed down
     */
    @Override
    public void action(Worm worm) {
        worm.setSpeed(worm.getSpeed()-2);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                worm.changeState(WormStateNormal.getInstance());
                }
            }, 5000);
        }
}
