// Jonah Rothman
// CaveSpace.java
// This class models a cave cell on the game board

public class CaveSpace extends BoardCell {
    private final String piece;

    // Default CaveSpace
    public CaveSpace() {
        this.piece = "\u001B[90mC\u001B[0m"; // Light gray 'C' for cave space on the board
    }

    @Override
    public String toString() {
        return piece;
    }
}
