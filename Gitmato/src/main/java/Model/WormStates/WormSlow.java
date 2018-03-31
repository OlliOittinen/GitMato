package Model.WormStates;

import Model.Worm;

import java.util.Timer;
import java.util.TimerTask;

public class WormSlow extends WormState {

    //create a singleton instance of this class
    private static WormSlow instance = null;

    private WormSlow() {}

    public static WormSlow getInstance() {
        if (instance == null) {
            instance = new WormSlow();
        }
        return instance;
    }

    //override abstraction in superclass
    @Override
    public void action(Worm worm) {
        //set speed of this worm to be less
        worm.setSpeed(worm.getSpeed()-2);

        //create a timer, after which call on normal state
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //normalize the speed again
                worm.changeState(WormStateNormal.getInstance());
                }
            }, 5000); //delay (in ms) after which everything under timer.schedule is done
        }
}
