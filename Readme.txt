# CS611-Assignment 4
## Game Infrastructure Monsters and Heroes
---------------------------------------------------------------------------
- Name: Jonah Rothman
- Email: jonahr@bu.edu
- Student ID: U45391341

## Files
---------------------------------------------------------------------------

                                            --------------------------
                                             Core Game Infrastructure
                                            --------------------------
Main.java: Entry point of the program that initializes the game manager and starts the user interaction. It allows the
user to select the game to play or quit.

Game.java: An interface that defines the methods common to all games, ensuring consistency in the game implementations.

Board.java: Manages the board's creation and updating, handling the display and resetting of cells during gameplay.
This class is used by both Tic Tac Toe and Order and Chaos.

BoardCell.java: Represents individual cells on the board by storing the Object of whatever the Piece might be.

GameRunner.java: Manages the overall game flow, allowing users to switch between different games, record results, and
track player statistics. This class handles user-friendly commands such as quitting and switching games.

                                            --------------------------
                                            Monsters and Heroes Classes
                                            --------------------------

MonstersAndHeros.java: Manages the main gameplay for Monsters and Heroes, handling character movement, battles, and
interactions with markets.

Character.java: This is a simple interface class for the Heroes and monsters so they are the same type and we can use
polymorphism to instantiate them

Hero.java: Base class for all hero types, providing core attributes such as HP, Mana, and stats. Implements Character
interface

HeroFactory.java: Factory class for creating hero instances, generating random names and attributes based on hero type.

Warrior.java: Defines the Warrior hero type, with enhanced strength and agility. Extends Hero Class.

Paladin.java: Represents the Paladin hero type, with enhanced Strength and dexterity. Extends Hero Class.

Sorcerer.java: Represents the Sorcerer hero type, with enhanced dexterity and agility. Extends Hero Class.

Monster.java: Base class for all monsters, containing shared properties like level, damage, defense, and dodge chance.

MonsterFactory.java: Factory class for generating random monsters, with attributes scaled to match hero levels.

Dragon.java: Represents the Dragon monster type with increased base damage. Extends the Monster class.

Exoskeleton.java:  Represents the Exoskeleton monster type with increased defence. Extends the Monster class.

Spirit.java: Represents the Spirit monster type with increased dodge ability. Extends the Monster class.

                                            --------------------------
                                            Combat and Strategy Classes
                                            --------------------------

BattleManager.java: Central battle controller that organizes turns between heroes and monsters, manages action outcomes,
and handles post-battle rewards.

ActionStrategy.java: Interface for defining different strategies heroes can use in battles (Attack, Defend, Cast Spell,
etc.).

AttackStrategy.java: Implements an attack strategy, calculating damage based on hero’s equipped weapon/weapons.
Implements Action Strategy.

CastSpellStrategy.java: Strategy allowing heroes to cast spells from their inventory, applying effects based on spell
type. Implements Action Strategy.

UsePotionStrategy.java: Implements potion-using strategy, enhancing hero stats temporarily or restoring health/mana.
Implements Action Strategy.

                                            --------------------------
                                          Items, Inventory, Factory Classes
                                            --------------------------
Inventory.java: Manages each hero's inventory, storing and organizing items like weapons, armor, potions, and spells.

Item.java: Base class for all items, defining essential attributes like name, price, and level.

ItemFactory.java: Factory class generating items randomly, creating weapons, armor, potions, and spells based on
probabilities.

Weapon.java: Class for weapon items, providing attack power and tracking how many hands the weapon requires.

Armor.java: Class for armor items, offering damage reduction for equipped heroes.

Potion.java: Defines potions that temporarily enhance hero attributes or replenish health/mana.

Spell.java: Abstract class for spells, holding attributes such as damage, mana cost, and level.

IceSpell.java: A type of spell that lowers the target’s agility in addition to dealing damage.

FireSpell.java: A type of spell that reduces the target’s defense, causing extra vulnerability to attacks.

LightningSpell.java: A spell type that decreases the target's dodge chance, making them easier to hit.

Equipable.java: Interface for items that can be equipped by heroes, enforcing methods like equip and unequip.

                                            --------------------------
                                           Game Board Spaces and Market
                                            --------------------------
CommonSpace.java: Represents a traversable board cell where heroes may encounter monsters.

MarketSpace.java: Space type where heroes can buy and sell items, offering various RPG items for purchase.

InaccessibleSpace.java: Represents a non-traversable cell on the game board, blocking hero movement.

HeroSpace.java: Represents the space where the hero is.

Market.java: Holds the items available for sale and buyback prices, managing transactions with heroes.

                                            --------------------------
                                               Tic Tac Toe Classes
                                            --------------------------
XPiece.java: Represents the red X piece

OPiece.java: Represents the Blue O piece

Player.java: Class for player objects which stores the player name and a hashmap for their win stats

TicTacToe.java: Implements the Tic Tac Toe game logic, supporting scalable board sizes and player moves. It checks for
win conditions horizontally, vertically, and diagonally.

OrderAndChaos.java: Implements the Order and Chaos game rules, fixed on a 6x6 board where players aim to get five in a
row. It allows players to choose between X and O on each turn.

SuperTicTacToe.java: Implements the Super Tic Tac Toe game rules. Stores a Super board, containing  3x3 boards for each
game and has them represented on an additional display board which acts as the actual game board


## Notes
---------------------------------------------------------------------------
                                            ------- Design Patterns -------
Strategy Pattern: Implemented in Monsters and Heroes to handle flexible hero actions during combat. Each action—attack,
defend, cast spell, and use potion—is encapsulated as a separate strategy class (e.g., AttackStrategy, DefendStrategy),
allowing each hero to dynamically select and execute different actions depending on the game context.

Factory Pattern: Widely utilized for object creation across various game entities. The HeroFactory, MonsterFactory, and
ItemFactory classes abstract away instantiation details and provide a clear, consistent interface for creating heroes,
monsters, and items.

Iterator Usage: In BattleManager.java bringBackTheFainted method used the iterator approach we learned from class,
iterating though the heros.

                              ------- Reusability, Scalability, and Encapsulation -------
Reusability: Key interfaces, such as Game and Equipable, provide a foundation for implementing new games or equippable
items without modifying core logic. Additionally, the Character interface ensures that methods common to both heroes and
monsters (e.g., takeDamage, getHP) are accessible across these classes. This makes features like removeTheDead, which
uses generics and accepts any type of Character, applicable to both heroes and monsters, reducing redundant code and
enhancing reusability.

Encapsulation: Classes in the project emphasize encapsulation, exposing only necessary methods and fields while keeping
internal details private. This principle is especially evident in the Hero and Monster classes, where health, mana, and
inventory are managed through getters and setters. In particular, combat actions are handled by the ActionStrategy
classes, which interact only with the public methods of Hero and Monster, preserving their internal state.

Extra Credit Features
Color-Coded Output: To improve visual clarity, ANSI escape codes are used for color coding. Throughout gameplay colors
make for a better playing experience.

Draft Mode for Heroes: A draft system allows players to select heroes from a randomized pool, adding an element of
choice and strategy to the game setup. This feature offers an engaging way to customize the hero team and experiment
with different combinations.

## How to compile and run
---------------------------------------------------------------------------
1. Unzip MonstersAndHeros
2. Locate the main file
3. Run the main file

## Input/Output Example
---------------------------------------------------------------------------

Choose a game to play:
1. Tic-Tac-Toe
2. Order and Chaos
3. Super Tic Tac Toe
4. Monsters and Heros
5. Display Statistics
q. Quit
4
Welcome to Monsters and Heroes!
In this game, you'll lead a team of heroes on a quest to battle monsters, collect items, and level up.
Instructions:
 - Use W, A, S, D keys to move your heroes across the 8x8 board.
 - Use Q to quit the game, I to show information, and M to enter the market.
 - When you enter a Common space, you may encounter monsters to fight.
 - Enter Market spaces (M) to buy or sell items that aid in battles.
 - Avoid Inaccessible spaces (X) — your heroes cannot pass through them.
 - Your hero party is denoted by the H on the board.
Goal: Defeat monsters, collect rewards, and see how long your heroes can survive!
Good luck, and may your heroes emerge victorious!


Enter the number of heroes to have in your party (1-3): 2
 These are your available Heroes:
Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Bran the Yassine     Warrior    100   100     700         500     600         1354    1                 7
2. Pavel the Fierce     Paladin    100   300     750         700     700         2500    1                 7
3. Maia the Noble       Warrior    100   200     750         650     700         2500    1                 7
4. Mira the Fearless    Warrior    100   400     700         800     600         2500    1                 6
5. Soren the Unyielding Paladin    100   300     750         650     700         2500    1                 7
6. Drake the Just       Sorcerer   100   1000    700         400     500         2500    1                 5
7. Talia the Cunning    Sorcerer   100   900     800         700     400         2500    1                 7
8. Elara the Just       Sorcerer   100   800     850         400     600         2500    1                 6
9. Finn the Wise        Paladin    100   500     500         500     500         2500    1                 5
10. Rhea the Fearless    Sorcerer   100   1000    700         400     500         2500    1                 5

