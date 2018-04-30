package Model.WormStates;

import Model.Worm;

import java.util.Timer;
import java.util.TimerTask;

public class WormConfuse extends WormState {

    //create a singleton instance of this class
    private static WormConfuse instance;

    private WormConfuse() {
    }

    /**
     * Class 'constructor'. Retrieves this class's instance.
     * This class is defined as a singleton, so it is only ever created once.
     *
     * @return the instance of this class
     */
    public static WormConfuse getInstance() {
        if (instance == null) {
            instance = new WormConfuse();
        }
        return instance;
    }


    /**
     * Makes this <code>Worm</code>  move in the opposing direction on every key press.
     * @param worm the <code>Worm</code> that's supposed to have its' movement reversed.
     */
    @Override
    public void action(Worm worm) {

        worm.setReverse(true);
        worm.setSpeed(worm.getSpeed() * -1);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                worm.setReverse(false);
                worm.changeState(WormStateNormal.getInstance());
            }
        }, 5000);
    }
}
