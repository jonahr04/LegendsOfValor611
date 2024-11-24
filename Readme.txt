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
                                             Legends of Valor Classes
                                            --------------------------

LegendsOfValor.java: Manages the main gameplay for Legends of Valor, handling character movement, user inputs, and interactions
with board spaces.

ValorBattle.java: Manages gameplay details, particularly those related to the monsters, and controls all specific movements during a round.

NexusSpace.java: Represents a board cell at the top and bottom of each lane.

BushSpace.java: Represents a board cell where heroes gains dexterity.

CaveSpace.java: Represents a board cell where heroes gains agility.

KoulouSpace.java: Represents a board cell where heroes gains strength.


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
1. In terminal, locate the LegendsOfValor folder
2. cd the src folder
3. run command 'javac Main.java'
4. run command 'java Main'
5. continue to play the game as demonstrated

## Input/Output Example
---------------------------------------------------------------------------
Choose a game to play:
1. Tic-Tac-Toe
2. Order and Chaos
3. Super Tic Tac Toe
4. Monsters and Heros
5. Legends of Valor
6. Display Statistics
q. Quit
5
 These are your available Heroes:
Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Kyra the Fierce      Warrior    100   300     900         500     750         2546    1                 6
2. Cedric the Yassine   Sorcerer   100   1000    700         400     500         2500    1                 5
3. Talia the Vigilant   Paladin    100   300     750         700     700         2500    1                 7
4. Luna the Unyielding  Sorcerer   100   800     800         800     800         2500    1                 8
5. Ivy the Cunning      Sorcerer   100   1000    700         400     500         2500    1                 5
6. Hector the Fearless  Sorcerer   100   800     800         800     800         2500    1                 8
7. Ulric the Just       Paladin    100   500     500         500     500         2500    1                 5
8. Sean the Steadfast   Warrior    100   400     800         400     700         2500    1                 7
9. Gwen the Wise        Sorcerer   100   800     850         400     600         2500    1                 6
10. Damon the Merciful   Paladin    100   100     600         500     400         2500    1                 5

Select hero 1 of 3:
Enter the number of the hero you'd like to recruit: 1

Remaining heroes:
Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Cedric the Yassine   Sorcerer   100   1000    700         400     500         2500    1                 5
2. Talia the Vigilant   Paladin    100   300     750         700     700         2500    1                 7
3. Luna the Unyielding  Sorcerer   100   800     800         800     800         2500    1                 8
4. Ivy the Cunning      Sorcerer   100   1000    700         400     500         2500    1                 5
5. Hector the Fearless  Sorcerer   100   800     800         800     800         2500    1                 8
6. Ulric the Just       Paladin    100   500     500         500     500         2500    1                 5
7. Sean the Steadfast   Warrior    100   400     800         400     700         2500    1                 7
8. Gwen the Wise        Sorcerer   100   800     850         400     600         2500    1                 6
9. Damon the Merciful   Paladin    100   100     600         500     400         2500    1                 5

Select hero 2 of 3:
Enter the number of the hero you'd like to recruit: 2

Remaining heroes:
Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Cedric the Yassine   Sorcerer   100   1000    700         400     500         2500    1                 5
2. Luna the Unyielding  Sorcerer   100   800     800         800     800         2500    1                 8
3. Ivy the Cunning      Sorcerer   100   1000    700         400     500         2500    1                 5
4. Hector the Fearless  Sorcerer   100   800     800         800     800         2500    1                 8
5. Ulric the Just       Paladin    100   500     500         500     500         2500    1                 5
6. Sean the Steadfast   Warrior    100   400     800         400     700         2500    1                 7
7. Gwen the Wise        Sorcerer   100   800     850         400     600         2500    1                 6
8. Damon the Merciful   Paladin    100   100     600         500     400         2500    1                 5

Select hero 3 of 3:
Enter the number of the hero you'd like to recruit: 3

