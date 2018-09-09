package test.hw2.parser600;

import hw2.parser600.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Iterator;

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
    Connector c1 = Connector.build('^');
    Connector c2 = Connector.build('~');
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
        System.out.println(bb.getListRepresentation().toString());
    }

    /**
     * Method: iterator()
     */
    @Test
    public void testIterator() {
        Iterator<ListSymbol> i = bb.iterator();
        while(i.hasNext()){
            ListSymbol ls = i.next();
            System.out.print(ls + " ");
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
        String expected = "a^~b";
        assertTrue(bb.toString().equals(expected));
    }


} 
