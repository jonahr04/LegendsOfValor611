//Jonah Rothman
//XPiece.Java
//X piece is a class for the O piece objects which extends the Board cell class

public class XPiece extends BoardCell {
    private final String piece;

    //Default Constructor
    public XPiece(){
        piece = "\u001B[31mX\u001B[0m";
    }

    @Override
    public String toString() {
        return piece;
    }
}