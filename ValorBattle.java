import java.util.List;

import static java.lang.Math.abs;

public class ValorBattle {
    private final LegendsOfValor game;

    private List<Monster> monsters;
    List<int[]> monstersPositions;
    private final int[][] nexusPositions = {{0, 0}, {0, 3}, {0, 6}};

    int round = 0;
    int roundsForNewMonsters = 5;

    ValorBattle(LegendsOfValor game) {
        this.game = game;
    }

    List<int[]> getMonstersPositions() {
        return monstersPositions;
    }

    boolean withinAttackRange(int heroIdx, int monsterIdx){
        // Whether an enemy is within range to attack.
        return abs(game.playerPositions[heroIdx][0] - monstersPositions.get(monsterIdx)[0]) < 1 && abs(game.playerPositions[heroIdx][1] - monstersPositions.get(monsterIdx)[1]) < 1;
    }


    void heroMove(Hero hero, Monster monster, int action){
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
    }


    void round(){
        if(round % roundsForNewMonsters == 0){
            createNewMonsters();
        }
        heroesMove(); // Move or Attack Monsters
        monstersMove(); // Move or Attack Heroes
        round++;
    }

    void heroesMove(){
        // prompt, call game.move/purchase & heroMove
        for(int heroIdx = 0; heroIdx < game.getHeroes().size(); heroIdx++){
            while(!game.getHeroAction(heroIdx)) /* Enter Again */;
        }
    }

    void monstersMove(){
        // Move or attack heroes by chance
    }

    void monsterMove(Hero hero, Monster monster){

    }

    void createNewMonsters(){
        for (int i = 0; i < nexusPositions.length; i++) {
            Hero hero = game.getHeroes().get(i);
            monsters.add(MonsterFactory.getRandomMonster(hero.getExperienceLevel()));
            monstersPositions.add(nexusPositions[i]);
        }
    }
}
