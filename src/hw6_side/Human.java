package hw6_side;

import java.math.BigInteger;

public class Human {

    private BigInteger x_position;

    private Human(BigInteger x){
        this.x_position = x;
    }

    public static Human build(BigInteger x){
        return new Human(x);
    }

    public BigInteger getX_position() {
        return x_position;
    }

    public void setX_position(BigInteger x_position) {
        this.x_position = x_position;
    }

    @Override
    public String toString() {
        return "Human at top with x: " + getX_position();
    }
}
