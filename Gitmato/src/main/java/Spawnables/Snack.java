/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spawnables;

import Model.Board;
import Model.Tail;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * @author maxki
 */
public class Snack extends AbstractSpawnables {

    /**
     * Class constructor
     */
    public Snack() {
        init();
    }

    /**
     * Randomizes the location of the snack icon at start.
     */
    @Override
    public void init() {
        setX(600 - (int) (Math.random() * 400));
        setY(200);
    }

    /**
     * Empty method, overridden using the one with @param Board
     *
     */
    @Override
    public void randomizeIconLocation() {
    }

    /**
     * This method randomizes the location for the icon
     * to be placed using Math.random (ie. pseudo-random)
     * and uses the size of the map to do so.
     * @param board the board where the tails are retrieved from
     */
    public void randomizeIconLocation(Board board) {
        ArrayList<Tail> t1 = board.getTailList();
        ArrayList<Tail> t2 = board.getTailList2();
        ArrayList<Rectangle> trees = board.getTreeBoxes();

        do {
            setX((int) (Math.random() * 750));
            setY((int) (Math.random() * 550));
        } while (checkCollision(t1, t2) || checkCollision(trees));
    }

    private boolean checkCollision(ArrayList<Rectangle> trees) {
        boolean intersects = false;
        for (Rectangle r : trees) {
            if (r.getLayoutBounds().intersects(this.getBoundsForIcon())) {
                intersects = true;
            }
        }
        return intersects;
    }

    private boolean checkCollision(ArrayList<Tail> t1, ArrayList<Tail> t2) {
        boolean intersects = false;
        for (Tail aT1 : t1) {
            if (aT1.getBounds().intersects(this.getBoundsForIcon())) {
                intersects = true;
            }
        }
        for (Tail aT2 : t2) {
            if (aT2.getBounds().intersects(this.getBoundsForIcon())) {
                intersects = true;
            }
        }
        return intersects;
    }
}

