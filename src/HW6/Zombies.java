package HW6;

import java.math.BigInteger;
import java.util.*;

public class Zombies {

    private static final boolean LEFT = true;

    private static NavigableMap<BigInteger, PriorityQueue<Zombie>> zombies_map = new TreeMap<>();

    private void checkInLine(PriorityQueue<Zombie> zombieInLine, BigInteger x) {
        for (Zombie z : zombieInLine) {
            if(z.getY_position().equals(x)) throw new IllegalArgumentException("zombie already there");
        }
    }

    public void insert(Zombie z, BigInteger x, BigInteger y) {
        z.setX_position(x);
        z.setY_position(y);

        if (!zombies_map.containsKey(z.getX_position())) {
            zombies_map.put(z.getX_position(), new PriorityQueue<>((o1, o2) -> o2.getY_position().compareTo(o1.getY_position())));
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
        PriorityQueue<Zombie> zombiesInLine = zombies_map.get(x);

        assert !zombiesInLine.isEmpty():"no zombie in this line";

        if (zombiesInLine == null) {
            return null;
        }

        return getZombieInLine(zombiesInLine, y);
    }


    public Zombie delete(BigInteger x, BigInteger y) {
        Zombie tempZ = Objects.requireNonNull(zombie(x, y), "No zombie there.");

        PriorityQueue<Zombie> zombiesInLine = zombies_map.get(x);

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

        return z.getLocation();
    }


    public BigInteger[] arrow(boolean direction) {
        if (zombies_map.isEmpty()) return null;

        Zombie z;
        BigInteger key;

        if(direction == LEFT){
            key = zombies_map.firstKey();
        }else{
            key = zombies_map.lastKey();
        }

        z = Objects.requireNonNull(zombies_map.get(key).peek(), "what? a null zombie?");

        return z.getLocation();
    }


    private Zombie getMaxYInSubmap(NavigableMap<BigInteger, PriorityQueue<Zombie>> subMap){
        Zombie z = subMap.firstEntry().getValue().peek();

        for (BigInteger key : subMap.keySet()) {
            assert z != null;
            if (Objects.requireNonNull(subMap.get(key).peek(), "what? a null zombie?").getY_position().compareTo(z.getY_position()) > 0) {
                z = subMap.get(key).peek();
            }
        }

        return z;
    }


    public BigInteger[] bomb(BigInteger xp, BigInteger r) {
        if (zombies_map.isEmpty()) return null;

        NavigableMap<BigInteger, PriorityQueue<Zombie>> subMap = zombies_map.subMap(xp.subtract(r),true, xp.add(r), true);

        if (subMap.isEmpty()) return null;

        Zombie z = getMaxYInSubmap(subMap);

        return z.getLocation();
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<BigInteger, PriorityQueue<Zombie>> entry : zombies_map.entrySet()) {
            sb.append("Key: ").append(entry.getKey()).append("  \tValue: ").append(entry.getValue().toString()).append("\n");
        }

        return sb.toString();
    }

    public void clearAllZombies() {
        zombies_map.clear();
    }
}
