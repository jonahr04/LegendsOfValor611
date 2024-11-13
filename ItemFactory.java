// Jonah Rothman
// ItemFactory.java
// This class generates items of various types (weapons, armor, potions, spells), used to equip and enhance heroes.

import java.util.Random;

public class ItemFactory {

    // Array of main item categories
    private static final String[] itemCategories = {"Weapon", "Armor", "Potion", "Spell"};

    // Array of possible spell types
    private static final String[] spellTypes = {"Ice Spell", "Fire Spell", "Lightning Spell"};

    // Factory method to create an item based on the specified item type
    public static Item getItem(String itemType) {
        switch (itemType.toLowerCase()) {
            case "weapon":
                return new Weapon();
            case "armor":
                return new Armor();
            case "potion":
                return new Potion();
            case "spell":
                Random rand = new Random();
                String spellType = spellTypes[rand.nextInt(spellTypes.length)];
                switch (spellType) {
                    case "Ice Spell":
                        return new IceSpell();
                    case "Fire Spell":
                        return new FireSpell();
                    case "Lightning Spell":
                        return new LightningSpell();
                    default:
                        throw new IllegalArgumentException("Unknown spell type: " + spellType);
                }
            default:
                throw new IllegalArgumentException("Unknown item type: " + itemType);
        }
    }

    // Method to generate a completely random item
    public static Item getRandomItem() {
        Random rand = new Random();

        // Randomly select an item category
        String itemCategory = itemCategories[rand.nextInt(itemCategories.length)];

        // Use getItem to create an item based on the randomly selected category
        return getItem(itemCategory);
    }
}
