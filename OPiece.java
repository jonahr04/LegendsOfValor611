//Jonah Rothman
//OPiece.Java
//O piece is a class for the O piece objects which extends the Board cell class


public class OPiece extends BoardCell {
    private final String piece;

    //Default Constructor
    public OPiece(){
        piece = "\u001B[34mO\u001B[0m";
    }

    @Override
    public String toString() {
        return piece;
    }
}