/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import Controller.PlayerController;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javafx.scene.layout.Background;
import javax.swing.ImageIcon;


/**
 *
 * @author maxki
 */
public class Board extends JPanel implements ActionListener {
    private Worm worm;
    private Worm worm2;
    private PlayerController control;
    private Tail tail;
    private Tail tail2;
    private Timer timer;
    private final int DELAY = 10;
    private Snack snack;
    private int life = 1;
    private int life2 = 1;
    private boolean ingame;
    private int pisteet;
    private int pisteet2;

    //Lista Tail paloista
    private final List<Tail> body;
    private final List<Tail> body2;
    //pidetään lukua kuinka monta Tail objektia on.
    private int tailNro = 0;
    private int tailNro2 = 0;
    // Wormin locaatio muuttujat:
    Point2D p;    
    Point2D p2;// coordinaatit
    private int x;
    private int y;
    private int x2;
    private int y2;
    private final List<Point2D> cordinates;  
    private final List<Point2D> cordinates2;
    private static List<Worm> worms;
    
    private Image background;
    
    
    
    
    public Board() {
        //alustetaan listat
        this.worms = new ArrayList<>();
        this.cordinates = new ArrayList<>();
        this.body = new ArrayList<>();
        this.p = new Point2D.Double(0,0);
        this.cordinates2 = new ArrayList<>();
        this.body2 = new ArrayList<>();
        this.p2 = new Point2D.Double(0,0);
        initBoard();
    }
    
    private void initBoard() {
        
        
        //TODO: Tähän täytyy tehdä kaikki mahdolliset pelimuodot
        
        
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);

        worms.add(worm = new Worm()); //lista worm olioista
        worms.add(worm2 = new Worm());
        
        
        snack = new Snack();
        timer = new Timer(DELAY, this);
        timer.start();
        ingame = true;
        
        control = new PlayerController(); // 
        control.updateWorms(); // Worms-lista liitetään playercontrolleriin
        
