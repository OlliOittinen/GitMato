/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import GUI.Matopeli;
import Model.Board;
import Model.Worm;
import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
    private main.java.Model.Bot bot;
    
    public PlayerController(){
        initPlayerController();
    }
    
    private void initPlayerController(){
        
    }
    public void yksinPeli(String peliMoodi){
        this.pelimoodi = peliMoodi;
    }
   
    public void updateWorms(){ // tätä täytyy kutsua joka pelin alussa!!!!
        this.worms = board.getWorms(); // saadaan oikeat pelaajat.
    }
     
    public void keyPressed(KeyEvent e) {

        KeyCode key = e.getCode();
        
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
        public void actionPerformed(ActionEvent e) {

        board.checkCollisions();
        worm.move();
        worm.moveCont();
        worm2.move();
        worm2.moveCont();
        //tallennnetaan wormin coordinaatit yhteen 2D muuttujaan
            int x = worm.getX();
        y = worm.getY();
        x2 = worm2.getX();
        y2 = worm2.getY();
        p = new Point2D(x, y);
        p2 = new Point2D(x2, y2);
        //Lisätään coortinaatit listan cordinates alkuun (0).
        //siirtää automaattisesti taulukon arvot yhden eteenpäin, 0->1
        cordinates.add(0, p);
        cordinates2.add(0, p2);

        //jos lista liian suuri poistetaan viimeinen
        if (cordinates.size() >= 10000) {
            cordinates.remove(cordinates.size() - 1);
        }

        if (cordinates2.size() >= 10000) {
            cordinates2.remove(cordinates2.size() - 1);
        }

        //Päivitetään jokaisen "Tail" olion coordinaatit
        for (int i = 0; i < body.size(); i++) {
            int f = body.get(i).getCordinateInt();
            p = cordinates.get(f);
            x = (int) p.getX();
            y = (int) p.getY();
            body.get(i).setX(x);
            body.get(i).setY(y);
        }

        for (int i = 0; i < body2.size(); i++) {
            int f = body2.get(i).getCordinateInt();
            p2 = cordinates2.get(f);
            x2 = (int) p2.getX();
            y2 = (int) p2.getY();
            body2.get(i).setX(x2);
            body2.get(i).setY(y2);
        }

        repaint();

        if (pelimoodi == "vs AI") {
            bot.BlueAIBot();
        }

    }
}
