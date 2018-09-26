package hw2.parser600;

import java.util.ArrayList;
import java.util.List;

public final class State {
    private final boolean correct;
    private final List<Symbol> workingList;

    private State(boolean correct, List<Symbol> workingList){
        this.correct = correct;
        this.workingList = workingList;
    }

    static final State build(List<Symbol> workingList){
        boolean correct = workingList.size() == 1 && workingList.get(0).getType() == Type.EXPRESSION;
        return new State(correct, workingList);
    }

    public final boolean isCorrect(){
        return this.correct;
    }

    public final List<Symbol> getWorkingList() {
        return new ArrayList<>(workingList);
    }

    public final Expression getExpression() throws IllegalAccessException {
        if(correct){
            return (Expression) workingList.get(0);
        }else{
            throw new IllegalAccessException("cannot access expression since parsing not done");
        }
    }

    public String toString(){
        try {
            return this.getExpression().toString();
        }catch (IllegalAccessException e){
            return null;
        }
    }
}
