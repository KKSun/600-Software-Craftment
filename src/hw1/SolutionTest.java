package hw1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void longestPrefix() {
        List<Character> a3 = new ArrayList<>();
        List<Character> b3 = new ArrayList<>();
        Comparator<Character> cmp3 = (o1, o2) -> (o1 == o2) ? 0 : -1;
        a3.add('c');a3.add('v');a3.add('a');a3.add('t');
        b3.add('c');b3.add('v');b3.add('b');b3.add('t');b3.add('m');b3.add('0');
        List<Character> result3 = Solution.longestPrefix(a3,b3,cmp3);
        System.out.println("elements in a : " + a3);
        System.out.println("elements in b : " + b3);
        System.out.println("longest-prefix: " + result3);
        System.out.println();
        List<Character> ansans = new ArrayList<>();
        ansans.add('c');
        ansans.add('v');
        Character[] expected = ansans.toArray(new Character[ansans.size()]);
        assertArrayEquals(expected, result3.toArray(new Character[result3.size()]));

        String sa = "abracadebra";
        String sb = "abracavocado";
        List<Character> l1 = new ArrayList<>();
        for(char c : sa.toCharArray()){
            l1.add(c);
        }
        List<Character> l2 = new ArrayList<>();
        for(char c : sb.toCharArray()){
            l2.add(c);
        }
        List<Character> result4 = Solution.longestPrefix(l1,l2,cmp3);
        System.out.println("elements in a : " + l1);
        System.out.println("elements in b : " + l2);
        System.out.println("longest-prefix: " + Solution.longestPrefix(l1,l2,cmp3));
        List<Character> ansans2 = new ArrayList<>();
        ansans2.add('a');
        ansans2.add('b');
        ansans2.add('r');
        ansans2.add('a');
        ansans2.add('c');
        ansans2.add('a');
        Character[] expected2 = ansans2.toArray(new Character[ansans2.size()]);
        assertArrayEquals(expected2, result4.toArray(new Character[result4.size()]));
    }
}