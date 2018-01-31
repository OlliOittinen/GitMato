/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gitmato;

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


/**
 *
 * @author maxki
 */
public class Board extends JPanel implements ActionListener {
    private Worm worm;
    private Tail tail;
    private Timer timer;
    private final int DELAY = 10;
    private Snack snack;
    private int life = 1;
    private boolean ingame;
    private int pisteet;
    //Lista Tail paloista
    private final List<Tail> body;
    //pidetään lukua kuinka monta Tail objektia on.
    private int tailNro = 0;
    // Wormin locaatio muuttujat:
    Point2D p;          // coordinaatit
    private int x;
    private int y;
    private final List<Point2D> cordinates;     
    
    public Board() {
        //alustetaan listat
        this.cordinates = new ArrayList<>();
        this.body = new ArrayList<>();
        this.p = new Point2D.Double(0,0);

        initBoard();
    }
    
    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        
        
        
        worm = new Worm();
        
        snack = new Snack();
        timer = new Timer(DELAY, this);
        timer.start();
        ingame = true;
        
        
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
            worm.keyPressed(e);
 
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        
            if (ingame == true) {
            super.paintComponent(g);

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
        g2d.drawImage(snack.getImage(), snack.getX(), snack.getY(), this);
        
        //tarkistetaan onko häntiä piirrettäväksi
        if(tailNro > 0){         
            for(int i=0; i < body.size() ; i++){
                // pidetään huoli että jokainen "tail" tulee piirrettyä per frame
                g2d.drawImage(body.get(i).getImage(), body.get(i).getX(), body.get(i).getY(), this);
                System.out.println("tätä tehdään");
            }
        }
        
        if(this.life == 0){
            drawGameOver(g);
        }
        
    }
    
    private void drawPisteet(Graphics g) {

        String msg = "Score: " + pisteet;

        Font small = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (125 - fm.stringWidth(msg)) / 2, 50 / 2);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        
        checkCollisions();
        worm.move();
        worm.moveCont();
        //tallennnetaan wormin coordinaatit yhteen 2D muuttujaan
        x = worm.getX();
        y = worm.getY();
        p = new Point2D.Double(x,y);
        
        //Lisätään coortinaatit listan cordinates alkuun (0).
        //siirtää automaattisesti taulukon arvot yhden eteenpäin, 0->1
        cordinates.add(0 , p);
        
        //jos lista liian suuri poistetaan viimeinen
        if(cordinates.size() >= 10000){
            cordinates.remove(10000);
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
            
        //faster();
        repaint();
        
        
    }
    
    public void checkCollisions() {
        
        Rectangle Matokuutio = worm.getBounds();

        Rectangle r1 = snack.getBounds();
        
        
        if (r1.intersects(Matokuutio)){
            snack.setX((int) (Math.random() * 940));
            snack.setY((int) (Math.random() * 940));
            pisteet++;
            spawnTail();
            
        }
        
        if (worm.getX() < 0 || worm.getX() > 950 || worm.getY() < 0 || worm.getY() > 950){
            life --;
        }
    }
    
    private void drawGameOver(Graphics g) {

    String msg = "HÄVISIT PELIN!!! PISTEESI: " + pisteet;
    Font small = new Font("Helvetica", Font.BOLD, 20);
    FontMetrics fm = getFontMetrics(small);

    g.setColor(Color.white);
    g.setFont(small);
    g.drawString(msg, (1000 - fm.stringWidth(msg)) / 2, 500 / 2);
    ingame = false;       
    }
    
    private void spawnTail(){
        //tulee yksi Tail pala lisää
        tailNro ++;
        // lisätään wormin bodiin Tail pala ja annetaan sille järjestyslukunsa
        body.add(tail = new Tail(tailNro * 20));
        System.out.println(body.size());
        
    }
}
