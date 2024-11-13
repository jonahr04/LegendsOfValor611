// Jonah Rothman
// Paladin.java
// This class defines the Paladin hero type, with unique stats and abilities specific to Paladins.

import java.util.Random;

public class Paladin extends Hero {
    // Paladin stats array
    private static final int[][] PALADIN_STATS = {
            {300, 750, 650, 700, 2500, 7},
            {300, 750, 700, 700, 2500, 7},
            {250, 650, 600, 350, 2500, 4},
            {100, 600, 500, 400, 2500, 5},
            {500, 500, 500, 500, 2500, 5},
            {400, 400, 400, 400, 2500, 8}
    };

    // Public constructor that assigns random stats
    public Paladin(String name) {
        this(name, getRandomStats());
    }

    // Private helper constructor that uses a pre-generated random stats array
    private Paladin(String name, int[] stats) {
        super(name, 100, stats[0], stats[1], stats[2], stats[3], stats[4], stats[5]);
    }

    // Static method to get a single, consistent random set of stats
    private static int[] getRandomStats() {
        Random rand = new Random();
        return PALADIN_STATS[rand.nextInt(PALADIN_STATS.length)];
    }


    public String getType(){
        return "Paladin";
    }
}
