package Spawnables;

import Model.Worm;

/**
 *
 * @author Olli
 * @version 1
 */
//technically this could be a non-abstract class, but left abstract so you can't instantiate one as-is
public abstract class AbstractDamagingSpawnables extends AbstractSpawnables {

    boolean lethal = false;

    /**
     * Checks if this object should lose a life.
     *
     * @param worm the Worm object that is being checked
     */
    public void damage(Worm worm) {
        if (lethal) {
            Life.loseLife(worm);
        }
    }
}
