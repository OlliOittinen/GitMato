package Spawnables;


import Model.Worm;
import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;

/**
 * @author Max
 */
public class Steal extends AbstractSpawnables{



    /**
     * Shortens the opposing worm's tail by one quarter.
     * Rewards player who picked up the powerup icon with 50 points per tailpiece cut.
     * Reduces the points from the opposing worm.
     * @param worm the Worm object that picked up the icon
     * @param worm2 the Worm object that is being targeted
     */
    public void cut(Worm worm, Worm worm2) {


        int currentLength = worm2.getTails().size();
        int howMuchAfterCut = (int) (worm2.getTails().size()*0.75);

        if (currentLength > 0) {
            do {
                worm2.getTails().remove(currentLength - 1);
                worm.setPoints(worm.getPoints() - 50);
                worm2.setPoints(worm2.getPoints() - 50);
                worm.addTail();

                currentLength = worm2.getTails().size();


            } while (currentLength > howMuchAfterCut);
        }
    }
    /**
     * Class constructor, calls on init();
     *
     * @see AbstractSpawnables
     */
    public Steal() {
        init();
    }

    @Override
    public Bounds getBoundsForIcon() {
        Rectangle icon = new Rectangle(x, y, 55, 42);
        return icon.getLayoutBounds();
    }

}