Select hero 1 of 2:
Enter the number of the hero you'd like to recruit: 2

Remaining heroes:
Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Bran the Yassine     Warrior    100   100     700         500     600         1354    1                 7
2. Maia the Noble       Warrior    100   200     750         650     700         2500    1                 7
3. Mira the Fearless    Warrior    100   400     700         800     600         2500    1                 6
4. Soren the Unyielding Paladin    100   300     750         650     700         2500    1                 7
5. Drake the Just       Sorcerer   100   1000    700         400     500         2500    1                 5
6. Talia the Cunning    Sorcerer   100   900     800         700     400         2500    1                 7
7. Elara the Just       Sorcerer   100   800     850         400     600         2500    1                 6
8. Finn the Wise        Paladin    100   500     500         500     500         2500    1                 5
9. Rhea the Fearless    Sorcerer   100   1000    700         400     500         2500    1                 5

Select hero 2 of 2:
Enter the number of the hero you'd like to recruit: 1

Your chosen heroes:
Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Pavel the Fierce     Paladin    100   300     750         700     700         2500    1                 7
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: true)
2. Bran the Yassine     Warrior    100   100     700         500     600         1354    1                 7
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: true)

Current Position: (1, 8)
Use W, A, S, D to move, I for info, M for market, Q to quit
    1    2    3    4    5    6    7    8
  +----+----+----+----+----+----+----+----+
1 | M  | X  |    |    | M  | M  | X  | H  |
  +----+----+----+----+----+----+----+----+
2 | M  |    |    |    |    | M  |    | M  |
  +----+----+----+----+----+----+----+----+
3 | X  | M  | X  | M  | M  |    | M  | M  |
  +----+----+----+----+----+----+----+----+
4 |    |    | M  |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
5 | M  |    |    |    |    | M  |    | X  |
  +----+----+----+----+----+----+----+----+
6 |    | X  | M  | M  |    | X  |    |    |
  +----+----+----+----+----+----+----+----+
7 |    |    |    |    | X  | X  | X  |    |
  +----+----+----+----+----+----+----+----+
8 |    | X  |    |    |    |    | M  |    |
  +----+----+----+----+----+----+----+----+
s

Current Position: (2, 8)
Use W, A, S, D to move, I for info, M for market, Q to quit
    1    2    3    4    5    6    7    8
  +----+----+----+----+----+----+----+----+
1 | M  | X  |    |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
2 | M  |    |    |    |    | M  |    | H  |
  +----+----+----+----+----+----+----+----+
3 | X  | M  | X  | M  | M  |    | M  | M  |
  +----+----+----+----+----+----+----+----+
4 |    |    | M  |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
5 | M  |    |    |    |    | M  |    | X  |
  +----+----+----+----+----+----+----+----+
6 |    | X  | M  | M  |    | X  |    |    |
  +----+----+----+----+----+----+----+----+
7 |    |    |    |    | X  | X  | X  |    |
  +----+----+----+----+----+----+----+----+
8 |    | X  |    |    |    |    | M  |    |
  +----+----+----+----+----+----+----+----+
m
Items available for sale:
    1.   - Electric_Arrows (Type: LightningSpell, Damage: 650, Mana Cost: 200, Level: 5, Price: 550)
    2.   - Wizard_Shield (Type: Armor, Damage Reduction: 1500, Level: 10, Price: 1200)
    3.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150)
    4.   - TSwords (Type: Weapon, Damage: 1600, Hands Required: 2, Level: 8, Price: 1400)
    5.   - Magic_Potion (Type: Potion, Effect Ammount: 100, Stat Effected: Mana, Level: 2, Price: 350)

Choose a hero to enter the market:

Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Pavel the Fierce     Paladin    100   300     750         700     700         2500    1                 7
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: true)
2. Bran the Yassine     Warrior    100   100     700         500     600         1354    1                 7
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: true)

Enter the hero number to enter the market or 0 to leave: 1

Welcome to the market, Pavel the Fierce!
Items available for sale:
    1.   - Electric_Arrows (Type: LightningSpell, Damage: 650, Mana Cost: 200, Level: 5, Price: 550)
    2.   - Wizard_Shield (Type: Armor, Damage Reduction: 1500, Level: 10, Price: 1200)
    3.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150)
    4.   - TSwords (Type: Weapon, Damage: 1600, Hands Required: 2, Level: 8, Price: 1400)
    5.   - Magic_Potion (Type: Potion, Effect Ammount: 100, Stat Effected: Mana, Level: 2, Price: 350)

Hero's current money: 2500
Choose an action:
1. Buy an item
2. Sell an item
0. Exit market for this hero
Enter your choice: 1

Choose an item to buy (enter the item number or 0 to cancel):
Items available for sale:
    1.   - Electric_Arrows (Type: LightningSpell, Damage: 650, Mana Cost: 200, Level: 5, Price: 550)
    2.   - Wizard_Shield (Type: Armor, Damage Reduction: 1500, Level: 10, Price: 1200)
    3.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150)
    4.   - TSwords (Type: Weapon, Damage: 1600, Hands Required: 2, Level: 8, Price: 1400)
    5.   - Magic_Potion (Type: Potion, Effect Ammount: 100, Stat Effected: Mana, Level: 2, Price: 350)
3

Pavel the Fierce has bought Platinum_Shield for 150 coins.

Welcome to the market, Pavel the Fierce!
Items available for sale:
    1.   - Electric_Arrows (Type: LightningSpell, Damage: 650, Mana Cost: 200, Level: 5, Price: 550)
    2.   - Wizard_Shield (Type: Armor, Damage Reduction: 1500, Level: 10, Price: 1200)
    3.   - TSwords (Type: Weapon, Damage: 1600, Hands Required: 2, Level: 8, Price: 1400)
    4.   - Magic_Potion (Type: Potion, Effect Ammount: 100, Stat Effected: Mana, Level: 2, Price: 350)

Hero's current money: 2350
Choose an action:
1. Buy an item
2. Sell an item
0. Exit market for this hero
Enter your choice: 0
Items available for sale:
    1.   - Electric_Arrows (Type: LightningSpell, Damage: 650, Mana Cost: 200, Level: 5, Price: 550)
    2.   - Wizard_Shield (Type: Armor, Damage Reduction: 1500, Level: 10, Price: 1200)
    3.   - TSwords (Type: Weapon, Damage: 1600, Hands Required: 2, Level: 8, Price: 1400)
    4.   - Magic_Potion (Type: Potion, Effect Ammount: 100, Stat Effected: Mana, Level: 2, Price: 350)

Choose a hero to enter the market:

Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Pavel the Fierce     Paladin    100   300     750         700     700         2350    1                 7
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: true)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: false)
2. Bran the Yassine     Warrior    100   100     700         500     600         1354    1                 7
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: true)

Enter the hero number to enter the market or 0 to leave: 0

Current Position: (2, 8)
Use W, A, S, D to move, I for info, M for market, Q to quit
    1    2    3    4    5    6    7    8
  +----+----+----+----+----+----+----+----+
1 | M  | X  |    |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
2 | M  |    |    |    |    | M  |    | H  |
  +----+----+----+----+----+----+----+----+
3 | X  | M  | X  | M  | M  |    | M  | M  |
  +----+----+----+----+----+----+----+----+
4 |    |    | M  |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
5 | M  |    |    |    |    | M  |    | X  |
  +----+----+----+----+----+----+----+----+
6 |    | X  | M  | M  |    | X  |    |    |
  +----+----+----+----+----+----+----+----+
7 |    |    |    |    | X  | X  | X  |    |
  +----+----+----+----+----+----+----+----+
8 |    | X  |    |    |    |    | M  |    |
  +----+----+----+----+----+----+----+----+
s

Current Position: (3, 8)
Use W, A, S, D to move, I for info, M for market, Q to quit
    1    2    3    4    5    6    7    8
  +----+----+----+----+----+----+----+----+
1 | M  | X  |    |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
2 | M  |    |    |    |    | M  |    | M  |
  +----+----+----+----+----+----+----+----+
3 | X  | M  | X  | M  | M  |    | M  | H  |
  +----+----+----+----+----+----+----+----+
4 |    |    | M  |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
5 | M  |    |    |    |    | M  |    | X  |
  +----+----+----+----+----+----+----+----+
6 |    | X  | M  | M  |    | X  |    |    |
  +----+----+----+----+----+----+----+----+
7 |    |    |    |    | X  | X  | X  |    |
  +----+----+----+----+----+----+----+----+
8 |    | X  |    |    |    |    | M  |    |
  +----+----+----+----+----+----+----+----+
m
Items available for sale:
    1.   - Full_Body_Armor (Type: Armor, Damage Reduction: 1100, Level: 8, Price: 1000)
    2.   - TSwords (Type: Weapon, Damage: 1600, Hands Required: 2, Level: 8, Price: 1400)
    3.   - Breastplate (Type: Armor, Damage Reduction: 600, Level: 3, Price: 350)
    4.   - Bow (Type: Weapon, Damage: 600, Hands Required: 2, Level: 2, Price: 300)
    5.   - Wizard_Shield (Type: Armor, Damage Reduction: 1500, Level: 10, Price: 1200)
    6.   - TSwords (Type: Weapon, Damage: 1600, Hands Required: 2, Level: 8, Price: 1400)
    7.   - Strength_Potion (Type: Potion, Effect Ammount: 75, Stat Effected: Strength, Level: 1, Price: 200)
    8.   - Full_Body_Armor (Type: Armor, Damage Reduction: 1100, Level: 8, Price: 1000)

