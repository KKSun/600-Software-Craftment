package hw2.test;

import hw2.parser600.Type;
import hw2.parser600.Variable;
import org.junit.Test;

import static org.junit.Assert.*;

public class VariableTest {
    private Variable v = Variable.build("rep1");

    @Test
    public void getType() {
        Type expected = Type.VARIABLE;
        Type res = v.getType();
        assertEquals(expected,res);
    }

    @Test
    public void representationGetter() {
        String expedcted = "rep1";
        String res = v.representationGetter();
        assertEquals(expedcted,res);
    }

    @Test(expected = NullPointerException.class)
    public void build() {
        Variable.build(null);
    }

    @Test
    public void testToString() {
        String expected = "rep1";
        String res = v.toString();
        assertEquals(expected,res);
    }

    @Test
    public void toList(){

    }
}