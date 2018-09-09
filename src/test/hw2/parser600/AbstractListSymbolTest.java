package test.hw2.parser600;

import hw2.parser600.BooleanList;
import hw2.parser600.Connector;
import hw2.parser600.Variable;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;


/**
 * AbstractListSymbol Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Sep 9, 2018</pre>
 */
public class AbstractListSymbolTest {

    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    /**
     * Method: toList()
     */
    @Test
    public void testToList() {
        Variable v = Variable.build("awesome");
        Connector c = Connector.build('~');
        BooleanList bv = v.toList();
        BooleanList cv = c.toList();
        System.out.println(bv);
        System.out.println(cv);
    }


} 
