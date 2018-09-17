package test.hw2.parser600;

import hw2.parser600.Type;
import hw2.parser600.Variable;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

/**
 * Variable Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Sep 9, 2018</pre>
 */
public class VariableTest {
    private Variable v = Variable.build("cc");

    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    /**
     * Method: getType()
     */
    @Test
    public void testGetType() {
        Type expected = Type.VARIABLE;
        Type t = v.getType();
        assertEquals(t,expected);
    }

    /**
     * Method: representationGetter()
     */
    @Test
    public void testRepresentationGetter() {
        String expected = "cc";
        String s = v.getRepresentation();
        assertEquals(s,expected);
    }

    /**
     * Method: build(String representation)
     */
    @Test(expected = NullPointerException.class)
    public void testBuild() {
        Variable.build(null);
    }

    /**
     * Method: toString()
     */
    @Test
    public void testToString() {
        String expected = "cc";
        String s = v.toString();
        assertEquals(expected,s);
    }

} 
