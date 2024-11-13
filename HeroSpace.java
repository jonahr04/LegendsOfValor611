// Jonah Rothman
// HeroSpace.java
// Represents a game board space occupied by a hero, facilitating hero-related interactions on the board.

public class HeroSpace extends BoardCell{
    //The piece that will be in HeroSpaces are blank Strings
    private final String piece;;

    //Default Constructor
    public HeroSpace() {
        piece  = "\u001B[33;1mH\u001B[0m";
    }

    @Override
    public String toString() {
        return piece;
    }
}