package Model.WormStates;

import Model.Worm;

import java.util.Timer;
import java.util.TimerTask;

public class WormSlow extends WormState {

    private static WormSlow instance = null;

    private WormSlow() {}

    public static WormSlow getInstance() {
        if (instance == null) {
            instance = new WormSlow();
        }
        return instance;
    }

    @Override
    public void slowerSpeed(Worm worm) {
        worm.setSpeed(worm.getSpeed()-2);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //normalize the speed again
                worm.normalSpeed();
                }
            }, 5000);
        }
}