Choose a hero to enter the market:

Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Pavel the Fierce     Paladin    100   300     750         700     700         2350    1                 7
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: true)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: false)
2. Bran the Yassine     Warrior    100   100     700         500     600         1354    1                 7
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: true)

Enter the hero number to enter the market or 0 to leave: 1

Welcome to the market, Pavel the Fierce!
Items available for sale:
    1.   - Full_Body_Armor (Type: Armor, Damage Reduction: 1100, Level: 8, Price: 1000)
    2.   - TSwords (Type: Weapon, Damage: 1600, Hands Required: 2, Level: 8, Price: 1400)
    3.   - Breastplate (Type: Armor, Damage Reduction: 600, Level: 3, Price: 350)
    4.   - Bow (Type: Weapon, Damage: 600, Hands Required: 2, Level: 2, Price: 300)
    5.   - Wizard_Shield (Type: Armor, Damage Reduction: 1500, Level: 10, Price: 1200)
    6.   - TSwords (Type: Weapon, Damage: 1600, Hands Required: 2, Level: 8, Price: 1400)
    7.   - Strength_Potion (Type: Potion, Effect Ammount: 75, Stat Effected: Strength, Level: 1, Price: 200)
    8.   - Full_Body_Armor (Type: Armor, Damage Reduction: 1100, Level: 8, Price: 1000)

Hero's current money: 2350
Choose an action:
1. Buy an item
2. Sell an item
0. Exit market for this hero
Enter your choice: 1

Choose an item to buy (enter the item number or 0 to cancel):
Items available for sale:
    1.   - Full_Body_Armor (Type: Armor, Damage Reduction: 1100, Level: 8, Price: 1000)
    2.   - TSwords (Type: Weapon, Damage: 1600, Hands Required: 2, Level: 8, Price: 1400)
    3.   - Breastplate (Type: Armor, Damage Reduction: 600, Level: 3, Price: 350)
    4.   - Bow (Type: Weapon, Damage: 600, Hands Required: 2, Level: 2, Price: 300)
    5.   - Wizard_Shield (Type: Armor, Damage Reduction: 1500, Level: 10, Price: 1200)
    6.   - TSwords (Type: Weapon, Damage: 1600, Hands Required: 2, Level: 8, Price: 1400)
    7.   - Strength_Potion (Type: Potion, Effect Ammount: 75, Stat Effected: Strength, Level: 1, Price: 200)
    8.   - Full_Body_Armor (Type: Armor, Damage Reduction: 1100, Level: 8, Price: 1000)
7

Pavel the Fierce has bought Strength_Potion for 200 coins.

Welcome to the market, Pavel the Fierce!
Items available for sale:
    1.   - Full_Body_Armor (Type: Armor, Damage Reduction: 1100, Level: 8, Price: 1000)
    2.   - TSwords (Type: Weapon, Damage: 1600, Hands Required: 2, Level: 8, Price: 1400)
    3.   - Breastplate (Type: Armor, Damage Reduction: 600, Level: 3, Price: 350)
    4.   - Bow (Type: Weapon, Damage: 600, Hands Required: 2, Level: 2, Price: 300)
    5.   - Wizard_Shield (Type: Armor, Damage Reduction: 1500, Level: 10, Price: 1200)
    6.   - TSwords (Type: Weapon, Damage: 1600, Hands Required: 2, Level: 8, Price: 1400)
    7.   - Full_Body_Armor (Type: Armor, Damage Reduction: 1100, Level: 8, Price: 1000)

Hero's current money: 2150
Choose an action:
1. Buy an item
2. Sell an item
0. Exit market for this hero
Enter your choice: 0
Items available for sale:
    1.   - Full_Body_Armor (Type: Armor, Damage Reduction: 1100, Level: 8, Price: 1000)
    2.   - TSwords (Type: Weapon, Damage: 1600, Hands Required: 2, Level: 8, Price: 1400)
    3.   - Breastplate (Type: Armor, Damage Reduction: 600, Level: 3, Price: 350)
    4.   - Bow (Type: Weapon, Damage: 600, Hands Required: 2, Level: 2, Price: 300)
    5.   - Wizard_Shield (Type: Armor, Damage Reduction: 1500, Level: 10, Price: 1200)
    6.   - TSwords (Type: Weapon, Damage: 1600, Hands Required: 2, Level: 8, Price: 1400)
    7.   - Full_Body_Armor (Type: Armor, Damage Reduction: 1100, Level: 8, Price: 1000)

Choose a hero to enter the market:

Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Pavel the Fierce     Paladin    100   300     750         700     700         2150    1                 7
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: true)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: false)
    3.   - Strength_Potion (Type: Potion, Effect Ammount: 75, Stat Effected: Strength, Level: 1, Price: 200)
2. Bran the Yassine     Warrior    100   100     700         500     600         1354    1                 7
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: true)

Enter the hero number to enter the market or 0 to leave: 0

Current Position: (3, 8)
Use W, A, S, D to move, I for info, M for market, Q to quit
    1    2    3    4    5    6    7    8
  +----+----+----+----+----+----+----+----+
1 | M  | X  |    |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
2 | M  |    |    |    |    | M  |    | M  |
  +----+----+----+----+----+----+----+----+
3 | X  | M  | X  | M  | M  |    | M  | H  |
  +----+----+----+----+----+----+----+----+
4 |    |    | M  |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
5 | M  |    |    |    |    | M  |    | X  |
  +----+----+----+----+----+----+----+----+
6 |    | X  | M  | M  |    | X  |    |    |
  +----+----+----+----+----+----+----+----+
7 |    |    |    |    | X  | X  | X  |    |
  +----+----+----+----+----+----+----+----+
8 |    | X  |    |    |    |    | M  |    |
  +----+----+----+----+----+----+----+----+
a

Current Position: (3, 7)
Use W, A, S, D to move, I for info, M for market, Q to quit
    1    2    3    4    5    6    7    8
  +----+----+----+----+----+----+----+----+
1 | M  | X  |    |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
2 | M  |    |    |    |    | M  |    | M  |
  +----+----+----+----+----+----+----+----+
3 | X  | M  | X  | M  | M  |    | H  | M  |
  +----+----+----+----+----+----+----+----+
4 |    |    | M  |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
5 | M  |    |    |    |    | M  |    | X  |
  +----+----+----+----+----+----+----+----+
6 |    | X  | M  | M  |    | X  |    |    |
  +----+----+----+----+----+----+----+----+
7 |    |    |    |    | X  | X  | X  |    |
  +----+----+----+----+----+----+----+----+
8 |    | X  |    |    |    |    | M  |    |
  +----+----+----+----+----+----+----+----+
m
Items available for sale:
    1.   - Axe (Type: Weapon, Damage: 850, Hands Required: 1, Level: 5, Price: 550)
    2.   - Thunder_Blast (Type: LightningSpell, Damage: 950, Mana Cost: 400, Level: 4, Price: 750)
    3.   - Breath_of_Fire (Type: FireSpell, Damage: 450, Mana Cost: 100, Level: 1, Price: 350)
    4.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150)

Choose a hero to enter the market:

Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Pavel the Fierce     Paladin    100   300     750         700     700         2150    1                 7
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: true)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: false)
    3.   - Strength_Potion (Type: Potion, Effect Ammount: 75, Stat Effected: Strength, Level: 1, Price: 200)
2. Bran the Yassine     Warrior    100   100     700         500     600         1354    1                 7
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: true)

Enter the hero number to enter the market or 0 to leave: 2

Welcome to the market, Bran the Yassine!
Items available for sale:
    1.   - Axe (Type: Weapon, Damage: 850, Hands Required: 1, Level: 5, Price: 550)
    2.   - Thunder_Blast (Type: LightningSpell, Damage: 950, Mana Cost: 400, Level: 4, Price: 750)
    3.   - Breath_of_Fire (Type: FireSpell, Damage: 450, Mana Cost: 100, Level: 1, Price: 350)
    4.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150)

Hero's current money: 1354
Choose an action:
1. Buy an item
2. Sell an item
0. Exit market for this hero
Enter your choice: 1

Choose an item to buy (enter the item number or 0 to cancel):
Items available for sale:
    1.   - Axe (Type: Weapon, Damage: 850, Hands Required: 1, Level: 5, Price: 550)
    2.   - Thunder_Blast (Type: LightningSpell, Damage: 950, Mana Cost: 400, Level: 4, Price: 750)
    3.   - Breath_of_Fire (Type: FireSpell, Damage: 450, Mana Cost: 100, Level: 1, Price: 350)
    4.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150)
3

Bran the Yassine has bought Breath_of_Fire for 350 coins.

Welcome to the market, Bran the Yassine!
Items available for sale:
    1.   - Axe (Type: Weapon, Damage: 850, Hands Required: 1, Level: 5, Price: 550)
    2.   - Thunder_Blast (Type: LightningSpell, Damage: 950, Mana Cost: 400, Level: 4, Price: 750)
    3.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150)

