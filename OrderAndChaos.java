//Jonah Rothman
//Order and Chaos.Java
//This is class that have the methods needed to run Order and Chaos

import java.util.Scanner;

public class OrderAndChaos implements Game {
    private Board gameboard = null;
    private final int size = 6;

    // Constructor
    public OrderAndChaos() {
        gameboard = new Board(size, size);
    }



    //This method initiates and plays the order and choas game
    @Override
    public void play(Player players[]) {
        Scanner scanner = new Scanner(System.in);
        int playerTurn = 2; // start with Player 2 so the first switch changes it to Player 1
        String currentPlayerSymbol;

        //Set turn vars so we know when the board is filled
        int currentTurn = 0;
        int maxTurns = size * size;

        do {
            // switch players turn at the start
            if (playerTurn == 1) {
                playerTurn = 2;
            } else {
                playerTurn = 1;
            }

            // Display the current board
            gameboard.displayBoard();

            // Ask the current player whether they want to place an X or O
            boolean validSymbol = false;
            BoardCell currentPlayerPiece = null;
            while (!validSymbol) {
                System.out.println("Player " + playerTurn + ", do you want to place an X or an O?");
                currentPlayerSymbol = scanner.next().toUpperCase();
                if (currentPlayerSymbol.equals("X")){
                    currentPlayerPiece  = new XPiece();
                    validSymbol = true;
                }
                else if (currentPlayerSymbol.equals("O")) {
                    currentPlayerPiece  = new OPiece();
                    validSymbol = true;
                } else {
                    System.out.println("Invalid choice. Please enter either X or O.");
                }
            }

            System.out.println("Player " + playerTurn + " (" + currentPlayerPiece + "), it's your turn.");
            System.out.println("Enter your move (row and column): ");

            int rowMove = 0;
            int colMove = 0;

            boolean validMove = false;

            //Make sure input is valid loop
            while (!validMove) {
                //Input Row
                System.out.print("Row (1-" + (size) + "): ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.next(); // Clear the invalid input
                    continue; // Restart the loop
                }
                rowMove = scanner.nextInt();
                rowMove--; //decrase for bounds

                // Input Col
                System.out.print("Column (1-" + (size) + "): ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.next(); // Clear the invalid input
                    continue; // Restart the loop
                }
                colMove = scanner.nextInt();
                colMove--;

                //check if the move is valid
                if (isValidMove(rowMove, colMove,gameboard)) {
                    validMove = true;
                } else {
                    System.out.println("Invalid move. The cell is already occupied or out of bounds. Try again.");
                }
            }

            // update the board with the players move
            gameboard.updateBoard(rowMove, colMove, currentPlayerPiece);

            // Check if the game is over
            if (isGameOver(gameboard)) {
                gameboard.displayBoard();
                System.out.println("Player " + playerTurn + " (" + currentPlayerPiece + ") wins!");

                //Update player win count
                if(playerTurn==1){
                    players[0].incrementScore("Order and Chaos");
                }
                else{
                    players[1].incrementScore("Order and Chaos");
                }

                break;
            }

            currentTurn++;
        } while (!isGameOver(gameboard) && currentTurn < maxTurns);

        // if the game ends in a draw
        if (currentTurn == maxTurns && !isGameOver(gameboard)) {
            gameboard.displayBoard();
            System.out.println("The board is full! No one wins!");
        }
    }


    //This is a helper method to see if a move made by a player is valid
    @Override
    public boolean isValidMove(int row, int col, Board gameboard) {
        // check if the row and column are within bounds
        if (row < 0 || row >= 6 || col < 0 || col >= 6) {
            return false;
        }
        // check if the cell is blank
        return gameboard.getBoard()[row][col].getCellValue() == null;
    }

    //This is a helper method to see if the game is over
    @Override
    public boolean isGameOver(Board gameboard) {
        // check Rows
        for (int r = 0; r < size; r++) {
            for (int c = 0; c <= size - 5; c++) { // To fit 5 cells in the row
                if (checkRow(r, c, 0, 1)) {  // Check 5 horizontally
                    return true;
                }
            }
        }

        //Check columns
        for (int c = 0; c < size; c++) {
            for (int r = 0; r <= size - 5; r++) { // To fit 5 cells in the column
                if (checkRow(r, c, 1, 0)) {  // Check 5 vertically
                    return true;
                }
            }
        }

        // check all Diagonals (top left to bottom right)
        for (int r = 0; r <= size - 5; r++) {
            for (int c = 0; c <= size - 5; c++) {
                if (checkRow(r, c, 1, 1)) {  // Check 5 diagonally (down-right)
                    return true;
                }
            }
        }

        // check  all diagonals (Bottom-left to Top-right)
        for (int r = size - 1; r >= 4; r--) {
            for (int c = 0; c <= size - 5; c++) {
                if (checkRow(r, c, -1, 1)) {  // Check 5 diagonally (up-right)
                    return true;
                }
            }
        }

        //If no winner was found
        return false;
    }

    // Helper method to check 5 in a row in any direction
    public boolean checkRow(int startRow, int startCol, int rowIncrement, int colIncrement) {
        BoardCell[][] grid = gameboard.getBoard();

        // Make sure the starting point is valid
        if (startRow < 0 || startRow >= 6 || startCol < 0 || startCol >= 6) {
            return false;
        }

        Object firstCell = grid[startRow][startCol].getCellValue();

        if (firstCell ==  null) {
            return false;  // Skip blank cells
        }

        for (int i = 1; i < 5; i++) {
            Object cellVal = grid[startRow + i * rowIncrement][startCol + i * colIncrement].getCellValue();
            if (cellVal == null){
                return  false;
            }
            if (!cellVal.toString().equals(firstCell.toString())) {
                return false;
            }
        }
        return true;  // All 5 cells match
    }
}
