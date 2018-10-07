package hw2.parser600;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

abstract class AbstractTreeSymbol implements TreeSymbol{
    private final Type structure;

    public Type getStructure(){
        return structure;
    }

    protected AbstractTreeSymbol(Type t){
        this.structure = t;
    }

    protected static final void validateSubexpression( Symbol subexpression, Set<Type> expectedTypes, String subexpressionDescription){
        Objects.requireNonNull(subexpression, subexpressionDescription + ": subexpression should not be null");
        if(!expectedTypes.contains(subexpression.getType())){
            throw new IllegalArgumentException(subexpressionDescription + ": type of subexpression is wrong");
        }
    }

    protected static final void validateSubexpression( Symbol subexpression, Type expectedType, String subexpressionDescription){
        validateSubexpression(subexpression, new HashSet<Type>(){{add(expectedType);}}, subexpressionDescription);
    }
}
