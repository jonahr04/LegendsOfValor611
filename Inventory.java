// Jonah Rothman
// Inventory.java
// Manages a collection of items held by a hero, providing methods for adding, removing, and equipping items.

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    // Method to add an item to the inventory
    public void addItem(Item item) {
        items.add(item);
    }

    // Method to remove an item from the inventory
    public void removeItem(Item item) {
        if (items.remove(item)) {
            System.out.println(item.getName() + " removed from inventory.");
        } else {
            System.out.println(item.getName() + " not found in inventory.");
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public void displayInventory() {
        if(!items.isEmpty()) {
            System.out.println("    Inventory:");
            int counter = 1;
            for (Item item : items) {
                System.out.print("    "+counter + ". ");
                counter++;
                if (item instanceof Weapon) {
                    System.out.println("  - " + item.getName() +
                            " (Type: Weapon, Damage: " + ((Weapon) item).getDamage() +
                            ", Hands Required: " + ((Weapon) item).getHandsRequired()+
                            ", Level: " + item.getRequiredLevel()+
                            ", Price: " + item.getPrice() +
                            ", Equipped: "+
                            ((Equipable) item).isEquipped() +")");
                }
                else if (item instanceof Armor) {
                    System.out.println("  - " + item.getName() +
                            " (Type: Armor, Damage Reduction: " + ((Armor) item).getDamageReduction() +
                            ", Level: " + item.getRequiredLevel()+
                            ", Price: " + item.getPrice() +
                            ", Equipped: "+
                            ((Equipable) item).isEquipped() +")");
                }
                else if (item instanceof Spell) {
                    System.out.println("  - " + item.getName() +
                            " (Type: "+item.getType()+
                            ", Damage: "+ ((Spell) item).getDamage() +
                            ", Mana Cost: " + ((Spell) item).getManaCost() +
                            ", Level: " + item.getRequiredLevel()+
                            ", Price: " + item.getPrice()  +")");
                }
                else if (item instanceof Potion) {
                    System.out.println("  - " + item.getName() +
                            " (Type: Potion, Effect Ammount: " + ((Potion) item).getEffectAmount() +
                            ", Stat Effected: " + ((Potion) item).getStatAffected() +
                            ", Level: " + item.getRequiredLevel()+
                            ", Price: " + item.getPrice()  +")");
                }
            }
        }
    }

    public void displayPotions() {
        if(!items.isEmpty()) {
            System.out.println("    Potions:");
            int counter = 1;
            for (Item item : items) {
                if (item instanceof Potion) {
                    System.out.print("    "+counter + ". ");
                    System.out.println("  - " + item.getName() +
                            " (Type: Potion, Effect Ammount: " + ((Potion) item).getEffectAmount() +
                            ", Stat Effected: " + ((Potion) item).getStatAffected() +
                            ", Level: " + item.getRequiredLevel()+
                            ", Price: " + item.getPrice()  +")");
                    counter++;
                }
            }
        }
    }

    public void displaySpells() {
        if(!items.isEmpty()) {
            System.out.println("    Spells:");
            int counter = 1;
            for (Item item : items) {
                if (item instanceof Spell) {
                    System.out.print("    "+counter + ". ");
                    System.out.println("  - " + item.getName() +
                            " (Type: "+item.getType()+
                            ", Damage: "+ ((Spell) item).getDamage() +
                            ", Mana Cost: " + ((Spell) item).getManaCost() +
                            ", Level: " + item.getRequiredLevel()+
                            ", Price: " + item.getPrice()  +")");
                    counter++;
                }
            }
        }
    }
}
