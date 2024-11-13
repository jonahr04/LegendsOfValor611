// Jonah Rothman
// FireSpell.java
// Represents a Fire Spell item with properties and effects related to fire damage, usable by heroes.

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FireSpell extends Spell {
    // Static map to hold spell attributes based on the spell name
    private static final Map<String, int[]> fireSpellData = new HashMap<>();

    // Static initializer block to populate fireSpellData
    static {
        fireSpellData.put("Flame_Tornado", new int[] {700, 4, 850, 300});
        fireSpellData.put("Breath_of_Fire", new int[] {350, 1, 450, 100});
        fireSpellData.put("Heat_Wave", new int[] {450, 2, 600, 150});
        fireSpellData.put("Lava_Comet", new int[] {800, 7, 1000, 550});
        fireSpellData.put("Hell_Storm", new int[] {600, 3, 950, 600});
    }

    // Global Random instance
    private static final Random random = new Random();

    // Constructor that takes only the spell name
    public FireSpell(String name) {
        super(name, fireSpellData.get(name)[0], fireSpellData.get(name)[1],
                fireSpellData.get(name)[2], fireSpellData.get(name)[3]);
    }

    // Default constructor that randomly selects a spell from the map
    public FireSpell() {
        // Use the private helper method to get random spell attributes
        this(getRandomSpellAttributes());
    }

    // Private constructor that takes name, price, level, damage, manaCost directly
    private FireSpell(String name, int price, int level, int damage, int manaCost) {
        super(name, price, level, damage, manaCost);
    }

    // Helper method to retrieve random spell attributes as an array
    private static String[] getRandomSpellAttributes() {
        String[] spellNames = fireSpellData.keySet().toArray(new String[0]);
        String randomName = spellNames[random.nextInt(spellNames.length)];
        int[] attributes = fireSpellData.get(randomName);

        // Return name and attributes as String array for the secondary constructor
        return new String[] { randomName, String.valueOf(attributes[0]), String.valueOf(attributes[1]),
                String.valueOf(attributes[2]), String.valueOf(attributes[3]) };
    }

    // Secondary constructor to initialize with specific attributes
    private FireSpell(String[] attributes) {
        this(attributes[0], Integer.parseInt(attributes[1]), Integer.parseInt(attributes[2]),
                Integer.parseInt(attributes[3]), Integer.parseInt(attributes[4]));
    }

    @Override
    public String getType(){ return "FireSpell"; }

    //Fire Spells reduce Defence of the target
    @Override
    public void useSpell(Hero hero, Monster target) {
        //First subtract mana from hero
        hero.setMana(hero.getMana() - getManaCost());

        //Calculate spell damage
        int spellDamage = getDamage() + (hero.getDexterity() / 10000) * getDamage();

        target.reduceDefense(spellDamage);

        System.out.println("\n\u001B[93m"+hero.getName() + " uses " + getName() +
                " spell and " + target.getName() + " looses " + spellDamage + " defence points.\u001B[0m");
    }
}
