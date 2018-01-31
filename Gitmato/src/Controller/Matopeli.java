
package Controller;


import GUI.MainFrame;
import java.awt.EventQueue;

public class Matopeli{
    

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                MainFrame peli = new MainFrame();
                peli.setVisible(true);
                
            }
        });         
    }
}

