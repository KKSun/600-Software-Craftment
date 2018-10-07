package HW6;

public class Human {

    private int x_position;

    private Human(int x){
        this.x_position = x;
    }

    public static Human build(int x){
        return new Human(x);
    }

    public int getX_position() {
        return x_position;
    }

    public void setX_position(int x_position) {
        this.x_position = x_position;
    }

}
