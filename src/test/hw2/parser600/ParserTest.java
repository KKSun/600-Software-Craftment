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
    Variable c = Variable.build("c");
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
        BooleanList lll = new BooleanList();
        lll.addAll(teAomAcB.toList());
        State s = Parser.parse(lll);
        System.out.println(s.getExpression());
        System.out.println(s.getExpression().getType());
        System.out.println(s.getExpression().simplified());
        System.out.println(s.isCorrect());
        System.out.println(s.getWorkingList().size());
        System.out.println("--------------------");
        BooleanList llll = new BooleanList();
        llll.addAll(lll);
        llll.add(Type.OPEN);
        State ss = Parser.parse(llll);
        System.out.println(ss.getExpression());
        System.out.println(ss.isCorrect());
        System.out.println(ss.getWorkingList().size());
        System.out.println("--------------------");
        ll.add(Type.OPEN);
        ll.add(Type.OPEN);
        ll.add(Type.OPEN);
        ll.add(a);
        ll.add(Type.OR);
        ll.add(b);
        ll.add(Type.CLOSE);
        ll.add(Type.AND);
        ll.add(Type.OPEN);
        ll.add(Type.OPEN);
        ll.add(Type.OPEN);
        ll.add(Type.NOT);
        ll.add(b);
        ll.add(Type.OR);
        ll.add(c);
        ll.add(Type.CLOSE);
        ll.add(Type.CLOSE);
        ll.add(Type.CLOSE);
        ll.add(Type.CLOSE);
        ll.add(Type.CLOSE);
//        ll.add(Type.OPEN);
//        ll.add(Type.OPEN);
//        ll.add(a);
//        ll.add(Type.CLOSE);
//        ll.add(Type.CLOSE);
        State sss = Parser.parse(ll);
        System.out.println(sss.getExpression());
        System.out.println(sss.getExpression().getStructure());
        System.out.println(sss.getExpression().getType());
        System.out.println(sss.isCorrect());
        System.out.println("--------------------");
        System.out.println(sss.getExpression().simplified());
    }

} 
