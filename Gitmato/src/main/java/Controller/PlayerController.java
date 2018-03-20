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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

/**
 * @author gedst
 */
public class PlayerController {

    private Matopeli GUI;
    private Board board;
    private ArrayList<Worm> worms = board.getWorms();
    private String pelimoodi = board.getPelimoodi();

    public PlayerController() {

    }

    public void keyPressed(KeyEvent e) {

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

    /*public void actionPerformed(ActionEvent e) {

        Worm worm = board.getWorm();
        Worm worm2 = board.getWorm2();

        board.checkCollisions();
        worm.move();
        worm.moveCont();
        worm2.move();
        worm2.moveCont();
        //tallennnetaan wormin coordinaatit yhteen 2D muuttujaan
        int x = worm.getX();
        int y = worm.getY();
        int x2 = worm2.getX();
        int y2 = worm2.getY();
        Point2D p = new Point2D(x, y);
        Point2D p2 = new Point2D(x2, y2);
        //Lisätään coortinaatit listan cordinates alkuun (0).
        //siirtää automaattisesti taulukon arvot yhden eteenpäin, 0->1

        List coordinates = board.getCoordinates();
        List coordinates2 = board.getCoordinates2();


        coordinates.add(0, p);
        coordinates2.add(0, p2);

        //jos lista liian suuri poistetaan viimeinen
        if (coordinates.size() >= 10000) {
            coordinates.remove(coordinates.size() - 1);
        }

        if (coordinates2.size() >= 10000) {
            coordinates2.remove(coordinates2.size() - 1);
        }

        //Päivitetään jokaisen "Tail" olion coordinaatit

        List<Tail> tailList = board.getTailList();
        List<Tail> tailList2 = board.getTailList2();

        for (int i = 0; i < tailList.size(); i++) {
            int f = tailList.get(i).getCoordinateInt();
            p = (Point2D) coordinates.get(f);
            x = (int) p.getX();
            y = (int) p.getY();
            tailList.get(i).setX(x);
            tailList.get(i).setY(y);
        }

        for (int i = 0; i < tailList2.size(); i++) {
            int f = tailList2.get(i).getCoordinateInt();
            p2 = (Point2D) coordinates2.get(f);
            x2 = (int) p2.getX();
            y2 = (int) p2.getY();
            tailList2.get(i).setX(x2);
            tailList2.get(i).setY(y2);
        }


        //botti ja sen toiminta
        if (pelimoodi == "vs AI") {
            bot.BlueAIBot();
        }

    }*/
}

