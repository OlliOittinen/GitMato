/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;

/**
 *
 * @author Olli
 */

public class Powerup implements Spawnables {
    
    private int xe;
    private int ye;
    private Image image;
    
    public void faster(Worm worm) {
        worm.setNopeus(worm.getNopeus()+1);
        Sound.Music.sound1.stop();
        Sound.Music.sound2.play();
        
        //säätää nopeuden väliaikseks
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                worm.setNopeus(worm.getNopeus()-1);
                Sound.Music.sound2.stop();
                Sound.Music.sound1.loop();
            }
        }, 5000);
    }
    
    public Powerup() {
        init();
    }
    
    @Override
    public void loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();    }

    @Override
    public void init() {
        ImageIcon kuva = new ImageIcon("src/Images/Bottle(800x600).png");
        image = kuva.getImage();
            
        setX((int) (Math.random() * 750));
        setY((int) (Math.random() * 550));
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(xe+3, ye+3, 30, 30);
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
    public void setX(int x) {
        this.xe = x;
    }

    @Override
    public void setY(int y) {
        this.ye = y;
    }

    @Override
    public Image getImage() {
        return image;
    }
}
