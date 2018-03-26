/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import GUI.Matopeli;
import Model.Board;

import Model.Worm;
import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

/**
 * @author gedst
 */
public class PlayerController {

    private Matopeli GUI;
    private Board board;
    private ArrayList<Worm> worms;
    private String pelimoodi;


    public PlayerController(Board board, String pelimoodi) {
        this.board = board;
        this.pelimoodi = pelimoodi;
        worms = board.getWorms();
    }

    public void keyPressed(KeyEvent e) {

        pelimoodi = board.getPelimoodi();

        KeyCode key = e.getCode();
        if (key == KeyCode.LEFT) {
            if (worms.get(0).getSuunta() != 2) {
                if (worms.get(0).getSuunta() != 1 && (worms.get(0).getX() < 20 || worms.get(0).getX() > 740)) {
                    worms.get(0).setX(worms.get(0).getX() - 9);
                }
                worms.get(0).setSuunta(1);
                worms.get(0).setSuuntaAdv(2);
            }

        }

        if (key == KeyCode.RIGHT) {

            if (worms.get(0).getSuunta() != 1) {
                if (worms.get(0).getSuunta() != 2 && (worms.get(0).getX() < 20 || worms.get(0).getX() > 740)) {
                    worms.get(0).setX(worms.get(0).getX() + 9);
                }
                worms.get(0).setSuunta(2);
                worms.get(0).setSuuntaAdv(2);

            }
        }

        if (key == KeyCode.UP) {
            if (worms.get(0).getSuunta() != 4) {
                if (worms.get(0).getSuunta() != 3 && (worms.get(0).getY() < 20 || worms.get(0).getY() > 530)) {
                    worms.get(0).setY(worms.get(0).getY() - 9);
                }
                worms.get(0).setSuunta(3);
                worms.get(0).setSuuntaAdv(1);
            }
        }

        if (key == KeyCode.DOWN) {
            if (worms.get(0).getSuunta() != 3) {
                if (worms.get(0).getSuunta() != 4 && (worms.get(0).getY() < 20 || worms.get(0).getY() > 530)) {
                    worms.get(0).setY(worms.get(0).getY() + 9);

                }
                worms.get(0).setSuunta(4);
                worms.get(0).setSuuntaAdv(1);

            }
        }


        // controlls for player 2 
        if (pelimoodi == "versus") {
            if (key == KeyCode.A) {


                if (worms.get(1).getSuunta() != 2) {
                    if (worms.get(1).getSuunta() != 1 && (worms.get(1).getX() < 20 || worms.get(1).getX() > 740)) {
                        worms.get(1).setX(worms.get(1).getX() - 9);
                    }
                    worms.get(1).setSuunta(1);
                    worms.get(1).setSuuntaAdv(2);
                }

            }

            if (key == KeyCode.D) {
                if (worms.get(1).getSuunta() != 1) {
                    if (worms.get(1).getSuunta() != 2 && (worms.get(1).getX() < 20 || worms.get(1).getX() > 740)) {
                        worms.get(1).setX(worms.get(1).getX() + 9);
                    }
                    worms.get(1).setSuunta(2);
                    worms.get(1).setSuuntaAdv(2);

                }
            }

            if (key == KeyCode.W) {
                if (worms.get(1).getSuunta() != 4) {
                    if (worms.get(1).getSuunta() != 3 && (worms.get(1).getY() < 20 || worms.get(1).getY() > 530)) {
                        worms.get(1).setY(worms.get(1).getY() - 9);
                    }

                    worms.get(1).setSuunta(3);
                    worms.get(1).setSuuntaAdv(1);
                }
            }

            if (key == KeyCode.S) {
                if (worms.get(1).getSuunta() != 3) {
                    if (worms.get(1).getSuunta() != 4 && (worms.get(1).getY() < 20 || worms.get(1).getY() > 530)) {
                        worms.get(1).setY(worms.get(1).getY() + 9);
                    }

                    worms.get(1).setSuunta(4);
                    worms.get(1).setSuuntaAdv(1);
                }
            }
        }

        //GAME RESET ----------------------------------------------------------------------------------------------------
        if (key == KeyCode.SPACE || key == KeyCode.ENTER) {
            board.restartGame();
        }
        if (key == KeyCode.H) {
            board.submitHighscore();
        }
    }


}

