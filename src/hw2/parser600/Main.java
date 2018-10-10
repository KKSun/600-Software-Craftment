package hw2.parser600;

public class Main {
    public static void main(String args[]){
        BooleanList ll = new BooleanList();
        Variable a = Variable.build("a");
        Variable b = Variable.build("b");
        Variable c = Variable.build("c");
        ll.add(Type.OPEN);
        ll.add(Type.OPEN);
        ll.add(Type.OPEN);
        ll.add(a);
        ll.add(Type.OR);
        ll.add(b);
        ll.add(Type.CLOSE);
        ll.add(Type.AND);
        ll.add(Type.OPEN);
        ll.add(Type.OPEN);
        ll.add(Type.OPEN);
        ll.add(Type.NOT);
        ll.add(b);
        ll.add(Type.OR);
        ll.add(c);
        ll.add(Type.CLOSE);
        ll.add(Type.CLOSE);
        ll.add(Type.CLOSE);
        ll.add(Type.CLOSE);
        ll.add(Type.CLOSE);
        System.out.println(ll.toString());
        System.out.println("--------------------");
        State sss = Parser.parse(ll);
        System.out.println(sss.getExpression().getStructure());
        System.out.println(sss.getExpression().getType());
        System.out.println(sss.isCorrect());
        System.out.println("--------------------");
        System.out.println(sss.getExpression().simplified());
    }
}
