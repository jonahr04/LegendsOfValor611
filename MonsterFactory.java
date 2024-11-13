// Jonah Rothman
// MonsterFactory.java
// This factory class generates monsters of various types, randomly selecting monsters for gameplay encounters.

import java.util.Random;

public class MonsterFactory {

    // Arrays of possible monster types and names
    private static final String[] monsterTypes = {"Dragon", "Exoskeleton", "Spirit"};

    // Factory method to create a monster with a random name and type
    public static Monster getMonster(String monsterType,int level) {


        // Create the monster based on the specified monster type
        switch (monsterType.toLowerCase()) {
            case "dragon":
                return new Dragon(level);
            case "exoskeleton":
                return new Exoskeleton(level);
            case "spirit":
                return new Spirit(level);
        }

        return null;
    }

    // Method to generate a completely random monster (random type and name)
    public static Monster getRandomMonster(int level) {
        Random rand = new Random();

        // Randomly select a monster type
        String monsterType = monsterTypes[rand.nextInt(monsterTypes.length)];

        // Use getMonster to create a monster with a random name and type
        return getMonster(monsterType,level);
    }
}
