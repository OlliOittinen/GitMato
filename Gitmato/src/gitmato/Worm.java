/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gitmato;

import java.awt.Image;
import java.awt.Rectangle;
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
    
    double nopeus = 3;
    

    public Worm() {

        initWorm();
    }

    private void initWorm() {
        ImageIcon kuvamato = new ImageIcon("src/Images/Matopala.png");

        image = kuvamato.getImage();

        x = 200;
        y = 800;
    }
    
    public void move() {
        if (x > 0 && dx < 0 || x < 960 && dx > 0) {
            if(suuntaAdv == 2){
                x += dx;
            }
            
        }
        if (y > 0 && dy < 0 || y < 950 && dy > 0) {
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
    
    public void setX(int luku) {
        this.x = luku;
    }

    public void setY(int luku) {
        this.y = luku;
    }

    public Image getImage() {
        return image;
    }
    
    public int getSuunta(){
        return suunta;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            if(suunta != 2){
                suunta = 1;
                suuntaAdv = 2;
            }
            
        }

        if (key == KeyEvent.VK_RIGHT) {
            if(suunta != 1){
            suunta = 2;
            suuntaAdv = 2;

            }
        }

        if (key == KeyEvent.VK_UP) {
            if(suunta != 4){
        
            suunta = 3;
            suuntaAdv = 1;
            }
        }

        if (key == KeyEvent.VK_DOWN) {
            if(suunta != 3){
        
            suunta = 4;
            suuntaAdv = 1;
            }
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
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, 50, 50);
    }
    
    
}
