package test.hw2.parser600;

import hw2.parser600.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import sun.jvm.hotspot.utilities.ObjectReader;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;


/**
 * BooleanList Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Sep 9, 2018</pre>
 */
public class BooleanListTest {

    BooleanList bb = new BooleanList();
    Variable v1 = Variable.build("a");
    Connector c1 = Connector.build(Type.AND);
    Connector c2 = Connector.build(Type.NOT);
    Variable v2 = Variable.build("b");

    @Before
    public void before() {
        bb.add(v1);
        bb.add(c1);
        bb.add(Type.NOT);
        bb.add(v2);
    }

    @After
    public void after() {
    }

    /**
     * Method: getListRepresentation()
     */
    @Test
    public void testGetListRepresentation() {
        List<ListSymbol> bb2 = bb.getListRepresentation();
        assertTrue(bb2.hashCode() != bb.hashCode());
        System.out.println(bb2.toString());
        System.out.println(bb.toString());
    }

    /**
     * Method: iterator()
     */
    @Test
    public void testIterator() {
        Iterator<ListSymbol> i = bb.iterator();
        while(i.hasNext()){
            ListSymbol ls = i.next();
            System.out.print((ls + " "));
        }
    }

    /**
     * Method: add(Type type)
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddType() {
        assertTrue(bb.add(Type.EXPRESSION));
    }

    /**
     * Method: freeze()
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testFreeze() {
        bb.freeze();
        bb.add(Type.NOT);
    }

    /**
     * Method: toString()
     */
    @Test
    public void testToString() {
        String expected = "a ∧ ¬ b";
        assertEquals(bb.toString(), expected);
        bb.add(Type.AND);
        bb.add(Variable.build("c"));
        bb.add(Type.OR);
        bb.add(Variable.build("d"));
        System.out.println(bb);
    }


} 
