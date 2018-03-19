/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;


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
    private ImageView wormup;
    private ImageView wormdown;
    private ImageView wormleft;
    private ImageView wormright;

    
    double nopeus = 2;
    private int life=3;

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
             wormup = new ImageView("src/main/resources/images/RedWormUp(800x600).png");
             wormdown = new ImageView("src/main/resources/images/RedWormDown(800x600).png");
             wormleft = new ImageView("src/main/resources/images/RedWormLeft(800x600).png");
             wormright = new ImageView("src/main/resources/images/RedWormRight(800x600).png");
            x = 200;
            y = 279; //oma puoli kentästä-kuvan korkeus
        }
        if(playerNro==2){
             wormup = new ImageView("src/main/resources/images/BlueWormUp(800x600).png");
             wormdown = new ImageView("src/main/resources/images/BlueWormDown(800x600).png");
             wormleft = new ImageView("src/main/resources/images/BlueWormLeft(800x600).png");
             wormright = new ImageView("src/main/resources/images/BlueWormRight(800x600).png");
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
    public boolean getReverse(Worm worm) {
        return this.reverse;
    }
    
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public void randomizeXY() {
        setX((int) (Math.random() * 740) + 10);
        setY((int) (Math.random() * 540) + 10);
    }
    
    public void moveCont(){
        //if shield is NOT active on worm
        if(suunta == 1){
            setImage (wormleft.getImage());
            dx = -1 * nopeus;
        }
        
        if(suunta == 2){
            setImage (wormright.getImage());
            dx = 1 * nopeus;
        }
        
        if(suunta == 3){
            setImage (wormup.getImage());
            dy = -1 * nopeus;
            
        }
        
        if(suunta == 4){
            setImage (wormdown.getImage());
            dy = 1 * nopeus;
            
        }
    }
    
    public Bounds getBounds() {
       Rectangle mato = new Rectangle(x, y, 35, 42);
       return mato.getLayoutBounds();
    }
    
    
}