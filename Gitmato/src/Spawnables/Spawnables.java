/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spawnables;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Olli
 */
public interface Spawnables {
    int xe = 200;
    int ye = 200;
   void loadImage(String imageName);
   void init();
   public Rectangle getBounds();
   public int getX();
   public int getY();
   public void setX(int x);
   public void setY(int y);
   public Image getImage();
   public void randomizeXY();
}

    
