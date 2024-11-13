// Jonah Rothman
// Sorcerer.java
// This class defines the Sorcerer hero type, with specific magical abilities and stat advantages.

import java.util.Random;

public class Sorcerer extends Hero {
    // Sorcerer stats array
    private static final int[][] SORCERER_STATS = {
            {1300, 750, 450, 500, 2500, 9},
            {900, 800, 500, 650, 2500, 5},
            {800, 800, 800, 800, 2500, 8},
            {900, 800, 700, 400, 2500, 7},
            {800, 850, 400, 600, 2500, 6},
            {1000, 700, 400, 500, 2500, 5}
    };

    // Public constructor that assigns random stats
    public Sorcerer(String name) {
        this(name, getRandomStats());
    }

    // Private helper constructor that uses a pre-generated random stats array
    private Sorcerer(String name, int[] stats) {
        super(name, 100, stats[0], stats[1], stats[2], stats[3], stats[4], stats[5]);
    }

    // Static method to get a single, consistent random set of stats
    private static int[] getRandomStats() {
        Random rand = new Random();
        return SORCERER_STATS[rand.nextInt(SORCERER_STATS.length)];
    }


    public String getType(){
        return "Sorcerer";
    }
}