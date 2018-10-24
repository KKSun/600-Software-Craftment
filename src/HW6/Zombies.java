package HW6;

import java.math.BigInteger;
import java.util.*;

public class Zombies {

    private static final boolean LEFT = true;

    private static NavigableMap<BigInteger, PriorityQueue<Zombie>> zombies_map = new TreeMap<>();

    private void checkInLine(PriorityQueue<Zombie> zombieInLine, BigInteger x) {
        if(zombieInLine == null) return;

        for (Zombie z : zombieInLine) {
            if(z.getY_position().equals(x)) throw new IllegalArgumentException("zombie already there");
        }
    }

    public void insert(Zombie z, BigInteger x, BigInteger y) {
        checkInLine(zombies_map.get(x), y);

        z.setX_position(x);
        z.setY_position(y);

        if (!zombies_map.containsKey(x)) {
            zombies_map.put(x, new PriorityQueue<>((o1, o2) -> o2.getY_position().compareTo(o1.getY_position())));
        }

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

        if (zombiesInLine == null) {
            return null;
        }

        assert !zombiesInLine.isEmpty():"no zombie in this line";

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


    private BigInteger checkInBound(BigInteger xp){
        if(xp.compareTo(zombies_map.firstKey()) < 0){
            return zombies_map.firstKey();
        }

        if(xp.compareTo(zombies_map.lastKey()) > 0){
            return zombies_map.lastKey();
        }

        BigInteger floorX = zombies_map.floorKey(xp);
        BigInteger ceilingX = zombies_map.ceilingKey(xp);
        return floorX.subtract(xp).abs().compareTo(ceilingX.subtract(xp).abs()) > 0 ? ceilingX : floorX;
    }

    public BigInteger[] javelin(BigInteger xp) {
        if (zombies_map.isEmpty()) return null;

        BigInteger x = checkInBound(xp);

        Zombie z = Objects.requireNonNull(zombies_map.get(x).peek(), "what? a null zombie?");

        return z.getLocation();
    }


    public BigInteger[] arrow(boolean direction) {
        if (zombies_map.isEmpty()) return null;

        BigInteger key = direction == LEFT ? zombies_map.firstKey(): zombies_map.lastKey();

        Zombie z = Objects.requireNonNull(zombies_map.get(key).peek(), "what? a null zombie?");

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

    class TestHook{
        boolean direction = LEFT;

        NavigableMap <BigInteger, PriorityQueue<Zombie>> map = zombies_map;

        void checkInLine(PriorityQueue<Zombie> zombieInLine, BigInteger x) {
            Zombies.this.checkInLine(zombieInLine,x);
        }

        Zombie getZombieInLine(PriorityQueue<Zombie> zombieInLine, BigInteger x) {
            return Zombies.this.getZombieInLine(zombieInLine,x);
        }

        Zombie getMaxYInSubmap(NavigableMap<BigInteger, PriorityQueue<Zombie>> subMap){
            return Zombies.this.getMaxYInSubmap(subMap);
        }

        BigInteger checkInBound(BigInteger xp){
            return Zombies.this.checkInBound(xp);
        }
    }
}
