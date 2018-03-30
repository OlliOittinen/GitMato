package Model.WormStates;

import Model.Worm;

public class WormStateNormal extends WormState {

    //create a singleton instance of this class
    private static WormStateNormal instance;

    private WormStateNormal() {}

    public static WormStateNormal getInstance() {
        if (instance == null) {
            instance = new WormStateNormal();
        }
        return instance;
    }


    //override abstraction of superclass; set speed to be 3
    @Override
    public void action(Worm worm) {
        worm.setSpeed(3);
    }

}
