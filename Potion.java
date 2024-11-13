// Jonah Rothman
// Potion.java
// This class represents a potion item, defining its effects and the stats it boosts for the hero.

import java.util.*;

public class Potion implements Item {
    private String name;
    private int price;
    private int level;
    private int effectAmount;
    private String statAffected;

    // Map to hold potion attributes based on the potion name
    private Map<String, Object[]> potionData = new HashMap<>();{
        potionData.put("Healing_Potion", new Object[] {250, 1, 100, "Health"});
        potionData.put("Strength_Potion", new Object[] {200, 1, 75, "Strength"});
        potionData.put("Magic_Potion", new Object[] {350, 2, 100, "Mana"});
        potionData.put("Luck_Elixir", new Object[] {500, 4, 65, "Agility"});
        potionData.put("Mermaid_Tears", new Object[] {850, 5, 100, "Health/Mana/Strength/Agility"});
        potionData.put("Ambrosia", new Object[] {1000, 8, 150, "Health/Mana/Strength/Dexterity/Agility"});
    }

    // constructor with all parameters
    public Potion(String name, int price, int level, int effectAmount, String statAffected) {
        this.name = name;
        this.price = price;
        this.level = level;
        this.effectAmount = effectAmount;
        this.statAffected = statAffected;
    }

    // overloaded constructor that takes only the name and looks up other attributes
    public Potion(String name) {
        if (potionData.containsKey(name)) {
            this.name = name;
            Object[] attributes = potionData.get(name);
            this.price = (int) attributes[0];
            this.level = (int) attributes[1];
            this.effectAmount = (int) attributes[2];
            this.statAffected = (String) attributes[3];
        } else {
            throw new IllegalArgumentException("Potion name not found in data map: " + name);
        }
    }

    //default constructor that randomly selects a potion from the map
    public Potion() {
        String[] potionNames = potionData.keySet().toArray(new String[0]);
        String randomName = potionNames[new Random().nextInt(potionNames.length)];
        Object[] attributes = potionData.get(randomName);

        this.name = randomName;
        this.price = (int) attributes[0];
        this.level = (int) attributes[1];
        this.effectAmount = (int) attributes[2];
        this.statAffected = (String) attributes[3];
    }

    public void usePotion(Hero hero) {
        List<String> statsList = new ArrayList<>();
        statsList = getAffectedStats(statAffected);
        int amount = effectAmount;

        for(String stat : statsList) {
            switch (stat.toLowerCase()) {
                case "health":
                    hero.increaseHP(amount);
                    break;
                case "mana":
                    hero.increaseMana(amount);
                    break;
                case "strength":
                    hero.increaseStrength(amount);
                    break;
                case "dexterity":
                    hero.increaseDexterity(amount);
                    break;
                case "agility":
                    hero.increaseAgility(amount);
                    break;
                default:
                    System.out.println("Unknown stat: " + stat);
                    break;
            }
        }

    }

    private List<String> getAffectedStats(String statString) {
        // Split the stat string by "/" and return as a list
        List<String> stats = new ArrayList<>();
        for (String stat : statString.split("/")) {
            stats.add(stat.trim());
        }
        return stats;
    }

    // Getter methods
    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getRequiredLevel() { return level; }
    public String getStatAffected() { return statAffected; }
    public int getEffectAmount() { return effectAmount; }
    public String getType() { return "Potion"; }
}
