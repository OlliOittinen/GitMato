/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spawnables;

import Model.Worm;
import Model.Board;
import Sound.Music;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.*;

/**
 *
 * @author maxki
 */
public class Bombs implements Spawnables {

    private int xe, ye, xe2, ye2, xe3, ye3, xe4, ye4, xe5, ye5, xe6, ye6, xe7, ye7;

    private Image image, image2, image3;

    private Board board;
    private boolean lethal = false;

    private int xlist[] = new int[]{xe, xe2, xe3, xe4, xe5, xe6, xe7};
    private int ylist[] = new int[]{ye, ye2, ye3, ye4, ye5, ye6, ye7};

    public void bombs(Worm worm) {
        worm.setPoints(worm.getPoints() + 100);
    }

    public void damage(Worm worm) {
        if (lethal) {
            if (worm.getLife() > 1) {
                worm.randomizeXY();
                worm.setSuuntaAdv(0);
                worm.setSuunta(0);
            }
            Life.loseLife(worm);
        }
    }

    public Bombs() {
        init();
    }

    @Override
    public void loadImage(String imageName) {
        ImageView ii = new ImageView(imageName);
        image = ii.getImage();
    }

    @Override
    public void init() {
        ImageView kuva = new ImageView("src/main/resources/images/Bombs(800-600).png");
        image = kuva.getImage();
        ImageView kuva2 = new ImageView("src/main/resources/images/Target2.png");
        image2 = kuva2.getImage();
        ImageView kuva3 = new ImageView("src/main/resources/images/firestorm2.png");
        image3 = kuva3.getImage();

        setX(-100);
        setY(-100);
        for (int i = 0; i < xlist.length; i++) {
            setXBombs(i, -1000);
            setYBombs(i, -1000);
        }
    }

    public void bombZone() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                randomizeXYBombs();
            }
        }, 1000); //aika (ms), joka odotetaan
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Music.sound7.play();
                for (int i = 2; i < xlist.length; i = i + 2) {
                    setXBombs(i, xlist[i - 1]);
                    setYBombs(i, ylist[i - 1]);
                }
                for (int i = 1; i <= 5; i = i + 2) {
                    setXBombs(i, -1000);
                    setYBombs(i, -1000);
                }
                lethal = true;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        for (int i = 2; i <=6; i = i + 2) {
                            setXBombs(i, -1000);
                            setYBombs(i, -1000);
                        }
                    }
                }, 5000);
            }
        }, 2500); //aika (ms), joka odotetaan
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(xe + 3, ye + 3, 30, 30);
    }

    public Ellipse getBoundsBombs(int n) {
        return new Ellipse(xlist[n] + 3, ylist[n] + 3, 200, 200);
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
        xe = x;
    }

    @Override
    public void setY(int y) {
        ye = y;
    }

    public int getXBombs(int n) {
        return xlist[n];
    }

    public int getYBombs(int n) {
        return ylist[n];
    }

    public void setXBombs(int n, int value) {
        xlist[n] = value;
    }

    public void setYBombs(int n, int value) {
        ylist[n] = value;
    }

    @Override
    public Image getImage() {
        return image;
    }

    public Image getImage(int n) {
        Image img = null;
        switch (n) {
            case 1:
                img = image;
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
    public void randomizePowerUpLocation() {
        setX((int) (Math.random() * 750));
        setY((int) (Math.random() * 550));
    }

    public void randomizeXYBombs() {
        for (int i = 1; i < xlist.length; i = i + 2) {
            setXBombs(i, (int) (Math.random() * 600));
            setYBombs(i, (int) (Math.random() * 400));
        }
    }
}
