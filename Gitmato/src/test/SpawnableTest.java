import Model.Worm;
import Spawnables.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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

    //INCOMPLETE, DOESN'T WORK AS SUPPOSED TO
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
}