Remaining heroes:
Name					Type		HP	 Mana	Strength	Agility	Dexterity	Money	Experience Level	Experience Points
---------------------------------------------------------------------------------------------------------------------------
1. Cedric the Yassine   Sorcerer   100   1000    700         400     500         2500    1                 5
2. Luna the Unyielding  Sorcerer   100   800     800         800     800         2500    1                 8
3. Hector the Fearless  Sorcerer   100   800     800         800     800         2500    1                 8
4. Ulric the Just       Paladin    100   500     500         500     500         2500    1                 5
5. Sean the Steadfast   Warrior    100   400     800         400     700         2500    1                 7
6. Gwen the Wise        Sorcerer   100   800     850         400     600         2500    1                 6
7. Damon the Merciful   Paladin    100   100     600         500     400         2500    1                 5
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  B   |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 | H1 N |  N   |  X   | H2 N |  N   |  X   | H3 N |  N   |
  +------+------+------+------+------+------+------+------+
New monsters created!
Player, enter your move for hero 1: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
w
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  M N |  N   |  X   |  M N |  N   |  X   |  M N |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  B   |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 | H1   |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   | H2 N |  N   |  X   | H3 N |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 2: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
w
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  M N |  N   |  X   |  M N |  N   |  X   |  M N |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  B   |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 | H1   |      |  X   | H2   |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   | H3 N |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 3: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
w
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  M N |  N   |  X   |  M N |  N   |  X   |  M N |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  B   |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 | H1   |      |  X   | H2   |  C   |  X   | H3   |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Monster TheScaleless The Dragon moves forward!
Monster Casper The Spirit moves forward!
Monster Alexstraszan The Dragon moves forward!
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |  M   |      |  X   |  M   |  K   |  X   |  M   |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  B   |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 | H1   |      |  X   | H2   |  C   |  X   | H3   |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 1: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
w
Going to BushSpace. Changing dexterity from 750 -> 1125
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |  M   |      |  X   |  M   |  K   |  X   |  M   |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  B   |  B   |
  +------+------+------+------+------+------+------+------+
6 | H1 B |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   | H2   |  C   |  X   | H3   |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 2: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
w
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |  M   |      |  X   |  M   |  K   |  X   |  M   |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  B   |  B   |
  +------+------+------+------+------+------+------+------+
6 | H1 B |  B   |  X   | H2   |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   | H3   |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 3: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
w
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |  M   |      |  X   |  M   |  K   |  X   |  M   |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  B   |  B   |
  +------+------+------+------+------+------+------+------+
6 | H1 B |  B   |  X   | H2   |  B   |  X   | H3   |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Monster TheScaleless The Dragon moves forward!
Monster Casper The Spirit moves forward!
Monster Alexstraszan The Dragon moves forward!
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  M C |  B   |  X   |  M C |  B   |  X   |  M C |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  B   |  B   |
  +------+------+------+------+------+------+------+------+
6 | H1 B |  B   |  X   | H2   |  B   |  X   | H3   |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 1: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
w
Leaving BushSpace. Changing dexterity from 1125 -> 750
Going to CaveSpace. Changing agility from 500 -> 750
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  M C |  B   |  X   |  M C |  B   |  X   |  M C |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
5 | H1 C |  C   |  X   |  K   |      |  X   |  B   |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   | H2   |  B   |  X   | H3   |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 2: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
w
Going to KoulouSpace. Changing strength from 750 -> 1125
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  M C |  B   |  X   |  M C |  B   |  X   |  M C |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
5 | H1 C |  C   |  X   | H2 K |      |  X   |  B   |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   | H3   |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 3: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
w
Going to BushSpace. Changing dexterity from 500 -> 750
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  M C |  B   |  X   |  M C |  B   |  X   |  M C |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
5 | H1 C |  C   |  X   | H2 K |      |  X   | H3 B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Monster TheScaleless The Dragon moves forward!
Monster Casper The Spirit moves forward!
Monster Alexstraszan The Dragon moves forward!
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  M K |      |  X   |  M   |  K   |  X   |  M   |  K   |
  +------+------+------+------+------+------+------+------+
