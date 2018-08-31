package hw1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class test {
    public static void main(String[] args){
        System.out.println("----------Test on Integer----------");
        Solution<Integer> s1 = new Solution<>();
        List<Integer> a1 = new ArrayList<>();
        List<Integer> b1 = new ArrayList<>();
        Comparator<Integer> cmp1 = (o1, o2) -> {
            if(o1.intValue() == o2.intValue()){
                return 0;
            }else{
                return -1;
            }
        };
        a1.add(1);a1.add(2);a1.add(3);a1.add(4);a1.add(5);
        b1.add(1);b1.add(2);b1.add(3);b1.add(9);b1.add(5);
        List<Integer> result1 = s1.longestPrefix(a1,b1,cmp1);
        System.out.println("elements in a : " + a1.toString());
        System.out.println("elements in b : " + b1);
        System.out.println("longest-prefix: " + result1.toString());

        System.out.println("----------Test on String----------");
        Solution<String> s2 = new Solution<>();
        List<String> a2 = new ArrayList<>();
        List<String> b2 = new ArrayList<>();
        Comparator<String> cmp2 = (o1, o2) -> {
            if(o1.equals(o2)){
                return 0;
            }else{
                return -1;
            }
        };
        a2.add("amy");a2.add("bob");a2.add("cola");a2.add("mika");
        b2.add("amy");b2.add("bob");b2.add("Dosy");b2.add("mika");
        List<String> result2 = s2.longestPrefix(a2,b2,cmp2);
        System.out.println("elements in a : " + a2.toString());
        System.out.println("elements in b : " + b2);
        System.out.println("longest-prefix: " + result2.toString());

        System.out.println("----------Test on Character----------");
        Solution<Character> s3 = new Solution<>();
        List<Character> a3 = new ArrayList<>();
        List<Character> b3 = new ArrayList<>();
        Comparator<Character> cmp3 = (o1, o2) -> {
            if(o1 == o2){
                return 0;
            }else{
                return -1;
            }
        };
        a3.add('c');a3.add('v');a3.add('a');a3.add('t');
        b3.add('c');b3.add('v');b3.add('b');b3.add('t');b3.add('m');b3.add('0');
        List<Character> result3 = s3.longestPrefix(a3,b3,cmp3);
        System.out.println("elements in a : " + a3.toString());
        System.out.println("elements in b : " + b3);
        System.out.println("longest-prefix: " + result3.toString());
    }
}
