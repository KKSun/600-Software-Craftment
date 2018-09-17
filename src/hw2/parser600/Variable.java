package hw2.parser600;

import java.util.Objects;

public final class Variable extends AbstractListSymbol implements ListSymbol{

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

}
