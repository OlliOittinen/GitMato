/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Image;
import java.awt.Rectangle;
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
        worm.setNopeus(worm.getNopeus()*2);
        Sound.Music.sound1.stop();
        Sound.Music.sound2.play();
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
            
        ye = 200;
        xe = 200;
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
