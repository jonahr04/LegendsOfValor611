//Jonah Rothman
//BoardCell.java
//This method stores the board cells that the board is made of

public class BoardCell {
    private Object piece; // Could be any piece type (XPiece, OPiece, etc.)

    // Default Constructor
    public BoardCell() {
        this.piece = null; // No piece by default
    }

    // Overloaded Constructor
    public BoardCell(Object input) {
        piece = input;
    }

    public void setPiece(Object piece) {
        this.piece = piece;
    }

    // Getter method for cell value
    public Object getCellValue() {
        return this.piece;
    }

    @Override
    public String toString() {
        if(piece == null){
            return " ";
        }
        return piece.toString();
    }
}
