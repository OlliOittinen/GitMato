package Model.WormStates;

import Model.Worm;

public abstract class WormState {

    /**
     * Empty method that handles the <code>Worm</code>  according to subclass
     * @param worm the <code>Worm</code>  to be handled
     */
    //abstract methcd used for all subclasses; overridden
    public void action(Worm worm) {}

    /**
     * Handles changing states of the <code>Worm</code>
     * @param worm the <code>Worm</code>  that gets its' state changed
     * @param wormState the desired state for this <code>Worm</code>
     */
    //change state of the worm to the corresponding instance
    void changeState(Worm worm, WormState wormState) {
        worm.changeState(wormState);
    }
}
