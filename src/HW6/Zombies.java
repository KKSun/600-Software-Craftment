package HW6;

import java.math.BigInteger;
import java.util.*;

public class Zombies {

    private static final boolean LEFT = true;

    private static NavigableMap<BigInteger, PriorityQueue<Zombie>> zombies_map = new TreeMap<>();

    private Comparator<Zombie> largerYFirst = (o1, o2) -> o2.getY_position().compareTo(o1.getY_position());


    private void checkInLine(PriorityQueue<Zombie> zombieInLine, BigInteger x) {
        for (Zombie z : zombieInLine) {
            if(z.getY_position().equals(x)) throw new IllegalArgumentException("zombie already there");
        }
    }

    public void insert(Zombie z, BigInteger x, BigInteger y) {
        z.setX_position(x);
        z.setY_position(y);

        if (!zombies_map.containsKey(z.getX_position())) {
            zombies_map.put(z.getX_position(), new PriorityQueue<>(largerYFirst));
        }

        checkInLine(zombies_map.get(x), z.getY_position());

        zombies_map.get(z.getX_position()).add(z);
    }


    private Zombie getZombieInLine(PriorityQueue<Zombie> zombieInLine, BigInteger x) {
        for (Zombie z : zombieInLine) {
            if (z.getY_position().equals(x)) {
                return z;
            }
        }
        return null;
    }

    public Zombie zombie(BigInteger x, BigInteger y) {
        PriorityQueue<Zombie> zombiesInLine = zombies_map.getOrDefault(x, null);

        assert !zombiesInLine.isEmpty():"no zombie in this line";

        if (zombiesInLine == null) {
            return null;
        }

        return getZombieInLine(zombiesInLine, y);
    }


    public Zombie delete(BigInteger x, BigInteger y) {
        Zombie tempZ = Objects.requireNonNull(zombie(x, y), "No zombie there.");

        PriorityQueue<Zombie> zombiesInLine = zombies_map.getOrDefault(x, null);

        zombiesInLine.remove(tempZ);

        if (zombiesInLine.isEmpty()) {
            zombies_map.remove(x);
        }

        return tempZ;
    }


    public BigInteger[] javelin(BigInteger xp) {
        if (zombies_map.isEmpty()) return null;

        BigInteger floorX = zombies_map.floorKey(xp);
        BigInteger ceilingX = zombies_map.ceilingKey(xp);
        BigInteger x = floorX.subtract(xp).abs().compareTo(ceilingX.subtract(xp).abs()) > 0 ? ceilingX : floorX;

        Zombie z = Objects.requireNonNull(zombies_map.get(x).peek(), "what? a null zombie?");

        return new BigInteger[]{z.getX_position(), z.getY_position()};
    }

    public BigInteger[] arrow(boolean direction) {
        if (zombies_map.isEmpty()) return null;

        Zombie z;

        if (direction == LEFT) {
            z = Objects.requireNonNull(zombies_map.get(zombies_map.firstKey()).peek(), "what? a null zombie?");
        } else {
            z = Objects.requireNonNull(zombies_map.get(zombies_map.lastKey()).peek(), "what? a null zombie?");
        }

        return new BigInteger[]{z.getX_position(), z.getY_position()};
    }

    public BigInteger[] bomb(BigInteger xp, BigInteger r) {
        if (zombies_map.isEmpty()) return null;

        NavigableMap<BigInteger, PriorityQueue<Zombie>> subMap = zombies_map.subMap(xp.subtract(r),true, xp.add(r), true);

        if (subMap.isEmpty()) return null;

        Zombie z = subMap.firstEntry().getValue().peek();

        for (BigInteger key : subMap.keySet()) {
            assert z != null;
            if (Objects.requireNonNull(subMap.get(key).peek(), "what? a null zombie?").getY_position().compareTo(z.getY_position()) > 0) {
                z = subMap.get(key).peek();
            }
        }

        assert z != null;
        return new BigInteger[]{z.getX_position(), z.getY_position()};
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<BigInteger, PriorityQueue<Zombie>> entry : zombies_map.entrySet()) {
            sb.append("Key: ").append(entry.getKey()).append("  \tValue: ").append(entry.getValue().toString()).append("\n");
        }

        return sb.toString();
    }

    public void clearAll() {
        zombies_map.clear();
    }
}
