package Spawnables;
/**
 *
 *@author Olli
*/

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public abstract class AbstractSpawnables implements Spawnables {

    int x;
    int y;

    @Override
    public Bounds getBoundsForIcon(){
        Rectangle icon = new Rectangle(x + 3, y + 3, 30, 30);
        return icon.getLayoutBounds();
    };

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

    @Override
    public void randomizePowerUpLocation() {
        setX((int) (Math.random() * 750));
        setY((int) (Math.random() * 550));
    }

    @Override
    public void init(){
        setX(-100);
        setY(-100);
    }
}