5 | H1 C |  C   |  X   | H2 K |      |  X   | H3 B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 1: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
b
Select an action:
1. Attack
2. Spell
3. Potion
Enter your choice (1-3): 1

Select a target:
1. TheScaleless The Dragon at position (4, 1)
2. Casper The Spirit at position (4, 4)
3. Alexstraszan The Dragon at position (4, 7)
Enter your choice (1-3): 1
Kyra the Fierce attacked TheScaleless The Dragon for 70 damage with Bare Hands TheScaleless The Dragon defended 16 so 54 total damage done
TheScaleless The Dragon dodged the attack! No damage done.
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  M K |      |  X   |  M   |  K   |  X   |  M   |  K   |
  +------+------+------+------+------+------+------+------+
5 | H1 C |  C   |  X   | H2 K |      |  X   | H3 B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 2: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
b
Select an action:
1. Attack
2. Spell
3. Potion
Enter your choice (1-3): 1

Select a target:
1. TheScaleless The Dragon at position (4, 1)
2. Casper The Spirit at position (4, 4)
3. Alexstraszan The Dragon at position (4, 7)
Enter your choice (1-3): 2
Talia the Vigilant attacked Casper The Spirit for 81 damage with Bare Hands Casper The Spirit defended 6 so 75 total damage done
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  M K |      |  X   |  M   |  K   |  X   |  M   |  K   |
  +------+------+------+------+------+------+------+------+
5 | H1 C |  C   |  X   | H2 K |      |  X   | H3 B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 3: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
b
Select an action:
1. Attack
2. Spell
3. Potion
Enter your choice (1-3): 1

Select a target:
1. TheScaleless The Dragon at position (4, 1)
2. Casper The Spirit at position (4, 4)
3. Alexstraszan The Dragon at position (4, 7)
Enter your choice (1-3): 3
Ivy the Cunning attacked Alexstraszan The Dragon for 60 damage with Bare Hands Alexstraszan The Dragon defended 16 so 44 total damage done
Alexstraszan The Dragon dodged the attack! No damage done.
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  M K |      |  X   |  M   |  K   |  X   |  M   |  K   |
  +------+------+------+------+------+------+------+------+
5 | H1 C |  C   |  X   | H2 K |      |  X   | H3 B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
TheScaleless The Dragon attacks Kyra the Fierce!  Kyra the Fierce dodged the attack completely!
Casper The Spirit attacks Talia the Vigilant!  Talia the Vigilant took 45 damage!
Alexstraszan The Dragon attacks Ivy the Cunning!  Ivy the Cunning dodged the attack completely!
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  M K |      |  X   |  M   |  K   |  X   |  M   |  K   |
  +------+------+------+------+------+------+------+------+
5 | H1 C |  C   |  X   | H2 K |      |  X   | H3 B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
New monsters created!
Player, enter your move for hero 1: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
b
Select an action:
1. Attack
2. Spell
3. Potion
Enter your choice (1-3): 1

Select a target:
1. TheScaleless The Dragon at position (4, 1)
2. Casper The Spirit at position (4, 4)
3. Alexstraszan The Dragon at position (4, 7)
4. BlueEyesWhite The Dragon at position (1, 1)
5. Phaarthurnax The Dragon at position (1, 4)
6. Casper The Spirit at position (1, 7)
Enter your choice (1-6): 1
Kyra the Fierce attacked TheScaleless The Dragon for 70 damage with Bare Hands TheScaleless The Dragon defended 16 so 54 total damage done
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  M N |  N   |  X   |  M N |  N   |  X   |  M N |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  M K |      |  X   |  M   |  K   |  X   |  M   |  K   |
  +------+------+------+------+------+------+------+------+
5 | H1 C |  C   |  X   | H2 K |      |  X   | H3 B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 2: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
b
Select an action:
1. Attack
2. Spell
3. Potion
Enter your choice (1-3): 1

