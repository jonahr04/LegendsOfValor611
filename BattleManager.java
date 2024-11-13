//Jonah Rothman
//BattleManager.Java
//This is class that runs the logic for the battles in the game

import java.util.*;

public class BattleManager {

    private List<Hero> heroes;
    private List<Hero> faintedHeroes;
    private List<Monster> monsters;
    private ActionStrategy actionStrategy;
    private Random random = new Random();
    private int expiernceGained = 0;
    private int moneyGained = 0;


    public BattleManager(List<Hero> heroes, List<Monster> monsters) {
        this.heroes = heroes;
        this.monsters = monsters;
        faintedHeroes = new ArrayList<>();
    }


    //Method starts and runs the battle for the game
    public void startBattle() {

        displayAllStats();

        // Battle continues until all heroes or all monsters are defeated
        while (!heroes.isEmpty() && !monsters.isEmpty()) {

            //If heros remain
            if (!heroes.isEmpty()) {
                System.out.println("\u001B[94m\n\t\t\t\t\t\t\t\t\t------HEROES BATTLE TURN------\u001B[0m");
                // Heroes take their turn
                for (Hero hero : heroes) {
                    if (hero.getHP() > 0) {
                        if (!monsters.isEmpty()) {
                            Monster target = chooseMonsterTarget(monsters);
                            performHeroAction(hero, target);
                            removeTheDead(monsters);
                        }
                    }
                }
            }

            // Remove defeated monsters
            monsters.removeIf(monster -> monster.getHP() <= 0);

            // Monsters' turn if any remain
            if (!monsters.isEmpty()) {
                System.out.println("\u001B[91m\n\t\t\t\t\t\t\t\t\t------MONSTERS BATTLE TURN------\n\u001B[0m");
                for (Monster monster : monsters) {
                    if (monster.getHP() > 0) {
                        if(!heroes.isEmpty()) {
                            Hero target = chooseHeroTarget(heroes);
                            performMonsterAction(monster, target);
                            removeTheDead(heroes);
                        }
                    }
                }
            }


            // End-of-round healing for heroes
            endOfRoundRecovery();
        }

        // Outcome of the battle
        if (heroes.isEmpty()) {
            System.out.println("The monsters have won. Game over!\n\n");
        } else {
            System.out.println("The heroes have triumphed! Gaining rewards...");
            distributeRewards();
            bringBackFainted();
            levelUpPlayers();
        }
    }

    // This method will level up the players
    private void levelUpPlayers() {
        for(Hero hero : heroes) {

            int currentLevel = hero.getExperienceLevel();

            // If player has enough XP to level up
            if(hero.getExperiencePoints() >= currentLevel * 10){
                int newLevel = hero.getExperiencePoints() / 10 + 1;

                // Update the hero's level
                hero.setExperienceLevel(newLevel);
                hero.setHP(newLevel * 100);  // Update HP based on new level

                // Track initial and final values for skill increase reporting
                int initialAgility = hero.getAgility();
                int initialDexterity = hero.getDexterity();
                int initialStrength = hero.getStrength();

                // Increase all skills by 5 percent
                hero.increaseAgility((int) (hero.getAgility() * .05));
                hero.increaseDexterity((int) (hero.getDexterity() * .05));
                hero.increaseStrength((int) (hero.getStrength() * .05));

                // Apply favored skill bonus for specific hero types
                if (hero instanceof Warrior) {
                    hero.increaseAgility((int) (hero.getAgility() * .05));
                    hero.increaseStrength((int) (hero.getStrength() * .05));
                } else if (hero instanceof Sorcerer) {
                    hero.increaseAgility((int) (hero.getAgility() * .05));
                    hero.increaseDexterity((int) (hero.getDexterity() * .05));
                } else {
                    // Paladins favor Dexterity and Strength
                    hero.increaseDexterity((int) (hero.getDexterity() * .05));
                    hero.increaseStrength((int) (hero.getStrength() * .05));
                }

                // Calculate final values after level-up
                int finalAgility = hero.getAgility();
                int finalDexterity = hero.getDexterity();
                int finalStrength = hero.getStrength();

                // Print level-up message with detailed changes in yellow
                System.out.println("\n\u001B[93mLeveling up " + hero.getName() + " from level " + currentLevel +
                        " to level " + newLevel + " due to their " + hero.getExperiencePoints() + " experience points.");
                System.out.println("New HP: " + (newLevel * 100));
                System.out.println("Skill upgrades:");
                System.out.printf(" - Agility: %d -> %d %s\n", initialAgility, finalAgility,
                        (hero instanceof Warrior || hero instanceof Sorcerer) ? "(favored)" : "");
                System.out.printf(" - Dexterity: %d -> %d %s\n", initialDexterity, finalDexterity,
                        (hero instanceof Sorcerer || hero instanceof Paladin) ? "(favored)" : "");
                System.out.printf(" - Strength: %d -> %d %s\n\u001B[0m", initialStrength, finalStrength,
                        (hero instanceof Warrior || hero instanceof Paladin) ? "(favored)" : "");

                System.out.println();
            }
        }
    }


