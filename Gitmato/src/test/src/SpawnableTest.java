import Model.Worm;
import Spawnables.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class SpawnableTest {

    private ArrayList<Spawnables> all = new ArrayList<Spawnables>();
    private Worm worm = new Worm(1);

    /**
     * Creates every Spawnable except for Snack as it has a different init() method.
     */
    @Before
    public void setUp() {
        Bombs b = new Bombs();
        Confuse c = new Confuse();
        Faster f = new Faster();
        Laser l = new Laser();
        Life life = new Life();
        Shield s = new Shield();
        Slower slo = new Slower();

        all.add(b);
        all.add(c);
        all.add(f);
        all.add(l);
        all.add(life);
        all.add(s);
        all.add(slo);
    }

    /**
     * Checks that every spawnable icon is set to (-100, -100)
     */
    @Test
    public void testXYOnInit() {
        for (Spawnables s : all) {
            assertEquals(-100, s.getX());
        }

        for (Spawnables s2: all) {
            assertEquals(-100, s2.getY());
        }

    }

    /**
     * Checks that every icon has correct x- and y-coordinates
     */
    @Test
    public void testBoundsOnInit() {
        for (Spawnables s: all) {
            assertEquals(-100, s.getBoundsForIcon().getMinX(), 0.1);
            assertEquals(-100, s.getBoundsForIcon().getMinY(), 0.1);
            assertEquals((-100+35), s.getBoundsForIcon().getMaxX(), 0.1);
            assertEquals((-100+42), s.getBoundsForIcon().getMaxY(), 0.1);
        }
    }

    /**
     * Sets a snack and a worm to the same x- & y-coordinate and makes sure it has relocated to somewhere else on the board spectrum.
     */
    @Test
    public void testCollisionWithSnack() {
        Snack snack = new Snack();
        snack.setX(100);
        snack.setY(100);
        worm.setX(100);
        worm.setY(100);

        assertEquals(400, snack.getX(), 400);
        assertEquals(300, snack.getY(), 300);
    }

    @Test
    public void testCollisionWithSpawnable() {
        for (int i= 1; i< all.size(); i++) {
            all.get(i).init();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assertEquals(-100, all.get(i).getX());
            assertEquals(-100, all.get(i).getY());
        }
    }

    @Test
    public void testLives() {
        Life.addLife(worm);
        assertEquals(4, worm.getLife());
        assertEquals(100, worm.getPoints());

        Life.loseLife(worm);
        assertEquals(3, worm.getLife());

        worm.shield();
        Life.loseLife(worm);
        assertEquals(3, worm.getLife());
    }

    @Test
    public void bombs() {
        Bombs b = (Bombs) all.get(0);
        assertEquals(-1000, b.getXBombs(2));
        assertEquals(-1000, b.getXBombs(3));
        assertEquals(-1000, b.getXBombs(4));
        assertEquals(-1000, b.getXBombs(5));
        assertEquals(-1000, b.getXBombs(6));

        assertEquals(-1000, b.getYBombs(2));
        assertEquals(-1000, b.getYBombs(3));
        assertEquals(-1000, b.getYBombs(4));
        assertEquals(-1000, b.getYBombs(5));
        assertEquals(-1000, b.getYBombs(6));

        b.bombs(worm);
        assertEquals(100, worm.getPoints());
        b.bombZone();
        try {
            Thread.sleep(8501);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void laser() {
        Worm worm2 = new Worm(2);
        Laser laser = (Laser) all.get(3);
        laser.onPickup(worm, worm2);
        assertEquals(100, worm.getPoints());
        try {
            Thread.sleep(1001);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(laser.getBoundsB().contains(worm2.getX(), worm2.getY()));

        try {
            Thread.sleep(3800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(-1000, laser.getX2());
        assertEquals(-1000, laser.getX3());
        assertEquals(-1000, laser.getY2());
        assertEquals(-1000, laser.getY3());
    }


}
