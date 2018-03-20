package Spawnables;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AbstractSpawnables implements Spawnables {

    private int x;
    private int y;
    private Image image;

    @Override
    public void loadImage(String imageName) {
        ImageView ii = new ImageView(imageName);
        image = ii.getImage();
    }

    public abstract void init();

    public abstract Bounds getBounds();

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
    public Image getImage() {
        return image;
    }

    @Override
    public void randomizePowerUpLocation() {
        setX((int) (Math.random() * 750));
        setY((int) (Math.random() * 550));
    }
}
