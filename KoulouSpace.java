// Jonah Rothman
// KoulouSpace.java
// This class models a Koulou cell on the game board

public class KoulouSpace extends BoardCell {
    private final String piece;

    // Default constructor
    public KoulouSpace() {
        this.piece = "\u001B[33mK\u001B[0m"; // Yellow 'K' for market space on the board
    }

    @Override
    public String toString() {
        return piece;
    }
}
