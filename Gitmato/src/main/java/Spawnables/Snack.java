/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spawnables;

import Model.Board;
import Model.Tail;

import java.util.ArrayList;

/**
 * @author maxki
 */
public class Snack extends AbstractSpawnables {

    /**
     * Class constructor, calls on init();
     */
    public Snack() {
        init();
    }

    /**
     * Randomizes the location of the snack icon at start.
     */
    //on init, set the starting snack to be at a random x-location between 600 & 200
    @Override
    public void init() {
        setX(600 - (int) (Math.random() * 400));
        setY(200);
    }

    @Override
    public void randomizeIconLocation() {
    }

    /**
     * This method randomizes the location for the icon
     * to be placed using Math.random (ie. pseudo-random)
     * and uses the size of the map to do so
     */
    public void randomizeIconLocation(Board board) {
        ArrayList<Tail> t1 = board.getTailList();
        ArrayList<Tail> t2 = board.getTailList();
        do {
            setX((int) (Math.random() * 750));
            setY((int) (Math.random() * 550));
        } while (checkCollision(t1, t2));
    }

    private boolean checkCollision(ArrayList<Tail> t1, ArrayList<Tail> t2) {
        boolean intersects = false;
        for (int i = 0; i < t1.size(); i++) {
            if (t1.get(i).getBounds().intersects(this.getBoundsForIcon())) {
                intersects = true;
            }
        }
        for (int i = 0; i < t2.size(); i++) {
            if (t2.get(i).getBounds().intersects(this.getBoundsForIcon())) {
                intersects = true;
            }
        }
        return intersects;
    }
}

