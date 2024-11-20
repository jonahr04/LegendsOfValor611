
public class MonsterSpace extends BoardCell{
    //The piece that will be in HeroSpaces are blank Strings
    private final String piece;;

    //Default Constructor
    public MonsterSpace() {
        piece  = "\u001B[33;1mM\u001B[0m";
    }


    @Override
    public String toString() {
        return piece;
    }
}