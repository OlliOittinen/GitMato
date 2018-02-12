/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spawnables;

import Model.Worm;
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

    public Shield() {
        init();
    }
    
    public void shield(Worm worm, int luku) {
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
        ImageIcon kuva = new ImageIcon("src/Images/shieldicon.png");
        image = kuva.getImage();
        setX((int) (Math.random() * 750));
        setY((int) (Math.random() * 550));
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
    public void randomizeXY() {
        setX((int) (Math.random() * 750));
        setY((int) (Math.random() * 550));
    }

    public boolean isActive(Worm worm) {
        return worm.getShield(worm);
    }
    
}
