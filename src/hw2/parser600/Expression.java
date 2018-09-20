package hw2.parser600;

import java.util.HashSet;
import java.util.Set;

public final class Expression extends AbstractTreeSymbol implements TreeSymbol{

    public Type getType(){
        return Type.EXPRESSION;
    }


    private final TreeSymbol leftSubexpression;
    private final TreeSymbol rightSubexpression;

    final TreeSymbol getLeftSubexpression() {
        return leftSubexpression;
    }
    final TreeSymbol getRightSubexpression() {
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
        return isPositive ?
                new Expression(Type.TERM, subexpression,null):
                new Expression(Type.NOT, subexpression, null);
    }


    public static final Expression build(boolean isConjunction, Symbol leftSubexpression, Symbol rightSubexpression){
        //valid the input first
        AbstractTreeSymbol.validateSubexpression((TreeSymbol) leftSubexpression, Type.EXPRESSION,"test valid left subexpression");
        AbstractTreeSymbol.validateSubexpression((TreeSymbol) rightSubexpression, Type.EXPRESSION,"test valid right subexpression");
        //return conjunction when input type is AND or else
        return isConjunction ?
                new Expression(Type.AND, leftSubexpression, rightSubexpression):
                new Expression(Type.OR, leftSubexpression, rightSubexpression);
    }

    private final boolean isAndOr(){
        return this.getStructure() == Type.AND || this.getStructure() == Type.OR;
    }

    public BooleanList toList(){
        BooleanList res = new BooleanList();

        if(this.getStructure() == Type.NOT){
            res.add(Type.NOT);
        }

        BooleanList left = this.getLeftSubexpression().toList();
        for(ListSymbol i : left) res.add(i);

        if(isAndOr()){
            ListSymbol conj = this.getStructure() == Type.AND ? Connector.build(Type.AND) : Connector.build(Type.OR);
            BooleanList rex = this.getRightSubexpression().toList();
            for(ListSymbol i : rex) res.add(i);
        }

        return res;
    }


    public String toString(){
        return this.toList().toString();
    }


    public long complexity(){
        return this.toList().complexity();
    }

}
