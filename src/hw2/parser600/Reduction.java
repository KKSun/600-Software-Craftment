package hw2.parser600;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

final class Reduction {

    private final List<Type> pattern;
    private final Function<List<Symbol>, TreeSymbol> reduction;

    private Reduction(List<Type> pattern, Function<List<Symbol>, TreeSymbol> reduction){
        this.pattern = pattern;
        this.reduction = reduction;
    }

    static final Reduction build(List<Type> pattern, Function<List<Symbol>, TreeSymbol> reduction){
        return new Reduction(
                Objects.requireNonNull(pattern, "pattern should not be null"),
                Objects.requireNonNull(reduction, "reduction should not be null"));
    }

    final int size(){
        return pattern.size();
    }

    final Symbol apply(List<Symbol> symbolList){
        return reduction.apply(symbolList);
    }

    final boolean matches(List<Type> typeList){
        return pattern.equals(typeList);
    }

}
