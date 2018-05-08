/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 *
 *
 * @author Eero, Max, Olli
 */
package Model;

import Spawnables.*;
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

class Bot {
    private ArrayList<Worm> worms;
    private Worm worm2;
    private Bombs bombs;
    private ArrayList<Spawnables> pickableList;
    private Laser laser;
    private ArrayList<Tail> body;


    /**
     * Class Constructor.
     *
     * @param board board that the Bot uses as its field.
     */

    Bot(Board board) {
        worms = board.getWorms();
        worm2 = board.getWorm2();
        bombs = (Bombs) board.getPickableList().get(6);
        pickableList = board.getPickableList();
        laser = (Laser) board.getPickableList().get(7);
        body = board.getTailList();
    }

    /**
     * Controls the movements of the AI controlled worm.
     * Compares location of the worm to locations of the pickables.
     * Evades player and walls using additional hitboxes and compared locations.
     * Creates hitboxes for bombs for easy comparing.
     */
    void runBotRun() {

        Circle pb1 = bombs.getBoundsBombs(1);
        Circle pb2 = bombs.getBoundsBombs(2);
        Circle pb3 = bombs.getBoundsBombs(3);
        Circle pb4 = bombs.getBoundsBombs(4);
        Circle pb5 = bombs.getBoundsBombs(5);
        Circle pb6 = bombs.getBoundsBombs(6);


        for (int i = 0; i < pickableList.size(); i++) {

            if (worms.get(1).getX() < (pickableList.get(i).getX() + 10) && worms.get(1).getX() > (pickableList.get(i).getX() - 10) && !worms.get(1).getReverse()) {


                if (worms.get(1).getY() < pickableList.get(i).getY()) {
                    BotTurnDown();
                } else {
                    BotTurnUp();
                }
            }

            if (worms.get(1).getY() < (pickableList.get(i).getY() + 10) && worms.get(1).getY() > (pickableList.get(i).getY() - 10) && !worms.get(1).getReverse()) {

                if (worms.get(1).getX() < pickableList.get(i).getX()) {
                    BotTurnRight();
                } else {
                    BotTurnLeft();
                }
            }
        }

        if (worms.get(1).getX() < 20 && worms.get(1).getDirection() != 4) {
            if (worms.get(1).getDirection() != 2) {
                if (worm2.getY() > pickableList.get(0).getY()) {
                    BotTurnUp();
                } else {
                    BotTurnDown();
                }
                worms.get(1).setX(25);
            }

            if (worms.get(1).getReverse()) {
                if (worm2.getY() > pickableList.get(0).getY()) {
                    BotTurnDown();
                } else {
                    BotTurnUp();
                }
                worms.get(1).setX(25);
            }

        }

        if (worms.get(1).getX() > 715 && worms.get(1).getDirection() != 3) {
            if (worms.get(1).getDirection() != 1) {
                if (worm2.getY() > pickableList.get(0).getY()) {
                    BotTurnUp();
                } else {
                    BotTurnDown();
                }
                worms.get(1).setX(710);
            }

            if (worms.get(1).getReverse()) {
                if (worm2.getY() > pickableList.get(0).getY()) {
                    BotTurnDown();
                } else {
                    BotTurnUp();
                }
                worms.get(1).setX(710);
            }

        }

        if (worms.get(1).getY() > 540 && worms.get(1).getDirection() != 2) {
            if (worms.get(1).getDirection() != 3) {
                if (worm2.getX() > pickableList.get(0).getX()) {
                    BotTurnLeft();
                } else {
                    BotTurnRight();
                }
                worms.get(1).setY(535);
            }

            if (worms.get(1).getReverse()) {
                if (worm2.getX() > pickableList.get(0).getX()) {
                    BotTurnRight();
                } else {
                    BotTurnLeft();
                }
                worms.get(1).setY(535);
            }

        }

        if ((worms.get(1).getY() < 20 && worms.get(1).getDirection() != 1)) {
            if (worms.get(1).getDirection() != 4) {
                if (worm2.getX() > pickableList.get(0).getX()) {
                    BotTurnLeft();
                } else {
                    BotTurnRight();
                }
                worms.get(1).setY(25);
            }

            if (worms.get(1).getReverse()) {
                if (worm2.getX() > pickableList.get(0).getX()) {
                    BotTurnRight();
                } else {
                    BotTurnLeft();
                }
                worms.get(1).setY(25);
            }

        }

        Bounds AIleft = getBoundsLeft();
        for (int i = 0; i < body.size(); i++) {
            Bounds MatotailForAI = body.get(i).getBounds();

            Rectangle l2 = laser.getBoundsB();

            if ((AIleft.intersects(MatotailForAI) || (pb1.intersects(AIleft) || pb2.intersects(AIleft) || pb3.intersects(AIleft) || pb4.intersects(AIleft) || pb5.intersects(AIleft) || pb6.intersects(AIleft) || (l2.intersects(AIleft) && laser.getHorizontal()) || (l2.intersects(AIleft) && !l2.intersects(worms.get(1).getBounds()))) && (worms.get(1).getDirection() == 1 || worms.get(1).getReverse()))) {

                if (worms.get(1).getY() < 300) {
                    BotTurnDown();
                } else {
                    BotTurnUp();
                }

            }


        }

        Bounds AIright = getBoundsRight();
        for (int i = 0; i < body.size(); i++) {
            Bounds MatotailForAI = body.get(i).getBounds();

            Rectangle l2 = laser.getBoundsB();

            if ((AIright.intersects(MatotailForAI) || pb1.intersects(AIright) || pb2.intersects(AIright) || pb3.intersects(AIright) || pb4.intersects(AIright) || pb5.intersects(AIright) || pb6.intersects(AIright) || (l2.intersects(AIright) && laser.getHorizontal()) || (l2.intersects(AIright) && !l2.intersects(worms.get(1).getBounds()))) && (worms.get(1).getDirection() == 2 || worms.get(1).getReverse())) {


                if (worms.get(1).getY() < 300) {
                    BotTurnDown();
                } else {
                    BotTurnUp();
                }


            }


        }

        Bounds AIup = getBoundsUp();
        for (int i = 0; i < body.size(); i++) {
            Bounds MatotailForAI = body.get(i).getBounds();

            Rectangle l2 = laser.getBoundsB();

            if ((AIup.intersects(MatotailForAI) || pb1.intersects(AIup) || pb2.intersects(AIup) || pb3.intersects(AIup) || pb4.intersects(AIup) || pb5.intersects(AIup) || pb6.intersects(AIup) || (l2.intersects(AIup) && !laser.getHorizontal()) || (l2.intersects(AIup) && !l2.intersects(worms.get(1).getBounds()))) && (worms.get(1).getDirection() == 3 || worms.get(1).getReverse())) {

                if (worms.get(1).getX() < 400) {
                    BotTurnRight();
                } else {
                    BotTurnLeft();
                }



            }


        }

        Bounds AIdown = getBoundsDown();
        for (int i = 0; i < body.size(); i++) {
            Bounds MatotailForAI = body.get(i).getBounds();

            Rectangle l2 = laser.getBoundsB();


            if ((AIdown.intersects(MatotailForAI) || pb1.intersects(AIdown) || pb2.intersects(AIdown) || pb3.intersects(AIdown) || pb4.intersects(AIdown) || pb5.intersects(AIdown) || pb6.intersects(AIdown) || (l2.intersects(AIdown) && !l2.intersects(worms.get(1).getBounds()))) && (worms.get(1).getDirection() == 4 || worms.get(1).getReverse())) {


                if (worms.get(1).getX() < 400) {
                    BotTurnRight();
                } else {
                    BotTurnLeft();
                }

            }



        }
    }

