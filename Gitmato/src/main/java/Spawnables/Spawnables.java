/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spawnables;

<<<<<<< HEAD

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
=======
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;


>>>>>>> 0a5cd5d6fdb6519ca81d422f407ae2389cf7879a

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

    
