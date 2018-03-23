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
 * @author maxki
 */
public class Shield extends AbstractSpawnables{

    private Image shieldeffect = new Image("images/ShieldEffect.png");

    public Shield() {
        init();
    }
    
    public void shield(Worm worm, int time) {
        Music.shield.play();
        worm.setPoints(worm.getPoints()+100);
        worm.setShield(true);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                worm.setShield(false);
            }
        }, time); //aika (ms), joka odotetaan
    }
    
@Override
    public void init() {
        image = new Image("images/Shield.png");
        x = -100;
        y = -100;
    }

 public Image getShieldImage() {
       Image img = shieldeffect;
       return img;
    }

    public boolean isActive(Worm worm) {
        return worm.getShield(worm);
    }
    
}
