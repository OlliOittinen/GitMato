package Model.WormStates;

import Model.Worm;

import java.util.Timer;
import java.util.TimerTask;

public class WormFast extends WormState {

    //create a singleton instance of this class
    private static WormFast instance;

    private WormFast() {}

    public static WormFast getInstance() {
        if (instance == null) {
            instance = new WormFast();
        }
        return instance;
    }

    //override abstraction
    @Override
    public void action(Worm worm) {
        //set speed of this worm to be higher
        worm.setSpeed(worm.getSpeed()+2);

        //create a new timer
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //normalize the speed again
                worm.changeState(WormStateNormal.getInstance());
            }
        }, 5000); //after this delay (ms)
    }

}
