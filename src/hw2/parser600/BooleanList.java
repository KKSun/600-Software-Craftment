package hw2.parser600;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public final class BooleanList implements Iterable<ListSymbol> {

    //cannot be final if we use Collections.unmodifiableList() to do 'freeze'
    private List<ListSymbol> listRepresentation = new ArrayList<>();

    public final List<ListSymbol> getListRepresentation() {
        return new ArrayList<>(listRepresentation);
    }

    @Override
    public Iterator<ListSymbol> iterator() {
        return this.getListRepresentation().iterator();
    }

    public final Boolean add(ListSymbol listSymbol){
        return listRepresentation.add(listSymbol);
    }

    public final boolean add(Type type){
        return listRepresentation.add(Connector.build(type));
    }

    public final void addAll(BooleanList list){
        for(ListSymbol each : list){
            listRepresentation.add(each);
        }
    }

    public final void freeze(){
        listRepresentation = Collections.unmodifiableList(listRepresentation);
    }

    public String toString(){
        return getListRepresentation().stream().
                map(ListSymbol::toString).collect(Collectors.joining(""));
    }

    public final long complexity(){
        return getListRepresentation().stream().mapToLong(ListSymbol::complexity).sum();
    }
}
