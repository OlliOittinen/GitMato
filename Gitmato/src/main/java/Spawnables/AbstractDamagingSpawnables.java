package Spawnables;

import Model.Worm;

//technically this could be a non-abstract class, but left abstract so you can't instantiate one as-is
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
                worm.turnAround();
                worm.setDirectionAdv(0);
                worm.setDirection(0);
            }
            Life.loseLife(worm);
        }
    }
}
