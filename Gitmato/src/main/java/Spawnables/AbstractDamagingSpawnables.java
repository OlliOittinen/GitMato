package Spawnables;

import Model.Worm;

//technically this oculd be a non-abstract class, but left as such for conveniency
public abstract class AbstractDamagingSpawnables extends AbstractSpawnables {

    boolean lethal = false;

    /**
     * Checks if this object should lose a life.
     * Randomizes the worm's location on the map and sets it's current direction (facing of) to 0.
     * In other words, randomizes this Object's location and completely halts its movement forcibly.
     * @param worm the Worm object that is being checked
     */
    public void damage(Worm worm) {
        if (lethal) {
            if (worm.getLife() > 1) {
                worm.randomizeXY();
                worm.setDirectionAdv(0);
                worm.setDirection(0);
            }
            Life.loseLife(worm);
        }
    }
}