Hero's current money: 1004
Choose an action:
1. Buy an item
2. Sell an item
0. Exit market for this hero
Enter your choice: 1

Choose an item to buy (enter the item number or 0 to cancel):
Items available for sale:
    1.   - Axe (Type: Weapon, Damage: 850, Hands Required: 1, Level: 5, Price: 550)
    2.   - Thunder_Blast (Type: LightningSpell, Damage: 950, Mana Cost: 400, Level: 4, Price: 750)
    3.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150)
3

Bran the Yassine has bought Platinum_Shield for 150 coins.

Welcome to the market, Bran the Yassine!
Items available for sale:
    1.   - Axe (Type: Weapon, Damage: 850, Hands Required: 1, Level: 5, Price: 550)
    2.   - Thunder_Blast (Type: LightningSpell, Damage: 950, Mana Cost: 400, Level: 4, Price: 750)

Hero's current money: 854
Choose an action:
1. Buy an item
2. Sell an item
0. Exit market for this hero
Enter your choice: 0
Items available for sale:
    1.   - Axe (Type: Weapon, Damage: 850, Hands Required: 1, Level: 5, Price: 550)
    2.   - Thunder_Blast (Type: LightningSpell, Damage: 950, Mana Cost: 400, Level: 4, Price: 750)

Choose a hero to enter the market:

Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Pavel the Fierce     Paladin    100   300     750         700     700         2150    1                 7
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: true)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: false)
    3.   - Strength_Potion (Type: Potion, Effect Ammount: 75, Stat Effected: Strength, Level: 1, Price: 200)
2. Bran the Yassine     Warrior    100   100     700         500     600         854     1                 7
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: true)
    2.   - Breath_of_Fire (Type: FireSpell, Damage: 450, Mana Cost: 100, Level: 1, Price: 350)
    3.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: false)

Enter the hero number to enter the market or 0 to leave: 0

Current Position: (3, 7)
Use W, A, S, D to move, I for info, M for market, Q to quit
    1    2    3    4    5    6    7    8
  +----+----+----+----+----+----+----+----+
1 | M  | X  |    |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
2 | M  |    |    |    |    | M  |    | M  |
  +----+----+----+----+----+----+----+----+
3 | X  | M  | X  | M  | M  |    | H  | M  |
  +----+----+----+----+----+----+----+----+
4 |    |    | M  |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
5 | M  |    |    |    |    | M  |    | X  |
  +----+----+----+----+----+----+----+----+
6 |    | X  | M  | M  |    | X  |    |    |
  +----+----+----+----+----+----+----+----+
7 |    |    |    |    | X  | X  | X  |    |
  +----+----+----+----+----+----+----+----+
8 |    | X  |    |    |    |    | M  |    |
  +----+----+----+----+----+----+----+----+
a
No monsters encountered this time.

Current Position: (3, 6)
Use W, A, S, D to move, I for info, M for market, Q to quit
    1    2    3    4    5    6    7    8
  +----+----+----+----+----+----+----+----+
1 | M  | X  |    |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
2 | M  |    |    |    |    | M  |    | M  |
  +----+----+----+----+----+----+----+----+
3 | X  | M  | X  | M  | M  | H  | M  | M  |
  +----+----+----+----+----+----+----+----+
4 |    |    | M  |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
5 | M  |    |    |    |    | M  |    | X  |
  +----+----+----+----+----+----+----+----+
6 |    | X  | M  | M  |    | X  |    |    |
  +----+----+----+----+----+----+----+----+
7 |    |    |    |    | X  | X  | X  |    |
  +----+----+----+----+----+----+----+----+
8 |    | X  |    |    |    |    | M  |    |
  +----+----+----+----+----+----+----+----+
s

Current Position: (4, 6)
Use W, A, S, D to move, I for info, M for market, Q to quit
    1    2    3    4    5    6    7    8
  +----+----+----+----+----+----+----+----+
1 | M  | X  |    |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
2 | M  |    |    |    |    | M  |    | M  |
  +----+----+----+----+----+----+----+----+
3 | X  | M  | X  | M  | M  |    | M  | M  |
  +----+----+----+----+----+----+----+----+
4 |    |    | M  |    | M  | H  | X  |    |
  +----+----+----+----+----+----+----+----+
5 | M  |    |    |    |    | M  |    | X  |
  +----+----+----+----+----+----+----+----+
6 |    | X  | M  | M  |    | X  |    |    |
  +----+----+----+----+----+----+----+----+
7 |    |    |    |    | X  | X  | X  |    |
  +----+----+----+----+----+----+----+----+
8 |    | X  |    |    |    |    | M  |    |
  +----+----+----+----+----+----+----+----+
m
Items available for sale:
    1.   - Frost_Blizzard (Type: IceSpell, Damage: 850, Mana Cost: 350, Level: 5, Price: 750)
    2.   - Luck_Elixir (Type: Potion, Effect Ammount: 65, Stat Effected: Agility, Level: 4, Price: 500)
    3.   - Dagger (Type: Weapon, Damage: 350, Hands Required: 1, Level: 1, Price: 200)
    4.   - Sword (Type: Weapon, Damage: 800, Hands Required: 1, Level: 1, Price: 500)

Choose a hero to enter the market:

Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Pavel the Fierce     Paladin    100   300     750         700     700         2150    1                 7
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: true)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: false)
    3.   - Strength_Potion (Type: Potion, Effect Ammount: 75, Stat Effected: Strength, Level: 1, Price: 200)
2. Bran the Yassine     Warrior    100   100     700         500     600         854     1                 7
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: true)
    2.   - Breath_of_Fire (Type: FireSpell, Damage: 450, Mana Cost: 100, Level: 1, Price: 350)
    3.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: false)

Enter the hero number to enter the market or 0 to leave: 1

Welcome to the market, Pavel the Fierce!
Items available for sale:
    1.   - Frost_Blizzard (Type: IceSpell, Damage: 850, Mana Cost: 350, Level: 5, Price: 750)
    2.   - Luck_Elixir (Type: Potion, Effect Ammount: 65, Stat Effected: Agility, Level: 4, Price: 500)
    3.   - Dagger (Type: Weapon, Damage: 350, Hands Required: 1, Level: 1, Price: 200)
    4.   - Sword (Type: Weapon, Damage: 800, Hands Required: 1, Level: 1, Price: 500)

Hero's current money: 2150
Choose an action:
1. Buy an item
2. Sell an item
0. Exit market for this hero
Enter your choice: 1

Choose an item to buy (enter the item number or 0 to cancel):
Items available for sale:
    1.   - Frost_Blizzard (Type: IceSpell, Damage: 850, Mana Cost: 350, Level: 5, Price: 750)
    2.   - Luck_Elixir (Type: Potion, Effect Ammount: 65, Stat Effected: Agility, Level: 4, Price: 500)
    3.   - Dagger (Type: Weapon, Damage: 350, Hands Required: 1, Level: 1, Price: 200)
    4.   - Sword (Type: Weapon, Damage: 800, Hands Required: 1, Level: 1, Price: 500)
3

Pavel the Fierce has bought Dagger for 200 coins.

Welcome to the market, Pavel the Fierce!
Items available for sale:
    1.   - Frost_Blizzard (Type: IceSpell, Damage: 850, Mana Cost: 350, Level: 5, Price: 750)
    2.   - Luck_Elixir (Type: Potion, Effect Ammount: 65, Stat Effected: Agility, Level: 4, Price: 500)
    3.   - Sword (Type: Weapon, Damage: 800, Hands Required: 1, Level: 1, Price: 500)

Hero's current money: 1950
Choose an action:
1. Buy an item
2. Sell an item
0. Exit market for this hero
Enter your choice: 0
Items available for sale:
    1.   - Frost_Blizzard (Type: IceSpell, Damage: 850, Mana Cost: 350, Level: 5, Price: 750)
    2.   - Luck_Elixir (Type: Potion, Effect Ammount: 65, Stat Effected: Agility, Level: 4, Price: 500)
    3.   - Sword (Type: Weapon, Damage: 800, Hands Required: 1, Level: 1, Price: 500)

Choose a hero to enter the market:

Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Pavel the Fierce     Paladin    100   300     750         700     700         1950    1                 7
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: true)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: false)
    3.   - Strength_Potion (Type: Potion, Effect Ammount: 75, Stat Effected: Strength, Level: 1, Price: 200)
    4.   - Dagger (Type: Weapon, Damage: 350, Hands Required: 1, Level: 1, Price: 200, Equipped: false)
2. Bran the Yassine     Warrior    100   100     700         500     600         854     1                 7
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: true)
    2.   - Breath_of_Fire (Type: FireSpell, Damage: 450, Mana Cost: 100, Level: 1, Price: 350)
    3.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: false)

Enter the hero number to enter the market or 0 to leave: 2

Welcome to the market, Bran the Yassine!
Items available for sale:
    1.   - Frost_Blizzard (Type: IceSpell, Damage: 850, Mana Cost: 350, Level: 5, Price: 750)
    2.   - Luck_Elixir (Type: Potion, Effect Ammount: 65, Stat Effected: Agility, Level: 4, Price: 500)
    3.   - Sword (Type: Weapon, Damage: 800, Hands Required: 1, Level: 1, Price: 500)

