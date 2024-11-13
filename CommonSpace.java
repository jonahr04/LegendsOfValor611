//Jonah Rothman
//CommonSpace.Java
//This is class that is for common spaces

public class CommonSpace extends BoardCell{
    //The piece that will be in CommonSpaces are blank Strings
    private final String piece;;

    //Default Constructor
    public CommonSpace() {
        piece  = " ";
    }

    @Override
    public String toString() {
        return piece;
    }
}