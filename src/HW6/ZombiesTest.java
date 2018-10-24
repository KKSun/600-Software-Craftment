package HW6;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.PriorityQueue;

/**
 * Zombies Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Oct 24, 2018</pre>
 */
public class ZombiesTest {
    private Zombies zombies = new Zombies();
    private Zombies.TestHook testZombie = zombies.new TestHook();

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

    /**
     *  static boolean LEFT
     */
    @Test
    public void testStatic() throws Exception{
        assertTrue(testZombie.direction);
    }

    @After
    public void after() throws Exception {
        zombies.clearAllZombies();
    }

    /**
     * Method: insert(Zombie z, BigInteger x, BigInteger y)
     * Bad Data: Zombie(1,1) already exist
     * Compound
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInsert_alreadyExist() throws Exception {
        zombies.insert(Zombie.build(),BigInteger.valueOf(1), BigInteger.valueOf(1));
    }

    /**
     * Method: insert(Zombie z, BigInteger x, BigInteger y)
     * Structure
     * Data Flow
     */
    @Test
    public void testInsert_noX() throws Exception {
        zombies.insert(Zombie.build(),BigInteger.valueOf(15), BigInteger.valueOf(20));
    }

    /**
     * Method: insert(Zombie z, BigInteger x, BigInteger y)
     * Structure
     * Data Flow
     */
    @Test
    public void testInsert_normal() throws Exception {
        zombies.insert(Zombie.build(),BigInteger.valueOf(1), BigInteger.valueOf(5));
    }

    /**
     * Method: zombie(BigInteger x, BigInteger y)
     * Structure
     * Data Flow
     */
    @Test
    public void testZombie_null() throws Exception {
        assertNull(zombies.zombie(BigInteger.valueOf(20), BigInteger.valueOf(20)));
    }

    /**
     * Method: zombie(BigInteger x, BigInteger y)
     * Structure
     * Bad Data
     */
    @Test(expected = AssertionError.class)
    public void testZombie_empty() throws Exception {
        PriorityQueue<Zombie> q = testZombie.map.get(BigInteger.valueOf(4));
        q.clear();
        zombies.zombie(BigInteger.valueOf(4), BigInteger.valueOf(4));
    }

    /**
     * Method: zombie(BigInteger x, BigInteger y)
     * Structure
     * Data Flow
     */
    @Test
    public void testZombie_normal() throws Exception {
        Zombie z2 = zombies.zombie(BigInteger.valueOf(4), BigInteger.valueOf(3));
        Zombie z1 = zombies.delete(BigInteger.valueOf(4), BigInteger.valueOf(3));
        assertEquals(z1,z2);
    }

    /**
     * Method: delete(BigInteger x, BigInteger y)
     * Data Flow
     * Bad Data
     */
    @Test(expected = NullPointerException.class)
    public void testDelete_null() throws Exception {
        zombies.delete(BigInteger.valueOf(1), BigInteger.valueOf(1));
        zombies.delete(BigInteger.valueOf(1), BigInteger.valueOf(1));
    }

    /**
     * Method: delete(BigInteger x, BigInteger y)
     * Data Flow
     * Structure
     */
    @Test
    public void testDelete_lineEmpty() throws Exception {
        zombies.delete(BigInteger.valueOf(10), BigInteger.valueOf(2));
        assertNull(testZombie.map.get(BigInteger.valueOf(10)));
    }

    /**
     * Method: delete(BigInteger x, BigInteger y)
     * Structure
     */
    @Test
    public void testDelete_normal_lineNotEmpty() throws Exception {
        Zombie zz1 = zombies.zombie(BigInteger.valueOf(5), BigInteger.valueOf(6));
        Zombie zz2 = zombies.delete(BigInteger.valueOf(5), BigInteger.valueOf(6));
        assertEquals(zz1, zz2);
    }