Hero's current money: 854
Choose an action:
1. Buy an item
2. Sell an item
0. Exit market for this hero
Enter your choice: 1

Choose an item to buy (enter the item number or 0 to cancel):
Items available for sale:
    1.   - Frost_Blizzard (Type: IceSpell, Damage: 850, Mana Cost: 350, Level: 5, Price: 750)
    2.   - Luck_Elixir (Type: Potion, Effect Ammount: 65, Stat Effected: Agility, Level: 4, Price: 500)
    3.   - Sword (Type: Weapon, Damage: 800, Hands Required: 1, Level: 1, Price: 500)
3

Bran the Yassine has bought Sword for 500 coins.

Welcome to the market, Bran the Yassine!
Items available for sale:
    1.   - Frost_Blizzard (Type: IceSpell, Damage: 850, Mana Cost: 350, Level: 5, Price: 750)
    2.   - Luck_Elixir (Type: Potion, Effect Ammount: 65, Stat Effected: Agility, Level: 4, Price: 500)

Hero's current money: 354
Choose an action:
1. Buy an item
2. Sell an item
0. Exit market for this hero
Enter your choice: 0
Items available for sale:
    1.   - Frost_Blizzard (Type: IceSpell, Damage: 850, Mana Cost: 350, Level: 5, Price: 750)
    2.   - Luck_Elixir (Type: Potion, Effect Ammount: 65, Stat Effected: Agility, Level: 4, Price: 500)

Choose a hero to enter the market:

Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Pavel the Fierce     Paladin    100   300     750         700     700         1950    1                 7
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: true)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: false)
    3.   - Strength_Potion (Type: Potion, Effect Ammount: 75, Stat Effected: Strength, Level: 1, Price: 200)
    4.   - Dagger (Type: Weapon, Damage: 350, Hands Required: 1, Level: 1, Price: 200, Equipped: false)
2. Bran the Yassine     Warrior    100   100     700         500     600         354     1                 7
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: true)
    2.   - Breath_of_Fire (Type: FireSpell, Damage: 450, Mana Cost: 100, Level: 1, Price: 350)
    3.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: false)
    4.   - Sword (Type: Weapon, Damage: 800, Hands Required: 1, Level: 1, Price: 500, Equipped: false)

Enter the hero number to enter the market or 0 to leave: 0

Current Position: (4, 6)
Use W, A, S, D to move, I for info, M for market, Q to quit
    1    2    3    4    5    6    7    8
  +----+----+----+----+----+----+----+----+
1 | M  | X  |    |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
2 | M  |    |    |    |    | M  |    | M  |
  +----+----+----+----+----+----+----+----+
3 | X  | M  | X  | M  | M  |    | M  | M  |
  +----+----+----+----+----+----+----+----+
4 |    |    | M  |    | M  | H  | X  |    |
  +----+----+----+----+----+----+----+----+
5 | M  |    |    |    |    | M  |    | X  |
  +----+----+----+----+----+----+----+----+
6 |    | X  | M  | M  |    | X  |    |    |
  +----+----+----+----+----+----+----+----+
7 |    |    |    |    | X  | X  | X  |    |
  +----+----+----+----+----+----+----+----+
8 |    | X  |    |    |    |    | M  |    |
  +----+----+----+----+----+----+----+----+
s

Current Position: (5, 6)
Use W, A, S, D to move, I for info, M for market, Q to quit
    1    2    3    4    5    6    7    8
  +----+----+----+----+----+----+----+----+
1 | M  | X  |    |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
2 | M  |    |    |    |    | M  |    | M  |
  +----+----+----+----+----+----+----+----+
3 | X  | M  | X  | M  | M  |    | M  | M  |
  +----+----+----+----+----+----+----+----+
4 |    |    | M  |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
5 | M  |    |    |    |    | H  |    | X  |
  +----+----+----+----+----+----+----+----+
6 |    | X  | M  | M  |    | X  |    |    |
  +----+----+----+----+----+----+----+----+
7 |    |    |    |    | X  | X  | X  |    |
  +----+----+----+----+----+----+----+----+
8 |    | X  |    |    |    |    | M  |    |
  +----+----+----+----+----+----+----+----+
a

								A battle begins! You encountered monsters!


											-----HEROES-----

Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Pavel the Fierce     Paladin    100   300     750         700     700         1950    1                 7
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: true)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: false)
    3.   - Strength_Potion (Type: Potion, Effect Ammount: 75, Stat Effected: Strength, Level: 1, Price: 200)
    4.   - Dagger (Type: Weapon, Damage: 350, Hands Required: 1, Level: 1, Price: 200, Equipped: false)
2. Bran the Yassine     Warrior    100   100     700         500     600         354     1                 7
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: true)
    2.   - Breath_of_Fire (Type: FireSpell, Damage: 450, Mana Cost: 100, Level: 1, Price: 350)
    3.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: false)
    4.   - Sword (Type: Weapon, Damage: 800, Hands Required: 1, Level: 1, Price: 500, Equipped: false)

											-----MONSTERS-----

Name							HP	Damage	Defense		Dodge Chance	Level
--------------------------------------------------------------------------------------------
1. TheScaleless The Dragon     95    126     107           15.00          1
2. Casper The Spirit           95    45      45            25.00          1

									------HEROES BATTLE TURN------

Pavel the Fierce, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 4

You have 0/2 hands available.

Equipment Menu:
1. Equip Item
2. Unequip Item
3. Leave Equipment Menu
Choose an option (1-3): 2
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: true)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: false)
    3.   - Strength_Potion (Type: Potion, Effect Ammount: 75, Stat Effected: Strength, Level: 1, Price: 200)
    4.   - Dagger (Type: Weapon, Damage: 350, Hands Required: 1, Level: 1, Price: 200, Equipped: false)
Enter the item number to unequip: 1
Bare Hands unequipped.

Equipment Menu:
1. Equip Item
2. Unequip Item
3. Leave Equipment Menu
Choose an option (1-3): 1
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: false)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: false)
    3.   - Strength_Potion (Type: Potion, Effect Ammount: 75, Stat Effected: Strength, Level: 1, Price: 200)
    4.   - Dagger (Type: Weapon, Damage: 350, Hands Required: 1, Level: 1, Price: 200, Equipped: false)
Enter the item number to equip: 4
Dagger equipped as a weapon. 1 hands available.

Equipment Menu:
1. Equip Item
2. Unequip Item
3. Leave Equipment Menu
Choose an option (1-3): 1
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: false)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: false)
    3.   - Strength_Potion (Type: Potion, Effect Ammount: 75, Stat Effected: Strength, Level: 1, Price: 200)
    4.   - Dagger (Type: Weapon, Damage: 350, Hands Required: 1, Level: 1, Price: 200, Equipped: true)
Enter the item number to equip: 2
Platinum_Shield equipped as armor.

Equipment Menu:
1. Equip Item
2. Unequip Item
3. Leave Equipment Menu
Choose an option (1-3): 3

Pavel the Fierce, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 3
Strength_Potion removed from inventory.

Pavel the Fierce uses Strength_Potion and Strength gains 75 points.

Bran the Yassine, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 4

You have 0/2 hands available.

Equipment Menu:
1. Equip Item
2. Unequip Item
3. Leave Equipment Menu
Choose an option (1-3): 2
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: true)
    2.   - Breath_of_Fire (Type: FireSpell, Damage: 450, Mana Cost: 100, Level: 1, Price: 350)
    3.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: false)
    4.   - Sword (Type: Weapon, Damage: 800, Hands Required: 1, Level: 1, Price: 500, Equipped: false)
Enter the item number to unequip: 1
Bare Hands unequipped.

Equipment Menu:
1. Equip Item
2. Unequip Item
3. Leave Equipment Menu
Choose an option (1-3): 1
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: false)
    2.   - Breath_of_Fire (Type: FireSpell, Damage: 450, Mana Cost: 100, Level: 1, Price: 350)
    3.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: false)
    4.   - Sword (Type: Weapon, Damage: 800, Hands Required: 1, Level: 1, Price: 500, Equipped: false)
Enter the item number to equip: 3
Platinum_Shield equipped as armor.

Equipment Menu:
1. Equip Item
2. Unequip Item
3. Leave Equipment Menu
Choose an option (1-3): 1
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: false)
    2.   - Breath_of_Fire (Type: FireSpell, Damage: 450, Mana Cost: 100, Level: 1, Price: 350)
    3.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: true)
    4.   - Sword (Type: Weapon, Damage: 800, Hands Required: 1, Level: 1, Price: 500, Equipped: false)
Enter the item number to equip: 4
Sword equipped as a weapon. 1 hands available.

Equipment Menu:
1. Equip Item
2. Unequip Item
3. Leave Equipment Menu
Choose an option (1-3): 3

Bran the Yassine, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 2

Bran the Yassine uses Breath_of_Fire spell and TheScaleless The Dragon looses 450 defence points.
Breath_of_Fire removed from inventory.

									------MONSTERS BATTLE TURN------

