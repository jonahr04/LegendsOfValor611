// Jonah Rothman
// Hero.java
// Base class for all hero types, containing common attributes and methods shared among hero subclasses.

public class Hero implements Character {
    protected String name;
    protected int HP;
    protected int mana;
    protected int strength;
    protected int BASE_MANA;
    protected int BASE_HP;
    protected int agility;
    protected int dexterity;
    protected int money;
    protected int experiencePoints;
    protected int experienceLevel;
    protected Inventory inventory;

    // Constructor that assigns base stats
    public Hero(String name, int HP, int mana, int strength, int agility, int dexterity, int money, int experiencePoints) {
        this.name = name;
        this.HP = HP;
        this.mana = mana;
        this.strength = strength;
        this.BASE_MANA = mana;
        this.BASE_HP = HP;
        this.agility = agility;
        this.dexterity = dexterity;
        this.money = money;
        this.experiencePoints = experiencePoints;
        this.experienceLevel = 1;
        this.inventory = new Inventory();
    }

    // Common methods shared across all heroes
    public String getName() {
        return (name);
    }

    public String getType() {
        return "Hero";
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int Hp) { this.HP = Hp; }

    public void increaseHP(int HP) {
        this.HP += HP;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int Mana) { mana = Mana; }

    public void increaseMana(int MANA) { this.mana += MANA; }

    public int getStrength() {
        return strength;
    }

    public void increaseStrength(int STRENGTH) { this.strength += STRENGTH; }

    public int getBaseMana() {
        return BASE_MANA;
    }

    public int getBaseHP() { return BASE_HP; }

    public int getAgility() {
        return agility;
    }

    public void increaseAgility(int AGILITY) { this.agility += AGILITY; }

    public int getDexterity() {
        return dexterity;
    }

    public void increaseDexterity(int DEXTERITY) { this.dexterity += DEXTERITY; }

    public int getMoney() {
        return money;
    }

    public int getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(int experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void takeDamage(int damage) {
        HP -= damage;
        if (HP < 0) {
            HP=0;
        }
    }

    public void recoverHP(double scale){
        scale++;
        HP *= scale;
    }

    public void recoverMP(double scale){
        scale++;
        mana *= scale;
    }

    public void gainExperiencePoints(int points) {
        experiencePoints += points;
    }

    public void gainMoney(int dollars) {
        money += dollars;
    }

    public boolean hasEquippedArmor(){
        for(Item item : inventory.getItems()){
            if(item instanceof Armor){
                if(((Armor) item).isEquipped()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasEquippedWeapon(){
        for(Item item : inventory.getItems()){
            if(item instanceof Weapon){
                if(((Weapon) item).isEquipped()) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getArmorReduction(){
        for(Item item : inventory.getItems()){
            if(item instanceof Armor){
                if(((Armor) item).isEquipped()) {
                    return ((Armor) item).getDamageReduction();
                }
            }
        }
        return 0;
    }

    public int getHandsAvailable(){
        int handsAvailable = 2;

        //Calculate Hands Available
        for(Item item : inventory.getItems()) {
            if(item instanceof Weapon){
                if(((Equipable) item).isEquipped()){
                    handsAvailable -= ((Weapon) item).getHandsRequired();
                }
            }
        }

        return handsAvailable;
    }
}
