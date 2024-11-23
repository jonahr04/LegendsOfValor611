import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class ValorBattle {
    private final LegendsOfValor game;
    BattleManager battleManager;

    private List<Monster> monsters;
    List<int[]> monstersPositions;

    boolean isGameOver;


    int round = 0;
    int roundsForNewMonsters = 4;

    ValorBattle(LegendsOfValor game) {
        this.game = game;
        battleManager = new BattleManager(game.getHeroes(), monsters);
        monsters = new ArrayList<>();
        monstersPositions = new ArrayList<>();
    }

    List<int[]> getMonstersPositions() {
        return monstersPositions;
    }

    List<Monster> getMonsters() {
        return monsters;
    }

    boolean withinAttackRange(int heroIdx, int monsterIdx){
        // Whether an enemy is within range to attack.
        return abs(game.playerPositions[heroIdx][0] - monstersPositions.get(monsterIdx)[0]) <= 1 && abs(game.playerPositions[heroIdx][1] - monstersPositions.get(monsterIdx)[1]) <= 1;
    }


    void heroMove(int heroIdx, int monsterIdx, int action){
        Hero hero = game.getHeroes().get(heroIdx);
        Monster monster = monsters.get(monsterIdx);
        ActionStrategy actionStrategy;
        switch (action){
            case 1:
                actionStrategy = new AttackStrategy();
                break;
            case 2:
                actionStrategy = new CastSpellStrategy();
                break;
            case 3:
                actionStrategy = new UsePotionStrategy();
                break;
            default:
                System.out.println("Invalid action Type");
                return;
        }
        actionStrategy.execute(hero, monster);
        // If monster is dead: remove it from the list
        if(monster.getHP() <= 0){
            int monsterLevel = monster.getLevel();
            System.out.println("\u001B[91m\n" + monster.getName() + " has been killed!\n\u001B[0m");
            monsters.remove(monster);
            monstersPositions.remove(monsterIdx);
            hero.gainExperiencePoints(monsterLevel);  // Gain experience points
            hero.gainMoney(monsterLevel*100);  // Gain gold
        }
    }


    void round(){
        if(round % roundsForNewMonsters == 0){
            createNewMonsters();
        }
        heroesMove();
        if(isGameOver){
            return;
        }
        monstersMove();
        if(isGameOver){
            return;
        }
        battleManager.endOfRoundRecovery();
        battleManager.levelUpPlayers();
        round++;
    }

    void heroesMove(){
        // prompt, call game.move/purchase & heroMove
        for(int heroIdx = 0; heroIdx < game.getHeroes().size() && !isGameOver; heroIdx++){
            game.displayBoard();
            while(!game.getHeroAction(heroIdx)) {
                game.displayBoard();
            }
            if(game.playerPositions[heroIdx][0] == 0){
                isGameOver = true;
            }
        }
    }

    void monstersMove(){
        // Move or attack heroes by chance
        for(int monsterIdx = 0; monsterIdx < monsters.size() && !isGameOver; monsterIdx++){
            int heroIdx = monstersPositions.get(monsterIdx)[1]/3;
            if(withinAttackRange(heroIdx, monsterIdx)){
                monsterMove(heroIdx, monsterIdx);
            }else{
                monsterPosMove(monsterIdx);
            }

        }

    }

    void monsterMove(int heroIdx, int monsterIdx){
        Hero hero = game.getHeroes().get(heroIdx);
        Monster monster = monsters.get(monsterIdx);
        if (monster.getHP() > 0) {
            battleManager.performMonsterAction(monster, hero);
        }
        // If hero is dead: initialize its position & reset MP
        if(hero.getHP() <= 0){
            System.out.println("\u001B[91m\n" + hero.getName() + " has been killed!\n\u001B[0m");
            resPawnHero(heroIdx);
        }
    }

    void resPawnHero(int heroIdx){
        Hero hero = game.getHeroes().get(heroIdx);
        hero.setHP(hero.getBaseHP() / 2);
        hero.setMana(hero.getBaseMana() / 2);
        game.playerPositions[heroIdx]= game.nexusBeginPositions[heroIdx];
    }

    void monsterPosMove(int monsterIdx){
        System.out.println("Monster " + monsters.get(monsterIdx).getName() + " moves forward!");
        monstersPositions.get(monsterIdx)[0]++;
        if(monstersPositions.get(monsterIdx)[0] == 7){
            isGameOver = true;
        }
    }

    void createNewMonsters(){
        System.out.println("New monsters created!");
        for (int i = 0; i < game.nexusEndPositions.length; i++) {
            Hero hero = game.getHeroes().get(i);
            monsters.add(MonsterFactory.getRandomMonster(hero.getExperienceLevel()));
            monstersPositions.add(new int[]{game.nexusEndPositions[i][0], game.nexusEndPositions[i][1]});
        }
    }

}
