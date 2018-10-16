package hw6_side;

import HW6.Zombie;
import HW6.Zombies;

import java.util.Arrays;

public class Game {
    public static void main(String[] args){
        System.out.println("--------- They are coming ... ---------");

        Zombie z1 = Zombie.build(1,2);
        System.out.println(z1.getX_position());
        System.out.println(z1.getY_position());
        System.out.println(z1.hashCode());
        Zombie z2 = Zombie.build(1,2);
        System.out.println(z2.hashCode());

        Human h1 = Human.build(6);
        System.out.println(h1.getX_position());

        Zombies zombies = new Zombies();

        zombies.insert(Zombie.build(), 1, 1);
        zombies.insert(Zombie.build(), 2, 2);
        zombies.insert(Zombie.build(2,5), 4, 1);
        zombies.insert(Zombie.build(), 4, 10);
        zombies.insert(Zombie.build(), 4, 80);
        zombies.insert(Zombie.build(), 4, 3);
        zombies.insert(Zombie.build(), 5, 6);
//        zombies.insert(Zombie.build(), 5, 6);
        zombies.insert(Zombie.build(), 5, 22);
        zombies.insert(Zombie.build(), 6, 12);
        zombies.insert(Zombie.build(), 6, 8);
        zombies.insert(Zombie.build(), 7, 28);
        zombies.insert(Zombie.build(), 10, 2);

        zombies.insert(zombies.delete(4,10), 10, 12);

        System.out.println(zombies);

        zombies.delete(5,6);

        System.out.println(zombies);

        zombies.insert(zombies.delete(6, 12), 6,4);

        System.out.println(zombies);

        System.out.println(Arrays.toString(zombies.arrow(true)));
        System.out.println(Arrays.toString(zombies.arrow(false)));
        System.out.println(Arrays.toString(zombies.javelin(4)));
        System.out.println(Arrays.toString(zombies.bomb(5,5)));
    }
}
