package hw2.parser600;

import java.util.Objects;

public final class Variable extends AbstractListSymbol implements ListSymbol, TreeSymbol{
    //code of hw2
    private final String representation;

    private Variable(String representation) {
        this.representation = representation;
    }

    @Override
    public Type getType() {
        return Type.VARIABLE;
    }

    public static Variable build(String representation){
        // representation is required to be not null
        return new Variable(Objects.
                requireNonNull(representation, "representation of a Variable should not be null"));
    }

    public final String getRepresentation(){
        return representation;
    }

    public String toString(){
        return this.getRepresentation();
    }

    // new code of hw3
    @Override
    public long complexity() {
        return 0;
    }

    @Override
    public Type getStructure() {
        return Type.VARIABLE;
    }
}
