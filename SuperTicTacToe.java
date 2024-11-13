//Jonah Rothman
//SuperTicTacToe.Java
//This is class that have the methods needed to run Super Tic Tac Toe


import java.util.Scanner;

public class SuperTicTacToe implements  Game{

    private Board[][] superBoard;
    private Board displayBoard;
    final int rows=3;
    final int cols=3;

    //Default constructor initialized the display and superboard
    public SuperTicTacToe(){
        superBoard = new Board[3][3]; // Create 3x3 array of Board objects

        // Initialize each Board inside the array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                superBoard[i][j] = new Board(); // Initialize each Board
            }
        }

        displayBoard = new Board();
        char boardCount = 'A';

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                displayBoard.updateBoard(i,j,new BoardCell(boardCount));// Initialize each Board
                boardCount++;
            }
        }
    }

    //This method handles the functionality of playing the game
    @Override
    public void play(Player[] players) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nHello, Welcome to Super Tic Tac Toe!\n" +
                "To play the game you will alternate turns, and will be prompted to choose a board A-I, \n" +
                "and then your move to make. First player to win 3 sub games in a row wins.\n");


        int playerTurn = 2;
        BoardCell currentPlayerPiece = null;


        //Loop for each players turn, played until game is over
        do{

            //Switch players turn at the start
            if(playerTurn == 1){
                playerTurn = 2;
            }
            else{
                playerTurn = 1;
            }

            //Set current player symbol based on player turn
            if (playerTurn == 1) {
                currentPlayerPiece  = new XPiece();
            } else {
                currentPlayerPiece  = new OPiece();
            }

            boolean hasMadeMove = false;
            int[] move = null;

            //Loop until player has made their turn
            while(!hasMadeMove) {
                //Show super Board
                displayBoard.displayBoard();

                //Prompt User to open Board
                char selectedBoard = openBoard(playerTurn, currentPlayerPiece);

                //Display The selected Board
                int index = selectedBoard - 'A';  // 'A' corresponds to 0
                int row = index / 3;         // Row is determined by dividing by 3
                int col = index % 3;         // Column is the remainder when divided by 3

                System.out.println("\t  Board: " + selectedBoard);
                superBoard[row][col].displayBoard();

                //Give user option to make a move or go back to see super board
                System.out.println("Choose an option:");
                System.out.println("1. Make a move");
                System.out.println("2. Go back to see the super board");

                boolean validInput = false;

                while (!validInput) {
                    String userChoice = scanner.nextLine();

                    switch (userChoice) {
                        case "1":
                            hasMadeMove = true;

                            //Wont allow a move on a full board
                            if(superBoard[row][col].isBoardFull()){
                                System.out.println("Board is full! cannot make move");
                                validInput = true;
                                continue;
                            }else {
                                move = getPlayerMove(playerTurn, currentPlayerPiece, superBoard[row][col]);
                                // update the board with the player's move
                                superBoard[row][col].updateBoard(move[0], move[1], currentPlayerPiece);
                            }
                            validInput = true;
                            break;
                        case "2":
                            //Restart loop
                            validInput = true;
                            break;
                        default:
                            System.out.println("Invalid choice. Please select 1 or 2.");
                    }
                }

                //Once User has made thier turn see if they won that sub Board Game
                if (isGameOver(superBoard[row][col])) {
                    System.out.println("\n\nPlayer " + playerTurn + " (" + currentPlayerPiece + ") wins on board " + selectedBoard + "!");
                    displayBoard.updateBoard(row,col,currentPlayerPiece);
                }

                //If Board is full call it a draw and update the display board to reflect
                if(superBoard[row][col].isBoardFull() && !isGameOver(superBoard[row][col])){
                    System.out.println("\nThe game for board " + selectedBoard + " ends in a draw! it will be removed from the superboard");
                    displayBoard.updateBoard(row,col,new BoardCell());
                }
            }

        }while(!isGameOver(displayBoard));

       //Update Winners
        displayBoard.displayBoard();
        System.out.println("Player " + playerTurn + " (" + currentPlayerPiece + ") wins!");

        //Update player win count
        if(playerTurn==1){
            players[0].incrementScore("Super Tic Tac Toe");
        }
        else{
            players[1].incrementScore("Super Tic Tac Toe");
        }

    }

    //This Method asks user which board they would like opened and will return the letter as a char
    public char openBoard(int playerTurn, BoardCell currentPlayerPiece){
        Scanner scanner = new Scanner(System.in);
        //Prompt User to open Board
        System.out.println("Player " + playerTurn + " (" + currentPlayerPiece + "), it's your turn.");
        System.out.println("Which Tic Tac Toe Game would you like to open? (A-I)");
        char selectedGame = ' ';
        boolean validInput = false;

        //Make sure game letter is valid
        while (!validInput) {
            // Read user input as a String, take the first char, and convert to uppercase
            String input = scanner.nextLine().toUpperCase();
            if (!input.isEmpty()) {
                selectedGame = input.charAt(0); // Get the first character
            }

            // Check if the input is between 'A' and 'I'
            if (selectedGame >= 'A' && selectedGame <= 'I') {
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter a letter between A and I.");
            }
        }

        return selectedGame;
    }

    //This Method prompts the user for their sub board move and returns array containing idicies
    public int[] getPlayerMove(int playerTurn, BoardCell currentPlayerPiece, Board gameboard){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player " + playerTurn + " (" + currentPlayerPiece + "), it's your turn.");
        System.out.println("Enter your move (row and column): ");

        int rowMove = 0;
        int colMove = 0;

        boolean validMove = false;

        //Make sure input is valid loop
        while (!validMove) {
            //Input Row
            System.out.print("Row (1-3): ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear the invalid input
                continue; // Restart the loop
            }
            rowMove = scanner.nextInt();
            rowMove--; //decrase for bounds

            // Input Col
            System.out.print("Column (1-3): ");
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

        return new int[]{rowMove, colMove};
    }

    //This is a helper method to see if a move made by a player is valid
    @Override
    public boolean isValidMove(int row, int col, Board gameboard) {
        // Check if the row and column are within bounds
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
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
            if (checkRow(r, 0, 0, 1,rows,gameboard)) {
                return true;
            }
        }


        //Check columns
        for (int c = 0; c < cols; c++) {
            if (checkRow(0, c, 1, 0,cols,gameboard)) {
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
        if (checkRow(0, 0, 1, 1,diagnoalLen,gameboard)) {
            return true;
        }
        // Check bottom left to top right Diagonal
        if (checkRow(rows-1, 0, -1, 1,diagnoalLen,gameboard)) {
            return true;
        }

        //if no winner was found
        return false;
    }

    // Method to check if there is a row, col, or diagonal in a row in any direction
    public boolean checkRow(int startRow, int startCol, int rowIncrement, int colIncrement, int length, Board gameboard) {
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
