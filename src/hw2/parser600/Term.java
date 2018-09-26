package hw2.parser600;

import java.util.HashSet;
import java.util.Set;

public final class Term extends AbstractTreeSymbol implements TreeSymbol {

    public Type getType(){
        return Type.TERM;
    }

    private final Symbol subexpression;

    final Symbol getSubexpression(){
        return subexpression;
    }


    private Term(Symbol subexpression) {
        super(subexpression.getType());
        this.subexpression = subexpression;
    }


    private static Set<Type> s = new HashSet<Type>(){{
        add(Type.VARIABLE);
        add(Type.EXPRESSION);
    }};

    public static final Term build(Symbol subexpression){
        //validate the input subexpression as Type of VARIABLE or EXPRESSION
        AbstractTreeSymbol.validateSubexpression(subexpression,s,"you want to build a term");
        return new Term(subexpression);
    }


    public String toString() {
        return this.toList().toString();
    }


    public long complexity() {
        return this.getSubexpression().complexity();
    }


    public BooleanList toList(){
        BooleanList res = new BooleanList();
        if(this.getStructure() == Type.VARIABLE){
            res.add((Variable) this.getSubexpression());
        }else {
            res.add(Type.OPEN);
            res.addAll(this.getSubexpression().toList());
            res.add(Type.CLOSE);
        }
        return res;
    }
}
