package hw2.parser600;

import java.util.Objects;

public final class Variable extends AbstractListSymbol implements ListSymbol{

    private final String representation;

    private Variable(String representation) {
        // representation is required to be not null
        this.representation =
                Objects.requireNonNull(representation, "representation of a Variable should not be null");
    }

    @Override
    public Type getType() {
        return Type.VARIABLE;
    }

    public final String representationGetter(){
        return representation;
    }

    //statically build the Variable since the constructor is private
    public static final Variable build(String representation){
        return new Variable(representation);
    }

    public String toString(){
        return representation;
    }
}
