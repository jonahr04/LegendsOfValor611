//Jonah Rothman
//AttackStrategy.Java
//This is class that implements the attack strategy for the hero obects

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AttackStrategy implements ActionStrategy {

    @Override
    public void execute(Hero hero, Monster target) {
        //Find Weapon Equipped
        List<Weapon> equippedWeapons = getEquippedWeapons(hero);
        int totalDamage = 0;
        int damageAfterDefence =0;


        // Calculate total damage based on equipped weapons
        if (equippedWeapons.size() == 1) {
            Weapon weapon = equippedWeapons.get(0);
            totalDamage = (int)((hero.getStrength() + weapon.getDamage()) * 0.05);

            if (weapon.getHandsRequired() == 1 && hero.getHandsAvailable() == 1) {
                System.out.println("\n\u001B[93m" + weapon.getName() +
                        " is one-handed, but wielded with both hands, increasing damage by 75%!\u001B[0m");
                totalDamage *= 1.75;
            }
        } else if (equippedWeapons.size() == 2) {
            Weapon weapon1 = equippedWeapons.get(0);
            Weapon weapon2 = equippedWeapons.get(1);
            totalDamage = (int)((hero.getStrength() + weapon1.getDamage() + weapon2.getDamage()) * 0.05);

            System.out.println("\n\u001B[93mDual-wielding " + weapon1.getName() + " and " + weapon2.getName() +
                    "! Combined damage calculated to be "+totalDamage+".\u001B[0m");
        } else {
            System.out.println("No weapon equipped; using unarmed attack.");
        }

        int monsterDefence = (int) (target.getDefense() * 0.15);
        damageAfterDefence = totalDamage - monsterDefence;
        if(damageAfterDefence < 0) {
            damageAfterDefence = 0;
        }

        // Print attack message
        if (!equippedWeapons.isEmpty()) {
            System.out.println("\u001B[91m" + hero.getName() + " attacked " + target.getName() + " for " + totalDamage
                    + " damage with " + (equippedWeapons.size() == 2 ? "dual weapons" : equippedWeapons.get(0).getName())
                    + " "+ target.getName()+" defended "+monsterDefence+" so "+ damageAfterDefence+" total damage done\u001B[0m");
        }

        // Check if the monster dodges
        if (monsterDodge(target)) {
            System.out.println("\u001B[94m"+target.getName() + " dodged the attack! No damage done.\u001B[0m");
        } else {
            target.takeDamage(damageAfterDefence);
        }
    }

    // Method to get a list of equipped weapons
    public List<Weapon> getEquippedWeapons(Hero hero) {
        List<Weapon> equippedWeapons = new ArrayList<>();
        Inventory inventory = hero.getInventory();

        for (Item item : inventory.getItems()) {
            if (item instanceof Weapon && ((Weapon) item).isEquipped()) {
                equippedWeapons.add((Weapon) item);
            }
        }

        if (equippedWeapons.isEmpty()) {
            System.out.println("COULD NOT FIND EQUIPPED WEAPONS");
        }

        return equippedWeapons;
    }

    //Returns true if Monster Dodged
    public boolean monsterDodge(Monster monster) {
        double dodgeChance = monster.getDodgeChance();

        Random rand = new Random();
        double roll = rand.nextDouble(); // Generates a value between 0.0 and 1.0


        //System.out.println("Roll: " + roll);
        //System.out.println("Dodge chance: " + dodgeChance);
        return roll < dodgeChance;
    }
}
