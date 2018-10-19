package hw6_side;

import HW6.Zombie;
import HW6.Zombies;

import java.math.BigInteger;
import java.util.Arrays;

public class Game {
    public static void main(String[] args){
        System.out.println("--------- Testing ... ---------");

        Zombie z1 = Zombie.build(BigInteger.valueOf(1),BigInteger.valueOf(2));
        System.out.println(z1.getX_position());
        System.out.println(z1.getY_position());
        System.out.println(z1.hashCode());
        Zombie z2 = Zombie.build(BigInteger.valueOf(1),BigInteger.valueOf(2));
        System.out.println(z2.hashCode());

        Human h1 = Human.build(BigInteger.valueOf(8));
        System.out.println(h1.getX_position());
        System.out.println(h1.toString());

        System.out.println("\n--------- They are coming ... ---------");
        Zombies zombies = new Zombies();

        zombies.insert(Zombie.build(), BigInteger.valueOf(1), BigInteger.valueOf(1));
        zombies.insert(Zombie.build(), BigInteger.valueOf(2), BigInteger.valueOf(2));
        zombies.insert(Zombie.build(), BigInteger.valueOf(4), BigInteger.valueOf(1));
        zombies.insert(Zombie.build(), BigInteger.valueOf(4), BigInteger.valueOf(10));
        zombies.insert(Zombie.build(), BigInteger.valueOf(4), BigInteger.valueOf(80));
        zombies.insert(Zombie.build(), BigInteger.valueOf(4), BigInteger.valueOf(3));
        zombies.insert(Zombie.build(), BigInteger.valueOf(5), BigInteger.valueOf(6));
//        zombies.insert(Zombie.build(),BigInteger.valueOf(5), BigInteger.valueOf(6));
        zombies.insert(Zombie.build(), BigInteger.valueOf(5), BigInteger.valueOf(22));
        zombies.insert(Zombie.build(), BigInteger.valueOf(6), BigInteger.valueOf(12));
        zombies.insert(Zombie.build(), BigInteger.valueOf(6), BigInteger.valueOf(8));
        zombies.insert(Zombie.build(), BigInteger.valueOf(7), BigInteger.valueOf(28));
        zombies.insert(Zombie.build(), BigInteger.valueOf(10), BigInteger.valueOf(2));

        zombies.insert(zombies.delete(BigInteger.valueOf(4), BigInteger.valueOf(10)), BigInteger.valueOf(10), BigInteger.valueOf(12));

        System.out.println(zombies);

        zombies.delete(BigInteger.valueOf(5),BigInteger.valueOf(6));

        System.out.println(zombies);

        zombies.insert(zombies.delete(BigInteger.valueOf(6), BigInteger.valueOf(12)), BigInteger.valueOf(6),BigInteger.valueOf(4));

        System.out.println(zombies);

        System.out.println(Arrays.toString(zombies.arrow(true)));
        System.out.println(Arrays.toString(zombies.arrow(false)));
        System.out.println(Arrays.toString(zombies.javelin(BigInteger.valueOf(4))));
        System.out.println(Arrays.toString(zombies.bomb(BigInteger.valueOf(5),BigInteger.valueOf(5))));
    }
}