Select a target:
1. TheScaleless The Dragon at position (4, 1)
2. Casper The Spirit at position (4, 4)
3. Alexstraszan The Dragon at position (4, 7)
4. BlueEyesWhite The Dragon at position (1, 1)
5. Phaarthurnax The Dragon at position (1, 4)
6. Casper The Spirit at position (1, 7)
Enter your choice (1-6): 2
Talia the Vigilant attacked Casper The Spirit for 81 damage with Bare Hands Casper The Spirit defended 6 so 75 total damage done
Casper The Spirit dodged the attack! No damage done.
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  M N |  N   |  X   |  M N |  N   |  X   |  M N |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  M K |      |  X   |  M   |  K   |  X   |  M   |  K   |
  +------+------+------+------+------+------+------+------+
5 | H1 C |  C   |  X   | H2 K |      |  X   | H3 B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 3: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
b
Select an action:
1. Attack
2. Spell
3. Potion
Enter your choice (1-3): 1

Select a target:
1. TheScaleless The Dragon at position (4, 1)
2. Casper The Spirit at position (4, 4)
3. Alexstraszan The Dragon at position (4, 7)
4. BlueEyesWhite The Dragon at position (1, 1)
5. Phaarthurnax The Dragon at position (1, 4)
6. Casper The Spirit at position (1, 7)
Enter your choice (1-6): 3
Ivy the Cunning attacked Alexstraszan The Dragon for 60 damage with Bare Hands Alexstraszan The Dragon defended 16 so 44 total damage done
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  M N |  N   |  X   |  M N |  N   |  X   |  M N |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  M K |      |  X   |  M   |  K   |  X   |  M   |  K   |
  +------+------+------+------+------+------+------+------+
5 | H1 C |  C   |  X   | H2 K |      |  X   | H3 B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
TheScaleless The Dragon attacks Kyra the Fierce!  Kyra the Fierce dodged the attack completely!
Casper The Spirit attacks Talia the Vigilant!  Talia the Vigilant dodged the attack completely!
Alexstraszan The Dragon attacks Ivy the Cunning!  Ivy the Cunning took 125 damage!
Monster BlueEyesWhite The Dragon moves forward!
Monster Phaarthurnax The Dragon moves forward!
Monster Casper The Spirit moves forward!
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |  M   |      |  X   |  M   |  K   |  X   |  M   |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  M K |      |  X   |  M   |  K   |  X   |  M   |  K   |
  +------+------+------+------+------+------+------+------+
5 | H1 C |  C   |  X   | H2 K |      |  X   | H3 B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 1: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
b
Select an action:
1. Attack
2. Spell
3. Potion
Enter your choice (1-3): 1

Select a target:
1. TheScaleless The Dragon at position (4, 1)
2. Casper The Spirit at position (4, 4)
3. Alexstraszan The Dragon at position (4, 7)
4. BlueEyesWhite The Dragon at position (2, 1)
5. Phaarthurnax The Dragon at position (2, 4)
6. Casper The Spirit at position (2, 7)
Enter your choice (1-6): 1
Kyra the Fierce attacked TheScaleless The Dragon for 70 damage with Bare Hands TheScaleless The Dragon defended 16 so 54 total damage done

 Monster TheScaleless The Dragon has been killed!

     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |  M   |      |  X   |  M   |  K   |  X   |  M   |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |  M   |  K   |  X   |  M   |  K   |
  +------+------+------+------+------+------+------+------+
5 | H1 C |  C   |  X   | H2 K |      |  X   | H3 B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 2: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
b
Select an action:
1. Attack
2. Spell
3. Potion
Enter your choice (1-3): 1

Select a target:
1. Casper The Spirit at position (4, 4)
2. Alexstraszan The Dragon at position (4, 7)
3. BlueEyesWhite The Dragon at position (2, 1)
4. Phaarthurnax The Dragon at position (2, 4)
5. Casper The Spirit at position (2, 7)
Enter your choice (1-5): 2
Hero 2 can not attack this monster, it is out of range!
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |  M   |      |  X   |  M   |  K   |  X   |  M   |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |  M   |  K   |  X   |  M   |  K   |
  +------+------+------+------+------+------+------+------+
