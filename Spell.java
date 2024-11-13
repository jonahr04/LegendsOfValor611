// Jonah Rothman
// Spell.java
// This class defines general spell attributes, serving as a base for specific spell types like fire, ice, and lightning.

public abstract class Spell implements Item {
    private String name;
    private int price;
    private int level;
    private int damage;
    private int manaCost;

    public Spell(String name, int price, int level, int damage, int manaCost) {
        this.name = name;
        this.price = price;
        this.level = level;
        this.damage = damage;
        this.manaCost = manaCost;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getRequiredLevel() { return level; }
    public int getDamage() { return damage; }
    public int getManaCost() { return manaCost; }
    public String getType() { return "Spell"; }

    public abstract void useSpell(Hero hero, Monster target);
}
