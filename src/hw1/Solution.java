package hw1;

import java.util.*;

class Solution<T extends Comparable<T>> {
    private static<T> boolean oneOfEmpty(Iterator<T> ia, Iterator<T> ib) {
        return !ia.hasNext() || !ib.hasNext();
    }
    static <T>
    List<T> longestPrefix(List<T> a, List<T> b, Comparator<? super T> cmp){
        Iterator<T> ia = Objects.requireNonNull(a,"List a Null").iterator();
        Iterator<T> ib = Objects.requireNonNull(b,"List b Null").iterator();
        //use list iterator

        if(oneOfEmpty(ia,ib)){
            return new ArrayList<>();
        }

        List<T> res = new ArrayList<>();

        while (ia.hasNext() && ib.hasNext()) {
            T aElement = ia.next();
            T bElement = ib.next();
            if (Objects.requireNonNull(cmp,"Comparator Null").compare(aElement, bElement) == 0) { //cmp will return 0 if element in a is equal to element in b
                res.add(bElement);
            } else break;
        }

        return res;
    }
}
