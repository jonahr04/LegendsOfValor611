// Jonah Rothman
// Monster.java
// This base class for monsters defines shared attributes and scaling for different types of monsters.

public class Monster implements Character{
    private String name;
    private int level;
    private int damage;
    private int defense;
    private double dodgeChance;
    private int HP;

    // Constructor that assigns base stats
    public Monster(String name, int HP, int level, int monsterLevel, int damage, int defense, int dodgeChance) {
        this.name = name;
        this.HP = HP;
        scaleAttributes(level,monsterLevel,damage,defense,dodgeChance);
    }

    private void scaleAttributes(int level, int monsterLevel, int damage, int defense, int dodgeChance) {
        double levelRatio = ((double) level / monsterLevel);
        double scaleFactor = 0;

        //take more away down scalling than up scalling
        if(levelRatio<1) {
            scaleFactor = levelRatio * 1.4;
        }else{
            scaleFactor = 0.5 * levelRatio;

        }
        this.level = level;

        this.damage = (int) (damage * (scaleFactor)*0.9);
        //Cap Monster damage at 800
        if(this.damage > 800) {
            this.damage = 800;
        }

        this.defense = (int) (defense * scaleFactor * 0.9);
        this.dodgeChance = Math.min(0.7, (dodgeChance * scaleFactor * 0.01)); //Never go above 80 percent
        this.HP = level * 95;
    }

    public String getName() { return (name + " The Monster"); }
    public String getRawName() { return (name); }
    public int getLevel() { return level; }
    public int getDamage() { return damage; }
    public void reduceDamage(int damage) {
        this.damage -= damage;
        if(this.damage <= 0) {
            this.damage = 0;
        }
    }
    public int getDefense() { return defense; }
    public void reduceDefense(int damage) {
        this.defense -= damage;
        if(this.defense <= 0) {
            this.defense = 0;
        }
    }
    public double getDodgeChance() { return dodgeChance; }
    public void reduceDodgeChance(double chance) {
        this.dodgeChance -= chance;
        if(this.dodgeChance <= 0) {
            this.dodgeChance = 0;
        }
    }
    public int getHP() { return HP; }
    public void takeDamage(int damage) { this.HP = Math.max(0, HP - damage); }
}