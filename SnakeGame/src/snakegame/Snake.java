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
import java.util.LinkedList;

public class Snake {

    private LinkedList<Node> body = new LinkedList<Node>();

    public boolean isEatFood(Node food) {
        Node head = body.getFirst();
        return Math.abs(head.getX() - food.getX()) + Math.abs(head.getY() - food.getY()) == 0;
    }

    public Node move(Direction direction) {
        Node node = null;
        int headX = this.body.getFirst().getX();
        int headY = this.body.getFirst().getY();
        switch(direction) {
            case UP :
                node = new Node(headX, headY - 1);
                break;
            case RIGHT :
                node = new Node(headX + 1, headY);
                break;
            case DOWN :
                node = new Node(headX, headY + 1);
                break;
            case LEFT :
                node = new Node(headX - 1, headY);
                break;
        }
        this.body.addFirst(node);
        return body.removeLast();
    }

    public Node getHead() {
        return body.getFirst();
    }

    public Node addTail(Node area) {
        this.body.addLast(area);
        return area;
    }

    public LinkedList<Node> getBody() {
        return body;
    }
}
