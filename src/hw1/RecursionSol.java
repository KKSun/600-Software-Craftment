package hw1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class RecursionSol<T extends Comparable<T>> {
    private List<T> result = new ArrayList<>();
    private int count = 0;
    List<T> longestPrefix(List<T> a, List<T> b, Comparator<? super T> cmp){
        if(a.isEmpty() || b.isEmpty()) return result;
        T aElement = a.get(0);
        T bElement = b.get(0);
        int aLen = a.size();
        int bLen = b.size();
        if(cmp.compare(aElement,bElement) != 0){ // when aElements equal to bElements, cmp is 0
            return result;
        }else{
            result.add(aElement);
            count += 1;
            result.addAll(longestPrefix(a.subList(count, aLen), b.subList(count, bLen), cmp));
            return result;
        }
    }
}
