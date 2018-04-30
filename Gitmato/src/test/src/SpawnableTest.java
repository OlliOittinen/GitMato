import Model.Worm;
import Spawnables.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

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
        Steal steal = new Steal();
        Switcher swi = new Switcher();

        all.add(b);
        all.add(c);
        all.add(f);
        all.add(l);
        all.add(life);
        all.add(s);
        all.add(slo);
        all.add(steal);
        all.add(swi);
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
            assertEquals((-100+45), s.getBoundsForIcon().getMaxX(), 10);
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

    /**
     * Checks that when worm collides with spawnable, the spawnable is hidden,
     */
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

    /**
     * Checks that the functions in Life are working correctly.
     */
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

    /**
     * Checks that bombs are initiated correctly.
     * Asserts that worm gets 100 points on pickup.
     */
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

    /**
     * Checks that the laser is created on top of the other worm after the set time.
     * Makes sure the laser is hidden after its' timer is run out.
     */
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
    /**
     * Checks that upon picking up the switcher the worms' locations are switched
     */
    @Test
    public void switcher(){
        Worm worm2 = new Worm(2);
        Switcher switcher = (Switcher) all.get(8);
        worm.setX(200);
        worm.setY(200);
        worm2.setX(400);
        worm2.setY(400);

        switcher.switcher(worm, worm2);

        assertEquals(400, worm.getX());
        assertEquals(400, worm.getX());
        assertEquals(200, worm2.getX());
        assertEquals(200, worm2.getX());

    }

    /**
     * Assigns three tailpieces to second worm.
     * Checks the length of both worms' tails before and after power-up, when worm #1 picks it up.
     */
    @Test
    public void steal() {
        Worm worm2 = new Worm(2);
        Steal steal = (Steal) all.get(7);

        worm2.addTail();
        worm2.addTail();
        worm2.addTail();

        assertEquals(3, worm2.getTails().size());
        assertEquals(0, worm.getTails().size());

        steal.steal(worm, worm2);

        assertEquals(2, worm2.getTails().size());
        assertEquals(1, worm.getTails().size());
    }

    /**
     * Tests whether or not the abstractDamagingSpawnables method 'damage' reduces the worm's life by 1
     * assuming that the spawnable is set to 'lethal'.
     */
    @Test
    public void testLethality() {
        Worm worm2 = new Worm(2);
        AbstractDamagingSpawnables asd = new Laser();
        assertFalse(((Laser) asd).getLethal());
        ((Laser) asd).onPickup(worm, worm2);
        try {
            Thread.sleep(2850);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(((Laser) asd).getLethal());
        asd.damage(worm2);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(2, worm2.getLife());
    }

    /**
     * Checks whether the slower and faster spawnables function correctly, with and without shield statuses.
     */
    @Test
    public void fasterAndSlowerSpawnable() {
        Faster f = (Faster) all.get(2);
        Slower s = (Slower) all.get(6);
        Worm worm2 = new Worm(2);
        assertEquals(3, worm.getSpeed(), 0.01);
        f.faster(worm);
        assertEquals(5, worm.getSpeed(), 0.01);
        worm.setSpeed(3);
        worm.setShield(true);
        s.slower(worm2, worm);
        assertEquals(3, worm.getSpeed(), 0.01);
        worm.setShield(false);
        s.slower(worm2, worm);
        assertEquals(1, worm.getSpeed(), 0.01);
    }

    /**
     * Tests if <code>Confuse</code> spawnable sets the worm's speed
     * to be negative of current speed with and without shield.
     */
    @Test
    public void confusion() {
        Confuse c = (Confuse) all.get(1);
        Worm worm2 = new Worm(2);
        worm.setShield(true);
        c.confuse(worm2, worm);
        assertEquals(3, worm.getSpeed(), 0.01);
        worm.setShield(false);
        c.confuse(worm2, worm);
        assertEquals(-3, worm.getSpeed(), 0.01);
    }

    @Test
    public void shield() {
        Shield shield = (Shield) all.get(5);
        assertFalse(worm.getShield());
        shield.shield(worm);
        assertTrue(shield.isActive(worm));
    }
}
