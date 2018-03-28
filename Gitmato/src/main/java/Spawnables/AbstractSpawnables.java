package Spawnables;
/**
 *
 *@author Olli
*/

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/*

    abstract class that handles all the spawnables, ie. everything under Spawnables directory

*/

public abstract class AbstractSpawnables implements Spawnables {

    //coordinates for the ICON
    int x;
    int y;

    //get the bounds for the rectangle, which is based on the size of the icon
    @Override
    public Bounds getBoundsForIcon(){
        Rectangle icon = new Rectangle(x, y, 30, 30);
        return icon.getLayoutBounds();
    }

    //classic getters & setters
    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    //method that randomizes the location for the ICON of the powerup
    @Override
    public void randomizePowerUpLocation() {
        //multiplied by size of the board -50 px to keep them visible
        setX((int) (Math.random() * 750));
        setY((int) (Math.random() * 550));
    }

    //when this ICON is created, hide it outside the vision of the user (user sees everything between 0 & width/height)
    @Override
    public void init(){
        setX(-100);
        setY(-100);
    }
}
