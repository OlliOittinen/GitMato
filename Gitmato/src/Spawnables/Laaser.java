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
public class Laaser implements Spawnables {

    private int xe;
    private int ye;
    private Image image;
    private int xe2;
    private int ye2;
    private Image image2;
    private int xe3;
    private int ye3;
    private Image image3;
    private boolean lethal = false;
    private Rectangle beam = new Rectangle(-1000, -1000, 1, 1);

    public Laaser() {
        init();
    }

    @Override
    public void loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }

    public void damage(Worm worm) {
        if (lethal) {
            Life.loseLife(worm);
        }
    }

    @Override
    public void init() {
        ImageIcon kuva = new ImageIcon("src/Images/Lasercannon.png");
        image = kuva.getImage();
        ImageIcon kuva2 = new ImageIcon("src/Images/LazerH.png");
        image2 = kuva2.getImage();
        ImageIcon kuva3 = new ImageIcon("src/Images/lazerV.png");
        image3 = kuva3.getImage();

        setX(-100);
        setY(-100);
        setX2(-1000);
        setY2(-1000);
        setX3(-1000);
        setY3(-1000);
    }

    public void onPickup(Worm worm, Worm worm2) {
        worm.setPoints(worm.getPoints() + 100);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //hae kohteen nykysijainti, tallenna muuttujiin
                int wormLocX = worm2.getX();
                int wormLocY = worm2.getY();
                //horisontaalinen vai vertikaalinen säde, random arvo 0...1
                double r = Math.random();
                if (r < 0.5) {
                    //vertical
                    setX2(wormLocX);
                    setY2(0);
                    beam.setBounds(wormLocX, 0, 100, 600);

                } else {
                    //horizontal
                    setX3(0);
                    setY3(wormLocY);
                    beam.setBounds(0, wormLocY, 800, 100);
                }

            }
        }, 1000); //aika (ms), joka odotetaan
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                lethal = true;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        setX3(-1000);
                        setY3(-1000);
                        setX2(-1000);
                        setY2(-1000);
                        beam.setBounds(-1000, -1000, 1, 1);
                        lethal = false;
                    }
                }, 2000);
            }
        }, 2500); //aika (ms), joka odotetaanF

    }

    //ikoni powerupille
    @Override
    public Rectangle getBounds() {
        return new Rectangle(xe + 3, ye + 3, 30, 30);
    }

    public Rectangle getBoundsB() {
        return beam;
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

    public void randomizeXY2(int x) {
        setX2(x);
        setY2(0);
    }

    public void randomizeXY3(int y) {
        setX3(0);
        setY3(y);
    }

}
