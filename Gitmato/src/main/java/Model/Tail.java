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
    private Image image;
    private int x;
    private int y;
    private int playerNro;
    // luku jonka mukaan hakee cordinaateista itselleen arvon.
    private int coordinateInt;
    
     public Tail(int ci, int playerNro) {
        this.coordinateInt = ci;
        this.playerNro = playerNro;
        initTail();
    }
    private void initTail(){
        if(playerNro==1){
            ImageView kuvamato = new ImageView("src/main/resources/images/RedWormTail(800x600).png");
            image = kuvamato.getImage();   
        }
         if(playerNro==2){
            ImageView kuvamato = new ImageView("src/main/resources/images/BlueWormTail(800x600).png");
            image = kuvamato.getImage();   
        }
    }
    
    public Image getImage() {
        return image;
    }

    public int getCoordinateInt(){
        return coordinateInt;
    }
    
     public void setX(int luku) {
        this.x = luku;
    }

    public void setY(int luku) {
        this.y = luku;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public Bounds getBounds() {
        Rectangle tail = new Rectangle(x, y, 35, 42);
        return tail.getLayoutBounds();
    }
}