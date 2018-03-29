package Model.WormStates;

import Model.Worm;

public abstract class WormState {

    public void action(Worm worm) {}
    void changeState(Worm worm, WormState ws) {
        worm.changeState(ws);
    }
}
