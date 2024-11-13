//Jonah Rothman
//CastSpellStrategy.Java
//This is class that implements the strategy for casting spells


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CastSpellStrategy implements ActionStrategy {
    @Override
    public void execute(Hero hero, Monster target) {
        int currentMP = hero.getMana();
        Inventory inventory = hero.getInventory();
        List<Spell> spells = getSpells(hero);
        Scanner scanner = new Scanner(System.in);

        //Return if cant afford to use spells
        if(currentMP<=0){
            System.out.println("\u001B[93m"+hero.getName() + " doesnt have enough mana for any spells!\u001B[0m");
            return;
        }


        //If potions are empty, we cannot use one
        if (spells.isEmpty()) {
            System.out.println("\u001B[93mNo spells found, cannot use!\u001B[0m");
            return;
        }

        Spell spellToUse = spells.get(0); //First by default
        if(spells.size() == 1) {
            spellToUse = spells.get(0);
        }
        else {
            boolean validChoice = false;
            int choice = -1;

            // Display potion choices and prompt for selection
            while (!validChoice) {
                System.out.println("Which Spell would you like to use?");
                inventory.displaySpells();

                System.out.print("Enter the Spell number (1-" + spells.size() + "): ");
                String input = scanner.nextLine().trim();

                try {
                    choice = Integer.parseInt(input) - 1;
                    if (choice >= 0 && choice < spells.size()) {
                        if (currentMP >= spells.get(choice).getManaCost()) {
                            validChoice = true;
                        } else {
                            System.out.println("\u001B[93mInvalid choice. Not enough mana.\u001B[0m");
                        }
                    } else {
                        System.out.println("\u001B[93mInvalid choice. Please choose a number between 1 and " + spells.size() + ".\u001B[0m");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\u001B[93mInvalid input. Please enter a number.\u001B[0m");
                }
            }
            spellToUse = spells.get(choice);
        }


        //Apply the potion to the hero
        spellToUse.useSpell(hero,target);

        hero.getInventory().removeItem(spellToUse);  // Remove potion after use
        //ystem.out.println("\n\u001B[93m"+hero.getName() + " uses " + spellToUse.getName() +
        //        " and " + potionToUse.getStatAffected() + " gains " + potionToUse.getEffectAmount() + " points.\u001B[0m");
    }

    private List<Spell> getSpells(Hero hero) {
        List<Spell> spells = new ArrayList<>();

        Inventory inventory = hero.getInventory();
        for(Item item : inventory.getItems()) {
            if(item instanceof Spell) {
                spells.add((Spell) item);
            }
        }

        return spells;
    }
}
