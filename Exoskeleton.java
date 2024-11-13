// Jonah Rothman
// Exoskeleton.java
// Represents an Exoskeleton monster with unique attributes like enhanced defense and dodge abilities.

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Exoskeleton extends Monster {

    private static final Map<String, int[]> exoskeletonData = new HashMap<>();

    static {
        exoskeletonData.put("Cyrrollalee", new int[]{7, 700, 800, 75});
        exoskeletonData.put("Brandobaris", new int[]{3, 350, 450, 30});
        exoskeletonData.put("BigBad-Wolf", new int[]{1, 150, 250, 15});
        exoskeletonData.put("WickedWitch", new int[]{2, 250, 350, 25});
        exoskeletonData.put("Aasterinian", new int[]{4, 400, 500, 45});
        exoskeletonData.put("Chronepsish", new int[]{6, 650, 750, 60});
        exoskeletonData.put("Kiaransalee", new int[]{8, 850, 950, 85});
        exoskeletonData.put("St-Shargaas", new int[]{5, 550, 650, 55});
        exoskeletonData.put("Merrshaullk", new int[]{10, 1000, 900, 55});
        exoskeletonData.put("St-Yeenoghu", new int[]{9, 950, 850, 90});
        exoskeletonData.put("DocOck", new int[]{6, 600, 600, 55});
        exoskeletonData.put("Exodia", new int[]{10, 1000, 1000, 50});
    }

    // Public constructor that scales the dragon stats to the specified level
    public Exoskeleton(int level) {
        this(selectRandomName(), level);
    }

    // Private constructor to take in the selected name and level, and scale attributes
    private Exoskeleton(String name, int level) {
        super(name, level * 100, level, exoskeletonData.get(name)[0],exoskeletonData.get(name)[1], exoskeletonData.get(name)[2], exoskeletonData.get(name)[3]);
    }

    // Selects a random dragon name from the dragonData map
    private static String selectRandomName() {
        String[] dragonNames = exoskeletonData.keySet().toArray(new String[0]);
        return dragonNames[new Random().nextInt(dragonNames.length)];
    }


    @Override
    public String getName() { return (super.getRawName() + " The Exoskeleton"); }

}

