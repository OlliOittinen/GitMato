import Model.Worm;
import Model.WormStates.WormConfuse;
import Model.WormStates.WormShield;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WormTest {

    private Worm worm;


    /**
     * Creates a new worm to be tested
     */
    @Before
    public void setUp() {
        worm = new Worm(1);
    }


    /**
     *
     */
    @Test
    public void testStatsOnStart() {
        assertEquals(3, worm.getLife());
        assertEquals(3, worm.getSpeed(), 0.01);
        assertEquals(200, worm.getX());
        assertEquals(279, worm.getY());
        assertEquals(2, worm.getDirection());
    }
    /**
     * Tests whether or not worm gets confused and shielded based on state
     */
    @Test
    public void testConfuseAndShield() {
        worm.changeState(WormConfuse.getInstance());
        assertTrue(worm.getReverse());
        worm.changeState(WormShield.getInstance());
        assertTrue(worm.getShield());
    }
}