5 | H1 C |  C   |  X   | H2 K |      |  X   | H3 B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 2: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
b
Select an action:
1. Attack
2. Spell
3. Potion
Enter your choice (1-3): 1

Select a target:
1. Casper The Spirit at position (4, 4)
2. Alexstraszan The Dragon at position (4, 7)
3. BlueEyesWhite The Dragon at position (2, 1)
4. Phaarthurnax The Dragon at position (2, 4)
5. Casper The Spirit at position (2, 7)
Enter your choice (1-5): 1
Talia the Vigilant attacked Casper The Spirit for 81 damage with Bare Hands Casper The Spirit defended 6 so 75 total damage done

 Monster Casper The Spirit has been killed!

     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |  M   |      |  X   |  M   |  K   |  X   |  M   |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |  M   |  K   |
  +------+------+------+------+------+------+------+------+
5 | H1 C |  C   |  X   | H2 K |      |  X   | H3 B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 3: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
b
Select an action:
1. Attack
2. Spell
3. Potion
Enter your choice (1-3): 1

Select a target:
1. Alexstraszan The Dragon at position (4, 7)
2. BlueEyesWhite The Dragon at position (2, 1)
3. Phaarthurnax The Dragon at position (2, 4)
4. Casper The Spirit at position (2, 7)
Enter your choice (1-4): 3
Hero 3 can not attack this monster, it is out of range!
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |  M   |      |  X   |  M   |  K   |  X   |  M   |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |  M   |  K   |
  +------+------+------+------+------+------+------+------+
5 | H1 C |  C   |  X   | H2 K |      |  X   | H3 B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 3: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
b
Select an action:
1. Attack
2. Spell
3. Potion
Enter your choice (1-3): 1

Select a target:
1. Alexstraszan The Dragon at position (4, 7)
2. BlueEyesWhite The Dragon at position (2, 1)
3. Phaarthurnax The Dragon at position (2, 4)
4. Casper The Spirit at position (2, 7)
Enter your choice (1-4): 2
Hero 3 can not attack this monster, it is out of range!
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |  M   |      |  X   |  M   |  K   |  X   |  M   |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |  M   |  K   |
  +------+------+------+------+------+------+------+------+
5 | H1 C |  C   |  X   | H2 K |      |  X   | H3 B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 3: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
b
Select an action:
1. Attack
2. Spell
3. Potion
Enter your choice (1-3): 1

Select a target:
1. Alexstraszan The Dragon at position (4, 7)
2. BlueEyesWhite The Dragon at position (2, 1)
3. Phaarthurnax The Dragon at position (2, 4)
4. Casper The Spirit at position (2, 7)
Enter your choice (1-4): 1
Ivy the Cunning attacked Alexstraszan The Dragon for 60 damage with Bare Hands Alexstraszan The Dragon defended 16 so 44 total damage done
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |  M   |      |  X   |  M   |  K   |  X   |  M   |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |  M   |  K   |
  +------+------+------+------+------+------+------+------+
5 | H1 C |  C   |  X   | H2 K |      |  X   | H3 B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Alexstraszan The Dragon attacks Ivy the Cunning!  Ivy the Cunning took 125 damage!

 Hero Ivy the Cunning has been killed!

Monster BlueEyesWhite The Dragon moves forward!
Monster Phaarthurnax The Dragon moves forward!
Monster Casper The Spirit moves forward!
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  M C |  B   |  X   |  M C |  B   |  X   |  M C |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |  M   |  K   |
  +------+------+------+------+------+------+------+------+
5 | H1 C |  C   |  X   | H2 K |      |  X   |  B   |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   | H3 N |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 1: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
w
Leaving to CaveSpace. Changing agility from 750 -> 500
Going to KoulouSpace. Changing strength from 900 -> 1350
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  M C |  B   |  X   |  M C |  B   |  X   |  M C |  B   |
  +------+------+------+------+------+------+------+------+
