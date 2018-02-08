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

    private Worm worm;
    private Worm worm2;
    private PlayerController control;
    private Tail tail;
    private Tail tail2;
    private Timer timer;
    private final int DELAY = 10;
    private Snack snack;
    private Faster faster;
    private Slower slower;
    private Reverse reverse;
    private int life = 1;
    private int life2 = 1;
    private boolean ingame;
    private int Pituus;
    private int Pituus2;
    private MainFrame frame;

    //Lista Tail paloista
    private final List<Tail> body;
    private final List<Tail> body2;
    //pidetään lukua kuinka monta Tail objektia on.
    private int tailNro = 0;
    private int tailNro2 = 0;
    // Wormin locaatio muuttujat:
    Point2D p;
    Point2D p2;// coordinaatit
    private int x;
    private int y;
    private int x2;
    private int y2;
    private final List<Point2D> cordinates;
    private final List<Point2D> cordinates2;
    private static List<Worm> worms;

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

        worms.add(worm = new Worm(1)); //lista worm olioista
        worms.add(worm2 = new Worm(2));

        faster = new Faster();
        slower = new Slower();
        reverse = new Reverse();
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

            worms.add(worm = new Worm(1)); //lista worm olioista
            worms.add(worm2 = new Worm(2));
            
            timer.start();

            control.updateWorms();
            control.updateBoard(this);
            life = 1;
            life2 = 1;
            
            cordinates.clear();
            cordinates2.clear();
            body.clear();
            body2.clear();
            Pituus = 0;
            Pituus2 = 0;

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

        g2d.drawImage(worm.getImage(), worm.getX(), worm.getY(), this);
        g2d.drawImage(worm2.getImage(), worm2.getX(), worm2.getY(), this);
        g2d.drawImage(snack.getImage(), snack.getX(), snack.getY(), this);
        g2d.drawImage(faster.getImage(), faster.getX(), faster.getY(), this);
        g2d.drawImage(slower.getImage(), slower.getX(), slower.getY(), this);
        g2d.drawImage(reverse.getImage(), reverse.getX(), reverse.getY(), this);

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

        if (this.life == 0 || this.life2 == 0) {
            drawGameOver(g);
        }

    }

    private void drawPisteet(Graphics g) {

        String msg = "P1 pituus: " + Pituus;
        String msg2 = "P2 pituus: " + Pituus2;

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
        worm.move();
        worm.moveCont();
        worm2.move();
        worm2.moveCont();
        //tallennnetaan wormin coordinaatit yhteen 2D muuttujaan
        x = worm.getX();
        y = worm.getY();
        x2 = worm2.getX();
        y2 = worm2.getY();
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

        Rectangle Matokuutio = worm.getBounds();
        Rectangle Matokuutio2 = worm2.getBounds();

        Rectangle r1 = snack.getBounds();
        Rectangle pu = faster.getBounds();
        Rectangle ps = slower.getBounds();
        Rectangle pr = reverse.getBounds();

        for (int i = 0; i < body.size(); i++) {
            Rectangle Matotail = body.get(i).getBounds();
            if (Matokuutio2.intersects(Matotail)) {
                System.out.println("Blue dead");
                life2 = 0;

            }
        }

        for (int i = 0; i < body2.size(); i++) {
            Rectangle Matotail2 = body2.get(i).getBounds();
            if (Matokuutio.intersects(Matotail2)) {
                System.out.println("Red dead");
                life = 0;

            }
        }

        //mato 1 collisions
        if (r1.intersects(Matokuutio)) {
            snack.randomizeXY();
            Pituus += 1;
            spawnTail();
        }

        if (pu.intersects(Matokuutio)) {
            faster.faster(worm);
            powerUpCD();
        }

        if (ps.intersects(Matokuutio)) {
            slower.slower(worm, worm2);
            powerUpCD();
        }

        if (pr.intersects(Matokuutio)) {
            reverse.reverse(worm, worm2);
            powerUpCD();
        }
        if (worm.getX() < 5 || worm.getX() > 760 || worm.getY() < 5
                || worm.getY() > 550) {
            life--;
        }

        //mato 2 collisions
        if (r1.intersects(Matokuutio2)) {
            snack.randomizeXY();
            Pituus2 += 1;
            spawnTail2();
        }

        if (pu.intersects(Matokuutio2)) {
            faster.faster(worm2);
            powerUpCD();
        }

        if (ps.intersects(Matokuutio2)) {
            slower.slower(worm2, worm);
            powerUpCD();
        }

        if (pr.intersects(Matokuutio2)) {
            reverse.reverse(worm2, worm);
            powerUpCD();
        }

        if (worm2.getX() < 5 || worm2.getX() > 760 || worm2.getY() < 5
                || worm2.getY() > 550) {
            life2--;
        }
    }

    private void drawGameOver(Graphics g) {

        if (life == 0) {
            String msg = "Pelaaja 2 voitti pelin!!! Paina Space pelataksesi uudelleen";
            Font small = new Font("Helvetica", Font.BOLD, 20);
            FontMetrics fm = getFontMetrics(small);

            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(msg, (806 - fm.stringWidth(msg)) / 2, 500 / 2);
            Sound.Music.sound1.stop();
            ingame = false;
        }

        if (life2 == 0) {
            String msg = "Pelaaja 1 voitti pelin!!! Paina Space pelataksesi uudelleen";
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
        System.out.println(body.size());

    }

    private void spawnTail2() {
        //tulee yksi Tail pala lisää
        tailNro2++;
        // lisätään wormin bodiin Tail pala ja annetaan sille järjestyslukunsa
        body2.add(tail = new Tail(tailNro2 * 15, 2));
        System.out.println(body2.size());

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

        java.util.Timer timer2 = new java.util.Timer();
        timer2.schedule(new TimerTask() {

            @Override
            public void run() {

                Random rand = new Random();

                int n = rand.nextInt(10);
                if (n < 5) {
                    faster.randomizeXY();
                } else if (n >= 8) {
                    reverse.randomizeXY();
                } else {
                    slower.randomizeXY();
                }
            }
        }, 5000); //aika (ms), joka odotetaan
    }
}
