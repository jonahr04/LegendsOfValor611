// Jonah Rothman
// NexusSpace.java
// This class models a bush cell on the game board

public class NexusSpace extends BoardCell {
    private final String piece;
    private final Market market; // Each MarketSpace has a unique Market instance

    // Default constructor
    public NexusSpace() {
        this.piece = "\u001B[34mN\u001B[0m"; // Blue 'N' for Nexus space on the board
        this.market = new Market(); // Initialize with a unique Market instance
    }

    // Accessor method for the Market
    public Market getMarket() {
        return market;
    }

    @Override
    public String toString() {
        return piece;
    }
}