TheScaleless The Dragon attacks Bran the Yassine!  Bran the Yassine took 0 damage, and his armor blocked 200 damage!
Casper The Spirit attacks Bran the Yassine!  Bran the Yassine took 0 damage, and his armor blocked 200 damage!

									------HEROES BATTLE TURN------

Pavel the Fierce, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 1

Dagger is one-handed, but wielded with both hands, increasing damage by 75%!
Pavel the Fierce attacked TheScaleless The Dragon for 101 damage with Dagger TheScaleless The Dragon defended 0 so 101 total damage done

TheScaleless The Dragon has been killed!


Bran the Yassine, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 1

Sword is one-handed, but wielded with both hands, increasing damage by 75%!
Bran the Yassine attacked Casper The Spirit for 131 damage with Sword Casper The Spirit defended 6 so 125 total damage done

Casper The Spirit has been killed!

The heroes have triumphed! Gaining rewards...

Current Position: (5, 5)
Use W, A, S, D to move, I for info, M for market, Q to quit
    1    2    3    4    5    6    7    8
  +----+----+----+----+----+----+----+----+
1 | M  | X  |    |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
2 | M  |    |    |    |    | M  |    | M  |
  +----+----+----+----+----+----+----+----+
3 | X  | M  | X  | M  | M  |    | M  | M  |
  +----+----+----+----+----+----+----+----+
4 |    |    | M  |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
5 | M  |    |    |    | H  | M  |    | X  |
  +----+----+----+----+----+----+----+----+
6 |    | X  | M  | M  |    | X  |    |    |
  +----+----+----+----+----+----+----+----+
7 |    |    |    |    | X  | X  | X  |    |
  +----+----+----+----+----+----+----+----+
8 |    | X  |    |    |    |    | M  |    |
  +----+----+----+----+----+----+----+----+
a
No monsters encountered this time.

Current Position: (5, 4)
Use W, A, S, D to move, I for info, M for market, Q to quit
    1    2    3    4    5    6    7    8
  +----+----+----+----+----+----+----+----+
1 | M  | X  |    |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
2 | M  |    |    |    |    | M  |    | M  |
  +----+----+----+----+----+----+----+----+
3 | X  | M  | X  | M  | M  |    | M  | M  |
  +----+----+----+----+----+----+----+----+
4 |    |    | M  |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
5 | M  |    |    | H  |    | M  |    | X  |
  +----+----+----+----+----+----+----+----+
6 |    | X  | M  | M  |    | X  |    |    |
  +----+----+----+----+----+----+----+----+
7 |    |    |    |    | X  | X  | X  |    |
  +----+----+----+----+----+----+----+----+
8 |    | X  |    |    |    |    | M  |    |
  +----+----+----+----+----+----+----+----+
a
No monsters encountered this time.

Current Position: (5, 3)
Use W, A, S, D to move, I for info, M for market, Q to quit
    1    2    3    4    5    6    7    8
  +----+----+----+----+----+----+----+----+
1 | M  | X  |    |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
2 | M  |    |    |    |    | M  |    | M  |
  +----+----+----+----+----+----+----+----+
3 | X  | M  | X  | M  | M  |    | M  | M  |
  +----+----+----+----+----+----+----+----+
4 |    |    | M  |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
5 | M  |    | H  |    |    | M  |    | X  |
  +----+----+----+----+----+----+----+----+
6 |    | X  | M  | M  |    | X  |    |    |
  +----+----+----+----+----+----+----+----+
7 |    |    |    |    | X  | X  | X  |    |
  +----+----+----+----+----+----+----+----+
8 |    | X  |    |    |    |    | M  |    |
  +----+----+----+----+----+----+----+----+
a

								A battle begins! You encountered monsters!


											-----HEROES-----

Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Pavel the Fierce     Paladin    115   346     825         700     700         2150    1                 9
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: false)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: true)
    3.   - Dagger (Type: Weapon, Damage: 350, Hands Required: 1, Level: 1, Price: 200, Equipped: true)
2. Bran the Yassine     Warrior    115   0       700         500     600         554     1                 9
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: false)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: true)
    3.   - Sword (Type: Weapon, Damage: 800, Hands Required: 1, Level: 1, Price: 500, Equipped: true)

											-----MONSTERS-----

Name							HP	Damage	Defense		Dodge Chance	Level
--------------------------------------------------------------------------------------------
1. Phaarthurnax The Dragon     95    125     147           14.00          1
2. FallenAngel The Spirit      95    201     176           14.00          1

									------HEROES BATTLE TURN------

Pavel the Fierce, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 1

Dagger is one-handed, but wielded with both hands, increasing damage by 75%!
Pavel the Fierce attacked FallenAngel The Spirit for 101 damage with Dagger FallenAngel The Spirit defended 26 so 75 total damage done
FallenAngel The Spirit dodged the attack! No damage done.

Bran the Yassine, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 1

Sword is one-handed, but wielded with both hands, increasing damage by 75%!
Bran the Yassine attacked FallenAngel The Spirit for 131 damage with Sword FallenAngel The Spirit defended 26 so 105 total damage done

FallenAngel The Spirit has been killed!


									------MONSTERS BATTLE TURN------

Phaarthurnax The Dragon attacks Bran the Yassine!  Bran the Yassine dodged the attack completely!

									------HEROES BATTLE TURN------

Pavel the Fierce, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 1

Dagger is one-handed, but wielded with both hands, increasing damage by 75%!
Pavel the Fierce attacked Phaarthurnax The Dragon for 101 damage with Dagger Phaarthurnax The Dragon defended 22 so 79 total damage done

Bran the Yassine, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 1

Sword is one-handed, but wielded with both hands, increasing damage by 75%!
Bran the Yassine attacked Phaarthurnax The Dragon for 131 damage with Sword Phaarthurnax The Dragon defended 22 so 109 total damage done

Phaarthurnax The Dragon has been killed!

The heroes have triumphed! Gaining rewards...
Leveling up Pavel the Fierce to level 2 because of their 11 experience points. Now at HP 200.
Leveling up Bran the Yassine to level 2 because of their 11 experience points. Now at HP 200.

Current Position: (5, 2)
Use W, A, S, D to move, I for info, M for market, Q to quit
    1    2    3    4    5    6    7    8
  +----+----+----+----+----+----+----+----+
1 | M  | X  |    |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
2 | M  |    |    |    |    | M  |    | M  |
  +----+----+----+----+----+----+----+----+
3 | X  | M  | X  | M  | M  |    | M  | M  |
  +----+----+----+----+----+----+----+----+
4 |    |    | M  |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
5 | M  | H  |    |    |    | M  |    | X  |
  +----+----+----+----+----+----+----+----+
6 |    | X  | M  | M  |    | X  |    |    |
  +----+----+----+----+----+----+----+----+
7 |    |    |    |    | X  | X  | X  |    |
  +----+----+----+----+----+----+----+----+
8 |    | X  |    |    |    |    | M  |    |
  +----+----+----+----+----+----+----+----+
s
Cannot move down!

Current Position: (5, 2)
Use W, A, S, D to move, I for info, M for market, Q to quit
    1    2    3    4    5    6    7    8
  +----+----+----+----+----+----+----+----+
1 | M  | X  |    |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
2 | M  |    |    |    |    | M  |    | M  |
  +----+----+----+----+----+----+----+----+
3 | X  | M  | X  | M  | M  |    | M  | M  |
  +----+----+----+----+----+----+----+----+
4 |    |    | M  |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
5 | M  | H  |    |    |    | M  |    | X  |
  +----+----+----+----+----+----+----+----+
6 |    | X  | M  | M  |    | X  |    |    |
  +----+----+----+----+----+----+----+----+
7 |    |    |    |    | X  | X  | X  |    |
  +----+----+----+----+----+----+----+----+
8 |    | X  |    |    |    |    | M  |    |
  +----+----+----+----+----+----+----+----+
d

								A battle begins! You encountered monsters!


											-----HEROES-----

Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Pavel the Fierce     Paladin    200   398     825         700     700         2350    2                 11
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: false)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: true)
    3.   - Dagger (Type: Weapon, Damage: 350, Hands Required: 1, Level: 1, Price: 200, Equipped: true)
2. Bran the Yassine     Warrior    200   0       700         500     600         754     2                 11
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: false)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: true)
    3.   - Sword (Type: Weapon, Damage: 800, Hands Required: 1, Level: 1, Price: 500, Equipped: true)

											-----MONSTERS-----

Name							HP	Damage	Defense		Dodge Chance	Level
--------------------------------------------------------------------------------------------
1. Andrealphus The Spirit      190   270     225           20.00          2
2. BigBad-Wolf The Exoskeleton 190   135     225           15.00          2

									------HEROES BATTLE TURN------

Pavel the Fierce, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 1

Dagger is one-handed, but wielded with both hands, increasing damage by 75%!
Pavel the Fierce attacked Andrealphus The Spirit for 101 damage with Dagger Andrealphus The Spirit defended 33 so 68 total damage done

Bran the Yassine, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 1

