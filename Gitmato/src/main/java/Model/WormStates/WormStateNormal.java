package Model.WormStates;

import Model.Worm;

public class WormStateNormal extends WormState {

    private static WormStateNormal instance;

    private WormStateNormal() {}

    public static WormStateNormal getInstance() {
        if (instance == null) {
            instance = new WormStateNormal();
        }
        return instance;
    }

    @Override
    public void action(Worm worm) {
        worm.setSpeed(3);
    }

}
