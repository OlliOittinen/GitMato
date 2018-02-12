/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Spawnables.*;
import Controller.Matopeli;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

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
import java.util.List;
import java.util.Random;

import Controller.PlayerController;
import GUI.MainFrame;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.TimerTask;

import javax.swing.ImageIcon;

/**
 *
 * @author maxki
 */
public final class Board extends JPanel implements ActionListener {

    private Worm punainen;
    private Worm sininen;
    private PlayerController control;
    private Tail tail;
    private Tail tail2;
    private Timer timer;
    private final int DELAY = 10;
    private Snack snack;
    private Faster faster;
    private Slower slower;
    private Reverse reverse;
    private Life HP;
    private Shield shield;
    private boolean ingame;
    private MainFrame frame;
    private ImageIcon Ironpic;
    //Lista Tail paloista
    private final List<Tail> body;
    private final List<Tail> body2;
    //pidetään lukua kuinka monta Tail objektia on.
    private int tailNro = 0;
    private int tailNro2 = 0;
    // Wormin locaatio muuttujat:
    Point2D p;
    Point2D p2;// coordinaatit
    private int x, y;
    private int x2, y2;
    private final List<Point2D> cordinates;
    private final List<Point2D> cordinates2;
    private static List<Worm> worms;
    private Image halo;
    private Image halo2;

    private Matopeli engine;

    private Image background;

    public Board(Matopeli e) {
        this.engine = e;

        //alustetaan listat
        Board.worms = new ArrayList<>();
        this.cordinates = new ArrayList<>();
        this.body = new ArrayList<>();
        this.p = new Point2D.Double(0, 0);

        this.cordinates2 = new ArrayList<>();
        this.body2 = new ArrayList<>();
        this.p2 = new Point2D.Double(0, 0);

        initBoard();

    }

    private void initBoard() {
        //TODO: Tähän täytyy tehdä kaikki mahdolliset pelimuodot

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);

        worms.add(punainen = new Worm(1)); //lista worm olioista
        worms.add(sininen = new Worm(2));

        faster = new Faster();
        slower = new Slower();
        reverse = new Reverse();
        HP = new Life();
        shield = new Shield();
        powerUpCD(); //piilottaa powerupit alussa

        snack = new Snack();
        timer = new Timer(DELAY, this);
        timer.start();
        ingame = true;

        control = new PlayerController(); // 
        control.updateWorms(); // Worms-lista liitetään playercontrolleriin
        control.updateBoard(this);

