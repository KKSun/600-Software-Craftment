package test.hw2.parser600;

import hw2.parser600.Expression;
import hw2.parser600.Term;
import hw2.parser600.Type;
import hw2.parser600.Variable;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

/**
 * Term Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Sep 19, 2018</pre>
 */
public class TermTest {

    Variable a = Variable.build("a");
    Term ta = Term.build(a);
    Expression ena = Expression.build(false, ta);
    Variable b = Variable.build("b");
    Term tb = Term.build(b);
    Expression eb = Expression.build(true, tb);
    Expression ccc = Expression.build(true, eb, ena);
    Term tccc = Term.build(ccc);

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getType()
     */
    @Test
    public void testGetType() throws Exception {
        assertEquals(Type.TERM, ta.getType());
        assertEquals(Type.TERM, tccc.getType());
    }

    /**
     * Method: getStructure()
     */
    @Test
    public void testGetStructure() throws Exception {
        assertEquals(Type.VARIABLE, ta.getStructure());
        assertEquals(Type.EXPRESSION, tccc.getStructure());
    }

    /**
     * Method: build(Symbol subexpression)
     */
    @Test
    public void testBuild() throws Exception {

    }

    /**
     * Method: toString()
     */
    @Test
    public void testToString() throws Exception {
        System.out.println(a.toString());
        System.out.println(ta.toString());
        System.out.println(ena.toString());
        System.out.println(ccc.toString());
        System.out.println(tccc.toString());
    }

    /**
     * Method: complexity()
     */
    @Test
    public void testComplexity() throws Exception {
        System.out.println(tccc.complexity());
    }

    /**
     * Method: toList()
     */
    @Test
    public void testToList() throws Exception {
        System.out.println(a.toList());
        System.out.println(ta.toList());
        System.out.println(ena.toList());
        System.out.println(ccc.toList());
        System.out.println(tccc.toList());
    }


} 
