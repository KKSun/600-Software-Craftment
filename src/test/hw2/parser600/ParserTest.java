package test.hw2.parser600;

import hw2.parser600.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * Parser Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Sep 26, 2018</pre>
 */
public class ParserTest {


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
    BooleanList ll = new BooleanList();
    Expression anb = Expression.build(true, ea, eb);

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: parse(BooleanList input)
     */
    @Test
    public void testParse() throws Exception {
        System.out.println(anb.toList());
    }

} 