        ImageIcon kuvamato = new ImageIcon("src/Images/BlueBG800x600.png");
        background = kuvamato.getImage();
        System.out.println("In initBoard");

    }

    public void restartGame() {
        if (!ingame) {
            ingame = true;
            powerUpCD();
            snack.randomizeXY();

            worms.remove(0);
            worms.remove(0);

            worms.add(punainen = new Worm(1)); //lista worm olioista
            worms.add(sininen = new Worm(2));

            timer.start();

            control.updateWorms();
            control.updateBoard(this);

            cordinates.clear();
            cordinates2.clear();
            body.clear();
            body2.clear();
            tailNro = 0;
            tailNro2 = 0;

        }

    }

    private void inGame() {
        if (!ingame) {
            repaint();
            timer.stop();
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            control.keyPressed(e);
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        if (ingame == true) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            g2.setPaint(Color.BLACK);
            g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
            g.drawImage(this.background, 0, 0, null);
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

        g2d.drawImage(punainen.getImage(), punainen.getX(), punainen.getY(), this);
        g2d.drawImage(sininen.getImage(), sininen.getX(), sininen.getY(), this);
        g2d.drawImage(snack.getImage(), snack.getX(), snack.getY(), this);
        g2d.drawImage(faster.getImage(), faster.getX(), faster.getY(), this);
        g2d.drawImage(slower.getImage(), slower.getX(), slower.getY(), this);
        g2d.drawImage(reverse.getImage(), reverse.getX(), reverse.getY(), this);
        g2d.drawImage(HP.getImage(), HP.getX(), HP.getY(), this);
        g2d.drawImage(shield.getImage(), shield.getX(), shield.getY(), this);

        //tarkistetaan onko häntiä piirrettäväksi
        if (tailNro > 0) {
            for (int i = 0; i < body.size(); i++) {
                // pidetään huoli että jokainen "tail" tulee piirrettyä per frame
                g2d.drawImage(body.get(i).getImage(), body.get(i).getX(), body.get(i).getY(), this);
                //System.out.println("tätä tehdään");
            }
        }

        if (tailNro2 > 0) {
            for (int i = 0; i < body2.size(); i++) {
                // pidetään huoli että jokainen "tail" tulee piirrettyä per frame
                g2d.drawImage(body2.get(i).getImage(), body2.get(i).getX(), body2.get(i).getY(), this);
                //System.out.println("tätä tehdään");
            }
        }

        if (punainen.getLife() <= 0 || sininen.getLife() <= 0) {
            drawGameOver(g);
        }
    }

    private void drawPisteet(Graphics g) {

        String msg = "Punaisen HP: " + punainen.getLife();
        String msg2 = "Sinisen HP: " + sininen.getLife();

        Font small = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (150 - fm.stringWidth(msg)) / 2, 50 / 2);
        g.drawString(msg2, (150 - fm.stringWidth(msg2)) / 2, 100 / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        checkCollisions();
        punainen.move();
        punainen.moveCont();
        sininen.move();
        sininen.moveCont();
        //tallennnetaan wormin coordinaatit yhteen 2D muuttujaan
        x = punainen.getX();
        y = punainen.getY();
        x2 = sininen.getX();
        y2 = sininen.getY();
        p = new Point2D.Double(x, y);
        p2 = new Point2D.Double(x2, y2);
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
    }

    public void checkCollisions() {
        Rectangle Matokuutio = punainen.getBounds();
        Rectangle Matokuutio2 = sininen.getBounds();

        Rectangle s = snack.getBounds();
        Rectangle pf = faster.getBounds();
        Rectangle ps = slower.getBounds();
        Rectangle pr = reverse.getBounds();
        Rectangle pl = HP.getBounds();
        Rectangle psh = shield.getBounds();

        for (int i = 0; i < body.size(); i++) {
            Rectangle Matotail = body.get(i).getBounds();
            if (Matokuutio2.intersects(Matotail) && shield.isActive(punainen)) {
                System.out.println("SINISEE SATTU");
                if (sininen.getLife() > 1) {
                    shield.shield(sininen, 50);
                    sininen.randomizeXY();
                    sininen.setSuuntaAdv(0);
                    sininen.setSuunta(0);
                } else {
                    System.out.println("Blue dead");
                }
                sininen.setLife(sininen.getLife() - 1);
            }
        }

        for (int i = 0; i < body2.size(); i++) {
            Rectangle Matotail2 = body2.get(i).getBounds();
            if (Matokuutio.intersects(Matotail2) && shield.isActive(sininen)) {
                System.out.println("PUNASEE SATTU");
                if (punainen.getLife() > 1) {
                    shield.shield(punainen, 50);
                    punainen.randomizeXY();
                    punainen.setSuuntaAdv(0);
                    punainen.setSuunta(0);
                } else {
                    System.out.println("Red dead");
                }
                punainen.setLife(punainen.getLife() - 1);
            }
        }

        //mato 1 collisions
        if (s.intersects(Matokuutio)) {
            snack.randomizeXY();
            
            spawnTail();
        }

        if (pf.intersects(Matokuutio)) {
            faster.faster(punainen);
            powerUpCD();
        }

        if (ps.intersects(Matokuutio)) {
            slower.slower(punainen, sininen);
            powerUpCD();
        }

        if (pr.intersects(Matokuutio)) {
            reverse.reverse(punainen, sininen);
            powerUpCD();
        }

        if (pl.intersects(Matokuutio)) {
            HP.Life(punainen);
            powerUpCD();
        }

        if (psh.intersects(Matokuutio)) {
            shield.shield(punainen, 10000);
            powerUpCD();
        }

        if (punainen.getX() < 5 || punainen.getX() > 760 || punainen.getY() < 5 || punainen.getY() > 550) {
            System.out.println("PUNASEE SATTU");
            if (punainen.getLife() > 1) {
                punainen.randomizeXY();
                punainen.setSuuntaAdv(0);
                punainen.setSuunta(0);
            }
            punainen.setLife(punainen.getLife() - 1);
        }

        //mato 2 collisions
        if (s.intersects(Matokuutio2)) {
            snack.randomizeXY();
            
            spawnTail2();
        }

        if (pf.intersects(Matokuutio2)) {
            faster.faster(sininen);
            powerUpCD();
        }

        if (ps.intersects(Matokuutio2)) {
            slower.slower(sininen, punainen);
            powerUpCD();
        }

        if (pr.intersects(Matokuutio2)) {
            reverse.reverse(sininen, punainen);
            powerUpCD();
        }

        if (pl.intersects(Matokuutio2)) {
            HP.Life(sininen);
            powerUpCD();
        }

        if (psh.intersects(Matokuutio2)) {
            shield.shield(sininen, 10000);
            powerUpCD();
        }

        if (sininen.getX() < 5 || sininen.getX() > 760 || sininen.getY() < 5 || sininen.getY() > 550) {
            System.out.println("SINISEE SATTU");
            if (sininen.getLife() > 1) {
                sininen.randomizeXY();
                sininen.setSuuntaAdv(0);
                sininen.setSuunta(0);
            }
            sininen.setLife(sininen.getLife() - 1);
        }
    }

    private void drawGameOver(Graphics g) {

        if (punainen.getLife() <= 0) {
            String msg = "Sininen voitti pelin!!! Paina Space pelataksesi uudelleen";
            Font small = new Font("Helvetica", Font.BOLD, 20);
            FontMetrics fm = getFontMetrics(small);

            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(msg, (806 - fm.stringWidth(msg)) / 2, 500 / 2);
            Sound.Music.sound1.stop();
            ingame = false;
        }

        if (sininen.getLife() <= 0) {
            String msg = "Punainen voitti pelin!!! Paina Space pelataksesi uudelleen";
            Font small = new Font("Helvetica", Font.BOLD, 20);
            FontMetrics fm = getFontMetrics(small);

            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(msg, (806 - fm.stringWidth(msg)) / 2, 500 / 2);
            Sound.Music.sound1.stop();
            ingame = false;
        }

    }

    private void spawnTail() {
        //tulee yksi Tail pala lisää
        tailNro++;
        // lisätään wormin bodiin Tail pala ja annetaan sille järjestyslukunsa
        body.add(tail = new Tail(tailNro * 15, 1));
    }

    private void spawnTail2() {
        //tulee yksi Tail pala lisää
        tailNro2++;
        // lisätään wormin bodiin Tail pala ja annetaan sille järjestyslukunsa
        body2.add(tail = new Tail(tailNro2 * 15, 2));
    }

    public static List getWorms() {
        return worms;
    }

    public void powerUpCD() {

        faster.setX(-100);
        faster.setY(-100);
        slower.setX(-100);
        slower.setY(-100);
        reverse.setX(-100);
        reverse.setY(-100);
        HP.setX(-100);
        HP.setY(-100);
        shield.setX(-100);
        shield.setY(-100);

        java.util.Timer timer2 = new java.util.Timer();
        timer2.schedule(new TimerTask() {

            @Override
            public void run() {
                
                int n = (int) Math.random()*5;
                
                switch (n) {
                    case 0:
                        shield.randomizeXY();
                        break;
                    case 1:
                        faster.randomizeXY();
                        break;
                    case 2:
                        slower.randomizeXY();
                        break;
                    case 3:
                        reverse.randomizeXY();
                        break;
                    case 4:
                        HP.randomizeXY();
                        break;
                    default:
                        System.out.println("WHAT");
                }

            }
        }, 5000); //aika (ms), joka odotetaan
    }
    
}