/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.Controller;

import main.java.GUI.Matopeli;
import java.awt.event.KeyEvent;

import main.java.GUI.Matopeli;
import main.java.Model.Board;
import main.java.Model.Worm;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gedst
 */
public class PlayerController  {
    
    private List<Worm> worms = new ArrayList<>(); 
    private Board board;
    private Matopeli peli;
    private String pelimoodi= "versus";
    
    public PlayerController(){
        initPlayerController();
    }
    
    private void initPlayerController(){
        
    }
    public void yksinPeli(String peliMoodi){
        this.pelimoodi = peliMoodi;
    }
   
    public void updateWorms(){ // tätä täytyy kutsua joka pelin alussa!!!!
        this.worms = Board.getWorms(); // saadaan oikeat pelaajat.
    }
    public void updateBoard(Board b){
        this.board = b;
    }
     
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        
        // controlls for player 1      
        if (key == KeyEvent.VK_LEFT) {
            if(worms.get(0).getSuunta() != 2){
                if(worms.get(0).getSuunta() != 1 && (worms.get(0).getX() < 20 || worms.get(0).getX() > 740)){
                    worms.get(0).setX(worms.get(0).getX()-9);
                }
                worms.get(0).setSuunta(1);
                worms.get(0).setSuuntaAdv(2);
            }
            
        }

        if (key == KeyEvent.VK_RIGHT) {
            
            if(worms.get(0).getSuunta() != 1){
                if(worms.get(0).getSuunta() != 2 && (worms.get(0).getX() < 20 || worms.get(0).getX() > 740)){
                    worms.get(0).setX(worms.get(0).getX()+9);
                }
                worms.get(0).setSuunta(2) ;
                worms.get(0).setSuuntaAdv(2) ;

            }
        }

        if (key == KeyEvent.VK_UP) {
            if(worms.get(0).getSuunta() != 4){
                if(worms.get(0).getSuunta() != 3 && (worms.get(0).getY() < 20 || worms.get(0).getY() > 530)){
                    worms.get(0).setY(worms.get(0).getY()-9);
                }
                worms.get(0).setSuunta(3);
                worms.get(0).setSuuntaAdv(1);
            }
        }

        if (key == KeyEvent.VK_DOWN) {
            if(worms.get(0).getSuunta() != 3){
               if(worms.get(0).getSuunta() != 4 && (worms.get(0).getY() < 20 || worms.get(0).getY() > 530)){
                    worms.get(0).setY(worms.get(0).getY()+9);
                    
                }
               worms.get(0).setSuunta(4);
               worms.get(0).setSuuntaAdv(1);
               
            }
        }
        
        
        // controlls for player 2 
        if(pelimoodi == "versus"){
            if (key == KeyEvent.VK_A) {
            

                    if(worms.get(1).getSuunta() != 2){
                        if(worms.get(1).getSuunta() != 1 && (worms.get(1).getX() < 20 || worms.get(1).getX() > 740)){
                            worms.get(1).setX(worms.get(1).getX()-9);
                        }
                        worms.get(1).setSuunta(1);
                        worms.get(1).setSuuntaAdv(2);
                    }

                }

                if (key == KeyEvent.VK_D) {
                    if(worms.get(1).getSuunta() != 1){
                        if(worms.get(1).getSuunta() != 2 && (worms.get(1).getX() < 20 || worms.get(1).getX() > 740)){
                            worms.get(1).setX(worms.get(1).getX()+9);
                        }
                        worms.get(1).setSuunta(2) ;
                        worms.get(1).setSuuntaAdv(2) ;

                    }
                }

                if (key == KeyEvent.VK_W) {
                    if(worms.get(1).getSuunta() != 4){
                        if(worms.get(1).getSuunta() != 3 && (worms.get(1).getY() < 20 || worms.get(1).getY() > 530)){
                            worms.get(1).setY(worms.get(1).getY()-9);
                        }

                        worms.get(1).setSuunta(3);
                        worms.get(1).setSuuntaAdv(1);
                    }
                }

                if (key == KeyEvent.VK_S) {
                    if(worms.get(1).getSuunta() != 3){
                        if(worms.get(1).getSuunta() != 4 && (worms.get(1).getY() < 20 || worms.get(1).getY() > 530)){
                            worms.get(1).setY(worms.get(1).getY()+9);
                        }

                        worms.get(1).setSuunta(4);
                        worms.get(1).setSuuntaAdv(1);
                    }
                }
            }
        
        //GAME RESET
        if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_ENTER) {
            board.restartGame();
        }
        if (key == KeyEvent.VK_H) {
            board.submitHighscore();
        }
                    
        // controlls for player 2 
        // --- Here ---
    }  
}
