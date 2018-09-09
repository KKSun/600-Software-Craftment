package hw2.parser600;

import java.util.Objects;

public final class Connector extends AbstractListSymbol implements ListSymbol{

    private final Type t;
    private static Connector and_Connector = new Connector(Type.AND);
    private static Connector or_Connector = new Connector(Type.OR);
    private static Connector not_Connector = new Connector(Type.NOT);
    private static Connector open_Connector = new Connector(Type.OPEN);
    private static Connector close_Connector = new Connector(Type.CLOSE);

    private Connector(Type t) {
        // Type t should not be null
        this.t = Objects.requireNonNull(t, "type of a Symbol should not be null");
    }

    @Override
    public final Type getType() {
        return t;
    }

    //use char connector [  v  ,  ^  ,  ~  ,  (  ,   )  ]
    //respectively means [  or , and , not , open, close]
    //if specific connector is null, then create one, else we directly return the existing connector
    public static final Connector build(char c) throws IllegalArgumentException{
        if(c == '^'){
            return and_Connector;
        }else if(c == 'v'){
            return or_Connector;
        }else if(c == '~'){
            return not_Connector;
        }else if(c == '('){
            return open_Connector;
        }else if(c == ')'){
            return close_Connector;
        }else{
            throw new IllegalArgumentException("Connector Illegal");
        }
    }

    public String toString(){
        Type t = this.getType();
        String s = "";
        switch (t){
            case OR:
                s += "v";
                break;
            case AND:
                s += "^";
                break;
            case NOT:
                s += "~";
                break;
            case OPEN:
                s += "(";
                break;
            case CLOSE:
                s += ")";
                break;
        }
        return s;
    }

    //helper function to simplify BooleanList add(Type t)
    public static Connector getConnectorByType(Type t){
        if(t == Type.OR){
            return or_Connector;
        }else if(t == Type.AND){
            return and_Connector;
        }else if(t == Type.NOT){
            return not_Connector;
        }else if(t == Type.OPEN){
            return open_Connector;
        }else{
            return close_Connector;
        }
    }
}
