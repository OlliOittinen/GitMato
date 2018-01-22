/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gitmato;

import gitmato.Worm;
import java.awt.Color;
import javax.swing.Timer;
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
    
    
    public Board() {

        initBoard();
    }
    
    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        worm = new Worm();
        timer = new Timer(DELAY, this);
        timer.start();
        
    }
    
    private class TAdapter extends KeyAdapter {

        

        @Override
        public void keyPressed(KeyEvent e) {
            worm.keyPressed(e);
            
            
            
            
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        
            super.paintComponent(g);

            doDrawing(g);

            Toolkit.getDefaultToolkit().sync();
        

    }
    
    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(worm.getImage(), worm.getX(), worm.getY(), this);
        
        
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        
        
        worm.move();
        worm.moveCont();
        //faster();
        repaint();
        
        
    }
}
