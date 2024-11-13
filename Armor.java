//Jonah Rothman
//Armor.Java
//This is class for armor objects that implements item and equipable


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Armor implements Item, Equipable {
    private String name;
    private int price;
    private int level;
    private int damageReduction;
    private boolean equipped;

    // Map to hold armor attributes based on the armor name
    private Map<String, int[]> armorData = new HashMap<>();{
        armorData.put("Platinum_Shield", new int[] {150, 1, 200});
        armorData.put("Breastplate", new int[] {350, 3, 600});
        armorData.put("Full_Body_Armor", new int[] {1000, 8, 1100});
        armorData.put("Wizard_Shield", new int[] {1200, 10, 1500});
        armorData.put("Guardian_Angel", new int[] {1000, 10, 1000});
    }


    // Constructor with all parameters
    public Armor(String name, int price, int level, int damageReduction) {
        this.name = name;
        this.price = price;
        this.level = level;
        this.damageReduction = damageReduction;
        this.equipped = false;
    }

    // Overloaded constructor that takes only the name and looks up other attributes
    public Armor(String name) {
        if (armorData.containsKey(name)) {
            this.name = name;
            int[] attributes = armorData.get(name);
            this.price = attributes[0];
            this.level = attributes[1];
            this.damageReduction = attributes[2];
            this.equipped = false;
        } else {
            throw new IllegalArgumentException("Armor name not found in data map: " + name);
        }
    }

    // Default constructor that randomly selects an armor piece from the map
    public Armor() {
        String[] armorNames = armorData.keySet().toArray(new String[0]);
        String randomName = armorNames[new Random().nextInt(armorNames.length)];
        int[] attributes = armorData.get(randomName);

        this.name = randomName;
        this.price = attributes[0];
        this.level = attributes[1];
        this.damageReduction = attributes[2];
        this.equipped = false;
    }

    // Getter methods
    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getRequiredLevel() { return level; }
    public int getDamageReduction() { return damageReduction; }
    public String getType() { return "Armor"; }

    // Equipable interface methods
    public void equip() { this.equipped = true; }
    public void unequip() { this.equipped = false; }
    public boolean isEquipped() { return equipped; }
}
