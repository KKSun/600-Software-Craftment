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

    private Connector c1 = Connector.build('^');
    private Connector c2 = Connector.build('v');
    private Connector c3 = Connector.build('~');
    private Connector c4 = Connector.build('(');
    private Connector c5 = Connector.build(')');
    private Connector c6 = Connector.build(')');

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
        Connector.build('m');
    }

    /**
     * Method: toString()
     */
    @Test
    public void testToString() {
        System.out.println(c3.toList().toString());
        System.out.println(c4.toList().toString());
    }


    /**
     * Method: getConnectorByType(Type t)
     */
    @Test
    public void testGetConnectorByType() {
        Connector cc = Connector.getConnectorByType(Type.OR);
        assertEquals(c2,cc);
    }

} 

