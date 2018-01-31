/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author maxki
 */
public class Tail extends Worm {
    private Image image;
    private int x;
    private int y;
    // luku jonka mukaan hakee cordinaateista itselleen arvon.
    private int cordinateInt;
    
     public Tail(int ci) {
        this.cordinateInt = ci;
        initTail();
    }
    private void initTail(){
        ImageIcon kuvamato = new ImageIcon("src/images/MatoOsa.png");
        image = kuvamato.getImage();   
    }
    
    @Override
    public Image getImage() {
        return image;
    }
    public int getCordinateInt(){
        return cordinateInt;
    }
}