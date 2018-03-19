/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

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
    private int cordinateInt;
    
     public Tail(int ci, int playerNro) {
        this.cordinateInt = ci;
        this.playerNro = playerNro;
        initTail();
    }
    private void initTail(){
        if(playerNro==1){
            ImageIcon kuvamato = new ImageIcon("src/main/resources/images/RedWormTail(800x600).png");
            image = kuvamato.getImage();   
        }
         if(playerNro==2){
            ImageIcon kuvamato = new ImageIcon("src/main/resources/images/BlueWormTail(800x600).png");
            image = kuvamato.getImage();   
        }
    }
    
    public Image getImage() {
        return image;
    }
    public int getCordinateInt(){
        return cordinateInt;
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
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, 35, 42);
    }
}