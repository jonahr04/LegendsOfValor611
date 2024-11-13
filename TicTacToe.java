//Jonah Rothman
//TicTacToe.Java
//This is class that have the methods needed to run Tic Tac Toe

import java.util.Scanner;

public class TicTacToe implements Game {
    private Board gameboard = null;
    private int rows;
    private int cols;

    //Default Constructor
    public TicTacToe() {
        gameboard = new Board(3, 3);
        rows = 3;
        cols = 3;
    }

    //Overloaded Constructor
    public TicTacToe(int r, int c) {
        gameboard = new Board(r, c);
        rows = r;
        cols = c;
    }

    //This method initiates and plays the tic tac toe game
    @Override
    public void play(Player players[]) {
        Scanner scanner = new Scanner(System.in);
        int playerTurn = 2;

        //Set turn vars so we know when the board is filled
        int currentTurn = 0;
        int maxTurns = rows* cols;

        //Play game until it is over
        do {
            //Switch players turn at the start
            if(playerTurn == 1){
                playerTurn = 2;
            }
            else{
                playerTurn = 1;
            }

            BoardCell currentPlayerPiece = null;
            //Set current player symbol based on player turn
            if (playerTurn == 1) {
                currentPlayerPiece  = new XPiece();
            } else {
                currentPlayerPiece  = new OPiece();
            }

            // Display the current board
            gameboard.displayBoard();

            System.out.println("Player " + playerTurn + " (" + currentPlayerPiece + "), it's your turn.");
            System.out.println("Enter your move (row and column): ");


            int rowMove = 0;
            int colMove = 0;

            boolean validMove = false;

            //Make sure input is valid loop
            while (!validMove) {
                //Input Row
                System.out.print("Row (1-" + (rows) + "): ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.next(); // Clear the invalid input
                    continue; // Restart the loop
                }
                rowMove = scanner.nextInt();
                rowMove--; //decrase for bounds

                // Input Col
                System.out.print("Column (1-" + (cols) + "): ");
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

            // update the board with the player's move
            gameboard.updateBoard(rowMove, colMove, currentPlayerPiece);
            //System.out.println("Updated Board at "+rowMove+", "+colMove+" with :"+currentPlayerPiece);

            // Check if the game is over
            if (isGameOver(gameboard)) {
                gameboard.displayBoard();
                System.out.println("Player " + playerTurn + " (" + currentPlayerPiece + ") wins!");

                //Update player win count
                if(playerTurn==1){
                    players[0].incrementScore("Tic-Tac-Toe");
                }
                else{
                    players[1].incrementScore("Tic-Tac-Toe");
                }
                break;
            }

            currentTurn++;
        } while (!isGameOver(gameboard) && currentTurn<maxTurns);

        // if the game ends in a draw
        if(currentTurn == maxTurns && !isGameOver(gameboard)){
            gameboard.displayBoard();
            System.out.println("The board is full! no one wins!");
        }

    }

    //This is a helper method to see if a move made by a player is valid
    @Override
    public boolean isValidMove(int row, int col, Board gameboard) {
        // Check if the row and column are within bounds
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return false;
        }
        // Check if the cell is blank
        //System.out.println("Checking :"+ gameboard.getBoard()[row][col].getCellValue());
        return gameboard.getBoard()[row][col].getCellValue() == null;
    }

    //This is a helper method to see if the game is over
    @Override
    public boolean isGameOver(Board gameboard) {
        BoardCell[][] grid = gameboard.getBoard();

        //Check rows
        for (int r = 0; r < rows; r++) {
            if (checkRow(r, 0, 0, 1,rows)) {
                return true;
            }
        }


        //Check columns
        for (int c = 0; c < cols; c++) {
            if (checkRow(0, c, 1, 0,cols)) {
                return true;
            }
        }

        // Check top left to bottom right diagonal
        int diagnoalLen = 0;
        if(rows>=cols){
            diagnoalLen = rows;
        }
        else{
            diagnoalLen = cols;
        }
        if (checkRow(0, 0, 1, 1,diagnoalLen)) {
            return true;
        }
        // Check bottom left to top right Diagonal
        if (checkRow(rows-1, 0, -1, 1,diagnoalLen)) {
            return true;
        }

        //if no winner was found
        return false;
    }

    // Method to check if there is a row, col, or diagonal in a row in any direction
    public boolean checkRow(int startRow, int startCol, int rowIncrement, int colIncrement, int length) {
        BoardCell[][] grid = gameboard.getBoard();

        // Make sure the starting point is valid
        if (startRow < 0 || startRow >= rows || startCol < 0 || startCol >= cols) {
            return false;
        }

        Object firstCell = grid[startRow][startCol].getCellValue();

        if (firstCell == null) {
            return false;  // skip blank cells
        }

        for (int i = 1; i < length; i++) {
            Object cellVal = grid[startRow + i * rowIncrement][startCol + i * colIncrement].getCellValue();
            if (cellVal == null){
                return  false;
            }
            if (!cellVal.toString().equals(firstCell.toString())) {
                return false;
            }
        }

        return true;
    }


}
