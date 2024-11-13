// Jonah Rothman
// UsePotionStrategy.java
// This class implements the strategy for heroes to use potions, affecting their stats or recovering health and mana during battles.

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsePotionStrategy implements ActionStrategy {
    @Override
    public void execute(Hero hero, Monster target) {
        Inventory inventory = hero.getInventory();
        List<Potion> potions = getPotions(hero);
        Scanner scanner = new Scanner(System.in);


        //If potions are empty, we cannot use one
        if (potions.isEmpty()) {
            System.out.println("\u001B[93mNo potions found, cannot use!\u001B[0m");
            return;
        }

        Potion potionToUse = potions.get(0); //First by default
        if(potions.size() == 1) {
            potionToUse = potions.get(0);
        }
        else {
            boolean validChoice = false;
            int choice = -1;

            // Display potion choices and prompt for selection
            while (!validChoice) {
                System.out.println("Which Potion would you like to use?");
                inventory.displayPotions();

                System.out.print("Enter the potion number (1-" + potions.size() + "): ");
                String input = scanner.nextLine().trim();

                try {
                    choice = Integer.parseInt(input) - 1;
                    if (choice >= 0 && choice < potions.size()) {
                        validChoice = true;
                    } else {
                        System.out.println("\u001B[93mInvalid choice. Please choose a number between 1 and " + potions.size() + ".\u001B[0m");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\u001B[93mInvalid input. Please enter a number.\u001B[0m");
                }
            }
            potionToUse = potions.get(choice);
        }

        //Apply the potion to the hero
        potionToUse.usePotion(hero);

        hero.getInventory().removeItem(potionToUse);  // Remove potion after use
        System.out.println("\n\u001B[93m"+hero.getName() + " uses " + potionToUse.getName() +
                " and " + potionToUse.getStatAffected() + " gains " + potionToUse.getEffectAmount() + " points.\u001B[0m");
    }

    private List<Potion> getPotions(Hero hero) {
        List<Potion> potions = new ArrayList<>();

        Inventory inventory = hero.getInventory();
        for(Item item : inventory.getItems()) {
            if(item instanceof Potion) {
                potions.add((Potion) item);
            }
        }

        return potions;
    }


}