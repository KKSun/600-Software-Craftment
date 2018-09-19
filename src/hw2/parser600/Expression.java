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
        BooleanList lex = this.getLeftSubexpression().toList();
        BooleanList rex = this.getRightSubexpression().toList();
        BooleanList res = new BooleanList();
        for(ListSymbol i : lex) res.add(i);
        res.add(conj);
        for(ListSymbol i : rex) res.add(i);
        return res;
    }


    public String toString(){
        //return this.toList().toString();
        if(this.getStructure() == Type.TERM){
            return this.getLeftSubexpression().toString();
        }
        if(this.getStructure() == Type.NOT){
            return Connector.build(Type.NOT).toString() + this.getLeftSubexpression().toString();
        }
        String con = this.getStructure() == Type.AND ? Connector.build(Type.AND).toString() : Connector.build(Type.OR).toString();
        return this.getLeftSubexpression().toString() + con + this.getRightSubexpression().toString();
    }


    public long complexity(){
        //return this.toList().complexity();
        long plus = this.getStructure() == Type.AND || this.getStructure() == Type.OR ? 1 : 0;
        if(this.getStructure() == Type.NOT || this.getStructure() == Type.TERM) return this.getLeftSubexpression().complexity();
        return this.getLeftSubexpression().complexity() + this.getRightSubexpression().complexity() + plus;
    }

}
