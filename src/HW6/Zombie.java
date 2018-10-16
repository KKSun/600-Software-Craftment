package HW6;

public class Zombie {

    private int x_position;
    private int y_position;

    private Zombie() {
    }

    private Zombie(int x, int y) {
        this.x_position = x;
        this.y_position = y;
    }

    public static Zombie build() {
        return new Zombie();
    }

    public static Zombie build(int x, int y) {
        return new Zombie(x, y);
    }

    public int getX_position() {
        return x_position;
    }

    public void setX_position(int x_position) {
        this.x_position = x_position;
    }

    public int getY_position() {
        return y_position;
    }

    public void setY_position(int y_position) {
        this.y_position = y_position;
    }

    public String toString() {
        return "[" + this.getX_position() + "," + this.getY_position() + "]";
    }
}
