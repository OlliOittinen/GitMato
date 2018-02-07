/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.Board;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

/**
 *
 * @author gedst
 */

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame {
    

   
    
    JPanel parentPanel;
    JPanel childPanel1;
    public Board childPanel2;

    public MainFrame() {
        
        initUI();
    }
    
    private void initUI() {
        /*
        parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout(800, 600));

        childPanel1 = new SceneSelector(uic);
        childPanel1.setBackground(Color.red);
        childPanel1.setPreferredSize(new Dimension(800, 600));
        
        childPanel2 = new Board();
        childPanel2.setBackground(Color.blue);
        childPanel2.setPreferredSize(new Dimension(800, 600));
       
        setTitle("GitMato");
        setLocation(10, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parentPanel.add(childPanel1, BorderLayout.CENTER);
        add(parentPanel);
        pack();
        setVisible(true);
        uic.getFrame(this); //annetaan UIC:lle tämä Mainframe
        
        
    }
     public void gameOver(){
        
        //add(peliOhi = new GameOver());
    }
    public void newVersus(){
        SwingUtilities.invokeLater(() -> {
            System.out.println("hei");
            parentPanel.remove(childPanel1);
            parentPanel.add(childPanel2, BorderLayout.CENTER);
            parentPanel.revalidate();
            parentPanel.repaint();
            pack();
          
        });
    }

    public void setGameOver(GameOver peliOhi) {
        this.peliOhi = peliOhi;
    }
    public Board getBoard(){
        return board;
    }
    public MainFrame getSelf(){
        return this;
*/      
}
  
}
