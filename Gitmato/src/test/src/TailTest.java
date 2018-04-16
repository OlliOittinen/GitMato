import Model.Tail;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TailTest {
    private Tail tail;

    @Before
    public void setUp() {
        tail = new Tail(15, 0);
    }

    @Test
    public void gettersSetters() {
        assertEquals(0, tail.getX());
        assertEquals(0, tail.getY());

        tail.setX(100);
        tail.setY(100);
        assertEquals(100, tail.getY());
        assertEquals(100, tail.getX());
    }
}
