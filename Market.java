// Jonah Rothman
// Market.java
// This class represents the market where heroes can buy and sell items, interacting with various available goods.

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Market {
    private List<Item> itemsForSale; // Inventory for each Market

    // Constructor that generates a random selection of items
    public Market() {
        this.itemsForSale = new ArrayList<>();
        populateMarket(); // Populate with random items
    }

    // Populate market with random items using ItemFactory
    private void populateMarket() {
        Random rand = new Random();
        int numItems = rand.nextInt(5) + 4; // Each market will have between 4 and 9 items

        for (int i = 0; i < numItems; i++) {
            itemsForSale.add(ItemFactory.getRandomItem()); // Generate random items
        }
    }

    // Display items for sale in this Market
    public void displayItems() {
        if(itemsForSale.isEmpty()) {
            System.out.println("No Items For Sale :(.  Sorry!!");
        }else {
            int counter = 1;
            System.out.println("Items available for sale:");
            for (Item item : itemsForSale) {
                System.out.print("    "+counter + ". ");
                counter++;
                if (item instanceof Weapon) {
                    System.out.println("  - " + item.getName() +
                            " (Type: Weapon, Damage: " + ((Weapon) item).getDamage() +
                            ", Hands Required: " + ((Weapon) item).getHandsRequired()+
                            ", Level: " + item.getRequiredLevel()+
                            ", Price: " + item.getPrice() +")");
                }
                else if (item instanceof Armor) {
                    System.out.println("  - " + item.getName() +
                            " (Type: Armor, Damage Reduction: " + ((Armor) item).getDamageReduction() +
                            ", Level: " + item.getRequiredLevel()+
                            ", Price: " + item.getPrice() +")");
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

    //Method will return if the hero can buy the item (both money and level need to meet the item)
    public boolean canBuyItem(Item item, Hero hero) {

        //First check if they have the money to buy
        if (hero.getMoney() < item.getPrice()) {
            return false;
        }

        if(hero.getExperienceLevel() < item.getRequiredLevel()) {
            return false;
        }

        //Player can buy the item
        return true;
    }

    public List<Item> getItemsForSale() {
        return itemsForSale;
    }

    public void removeItem(Item item) {
        itemsForSale.remove(item);
    }

    public void addItem(Item item) {
        itemsForSale.add(item);
    }

    public void buyItem(Item item, Hero hero) {
        hero.getInventory().addItem(item);
        hero.setMoney(hero.getMoney() - item.getPrice());
        removeItem(item);
        System.out.println("\n"+hero.getName() + " has bought " + item.getName() + " for " + item.getPrice() + " coins.");
    }

    public void sellItem(Item item, Hero hero) {
        if(item instanceof Weapon){
            if(((Equipable) item).isEquipped()){
                System.out.println("Cannot sell an equipped Weapon, go into battle to unequip/swap weapons");
            }
        }else {
            hero.getInventory().removeItem(item);
            hero.setMoney((int) (hero.getMoney() + (item.getPrice() * 0.5)));
            addItem(item);
            System.out.println("\n" + hero.getName() + " has bought " + item.getName() + " for " + (item.getPrice() * 0.5) + " coins.");
        }
    }
}
