//Jonah Rothman
//MonstersAndHeros.Java
//This is class that have the methods needed to run Monsters and Heros

import java.util.*;

public class LegendsOfValor implements Game{

    private Board gameBoard;
    private final int rows=8;
    private final int cols=8;
    private final int numHeros = 3;
    private List<Hero> Heros; // List of heroes chosen by the player
    // Initial position of the heroes, first index is which player 0-2 and second index is 0 for row 1 for col
    int[][] playerPositions = new int[3][2]; // 3 players, each with [row, col]
    final int[][] nexusEndPositions = {{0, 0}, {0, 3}, {0, 6}};
    final int[][] nexusBeginPositions = {{7, 0}, {7, 3}, {7, 6}};
    private ValorBattle valorBattle;
    Scanner scanner = new Scanner(System.in);



    //Default constructor initialized the display and superboard
    public LegendsOfValor(){
        gameBoard = new Board(rows,cols);

        initiateHeroes();
        // Setting positions for each player
        playerPositions[0][0] = 7; // Player 0's row
        playerPositions[0][1] = 0; // Player 0's column

        playerPositions[1][0] = 7; // Player 1's row
        playerPositions[1][1] = 3; // Player 1's column

        playerPositions[2][0] = 7; // Player 2's row
        playerPositions[2][1] = 6; //

        initializeBoard();
    }

    public void initiateHeroes(){
        // TODO: Logic for Selecting Heroes

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

        Heros = chosenHeroes;

        //Give them "Bare Hands" weapon by default

        for(Hero hero: Heros){
            Item hands = new Weapon("Bare Hands",0,1,500,2);
            ((Weapon) hands).equip();
            hero.getInventory().addItem(hands);
        }

    }


