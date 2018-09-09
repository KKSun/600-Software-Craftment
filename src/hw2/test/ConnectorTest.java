package hw2.test;

import hw2.parser600.Connector;
import hw2.parser600.Type;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectorTest {
    @Test
    public void sameObject(){
        Connector c1 = Connector.build('v');
        Connector c2 = Connector.build('v');
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    public void getType() {
        Type expected = Type.AND;
        assertEquals(Connector.build('^').getType(), expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void build() {
        Connector.build('m');
    }

    @Test
    public void testToString() {
        String expected = ")";
        assertEquals(")", Connector.build(')').toString());
    }

    @Test
    public void toList(){

    }
}