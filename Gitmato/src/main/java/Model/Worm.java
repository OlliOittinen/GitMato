/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;


/**
 * @author maxki
 */
public class Worm {

    private double dx;
    private double dy;
    private int x;
    private int y;
    private int direction = 1;
    private int directionAdv = 0;
    private int playerNro;
    private boolean shield = false; //shield power-up
    private boolean reverse = false; //Reverse debuff
    private int points;
    
    private double speed = 3;
    private int life = 3;

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Worm(int p) {
        initWorm(p);
    }

    private void initWorm(int p) {
        this.playerNro = p;
        if (playerNro == 1) {
            x = 200;
            y = 279; //oma puoli kentästä-kuvan korkeus
        }
        if (playerNro == 2) {
            x = 565; //kentän puoliväli-kuvan leveys
            y = 279; //oma puoli kentästä-kuvan korkeus
        }
    }

    public void move() {
        if (x > 0 && dx < 0 || x < 960 && dx > 0) {
            if (directionAdv == 2) {
                x += dx;
            }

        }
        if (y > 0 && dy < 0 || y < 950 && dy > 0) {
            if (directionAdv == 1) {
                y += dy;
            }

        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int luku) {
        this.x = luku;
    }

    public void setY(int luku) {
        this.y = luku;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int s) {
        this.direction = s;
    }

    public void setDirectionAdv(int a) {
        this.directionAdv = a;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean getShield(Worm worm) {
        return this.shield;
    }

    public void setShield(boolean active) {
        this.shield = active;
    }

    public void setReverse(boolean active) {
        this.reverse = active;
    }

    public boolean getReverse(Worm worm) {
        return this.reverse;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void randomizeXY() {
        setX((int) (Math.random() * 740) + 10);
        setY((int) (Math.random() * 540) + 10);
    }

    public void moveCont() {
        //if shield is NOT active on worm
        if (direction == 1) {
            dx = -1 * speed;
        }

        if (direction == 2) {
            dx = 1 * speed;
        }

        if (direction == 3) {
            dy = -1 * speed;

        }

        if (direction == 4) {
            dy = 1 * speed;

        }
    }

    public Bounds getBounds() {
        Ellipse mato = new Ellipse(x + 18, y + 18, 18, 18);
        return mato.getLayoutBounds();
    }


}