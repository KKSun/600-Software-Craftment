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
        return this.getStructure() == Type.VARIABLE ?
                this.getSubexpression().toString():
                "(" + this.getSubexpression().toString() + ")";
        //return this.toList().toString();
    }
                                                                //difference of using subexpression directly with through getter????
                                                                //
                                                                //
                                                                //   this is a question ❓
                                                                //
                                                                //
                                                                //what's the meaning of this getSubexpression since subexpression is private?????


    public long complexity() {
        return this.getSubexpression().complexity();
    }


    public BooleanList toList(){
        if(this.getStructure() == Type.VARIABLE){
            BooleanList res = new BooleanList();
            res.add((Variable) this.getSubexpression());
            return res;
        }
        BooleanList res = new BooleanList();
        res.add(Type.OPEN);
        for (ListSymbol listSymbol : this.getSubexpression().toList()) {
            res.add(listSymbol);
        }
        res.add(Type.CLOSE);
        return res;
    }
}
