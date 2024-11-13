//Jonah Rothman
//Dragon.Java
//This is class for dragons which are a type of monster


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Dragon extends Monster {
    // Map to hold dragon attributes based on the dragon name
    private static final Map<String, int[]> dragonData = new HashMap<>();
    static {
        dragonData.put("Desghidorrah", new int[] {3, 300, 400, 35});
        dragonData.put("Chrysophylax", new int[] {2, 200, 500, 20});
        dragonData.put("BunsenBurner", new int[] {4, 400, 500, 45});
        dragonData.put("Natsunomeryu", new int[] {1, 100, 200, 10});
        dragonData.put("TheScaleless", new int[] {7, 700, 600, 75});
        dragonData.put("Kas-Ethelinh", new int[] {5, 600, 500, 60});
        dragonData.put("Alexstraszan", new int[] {10, 1000, 900, 55});
        dragonData.put("Phaarthurnax", new int[] {6, 600, 700, 60});
        dragonData.put("D-Maleficent", new int[] {9, 900, 950, 85});
        dragonData.put("TheWeatherbe", new int[] {8, 800, 900, 80});
        dragonData.put("Igneel", new int[] {6, 600, 400, 60});
        dragonData.put("BlueEyesWhite", new int[] {9, 900, 600, 75});
    }


    // Public constructor that scales the dragon stats to the specified level
    public Dragon(int level) {
        this(selectRandomName(), level);
    }

    // Private constructor to take in the selected name and level, and scale attributes
    private Dragon(String name, int level) {
        super(name, level * 100, level, dragonData.get(name)[0],dragonData.get(name)[1], dragonData.get(name)[2], dragonData.get(name)[3]);
    }

    // Selects a random dragon name from the dragonData map
    private static String selectRandomName() {
        String[] dragonNames = dragonData.keySet().toArray(new String[0]);
        return dragonNames[new Random().nextInt(dragonNames.length)];
    }


    @Override
    public String getName() { return (super.getRawName() + " The Dragon"); }

}
