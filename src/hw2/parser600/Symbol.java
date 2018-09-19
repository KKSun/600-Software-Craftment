package hw2.parser600;

public interface Symbol {
    Type getType();
    BooleanList toList();
    long complexity();
}
