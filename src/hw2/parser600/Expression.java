package hw2.parser600;

import java.util.*;

public final class Expression extends AbstractTreeSymbol implements TreeSymbol{

    public Type getType(){
        return Type.EXPRESSION;
    }


    private final Symbol leftSubexpression;
    private final Symbol rightSubexpression;

    public final Symbol getLeftSubexpression() {
        return leftSubexpression;
    }
    final Symbol getRightSubexpression() {
        return rightSubexpression;
    }


    private Expression(Type structure, Symbol leftSubexpression, Symbol rightSubexpression){
        super(structure);
        this.leftSubexpression = leftSubexpression;
        this.rightSubexpression = rightSubexpression;
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
        AbstractTreeSymbol.validateSubexpression(leftSubexpression,  Type.EXPRESSION,"test valid left subexpression");
        AbstractTreeSymbol.validateSubexpression(rightSubexpression, Type.EXPRESSION,"test valid right subexpression");
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
        res.addAll(left);

        if(isAndOr()){
            BooleanList right = this.getRightSubexpression().toList();
            res.add(this.getStructure());
            res.addAll(right);
        }

        return res;
    }


    public String toString(){
        return this.toList().toString();
    }


    public long complexity(){
        return this.toList().complexity();
    }

    @Override
    public Optional<Symbol> subterm() {
        return null;
    }


    @Override
    public Symbol simplified() {
        return null;
    }

}
