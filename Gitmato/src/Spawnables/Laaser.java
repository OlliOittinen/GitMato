/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spawnables;

import Model.Board;
import Model.Worm;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;

/**
 *
 * @author Olli
 */
public class Laaser implements Spawnables{

    private int xe;
    private int ye;
    private Image image;
    private int xe2;
    private int ye2;
    private Image image2;
    private int xe3;


    private int ye3;
    private Image image3;
    
    public Laaser() {
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
        ImageIcon kuva2 = new ImageIcon("src/Images/IronHorizontal.png");
        image2 = kuva2.getImage();
        ImageIcon kuva3 = new ImageIcon("src/Images/IronVertical.png");
        image3 = kuva3.getImage();
            
        setX(-100);
        setY(-100);
        setX2(-100);
        setY2(-100);
    }

    
    public void onPickup(Worm worm, Worm worm2) {
        worm.setPoints(worm.getPoints()+100);
        
        //hae kohteen nykysijainti, tallenna muuttujiin
        int wormLocX = worm2.getX();
        int wormLocY = worm2.getY();
        
        //horisontaalinen vai vertikaalinen säde, random arvo 0...1
        double r = Math.random();
        //hae luotavan säteen rajat, laita sijainti 2. madon nykypaikkaan
        Rectangle beam;
        if (r<0.5) {
            beam = getBoundsHorizontal();
            beam.setFrameFromCenter(wormLocX, wormLocY, (wormLocX+800)/2, 0);
        }
        else {
            beam = getBoundsVertical();
            beam.setFrameFromCenter(wormLocX, wormLocY, 0, (wormLocY+600)/2);
        }   
    }
    //ikoni powerupille
    @Override
    public Rectangle getBounds() {
        return new Rectangle(xe+3, ye+3, 30, 30);
    }
    
    public Rectangle getBoundsVertical() {
        return new Rectangle(xe+3, ye+3, 800, 100);
    }
    
    public Rectangle getBoundsHorizontal() {
        return new Rectangle (xe+3, ye+3, 100, 600);
    }

    @Override
    public int getX() {
        return xe;    
    }

    @Override
    public int getY() {
        return ye;
    }
    
    public int getXe2() {
        return xe2;
    }

    public int getYe2() {
        return ye2;
    }

    public int getXe3() {
        return xe3;
    }

    public int getYe3() {
        return ye3;
    }
    
    @Override
    public void setX(int x) {
        this.xe = x;
    }
    
    public void setX2(int x) {
        this.xe2 = x;
    }
    
    public void setX3(int x) {
        this.xe3 = x;
    }

    @Override
    public void setY(int y) {
        this.ye = y;
    }
    
    public void setY2(int y) {
        this.ye2 = y;
    }
    
    public void setY3(int y) {
        this.ye3 = y;
    }
    @Override
    public Image getImage() {
        return image;
    }
    
    public Image getImageHori() {
        return image2;
    }
    
    public Image getImageVert() {
        return image3;
    }
    
    @Override
    public void randomizeXY() {
        setX((int) (Math.random() * 750));
        setY((int) (Math.random() * 550));
    }
}