    /**
     * Method: javelin(BigInteger xp)
     * Boundary xp < x of zombie's min
     */
    @Test
    public void testJavelin_min() throws Exception {
        BigInteger[] loc = zombies.javelin(BigInteger.valueOf(-1));
        assertEquals("[1,1]", "[" + loc[0] + "," + loc[1] + "]");
    }

    /**
     * Method: javelin(BigInteger xp)
     * Boundary xp > x of zombie's max
     */
    @Test
    public void testJavelin_max() throws Exception {
        BigInteger[] loc = zombies.javelin(BigInteger.valueOf(11));
        assertEquals("[10,2]", "[" + loc[0] + "," + loc[1] + "]");
    }

    /**
     * Method: javelin(BigInteger xp)
     * Good Data
     * Boundary: in bound
     */
    @Test
    public void testJavelin_normal() throws Exception {
        BigInteger[] loc = zombies.javelin(BigInteger.valueOf(4));
        assertEquals("[4,80]", "[" + loc[0] + "," + loc[1] + "]");
    }

    /**
     * Method: javelin(BigInteger xp)
     * Good Data: map null
     */
    @Test
    public void testJavelin_empty() throws Exception {
        zombies.clearAllZombies();
        assertNull(zombies.javelin(BigInteger.valueOf(5)));
    }

    /**
     * Method: arrow(boolean direction)
     * Data Flow
     * Structure
     */
    @Test
    public void testArrow_empty() throws Exception {
        zombies.clearAllZombies();
        assertNull(zombies.arrow(true));
    }

    /**
     * Method: arrow(boolean direction)
     * Structure
     * Data Flow
     */
    @Test
    public void testArrow_left() throws Exception {
        BigInteger[] loc = zombies.arrow(true);
        assertEquals("[1,1]", "[" + loc[0] + "," + loc[1] + "]");
    }

    /**
     * Method: arrow(boolean direction)
     * Structure
     * Data Flow
     */
    @Test
    public void testArrow_right() throws Exception {
        BigInteger[] loc = zombies.arrow(false);
        assertEquals("[10,2]", "[" + loc[0] + "," + loc[1] + "]");
    }

    /**
     * Method: arrow(boolean direction)
     * Bad Data
     */
    @Test(expected = NullPointerException.class)
    public void testArrow_null() throws Exception {
        PriorityQueue<Zombie> q = testZombie.map.get(BigInteger.valueOf(1));
        q.clear();
        zombies.arrow(true);
    }

