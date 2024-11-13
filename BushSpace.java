// Jonah Rothman
// BushSpace.java
// This class models a bush cell on the game board

public class BushSpace extends BoardCell {
    private final String piece;

    // Default constructor
    public BushSpace() {
        this.piece = "\u001B[32mB\u001B[0m"; // Green 'B' for market space on the board
    }

    @Override
    public String toString() {
        return piece;
    }
}
