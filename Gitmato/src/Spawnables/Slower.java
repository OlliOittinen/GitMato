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
 * @author Olli
 */
public class Slower implements Spawnables{
    private int xe;
    private int ye;
    private Image image;
            
    public void slower(Worm worm, Worm worm2) {
        worm.setPoints(worm.getPoints()+100);
        worm2.setNopeus(1);
        
        //säätää nopeuden väliaikseks
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                worm2.setNopeus(2);
            }
        }, 5000); //aika (ms), joka odotetaan
    }
    
    public Slower() {
        init();
    }
    
    @Override
    public void loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();    }

    @Override
    public void init() {
        ImageIcon kuva = new ImageIcon("src/Images/BottleRed(800x600).png");
        image = kuva.getImage();
            
        setX(-100);
        setY(-100);
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
    @Override
    public void randomizeXY() {
        setX((int) (Math.random() * 750));
        setY((int) (Math.random() * 550));
    }
}
