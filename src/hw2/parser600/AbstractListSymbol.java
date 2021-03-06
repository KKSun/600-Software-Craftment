package hw2.parser600;

import java.util.Optional;

abstract class AbstractListSymbol implements ListSymbol{
    // can be applied to both Variable and Connector as long as they extend AbstractListSymbol
    @Override
    public BooleanList toList() {
        BooleanList l = new BooleanList();
        l.add(this);
        return l;
    }

    @Override
    public Symbol simplified() {
        return this;
    }

    @Override
    public Optional<Symbol> subterm() {
        return Optional.empty();
    }
}
