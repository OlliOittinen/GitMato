/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import Model.Board;

/**
 *
 * @author gedst
 */

import javax.swing.JFrame;

public class MainFrame extends JFrame {

    public MainFrame() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Board());
        
        

        setSize(800, 600);

        setSize(806, 620);

        setResizable(false);
        
        setTitle("Matopeli");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