4 | H1 K |      |  X   |      |  K   |  X   |  M   |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   | H2 K |      |  X   |  B   |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   | H3 N |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 2: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
t

Enter the coordinates where you want to teleport to:
Row:4
Col:2

     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  M C |  B   |  X   |  M C |  B   |  X   |  M C |  B   |
  +------+------+------+------+------+------+------+------+
4 | H1 K | H2   |  X   |      |  K   |  X   |  M   |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  B   |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   | H3 N |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 3: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
w
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  M C |  B   |  X   |  M C |  B   |  X   |  M C |  B   |
  +------+------+------+------+------+------+------+------+
4 | H1 K | H2   |  X   |      |  K   |  X   |  M   |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  B   |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   | H3   |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Monster Alexstraszan The Dragon moves forward!
BlueEyesWhite The Dragon attacks Kyra the Fierce!  Kyra the Fierce dodged the attack completely!
Monster Phaarthurnax The Dragon moves forward!
Monster Casper The Spirit moves forward!
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  M C |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 | H1 K | H2   |  X   |  M   |  K   |  X   |  M   |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  M B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   | H3   |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 1: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
b
Select an action:
1. Attack
2. Spell
3. Potion
Enter your choice (1-3): 1

Select a target:
1. Alexstraszan The Dragon at position (5, 7)
2. BlueEyesWhite The Dragon at position (3, 1)
3. Phaarthurnax The Dragon at position (4, 4)
4. Casper The Spirit at position (4, 7)
Enter your choice (1-4): 2
Kyra the Fierce attacked BlueEyesWhite The Dragon for 92 damage with Bare Hands BlueEyesWhite The Dragon defended 12 so 80 total damage done
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  M C |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 | H1 K | H2   |  X   |  M   |  K   |  X   |  M   |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  M B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   | H3   |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 2: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
b
Select an action:
1. Attack
2. Spell
3. Potion
Enter your choice (1-3): 1

Select a target:
1. Alexstraszan The Dragon at position (5, 7)
2. BlueEyesWhite The Dragon at position (3, 1)
3. Phaarthurnax The Dragon at position (4, 4)
4. Casper The Spirit at position (4, 7)
Enter your choice (1-4): 2
Talia the Vigilant attacked BlueEyesWhite The Dragon for 81 damage with Bare Hands BlueEyesWhite The Dragon defended 12 so 69 total damage done

 Monster BlueEyesWhite The Dragon has been killed!

     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 | H1 K | H2   |  X   |  M   |  K   |  X   |  M   |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  M B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   |      |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   | H3   |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 3: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
w
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 | H1 K | H2   |  X   |  M   |  K   |  X   |  M   |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  M B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   | H3   |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Alexstraszan The Dragon attacks Ivy the Cunning!  Ivy the Cunning took 125 damage!

 Hero Ivy the Cunning has been killed!

Monster Phaarthurnax The Dragon moves forward!
Monster Casper The Spirit moves forward!
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 | H1 K | H2   |  X   |      |  K   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  M K |      |  X   |  M B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   | H3   |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
New monsters created!
Player, enter your move for hero 1: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
w
Leaving to KoulouSpace. Changing strength from 1350 -> 900
Going to CaveSpace. Changing agility from 500 -> 750
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  M N |  N   |  X   |  M N |  N   |  X   |  M N |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 | H1 C |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   | H2   |  X   |      |  K   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  M K |      |  X   |  M B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   | H3   |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 2: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
w
Going to BushSpace. Changing dexterity from 700 -> 1050
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  M N |  N   |  X   |  M N |  N   |  X   |  M N |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 | H1 C | H2 B |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  M K |      |  X   |  M B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   | H3   |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 3: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
b
Select an action:
1. Attack
2. Spell
3. Potion
Enter your choice (1-3): 1

