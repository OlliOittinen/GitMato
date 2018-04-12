import Model.Worm;
import Spawnables.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SpawnableTest {

    private ArrayList<Spawnables> all = new ArrayList<Spawnables>();
    private Worm worm = new Worm(1);


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

    @Test
    public void testXYOnInit() {

        setUp();

        for (Spawnables s : all) {
            assertEquals(-100, s.getX());
        }

        for (Spawnables s2: all) {
            assertEquals(-100, s2.getY());
        }

    }

    @Test
    public void testBoundsOnInit() {
        setUp();
        for (Spawnables s: all) {
            assertEquals(-100, s.getBoundsForIcon().getMinX(), 0.1);
            assertEquals(-100, s.getBoundsForIcon().getMinY(), 0.1);
            assertEquals((-100+35), s.getBoundsForIcon().getMaxX(), 0.1);
            assertEquals((-100+42), s.getBoundsForIcon().getMaxY(), 0.1);
        }
    }

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

}
