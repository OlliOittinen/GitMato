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

import javafx.scene.image.Image;

/**
 *
 * @author Olli
 */
public class Confuse extends AbstractSpawnables {

    private Image confusion;
            
    public void confuse(Worm worm, Worm worm2) {
        Music.reverse.play();
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

    public Image getConfusionImage() {
        return confusion;
    }

}