Select a target:
1. Alexstraszan The Dragon at position (5, 7)
2. Phaarthurnax The Dragon at position (5, 4)
3. Casper The Spirit at position (5, 7)
4. Aasterinian The Exoskeleton at position (1, 1)
5. Ereshkigall The Spirit at position (1, 4)
6. Casper The Spirit at position (1, 7)
Enter your choice (1-6): 3
Ivy the Cunning attacked Casper The Spirit for 60 damage with Bare Hands Casper The Spirit defended 6 so 54 total damage done
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  M N |  N   |  X   |  M N |  N   |  X   |  M N |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 | H1 C | H2 B |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  M K |      |  X   |  M B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   | H3   |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Alexstraszan The Dragon attacks Ivy the Cunning!  Ivy the Cunning took 125 damage!

 Hero Ivy the Cunning has been killed!

Monster Phaarthurnax The Dragon moves forward!
Casper The Spirit attacks Ivy the Cunning!  Ivy the Cunning took 45 damage!
Monster Aasterinian The Exoskeleton moves forward!
Monster Ereshkigall The Spirit moves forward!
Monster Casper The Spirit moves forward!
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |  M   |      |  X   |  M   |  K   |  X   |  M   |      |
  +------+------+------+------+------+------+------+------+
3 | H1 C | H2 B |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  M B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |  M   |  B   |  X   | H3   |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 1: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
b
Select an action:
1. Attack
2. Spell
3. Potion
Enter your choice (1-3): 1

Select a target:
1. Alexstraszan The Dragon at position (5, 7)
2. Phaarthurnax The Dragon at position (6, 4)
3. Casper The Spirit at position (5, 7)
4. Aasterinian The Exoskeleton at position (2, 1)
5. Ereshkigall The Spirit at position (2, 4)
6. Casper The Spirit at position (2, 7)
Enter your choice (1-6): 4
Kyra the Fierce attacked Aasterinian The Exoskeleton for 70 damage with Bare Hands Aasterinian The Exoskeleton defended 23 so 47 total damage done
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |  M   |      |  X   |  M   |  K   |  X   |  M   |      |
  +------+------+------+------+------+------+------+------+
3 | H1 C | H2 B |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  M B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |  M   |  B   |  X   | H3   |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 2: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
b
Select an action:
1. Attack
2. Spell
3. Potion
Enter your choice (1-3): 1

Select a target:
1. Alexstraszan The Dragon at position (5, 7)
2. Phaarthurnax The Dragon at position (6, 4)
3. Casper The Spirit at position (5, 7)
4. Aasterinian The Exoskeleton at position (2, 1)
5. Ereshkigall The Spirit at position (2, 4)
6. Casper The Spirit at position (2, 7)
Enter your choice (1-6): 4
Talia the Vigilant attacked Aasterinian The Exoskeleton for 81 damage with Bare Hands Aasterinian The Exoskeleton defended 23 so 58 total damage done

 Monster Aasterinian The Exoskeleton has been killed!

     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |  M   |  K   |  X   |  M   |      |
  +------+------+------+------+------+------+------+------+
3 | H1 C | H2 B |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  M B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |  M   |  B   |  X   | H3   |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 3: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
b
Select an action:
1. Attack
2. Spell
3. Potion
Enter your choice (1-3): 1

Select a target:
1. Alexstraszan The Dragon at position (5, 7)
2. Phaarthurnax The Dragon at position (6, 4)
3. Casper The Spirit at position (5, 7)
4. Ereshkigall The Spirit at position (2, 4)
5. Casper The Spirit at position (2, 7)
Enter your choice (1-5): 1
Ivy the Cunning attacked Alexstraszan The Dragon for 60 damage with Bare Hands Alexstraszan The Dragon defended 16 so 44 total damage done

 Monster Alexstraszan The Dragon has been killed!

     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |  M   |  K   |  X   |  M   |      |
  +------+------+------+------+------+------+------+------+
3 | H1 C | H2 B |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  M B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |  M   |  B   |  X   | H3   |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |      |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Monster Phaarthurnax The Dragon moves forward!
Casper The Spirit attacks Ivy the Cunning!  Ivy the Cunning took 45 damage!

 Hero Ivy the Cunning has been killed!

