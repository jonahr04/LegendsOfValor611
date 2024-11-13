//Jonah Rothman
//GameRunner.Java
//This is the GameRunner class. It is the interface the user uses to pick the game and display stats

import java.util.HashMap;
import java.util.Scanner;

public class GameRunner {
    Scanner scanner = new Scanner(System.in);

    // Array of Players
    private Player[] players;


    //Default Constructor
    public GameRunner(){
        // Initialize two players
        players = new Player[2];
        players[0] = new Player("Player 1"); // Player 1 uses X
        players[1] = new Player("Player 2"); // Player 2 uses O
    }

    //This method displays options for the user and runs the game
    public void start() {
        boolean keepPlaying = true;

        while (keepPlaying) {
            System.out.println("Choose a game to play:");
            System.out.println("1. Tic-Tac-Toe");
            System.out.println("2. Order and Chaos");
            System.out.println("3. Super Tic Tac Toe");
            System.out.println("4. Monsters and Heros");
            System.out.println("5. Display Statistics");
            System.out.println("q. Quit");

            String choice = scanner.nextLine().toLowerCase();
            switch (choice) {
                case "1":
                    playTicTacToe();
                    break;
                case "2":
                    playOrderAndChaos();
                    break;
                case "3":
                    playSuperTicTacToe();
                    break;
                case "4":
                    playMonstersAndHeros();
                    break;
                case "5":
                    displayStats();
                    break;
                case "q":
                    keepPlaying = false;
                    System.out.println("Exiting the game. Thank you for playing!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1,2,3,4, or q.");
            }
        }

    }

    //This method launches a tic tac toe game
    private void playTicTacToe() {

        System.out.println("Select Board Size");
        System.out.println("1. 3x3");
        System.out.println("2. Custom n x n (Max 10)");

        int choice;
        boolean valid = false;

        TicTacToe ticTacToe = null;
        while (!valid) {
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear the invalid input
                continue; // Restart the loop
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    ticTacToe = new TicTacToe(); //Use the default constructor
                    valid = true;
                    break;
                case 2:
                    System.out.print("What is your n value? :");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        scanner.next(); // Clear the invalid input
                        continue; // Restart the loop
                    }
                    int boardSize = scanner.nextInt(); // Make it so it make sure its an int
                    if (boardSize>10) {
                        System.out.println("Invalid input. Please enter size 10 or less.");
                        scanner.next(); // Clear the invalid input
                        continue; // Restart the loop
                    }

                    ticTacToe = new TicTacToe(boardSize,boardSize); // Use overloaded constructor
                    valid = true;
                    break;
                default:
                    valid = false;
                    System.out.println("Invalid choice. Please select 1 or 2");
            }
        }


        ticTacToe.play(players);

    }

    //This method launches an order and chaos game
    private void playOrderAndChaos() {
        OrderAndChaos orderAndChaos = new OrderAndChaos(); // Fixed size 6x6 as per requirements
        orderAndChaos.play(players);
    }

    private void playSuperTicTacToe(){
        SuperTicTacToe superTicTacToe = new SuperTicTacToe();
        superTicTacToe.play(players);
    }
    //This method displays the game statistics

    private void playMonstersAndHeros() {
        MonstersAndHeros monstersAndHeros = new MonstersAndHeros();
        monstersAndHeros.play(players);
    }

    private void displayStats() {
        HashMap<String, Integer> P1Score = players[0].getScores();
        HashMap<String, Integer> P2Score = players[1].getScores();

        System.out.println("\nGame Summary:\n");
        System.out.println("-------------------------------------------------------");
        System.out.println("Tic-Tac-Toe: \t\tPlayer 1 Wins: " + P1Score.getOrDefault("Tic-Tac-Toe",0) + ", Player 2 Wins: " + P2Score.getOrDefault("Tic-Tac-Toe",0));
        System.out.println("Order and Chaos: \tPlayer 1 Wins: " + P1Score.getOrDefault("Order and Chaos",0) + ", Player 2 Wins: " + P2Score.getOrDefault("Order and Chaos",0));
        System.out.println("Super Tic Tac Toe: \tPlayer 1 Wins: " + P1Score.getOrDefault("Super Tic Tac Toe",0) + ", Player 2 Wins: " + P2Score.getOrDefault("Super Tic Tac Toe",0));
        System.out.println("-------------------------------------------------------\n");
    }
}
