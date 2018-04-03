/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.WormStates.*;
import javafx.geometry.Bounds;
import javafx.scene.shape.Ellipse;


/**
 * @author maxki
 */
public class Worm {

    private WormState state = WormStateNormal.getInstance();

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
    /**
     * Getter for life.
     * @return life of the Worm.
     */
    public int getLife() {
        return life;
    }
    /**
     * Setter for life.
     * @param life value of the Worm.
     */
    public void setLife(int life) {
        this.life = life;
    }
    /**
     * Constructor for the Worm.
     */
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

    /**
     * Moves the Worm to the direction it is facing.
     */

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
    /**
     * Getter for x coordinate.
     * @return x coordinate of Worm object.
     */
    public int getX() {
        return x;
    }
    /**
     * Getter for y coordinate.
     * @return y coordinate of Worm object.
     */
    public int getY() {
        return y;
    }
    /**
     * Setter for x coordinate.
     * @param luku x coordinate of the Worm.
     */
    public void setX(int luku) {
        this.x = luku;
    }
    /**
     * Setter for y coordinate.
     * @param luku y coordinate of the Worm.
     */
    public void setY(int luku) {
        this.y = luku;
    }
    /**
     * Getter for direction.
     * @return direction of Worm object.
     */
    public int getDirection() {
        return direction;
    }
    /**
     * Setter for direction.
     * @param s direction of the Worm.
     */
    public void setDirection(int s) {
        this.direction = s;
    }
    /**
     * Setter for directionAdv.
     * @param a directionAdv of the Worm.
     */
    public void setDirectionAdv(int a) {
        this.directionAdv = a;
    }
    /**
     * Getter for speed.
     * @return speed of Worm object.
     */
    public double getSpeed() {
        return speed;
    }
    /**
     * Setter for speed.
     * @param speed speed of the Worm.
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    /**
     * Getter for shield.
     * @return shield of Worm object.
     */
    public boolean getShield(Worm worm) {
        return this.shield;
    }
    /**
     * Setter for shield.
     * @param active shield of the Worm.
     */
    public void setShield(boolean active) {
        this.shield = active;
    }
    /**
     * Setter for reverse.
     * @param active reverse of the Worm.
     */
    public void setReverse(boolean active) {
        this.reverse = active;
    }
    /**
     * Getter for reverse.
     * @return reverse of Worm object.
     */
    public boolean getReverse(Worm worm) {
        return this.reverse;
    }
    /**
     * Getter for points.
     * @return points of Worm object.
     */
    public int getPoints() {
        return points;
    }
    /**
     * Setter for points.
     * @param points points of the Worm.
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Randomizes x and y of the worm.
     */

    public void randomizeXY() {
        setX((int) (Math.random() * 740) + 10);
        setY((int) (Math.random() * 540) + 10);
    }

    /**
     * Moves the Worm continuosly to the same direction.
     */

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
    /**
     * Getter for bounds.
     * @return the boundsthe  of Worm object.
     */
    public Bounds getBounds() {
        Ellipse mato = new Ellipse(x + 18, y + 18, 18, 18);
        return mato.getLayoutBounds();
    }

    /**
     * Changes the state of the worm.
     * @param ws state of the Worm object.
     */

    public void changeState(WormState ws) {
        state = ws;
        state.action(this);
    }

    /**
     * Changes the speed of the Worm to faster.
     */

    public void fasterSpeed(Worm this) {
        changeState(WormFast.getInstance());
    }
    /**
     * Changes the speed of the Worm to slower.
     */

    public void slowerSpeed() {
        changeState(WormSlow.getInstance());
    }
    /**
     * Changes the state of the shield of the Worm.
     */

    public void shield() {
        changeState(WormShield.getInstance());
    }
    /**
     * Changes the state of the confuse of the Worm.
     */

    public void confuse() {
        changeState(WormConfuse.getInstance());
    }

}