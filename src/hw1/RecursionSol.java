package hw1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

class RecursionSol<T extends Comparable<T>> {
    private static<T> boolean oneOfEmpty(List<T> a, List<T> b) {
        return a.isEmpty() || b.isEmpty();
    }

    static<T> List<T> longestPrefix(List<T> a, List<T> b, Comparator<? super T> cmp){
        List<T> result = new ArrayList<>();
        if(oneOfEmpty(a,b)) return result;

        T aElement = Objects.requireNonNull(a, "List a Null").get(0);
        T bElement = Objects.requireNonNull(b, "List b Null").get(0);
        int aLen = a.size();
        int bLen = b.size();

        if(Objects.requireNonNull(cmp, "Null Comparator").compare(aElement,bElement) != 0){ // when aElements equal to bElements, cmp is 0
            return result;
        }else{
            result.add(aElement);
            result.addAll(longestPrefix(a.subList(1, aLen), b.subList(1, bLen), cmp));
        }

        return result;
    }
}
