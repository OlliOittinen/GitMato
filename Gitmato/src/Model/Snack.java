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
public class Snack {
    private int xe;
    private int ye;
    
    
    //private int width;
    //private int height;

    private Image image;

    public Snack() {

        initSnack();
    }

    public void initSnack() {
        
        ImageIcon kuva = new ImageIcon("src/Images/AppleActual.png");
        image = kuva.getImage();
            
        ye = 200;
        xe = 200;
    }
    
    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return xe;
    }

    public int getY() {
        return ye;
    }
    
    public void setX(int x){
        this.xe = x;
    }
    
    public void setY(int y){
        this.ye = y;
    }

    
    public Rectangle getBounds() {
        return new Rectangle(xe+5, ye+5, 40, 40);
    }
}

