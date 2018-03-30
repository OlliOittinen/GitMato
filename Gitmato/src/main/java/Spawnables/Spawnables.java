/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spawnables;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;


/**
 *
 * @author Olli
 * @version 1
 */
public interface Spawnables {


    /**
     * On creation of this powerup, sets this icon's location to be invisible to the user.
     * The user only sees values above #0, so setting them below this value effectively hides them.
     * The icon still exists, but is not shown to the user.
     */
    void init();

    /**
     Returns the Bounds object for the icon, which is based on a rectangular shape created
     @return the Bounds of the icon specified for implementing class
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
    * to be placed using Math.random() function (ie. pseudo-random)
    * and uses the size of the field's coordinates to do so
    */
    void randomizePowerUpLocation();

}

    
