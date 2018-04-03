package Model.WormStates;

import Model.Worm;

public abstract class WormState {

    /**
     * Empty method that handles the worm object according to subclass
     * @param worm the worm object to be handled
     */
    //abstract methcd used for all subclasses; overridden
    public void action(Worm worm) {}

    /**
     * Handles changing states of the worm
     * @param worm the worm that gets its' state changed
     * @param wormState the desired state for this worm
     */
    //change state of the worm to the corresponding instance
    void changeState(Worm worm, WormState wormState) {
        worm.changeState(wormState);
    }
}
