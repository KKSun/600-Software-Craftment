package test.HW6;

import HW6.Zombie;
import HW6.Zombies;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Zombies Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Oct 16, 2018</pre>
 */
public class ZombiesTest {

    private Zombies zombies = new Zombies();

    @Before
    public void before() throws Exception {
        zombies.insert(Zombie.build(), BigInteger.valueOf(1), BigInteger.valueOf(1));
        zombies.insert(Zombie.build(), BigInteger.valueOf(2), BigInteger.valueOf(2));
        zombies.insert(Zombie.build(), BigInteger.valueOf(4), BigInteger.valueOf(1));
        zombies.insert(Zombie.build(), BigInteger.valueOf(4), BigInteger.valueOf(10));
        zombies.insert(Zombie.build(), BigInteger.valueOf(4), BigInteger.valueOf(80));
        zombies.insert(Zombie.build(), BigInteger.valueOf(4), BigInteger.valueOf(3));
        zombies.insert(Zombie.build(), BigInteger.valueOf(5), BigInteger.valueOf(6));
        zombies.insert(Zombie.build(), BigInteger.valueOf(5), BigInteger.valueOf(22));
        zombies.insert(Zombie.build(), BigInteger.valueOf(6), BigInteger.valueOf(12));
        zombies.insert(Zombie.build(), BigInteger.valueOf(6), BigInteger.valueOf(8));
        zombies.insert(Zombie.build(), BigInteger.valueOf(7), BigInteger.valueOf(28));
        zombies.insert(Zombie.build(), BigInteger.valueOf(10), BigInteger.valueOf(2));
    }

    @After
    public void after() throws Exception {
        zombies.clearAllZombies();
    }

    /**
     * Method: insert(Zombie z, int x, int y)
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInsert() throws Exception {
        int temp = zombies.zombie(BigInteger.valueOf(6), BigInteger.valueOf(12)).hashCode();
        zombies.insert(zombies.delete(BigInteger.valueOf(6), BigInteger.valueOf(12)), BigInteger.valueOf(6), BigInteger.valueOf(4));
        assertEquals(temp, zombies.zombie(BigInteger.valueOf(6), BigInteger.valueOf(4)).hashCode());
        zombies.insert(Zombie.build(), BigInteger.valueOf(1), BigInteger.valueOf(1));
    }

    /**
     * Method: zombie(int x, int y)
     */
    @Test
    public void testZombie() throws Exception {
        assertEquals(zombies.zombie(BigInteger.valueOf(5), BigInteger.valueOf(22)).hashCode(), zombies.delete(BigInteger.valueOf(5), BigInteger.valueOf(22)).hashCode());
    }

    /**
     * Method: delete(int x, int y)
     */
    @Test(expected = NullPointerException.class)
    public void testDelete() throws Exception {
        zombies.delete(BigInteger.valueOf(1), BigInteger.valueOf(1));
        assertNull(zombies.zombie(BigInteger.valueOf(1), BigInteger.valueOf(1)));
        assertNull(zombies.delete(BigInteger.valueOf(1), BigInteger.valueOf(1)));
        Zombie zz1 = zombies.zombie(BigInteger.valueOf(5), BigInteger.valueOf(6));
        Zombie zz2 = zombies.delete(BigInteger.valueOf(5), BigInteger.valueOf(6));
        assertEquals(zz1, zz2);
        zombies.clearAllZombies();
        zombies.delete(BigInteger.valueOf(5), BigInteger.valueOf(6));
    }

    /**
     * Method: javelin(int xp)
     */
    @Test
    public void testJavelin() throws Exception {
        zombies.insert(zombies.delete(BigInteger.valueOf(4),BigInteger.valueOf(10)), BigInteger.valueOf(10), BigInteger.valueOf(12));
        assertEquals("[4, 80]", Arrays.toString(zombies.javelin(BigInteger.valueOf(4))));
        assertEquals("[2, 2]", Arrays.toString(zombies.javelin(BigInteger.valueOf(2))));
        assertEquals("[10, 12]", Arrays.toString(zombies.javelin(BigInteger.valueOf(10))));
    }

    /**
     * Method: arrow(boolean direction)
     */
    @Test
    public void testArrow() throws Exception {
        assertEquals("[1, 1]", Arrays.toString(zombies.arrow(true)));
        assertEquals("[10, 2]", Arrays.toString(zombies.arrow(false)));
        zombies.clearAllZombies();
        assertNull(zombies.arrow(true));
    }

    /**
     * Method: bomb(int xp, int r)
     */
    @Test
    public void testBomb() throws Exception {
        assertEquals("[4, 80]", Arrays.toString(zombies.bomb(BigInteger.valueOf(5), BigInteger.valueOf(5))));
        zombies.delete(BigInteger.valueOf(4),BigInteger.valueOf(80));
        assertEquals("[7, 28]", Arrays.toString(zombies.bomb(BigInteger.valueOf(4), BigInteger.valueOf(3))));
    }

    /**
     * Method: toString()
     */
    @Test
    public void testToString() throws Exception {
        zombies.clearAllZombies();
        zombies.insert(Zombie.build(), BigInteger.valueOf(1),BigInteger.valueOf(8));
        zombies.insert(Zombie.build(),BigInteger.valueOf(5),BigInteger.valueOf(3));
        assertEquals("Key: 1  \tValue: [[1,8]]\n" +
                "Key: 5  \tValue: [[5,3]]\n", zombies.toString());
    }
}
