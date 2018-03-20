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
public class Confuse extends AbstractSpawnables {
    private int xe;
    private int ye;
    private Image image;
    private Image confusion;
            
    public void confuse(Worm worm, Worm worm2) {
        Music.sound9.play();
        worm.setPoints(worm.getPoints()+100);
        worm2.setNopeus(worm2.getNopeus()*-1);
        worm2.setReverse(true);
        //säätää nopeuden väliaikseks
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                worm2.setNopeus(worm2.getNopeus()*-1);
                worm2.setReverse(false);
            }
        }, 5000); //aika (ms), joka odotetaan
    }
    
    public Confuse() {
        init();
    }

    @Override
    public void init() {
        ImageView kuva = new ImageView("src/main/resources/images/Reverse.png");
        image = kuva.getImage();
        ImageView kuva2 = new ImageView("src/main/resources/images/confusion.png");
        confusion = kuva2.getImage();
        
            
        setX(-100);
        setY(-100);
    }

    @Override
    public Bounds getBounds() {
        Rectangle confuse = new Rectangle(xe+3, ye+3, 30, 30);
        return confuse.getLayoutBounds();
    }

    public Image getConfusionImage() {
        return confusion;
    }

}
