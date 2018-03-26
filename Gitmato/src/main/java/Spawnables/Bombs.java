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

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.shape.*;

/**
 *
 * @author maxki, Olli, Eero
 */
public class Bombs extends AbstractSpawnables{

    private int x2, y2, x3, y3, x4, y4, x5, y5, x6, y6, x7, y7;

    private Image image, image2, image3;

    private boolean lethal = false;

    private int xlist[] = new int[]{x, x2, x3, x4, x5, x6, x7};
    private int ylist[] = new int[]{y, y2, y3, y4, y5, y6, y7};

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
    public void init() {
        image = new Image("images/Bombs(800-600).png");
        image2 = new Image("images/Target2.png");
        image3 = new Image("images/firestorm2.png");

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
        }, 1000); //aika (ms), joka odotetaan ENNEN targetteja
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Music.bombs.play();
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
                }, 5000); //tulialueet
            }
        }, 2500); //targetit
    }

    public Bounds getBoundsBombs(int n) {
        Ellipse b = new Ellipse(xlist[n] + 3, ylist[n] + 3, 200, 200);
        return b.getLayoutBounds();
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

    public void randomizeXYBombs() {
        for (int i = 1; i < xlist.length; i = i + 2) {
            setXBombs(i, (int) (Math.random() * 600));
            setYBombs(i, (int) (Math.random() * 400));
        }
    }
}
