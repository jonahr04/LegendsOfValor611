// Jonah Rothman
// LightningSpell.java
// This class describes the Lightning Spell, detailing its unique properties and effects in combat.

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LightningSpell extends Spell {
    private static final Map<String, int[]> lightningSpellData = new HashMap<>();

    // static initializer block to populate lightningSpellData
    static {
        lightningSpellData.put("Lightning_Dagger", new int[] {400, 1, 500, 150});
        lightningSpellData.put("Thunder_Blast", new int[] {750, 4, 950, 400});
        lightningSpellData.put("Electric_Arrows", new int[] {550, 5, 650, 200});
        lightningSpellData.put("Spark_Needles", new int[] {500, 2, 600, 200});
    }

    private static final Random random = new Random();

    // constructor that takes only the spell name
    public LightningSpell(String name) {
        super(name, lightningSpellData.get(name)[0], lightningSpellData.get(name)[1],
                lightningSpellData.get(name)[2], lightningSpellData.get(name)[3]);
    }

    //default constructor that randomly selects a spell from the map
    public LightningSpell() {
        this(getRandomSpellAttributes());
    }

    //private constructor that takes name, price, level, damage, manaCost directly
    private LightningSpell(String name, int price, int level, int damage, int manaCost) {
        super(name, price, level, damage, manaCost);
    }

    //  method to retrieve random spell attributes as an array
    private static String[] getRandomSpellAttributes() {
        String[] spellNames = lightningSpellData.keySet().toArray(new String[0]);
        String randomName = spellNames[random.nextInt(spellNames.length)];
        int[] attributes = lightningSpellData.get(randomName);

        // Return name and attributes as String array for the secondary constructor
        return new String[] { randomName, String.valueOf(attributes[0]), String.valueOf(attributes[1]),
                String.valueOf(attributes[2]), String.valueOf(attributes[3]) };
    }

    // constructor to initialize with specific attributes
    private LightningSpell(String[] attributes) {
        this(attributes[0], Integer.parseInt(attributes[1]), Integer.parseInt(attributes[2]),
                Integer.parseInt(attributes[3]), Integer.parseInt(attributes[4]));
    }

    @Override
    public String getType(){ return "LightningSpell"; }

    //Lighting Spells reduce dodge chance of the target
    @Override
    public void useSpell(Hero hero, Monster target) {
        //First subtract mana from hero
        hero.setMana(hero.getMana() - getManaCost());

        //Calculate spell damage
        double spellDamage = getDamage() + ((double) hero.getDexterity() / 10000) * getDamage();

        //scale to percent
        spellDamage /= 10000.0;

        target.reduceDodgeChance(spellDamage);

        System.out.println("\n\u001B[93m"+hero.getName() + " uses " + getName() +
                " spell and " + target.getName() + " looses " + (100*spellDamage) + " Dodge points.\u001B[0m");
    }
}
