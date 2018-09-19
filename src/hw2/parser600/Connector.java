package hw2.parser600;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Connector extends AbstractListSymbol implements ListSymbol{

    private final Type t;
    private final String s;
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

    private Connector(Type t, String s) {
        this.t = t;
        this.s = s;
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
        if(isAndOr()){
            return 1;
        }
        return 0;
    }

    public static Connector build(Type t) throws IllegalArgumentException{
        // Type t should not be null
        switch (Objects.requireNonNull(t, "Type of a connector should not be null")){
            case AND:
                return connectorTable.get(Type.AND);
            case OR:
                return connectorTable.get(Type.OR);
            case NOT:
                return connectorTable.get(Type.NOT);
            case OPEN:
                return connectorTable.get(Type.OPEN);
            case CLOSE:
                return connectorTable.get(Type.CLOSE);
            default:
                //throws exception when argument is illegal
                throw new IllegalArgumentException("Argument to build connector is invalid");
        }
    }

    public String toString(){
        return this.s;
    }
}
