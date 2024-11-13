// Jonah Rothman
// Weapon.java
// This class represents a weapon with attributes like damage, hands required, and level, equippable by heroes for combat use.

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Weapon implements Item, Equipable {
    private String name;
    private int price;
    private int level;
    private int damage;
    private int handsRequired;
    private boolean equipped;

    // map to hold weapon attributes based on the weapon name
    private Map<String, int[]> weaponData = new HashMap<>(); {
        weaponData.put("Sword", new int[] {500, 1, 800, 1});
        weaponData.put("Bow", new int[] {300, 2, 600, 2});
        weaponData.put("Scythe", new int[] {1000, 6, 1100, 2});
        weaponData.put("Axe", new int[] {550, 5, 850, 1});
        weaponData.put("TSwords", new int[] {1400, 8, 1600, 2});
        weaponData.put("Dagger", new int[] {200, 1, 350, 1});
    }

    // Constructor with all parameters
    public Weapon(String name, int price, int level, int damage, int handsRequired) {
        this.name = name;
        this.price = price;
        this.level = level;
        this.damage = damage;
        this.handsRequired = handsRequired;
        this.equipped = false;
    }

    // Overloaded constructor that takes only the name and looks up other attributes
    public Weapon(String name) {
        this.name = name;
        int[] attributes = weaponData.get(name);
        this.price = attributes[0];
        this.level = attributes[1];
        this.damage = attributes[2];
        this.handsRequired = attributes[3];
        this.equipped = false;
    }

    // Default constructor that randomly selects a weapon from the map
    public Weapon() {
        String[] weaponNames = weaponData.keySet().toArray(new String[0]);
        String randomName = weaponNames[new Random().nextInt(weaponNames.length)];
        int[] attributes = weaponData.get(randomName);

        this.name = randomName;
        this.price = attributes[0];
        this.level = attributes[1];
        this.damage = attributes[2];
        this.handsRequired = attributes[3];
        this.equipped = false;
    }

    // Getter methods
    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getRequiredLevel() { return level; }
    public int getDamage() { return damage; }
    public int getHandsRequired() { return handsRequired; }
    public String getType() { return "Weapon"; }

    // Equipable interface methods
    public void equip() { this.equipped = true; }
    public void unequip() { this.equipped = false; }
    public boolean isEquipped() { return equipped; }

    // Calculate effective damage, increasing if one-handed and equipped
    public int getEffectiveDamage() {
        if(handsRequired == 1 && equipped) {
            return (int) (damage * 1.5);
        }
        return damage;
    }
}
