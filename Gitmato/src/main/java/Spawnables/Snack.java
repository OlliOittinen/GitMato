/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spawnables;

import Sound.Music;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;


/**
 *
 * @author maxki
 */
public class Snack implements Spawnables{
    private int xe;
    private int ye;
    private Image image;

    public Snack() {
        init();
    }
    
    @Override
    public void init() {
        ImageView kuva = new ImageView("src/main/resources/images/Apple(800x600).png");
        image = kuva.getImage();
        setX( 600-(int)(Math.random()*400));
        setY(200);
    }
@Override    
    public void loadImage(String imageName) {
        ImageView ii = new ImageView(imageName);
        image = ii.getImage();
    }
@Override
    public Image getImage() {
        return image;
    }
@Override
    public int getX() {
        return xe;
    }
@Override
    public int getY() {
        return ye;
    }
@Override    
    public void setX(int x){
        this.xe = x;
    }
@Override    
    public void setY(int y){
        this.ye = y;
    }

@Override    
    public Bounds getBounds() {
        Rectangle snack = new Rectangle(xe, ye, 41, 55);
        return snack.getLayoutBounds();
    }
@Override
    public void randomizePowerUpLocation() {
        Music.sound10.play();
        setX((int) (Math.random() * 750));
        setY((int) (Math.random() * 550));
    }
}

