/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gitmato;

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
    
    
    private void initTail(){
        ImageIcon kuvamato = new ImageIcon("src/images/Matopala.png");
        
        image = kuvamato.getImage();

        
        
    }
    
    public Image getImage() {
        return image;
    }
}
