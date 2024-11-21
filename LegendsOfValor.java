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
        Heros = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            Heros.add(HeroFactory.getRandomHero());
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
                //Make hero space
//                else if((r == 7) && (c == 0 || c == 3 || c == 6) ) {
//                    int heroNum = c/3;
//                    int row = playerPositions[heroNum][0];
//                    int col = playerPositions[heroNum][1];
//                    gameBoard.getBoard()[row][col] = new BoardCell(new HeroSpace(heroNum));
//                }
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
        // TODO: More Actions
        System.out.println("Player, enter your move for hero " +  (heroIdx + 1) +": (w/a/s/d/q/i/b): ");
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

        // TODO: Check if the move is valid for the specific hero
        if (!isValidMove(newRow, newCol, gameBoard)) {
            System.out.println("You cannot move there. The space is inaccessible or out of bounds.");
            return false;
        }

        playerPositions[heroIdx][0] = newRow;
        playerPositions[heroIdx][1] = newCol;

        return true;
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
            default:
                return false;
        }
    }


}
