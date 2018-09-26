package hw2.parser600;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Connector extends AbstractListSymbol implements ListSymbol{

    private final Type t;
    private final String representation;
    private static final Map<Type,Connector> connectorTable;

    static {
        Map<Type, Connector> initConnectorTable = new HashMap<>();
        initConnectorTable.put(Type.OR, new Connector(Type.OR, "\u2228"));
        initConnectorTable.put(Type.AND, new Connector(Type.AND, "\u2227"));
        initConnectorTable.put(Type.NOT, new Connector(Type.NOT, "\u00ac"));
        initConnectorTable.put(Type.OPEN, new Connector(Type.OPEN, "("));
        initConnectorTable.put(Type.CLOSE, new Connector(Type.CLOSE, ")"));
        connectorTable = Collections.unmodifiableMap(initConnectorTable);
    }

    private Connector(Type t, String representation) {
        this.t = t;
        this.representation = representation;
    }

    @Override
    public final Type getType() {
        return t;
    }

    private boolean isAndOr(){
        return this.t == Type.AND || this.t == Type.OR;
    }

    @Override
    public long complexity() {
        return isAndOr() ? 1 : 0;
    }

    public static Connector build(Type t) throws IllegalArgumentException{
        if(!connectorTable.containsKey(t)) throw new IllegalArgumentException("wrong argument, should be a connector");
        return connectorTable.get(Objects.requireNonNull(t, "Type of a connector should not be null"));
    }

    public String toString(){
        return this.representation;
    }
}
