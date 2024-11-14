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
    private ValorBattle valorBattle;



    //Default constructor initialized the display and superboard
    public LegendsOfValor(){
        gameBoard = new Board(rows,cols);
        Heros = new ArrayList<>();
        // Setting positions for each player
        playerPositions[0][0] = 7; // Player 0's row
        playerPositions[0][1] = 0; // Player 0's column

        playerPositions[1][0] = 7; // Player 1's row
        playerPositions[1][1] = 3; // Player 1's column

        playerPositions[2][0] = 7; // Player 2's row
        playerPositions[2][1] = 6; //

        initializeBoard();
    }

    // Method to initialize the game board with random spaces
    private void initializeBoard() {
        // Total cells in the board
        int totalCells = 45; //subtracting restricted spaces and hero starting spaces

        // Calculate numbers of each space type
        int numBush = 9;
        int numCave = 9;
        int numKoulou = 9;
        int numCommon = 18;



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
                else if((r == 7) && (c == 0 || c == 3 || c == 6) ) {
                    int heroNum = c/3;
                    int row = playerPositions[heroNum][0];
                    int col = playerPositions[heroNum][1];
                    gameBoard.getBoard()[row][col] = new BoardCell(new HeroSpace(heroNum));
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
       gameBoard.displayBoard();
       valorBattle.round();
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

    public List<Hero> getHeroes() {
        return Heros;
    }

    public boolean getHeroAction(int heroIdx){
        // return false if not a valid move (buying & selling)

        // return true if valid move(move/attack monsters)
        // if attack monsters: choose monster, call valorBattle.withinAttackRange to verify
        // valorBattle.heroMove(hero, monster, 0);
        return true;
    }
}