Sword is one-handed, but wielded with both hands, increasing damage by 75%!
Bran the Yassine attacked Andrealphus The Spirit for 131 damage with Sword Andrealphus The Spirit defended 33 so 98 total damage done
Andrealphus The Spirit dodged the attack! No damage done.

									------MONSTERS BATTLE TURN------

Andrealphus The Spirit attacks Pavel the Fierce!  Pavel the Fierce dodged the attack completely!
BigBad-Wolf The Exoskeleton attacks Bran the Yassine!  Bran the Yassine took 0 damage, and his armor blocked 200 damage!

									------HEROES BATTLE TURN------

Pavel the Fierce, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 1

Dagger is one-handed, but wielded with both hands, increasing damage by 75%!
Pavel the Fierce attacked BigBad-Wolf The Exoskeleton for 101 damage with Dagger BigBad-Wolf The Exoskeleton defended 33 so 68 total damage done

Bran the Yassine, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 1

Sword is one-handed, but wielded with both hands, increasing damage by 75%!
Bran the Yassine attacked BigBad-Wolf The Exoskeleton for 131 damage with Sword BigBad-Wolf The Exoskeleton defended 33 so 98 total damage done

									------MONSTERS BATTLE TURN------

Andrealphus The Spirit attacks Pavel the Fierce!  Pavel the Fierce took 70 damage, and his armor blocked 200 damage!
BigBad-Wolf The Exoskeleton attacks Pavel the Fierce!  Pavel the Fierce dodged the attack completely!

									------HEROES BATTLE TURN------

Pavel the Fierce, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 1

Dagger is one-handed, but wielded with both hands, increasing damage by 75%!
Pavel the Fierce attacked BigBad-Wolf The Exoskeleton for 101 damage with Dagger BigBad-Wolf The Exoskeleton defended 33 so 68 total damage done

BigBad-Wolf The Exoskeleton has been killed!


Bran the Yassine, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 1

Sword is one-handed, but wielded with both hands, increasing damage by 75%!
Bran the Yassine attacked Andrealphus The Spirit for 131 damage with Sword Andrealphus The Spirit defended 33 so 98 total damage done

									------MONSTERS BATTLE TURN------

Andrealphus The Spirit attacks Pavel the Fierce!  Pavel the Fierce dodged the attack completely!

									------HEROES BATTLE TURN------

Pavel the Fierce, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 1

Dagger is one-handed, but wielded with both hands, increasing damage by 75%!
Pavel the Fierce attacked Andrealphus The Spirit for 101 damage with Dagger Andrealphus The Spirit defended 33 so 68 total damage done

Andrealphus The Spirit has been killed!

The heroes have triumphed! Gaining rewards...

Current Position: (5, 3)
Use W, A, S, D to move, I for info, M for market, Q to quit
    1    2    3    4    5    6    7    8
  +----+----+----+----+----+----+----+----+
1 | M  | X  |    |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
2 | M  |    |    |    |    | M  |    | M  |
  +----+----+----+----+----+----+----+----+
3 | X  | M  | X  | M  | M  |    | M  | M  |
  +----+----+----+----+----+----+----+----+
4 |    |    | M  |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
5 | M  |    | H  |    |    | M  |    | X  |
  +----+----+----+----+----+----+----+----+
6 |    | X  | M  | M  |    | X  |    |    |
  +----+----+----+----+----+----+----+----+
7 |    |    |    |    | X  | X  | X  |    |
  +----+----+----+----+----+----+----+----+
8 |    | X  |    |    |    |    | M  |    |
  +----+----+----+----+----+----+----+----+
i
Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Pavel the Fierce     Paladin    178   529     825         700     700         2750    2                 15
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: false)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: true)
    3.   - Dagger (Type: Weapon, Damage: 350, Hands Required: 1, Level: 1, Price: 200, Equipped: true)
2. Bran the Yassine     Warrior    266   0       700         500     600         1154    2                 15
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: false)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: true)
    3.   - Sword (Type: Weapon, Damage: 800, Hands Required: 1, Level: 1, Price: 500, Equipped: true)

Current Position: (5, 3)
Use W, A, S, D to move, I for info, M for market, Q to quit
    1    2    3    4    5    6    7    8
  +----+----+----+----+----+----+----+----+
1 | M  | X  |    |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
2 | M  |    |    |    |    | M  |    | M  |
  +----+----+----+----+----+----+----+----+
3 | X  | M  | X  | M  | M  |    | M  | M  |
  +----+----+----+----+----+----+----+----+
4 |    |    | M  |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
5 | M  |    | H  |    |    | M  |    | X  |
  +----+----+----+----+----+----+----+----+
6 |    | X  | M  | M  |    | X  |    |    |
  +----+----+----+----+----+----+----+----+
7 |    |    |    |    | X  | X  | X  |    |
  +----+----+----+----+----+----+----+----+
8 |    | X  |    |    |    |    | M  |    |
  +----+----+----+----+----+----+----+----+
d
No monsters encountered this time.

Current Position: (5, 4)
Use W, A, S, D to move, I for info, M for market, Q to quit
    1    2    3    4    5    6    7    8
  +----+----+----+----+----+----+----+----+
1 | M  | X  |    |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
2 | M  |    |    |    |    | M  |    | M  |
  +----+----+----+----+----+----+----+----+
3 | X  | M  | X  | M  | M  |    | M  | M  |
  +----+----+----+----+----+----+----+----+
4 |    |    | M  |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
5 | M  |    |    | H  |    | M  |    | X  |
  +----+----+----+----+----+----+----+----+
6 |    | X  | M  | M  |    | X  |    |    |
  +----+----+----+----+----+----+----+----+
7 |    |    |    |    | X  | X  | X  |    |
  +----+----+----+----+----+----+----+----+
8 |    | X  |    |    |    |    | M  |    |
  +----+----+----+----+----+----+----+----+
w

								A battle begins! You encountered monsters!


											-----HEROES-----

Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Pavel the Fierce     Paladin    178   529     825         700     700         2750    2                 15
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: false)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: true)
    3.   - Dagger (Type: Weapon, Damage: 350, Hands Required: 1, Level: 1, Price: 200, Equipped: true)
2. Bran the Yassine     Warrior    266   0       700         500     600         1154    2                 15
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: false)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: true)
    3.   - Sword (Type: Weapon, Damage: 800, Hands Required: 1, Level: 1, Price: 500, Equipped: true)

											-----MONSTERS-----

Name							HP	Damage	Defense		Dodge Chance	Level
--------------------------------------------------------------------------------------------
1. TheScaleless The Dragon     190   252     215           30.00          2
2. TheScaleless The Dragon     190   252     215           30.00          2

									------HEROES BATTLE TURN------

Pavel the Fierce, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 1

Dagger is one-handed, but wielded with both hands, increasing damage by 75%!
Pavel the Fierce attacked TheScaleless The Dragon for 101 damage with Dagger TheScaleless The Dragon defended 32 so 69 total damage done

Bran the Yassine, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 1

Sword is one-handed, but wielded with both hands, increasing damage by 75%!
Bran the Yassine attacked TheScaleless The Dragon for 131 damage with Sword TheScaleless The Dragon defended 32 so 99 total damage done
TheScaleless The Dragon dodged the attack! No damage done.

									------MONSTERS BATTLE TURN------

TheScaleless The Dragon attacks Pavel the Fierce!  Pavel the Fierce took 52 damage, and his armor blocked 200 damage!
TheScaleless The Dragon attacks Pavel the Fierce!  Pavel the Fierce took 52 damage, and his armor blocked 200 damage!

									------HEROES BATTLE TURN------

Pavel the Fierce, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 5

											-----HEROES-----

Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Pavel the Fierce     Paladin    79    568     825         700     700         2750    2                 15
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: false)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: true)
    3.   - Dagger (Type: Weapon, Damage: 350, Hands Required: 1, Level: 1, Price: 200, Equipped: true)
2. Bran the Yassine     Warrior    285   0       700         500     600         1154    2                 15
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: false)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: true)
    3.   - Sword (Type: Weapon, Damage: 800, Hands Required: 1, Level: 1, Price: 500, Equipped: true)

											-----MONSTERS-----

Name							HP	Damage	Defense		Dodge Chance	Level
--------------------------------------------------------------------------------------------
1. TheScaleless The Dragon     190   252     215           30.00          2
2. TheScaleless The Dragon     121   252     215           30.00          2

Pavel the Fierce, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 1

Dagger is one-handed, but wielded with both hands, increasing damage by 75%!
Pavel the Fierce attacked TheScaleless The Dragon for 101 damage with Dagger TheScaleless The Dragon defended 32 so 69 total damage done

Bran the Yassine, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 1

Sword is one-handed, but wielded with both hands, increasing damage by 75%!
Bran the Yassine attacked TheScaleless The Dragon for 131 damage with Sword TheScaleless The Dragon defended 32 so 99 total damage done

									------MONSTERS BATTLE TURN------

TheScaleless The Dragon attacks Bran the Yassine!  Bran the Yassine dodged the attack completely!
TheScaleless The Dragon attacks Pavel the Fierce!  Pavel the Fierce took 52 damage, and his armor blocked 200 damage!

									------HEROES BATTLE TURN------

