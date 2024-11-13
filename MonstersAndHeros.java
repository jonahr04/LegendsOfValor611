//Jonah Rothman
//MonstersAndHeros.Java
//This is class that have the methods needed to run Monsters and Heros

import java.util.*;

public class MonstersAndHeros implements Game{

    private Board gameBoard;
    private final int rows=8;
    private final int cols=8;
    private final int maxHeros = 3;
    private int numHeros;
    private List<Hero> Heros; // List of heroes chosen by the player
    // Initial position of the heroes on the board (e.g., starting at (0,0))
    int currentRow = 0;
    int currentCol = 0;



    //Default constructor initialized the display and superboard
    public MonstersAndHeros(){
        gameBoard = new Board(rows,cols);
        numHeros = 2; //2 by default
        Heros = new ArrayList<>();
        initializeBoard();
    }

    // Method to initialize the game board with random spaces
    private void initializeBoard() {
        // Total cells in the board
        int totalCells = rows * cols;

        // Calculate numbers of each space type
        int numInaccessible = (int) (totalCells * 0.2);
        int numMarket = (int) (totalCells * 0.3);
        int numCommon = totalCells - numInaccessible - numMarket;

        // Create lists for each space type
        ArrayList<BoardCell> spaces = new ArrayList<>();

        // Add specific space objects to the list based on the calculated number
        for (int i = 0; i < numInaccessible; i++) {
            spaces.add(new InaccessibleSpace());
        }
        for (int i = 0; i < numMarket; i++) {
            spaces.add(new MarketSpace());
        }
        for (int i = 0; i < numCommon; i++) {
            spaces.add(new CommonSpace());
        }

        // Shuffle spaces to randomize placement
        Collections.shuffle(spaces, new Random());

        // Fill the gameBoard with BoardCells containing the shuffled spaces
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                gameBoard.getBoard()[i][j] = new BoardCell(spaces.get(index++));
            }
        }

        // Find a random CommonSpace to place the heroes' starting position
        Random rand = new Random();
        while (true) {
            int randomRow = rand.nextInt(rows);
            int randomCol = rand.nextInt(cols);

            // Check if the randomly chosen cell is a CommonSpace
            if (gameBoard.getBoard()[randomRow][randomCol].getCellValue().toString().equals(" ")) {
                // Set this as the starting position for the heroes
                gameBoard.updateBoard(randomRow, randomCol, new BoardCell(new HeroSpace()));
                currentRow = randomRow;
                currentCol = randomCol;
                break;
            }
        }
    }

    @Override
    public void play(Player[] players) {
        displayIntroMessage();

        //First prompt for the heros in the party
        createHeros();
        // Confirm the heroes chosen for the party
        System.out.println("\nYour chosen heroes:");
        displayHeroes(Heros);

        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;

        //Hero will always be on a common space
        BoardCell lastSpace = new CommonSpace();
        BoardCell tempSpace;


        while (gameRunning&& !isGameOver(gameBoard)) {
            System.out.println("\nCurrent Position: (" + (currentRow+1) + ", " + (currentCol+1) + ")");
            System.out.println("Use W, A, S, D to move, I for info, M for market, C to consume potion, Q to quit");
            gameBoard.displayBoard();
            String input = scanner.nextLine().toUpperCase();
            boolean validSpace = false;

            switch (input) {
                case "W":
                    if (isValidMove(currentRow - 1, currentCol,gameBoard)){
                        tempSpace = gameBoard.getBoard()[currentRow-1][currentCol];
                        gameBoard.getBoard()[currentRow-1][currentCol] = new HeroSpace();
                        gameBoard.getBoard()[currentRow][currentCol] = lastSpace;
                        lastSpace = tempSpace;
                        currentRow--;
                        validSpace = true;
                    }
                    else {
                        System.out.println("Cannot move up!");
                    }
                    break;
                case "A":
                    if (isValidMove(currentRow, currentCol-1,gameBoard)){
                        tempSpace = gameBoard.getBoard()[currentRow][currentCol-1];
                        gameBoard.getBoard()[currentRow][currentCol-1] = new HeroSpace();
                        gameBoard.getBoard()[currentRow][currentCol] = lastSpace;
                        lastSpace = tempSpace;
                        currentCol--;
                        validSpace = true;
                    }
                    else {
                        System.out.println("Cannot move left!");
                    }
                    break;
                case "S":
                    if (isValidMove(currentRow + 1, currentCol,gameBoard)){
                        tempSpace = gameBoard.getBoard()[currentRow+1][currentCol];
                        gameBoard.getBoard()[currentRow+1][currentCol] = new HeroSpace();
                        gameBoard.getBoard()[currentRow][currentCol] = lastSpace;
                        lastSpace = tempSpace;
                        currentRow++;
                        validSpace = true;
                    }
                    else {
                        System.out.println("Cannot move down!");
                    }
                    break;
                case "D":
                    if (isValidMove(currentRow, currentCol+1,gameBoard)){
                        tempSpace = gameBoard.getBoard()[currentRow][currentCol+1];
                        gameBoard.getBoard()[currentRow][currentCol+1] = new HeroSpace();
                        gameBoard.getBoard()[currentRow][currentCol] = lastSpace;
                        lastSpace = tempSpace;
                        currentCol++;
                        validSpace = true;
                    }
                    else {
                        System.out.println("Cannot move right!");
                    }
                    break;
                case "I":
                    displayHeroes(Heros);
                    break;
                case "M":
                    if (lastSpace.toString().equals("\u001B[32mM\u001B[0m")) {
                        MarketSpace marketSpace = (MarketSpace) lastSpace.getCellValue();
                        inMarket(marketSpace);

                    } else {
                        System.out.println("You must be in a market space to enter the market.");
                    }
                    break;
                case "C":
                    // Display heroes and prompt the user to choose one for potion use
                    System.out.println("Choose a hero to consume a potion:\n");
                    displayHeroes(Heros);  // Display all heroes with their stats

                    int heroChoice;
                    while (true) {
                        System.out.print("Enter the hero number to use a potion (or 0 to cancel): ");
                        try {
                            heroChoice = Integer.parseInt(scanner.nextLine().trim());

                            if (heroChoice == 0) {
                                System.out.println("Potion use canceled.");
                                break;
                            } else if (heroChoice > 0 && heroChoice <= Heros.size()) {
                                Hero chosenHero = Heros.get(heroChoice - 1);

                                // Use UsePotionStrategy for the selected hero
                                ActionStrategy usePotionStrategy = new UsePotionStrategy();
                                usePotionStrategy.execute(chosenHero, null);  // Pass null as there's no target for potion use

                                System.out.println(chosenHero.getName() + " has used a potion.");
                                break;
                            } else {
                                System.out.println("Invalid selection. Please choose a valid hero number.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a number.");
                        }
                    }
                    break;
                case "Q":
                    System.out.println("Quitting game...");
                    gameRunning = false;
                    break;
                default:
                    System.out.println("Invalid command. Please use W, A, S, D, I, M, or Q.");
            }

            // Check if current space is a CommonSpace and potentially start a battle
            if (lastSpace.toString().equals(" ") && validSpace) {
                Random rand = new Random();
                int diceRoll = rand.nextInt(100) + 1; // Roll a number between 1 and 100

                if (diceRoll <= 37) {  // 37% chance to initiate a battle
                    System.out.println("\n\t\t\t\t\t\t\t\tA battle begins! You encountered monsters!\n");

                    //Create the monsters
                    List<Monster> battleMonsters = new ArrayList<>();
                    battleMonsters = createMonsters();

                    //make Battle Manger and Start the battle
                    BattleManager battleManager = new BattleManager(Heros,battleMonsters);
                    battleManager.startBattle();
                } else {
                    System.out.println("No monsters encountered this time.");
                }
            }
        }
    }


    public void inMarket(MarketSpace marketSpace) {
        Scanner scanner = new Scanner(System.in);
        boolean leaveMarket = false;

        while (!leaveMarket) {
            // Display Market items
            marketSpace.getMarket().displayItems();

            // Display all heroes so the player can choose which hero to enter the market
            System.out.println("\nChoose a hero to enter the market:\n");
            displayHeroes(Heros);

            System.out.print("\nEnter the hero number to enter the market or 0 to leave: ");
            int heroChoice;
            try {
                heroChoice = Integer.parseInt(scanner.nextLine().trim());

                if (heroChoice == 0) {
                    // Leave the market
                    leaveMarket = true;
                    continue;
                }

                // Validate hero choice
                if (heroChoice < 1 || heroChoice > Heros.size()) {
                    System.out.println("Invalid hero choice. Please select a valid hero.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            Hero selectedHero = Heros.get(heroChoice - 1);
            boolean inMarketForHero = true;

            // Hero-specific market actions
            while (inMarketForHero) {
                System.out.println("\nWelcome to the market, \u001B[33;1m" + selectedHero.getName() + "\u001B[0m!");
                marketSpace.getMarket().displayItems();
                System.out.println("\nHero's current money: " + selectedHero.getMoney());
                System.out.println("Choose an action:");
                System.out.println("1. Buy an item");
                System.out.println("2. Sell an item");
                System.out.println("0. Exit market for this hero");

                System.out.print("Enter your choice: ");
                int actionChoice;
                try {
                    actionChoice = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    continue;
                }

                switch (actionChoice) {
                    case 1:  // Buy an item
                        System.out.println("\nChoose an item to buy (enter the item number or 0 to cancel):");
                        marketSpace.getMarket().displayItems();
                        int itemChoice;
                        try {
                            itemChoice = Integer.parseInt(scanner.nextLine().trim());
                            if (itemChoice == 0) break;

                            // Check if item choice is valid
                            List<Item> itemsForSale = marketSpace.getMarket().getItemsForSale();
                            if (itemChoice < 1 || itemChoice > itemsForSale.size()) {
                                System.out.println("Invalid item choice.");
                                break;
                            }

                            Item itemToBuy = itemsForSale.get(itemChoice - 1);

                            // Make the market sell the item to the hero
                            if (marketSpace.getMarket().canBuyItem(itemToBuy, selectedHero)) {
                                marketSpace.getMarket().buyItem(itemToBuy, selectedHero);
                            } else {
                                System.out.println("\nNot enough money or level to buy " + itemToBuy.getName());
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a number.");
                        }
                        break;

                    case 2:  // Sell an item
                        System.out.println("Choose an item to sell (enter the item number or 0 to cancel):");
                        List<Item> heroInventory = selectedHero.getInventory().getItems();
                        if (heroInventory.isEmpty()) {
                            System.out.println("No items to sell.");
                            break;
                        }

                        // Display the hero's inventory
                        for (int i = 0; i < heroInventory.size(); i++) {
                            Item item = heroInventory.get(i);
                            System.out.println((i + 1) + ". " + item.getName() + " - Price: " + item.getPrice());
                        }
                        int sellChoice;
                        try {
                            sellChoice = Integer.parseInt(scanner.nextLine().trim());
                            if (sellChoice == 0) break;

                            // Check if sell choice is valid
                            if (sellChoice < 1 || sellChoice > heroInventory.size()) {
                                System.out.println("Invalid item choice.");
                                break;
                            }

                            // Have the player sell the item to the player
                            Item itemToSell = heroInventory.get(sellChoice - 1);
                            marketSpace.getMarket().sellItem(itemToSell, selectedHero);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a number.");
                        }
                        break;

                    case 0:  // Exit market for this hero
                        inMarketForHero = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                        break;
                }
            }
        }
    }

    // Method to create Monsters and returns the lsit of moneers
    private List<Monster> createMonsters(){
        int numMonsters = numHeros;
        List<Monster> monsters = new ArrayList<>();

        //Make a monster at the same level of each hero
        for (Hero hero : Heros) {
            monsters.add(MonsterFactory.getRandomMonster(hero.getExperienceLevel()));
        }

        return  monsters;
    }

    // Method to prompt the player to create heroes
    private void createHeros() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the number of heroes to have in your party (1-" + maxHeros + "): ");
            String input = scanner.nextLine().trim();

            try {
                numHeros = Integer.parseInt(input);

                if (numHeros >= 1 && numHeros <= maxHeros) {
                    break;  // Valid input; exit the loop
                } else {
                    System.out.println("Invalid! Please enter a number between 1 and " + maxHeros);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number between 1 and " + maxHeros + ".");
            }
        }

        //Generate a list of 10 Random Heros for the user to select for thier party
        List<Hero> availableHeroes = new ArrayList<>(); // List of available heroes for selection
        for (int i = 0; i < 10; i++) {
            availableHeroes.add(HeroFactory.getRandomHero());
        }

        System.out.println(" These are your available Heroes:");
        displayHeroes(availableHeroes);


        // List to store the player's chosen heroes
        List<Hero> chosenHeroes = new ArrayList<>();


        // Loop to select heroes for the party
        for (int i = 0; i < numHeros; i++) {
            System.out.println("\nSelect hero " + (i + 1) + " of " + numHeros + ":");
            int choice;

            // Validate input to ensure the player selects a valid hero
            while (true) {
                System.out.print("Enter the number of the hero you'd like to recruit: ");
                String input = scanner.nextLine().trim(); // Read input as a string
                try {
                    choice = Integer.parseInt(input); // Try to parse the input as an integer
                    if (choice >= 1 && choice <= availableHeroes.size()) {
                        break; // Valid choice, exit the loop
                    } else {
                        System.out.println("Invalid selection. Please choose a number between 1 and " + availableHeroes.size());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                }
            }

            // Add the chosen hero to the party and remove from available heroes
            Hero selectedHero = availableHeroes.get(choice - 1);
            chosenHeroes.add(selectedHero);
            availableHeroes.remove(choice - 1);

            // Display remaining available heroes after each selection
            if (!availableHeroes.isEmpty()) {
                System.out.println("\nRemaining heroes:");
                displayHeroes(availableHeroes);
            }
        }

        // Update the global hero list with the chosen heroes
        Heros = chosenHeroes;

        //Give them "Bare Hands" weapon by default

        for(Hero hero: Heros){
            Item hands = new Weapon("Bare Hands",0,1,500,2);
            ((Weapon) hands).equip();
            hero.getInventory().addItem(hands);
        }
    }

    // Method to display into message in the beggining of the game
    public void displayIntroMessage(){
        System.out.println("Welcome to Monsters and Heroes!");
        System.out.println("In this game, you'll lead a team of heroes on a quest to battle monsters, collect items, and level up.");
        System.out.println("Instructions:");
        System.out.println(" - Use W, A, S, D keys to move your heroes across the 8x8 board.");
        System.out.println(" - Use Q to quit the game, I to show information, and M to enter the market.");
        System.out.println(" - When you enter a Common space, you may encounter monsters to fight.");
        System.out.println(" - Enter Market spaces (M) to buy or sell items that aid in battles.");
        System.out.println(" - Avoid Inaccessible spaces (X) — your heroes cannot pass through them.");
        System.out.println(" - Your hero party is denoted by the H on the board.");
        System.out.println("Goal: Defeat monsters, collect rewards, and see how long your heroes can survive!");
        System.out.println("Good luck, and may your heroes emerge victorious!\n\n");
    }

    // Method to display all heroes and their stats
    public static void displayHeroes(List<Hero> heroes) {
        if (heroes.isEmpty()) {
            System.out.println("No heroes available to display.");
            return;
        }

        // Print header with column titles, using tabs for alignment
        System.out.println("Name\t\t\t\t\tType\t\tHP\t Mana\tStrength\tAgility\tDexterity\tMoney\tExperience Level\tExperience Points");

        // Print a line separator for readability
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");

        // Loop through each hero and print their stats
        int counter = 1;
        for (Hero hero : heroes) {
            System.out.print("\u001B[33;1m" + counter + ". ");
            counter++;
            System.out.printf("%-21s%-11s%-6d%-8d%-12d%-8d%-12d%-8d%-18d%-18d\u001B[0m\n",
                    hero.getName(),
                    hero.getType(),
                    hero.getHP(),
                    hero.getMana(),
                    hero.getStrength(),
                    hero.getAgility(),
                    hero.getDexterity(),
                    hero.getMoney(),
                    hero.getExperienceLevel(),
                    hero.getExperiencePoints()
            );

            // Get the hero's inventory and display it if not empty
            hero.getInventory().displayInventory();
        }
    }

    // Method to display all monsters and their stats
    public static void displayMonsters(List<Monster> monsters) {
        if (monsters.isEmpty()) {
            System.out.println("No monsters available to display.");
            return;
        }

        // Print header with column titles, using tabs for alignment
        System.out.println("Name\t\t\t\t\t\t\tHP\tDamage\tDefense\t\tDodge Chance\tLevel");

        // Print a line separator for readability
        System.out.println("--------------------------------------------------------------------------------------------");

        // Loop through each monster and print their stats
        int counter = 1;
        for (Monster monster : monsters) {
            System.out.print(counter + ". ");
            counter++;
            System.out.printf("%-28s%-6d%-8d%-14d%-15.2f%-8d\n",
                    monster.getName(),
                    monster.getHP(),
                    monster.getDamage(),
                    monster.getDefense(),
                    (monster.getDodgeChance()*100),  // Convert to percentage for readability
                    monster.getLevel()
            );
        }
    }


    @Override
    public boolean isValidMove(int row, int col, Board gameBoard) {
        // Check if the move is within the boundaries of the board
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return false;
        }

        // Check if the target cell is an InaccessibleSpace (marked with "X")
        BoardCell targetCell = gameBoard.getBoard()[row][col];
        if (targetCell.toString().equals("\u001B[31mX\u001B[0m")) {
            return false;
        }

        // If both conditions are met, the move is valid
        return true;
    }

    @Override
    public boolean isGameOver(Board gameboard) {
        if(Heros.isEmpty())
            return true;
        return false;
    }
}
