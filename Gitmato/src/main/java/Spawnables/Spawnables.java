/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spawnables;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;


/**
 *
 * @author Olli
 */
public interface Spawnables {

   void init();
   Bounds getBoundsForIcon();
   int getX();
   int getY();
   void setX(int x);
   void setY(int y);
   void randomizePowerUpLocation();

}

    