Pavel the Fierce, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 5

											-----HEROES-----

Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Pavel the Fierce     Paladin    29    610     825         700     700         2750    2                 15
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: false)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: true)
    3.   - Dagger (Type: Weapon, Damage: 350, Hands Required: 1, Level: 1, Price: 200, Equipped: true)
2. Bran the Yassine     Warrior    306   0       700         500     600         1154    2                 15
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: false)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: true)
    3.   - Sword (Type: Weapon, Damage: 800, Hands Required: 1, Level: 1, Price: 500, Equipped: true)

											-----MONSTERS-----

Name							HP	Damage	Defense		Dodge Chance	Level
--------------------------------------------------------------------------------------------
1. TheScaleless The Dragon     91    252     215           30.00          2
2. TheScaleless The Dragon     52    252     215           30.00          2

Pavel the Fierce, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 1

Dagger is one-handed, but wielded with both hands, increasing damage by 75%!
Pavel the Fierce attacked TheScaleless The Dragon for 101 damage with Dagger TheScaleless The Dragon defended 32 so 69 total damage done

Bran the Yassine, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 1

Sword is one-handed, but wielded with both hands, increasing damage by 75%!
Bran the Yassine attacked TheScaleless The Dragon for 131 damage with Sword TheScaleless The Dragon defended 32 so 99 total damage done

TheScaleless The Dragon has been killed!


									------MONSTERS BATTLE TURN------

TheScaleless The Dragon attacks Pavel the Fierce!  Pavel the Fierce dodged the attack completely!

									------HEROES BATTLE TURN------

Pavel the Fierce, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 1

Dagger is one-handed, but wielded with both hands, increasing damage by 75%!
Pavel the Fierce attacked TheScaleless The Dragon for 101 damage with Dagger TheScaleless The Dragon defended 32 so 69 total damage done
TheScaleless The Dragon dodged the attack! No damage done.

Bran the Yassine, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 1

Sword is one-handed, but wielded with both hands, increasing damage by 75%!
Bran the Yassine attacked TheScaleless The Dragon for 131 damage with Sword TheScaleless The Dragon defended 32 so 99 total damage done

TheScaleless The Dragon has been killed!

The heroes have triumphed! Gaining rewards...

Current Position: (4, 4)
Use W, A, S, D to move, I for info, M for market, Q to quit
    1    2    3    4    5    6    7    8
  +----+----+----+----+----+----+----+----+
1 | M  | X  |    |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
2 | M  |    |    |    |    | M  |    | M  |
  +----+----+----+----+----+----+----+----+
3 | X  | M  | X  | M  | M  |    | M  | M  |
  +----+----+----+----+----+----+----+----+
4 |    |    | M  | H  | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
5 | M  |    |    |    |    | M  |    | X  |
  +----+----+----+----+----+----+----+----+
6 |    | X  | M  | M  |    | X  |    |    |
  +----+----+----+----+----+----+----+----+
7 |    |    |    |    | X  | X  | X  |    |
  +----+----+----+----+----+----+----+----+
8 |    | X  |    |    |    |    | M  |    |
  +----+----+----+----+----+----+----+----+
i
Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Pavel the Fierce     Paladin    33    704     825         700     700         3150    2                 19
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: false)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: true)
    3.   - Dagger (Type: Weapon, Damage: 350, Hands Required: 1, Level: 1, Price: 200, Equipped: true)
2. Bran the Yassine     Warrior    352   0       700         500     600         1554    2                 19
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: false)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: true)
    3.   - Sword (Type: Weapon, Damage: 800, Hands Required: 1, Level: 1, Price: 500, Equipped: true)

Current Position: (4, 4)
Use W, A, S, D to move, I for info, M for market, Q to quit
    1    2    3    4    5    6    7    8
  +----+----+----+----+----+----+----+----+
1 | M  | X  |    |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
2 | M  |    |    |    |    | M  |    | M  |
  +----+----+----+----+----+----+----+----+
3 | X  | M  | X  | M  | M  |    | M  | M  |
  +----+----+----+----+----+----+----+----+
4 |    |    | M  | H  | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
5 | M  |    |    |    |    | M  |    | X  |
  +----+----+----+----+----+----+----+----+
6 |    | X  | M  | M  |    | X  |    |    |
  +----+----+----+----+----+----+----+----+
7 |    |    |    |    | X  | X  | X  |    |
  +----+----+----+----+----+----+----+----+
8 |    | X  |    |    |    |    | M  |    |
  +----+----+----+----+----+----+----+----+
s
No monsters encountered this time.

Current Position: (5, 4)
Use W, A, S, D to move, I for info, M for market, Q to quit
    1    2    3    4    5    6    7    8
  +----+----+----+----+----+----+----+----+
1 | M  | X  |    |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
2 | M  |    |    |    |    | M  |    | M  |
  +----+----+----+----+----+----+----+----+
3 | X  | M  | X  | M  | M  |    | M  | M  |
  +----+----+----+----+----+----+----+----+
4 |    |    | M  |    | M  | M  | X  |    |
  +----+----+----+----+----+----+----+----+
5 | M  |    |    | H  |    | M  |    | X  |
  +----+----+----+----+----+----+----+----+
6 |    | X  | M  | M  |    | X  |    |    |
  +----+----+----+----+----+----+----+----+
7 |    |    |    |    | X  | X  | X  |    |
  +----+----+----+----+----+----+----+----+
8 |    | X  |    |    |    |    | M  |    |
  +----+----+----+----+----+----+----+----+
a

								A battle begins! You encountered monsters!


											-----HEROES-----

Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Pavel the Fierce     Paladin    33    704     825         700     700         3150    2                 19
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: false)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: true)
    3.   - Dagger (Type: Weapon, Damage: 350, Hands Required: 1, Level: 1, Price: 200, Equipped: true)
2. Bran the Yassine     Warrior    352   0       700         500     600         1554    2                 19
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: false)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: true)
    3.   - Sword (Type: Weapon, Damage: 800, Hands Required: 1, Level: 1, Price: 500, Equipped: true)

											-----MONSTERS-----

Name							HP	Damage	Defense		Dodge Chance	Level
--------------------------------------------------------------------------------------------
1. FallenAngel The Spirit      190   403     352           28.00          2
2. Cyrrollalee The Exoskeleton 190   252     288           30.00          2

									------HEROES BATTLE TURN------

Pavel the Fierce, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 1

Dagger is one-handed, but wielded with both hands, increasing damage by 75%!
Pavel the Fierce attacked FallenAngel The Spirit for 101 damage with Dagger FallenAngel The Spirit defended 52 so 49 total damage done

Bran the Yassine, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 1

Sword is one-handed, but wielded with both hands, increasing damage by 75%!
Bran the Yassine attacked Cyrrollalee The Exoskeleton for 131 damage with Sword Cyrrollalee The Exoskeleton defended 43 so 88 total damage done

									------MONSTERS BATTLE TURN------

FallenAngel The Spirit attacks Pavel the Fierce!  Pavel the Fierce took 203 damage, and his armor blocked 200 damage!

Pavel the Fierce has been killed!

Cyrrollalee The Exoskeleton attacks Bran the Yassine!  Bran the Yassine took 52 damage, and his armor blocked 200 damage!

									------HEROES BATTLE TURN------

Bran the Yassine, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 5

											-----HEROES-----

Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Bran the Yassine     Warrior    322   0       700         500     600         1554    2                 19
    Inventory:
    1.   - Bare Hands (Type: Weapon, Damage: 500, Hands Required: 2, Level: 1, Price: 0, Equipped: false)
    2.   - Platinum_Shield (Type: Armor, Damage Reduction: 200, Level: 1, Price: 150, Equipped: true)
    3.   - Sword (Type: Weapon, Damage: 800, Hands Required: 1, Level: 1, Price: 500, Equipped: true)

											-----MONSTERS-----

Name							HP	Damage	Defense		Dodge Chance	Level
--------------------------------------------------------------------------------------------
1. FallenAngel The Spirit      141   403     352           28.00          2
2. Cyrrollalee The Exoskeleton 102   252     288           30.00          2

Bran the Yassine, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 1

Sword is one-handed, but wielded with both hands, increasing damage by 75%!
Bran the Yassine attacked FallenAngel The Spirit for 131 damage with Sword FallenAngel The Spirit defended 52 so 79 total damage done

									------MONSTERS BATTLE TURN------

FallenAngel The Spirit attacks Bran the Yassine!  Bran the Yassine took 203 damage, and his armor blocked 200 damage!
Cyrrollalee The Exoskeleton attacks Bran the Yassine!  Bran the Yassine took 52 damage, and his armor blocked 200 damage!

									------HEROES BATTLE TURN------

Bran the Yassine, choose your action:
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Item
5. Show Stats
Enter your choice (1-5): 1

Sword is one-handed, but wielded with both hands, increasing damage by 75%!
Bran the Yassine attacked Cyrrollalee The Exoskeleton for 131 damage with Sword Cyrrollalee The Exoskeleton defended 43 so 88 total damage done

									------MONSTERS BATTLE TURN------

FallenAngel The Spirit attacks Bran the Yassine!  Bran the Yassine took 203 damage, and his armor blocked 200 damage!

Bran the Yassine has been killed!

The monsters have won. Game over!