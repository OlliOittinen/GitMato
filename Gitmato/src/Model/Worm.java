/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

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
    private int playerNro;
    private boolean shield = false; //shield power-up
    private boolean reverse = false; //Reverse debuff up
    private int points;


    private Image image;    
    private ImageIcon wormup;
    private ImageIcon wormdown;
    private ImageIcon wormleft;
    private ImageIcon wormright;
    private ImageIcon shieldup;
    private ImageIcon shielddown;
    private ImageIcon shieldleft;
    private ImageIcon shieldright;

    
    double nopeus = 2;
    private int life=1;

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
    
    public Worm(int p) {  
        initWorm(p);
    }

    private void initWorm(int p) {
        this.playerNro = p;
        if(playerNro==1){
             wormup = new ImageIcon("src/Images/RedWormUp(800x600).png");
             wormdown = new ImageIcon("src/Images/RedWormDown(800x600).png");
             wormleft = new ImageIcon("src/Images/RedWormLeft(800x600).png");
             wormright = new ImageIcon("src/Images/RedWormRight(800x600).png");
             shieldup = new ImageIcon("src/Images/RedWormUpShield.png");
             shieldleft = new ImageIcon("src/Images/RedWormLeftShield.png");
             shieldright = new ImageIcon("src/Images/RedWormRightShield.png");
             shielddown = new ImageIcon("src/Images/RedWormDownShield.png");
            x = 200;
            y = 279; //oma puoli kentästä-kuvan korkeus
        }
        if(playerNro==2){
             wormup = new ImageIcon("src/Images/BlueWormUp(800x600).png");
             wormdown = new ImageIcon("src/Images/BlueWormDown(800x600).png");
             wormleft = new ImageIcon("src/Images/BlueWormLeft(800x600).png");
             wormright = new ImageIcon("src/Images/BlueWormRight(800x600).png");
             shieldup = new ImageIcon("src/Images/BlueWormUpShield.png");             
             shieldleft = new ImageIcon("src/Images/BlueWormLeftShield.png");
             shieldright = new ImageIcon("src/Images/BlueWormRightShield.png");
             shielddown = new ImageIcon("src/Images/BlueWormDownShield.png");

            x = 565; //kentän puoliväli-kuvan leveys
            y = 279; //oma puoli kentästä-kuvan korkeus
        }
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
    
    public void setImage(Image img) {
        this.image = img;
    }
    
    public int getSuunta(){
        return suunta;
    }

    public void setSuunta(int s){
        this.suunta = s;
    }
    public void setSuuntaAdv(int a){
        this.suuntaAdv = a;
    }
    
     public double getNopeus() {
        return nopeus;
    }

    public void setNopeus(double nopeus) {
        this.nopeus = nopeus;
    }
    
    public boolean getShield(Worm worm) {
        return this.shield;
    }
    
    public void setShield(boolean active) {
        this.shield = active;
    }
    
    public void setReverse(boolean active) {
        this.reverse = active;
    }
    
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public void randomizeXY() {
        setX((int) (Math.random() * 750));
        setY((int) (Math.random() * 550));
    }
    
    public void moveCont(){
        //if shield is NOT active on worm
        if(suunta == 1 && !(getShield(this))){
            setImage (wormleft.getImage());
            dx = -1 * nopeus;
        }
        
        if(suunta == 2 && !(getShield(this))){
            setImage (wormright.getImage());
            dx = 1 * nopeus;
        }
        
        if(suunta == 3 && !(getShield(this))){
            setImage (wormup.getImage());
            dy = -1 * nopeus;
            
        }
        
        if(suunta == 4 && !(getShield(this))){
            setImage (wormdown.getImage());
            dy = 1 * nopeus;
            
        }
        //if shield IS active on worm
        if(suunta == 1 && (getShield(this))){
            setImage (shieldleft.getImage());
            dx = -1 * nopeus;
        }
        if(suunta == 2 && (getShield(this))){
            setImage (shieldright.getImage());
            dx = 1 * nopeus;
        }
        if(suunta == 3 && (getShield(this))){
            setImage (shieldup.getImage());
            dy = -1 * nopeus;
        }
        if(suunta == 4 && (getShield(this))){
            setImage (shielddown.getImage());
            dy = 1 * nopeus;
        }
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, 35, 42);
    }
    
    
}