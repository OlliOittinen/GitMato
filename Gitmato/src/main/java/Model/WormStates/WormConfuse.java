package Model.WormStates;

import Model.Worm;

import java.util.Timer;
import java.util.TimerTask;

public class WormConfuse extends WormState {

    //create a singleton instance of this class
    private static WormConfuse instance;

    private WormConfuse() {}

    /**
     * Class 'constructor'. Retrieves this class's instance.
     * This class is defined as a singleton, so it is only ever created once.
     * @return the instance of this class
     */
    public static WormConfuse getInstance() {
        if (instance == null) {
            instance = new WormConfuse();
        }
        return instance;
    }


    /**
     * Makes this worm object move in the opposing direction on every key press
     * @param worm the worm that's supposed to have its' movement reversed
     */
    @Override
    public void action(Worm worm) {

    //set the boolean for drawing the effect on top of the worm to be true
    worm.setReverse(true);
    //set the speed to be opposite of current
    worm.setSpeed(worm.getSpeed() * -1);

    Timer timer = new Timer();
        timer.schedule(new TimerTask() {
        @Override
        public void run () {
            //stop drawing the image on top
            worm.setReverse(false);
            //after the delay, set the speed to be normal again
            worm.changeState(WormStateNormal.getInstance());
        }
    },5000); //this marks the delay ie. the time after all the stuff under timer.schedule is done
}
}
