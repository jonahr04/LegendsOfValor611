//Jonah Rothman
//Player.Java
//Player is a class for player objects which stores their name and a hashmap for their win stats
import java.util.HashMap;

public class Player {
    private String name;
    private HashMap<String, Integer> scores; // Store scores for each game

    // Constructor
    public Player(String name) {
        this.name = name;
        this.scores = new HashMap<>();
    }

    // Get the player's name
    public String getName() {
        return name;
    }

    // Update the player's score for a specific game
    public void incrementScore(String gameName) {
        scores.put(gameName, scores.getOrDefault(gameName, 0) + 1);
    }

    // Get the player's score for a specific game
    public int getScore(String gameName) {
        return scores.getOrDefault(gameName, 0);
    }

    // Get all the scores for different games
    public HashMap<String, Integer> getScores() {
        return scores;
    }
}