        ImageIcon kuvamato = new ImageIcon("src/Images/BlueBG800x600.png");
        background = kuvamato.getImage();
                
    }
    
    private void inGame() {

        if (!ingame) {
            this.life = this.life - 1;
            
            repaint();
            timer.stop();

        }
    }
    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            control.keyPressed(e);
 
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        
            if (ingame == true) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            
            g2.setPaint(Color.BLACK);
            g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
            g.drawImage(this.background, 0,0,null);
            doDrawing(g);

            Toolkit.getDefaultToolkit().sync();
        } else {
            drawGameOver(g);
            inGame();
            
        }
    }
    
    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        drawPisteet(g);
        
        g2d.drawImage(worm.getImage(), worm.getX(), worm.getY(), this);
        g2d.drawImage(worm2.getImage(), worm2.getX(), worm2.getY(), this);
        g2d.drawImage(snack.getImage(), snack.getX(), snack.getY(), this);
        
        //tarkistetaan onko häntiä piirrettäväksi
        if(tailNro > 0){         
            for(int i=0; i < body.size() ; i++){
                // pidetään huoli että jokainen "tail" tulee piirrettyä per frame
                g2d.drawImage(body.get(i).getImage(), body.get(i).getX(), body.get(i).getY(), this);
                //System.out.println("tätä tehdään");
            }
        }
        
        if(tailNro2 > 0){         
            for(int i=0; i < body2.size() ; i++){
                // pidetään huoli että jokainen "tail" tulee piirrettyä per frame
                g2d.drawImage(body2.get(i).getImage(), body2.get(i).getX(), body2.get(i).getY(), this);
                //System.out.println("tätä tehdään");
            }
        }
        
        if(this.life == 0 || this.life2 == 0){
            drawGameOver(g);
        }
        
    }
    
    private void drawPisteet(Graphics g) {

        String msg = "P1 pisteet: " + pisteet;
        String msg2 = "P2 pisteet: " + pisteet2;

        Font small = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (125 - fm.stringWidth(msg)) / 2, 50 / 2);
        g.drawString(msg2, (125 - fm.stringWidth(msg2)) / 2, 100 / 2);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        checkCollisions();
        worm.move();
        worm.moveCont();
        worm2.move();
        worm2.moveCont();
        //tallennnetaan wormin coordinaatit yhteen 2D muuttujaan
        x = worm.getX();
        y = worm.getY();
        x2 = worm2.getX();
        y2 = worm2.getY();
        p = new Point2D.Double(x,y);
        p2 = new Point2D.Double(x2,y2);
        //Lisätään coortinaatit listan cordinates alkuun (0).
        //siirtää automaattisesti taulukon arvot yhden eteenpäin, 0->1
        cordinates.add(0 , p);
        cordinates2.add(0 , p2);
        
        //jos lista liian suuri poistetaan viimeinen
        if(cordinates.size() >= 10000){
            cordinates.remove(cordinates.size() -1);
        }
        
        if(cordinates2.size() >= 10000){
            cordinates2.remove(cordinates2.size() -1);
        }
        
        //Päivitetään jokaisen "Tail" olion coordinaatit
        for(int i=0; i < body.size() ; i++){ 
        int f = body.get(i).getCordinateInt();
        p = cordinates.get(f);
        x = (int) p.getX();
        y = (int) p.getY();
        body.get(i).setX(x);
        body.get(i).setY(y);
        }
        
        for(int i=0; i < body2.size() ; i++){ 
        int f = body2.get(i).getCordinateInt();
        p2 = cordinates2.get(f);
        x2 = (int) p2.getX();
        y2 = (int) p2.getY();
        body2.get(i).setX(x2);
        body2.get(i).setY(y2);
        }
            
        //faster();
        repaint();        
    }
    
    public void checkCollisions() {
        
        Rectangle Matokuutio = worm.getBounds();
        Rectangle Matokuutio2 = worm2.getBounds();

        Rectangle r1 = snack.getBounds();
                
        if (r1.intersects(Matokuutio)){
            snack.setX((int) (Math.random() * 750));
            snack.setY((int) (Math.random() * 550));
            pisteet += 100;
            spawnTail();
            
        }
        
        if (r1.intersects(Matokuutio2)){
            snack.setX((int) (Math.random() * 750));
            snack.setY((int) (Math.random() * 550));
            pisteet2 += 100;
            spawnTail2();
            
        }
        
        if (worm.getX() < 5 || worm.getX() > 760 || worm.getY() < 5
                || worm.getY() > 550){
            life --;
        }
        
        if (worm2.getX() < 5 || worm2.getX() > 760 || worm2.getY() < 5
                || worm2.getY() > 550){
            life2 --;
        }
    }
    
    private void drawGameOver(Graphics g) {

        if(life==0){
            String msg = "Pelaaja 2 voitti pelin!!! PISTEESI: " + pisteet;
            Font small = new Font("Helvetica", Font.BOLD, 20);
            FontMetrics fm = getFontMetrics(small);

            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(msg, (806 - fm.stringWidth(msg)) / 2, 500 / 2);
            ingame = false; 
        }
        
        if(life2==0){
            String msg = "Pelaaja 1 voitti pelin!!! PISTEESI: " + pisteet;
            Font small = new Font("Helvetica", Font.BOLD, 20);
            FontMetrics fm = getFontMetrics(small);

            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(msg, (806 - fm.stringWidth(msg)) / 2, 500 / 2);
            ingame = false; 
        }
    
              
    }
    
    private void spawnTail(){
        //tulee yksi Tail pala lisää
        tailNro ++;
        // lisätään wormin bodiin Tail pala ja annetaan sille järjestyslukunsa
        body.add(tail = new Tail(tailNro * 15));
        System.out.println(body.size());
        
    }
    
    private void spawnTail2(){
        //tulee yksi Tail pala lisää
        tailNro2 ++;
        // lisätään wormin bodiin Tail pala ja annetaan sille järjestyslukunsa
        body2.add(tail = new Tail(tailNro2 * 15));
        System.out.println(body2.size());
        
    }
    
    public static List getWorms(){
        return worms;
    }
}
