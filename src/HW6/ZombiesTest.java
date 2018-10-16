package HW6;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;

import java.util.Arrays;

/**
 * Zombies Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Oct 16, 2018</pre>
 */
public class ZombiesTest {

    Zombies zombies = new Zombies();

    @Before
    public void before() throws Exception {
        zombies.insert(Zombie.build(), 1, 1);
        zombies.insert(Zombie.build(), 2, 2);
        zombies.insert(Zombie.build(2, 5), 4, 1);
        zombies.insert(Zombie.build(), 4, 10);
        zombies.insert(Zombie.build(), 4, 80);
        zombies.insert(Zombie.build(), 4, 3);
        zombies.insert(Zombie.build(), 5, 6);
        zombies.insert(Zombie.build(), 5, 22);
        zombies.insert(Zombie.build(), 6, 12);
        zombies.insert(Zombie.build(), 6, 8);
        zombies.insert(Zombie.build(), 7, 28);
        zombies.insert(Zombie.build(), 10, 2);
    }

    @After
    public void after() throws Exception {
        zombies.clearAll();
    }

    /**
     * Method: insert(Zombie z, int x, int y)
     */
    @Test
    public void testInsert() throws Exception {
        int temp = zombies.zombie(6, 12).hashCode();
        zombies.insert(zombies.delete(6, 12), 6, 4);
        assertEquals(temp, zombies.zombie(6, 4).hashCode());
    }

    /**
     * Method: zombie(int x, int y)
     */
    @Test
    public void testZombie() throws Exception {
        assertEquals(zombies.zombie(5, 22).hashCode(), zombies.delete(5, 22).hashCode());
    }

    /**
     * Method: delete(int x, int y)
     */
    @Test
    public void testDelete() throws Exception {
        Zombie zz1 = zombies.zombie(5, 6);
        Zombie zz2 = zombies.delete(5, 6);
        assertEquals(zz1, zz2);
    }

    /**
     * Method: javelin(int xp)
     */
    @Test
    public void testJavelin() throws Exception {
        assertEquals("[4, 80]", Arrays.toString(zombies.javelin(4)));
    }

    /**
     * Method: arrow(boolean direction)
     */
    @Test
    public void testArrow() throws Exception {
        assertEquals("[1, 1]", Arrays.toString(zombies.arrow(true)));
        assertEquals("[10, 2]", Arrays.toString(zombies.arrow(false)));
    }

    /**
     * Method: bomb(int xp, int r)
     */
    @Test
    public void testBomb() throws Exception {
        assertEquals("[4, 80]", Arrays.toString(zombies.bomb(5, 5)));
    }


    /**
     * Method: checkInLine(PriorityQueue<Zombie> zombieInLine, int y)
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCheckInLine() throws Exception {
        zombies.insert(Zombie.build(), 1, 1);
    }

}
