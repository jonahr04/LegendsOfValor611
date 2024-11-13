// Jonah Rothman
// Weapon.java
// This class represents a weapon with attributes like damage, hands required, and level, equippable by heroes for combat use.

import java.util.Random;

public class Warrior extends Hero {
    // Warrior stats array
    private static final int[][] WARRIOR_STATS = {
            {100, 700, 500, 600, 1354, 7},
            {600, 700, 800, 500, 2500, 8},
            {300, 900, 500, 750, 2546, 6},
            {200, 750, 650, 700, 2500, 7},
            {400, 800, 400, 700, 2500, 7},
            {400, 700, 800, 600, 2500, 6}
    };

    // Public constructor that assigns random stats
    public Warrior(String name) {
        this(name, getRandomStats());
    }

    // Private helper constructor that uses a pre-generated random stats array
    private Warrior(String name, int[] stats) {
        super(name, 100, stats[0], stats[1], stats[2], stats[3], stats[4], stats[5]);
    }

    // Static method to get a single, consistent random set of stats
    private static int[] getRandomStats() {
        Random rand = new Random();
        return WARRIOR_STATS[rand.nextInt(WARRIOR_STATS.length)];
    }


    public String getType(){
        return "Warrior";
    }
}
