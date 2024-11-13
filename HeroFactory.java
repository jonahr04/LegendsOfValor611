// Jonah Rothman
// HeroFactory.java
// Factory class responsible for creating hero objects of different types (e.g., Warrior, Sorcerer).

import java.util.Random;

public class HeroFactory {

    // Arrays of possible names and titles
    private static final String[] heroTypes = {"Warrior", "Sorcerer", "Paladin"};
    private static final String[] names = {
            "Sean", "Luna", "Drake", "Frey", "Ayoub", "Jonah", "Mira", "Bran", "Nyla", "Thor",
            "Arwen", "Cedric", "Damon", "Elara", "Finn", "Gwen", "Hector", "Ivy", "Jarek", "Kyra",
            "Leif", "Maia", "Nico", "Olwen", "Pavel", "Quinn", "Rhea", "Soren", "Talia", "Ulric"
    };
    private static final String[] titles = {
            "the Tomany", "the Wise", "the Yassine", "the Rothman", "the Fearless",
            "the Mighty", "the Cunning", "the Bold", "the Merciful", "the Fierce",
            "the Just", "the Noble", "the Valiant", "the Steadfast", "the Resolute",
            "the Unyielding", "the Protector", "the Vigilant", "the Fearless"
    };


    // Factory method to create a hero with a random name and title
    public static Hero getHero(String heroType) {
        Random randName = new Random();
        Random randTitle = new Random();


        // Select a random name and title
        String name = names[randName.nextInt(names.length)];
        String title = titles[randTitle.nextInt(titles.length)];
        String fullName = name + " " + title;

        // Create the hero based on the specified hero type
        switch (heroType.toLowerCase()) {
            case "warrior":
                return new Warrior(fullName);
            case "sorcerer":
                return new Sorcerer(fullName);
            case "paladin":
                return new Paladin(fullName);
        }

        return null;
    }

    // Method to generate a completely random hero (random type, name, and title)
    public static Hero getRandomHero() {
        Random rand = new Random();

        // Randomly select a hero type
        String heroType = heroTypes[rand.nextInt(heroTypes.length)];

        // Use getHero to create a hero with a random name and title
        return getHero(heroType);
    }
}
