package HW6;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * Zombie Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Oct 16, 2018</pre>
 */
public class ZombieTest {

    Zombie z1 = Zombie.build(BigInteger.valueOf(1), BigInteger.valueOf(2));

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
        Zombie z2 = Zombie.build();
        assertEquals(0,z2.getX_position().intValue());
        assertEquals(0,z2.getY_position().intValue());
    }

    /**
     * Method: build(int x, int y)
     */
    @Test
    public void testBuildForXY() throws Exception {
        Zombie z3 = Zombie.build(BigInteger.valueOf(5), BigInteger.valueOf(8));
        assertEquals(5,z3.getX_position().intValue());
        assertEquals(8,z3.getY_position().intValue());
    }

    /**
     * Method: getX_position().intValue()
     */
    @Test
    public void testGetX_position() throws Exception {
        assertEquals(1, z1.getX_position().intValue());
    }

    /**
     * Method: setX_position(int x_position)
     */
    @Test
    public void testSetX_position() throws Exception {
        z1.setX_position(BigInteger.valueOf(9));
        assertEquals(9, z1.getX_position().intValue());
    }

    /**
     * Method: getY_position().intValue()
     */
    @Test
    public void testGetY_position() throws Exception {
        assertEquals(2, z1.getY_position().intValue());
    }

    /**
     * Method: setY_position(int y_position)
     */
    @Test
    public void testSetY_position() throws Exception {
        z1.setY_position(BigInteger.valueOf(12));
        assertEquals(12, z1.getY_position().intValue());
    }

    /**
     * Method: toString()
     */
    @Test
    public void testToString() throws Exception {
        assertEquals("[1,2]", z1.toString());
    }

} 
