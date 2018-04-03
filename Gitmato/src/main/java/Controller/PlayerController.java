/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Board;

import Model.Worm;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

/**
 * @author gedst
 */
public class PlayerController {

    private Board board;
    private ArrayList<Worm> worms;
    private String gameMode;

    /**
     * Class constructor.
     * @param board the object given to access Worm objects.
     * @param gameMode game mode type.
     */
    public PlayerController(Board board, String gameMode) {
        this.board = board;
        this.gameMode = gameMode;
        worms = board.getWorms();
    }

    /**
     * Handles the player's key presses.
     * Checks what key is pressed and changes the worms direction accordingly.
     * Is called every time a player presses a key.
     * @param e An event which indicates that a keystroke occurred in a component.
     *
     */
    public void keyPressed(KeyEvent e) {

        gameMode = board.getGameMode();

        KeyCode key = e.getCode();
        if (key == KeyCode.LEFT) {
            if (worms.get(0).getDirection() != 2) {
                if (worms.get(0).getDirection() != 1 && (worms.get(0).getX() < 20 || worms.get(0).getX() > 740)) {
                    worms.get(0).setX(worms.get(0).getX() - 9);
                }
                worms.get(0).setDirection(1);
                worms.get(0).setDirectionAdv(2);
            }

        }

        if (key == KeyCode.RIGHT) {

            if (worms.get(0).getDirection() != 1) {
                if (worms.get(0).getDirection() != 2 && (worms.get(0).getX() < 20 || worms.get(0).getX() > 740)) {
                    worms.get(0).setX(worms.get(0).getX() + 9);
                }
                worms.get(0).setDirection(2);
                worms.get(0).setDirectionAdv(2);

            }
        }

        if (key == KeyCode.UP) {
            if (worms.get(0).getDirection() != 4) {
                if (worms.get(0).getDirection() != 3 && (worms.get(0).getY() < 20 || worms.get(0).getY() > 530)) {
                    worms.get(0).setY(worms.get(0).getY() - 9);
                }
                worms.get(0).setDirection(3);
                worms.get(0).setDirectionAdv(1);
            }
        }

        if (key == KeyCode.DOWN) {
            if (worms.get(0).getDirection() != 3) {
                if (worms.get(0).getDirection() != 4 && (worms.get(0).getY() < 20 || worms.get(0).getY() > 530)) {
                    worms.get(0).setY(worms.get(0).getY() + 9);

                }
                worms.get(0).setDirection(4);
                worms.get(0).setDirectionAdv(1);

            }
        }


        // controls for player 2
        if (gameMode == "versus") {
            if (key == KeyCode.A) {


                if (worms.get(1).getDirection() != 2) {
                    if (worms.get(1).getDirection() != 1 && (worms.get(1).getX() < 20 || worms.get(1).getX() > 740)) {
                        worms.get(1).setX(worms.get(1).getX() - 9);
                    }
                    worms.get(1).setDirection(1);
                    worms.get(1).setDirectionAdv(2);
                }

            }

            if (key == KeyCode.D) {
                if (worms.get(1).getDirection() != 1) {
                    if (worms.get(1).getDirection() != 2 && (worms.get(1).getX() < 20 || worms.get(1).getX() > 740)) {
                        worms.get(1).setX(worms.get(1).getX() + 9);
                    }
                    worms.get(1).setDirection(2);
                    worms.get(1).setDirectionAdv(2);

                }
            }

            if (key == KeyCode.W) {
                if (worms.get(1).getDirection() != 4) {
                    if (worms.get(1).getDirection() != 3 && (worms.get(1).getY() < 20 || worms.get(1).getY() > 530)) {
                        worms.get(1).setY(worms.get(1).getY() - 9);
                    }

                    worms.get(1).setDirection(3);
                    worms.get(1).setDirectionAdv(1);
                }
            }

            if (key == KeyCode.S) {
                if (worms.get(1).getDirection() != 3) {
                    if (worms.get(1).getDirection() != 4 && (worms.get(1).getY() < 20 || worms.get(1).getY() > 530)) {
                        worms.get(1).setY(worms.get(1).getY() + 9);
                    }

                    worms.get(1).setDirection(4);
                    worms.get(1).setDirectionAdv(1);
                }
            }
        }
    }
}

