// Jonah Rothman
// IceSpell.java
// Represents an Ice Spell item with properties that affect monsters, reducing their agility or speed.

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class IceSpell extends Spell {
    // Static map to hold spell attributes based on the spell name
    private static final Map<String, int[]> iceSpellData = new HashMap<>();

    // Static initializer block to populate iceSpellData
    static {
        iceSpellData.put("Snow_Cannon", new int[] {500, 2, 650, 250});
        iceSpellData.put("Ice_Blade", new int[] {250, 1, 450, 100});
        iceSpellData.put("Frost_Blizzard", new int[] {750, 5, 850, 350});
        iceSpellData.put("Arctic_Storm", new int[] {700, 6, 800, 300});
    }

    // Global Random instance
    private static final Random random = new Random();

    // Constructor that takes only the spell name
    public IceSpell(String name) {
        super(name, iceSpellData.get(name)[0], iceSpellData.get(name)[1],
                iceSpellData.get(name)[2], iceSpellData.get(name)[3]);
    }

    // Default constructor that randomly selects a spell from the map
    public IceSpell() {
        // Use the private helper method to get random spell attributes
        this(getRandomSpellAttributes());
    }

    // Private constructor that takes name, price, level, damage, manaCost directly
    private IceSpell(String name, int price, int level, int damage, int manaCost) {
        super(name, price, level, damage, manaCost);
    }

    // Helper method to retrieve random spell attributes as an array
    private static String[] getRandomSpellAttributes() {
        String[] spellNames = iceSpellData.keySet().toArray(new String[0]);
        String randomName = spellNames[random.nextInt(spellNames.length)];
        int[] attributes = iceSpellData.get(randomName);

        // Return name and attributes as String array for the secondary constructor
        return new String[] { randomName, String.valueOf(attributes[0]), String.valueOf(attributes[1]),
                String.valueOf(attributes[2]), String.valueOf(attributes[3]) };
    }

    // Secondary constructor to initialize with specific attributes
    private IceSpell(String[] attributes) {
        this(attributes[0], Integer.parseInt(attributes[1]), Integer.parseInt(attributes[2]),
                Integer.parseInt(attributes[3]), Integer.parseInt(attributes[4]));
    }

    @Override
    public String getType(){ return "IceSpell"; }

    //Ice Spells reduce damage of the target
    @Override
    public void useSpell(Hero hero, Monster target) {
        //First subtract mana from hero
        hero.setMana(hero.getMana() - getManaCost());

        //Calculate spell damage
        int spellDamage = getDamage() + (hero.getDexterity() / 10000) * getDamage();

        target.reduceDamage(spellDamage);

        System.out.println("\n\u001B[93m"+hero.getName() + " uses " + getName() +
                " spell and " + target.getName() + " looses " + spellDamage + " damage points.\u001B[0m");
    }
}
