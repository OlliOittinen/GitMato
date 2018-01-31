/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.KeyEvent;
import Model.Board;
import Model.Worm;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author gedst
 */
public class PlayerController {
    
    private List<Worm> worms = new ArrayList<>(); 
    private Board board;
    
    public PlayerController(){
        initPlayerController();
    }
    
    private void initPlayerController(){
        
    }
   
    public void updateWorms(){ // tätä täytyy kutsua joka pelin alussa!!!!
        this.worms = Board.getWorms(); // saadaan oikeat pelaajat.
    }
     
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        
        // controlls for player 1      
        if (key == KeyEvent.VK_LEFT) {
            if(worms.get(0).getSuunta() != 2){
                worms.get(0).setSuunta(1);
                worms.get(0).setSuuntaAdv(2);
            }
            
        }

        if (key == KeyEvent.VK_RIGHT) {
            if(worms.get(0).getSuunta() != 1){
            worms.get(0).setSuunta(2) ;
            worms.get(0).setSuuntaAdv(2) ;

            }
        }

        if (key == KeyEvent.VK_UP) {
            if(worms.get(0).getSuunta() != 4){
        
             worms.get(0).setSuunta(3);
            worms.get(0).setSuuntaAdv(1);
            }
        }

        if (key == KeyEvent.VK_DOWN) {
            if(worms.get(0).getSuunta() != 3){
        
             worms.get(0).setSuunta(4);
            worms.get(0).setSuuntaAdv(1);
            }
        }
        
        // controlls for player 2 
        // --- Here ---
    }  
}
