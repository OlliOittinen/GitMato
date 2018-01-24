/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gitmato;

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


/**
 *
 * @author maxki
 */
public class Board extends JPanel implements ActionListener {
    private Worm worm;
    
    private Timer timer;
    private final int DELAY = 10;
    private Snack snack;
    private int life = 1;
    private boolean ingame;
    private int pisteet;
    
    
    
    
    public Board() {

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
        
    }
}