    // Method to remove characters with 0 or less HP from the list
    private boolean removeTheDead(List<? extends Character> characters) {
        boolean removed = false;
        for (Character character : characters) {
            if (character.getHP() <= 0) {
                System.out.println("\u001B[91m\n" + character.getName() + " has been killed!\n\u001B[0m");

                //if the character was a hero add him to fainted heros
                if(character instanceof Hero) {
                    faintedHeroes.add((Hero) character);
                }

                //if the character was a monster add to xp gained and money ganed
                if(character instanceof Monster) {
                    int monsterLevel = ((Monster) character).getLevel();
                    moneyGained += monsterLevel*100;
                    expiernceGained += monsterLevel;
                }

                removed = true;
            }
        }
        characters.removeIf(character -> character.getHP() <= 0);

        return removed;
    }

    private void bringBackFainted() {
        Iterator<Hero> iterator = faintedHeroes.iterator();  // Use an iterator to safely remove from list during iteration
        while (iterator.hasNext()) {
            Hero hero = iterator.next();

            // Revive the hero with half of their base HP and mana
            hero.setHP(hero.getBaseHP() / 2);
            hero.setMana(hero.getBaseMana() / 2);

            // Add the hero back to the active heroes list
            heroes.add(hero);

            // Remove the hero from faintedHeroes
            iterator.remove();

            System.out.println(hero.getName() + " has been revived with " + hero.getBaseHP() / 2 + " health and " +
                    hero.getBaseMana() / 2 + " mana!");
        }
    }

    private void displayAllStats(){
        //First Display Heroes and Monsters
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t-----HEROES-----\n");
        MonstersAndHeros.displayHeroes(heroes);

        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t-----MONSTERS-----\n");
        MonstersAndHeros.displayMonsters(monsters);
    }

    // Using Strategy Pattern for the action to perform
    private void performHeroAction(Hero hero, Monster target) {
        Scanner scanner = new Scanner(System.in);
        boolean validAction = false;

        while (!validAction) {
            System.out.println("\n" + hero.getName() + ", choose your action: ");
            System.out.println("1. Attack");
            System.out.println("2. Cast Spell");
            System.out.println("3. Use Potion");
            System.out.println("4. Equip Item");
            System.out.println("5. Show Stats");

            System.out.print("Enter your choice (1-5): ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    actionStrategy = new AttackStrategy();
                    validAction = true;
                    break;
                case "2":
                    actionStrategy = new CastSpellStrategy();
                    validAction = true;
                    break;
                case "3":
                    actionStrategy = new UsePotionStrategy();
                    validAction = true;
                    break;
                case "4":
                    equipItems(hero);
                    break;
                case "5":
                    displayAllStats();
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number between 1 and 6.");
                    break;
            }
        }
        // Execute the selected action strategy once a valid action is chosen
        actionStrategy.execute(hero, target);
    }

