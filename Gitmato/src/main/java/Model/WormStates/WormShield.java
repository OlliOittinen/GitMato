package Model.WormStates;

import Model.Worm;

import java.util.Timer;
import java.util.TimerTask;

public class WormShield extends WormState{

    private static WormShield instance;

    private WormShield() {}

    public static WormShield getInstance() {
        if (instance == null) {
            instance = new WormShield();
        }
        return instance;
    }

    @Override
    public void action(Worm worm) {
        worm.setShield(true);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                worm.setShield(false);
            }
        }, 5000);
    }
}
