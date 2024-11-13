// Jonah Rothman
// Item.java
// This interface represents a general item in the game, defining common methods for items used by heroes.

public interface Item {
    String getName();
    int getPrice();
    int getRequiredLevel();
    String getType();
}
