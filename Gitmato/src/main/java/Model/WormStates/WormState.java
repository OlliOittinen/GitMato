package Model.WormStates;

import Model.Worm;

public abstract class WormState {

    //abstract methcd used for all subclasses; overridden
    public void action(Worm worm) {}

    //change state of the worm to the corresponding instance
    void changeState(Worm worm, WormState ws) {
        worm.changeState(ws);
    }
}