Monster Ereshkigall The Spirit moves forward!
Monster Casper The Spirit moves forward!
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 | H1 C | H2 B |  X   |  M C |  B   |  X   |  M C |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  M B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   | H3   |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |  M   |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+

Leveling up Talia the Vigilant from level 1 to level 2 due to their 10 experience points.
New HP: 200
Skill upgrades:
 - Agility: 700 -> 735
 - Dexterity: 1050 -> 1157 (favored)
 - Strength: 1125 -> 1240 (favored)

Player, enter your move for hero 1: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
w
Leaving to CaveSpace. Changing agility from 750 -> 500
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 | H1   |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  C   | H2 B |  X   |  M C |  B   |  X   |  M C |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  M B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   | H3   |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |  M   |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 2: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
r
Player 2 recalled to nexus!
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 | H1   |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  M C |  B   |  X   |  M C |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  M B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   | H3   |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |  M   |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   | H2 N |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 3: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
b
Select an action:
1. Attack
2. Spell
3. Potion
Enter your choice (1-3): 1

Select a target:
1. Phaarthurnax The Dragon at position (7, 4)
2. Casper The Spirit at position (5, 7)
3. Ereshkigall The Spirit at position (3, 4)
4. Casper The Spirit at position (3, 7)
Enter your choice (1-4): 3
Hero 3 can not attack this monster, it is out of range!
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 | H1   |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  M C |  B   |  X   |  M C |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  M B |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   | H3   |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |  M   |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   | H2 N |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 3: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
b
Select an action:
1. Attack
2. Spell
3. Potion
Enter your choice (1-3): 1

Select a target:
1. Phaarthurnax The Dragon at position (7, 4)
2. Casper The Spirit at position (5, 7)
3. Ereshkigall The Spirit at position (3, 4)
4. Casper The Spirit at position (3, 7)
Enter your choice (1-4): 2
Ivy the Cunning attacked Casper The Spirit for 60 damage with Bare Hands Casper The Spirit defended 6 so 54 total damage done

 Monster Casper The Spirit has been killed!

     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 | H1   |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  M C |  B   |  X   |  M C |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |      |  K   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  B   |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   | H3   |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |  M   |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   | H2 N |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Phaarthurnax The Dragon attacks Talia the Vigilant!  Talia the Vigilant took 125 damage!
Monster Ereshkigall The Spirit moves forward!
Monster Casper The Spirit moves forward!
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 |  N   |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 | H1   |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |  M   |  K   |  X   |  M   |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  B   |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   | H3   |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |  M   |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   | H2 N |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Player, enter your move for hero 1: (w,a,s,d, m (nexus market) , e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)):
w
Heroes Win! Hero0 has reached the heroes' nexus!
     1      2      3      4      5      6      7      8
  +------+------+------+------+------+------+------+------+
1 | H1 N |  N   |  X   |  N   |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
2 |      |      |  X   |      |  K   |  X   |      |      |
  +------+------+------+------+------+------+------+------+
3 |  C   |  B   |  X   |  C   |  B   |  X   |  C   |  B   |
  +------+------+------+------+------+------+------+------+
4 |  K   |      |  X   |  M   |  K   |  X   |  M   |  K   |
  +------+------+------+------+------+------+------+------+
5 |  C   |  C   |  X   |  K   |      |  X   |  B   |  B   |
  +------+------+------+------+------+------+------+------+
6 |  B   |  B   |  X   |      |  B   |  X   | H3   |  C   |
  +------+------+------+------+------+------+------+------+
7 |      |      |  X   |  M   |  C   |  X   |      |  K   |
  +------+------+------+------+------+------+------+------+
8 |  N   |  N   |  X   | H2 N |  N   |  X   |  N   |  N   |
  +------+------+------+------+------+------+------+------+
Choose a game to play:
1. Tic-Tac-Toe
2. Order and Chaos
3. Super Tic Tac Toe
4. Monsters and Heros
5. Legends of Valor
6. Display Statistics
q. Quit
q
Exiting the game. Thank you for playing!

Process finished with exit code 0