    // Method to initialize the game board with random spaces
    private void initializeBoard() {
        // Total cells in the board
        int totalCells = 45; //subtracting restricted spaces and hero starting spaces

        // Calculate numbers of each space type
        int numBush = 9;
        int numCave = 9;
        int numKoulou = 9;
        int numCommon = 22;


        // Create lists for each space type
        ArrayList<BoardCell> spaces = new ArrayList<>();

        // Add specific space objects to the list based on the calculated number
        for (int i = 0; i < numBush; i++) {
            spaces.add(new BushSpace());
        }
        for (int i = 0; i < numCave; i++) {
            spaces.add(new CaveSpace());
        }
        for (int i = 0; i < numKoulou; i++) {
            spaces.add(new KoulouSpace());
        }
        for (int i = 0; i < numCommon; i++) {
            spaces.add(new CommonSpace());
        }

        // Shuffle spaces to randomize placement
        Collections.shuffle(spaces, new Random());


        int index = 0;
        // Put in lines for innacessable spaces
        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < cols; c++) {

                //Ineccesible space
                if((c+1)%3==0) {
                    gameBoard.getBoard()[r][c] = new BoardCell(new InaccessibleSpace());
                }
                //Make Hero Nexus space
                else if((r == 7) && (c == 0 || c == 3 || c == 6 ||c == 1 || c == 4 || c == 7) ) {;
                     gameBoard.getBoard()[r][c] = new BoardCell(new NexusSpace());
                }
                //Make Monster Nexus space
                else if((r == 0) && (c == 0 || c == 3 || c == 6 ||c == 1 || c == 4 || c == 7) ) {;
                    gameBoard.getBoard()[r][c] = new BoardCell(new MonsterNexusSpace());
                }
                else{
                    gameBoard.getBoard()[r][c] = new BoardCell(spaces.get(index));
                    index++;
                }

            }
        }

        valorBattle = new ValorBattle(this);

    }


    @Override
    public void play(Player[] players) {
        while (!isGameOver(gameBoard)) {
            valorBattle.round();
            displayBoard();
        }
    }

    // TODO: Monsters and Heroes can share the same cell. Additionally, when they are in a
    //  special cell, it’s better to display the cell information as well. Therefore, instead
    //  of creating monster & Hero cell, perhaps we should extend the boardCell to display
    //  the hero/monster in it.
    public void displayBoard(){
        Map<int[], BoardCell> originalCells = new HashMap<>();

        for (int[] pos : playerPositions) {
            originalCells.put(pos, gameBoard.getBoard()[pos[0]][pos[1]]);
        }

        for (int[] pos : valorBattle.getMonstersPositions()) {
            originalCells.put(pos, gameBoard.getBoard()[pos[0]][pos[1]]);
        }

        for (int i = 0; i < playerPositions.length; i++) {
            gameBoard.getBoard()[playerPositions[i][0]][playerPositions[i][1]] = new BoardCell(new HeroSpace(i));
        }

        for (int i = 0; i < valorBattle.getMonstersPositions().size(); i++) {
            int[] monsterPos = valorBattle.getMonstersPositions().get(i);
            gameBoard.getBoard()[monsterPos[0]][monsterPos[1]] = new BoardCell(new MonsterSpace());
        }

        gameBoard.displayBoard();

        for (Map.Entry<int[], BoardCell> entry : originalCells.entrySet()) {
            int[] pos = entry.getKey();
            gameBoard.getBoard()[pos[0]][pos[1]] = entry.getValue();
        }
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

        List<int[]> monsterPos = valorBattle.getMonstersPositions();

        //Make sure Not above a monster
        for(int[] pos : monsterPos) {
            if(row<pos[0] && col == pos[1]) {
                System.out.println("Can not move above monster");
                return false;
            }
        }

        //Make sure hero 1, 2 or 3 isn't there
        if(row == playerPositions[0][0] && col == playerPositions[0][1]){
            System.out.println("Invalid coordinates, hero 1 occupies this spot");
            return false;
        } else if(row == playerPositions[1][0] && col == playerPositions[1][1]){
            System.out.println("Invalid coordinates, hero 2 occupies this spot");
            return false;
        } else if(row == playerPositions[2][0] && col == playerPositions[2][1]){
            System.out.println("Invalid coordinates, hero 3 occupies this spot");
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
        // TODO: More Logic for whether the game is over
        if(Heros.isEmpty())
            return true;
        return false;
    }

    public List<Hero> getHeroes() {
        return Heros;
    }

    public void promptForInput(int heroIdx){
        System.out.println("Player, enter your move for hero " +  (heroIdx + 1) +": (w,a,s,d, m (nexus market) , " +
                "e (equip), i (info), b (attack), r (recall), t (teleport), q (quit)): ");
    }

    public Object[] readInput() {
        try {
            String input = scanner.nextLine().trim();

            switch (input.toLowerCase()) {
                case "w":
                    return new Object[]{"move", "up"};
                case "a":
                    return new Object[]{"move", "left"};
                case "s":
                    return new Object[]{"move", "down"};
                case "d":
                    return new Object[]{"move", "right"};
                case "q":
                    return new Object[]{"quit"};
                case "i":
                    return new Object[]{"show information"};
                case "b":
                    return new Object[]{"attack"};
                case "r":
                    return new Object[]{"recall"};
                case "t":
                    return new Object[]{"teleport"};
                case "m":
                    return new Object[]{"market"};
                case "e":
                    return new Object[]{"equip"};
                default:
                    System.out.println("Invalid input. Please try again.");
                    return null;
            }
        } catch (Exception e) {
            System.out.println("An error occurred while reading input. Please try again.");
            return null;
        }
    }

    private boolean moveHero(String direction, int heroIdx) {
        int row = playerPositions[heroIdx][0];
        int col = playerPositions[heroIdx][1];
        int newRow = row;
        int newCol = col;

        switch (direction) {
            case "up":
                newRow--;
                break;
            case "down":
                newRow++;
                break;
            case "left":
                newCol--;
                break;
            case "right":
                newCol++;
                break;
            default:
                System.out.println("Invalid direction.");
                return false;
        }

        if (!isValidMove(newRow, newCol, gameBoard)) {
            System.out.println("You cannot move there. The space is inaccessible or out of bounds.");
            return false;
        }

        playerPositions[heroIdx][0] = newRow;
        playerPositions[heroIdx][1] = newCol;
        Hero hero = Heros.get(heroIdx);
        BoardCell newSpace = gameBoard.getBoard()[newRow][newCol];
        BoardCell oldSpace = gameBoard.getBoard()[row][col];
        updateStatsWhenMoved(hero, newSpace, oldSpace);

        return true;
    }

    public void updateStatsWhenMoved(Hero hero,BoardCell newSpace, BoardCell oldSpace) {
        //take away previous bonus (divide by 1.5)
        if(oldSpace.getCellValue() instanceof BushSpace){
            int dexterity = hero.getDexterity();
            int newDexterity = (int) (dexterity/1.5);
            hero.increaseDexterity( (newDexterity-dexterity));
            System.out.println("\u001B[31mLeaving BushSpace. Changing dexterity from "+ dexterity+" -> "+newDexterity+"\u001B[0m");
        } else if (oldSpace.getCellValue() instanceof CaveSpace) {
            int agility = hero.getAgility();
            int newAgility = (int) (agility/1.5);
            hero.increaseAgility( (newAgility-agility));
            System.out.println("\u001B[31mLeaving to CaveSpace. Changing agility from "+ agility+" -> "+newAgility+"\u001B[0m");
        } else if (oldSpace.getCellValue() instanceof KoulouSpace) {
            int strength = hero.getStrength();
            int newStrength = (int) (strength/1.5);
            hero.increaseStrength( (newStrength-strength));
            System.out.println("\u001B[31mLeaving to KoulouSpace. Changing strength from "+ strength+" -> "+newStrength+"\u001B[0m");
        }

        //Add Bonuses (multiply by 1.5)
        if(newSpace.getCellValue() instanceof BushSpace){
            int dexterity = hero.getDexterity();
            int newDexterity = (int) (dexterity*1.5);
            hero.increaseDexterity( (newDexterity-dexterity));
            System.out.println("\u001B[32mGoing to BushSpace. Changing dexterity from "+ dexterity+" -> "+newDexterity+"\u001B[0m");
        } else if (newSpace.getCellValue() instanceof CaveSpace) {
            int agility = hero.getAgility();
            int newAgility = (int) (agility*1.5);
            hero.increaseAgility( (newAgility-agility));
            System.out.println("\u001B[32mGoing to CaveSpace. Changing agility from "+ agility+" -> "+newAgility+"\u001B[0m");
        } else if (newSpace.getCellValue() instanceof KoulouSpace) {
            int strength = hero.getStrength();
            int newStrength = (int) (strength*1.5);
            hero.increaseStrength( (newStrength-strength));
            System.out.println("\u001B[32mGoing to KoulouSpace. Changing strength from "+ strength+" -> "+newStrength+"\u001B[0m");
        }
    }

    public Object[] selectTargetAndActionType(){
        int actionChoice = 0;
        int targetChoice = 0;
        List<Monster> monsters = valorBattle.getMonsters();

        if (monsters.isEmpty()) {
            System.out.println("No monsters available to target.");
            return null;
        }

        while (true) {
            try {
                System.out.println("Select an action:");
                System.out.println("1. Attack");
                System.out.println("2. Spell");
                System.out.println("3. Potion");
                System.out.print("Enter your choice (1-3): ");
                actionChoice = Integer.parseInt(scanner.nextLine());

                if (actionChoice < 1 || actionChoice > 3) {
                    throw new IllegalArgumentException("Invalid action choice. Please select 1, 2, or 3.");
                }

                System.out.println("\nSelect a target:");
                for (int i = 0; i < monsters.size(); i++) {
                    System.out.println((i + 1) + ". " + monsters.get(i).getName());
                }
                System.out.print("Enter your choice (1-" + monsters.size() + "): ");
                targetChoice = Integer.parseInt(scanner.nextLine());

                if (targetChoice < 1 || targetChoice > monsters.size()) {
                    throw new IllegalArgumentException("Invalid target choice. Please select a valid monster.");
                }

                return new Object[]{actionChoice, targetChoice - 1};
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean getHeroAction(int heroIdx){
        // return false if not a valid move (buying & selling & invalid input)
        promptForInput(heroIdx);

        Object[] inputs = readInput();
        if(inputs == null){
            return false;
        }
        String command = (String) inputs[0];

        switch (command) {
            case "move":
                String direction = (String) inputs[1];
                return moveHero(direction, heroIdx);
            case "attack":
                Object[] result = selectTargetAndActionType();
                if(result == null){
                    return false;
                }
                int actionChoice = (int) result[0];
                int monsterIdx = (int) result[1];
                if(!valorBattle.withinAttackRange(heroIdx, monsterIdx)){
                    System.out.println("Hero " + (heroIdx+1) + " can not attack this monster, it is out of range!");
                    return false;
                }
                valorBattle.heroMove(heroIdx, monsterIdx, actionChoice);
                return true;
            case "quit":
                System.out.println("Quitting the game.");
                //TODO Make implementation
                return false;
            case "show information":
                displayHeroes(getHeroes());
                System.out.println();
                return false; //Doesnt count as turn
            case "recall":
                playerPositions[heroIdx][0] = nexusBeginPositions[heroIdx][0];
                playerPositions[heroIdx][1] = nexusBeginPositions[heroIdx][1];
                System.out.println("Player "+(heroIdx+1)+" recalled to nexus!");
                return true; //Counts as turn
            case "teleport":
                telelport(heroIdx); // Call helper function
                return true; //counts as turn
            case "market":
                //Make sure in nexus spot
                int r = playerPositions[heroIdx][0];
                int c = playerPositions[heroIdx][1];

                if((r == 7) && (c == 0 || c == 3 || c == 6 ||c == 1 || c == 4 || c == 7) ) {
                    BoardCell boardCell = (NexusSpace) gameBoard.getBoard()[r][c].getCellValue();
                    inMarket((NexusSpace) boardCell,heroIdx);
                    return true;
                }

                System.out.println("Not at nexus space. Please try again.");
                return false;
            case "equip":
                equipItems(heroIdx);
                return false; //Doesnt count as a turn
            default:
                return false;
        }
    }

    // Function will attempt to teleport heroInx to new spot.
    private void telelport(int heroIdx){
        int row = 0;
        int col = 0;
        int targetIndex;
        BoardCell oldSpace = gameBoard.getBoard()[row][col];

        System.out.println("\nEnter the coordinates where you want to teleport to:");

        while (true) {
            try {
                System.out.print("Row:");
                row = Integer.parseInt(scanner.nextLine());
                System.out.print("Col:");
                col = Integer.parseInt(scanner.nextLine());
                System.out.println();

                // Decrement the values
                row--;
                col--;

                // Differnt case values for each hero
                if (heroIdx == 0) {
                    if(col>2 && col<5) {                     //Teleporting to hero 2
                        targetIndex = 1;
                    } else if (col>5 && col<8) {             //Teleporting to hero 3
                        targetIndex = 2;
                    } else {
                        //Error different lane not selected
                        throw new IllegalArgumentException("Invalid coordinates, please go to lane 2 or 3");
                    }
                }else if (heroIdx == 1) {
                    if(col>=0 && col<2) {                     //Teleporting to hero 1
                        targetIndex = 0;
                    } else if (col>5 && col<8) {              //Teleporting to hero 3
                        targetIndex = 2;
                    } else {
                        //Error different lane not selected
                        throw new IllegalArgumentException("Invalid coordinates, please go to lane 1 or 3");
                    }
                } else {
                    if(col>=0 && col<2) {                     //Teleporting to hero 1
                        targetIndex = 0;
                    } else if(col>2 && col<5) {               //Teleporting to hero 2
                        targetIndex = 1;
                    } else {
                        //Error different lane not selected
                        throw new IllegalArgumentException("Invalid coordinates, please go to lane 1 or 2");
                    }
                }

                int targetRow = playerPositions[targetIndex][0];
                int targetCol = playerPositions[targetIndex][1];

                //Make sure hero 1, 2 or 3 isn't there
                if(row == playerPositions[0][0] && col == playerPositions[0][1]){
                    throw new IllegalArgumentException("Invalid coordinates, hero 1 occupies this spot");
                } else if(row == playerPositions[1][0] && col == playerPositions[1][1]){
                    throw new IllegalArgumentException("Invalid coordinates, hero 2 occupies this spot");
                } else if(row == playerPositions[2][0] && col == playerPositions[2][1]){
                    throw new IllegalArgumentException("Invalid coordinates, hero 3 occupies this spot");
                }

                //Make sure not teleporting infront of  hero
                if(row < targetRow)
                    throw new IllegalArgumentException("Invalid coordinates, must go adjacent  or below hero ");

                //Make sure either to right or left
                if(!( (row == targetRow && Math.abs(col - targetCol) == 1 ) ||
                        (Math.abs(row - targetRow) == 1 && col == targetCol))) {
                    throw new IllegalArgumentException("Invalid coordinates, must go adjacent or below hero ");
                }

                // If all checks passed, break the loop
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        //Move the player once we found valid inputs
        playerPositions[heroIdx][0] = row;
        playerPositions[heroIdx][1] = col;

        BoardCell newSpace = gameBoard.getBoard()[row][col];
        Hero hero = Heros.get(heroIdx);
        updateStatsWhenMoved(hero, newSpace, oldSpace);
    }

    // Method will complete the inMarket actions for a hero
    private void inMarket(NexusSpace market, int heroIdx) {
        Scanner scanner = new Scanner(System.in);
        boolean leaveMarket = false;

            Hero selectedHero = Heros.get(heroIdx);
            boolean inMarketForHero = true;

            // Hero-specific market actions
            while (inMarketForHero) {
                System.out.println("\nWelcome to the market, \u001B[33;1m" + selectedHero.getName() + "\u001B[0m!");
                market.getMarket().displayItems();
                System.out.println("\nHero's current money: " + selectedHero.getMoney());
                System.out.println("Choose an action:");
                System.out.println("1. Buy an item");
                System.out.println("2. Sell an item");
                System.out.println("0. Exit market");

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
                        market.getMarket().displayItems();
                        int itemChoice;
                        try {
                            itemChoice = Integer.parseInt(scanner.nextLine().trim());
                            if (itemChoice == 0) break;

                            // Check if item choice is valid
                            List<Item> itemsForSale = market.getMarket().getItemsForSale();
                            if (itemChoice < 1 || itemChoice > itemsForSale.size()) {
                                System.out.println("Invalid item choice.");
                                break;
                            }

                            Item itemToBuy = itemsForSale.get(itemChoice - 1);

                            // Make the market sell the item to the hero
                            if (market.getMarket().canBuyItem(itemToBuy, selectedHero)) {
                                market.getMarket().buyItem(itemToBuy, selectedHero);
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
                            market.getMarket().sellItem(itemToSell, selectedHero);
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

    // Method to equip items
    private void equipItems(int heroInx) {
        Hero hero = Heros.get(heroInx);
        boolean equipMenuActive = true;

        //get Hands Available
        Inventory inventory = hero.getInventory();
        int handsAvailable = hero.getHandsAvailable();

        System.out.println("\nHello hero "+(heroInx+1)+", you have " + handsAvailable + "/2 hands available.\nThis is your inventory:");
        Inventory heroInventory = hero.getInventory();
        heroInventory.displayInventory();

        while (equipMenuActive) {
            System.out.println("\nEquipment Menu: ");
            System.out.println("1. Equip Item");
            System.out.println("2. Unequip Item");
            System.out.println("3. Leave Equipment Menu");

            System.out.print("Choose an option (1-3): ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1": // Equip Item
                    inventory.displayInventory();
                    System.out.print("Enter the item number to equip:  ");
                    try {
                        int itemIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;

                        if (itemIndex >= 0 && itemIndex < inventory.getItems().size()) {
                            Item item = inventory.getItems().get(itemIndex);

                            if (item instanceof Armor) {
                                if (hero.hasEquippedArmor()) {
                                    System.out.println("You already have an armor equipped. Unequip it first to equip a new one. ");
                                } else {
                                    ((Equipable) item).equip();
                                    System.out.println(item.getName() + " equipped as armor.");
                                }
                            } else if (item instanceof Weapon) {
                                int requiredHands = ((Weapon) item).getHandsRequired();
                                if (requiredHands <= handsAvailable) {
                                    ((Equipable) item).equip();
                                    handsAvailable -= requiredHands;
                                    System.out.println(item.getName() + " equipped as a weapon. "+handsAvailable+" hands available. ");
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
                    System.out.print("Enter the item number to unequip:  ");
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
}