    /**
     * Method: bomb(BigInteger xp, BigInteger r)
     */
    @Test
    public void testBomb() throws Exception {
        //todo
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

    /**
     * Method: clearAllZombies()
     */
    @Test
    public void testClearAllZombies() throws Exception {
        zombies.clearAllZombies();
        assertEquals(0, testZombie.map.size());
    }

    /*
     * tests of private methods below
     */

    /**
     * Method: checkInLine(PriorityQueue<Zombie> zombieInLine, BigInteger x)
     * Data Flow
     */
    @Test
    public void testCheckInLine_null() throws Exception {
        PriorityQueue<Zombie> q = testZombie.map.get(BigInteger.valueOf(4));
        q.clear();
        testZombie.getZombieInLine(q,BigInteger.valueOf(3));
    }

    /**
     * Method: checkInLine(PriorityQueue<Zombie> zombieInLine, BigInteger x)
     * Bad Data
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCheckInLine_YNotExist() throws Exception {
        PriorityQueue<Zombie> q = testZombie.map.get(BigInteger.valueOf(6));
        testZombie.checkInLine(q,BigInteger.valueOf(8));
    }

    /**
     * Method: checkInLine(PriorityQueue<Zombie> zombieInLine, BigInteger x)
     * Data Flow
     */
    @Test
    public void testCheckInLine_YExist() throws Exception {
        PriorityQueue<Zombie> q = testZombie.map.get(BigInteger.valueOf(4));
        testZombie.checkInLine(q,BigInteger.valueOf(3));
        Zombie z1 = testZombie.getZombieInLine(q,BigInteger.valueOf(3));
        Zombie z2 = zombies.zombie(BigInteger.valueOf(4), BigInteger.valueOf(3));
        assertEquals(z1,z2);
    }

    /**
     * Method: getZombieInLine(PriorityQueue<Zombie> zombieInLine, BigInteger x)
     * Data Flow
     */
    @Test
    public void testGetZombieInLine_null() throws Exception {
        PriorityQueue<Zombie> q = testZombie.map.get(BigInteger.valueOf(4));
        q.clear();
        Zombie z1 = testZombie.getZombieInLine(q,BigInteger.valueOf(2));
        assertNull(z1);
    }

    /**
     * Method: getZombieInLine(PriorityQueue<Zombie> zombieInLine, BigInteger x)
     * Data Flow
     */
    @Test
    public void testGetZombieInLine_notExist() throws Exception {
        PriorityQueue<Zombie> q = testZombie.map.get(BigInteger.valueOf(4));
        Zombie z1 = testZombie.getZombieInLine(q,BigInteger.valueOf(2));
        assertNull(z1);
    }

    /**
     * Method: getZombieInLine(PriorityQueue<Zombie> zombieInLine, BigInteger x)
     * Data Flow
     */
    @Test
    public void testGetZombieInLine_normal() throws Exception {
        PriorityQueue<Zombie> q = testZombie.map.get(BigInteger.valueOf(4));
        Zombie z1 = testZombie.getZombieInLine(q,BigInteger.valueOf(3));
        Zombie z2 = zombies.delete(BigInteger.valueOf(4), BigInteger.valueOf(3));
        assertEquals(z1,z2);
    }

    /**
     * Method: getMaxYInSubmap(NavigableMap<BigInteger, PriorityQueue<Zombie>> subMap)
     */
    @Test
    public void testGetMaxYInSubmap() throws Exception {
        //todo
    }

    /**
     * Method: checkInBound(BigInteger xp)
     * Boundary: xp < x min of zombies
     */
    @Test
    public void testCheckInBound_min() throws Exception {
        assertEquals(BigInteger.valueOf(1), testZombie.checkInBound(BigInteger.valueOf(-10)));
    }

    /**
     * Method: checkInBound(BigInteger xp)
     * Boundary: xp > x max of zombies
     */
    @Test
    public void testCheckInBound_max() throws Exception {
        assertEquals(BigInteger.valueOf(10), testZombie.checkInBound(BigInteger.valueOf(20)));
    }

    /**
     * Method: checkInBound(BigInteger xp)
     * Boundary: xp = x
     */
    @Test
    public void testCheckInBound_justSameX() throws Exception {
        assertEquals(BigInteger.valueOf(5), testZombie.checkInBound(BigInteger.valueOf(5)));
    }

    /**
     * Method: checkInBound(BigInteger xp)
     * Boundary: xp in bound choose left near x
     */
    @Test
    public void testCheckInBound_leftInBound() throws Exception {
        zombies.clearAllZombies();

        zombies.insert(Zombie.build(), BigInteger.valueOf(1), BigInteger.valueOf(1));
        zombies.insert(Zombie.build(), BigInteger.valueOf(2), BigInteger.valueOf(2));
        zombies.insert(Zombie.build(), BigInteger.valueOf(10), BigInteger.valueOf(2));

        assertEquals(BigInteger.valueOf(2), testZombie.checkInBound(BigInteger.valueOf(3)));
    }

    /**
     * Method: checkInBound(BigInteger xp)
     * Boundary: xp in bound choose right near x
     */
    @Test
    public void testCheckInBound_rightinBound() throws Exception {
        zombies.clearAllZombies();

        zombies.insert(Zombie.build(), BigInteger.valueOf(1), BigInteger.valueOf(1));
        zombies.insert(Zombie.build(), BigInteger.valueOf(7), BigInteger.valueOf(28));
        zombies.insert(Zombie.build(), BigInteger.valueOf(10), BigInteger.valueOf(2));

        assertEquals(BigInteger.valueOf(7), testZombie.checkInBound(BigInteger.valueOf(6)));
    }

} 
