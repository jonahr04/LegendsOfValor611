// Jonah Rothman
// Spirit.java
// This class represents a Spirit monster with unique attributes, including level, dodge chance, and abilities in battle.

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Spirit extends Monster {

    private static final Map<String, int[]> spiritData = new HashMap<>();
    static {
        spiritData.put("Andrealphus", new int[] {2, 600, 500, 40});
        spiritData.put("Blinky", new int[] {1, 450, 350, 35});
        spiritData.put("Andromalius", new int[] {3, 550, 450, 25});
        spiritData.put("Chiang-shih", new int[] {4, 700, 600, 40});
        spiritData.put("FallenAngel", new int[] {5, 800, 700, 50});
        spiritData.put("Ereshkigall", new int[] {6, 950, 450, 35});
        spiritData.put("Melchiresas", new int[] {7, 350, 150, 75});
        spiritData.put("Jormunngand", new int[] {8, 600, 900, 20});
        spiritData.put("Rakkshasass", new int[] {9, 550, 600, 35});
        spiritData.put("Taltecuhtli", new int[] {10, 300, 200, 50});
        spiritData.put("Casper", new int[] {1, 100, 100, 50});
    }

    // Public constructor that scales the dragon stats to the specified level
    public Spirit(int level) {
        this(selectRandomName(), level);
    }

    // Private constructor to take in the selected name and level, and scale attributes
    private Spirit(String name, int level) {
        super(name, level * 100, level, spiritData.get(name)[0],spiritData.get(name)[1], spiritData.get(name)[2], spiritData.get(name)[3]);
    }

    // Selects a random dragon name from the dragonData map
    private static String selectRandomName() {
        String[] dragonNames = spiritData.keySet().toArray(new String[0]);
        return dragonNames[new Random().nextInt(dragonNames.length)];
    }


    @Override
    public String getName() { return (super.getRawName() + " The Spirit"); }
}
