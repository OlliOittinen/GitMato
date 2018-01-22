/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gitmato;

/**
 *
 * @author maxki
 */
import gitmato.Board;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Matopeli extends JFrame {

    public Matopeli() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Board());
        
        setSize(1000, 1000);
        setResizable(false);
        
        setTitle("MatopeliXXDXDXD");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                Matopeli peli = new Matopeli();
                peli.setVisible(true);
            }
        });
    }
}
