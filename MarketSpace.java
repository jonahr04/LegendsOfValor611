// Jonah Rothman
// MarketSpace.java
// This class models a market cell on the game board where heroes can enter to purchase or sell items.

public class MarketSpace extends BoardCell {
    private final String piece;
    private final Market market; // Each MarketSpace has a unique Market instance

    // Default constructor
    public MarketSpace() {
        this.piece = "\u001B[32mM\u001B[0m"; // Green 'M' for market space on the board
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
