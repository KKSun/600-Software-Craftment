package HW6;

import java.util.*;

public class Zombies {

    private static final boolean LEFT = true;

    private static TreeMap<Integer, PriorityQueue<Zombie>> zombies_list = new TreeMap<>();

    private Comparator<Zombie> largerYFirst = (o1, o2) -> o2.getY_position() - o1.getY_position();


    private void checkInLine(PriorityQueue<Zombie> zombieInLine, int y) {
        for (Zombie z : zombieInLine) {
            //how to avoid this throw?
            if (z.getY_position() == y) throw new IllegalArgumentException("zombie already exist");
        }
    }

    public void insert(Zombie z, int x, int y) {
        //build a new or set position?
        z.setX_position(x);
        z.setY_position(y);

        if (!zombies_list.containsKey(z.getX_position())) {
            zombies_list.put(z.getX_position(), new PriorityQueue<>(largerYFirst));
        }

        checkInLine(zombies_list.get(x), z.getY_position());

        zombies_list.get(z.getX_position()).add(z);
    }


    private Zombie getZombieInLine(PriorityQueue<Zombie> zombieInLine, int y) {
        for (Zombie z : zombieInLine) {
            if (z.getY_position() == y) {
                return z;
            }
        }
        return null;
    }

    public Zombie zombie(int x, int y) {
        PriorityQueue<Zombie> zombiesInLine = zombies_list.getOrDefault(x, null);

        if (zombiesInLine == null || zombiesInLine.isEmpty()) {
            return null;
        }

        return getZombieInLine(zombiesInLine, y);
    }


    public Zombie delete(int x, int y) {
        Zombie tempZ = Objects.requireNonNull(zombie(x, y), "No zombie there.");

        PriorityQueue<Zombie> zombiesInLine = zombies_list.getOrDefault(x, null);

        zombiesInLine.remove(tempZ);

        if (zombiesInLine.isEmpty()) {
            zombies_list.remove(x);
        }

        return tempZ;
    }


    public int[] javelin(int xp) {
        if (zombies_list.isEmpty()) return null;

        int floorX = zombies_list.floorKey(xp);
        int ceilingX = zombies_list.ceilingKey(xp);
        int x = Math.abs(floorX - xp) > Math.abs(ceilingX - xp) ? ceilingX : floorX;

        Zombie z = Objects.requireNonNull(zombies_list.get(x).peek(), "what? a null zombie?");

        return new int[]{z.getX_position(), z.getY_position()};
    }

    public int[] arrow(boolean direction) {
        if (zombies_list.isEmpty()) return null;

        Zombie z;

        if (direction == LEFT) {
            z = Objects.requireNonNull(zombies_list.get(zombies_list.firstKey()).peek(), "what? a null zombie?");
        } else {
            z = Objects.requireNonNull(zombies_list.get(zombies_list.lastKey()).peek(), "what? a null zombie?");
        }

        return new int[]{z.getX_position(), z.getY_position()};
    }

    public int[] bomb(int xp, int r) {
        if (zombies_list.isEmpty()) return null;

        Optional<Integer> left_x = Optional.ofNullable(zombies_list.ceilingKey(xp - r)).isPresent() ?
                Optional.ofNullable(zombies_list.ceilingKey(xp - r)) :
                Optional.ofNullable(zombies_list.firstKey());

        //how to avoid this +1 ?
        Optional<Integer> right_x = Optional.ofNullable(zombies_list.ceilingKey(xp + r + 1));

        SortedMap<Integer, PriorityQueue<Zombie>> subMap =
                right_x.map(integer -> zombies_list.subMap(left_x.get(), integer)).orElseGet(() -> zombies_list.tailMap(left_x.get()));

        if (subMap.isEmpty()) return null;

        Zombie z = Objects.requireNonNull(subMap.get(left_x.get()).peek(), "what? a null zombie?");

        for (Integer key : subMap.keySet()) {
            if (Objects.requireNonNull(subMap.get(key).peek(), "what? a null zombie?").getY_position() > z.getY_position()) {
                z = Objects.requireNonNull(subMap.get(key).peek(), "what? a null zombie?");
            }
        }

        return new int[]{z.getX_position(), z.getY_position()};
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Integer, PriorityQueue<Zombie>> entry : zombies_list.entrySet()) {
            sb.append("Key: ").append(entry.getKey()).append("  \tValue: ").append(entry.getValue().toString()).append("\n");
        }

        return sb.toString();
    }

    void clearAll() {
        zombies_list.clear();
    }
}
