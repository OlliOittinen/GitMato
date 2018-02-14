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
    private Board board;
    
    public Laaser(Board board) {
        init();
    }
    
    public void Laaser(Worm worm, Worm worm2) {
        Timer timer = new Timer();
        worm.setPoints(worm.getPoints()+100);
        
        int wx = worm2.getX();
        int wy = worm2.getY();
        
        Rectangle laserpointer = new Rectangle(); //laserosoitin, joka varoittaa pelaajia tulevasta
        laserpointer.setLocation(wx, wy);
        if((wx<400 && wy>300) || (wx>400 && wy>300)){   //jos mato jommassa kummassa ylänurkassa
            laserpointer.setSize(100, 600); //leveys, korkeus
        }
        else { //jos alanurkassa
            laserpointer.setSize(800, 100);
        }
        
        Graphics g = board.getGraphics();
        g.setColor(Color.red); //väri, jolla piirretään
        board.paintImmediately(laserpointer);

        //buffer laaserille, jotta ei tapa heti vaan odottaa hetken
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //mitä tehdään ajan jälkeen
                
                Rectangle laser = new Rectangle(); //oikea laser, joka ammutaan
                laser.setBounds(laserpointer);
        
                if (laser.intersects(worm.getBounds())) { //jos osuu matoon, joka otti powerupin
                    Life.loseLife(worm);
                }
                else if (laser.intersects(worm2.getBounds())) { //jos osuu toiseen matoon
                    Life.loseLife(worm2);
                }
            }
        }, 3000); //aika (ms), joka odotetaan
        

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
