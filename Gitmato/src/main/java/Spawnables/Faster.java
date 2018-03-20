/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spawnables;

import Model.Worm;
import Sound.Music;
import java.util.Timer;
import java.util.TimerTask;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Olli
 */

public class Faster extends AbstractSpawnables {
    
    private int xe;
    private int ye;
    private Image image;
    
    
    public void faster(Worm worm) {
        worm.setPoints(worm.getPoints()+100);
        worm.setNopeus(3);
        Music.sound2.play();
        
        //säätää nopeuden väliaikseks
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                worm.setNopeus(2);
            }
        }, 5000); //aika (ms), joka odotetaan
    }
    
    public Faster() {
        init();
    }

    @Override
    public void init() {
        ImageView kuva = new ImageView("src/main/resources/images/SpeedUp.png");
        image = kuva.getImage();
            
        setX(-100);
        setY(-100);
    }

    @Override
    public Bounds getBounds() {
        Rectangle faster = new Rectangle(xe+3, ye+3, 30, 30);
        return faster.getLayoutBounds();
    }

}
