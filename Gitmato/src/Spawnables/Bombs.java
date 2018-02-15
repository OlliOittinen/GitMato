/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spawnables;

import Model.Worm;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;
import Model.Board;
import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author maxki
 */
public class Bombs implements Spawnables {
    
    private int xe;
    private int ye;
    private int xe2;
    private int ye2;
    private int xe3;
    private int ye3;
    private Image image;
    private Image image2;
    private Image image3;
    private Board board;
    private boolean lethal = false;
    
    public void bombs(Worm worm) {
        worm.setPoints(worm.getPoints()+100);
    }
    public void damage(Worm worm) {
        if (lethal) {
        worm.setLife(worm.getLife()-1);
        }
    }
    
    public Bombs() {
        init();
    }
    
    @Override
    public void loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }

    @Override
    public void init() {
        ImageIcon kuva = new ImageIcon("src/Images/Bombs(800-600).png");
        image = kuva.getImage();
        ImageIcon kuva2 = new ImageIcon("src/Images/Target2.png");
        image2 = kuva2.getImage();
        ImageIcon kuva3 = new ImageIcon("src/Images/firestorm2.png");
        image3 = kuva3.getImage();
            
        setX(-100);
        setY(-100);
        setX2(-1000);
        setY2(-1000);
        setX3(-1000);
        setY3(-1000);
    }
    
    public void bombZone(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                randomizeXY2();
            }
        }, 1000); //aika (ms), joka odotetaan
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setX3(getX2());
                setY3(getY2());
                setX2(-1000);
                setY2(-1000);
                lethal = true;
                timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    setX3(-1000);
                    setY3(-1000);
                    }
                }, 5000);
            }
        }, 2500); //aika (ms), joka odotetaan
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(xe+3, ye+3, 30, 30);
    }
    public Ellipse2D getBounds2() {
        return new Ellipse2D.Double(xe3+3, ye3+3, 200, 200);
    }

    @Override
    public int getX() {
        return xe;    
    }

    @Override
    public int getY() {
        return ye;
    }
    public int getX2() {
        return xe2;    
    }
    public int getY2() {
        return ye2;
    }
     public int getX3() {
        return xe3;    
    }
    public int getY3() {
        return ye3;
    }

    @Override
    public void setX(int x) {
        this.xe = x;
    }

    @Override
    public void setY(int y) {
        this.ye = y;
    }
    
    public void setX2(int x) {
        this.xe2 = x;
    }

    public void setY2(int y) {
        this.ye2 = y;
    }
    public void setX3(int x) {
        this.xe3 = x;
    }

    public void setY3(int y) {
        this.ye3 = y;
    }
    
    @Override
    public Image getImage() {
        return image;
    }
    public Image getImage(int n) {
        Image img = null;
        switch(n) {
            case 1:
                img =  image;
                break;
            case 2:
                img = image2;
                break;
            case 3:
                img = image3;
                break;
        }
        return img;
    }
    @Override
    public void randomizeXY() {
        setX((int) (Math.random() * 750));
        setY((int) (Math.random() * 550));
    }
    public void randomizeXY2() {
        setX2((int) (Math.random() * 600));
        setY2((int) (Math.random() * 400));
    }
}
