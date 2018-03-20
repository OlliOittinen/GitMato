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
public class Snack extends AbstractSpawnables{
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

}

