/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*

package main.java.Model;

*/
/**
 *
 * @author Eero
 *//*

public class Bot {
        public void BlueAIBot() {

        Ellipse2D pb1 = bombs.getBoundsBombs(1);
        Ellipse2D pb2 = bombs.getBoundsBombs(2);
        Ellipse2D pb3 = bombs.getBoundsBombs(3);
        Ellipse2D pb4 = bombs.getBoundsBombs(4);
        Ellipse2D pb5 = bombs.getBoundsBombs(5);
        Ellipse2D pb6 = bombs.getBoundsBombs(6);

        if (tailNro2 > 3) {
            if (worm2.getBounds().intersects(body2.get(body2.size() - 1).getBounds()) && body2.size() > 3) {
                BotTurnDown();
            }

        }
        for (int i = 0; i < pickableList.size(); i++) {

            if (worms.get(1).getX() < (pickableList.get(i).getX() + 10) && worms.get(1).getX() > (pickableList.get(i).getX() - 10) && !worms.get(1).getReverse(worms.get(1))) {
                if (worms.get(1).getY() < pickableList.get(i).getY()) {
                    BotTurnDown();

                    //alas
                } else {
                    BotTurnUp();

                    //ylös
                }

            }

            if (worms.get(1).getY() < (pickableList.get(i).getY() + 10) && worms.get(1).getY() > (pickableList.get(i).getY() - 10) && !worms.get(1).getReverse(worms.get(1))) {

                if (worms.get(1).getX() < pickableList.get(i).getX()) {
                    BotTurnRight();

                    //oikea
                } else {
                    BotTurnLeft();

                    //vasen
                }

            }
        }

        if (worms.get(1).getX() < 20 && worms.get(1).getSuunta() != 4) {
            if (worms.get(1).getSuunta() != 2) {
                BotTurnUp();
                worms.get(1).setX(25);
            }

            if (worms.get(1).getReverse(worms.get(1))) {
                BotTurnUp();
                worms.get(1).setX(25);
            }

        }

        if (worms.get(1).getX() > 715 && worms.get(1).getSuunta() != 3) {
            if (worms.get(1).getSuunta() != 1) {
                BotTurnDown();
                worms.get(1).setX(710);
            }

            if (worms.get(1).getReverse(worms.get(1))) {
                BotTurnDown();
                worms.get(1).setX(710);
            }

        }

        if (worms.get(1).getY() > 540 && worms.get(1).getSuunta() != 2) {
            if (worms.get(1).getSuunta() != 3) {
                BotTurnLeft();
                worms.get(1).setY(535);
            }

            if (worms.get(1).getReverse(worms.get(1))) {
                BotTurnLeft();
                worms.get(1).setY(535);
            }

        }

        if ((worms.get(1).getY() < 20 && worms.get(1).getSuunta() != 1)) {
            if (worms.get(1).getSuunta() != 4) {
                BotTurnRight();
                worms.get(1).setY(25);
            }

            if (worms.get(1).getReverse(worms.get(1))) {
                BotTurnRight();
                worms.get(1).setY(25);
            }

        }

        Rectangle AIleft = getBoundsLeft();
        for (int i = 0; i < body.size(); i++) {
            Rectangle MatotailForAI = body.get(i).getBounds();

            Rectangle2D l2 = laser.getBoundsB();
            if ((AIleft.intersects(MatotailForAI) || pb1.intersects(AIleft) || pb2.intersects(AIleft) || pb3.intersects(AIleft) || pb4.intersects(AIleft) || pb5.intersects(AIleft) || pb6.intersects(AIleft) || (l2.intersects(AIleft) && laser.getHorizontal()) || (l2.intersects(AIleft) && !l2.intersects(worms.get(1).getBounds()))) && (worms.get(1).getSuunta() == 1 || worms.get(1).getReverse(worms.get(1)))) {
                int n = (int) (Math.random() * 1);

                switch (n) {
                    case 0:
                        do {
                            BotTurnUp();
                            break;
                        } while (l2.intersects(worms.get(1).getBounds()));

                    case 1:
                        do {
                            BotTurnDown();
                            break;
                        } while (l2.intersects(worms.get(1).getBounds()));
                }
            }

        }

        Rectangle AIright = getBoundsRight();
        for (int i = 0; i < body.size(); i++) {
            Rectangle MatotailForAI = body.get(i).getBounds();

            Rectangle2D l2 = laser.getBoundsB();
            if ((AIright.intersects(MatotailForAI) || pb1.intersects(AIright) || pb2.intersects(AIright) || pb3.intersects(AIright) || pb4.intersects(AIright) || pb5.intersects(AIright) || pb6.intersects(AIright) || (l2.intersects(AIright) && laser.getHorizontal()) || (l2.intersects(AIright) && !l2.intersects(worms.get(1).getBounds()))) && (worms.get(1).getSuunta() == 2 || worms.get(1).getReverse(worms.get(1)))) {
                int n = (int) (Math.random() * 2);

                switch (n) {
                    case 0:
                        do {
                            BotTurnUp();
                            break;
                        } while (l2.intersects(worms.get(1).getBounds()));

                    case 1:
                        do {
                            BotTurnDown();
                            break;
                        } while (l2.intersects(worms.get(1).getBounds()));
                }
            }

        }

        Rectangle AIup = getBoundsUp();
        for (int i = 0; i < body.size(); i++) {
            Rectangle MatotailForAI = body.get(i).getBounds();

            Rectangle2D l2 = laser.getBoundsB();
            if ((AIup.intersects(MatotailForAI) || pb1.intersects(AIup) || pb2.intersects(AIup) || pb3.intersects(AIup) || pb4.intersects(AIup) || pb5.intersects(AIup) || pb6.intersects(AIup) || (l2.intersects(AIup) && !laser.getHorizontal()) || (l2.intersects(AIup) && !l2.intersects(worms.get(1).getBounds()))) && (worms.get(1).getSuunta() == 3 || worms.get(1).getReverse(worms.get(1)))) {
                int n = (int) (Math.random() * 2);

                switch (n) {
                    case 0:
                        do {
                            BotTurnLeft();
                            break;
                        } while (l2.intersects(worms.get(1).getBounds()));

                    case 1:
                        do {
                            BotTurnLeft();
                            break;
                        } while (l2.intersects(worms.get(1).getBounds()));
                }
            }
            //kek
        }

        Rectangle AIdown = getBoundsDown();
        for (int i = 0; i < body.size(); i++) {
            Rectangle MatotailForAI = body.get(i).getBounds();

            Rectangle2D l2 = laser.getBoundsB();
            if ((AIdown.intersects(MatotailForAI) || pb1.intersects(AIdown) || pb2.intersects(AIdown) || pb3.intersects(AIdown) || pb4.intersects(AIdown) || pb5.intersects(AIdown) || pb6.intersects(AIdown) || (l2.intersects(AIdown) && !laser.getHorizontal()) || (l2.intersects(AIdown) && !l2.intersects(worms.get(1).getBounds()))) && (worms.get(1).getSuunta() == 4 || worms.get(1).getReverse(worms.get(1)))) {
                int n = (int) (Math.random() * 2);

                switch (n) {
                    case 0:
                        do {
                            BotTurnLeft();
                            break;
                        } while (l2.intersects(worms.get(1).getBounds()));

                    case 1:
                        do {
                            BotTurnLeft();
                            break;
                        } while (l2.intersects(worms.get(1).getBounds()));
                }
            }

        }

    }

    public Rectangle getBoundsLeft() {
        return new Rectangle(worms.get(1).getX() - 50, worms.get(1).getY(), 35, 42);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle(worms.get(1).getX(), worms.get(1).getY(), 85, 42);
    }

    public Rectangle getBoundsUp() {
        return new Rectangle(worms.get(1).getX(), worms.get(1).getY() - 50, 35, 42);
    }

    public Rectangle getBoundsDown() {
        return new Rectangle(worms.get(1).getX(), worms.get(1).getY(), 35, 92);
    }

    public void BotTurnLeft() {
        worms.get(1).setSuunta(1);
        worms.get(1).setSuuntaAdv(2);
    }

    public void BotTurnRight() {
        worms.get(1).setSuunta(2);
        worms.get(1).setSuuntaAdv(2);
    }

    public void BotTurnUp() {
        worms.get(1).setSuunta(3);
        worms.get(1).setSuuntaAdv(1);
    }

    public void BotTurnDown() {
        worms.get(1).setSuunta(4);
        worms.get(1).setSuuntaAdv(1);
    }
}
*/
