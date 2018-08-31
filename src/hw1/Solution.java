package hw1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

class Solution<T extends Comparable<T>> {
    List<T> longestPrefix(List<T> a, List<T> b, Comparator<? super T> cmp){
        Iterator<T> ia = a.iterator();
        Iterator<T> ib = b.iterator();

        if(!ia.hasNext() || !ib.hasNext()){
            return new ArrayList<>();
        }

        List<T> res = new ArrayList<>();
        while(ia.hasNext() && ib.hasNext()){
            T aElement = ia.next();
            T bElement = ib.next();
            if(cmp.compare(aElement, bElement) == 0){ //cmp will return 0 if element in a is equal to element in b
                res.add(bElement);
            }else{
                break;
            }
        }

        return res;
    }
}
