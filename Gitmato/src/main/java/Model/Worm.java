/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.WormStates.*;
import javafx.geometry.Bounds;
import javafx.scene.shape.Ellipse;

import java.util.ArrayList;


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
    private boolean transparencychange;
    private final int multiplier = 8;
    private double speed = 3;
    private int life = 3;

    /**
     * Getter for life value.
     * @return life value of the Worm.
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

    public ArrayList<Tail> getTails() {
        return this.tailList;
    }

    private ArrayList<Tail> tailList = new ArrayList<>();
    /**
     * Class constructor.
     * @param p number of the user; 1 for first player, 2 for the second
     */
    public Worm(int p) {
        initWorm(p);
    }

    private void initWorm(int p) {
        this.playerNro = p;
        if (playerNro == 1) {
            x = 200;
            y = 279; //oma puoli kentästä-kuvan korkeus
            this.setDirection(2);
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
     * Getter for x-coordinate for this worm.
     * @return x-coordinate of Worm object.
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for y-coordinate for this worm.
     * @return y-coordinate of Worm object.
     */
    public int getY() {
        return y;
    }

    /**
     * Setter for x-coordinate for this worm.
     * @param luku x-coordinate of the Worm.
     */
    public void setX(int luku) {
        this.x = luku;
    }

    /**
     * Setter for y-coordinate for this worm.
     * @param luku y-coordinate of the Worm.
     */
    public void setY(int luku) {
        this.y = luku;
    }

    /**
     * Getter for direction this worm is facing.
     * (1) for left, (2) for right, (3) for up, (4) for down.
     * @return integer of the direction of Worm object.
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Setter for direction this worm should be facing.
     * @param s direction of the Worm.
     */
    public void setDirection(int s) {
        this.direction = s;
    }

    // --------------------------- MITÄH -------------------------------------
    /**
     * Setter for directionAdv.
     * directionAdv shows if the Worm is moving horizontally (2) or vertically (1).
     * @param a directionAdv of the Worm.
     */
    public void setDirectionAdv(int a) {
        this.directionAdv = a;
    }

    /**
     * Getter for current speed of this worm.
     * @return speed of Worm object.
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Setter for speed of this worm.
     * @param speed speed the worm should travel
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    /**
     * Getter for shield.
     * @return shield of Worm object.
     */
    public boolean getShield() {
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
     * Reverse implies if the Worm is under the effects of Reverse
     * @return reverse of Worm object.
     */
    public boolean getReverse() {
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
     * Setter for transparecy change.
     * @param transparencychange the boolean value to be set if transparency is active or not.
     */
    public void setTransparencyChange(boolean transparencychange) {
        this.transparencychange = transparencychange;
    }

    /**
     * Getter for transparency change.
     * @return boolean value if transparency is active or not.
     */
    public boolean isTransparencyChange() {
        return transparencychange;
    }

    /**
     * Reverses the worm's current direction to be the opposite of the current.
     * Eg. if the worm is traveling left, it will travel right after this method is called.
     */
    public void turnAround() {

        int currentDirection = this.direction;

        if (currentDirection==1) {
            this.setDirection(2);
        } else if (currentDirection==2) {
            this.setDirection(1);
        } else if (currentDirection==3) {
            this.setDirection(4);
        }else {
            this.setDirection(3);
        }

        moveCont();
    }

    /**
     * Moves the Worm continuously to the same direction.
     */
    public void moveCont() {
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
     * @return the bounds of Worm object.
     */
    Bounds getBounds() {
        Ellipse mato = new Ellipse(x + 18, y + 21, 18, 21);
        return mato.getLayoutBounds();
    }

    /**
     * Changes the state of the worm.
     * @param ws state of the Worm object.
     * @see Model.WormStates.WormState
     */
    public void changeState(WormState ws) {
        state = ws;
        state.action(this);
    }

    /**
     * Changes the state of the Worm to faster.
     * @see Model.WormStates.WormFast
     */
    public void fasterSpeed(Worm this) {
        changeState(WormFast.getInstance());
    }

    /**
     * Changes the state of the Worm to slower.
     * @see Model.WormStates.WormSlow
     */
    public void slowerSpeed() {
        changeState(WormSlow.getInstance());
    }

    /**
     * Changes the state of the shield of the Worm.
     * @see Model.WormStates.WormShield
     */
    public void shield() {
        changeState(WormShield.getInstance());
    }

    /**
     * Changes the state of the Worm to confused.
     * @see Model.WormStates.WormConfuse
     */
    public void confuse() {
        changeState(WormConfuse.getInstance());
    }

    /**
     * Adds an additional tail piece to this worm.
     * Adds 100 points to the worm.
     */
    public void addTail() {
        this.setPoints(this.getPoints()+100);
        tailList.add(new Tail((getTails().size()+1) * multiplier, playerNro));
    }
}