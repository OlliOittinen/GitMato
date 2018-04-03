package Model.WormStates;

import Model.Worm;

public class WormStateNormal extends WormState {

    //create a singleton instance of this class
    private static WormStateNormal instance;

    private WormStateNormal() {}

    /**
     * Class 'constructor'. Retrieves this class's instance.
     * This class is defined as a singleton, so it is only ever created once.
     * @return the instance of this class
     */
    public static WormStateNormal getInstance() {
        if (instance == null) {
            instance = new WormStateNormal();
        }
        return instance;
    }


    /**
     * Makes the worm object move normally
     * @param worm the worm object that gets its speed normalized
     */
    @Override
    public void action(Worm worm) {
        worm.setSpeed(3);
    }

}
