/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gitmato;

import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author maxki
 */
public class Worm {
    
    private double dx;
    private double dy;
    private int x;
    private int y;
    private int suunta = 1;
    private int suuntaAdv = 0;
    private Image image;
    
    double nopeus = 10;
    

    public Worm() {

        initWorm();
    }

    private void initWorm() {
        ImageIcon kuvamato = new ImageIcon("src/images/Matopala.png");

        image = kuvamato.getImage();

        x = 200;
        y = 800;
    }
    
    public void move() {
        if (x > 0 && dx < 0 || x < 450 && dx > 0) {
            if(suuntaAdv == 2){
                x += dx;
            }
            
        }
        if (y > 0 && dy < 0 || y < 875 && dy > 0) {
            if(suuntaAdv == 1){
                y += dy;
            }
            
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            suunta = 1;
            suuntaAdv = 2;
            
            
        }

        if (key == KeyEvent.VK_RIGHT) {
            suunta = 2;
            suuntaAdv = 1;

        }

        if (key == KeyEvent.VK_UP) {
            suunta = 3;
            suuntaAdv = 1;

        }

        if (key == KeyEvent.VK_DOWN) {
            suunta = 4;
            suuntaAdv = 2;

        }
    }
    
    public void moveCont(){
        if(suunta == 1){
            dx = -1 * nopeus;
        }
        
        if(suunta == 2){
            dx = 1 * nopeus;
        }
        
        if(suunta == 3){
            dy = -1 * nopeus;
        }
        
        if(suunta == 4){
            dy = 1 * nopeus;
        }
    }
    
    
}
