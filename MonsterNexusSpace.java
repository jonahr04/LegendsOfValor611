// Jonah Rothman
// MonsterNexusSpace.java

public class MonsterNexusSpace extends BoardCell {
    private final String piece;

    // Default constructor
    public MonsterNexusSpace() {
        this.piece = "\u001B[31mN\u001B[0m"; // Red 'N' for Nexus space on the board
    }

    @Override
    public String toString() {
        return piece;
    }
}
