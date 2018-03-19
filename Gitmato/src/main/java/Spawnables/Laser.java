/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spawnables;

import Model.Worm;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Olli
 */
public class Laser implements Spawnables {

    private int xe;
    private int ye;
    private Image image;
    private int xe2;
    private int ye2;
    private Image image2;
    private int xe3;
    private int ye3;
    private Image image3;
    private Image image4;
    private Image image5;
    private boolean horizontal = false;
    private boolean lethal = false;

    private Rectangle beam = new Rectangle(-1000, -1000, 1, 1);

    public Laser() {
        init();
    }

    @Override
    public void loadImage(String imageName) {
        ImageView ii = new ImageView(imageName);
        image = ii.getImage();
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

    @Override
    public void init() {
        ImageView kuva = new ImageView("src/main/resources/images/Lasercannon.png");
        image = kuva.getImage();
        ImageView kuva2 = new ImageView("src/main/resources/images/LazerH.png");
        image2 = kuva2.getImage();
        ImageView kuva3 = new ImageView("src/main/resources/images/lazerV.png");
        image3 = kuva3.getImage();
        ImageView kuva4 = new ImageView("src/main/resources/images/lasersightV.png");
        image4 = kuva4.getImage();
        ImageView kuva5 = new ImageView("src/main/resources/images/lasersightH.png");
        image5 = kuva5.getImage();

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
                    setX2(wormLocX - 30);
                    setY2(0);
                    setBoundsB(wormLocX - 30, 0, 100, 600);
                    horizontal = false;
                } else {
                    //horizontal
                    setX3(0);
                    setY3(wormLocY - 30);
                    setBoundsB(0, wormLocY - 30, 800, 100);
                    horizontal = true;
                }

            }
        }, 1000); //aika (ms), joka odotetaan
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                lethal = true;
                Sound.Music.sound3.play();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        setX3(-1000);
                        setY3(-1000);
                        setX2(-1000);
                        setY2(-1000);
                        setBoundsB(-1000, -1000, 1, 1);
                        lethal = false;
                    }
                }, 2000);
            }
        }, 1800); //aika (ms), joka odotetaanF

    }

    //ikoni powerupille
    @Override
    public Rectangle getBounds() {
        return new Rectangle(xe + 3, ye + 3, 30, 30);
    }

    public Rectangle getBoundsB() {
        return beam;
    }

    public boolean getHorizontal() {
        return horizontal;
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
    public void setBoundsB(int x, int y, int w, int h){
        beam.setX(x);
        beam.setY(y);
        beam.setWidth(w);
        beam.setHeight(h);
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

    public Image getLasersightV() {
        return image4;
    }

    public Image getlasersightH() {
        return image5;
    }

    public boolean getLethal() {
        return lethal;
    }

    @Override
    public void randomizePowerUpLocation() {
        setX((int) (Math.random() * 750));
        setY((int) (Math.random() * 550));
    }

    public void hide() {
        setX2(-1000);
        setY2(-1000);
        setX3(-1000);
        setY3(-1000);
        setBoundsB(-1000, -1000, 1, 1);
    }

}
