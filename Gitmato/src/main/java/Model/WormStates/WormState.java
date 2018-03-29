package Model.WormStates;

import Model.Worm;

public abstract class WormState {

    public void fasterSpeed(Worm worm){}
    public void slowerSpeed(Worm worm){}
    public void normalSpeed(Worm worm){}
    public void shield(Worm worm) {}
    public void confuse(Worm worm) {}
    void changeState(Worm worm, WormState ws) {
        worm.changeState(ws);
    }
}
