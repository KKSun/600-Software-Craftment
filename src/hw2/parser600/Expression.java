package hw2.parser600;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class Expression extends AbstractTreeSymbol implements TreeSymbol{

    public Type getType(){
        return Type.EXPRESSION;
    }

    private final TreeSymbol leftSubexpression;
    private final TreeSymbol rightSubexpression;

    private final TreeSymbol getLeftSubexpression() {
        return leftSubexpression;
    }

    private final TreeSymbol getRightSubexpression() {
        return rightSubexpression;
    }

    private Expression(Type structure, Symbol leftSubexpression, Symbol rightSubexpression){
        super(structure);
        this.leftSubexpression = (TreeSymbol) leftSubexpression;
        this.rightSubexpression = (TreeSymbol) rightSubexpression;
    }

    private static final Set<Type> termOrExpression = new HashSet<Type>(){{
        add(Type.EXPRESSION);
        add(Type.TERM);
    }};

    public static final Expression build(boolean isPositive, Symbol subexpression){
        AbstractTreeSymbol.validateSubexpression(subexpression, termOrExpression,"to build a expression of (neg-)term");
        if(isPositive){
            return new Expression(Type.TERM, subexpression,null);
        }
        return new Expression(Type.NOT, subexpression, null);
    }

    public static final Expression build(boolean isConjunction, Symbol leftSubexpression, Symbol rightSubexpression){
        AbstractTreeSymbol.validateSubexpression((TreeSymbol) leftSubexpression, Type.EXPRESSION,"test valid left subexpression");
        AbstractTreeSymbol.validateSubexpression((TreeSymbol) rightSubexpression, Type.EXPRESSION,"test valid right subexpression");
        if(isConjunction){
            return new Expression(Type.AND, leftSubexpression, rightSubexpression);
        }
        return new Expression(Type.OR, leftSubexpression, rightSubexpression);
    }

    public BooleanList toList(){
        if(this.getStructure() == Type.TERM){
            return this.getLeftSubexpression().toList();
        }
        if(this.getStructure() == Type.NOT){
            BooleanList nterm = this.getLeftSubexpression().toList();
            BooleanList res = new BooleanList();
            res.add(Type.NOT);
            for(ListSymbol i : nterm) res.add(i);
            return res;
        }
        ListSymbol conj = this.getStructure() == Type.AND ? Connector.build(Type.AND) : Connector.build(Type.OR);
        BooleanList res = new BooleanList();
        BooleanList lex = this.getLeftSubexpression().toList();
        BooleanList rex = this.getRightSubexpression().toList();
        for(ListSymbol i : lex) res.add(i);
        res.add(conj);
        for(ListSymbol i : rex) res.add(i);
        return res;
    }

    public String toString(){
        //todo
        return this.toList().toString();
    }

    public long complexity(){
        return this.toList().complexity();
    }

}
