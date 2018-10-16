package HW6;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;

/**
 * Zombie Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Oct 16, 2018</pre>
 */
public class ZombieTest {

    Zombie z1 = Zombie.build(1, 2);

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: build()
     */
    @Test
    public void testBuild() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: build(int x, int y)
     */
    @Test
    public void testBuildForXY() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getX_position()
     */
    @Test
    public void testGetX_position() throws Exception {
        assertEquals(1, z1.getX_position());
    }

    /**
     * Method: setX_position(int x_position)
     */
    @Test
    public void testSetX_position() throws Exception {
        z1.setX_position(9);
        assertEquals(9, z1.getX_position());
    }

    /**
     * Method: getY_position()
     */
    @Test
    public void testGetY_position() throws Exception {
        assertEquals(2, z1.getY_position());
    }

    /**
     * Method: setY_position(int y_position)
     */
    @Test
    public void testSetY_position() throws Exception {
        z1.setY_position(12);
        assertEquals(12, z1.getY_position());
    }

    /**
     * Method: toString()
     */
    @Test
    public void testToString() throws Exception {
        assertEquals("[1,2]", z1.toString());
    }

} 
