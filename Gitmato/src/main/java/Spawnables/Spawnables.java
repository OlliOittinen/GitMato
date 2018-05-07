package Spawnables;

import javafx.geometry.Bounds;


/**
 * All methods used by this interface are related to the icon (ie. Image displayed on screen) and not on what the spawnable actually does.
 * @author Olli
 * @version 1
 */
public interface Spawnables {


    /**
     * On creation of this powerup, sets this icon's location to be invisible to the user.
     * The icon still exists, but is not shown to the user.
     */
    void init();

    /**
     Returns the Bounds object for the icon.
     Bounds are based on a Rectangle created with a predetermined size.
     @return the Bounds object of the icon specified for implementing class
     @see Bounds
     */
    Bounds getBoundsForIcon();

    /**
     * Returns the Integer for this icon's x-coordinate
     @return the x-coordinate for this icon
     */
    int getX();

    /**
     * Returns the Integer for this icon's y-coordinate
     @return the y-coordinate for this icon
     */
    int getY();

    /**
     * Assigns the icon's x-coordinate to this Integer value
     @param x x-coordinate for this icon
     */
    void setX(int x);

    /**
     * Assigns the icon's y-coordinate to this Integer value
     @param y y-coordinate for this icon
     */
    void setY(int y);

   /**
    * This method randomizes the location for the icon
    * to be placed using Math.random (ie. pseudo-random)
    * and uses the size of the map to do so
    */
    void randomizeIconLocation();

}

    