    private Bounds getBoundsLeft() {
        Rectangle r = new Rectangle(worms.get(1).getX() - 50, worms.get(1).getY(), 35, 42);
        return r.getLayoutBounds();
    }

    private Bounds getBoundsRight() {
        Rectangle r = new Rectangle(worms.get(1).getX(), worms.get(1).getY(), 85, 42);
        return r.getLayoutBounds();
    }

    private Bounds getBoundsUp() {
        Rectangle r = new Rectangle(worms.get(1).getX(), worms.get(1).getY() - 50, 35, 42);
        return r.getLayoutBounds();
    }

    private Bounds getBoundsDown() {
        Rectangle r = new Rectangle(worms.get(1).getX(), worms.get(1).getY(), 35, 92);
        return r.getLayoutBounds();
    }

    private void BotTurnLeft() {
        worms.get(1).setDirection(1);
        worms.get(1).setDirectionAdv(2);

    }

    private void BotTurnRight() {
        worms.get(1).setDirection(2);
        worms.get(1).setDirectionAdv(2);

    }

    private void BotTurnUp() {
        worms.get(1).setDirection(3);
        worms.get(1).setDirectionAdv(1);

    }

    void BotTurnDown() {
        worms.get(1).setDirection(4);
        worms.get(1).setDirectionAdv(1);

    }
}
