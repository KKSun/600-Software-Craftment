package test.hw2.parser600;

import hw2.parser600.BooleanList;
import hw2.parser600.Connector;
import hw2.parser600.Type;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;

/**
 * Connector Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Sep 9, 2018</pre>
 */
public class ConnectorTest {

    private Connector c1 = Connector.build(Type.AND);
    private Connector c2 = Connector.build(Type.OR);
    private Connector c3 = Connector.build(Type.NOT);
    private Connector c4 = Connector.build(Type.OPEN);
    private Connector c5 = Connector.build(Type.CLOSE);
    private Connector c6 = Connector.build(Type.CLOSE);

    @Before
    public void before() {

    }

    @After
    public void after() {
        assertEquals(c5.hashCode(), c6.hashCode());
    }

    /**
     * Method: getType()
     */
    @Test
    public void testGetType() {
        Type t = c1.getType();
        Type expected = Type.AND;
        assertEquals(t, expected);
    }

    /**
     * Method: toList()
     */
    @Test
    public void testToList() {
        BooleanList b1 = new BooleanList();
        b1.add(Type.AND);
        BooleanList b2 = new BooleanList();
        b2.add(c1);
        assertEquals(b1.toString().charAt(0), b2.toString().charAt(0));
    }

    /**
     * Method: build(char c)
     */
    @Test(expected = IllegalArgumentException.class)
    public void testBuild() {
        Connector.build(Type.VARIABLE);
    }

    /**
     * Method: toString()
     */
    @Test
    public void testToString() {
        System.out.println(c3.toList().toString());
        System.out.println(c4.toList().toString());
    }

} 

