package HW6;

import java.math.BigInteger;

public class Zombie {

    private BigInteger x_position = BigInteger.valueOf(0);
    private BigInteger y_position = BigInteger.valueOf(0);

    private Zombie() {}

    public static Zombie build() {
        return new Zombie();
    }

    private Zombie(BigInteger x, BigInteger y) {
        this.x_position = x;
        this.y_position = y;
    }

    public static Zombie build(BigInteger x, BigInteger y) {
        return new Zombie(x, y);
    }

    public BigInteger getX_position() {
        return x_position;
    }

    public void setX_position(BigInteger x_position) {
        this.x_position = x_position;
    }

    public BigInteger getY_position() {
        return y_position;
    }

    public void setY_position(BigInteger y_position) {
        this.y_position = y_position;
    }

    public String toString() {
        return "[" + getX_position() + "," + getY_position() + "]";
    }

    public BigInteger[] getLocation(){
        return new BigInteger[]{this.getX_position(), this.getY_position()};
    }
}
