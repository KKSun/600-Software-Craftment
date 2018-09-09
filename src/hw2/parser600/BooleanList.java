package hw2.parser600;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class BooleanList implements Iterable<ListSymbol> {
    private boolean frozen = false;

    private final List<ListSymbol> listRepresentation = new ArrayList<>();

    public final List<ListSymbol> getListRepresentation() {
        List<ListSymbol> copy = new ArrayList<>(listRepresentation);
        return copy;
    }

    @Override
    public Iterator<ListSymbol> iterator() {
        return listRepresentation.iterator();
    }

    public final Boolean add(ListSymbol listSymbol) throws UnsupportedOperationException{
        if(frozen){
            throw new UnsupportedOperationException("cannot add anymore");
        }
        listRepresentation.add(listSymbol);
        return true;
    }

    public final boolean add(Type type) throws UnsupportedOperationException{
        if(frozen){
            throw new UnsupportedOperationException("cannot add anymore");
        }
        if(NotConnector(type)){
            throw new IllegalArgumentException("you can only put " +
                                               "and, or, not, open, close " +
                                               "if you use method add(Type type)");
        }
        listRepresentation.add(Connector.getConnectorByType(type));
        return true;
    }

    private static boolean NotConnector(Type t){
        return t == Type.VARIABLE || t == Type.EXPRESSION || t == Type.TERM;
    }

    public final void freeze(){
        frozen = true;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Iterator<ListSymbol> i = this.iterator();
        while(i.hasNext()){
            sb.append(i.next().toString());
        }
        return sb.toString();
    }
}
