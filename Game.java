//Jonah Rothman
//Game.Java
//This is an interface that TicTacToe and OrderAndChaos implement and are instantiated with

public interface Game {
    void play(Player players[]);

    boolean isValidMove(int row, int col, Board gameboard);

    boolean isGameOver(Board gameboard);
}
