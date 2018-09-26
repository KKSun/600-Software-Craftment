package hw2.parser600;

import java.util.*;
import java.util.function.Function;

public final class Parser {

    private static final List<Reduction> reductionList;

    static {
        Function<List<Symbol>, TreeSymbol> f1 = ls -> Term.build(ls.get(ls.size()-1));
        Function<List<Symbol>, TreeSymbol> f2 = ls -> Term.build(ls.get(ls.size()-2));
        Function<List<Symbol>, TreeSymbol> f3 = ls -> Expression.build(false,ls.get(ls.size()-1));
        Function<List<Symbol>, TreeSymbol> f4 = ls -> Expression.build(true, ls.get(ls.size()-1));
        Function<List<Symbol>, TreeSymbol> f5 = ls -> Expression.build(true,  ls.get(ls.size()-3), ls.get(ls.size()-1));
        Function<List<Symbol>, TreeSymbol> f6 = ls -> Expression.build(false, ls.get(ls.size()-3), ls.get(ls.size()-1));

        List<Type> pattern1 = new ArrayList<Type>(){{
            add(Type.VARIABLE);
        }};
        List<Type> pattern2 = new ArrayList<Type>(){{
            add(Type.OPEN);add(Type.EXPRESSION);add(Type.CLOSE);
        }};
        List<Type> pattern3 = new ArrayList<Type>(){{
            add(Type.NOT);add(Type.TERM);
        }};
        List<Type> pattern4 = new ArrayList<Type>(){{
            add(Type.TERM);
        }};
        List<Type> pattern5 = new ArrayList<Type>(){{
            add(Type.EXPRESSION);add(Type.AND);add(Type.EXPRESSION);
        }};
        List<Type> pattern6 = new ArrayList<Type>(){{
            add(Type.EXPRESSION);add(Type.OR);add(Type.EXPRESSION);
        }};

        List<Reduction> reductionsList = new ArrayList<Reduction>(){{
                add(Reduction.build(pattern1, f1));
                add(Reduction.build(pattern2, f2));
                add(Reduction.build(pattern3, f3));
                add(Reduction.build(pattern4, f4));
                add(Reduction.build(pattern5, f5));
                add(Reduction.build(pattern6, f6));
            }};
        reductionList = Collections.unmodifiableList(reductionsList);
    }

    public static final State parse(BooleanList input){
        List<Symbol> workingList = new ArrayList<>();

        for(ListSymbol symbol : input){
            workingList.add(symbol);
            if(noNeedParse(symbol.getType())) break;
            for(Reduction reduction : reductionList){
                if(match(reduction, workingList)){
                    reduction(reduction, workingList);
                }
            }
        }

        return State.build(workingList);
    }

    private static boolean match(Reduction reduction, List<Symbol> workingList){
        int size = reduction.size();
        int len = workingList.size();
        if(len < size) return false;
        List<Type> typeList = new ArrayList<>();
        for(int i = len-size; i < len; i++){
            typeList.add(workingList.get(i).getType());
        }
        return reduction.matches(typeList);
    }

    private static void reduction(Reduction reduction, List<Symbol> workingList){
        int size = reduction.size();
        int len = workingList.size();
        workingList.add(reduction.apply(workingList));
        workingList.subList(len-size,len).clear();
    }


    private static boolean noNeedParse(Type type){
        return type == Type.OR || type == Type.AND || type == Type.OPEN || type == Type.NOT;
    }
}
