import Model.Worm;
import Model.WormStates.WormConfuse;
import Model.WormStates.WormShield;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class WormTest {

    private Worm worm;

    @Before
    public void setUp() {
        worm = new Worm(1);
    }

    @Test
    public void testConfuseAndShield() {
        setUp();
        worm.changeState(WormConfuse.getInstance());
        assertTrue(worm.getReverse());
        worm.changeState(WormShield.getInstance());
        assertTrue(worm.getShield());
    }
}
