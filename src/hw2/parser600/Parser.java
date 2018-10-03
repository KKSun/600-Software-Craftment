package hw2.parser600;

import java.util.*;
import java.util.function.Function;

public final class Parser {

    private static final List<Reduction> reductionList;

    static {
        Function<List<Symbol>, TreeSymbol> fvarToTerm = ls -> Term.build(ls.get(ls.size()-1));
        Function<List<Symbol>, TreeSymbol> fbExToTerm = ls -> Term.build(ls.get(ls.size()-2));
        Function<List<Symbol>, TreeSymbol> fnTermToEx = ls -> Expression.build(false,ls.get(ls.size()-1));
        Function<List<Symbol>, TreeSymbol> ftTermToEx = ls -> Expression.build(true, ls.get(ls.size()-1));
        Function<List<Symbol>, TreeSymbol> fndExpToEx = ls -> Expression.build(true,  ls.get(ls.size()-3), ls.get(ls.size()-1));
        Function<List<Symbol>, TreeSymbol> forExpToEx = ls -> Expression.build(false, ls.get(ls.size()-3), ls.get(ls.size()-1));

        List<Type> pVarToTerm = new ArrayList<Type>(){{ add(Type.VARIABLE); }};
        List<Type> pBExToTerm = new ArrayList<Type>(){{ add(Type.OPEN);add(Type.EXPRESSION);add(Type.CLOSE); }};
        List<Type> pNTermToEx = new ArrayList<Type>(){{ add(Type.NOT);add(Type.TERM); }};
        List<Type> pTTermToEx = new ArrayList<Type>(){{ add(Type.TERM); }};
        List<Type> pNdExpToEx = new ArrayList<Type>(){{ add(Type.EXPRESSION);add(Type.AND);add(Type.EXPRESSION); }};
        List<Type> pOrExpToEx = new ArrayList<Type>(){{ add(Type.EXPRESSION);add(Type.OR);add(Type.EXPRESSION); }};

        List<Reduction> reductionsList = new ArrayList<Reduction>(){{
                add(Reduction.build(pVarToTerm, fvarToTerm));
                add(Reduction.build(pBExToTerm, fbExToTerm));
                add(Reduction.build(pNTermToEx, fnTermToEx));
                add(Reduction.build(pTTermToEx, ftTermToEx));
                add(Reduction.build(pNdExpToEx, fndExpToEx));
                add(Reduction.build(pOrExpToEx, forExpToEx));
        }};
        reductionList = Collections.unmodifiableList(reductionsList);
    }

    public static final State parse(BooleanList input){
        List<Symbol> workingList = new ArrayList<>();

        for(ListSymbol symbol : input){
            workingList.add(symbol);
            for(Reduction reduction : reductionList){
                matchAndApply(reduction, workingList);
            }
        }

        return State.build(workingList);
    }

    private static void matchAndApply(Reduction reduction, List<Symbol> workingList){
        int size = reduction.size();
        int len = workingList.size();
        if(len < size) return;
        List<Type> typeList = new ArrayList<>();
        for(Symbol symbol : workingList.subList(len-size,len)){
            typeList.add(symbol.getType());
        }
        if(reduction.matches(typeList)){
            workingList.add(reduction.apply(workingList));
            workingList.subList(len-size,len).clear();
        }
    }

}
