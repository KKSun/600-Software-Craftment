package test.hw2.parser600;

import hw2.parser600.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

/**
 * Expression Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Sep 19, 2018</pre>
 */
public class ExpressionTest {

    Variable a = Variable.build("a");
    Term ta = Term.build(a);
    Expression ena = Expression.build(false, ta);
    Variable b = Variable.build("b");
    Term tb = Term.build(b);
    Expression eb = Expression.build(true, tb);
    Expression ccc = Expression.build(true, eb, ena);
    Term mAcB = Term.build(ccc);
    Expression emAcB = Expression.build(false, mAcB);
    Expression ea = Expression.build(true,ta);
    Expression eAoemAcB = Expression.build(false, ea, emAcB);
    Term teAomAcB = Term.build(eAoemAcB);
    Expression nteAomAcB = Expression.build(false, teAomAcB);
    Term tneAomAcB = Term.build(nteAomAcB);

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
        assertEquals(Type.EXPRESSION, ena.getType());
        assertEquals(Type.TERM, mAcB.getType());
    }

    /**
     * Method: getStructure()
     */
    @Test
    public void testGetStructure() throws Exception {
        assertEquals(Type.NOT, ena.getStructure());
        assertEquals(Type.AND, ccc.getStructure());
        assertEquals(Type.TERM, eb.getStructure());
        assertEquals(Type.EXPRESSION, mAcB.getStructure());
    }

    /**
     * Method: build(boolean isPositive, Symbol subexpression)
     */
    @Test
    public void testBuildForIsPositiveSubexpression() throws Exception {
        Variable a = Variable.build("a");
        Term ta = Term.build(a);
        Expression ena = Expression.build(false, ta);
        Variable b = Variable.build("b");
        Term tb = Term.build(b);
        Expression eb = Expression.build(true, tb);
        Expression ccc = Expression.build(true, ena, eb);
        Term mAcB = Term.build(ccc);
    }

    /**
     * Method: build(boolean isConjunction, Symbol leftSubexpression, Symbol rightSubexpression)
     */
    @Test
    public void testBuildForIsConjunctionLeftSubexpressionRightSubexpression() throws Exception {

    }

    /**
     * Method: toList()
     */
    @Test
    public void testToList() throws Exception {
        System.out.println(nteAomAcB.toList());
        System.out.println(tneAomAcB.toList());
    }

    /**
     * Method: toString()
     */
    @Test
    public void testToString() throws Exception {
        System.out.println(nteAomAcB.toString());
        System.out.println(tneAomAcB.toString());
    }

    /**
     * Method: complexity()
     */
    @Test
    public void testComplexity() throws Exception {
        System.out.println(nteAomAcB.complexity());
        System.out.println(tneAomAcB.complexity());
    }


} 
