package Spawnables;


import Model.Worm;
import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;

/**
 * @author Max, Olli
 */
public class Steal extends AbstractSpawnables{

    /**
     * Shortens the opposing <code>Worm</code>'s tail by one quarter.
     * Rewards player who picked up the power-up icon with 50 points per tailpiece steal.
     * Reduces the points from the opposing <code>Worm</code> .
     * @param worm the <code>Worm</code> object that picked up the icon
     * @param worm2 the <code>Worm</code> object that is being targeted
     */
    public void steal(Worm worm, Worm worm2) {

        int currentLength = worm2.getTails().size();
        int howMuchAfterCut = (int) (worm2.getTails().size()*0.75);

        if (currentLength > 0 && !worm2.getShield()) {

            do {
                worm2.getTails().remove(currentLength - 1);
                worm.addTail();
                worm.setPoints(worm.getPoints() - 50); //addTail() adds 100 points so reduce the point/tail a little for balance
                worm2.setPoints(worm2.getPoints() - 50);
                currentLength = worm2.getTails().size();

            } while (currentLength > howMuchAfterCut);
        }else{
            worm2.setShield(false);
        }
    }
    /**
     * Class constructor
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
