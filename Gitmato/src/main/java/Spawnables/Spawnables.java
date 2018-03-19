/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spawnables;

import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author Olli
 */
public interface Spawnables {
   void loadImage(String imageName);
   void init();
   Rectangle getBounds();
   int getX();
   int getY();
   void setX(int x);
   void setY(int y);
   Image getImage();
   void randomizePowerUpLocation();
}

    
