// Jonah Rothman
// InaccessibleSpace.java
// Represents a space on the game board that is inaccessible to heroes and monsters, acting as an obstacle.

public class InaccessibleSpace extends BoardCell {

    //The piece that will be in InaccessibleSpaces are Red X peices
    private final String piece;;

    //Default Constructor
    public InaccessibleSpace(){
        piece  = "\u001B[31mX\u001B[0m";
    }

    @Override
    public String toString() {
        return piece;
    }
}
