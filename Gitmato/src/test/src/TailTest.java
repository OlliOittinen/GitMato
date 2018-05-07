import Model.Tail;
import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TailTest {
    private Tail tail;

    /**
     * Instantiates a new <code>Tail</code> object.
     */
    @Before
    public void setUp() {
        tail = new Tail(15, 0);
    }

    /**
     * Checks that the getters and setters for the tail function correctly.
     */
    @Test
    public void gettersSetters() {
        assertEquals(0, tail.getX());
        assertEquals(0, tail.getY());

        tail.setX(100);
        tail.setY(100);
        assertEquals(100, tail.getY());
        assertEquals(100, tail.getX());
    }

    /**
     *  Creates a new Rectangle with the same size and location as the <code>Tail</code> object.
     *  Asserts that these have the same <code>Bounds</code>.
     */
    @Test
    public void bounds() {
        tail.setY(100);
        tail.setX(100);

        Rectangle b = new Rectangle(tail.getX(), tail.getY(),35, 42);

        assertEquals(b.getLayoutBounds(), tail.getBounds());
    }
}
