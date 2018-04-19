/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;


/**
 *
 * @author maxki
 */
public class Tail{
    private int x;
    private int y;
    private int playerNumber;
    // luku jonka mukaan hakee cordinaateista itselleen arvon.
    private int coordinateInt;

    /**
     * Class constructor
     * @param ci Coordinate where the tail will be drawn.
     * @param playerNumber Number for the player.
     */
    public Tail(int ci, int playerNumber) {
        this.coordinateInt = ci;
        this.playerNumber = playerNumber;
        initTail();
    }

    private void initTail(){
        Image image;
        if(playerNumber==1){
            image = new Image("images/RedWormTail(800x600).png");
        }
         if(playerNumber==2){
            image = new Image("images/BlueWormTail(800x600).png");
        }
    }

    /**
     * Getter for CoordinateInt.
     * @return Coordinates where the tail will be drawn.
     */
    public int getCoordinateInt(){
        return coordinateInt;
    }

    /**
     * Setter for x coordinate.
     * @param luku Coordinate x of Tail object.
     */
    public void setX(int luku) {
        this.x = luku;
    }

    /**
     * Setter for x coordinate.
     * @param luku Coordinate x of Tail object.
     */
    public void setY(int luku) {
        this.y = luku;
    }
    /**
     * Getter for x coordinate.
     * @return x coordinate of Tail object.
     */

    public int getX() {
        return x;
    }
    /**
     * Getter for y coordinate.
     * @return y coordinate of Tail object.
     */
    public int getY() {
        return y;
    }

    /**
     * Creates Rectangle object for Tail for collision checking.
     * @return Bounds object based on Rectangle objects layout bounds.
     */
    public Bounds getBounds() {
        Rectangle tail = new Rectangle(x, y, 35, 42);
        return tail.getLayoutBounds();
    }
}