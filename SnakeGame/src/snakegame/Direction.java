/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

/**
 *
 * @author gedst
 */
public enum Direction {
    LEFT, RIGHT, UP, DOWN;

    public boolean compatibleWith(Direction newDirection) {
        if (this.equals(LEFT) || this.equals(RIGHT)) {
            return UP.equals(newDirection) || DOWN.equals(newDirection); 
        } else {
            return LEFT.equals(newDirection) || RIGHT.equals(newDirection);
        }
    }
}