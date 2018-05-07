import Model.Worm;
import Model.WormStates.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WormTest {

    private Worm worm;

    @Before
    public void setUp() {
        worm = new Worm(1);
    }

    @Test
    public void testStatsOnStart() {
        assertEquals(3, worm.getLife());
        assertEquals(3, worm.getSpeed(), 0.01);
        assertEquals(200, worm.getX());
        assertEquals(279, worm.getY());
        assertEquals(2, worm.getDirection());
    }


    @Test
    public void testConfuseAndShieldStates() throws InterruptedException {
        worm.confuse();
        assertTrue(worm.getReverse());
        Thread.sleep(5000);
        worm.shield();
        assertTrue(worm.getShield());
        Thread.sleep(5000);
    }

    @Test
    public void testSlowAndFastStates() {
        worm.fasterSpeed();
        assertEquals(5, worm.getSpeed(), 0.01);
        worm.changeState(WormStateNormal.getInstance());
        worm.slowerSpeed();
        assertEquals(1, worm.getSpeed(), 0.01);
    }

    @Test
    public void gettersSetters() {
        worm.setX(100);
        worm.setY(100);
        assertEquals(100, worm.getX());
        assertEquals(100, worm.getY());

        worm.setSpeed(3);
        assertEquals(3, worm.getSpeed(), 0.01);

        worm.setPoints(50);
        assertEquals(50, worm.getPoints());

        worm.setLife(5);
        assertEquals(5, worm.getLife());

        worm.setTransparencyChange(true);
        assertTrue(worm.isTransparencyChange());
    }

    @Test
    public void testTurning() {
        worm.setDirection(2);
        worm.turnAround();
        assertEquals(1, worm.getDirection());

        worm.setDirection(3);
        worm.turnAround();
        assertEquals(4, worm.getDirection());

        worm.setDirection(1);
        worm.turnAround();
        assertEquals(2, worm.getDirection());

        worm.setDirection(4);
        worm.turnAround();
        assertEquals(3, worm.getDirection());
    }


}