    // Method to equip items
    private void equipItems(Hero hero) {
        Scanner scanner = new Scanner(System.in);
        boolean equipMenuActive = true;

        //get Hands Available
        Inventory inventory = hero.getInventory();
        int handsAvailable = hero.getHandsAvailable();

        System.out.println("\nYou have " + handsAvailable + "/2 hands available.");

        while (equipMenuActive) {
            System.out.println("\nEquipment Menu:");
            System.out.println("1. Equip Item");
            System.out.println("2. Unequip Item");
            System.out.println("3. Leave Equipment Menu");

            System.out.print("Choose an option (1-3): ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1": // Equip Item
                    inventory.displayInventory();
                    System.out.print("Enter the item number to equip: ");
                    try {
                        int itemIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;

                        if (itemIndex >= 0 && itemIndex < inventory.getItems().size()) {
                            Item item = inventory.getItems().get(itemIndex);

                            if (item instanceof Armor) {
                                if (hero.hasEquippedArmor()) {
                                    System.out.println("You already have an armor equipped. Unequip it first to equip a new one.");
                                } else {
                                    ((Equipable) item).equip();
                                    System.out.println(item.getName() + " equipped as armor.");
                                }
                            } else if (item instanceof Weapon) {
                                int requiredHands = ((Weapon) item).getHandsRequired();
                                if (requiredHands <= handsAvailable) {
                                    ((Equipable) item).equip();
                                    handsAvailable -= requiredHands;
                                    System.out.println(item.getName() + " equipped as a weapon. "+handsAvailable+" hands available.");
                                } else {
                                    System.out.println("Not enough hands available to equip " + item.getName() + ".");
                                }
                            } else {
                                System.out.println("Cannot equip this item.");
                            }
                        } else {
                            System.out.println("Invalid item number.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                    }
                    break;

                case "2": // Unequip Item
                    inventory.displayInventory();
                    System.out.print("Enter the item number to unequip: ");
                    try {
                        int itemIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;

                        if (itemIndex >= 0 && itemIndex < inventory.getItems().size()) {
                            Item item = inventory.getItems().get(itemIndex);

                            if (item instanceof Equipable && ((Equipable) item).isEquipped()) {
                                ((Equipable) item).unequip();
                                if (item instanceof Weapon) {
                                    handsAvailable += ((Weapon) item).getHandsRequired();
                                }
                                System.out.println(item.getName() + " unequipped.");
                            } else {
                                System.out.println("This item is not currently equipped.");
                            }
                        } else {
                            System.out.println("Invalid item number.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                    }
                    break;

                case "3": // Leave Equipment Menu
                    if (!hero.hasEquippedWeapon()) {  // Check if weapon is equipped
                        System.out.println("\u001B[93mNeed Weapon Equipped! Go back and equip at least 1 weapon!\u001B[0m");
                    } else {
                        equipMenuActive = false;
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                    break;
            }
        }
    }

    // Monster's automatic attack action
    private void performMonsterAction(Monster monster, Hero target) {
        int armorReduction = target.getArmorReduction();

        System.out.print(monster.getName() + " attacks " + target.getName() + "! ");
        int damage = monster.getDamage() - armorReduction;
        if(damage<0){
            damage = 0;
        }

        if (!heroDodge(target)) {
            target.takeDamage(damage);
            System.out.print(" \u001B[91m" + target.getName() +  " took " + damage + " damage");

            if(target.hasEquippedArmor()){
                System.out.print(", and his armor blocked "+armorReduction+" damage");
            }

            System.out.println("! \u001B[0m");
        } else {
            System.out.println(" \u001B[94m" +target.getName() + " dodged the attack completely!"+ "\u001B[0m");
        }
    }

    //Returns true if Hero Dodged
    public boolean heroDodge(Hero hero) {
        double agility = (double) hero.getAgility();
        double dodgeChance = agility * 0.0011;

        //System.out.println("Dodge Chance"+ dodgeChance);

        Random rand = new Random();
        double roll = 2*rand.nextDouble(); // Generates a value between 0.0 and 1.0
        //System.out.println("Roll "+ roll);

        //cap at 80 percent
        if(dodgeChance>1.6){
            dodgeChance = 1.6;
        }

        return roll < dodgeChance;
    }

    // Choose a random target hero, ensuring the list is not empty
    private Hero chooseHeroTarget(List<Hero> heroes) {
        if (heroes.isEmpty()) {
            throw new IllegalStateException("No heroes available to target.");
        }
        return heroes.get(random.nextInt(heroes.size()));
    }

    // Choose a random target monster, ensuring the list is not empty
    private Monster chooseMonsterTarget(List<Monster> monsters) {
        if (monsters.isEmpty()) {
            throw new IllegalStateException("No monsters available to target.");
        }
        return monsters.get(random.nextInt(monsters.size()));
    }

    // End-of-round recovery for heroes
    private void endOfRoundRecovery() {
        for (Hero hero : heroes) {
            hero.recoverHP(0.075);  // Recover 10% of max HP
            hero.recoverMP(0.075);  // Recover 10% of max MP
        }
    }

    // Distribute rewards to heroes if they win
    private void distributeRewards() {
        for (Hero hero : heroes) {
            hero.gainExperiencePoints(expiernceGained);  // Gain experience points
            hero.gainMoney(moneyGained);        // Gain gold
        }
    }
}