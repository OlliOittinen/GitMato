import Model.Worm;
import Model.WormStates.WormConfuse;
import Model.WormStates.WormShield;
import org.junit.Before;
import org.junit.Test;

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
