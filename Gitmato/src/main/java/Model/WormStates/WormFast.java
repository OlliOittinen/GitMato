package Model.WormStates;

import Model.Worm;

import java.util.Timer;
import java.util.TimerTask;

public class WormFast extends WormState {

    private static WormFast instance;

    private WormFast() {}

    public static WormFast getInstance() {
        if (instance == null) {
            instance = new WormFast();
        }
        return instance;
    }

    @Override
    public void action(Worm worm) {
        worm.setSpeed(worm.getSpeed()+2);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //normalize the speed again
                worm.changeState(WormStateNormal.getInstance());
            }
        }, 5000);
    }

}
