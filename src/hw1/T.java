package hw1;

public class T<E> {
    private E key;
    public T(E key){
        this.key = key;
    }

    public E getKey(){
        return key;
    }
}
