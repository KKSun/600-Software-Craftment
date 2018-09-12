package hw2.parser600;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class BooleanList implements Iterable<ListSymbol> {
    //represent the BooleanList is frozen or not
    private boolean frozen = false;

    private final List<ListSymbol> listRepresentation = new ArrayList<>();

    public final List<ListSymbol> getListRepresentation() {
        return new ArrayList<>(listRepresentation);
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

    public final boolean add(Type type){
        if(frozen){
            throw new UnsupportedOperationException("cannot add anymore");
        }
        return listRepresentation.add(Connector.getConnectorByType(type));
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
