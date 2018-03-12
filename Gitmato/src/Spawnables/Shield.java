/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spawnables;

import Model.Worm;
import Sound.Music;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;

/**
 *
 * @author maxki
 */
public class Shield  implements Spawnables{
    private int xe;
    private int ye;
    private Image image;
    private ImageIcon shieldeffect = new ImageIcon("src/Images/ShieldEffect.png");

    public Shield() {
        init();
    }
    
    public void shield(Worm worm, int luku) {
        Music.sound6.play();
        worm.setPoints(worm.getPoints()+100);
        worm.setShield(true);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                worm.setShield(false);
            }
        }, luku); //aika (ms), joka odotetaan
    }
    
@Override
    public void init() {
        ImageIcon kuva = new ImageIcon("src/Images/Shield.png");
        image = kuva.getImage();
        xe = -100;
        ye = -100;
    }
@Override    
    public void loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }
@Override
    public Image getImage() {
        return image;
    }
 public Image getShieldImage() {
       Image img = shieldeffect.getImage();
       return img;
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
    public Rectangle getBounds() {
        return new Rectangle(xe, ye, 41, 55);
    }
@Override
    public void randomizePowerUpLocation() {
        setX((int) (Math.random() * 750));
        setY((int) (Math.random() * 550));
    }

    public boolean isActive(Worm worm) {
        return worm.getShield(worm);
    }
    
}
